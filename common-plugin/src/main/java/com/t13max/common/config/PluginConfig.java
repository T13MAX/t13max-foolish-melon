package com.t13max.common.config;

import com.t13max.common.consts.Const;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

/**
 * 插件自定义配置
 *
 * @author: t13max
 * @since: 15:27 2024/7/17
 */
@Data
public class PluginConfig {

    public static final PluginConfig CONFIG;

    static {

        Yaml yaml = new Yaml();

        CONFIG = yaml.loadAs(PluginConfig.class.getClassLoader().getResourceAsStream(Const.CONFIG_NAME), PluginConfig.class);

        CONFIG.check();
    }

    private String dbUrl;

    private int poolSize;

    private int id;

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
