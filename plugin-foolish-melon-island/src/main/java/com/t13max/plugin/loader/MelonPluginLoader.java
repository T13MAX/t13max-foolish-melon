package com.t13max.plugin.loader;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.JarLibrary;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * 瓜岛插件的类加载器
 * 直接在paper-plugin.yml中添加依赖 最终还是ClassNotFound 很气
 * <p>
 * libraries:
 * - com.alibaba.fastjson2:fastjson2-extension:2.0.92
 * - com.alibaba.fastjson2:fastjson2:2.0.92
 * - com.alibaba:fastjson:2.0.92
 * - org.apache.logging.log4j:log4j-api:2.17.22
 * - org.apache.logging.log4j:log4j-core:2.17.22
 * - com.zaxxer:HikariCP:5.0.12
 *
 * @author: t13max
 * @since: 13:50 2024/7/18
 */
public class MelonPluginLoader implements PluginLoader {

    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {

        getJarLibrary().forEach(classpathBuilder::addLibrary);

        //MavenLibraryResolver resolver = new MavenLibraryResolver();
        //resolver.addDependency(new Dependency(new DefaultArtifact("com.example:example:version"), null));
        //resolver.addRepository(new RemoteRepository.Builder("paper", "default", "https://repo.papermc.io/repository/maven-public/").build());
        //classpathBuilder.addLibrary(resolver);
    }

    private List<JarLibrary> getJarLibrary() {
        List<JarLibrary> result = new LinkedList<>();
        //fastjson
        result.add(new JarLibrary(Path.of("../libs/fastjson2-extension-2.0.9.jar")));
        result.add(new JarLibrary(Path.of("../libs/fastjson2-2.0.9.jar")));
        result.add(new JarLibrary(Path.of("../libs/fastjson-2.0.9.jar")));
        //HikariCP
        result.add(new JarLibrary(Path.of("../libs/HikariCP-5.0.1.jar")));
        //log4j
        result.add(new JarLibrary(Path.of("../libs/log4j-api-2.17.2.jar")));
        result.add(new JarLibrary(Path.of("../libs/log4j-core-2.17.2.jar")));
        return result;
    }
}
