package com.t13max.plugin.consts;

import lombok.Getter;
import net.kyori.adventure.text.format.TextColor;

/**
 * 颜色
 *
 * @author: t13max
 * @since: 13:10 2024/7/17
 */
public interface MelonTextColor {

    TextColor RED = TextColor.color(255, 0, 0);

    TextColor GREEN = TextColor.color(0, 255, 0);

    TextColor BLUE = TextColor.color(0, 0, 255);

    TextColor WHITE = TextColor.color(255, 255, 255);

    TextColor DARK = TextColor.color(0, 0, 0);

}
