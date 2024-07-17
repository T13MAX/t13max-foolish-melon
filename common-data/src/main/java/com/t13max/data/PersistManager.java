package com.t13max.data;

import com.alibaba.fastjson.JSONObject;
import com.t13max.common.config.PluginConfig;
import com.t13max.common.exception.DataException;
import com.t13max.common.manager.ManagerBase;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PluginData;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;


/**
 * 持久化管理器
 * 先使用SqlLite 原生存储 省事 后续考虑使用反射?
 * 如果后续数据量大 考虑使用MongoDB
 *
 * @author: t13max
 * @since: 13:56 2024/7/17
 */

public class PersistManager extends ManagerBase {

    //Hikari数据源
    private HikariDataSource dataSource;

    public static PersistManager inst() {
        return inst(PersistManager.class);
    }

    @Override
    protected void onShutdown() {
        dataSource.close();
    }

    @Override
    public void init() {

        String url = PluginConfig.CONFIG.getDbUrl(); // 数据库文件路径
        int poolSize = PluginConfig.CONFIG.getPoolSize();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(poolSize);  // 设置连接池的最大连接数

        dataSource = new HikariDataSource(config);


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            //校验是否需要创建表
            checkCreateTable(statement);
            //校验是否需要改表
            checkAlertTable(statement);
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new DataException(e);
        }

    }

    /**
     * 新建表
     *
     * @Author t13max
     * @Date 15:52 2024/7/17
     */
    private void checkCreateTable(Statement statement) throws SQLException {
        //表不多 也不大 临时写在这吧 懒得读文件了
        String sql = "CREATE TABLE IF NOT EXISTS IslandData (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "x INTEGER NOT NULL," +
                "y INTEGER NOT NULL," +
                "authorityMap BLOB NOT NULL)";
        statement.execute(sql);
    }

    /**
     * 表增加字段
     *
     * @Author t13max
     * @Date 15:52 2024/7/17
     */
    private void checkAlertTable(Statement statement) throws SQLException {
        //后续完善 优化方案
    }

    /**
     * 获取连接
     *
     * @Author t13max
     * @Date 15:53 2024/7/17
     */
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 获取空岛数据
     *
     * @Author t13max
     * @Date 16:04 2024/7/17
     */
    public IslandData getIslandData(int id) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IslandData where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new IslandData(resultSet);
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
        return null;
    }

    /**
     * 增
     *
     * @Author t13max
     * @Date 16:13 2024/7/17
     */
    public boolean saveIslandData(IslandData islandData) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO IslandData (id, name, pos, authorityMap, uuid) values (?,?,?,?,?)");
            statement.setInt(1, islandData.getId());
            statement.setString(2, islandData.getName());
            statement.setLong(3, islandData.getPos());
            statement.setBytes(4, JSONObject.toJSONBytes(islandData.getAuthorityMap()));
            statement.setString(5, islandData.getUuid());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    /**
     * 改
     *
     * @Author t13max
     * @Date 16:13 2024/7/17
     */
    public boolean updateIslandData(IslandData islandData) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE IslandData set name=?, pos=?, authorityMap=?, uuid=? where id=?");
            statement.setString(1, islandData.getName());
            statement.setLong(2, islandData.getPos());
            statement.setBytes(3, JSONObject.toJSONBytes(islandData.getAuthorityMap()));
            statement.setString(4, islandData.getUuid());
            statement.setInt(5, islandData.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    /**
     * 获取插件持久化数据
     *
     * @Author t13max
     * @Date 16:04 2024/7/17
     */
    public PluginData getPluginData(int id) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PluginData where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new PluginData(resultSet);
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
        return null;
    }

    /**
     * 增
     *
     * @Author t13max
     * @Date 16:13 2024/7/17
     */
    public boolean savePluginData(PluginData pluginData) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PluginData (id, index) values (?,?)");
            statement.setInt(1, pluginData.getId());
            statement.setInt(2, pluginData.getIndex());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    /**
     * 改
     *
     * @Author t13max
     * @Date 16:13 2024/7/17
     */
    public boolean updatePluginData(PluginData pluginData) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE PluginData set index=? where id=?");
            statement.setInt(1, pluginData.getIndex());
            statement.setInt(2, pluginData.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}

