package com.t13max.data.model;

import lombok.Data;

import java.util.List;

/**
 * 玩家数据实体
 * 此实体存在玩家身上
 *
 * @author: t13max
 * @since: 14:58 2024/7/17
 */
@Data
public class PlayerData {

    //玩家目前已取得的权限 (比如飞行, 死亡不掉落等)
    private long authority;
    //累计在线时间
    private int onlineSeconds;
    //上次登录时间
    private int lastLoginSeconds;
    //所拥有的岛屿的id
    private List<Integer> islandList;
    //拥有正常游完的权限的岛屿的id
    private List<Integer> friendIslandList;

}
