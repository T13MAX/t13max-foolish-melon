package com.t13max.common.config;

import lombok.Data;

/**
 * 插件自定义配置
 *
 * @author: t13max
 * @since: 15:27 2024/7/17
 */
@Data
public class PluginConfig {

    private int id;

    private String dbUrl;

    private int poolSize;

    public PluginConfig() {

    }

    /**
     * 校验数据
     *
     * @Author t13max
     * @Date 18:59 2024/5/23
     */
    public boolean check() {
        return true;
    }
}
