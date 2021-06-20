/**
 * ScriptBlockPlus - Allow you to add script to any blocks.
 * Copyright (C) 2021 yuttyann44581
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.yuttyann.scriptblockplus.command;

import java.util.List;

import com.github.yuttyann.scriptblockplus.enums.Permission;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * ScriptBlockPlus SubCommand コマンドクラス
 * @author yuttyann44581
 */
public abstract class SubCommand {

    private final BaseCommand baseCommand;

    public SubCommand(@NotNull BaseCommand baseCommand) {
        this.baseCommand = baseCommand;
    }

    @NotNull
    protected abstract List<String> getNames();

    @NotNull
    protected abstract List<CommandUsage> getUsages();

    protected abstract boolean runCommand(@NotNull CommandSender sender, @NotNull String label, String[] args);

    protected final boolean hasPermission(@NotNull CommandSender sender, @NotNull Permission permission) {
        return baseCommand.hasPermission(sender, permission);
    }

    protected final boolean hasPermission(@NotNull CommandSender sender, @NotNull Permission permission, boolean isPlayer) {
        return baseCommand.hasPermission(sender, permission, isPlayer);
    }

    protected final boolean isPlayer(@NotNull CommandSender sender) {
        return baseCommand.isPlayer(sender);
    }

    protected final boolean compare(@NotNull String source, @NotNull String text) {
        return baseCommand.compare(source, text);
    }

    protected final boolean compare(@NotNull String source, @NotNull String... texts) {
        return baseCommand.compare(source, texts);
    }

    protected final boolean compare(@NotNull String[] args, final int index, @NotNull String text) {
        return baseCommand.compare(args, index, text);
    }

    protected final boolean compare(@NotNull String[] args, final int index, @NotNull String... texts) {
        return baseCommand.compare(args, index, texts);
    }

    protected final boolean range(@NotNull String[] args, final int end) {
        return baseCommand.range(args, end);
    }

    protected final boolean range(@NotNull String[] args, final int start, final int end) {
        return baseCommand.range(args, start, end);
    }

    @NotNull
    protected final String get(@NotNull String[] args, final int index) {
        return baseCommand.get(args, index);
    }
}