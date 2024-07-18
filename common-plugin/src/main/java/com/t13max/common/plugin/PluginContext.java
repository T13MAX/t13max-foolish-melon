package com.t13max.common.plugin;

import com.t13max.common.config.PluginConfig;
import com.t13max.common.consts.Const;
import com.t13max.common.exception.CommonException;
import com.t13max.util.Log;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 * 插件上下文
 *
 * @Author: t13max
 * @Since: 21:39 2024/7/17
 */
public class PluginContext {

    public static PluginConfig CONFIG;

    /**
     * 插件enable的时候调用
     *
     * @Author: t13max
     * @Since: 21:42 2024/7/17
     */
    public static void onEnable() {

        //加载配置
        loadConfig();

        //其他
    }

    public static void onLoad(){
        //加载依赖jar
        loadLibraries();
    }

    /**
     * 加载依赖jar
     *
     * @Author: t13max
     * @Since: 7:57 2024/7/18
     */
    private static void loadLibraries() {
        // 加载依赖 JAR
        File libFolder = new File("./", "libs");
        if (libFolder.exists() && libFolder.isDirectory()) {
            try {
                loadLibraries(libFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void loadLibraries(File libFolder) throws IOException {
        Path libPath = libFolder.toPath();
        Files.walk(libPath)
                .filter(path -> path.toString().endsWith(".jar"))
                .forEach(PluginContext::addLibrary);
    }

    private static void addLibrary(Path path) {
        try {
            URL url = path.toUri().toURL();
            URLClassLoader classLoader = (URLClassLoader) PluginContext.class.getClassLoader();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, classLoader);
            Thread.currentThread().setContextClassLoader(urlClassLoader);
        } catch (IOException e) {
            e.printStackTrace();
            //getLogger().log(Level.SEVERE, "Failed to load library: " + path.getFileName(), e);
        }
    }

    /**
     * 加载配置文件
     *
     * @Author: t13max
     * @Since: 21:42 2024/7/17
     */
    private static void loadConfig() {

        Log.common.info("瓜岛插件, 加载配置...");

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
