package com.t13max.data.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 岛屿实体数据
 *
 * @author: t13max
 * @since: 15:06 2024/7/17
 */
@Data
public class IslandData {

    //岛屿id
    private int id;
    //岛屿名
    private String name;
    //岛主uuid
    private String uuid;
    //岛屿的坐标
    private long pos;
    //权限集合 玩家的唯一id -> 权限 (目前最多64个权限)
    private Map<UUID, Long> authorityMap = new HashMap<>();

    public IslandData() {
    }

    public IslandData(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.pos = resultSet.getLong("pos");
        this.uuid = resultSet.getString("uuid");
        this.authorityMap = JSONObject.parseObject(resultSet.getBytes("authorityMap"), Map.class);
    }
}
