/**
 * ScriptEntityPlus - Allow you to add script to any entities.
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
package com.github.yuttyann.scriptentityplus.json;

import com.github.yuttyann.scriptblockplus.file.json.annotation.JsonTag;
import com.github.yuttyann.scriptblockplus.file.json.basic.SingleJson;

import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@JsonTag(path = "json/entityscript", cachefileexists = true)
public class EntityScriptJson extends SingleJson<EntityScript> {

    private static final Set<UUID> EXISTING = ConcurrentHashMap.newKeySet();
    private static volatile boolean loaded;
    private final UUID uuid;

    public EntityScriptJson(@NotNull String name) {
        super(name, EntityScript::new);
        this.uuid = UUID.fromString(name);
    }

    @NotNull
    public static EntityScriptJson get(@NotNull UUID uuid) {
        return newJson(EntityScriptJson.class, uuid.toString());
    }

    public static boolean exists(@NotNull UUID uuid) {
        if (!loaded) {
            reloadExisting();
        }
        return EXISTING.contains(uuid);
    }

    public static void reloadExisting() {
        EXISTING.clear();
        for (var name : getNames(EntityScriptJson.class)) {
            try {
                EXISTING.add(UUID.fromString(name.substring(0, name.length() - ".json".length())));
            } catch (IllegalArgumentException ignored) { }
        }
        loaded = true;
    }

    public static void setExists(@NotNull UUID uuid, boolean exists) {
        if (exists) {
            EXISTING.add(uuid);
        } else {
            EXISTING.remove(uuid);
        }
    }

    @Override
    protected void onDeleteFile() {
        setExists(uuid, false);
    }

    @Override
    protected void onSaveJson() {
        setExists(uuid, true);
    }
}
