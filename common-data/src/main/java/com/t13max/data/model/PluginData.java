package com.t13max.data.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 插件持久化数据
 *
 * @author: t13max
 * @since: 15:54 2024/7/17
 */
@Data
public class PluginData {

    private int id = 1;

    //岛屿生成到第多少个了 根据这个判断下一个往哪里生成
    private int index = 10000;

    public PluginData() {
    }

    public PluginData(int id) {
        this.id = id;
    }

    public PluginData(ResultSet resultSet) throws SQLException {
        this.index = resultSet.getInt("index");
    }
}
