package com.github.yuttyann.scriptblockplus.option;

import org.bukkit.entity.Player;

import com.github.yuttyann.scriptblockplus.collplugin.CollPlugins;
import com.github.yuttyann.scriptblockplus.collplugin.VaultPermission;
import com.github.yuttyann.scriptblockplus.enums.PermType;

public class Perm {

	private String world;
	private String perm;
	private PermType permType;
	private VaultPermission permission;

	public Perm(String perm, PermType permType) {
		this(null, perm, permType);
	}

	public Perm(String world, String perm, PermType permType) {
		this.world = world;
		this.perm = perm;
		this.permType = permType;
		this.permission = CollPlugins.getVaultPermission();
	}

	public String getNode() {
		return perm;
	}

	public boolean playerPerm(Player player) {
		switch (permType) {
		case CHECK:
			if (world != null) {
				return permission.playerHas(world, player, getNode());
			}
			return permission.playerHas(player, getNode());
		case ADD:
			if (world != null) {
				return permission.playerAdd(world, player, getNode());
			}
			return permission.playerAdd(player, getNode());
		case REMOVE:
			if (world != null && permission.playerHas(world, player, getNode())) {
				return permission.playerRemove(world, player, getNode());
			} else if (world == null && permission.playerHas(player, getNode())) {
				return permission.playerRemove(player, getNode());
			}
		}
		return false;
	}
}