package com.t13max.plugin.persist.type;

import com.t13max.data.model.PlayerData;
import lombok.Getter;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家数据 序列化 反序列化
 *
 * @author: t13max
 * @since: 14:58 2024/7/17
 */
public class PlayerType implements PersistentDataType<byte[], PlayerData> {

    public static PlayerType INS = new PlayerType();

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<PlayerData> getComplexType() {
        return PlayerData.class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull PlayerData complex, @NotNull PersistentDataAdapterContext context) {
        return new byte[0];
    }

    @Override
    public @NotNull PlayerData fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext context) {
        return null;
    }
}
