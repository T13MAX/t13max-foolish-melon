package com.t13max.plugin.authority;

import com.t13max.common.manager.ManagerBase;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PlayerData;
import com.t13max.plugin.memory.MemoryManager;
import com.t13max.plugin.persist.type.PlayerType;
import com.t13max.util.Log;
import org.bukkit.entity.Player;

/**
 * 权限管理器
 *
 * @author: t13max
 * @since: 16:41 2024/7/17
 */
public class AuthorityManager extends ManagerBase {

    @Override
    protected void onShutdown() {
        super.onShutdown();
    }

    @Override
    public void init() {
        super.init();
    }

    /**
     * 校验某个玩家是否拥有某个全局权限
     *
     * @Author t13max
     * @Date 16:50 2024/7/17
     */
    public boolean checkAuthority(Player player, byte id) {

        PlayerData playerData = MemoryManager.inst().getPersistentData(player, PlayerType.INS);
        return checkAuthority(playerData.getAuthority(), id);
    }

    /**
     * 校验玩家是否拥有某个岛屿的某个权限
     *
     * @Author t13max
     * @Date 17:00 2024/7/17
     */
    public boolean checkAuthority(Player player, IslandData islandData, byte id) {
        Long authority = islandData.getAuthorityMap().get(player.getUniqueId());
        if (authority == null) {
            return false;
        }
        return checkAuthority(authority, id);
    }

    /**
     * 根据给定字段和权限id判断是否拥有某个权限
     *
     * @Author t13max
     * @Date 16:58 2024/7/17
     */
    public boolean checkAuthority(long authority, byte id) {
        if (!isValid(id)) {
            Log.melon.error("校验权限入参id非法");
            return false;
        }
        return ((authority >> id) & 0x1) > 0;
    }

    /**
     * id是否合法
     *
     * @Author t13max
     * @Date 16:58 2024/7/17
     */
    public boolean isValid(byte id) {
        return id >= 0 && id < Long.SIZE;
    }
}
