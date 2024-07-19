package com.t13max.data;

import com.alibaba.fastjson.JSONObject;
import com.t13max.common.consts.Const;
import com.t13max.common.exception.DataException;
import com.t13max.common.manager.ManagerBase;
import com.t13max.common.plugin.PluginContext;
import com.t13max.common.util.Log;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PluginData;
import com.t13max.util.TextUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
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
        if (dataSource == null) {
            return;
        }
        dataSource.close();
    }

    @Override
    public void init() {

        String url = PluginContext.CONFIG.getDbUrl(); // 数据库文件路径
        int poolSize = PluginContext.CONFIG.getPoolSize();

        //校验是否需要创建表
        checkCreateTable();

        //校验是否需要改表
        checkAlertTable();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(poolSize);  // 设置连接池的最大连接数
        config.setAutoCommit(true);
        dataSource = new HikariDataSource(config);

    }

    /**
     * 新建表
     *
     * @Author t13max
     * @Date 15:52 2024/7/17
     */
    private void checkCreateTable() {

        File dbFolder = new File("./", "db");
        if (!dbFolder.exists()) {
            if (!dbFolder.mkdir()) {
                throw new DataException("db数据路径创建失败!");
            }
        }

        String url = PluginContext.CONFIG.getDbUrl();
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String sql = TextUtil.readSql(Const.SQL, PluginContext.CLASS_LOADER);
            boolean execute = statement.execute(sql);
            if (!execute) {
                //表已存在会返回false
                Log.persist.info("忽略... checkCreate, sql执行失败, 表已存在会返回失败");
            }
        } catch (Exception e) {
            throw new DataException("创建表失败, error=" + e.getMessage());
        }
    }

    /**
     * 表增加字段
     *
     * @Author t13max
     * @Date 15:52 2024/7/17
     */
    private void checkAlertTable() {
        //后续完善 优化方案
    }

    /**
     * 获取连接
     *
     * @Author t13max
     * @Date 15:53 2024/7/17
     */
    private Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new DataException("dataSource不存在");
        }
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PluginData (id, isIndex) values (?,?)");
            statement.setInt(1, pluginData.getId());
            statement.setInt(2, pluginData.getIsIndex());
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
            statement.setInt(1, pluginData.getIsIndex());
            statement.setInt(2, pluginData.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}

