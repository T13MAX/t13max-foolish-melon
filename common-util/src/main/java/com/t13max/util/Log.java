package com.t13max.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: t13max
 * @since: 14:23 2024/5/23
 */
public class Log {
    public static Logger def = LogManager.getLogger("DEFAULT");
    public static Logger common = LogManager.getLogger("COMMON");
    public static Logger melon = LogManager.getLogger("PLUGIN_MELON");


}
