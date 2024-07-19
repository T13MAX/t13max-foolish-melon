package com.t13max.common.plugin;

import com.t13max.common.config.PluginConfig;
import com.t13max.common.consts.Const;
import com.t13max.common.exception.CommonException;
import com.t13max.common.util.Log;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

/**
 * 插件上下文
 *
 * @Author: t13max
 * @Since: 21:39 2024/7/17
 */
public class PluginContext {

    //瓜岛配置
    public static PluginConfig CONFIG;

    //插件的类加载器
    public static ClassLoader CLASS_LOADER;

    /**
     * 插件enable的时候调用
     *
     * @Author: t13max
     * @Since: 21:42 2024/7/17
     */
    public static void onEnable(ClassLoader classLoader) {

        //加载配置
        loadConfig();

        CLASS_LOADER = classLoader;
        //其他
    }

    /**
     * 加载配置文件
     *
     * @Author: t13max
     * @Since: 21:42 2024/7/17
     */
    private static void loadConfig() {

        Log.melon.info("瓜岛插件, 加载配置...");

        ClassLoader classLoader = PluginConfig.class.getClassLoader();

        Yaml yaml = new Yaml(new CustomClassLoaderConstructor(classLoader, new LoaderOptions()));

        try {

            CONFIG = yaml.loadAs(classLoader.getResourceAsStream(Const.CONFIG_NAME), PluginConfig.class);
        } catch (Exception e) {
            throw new CommonException(e);
        }

        CONFIG.check();
    }
}
