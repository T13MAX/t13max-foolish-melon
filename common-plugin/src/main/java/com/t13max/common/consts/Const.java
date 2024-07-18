package com.t13max.common.consts;

/**
 * 常量
 *
 * @Author: t13max
 * @Since: 22:21 2024/7/15
 */
public interface Const {
    //出生Y轴高度
    int SPAWN_Y = 64;
    //岛屿半径
    int ISLAND_RADIUS = 4096;
    //岛屿边长
    int ISLAND_SIDE = ISLAND_RADIUS * 2 + 1;
    //岛屿数量
    int ISLAND_NUM = 1000;
    //区块边界大小
    int CHUNK_SIDE_LENGTH = 16;
    //菜单用箱子大小 54格
    int CHEST_SIZE_54 = 54;
    //菜单用箱子大小 27格
    int CHEST_SIZE_27 = 27;
    //配置文件名
    String CONFIG_NAME = "config.yaml";
    //建表的sql文件
    String SQL = "create.sql";

}
