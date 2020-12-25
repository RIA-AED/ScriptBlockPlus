package com.github.yuttyann.scriptblockplus.script.option.chat;

import com.github.yuttyann.scriptblockplus.ScriptBlock;
import com.github.yuttyann.scriptblockplus.enums.Permission;
import com.github.yuttyann.scriptblockplus.enums.reflection.PackageType;
import com.github.yuttyann.scriptblockplus.file.config.SBConfig;
import com.github.yuttyann.scriptblockplus.player.SBPlayer;
import com.github.yuttyann.scriptblockplus.script.option.BaseOption;
import com.github.yuttyann.scriptblockplus.script.option.Option;
import com.github.yuttyann.scriptblockplus.script.option.OptionTag;
import com.github.yuttyann.scriptblockplus.utils.StringUtils;
import com.github.yuttyann.scriptblockplus.utils.Utils;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

/**
 * ScriptBlockPlus ActionBar オプションクラス
 * @author yuttyann44581
 */
@OptionTag(name = "actionbar", syntax = "@actionbar:")
public class ActionBar extends BaseOption {

    @Override
    @NotNull
    public Option newInstance() {
        return new ActionBar();
    }

    @Override
    protected boolean isValid() throws Exception {
        String[] array = StringUtils.split(getOptionValue(), "/");
        String message = StringUtils.setColor(array[0]);

        if (array.length > 1) {
            int stay = Integer.parseInt(array[1]);
            new Task(stay, message).runTaskTimer(ScriptBlock.getInstance(), 0, 20);
        } else {
            send(getSBPlayer(), message);
        }
        return true;
    }

    public static void send(@NotNull SBPlayer sbPlayer, @NotNull String message) {
        if (Utils.isCBXXXorLater("1.12.2")) {
            String command = "title " + sbPlayer.getName() + " actionbar {\"text\":\"" + message + "\"}";
            Utils.tempPerm(sbPlayer, Permission.MINECRAFT_COMMAND_TITLE, () -> Utils.dispatchCommand(sbPlayer, command));
        } else if (Utils.isPlatform()) {
            try {
                String chatSerializer = "IChatBaseComponent$ChatSerializer";
                Method a = PackageType.NMS.getMethod(chatSerializer, "a", String.class);
                Object component = a.invoke(null, "{\"text\": \"" + message + "\"}");
                Object arg = (byte) 2;
                Class<?>[] classes = { PackageType.NMS.getClass("IChatBaseComponent"), byte.class };
                if (Utils.isCBXXXorLater("1.12")) {
                    arg = PackageType.NMS.getEnumValueOf("ChatMessageType", "GAME_INFO");
                    classes[1] = arg.getClass();
                }
                Constructor<?> packetPlayOutChat = PackageType.NMS.getConstructor("PacketPlayOutChat", classes);
                PackageType.sendPacket(sbPlayer.getPlayer(), packetPlayOutChat.newInstance(component, arg));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        } else {
            String platforms = SBConfig.PLATFORMS.getValue().stream().map(String::valueOf).collect(Collectors.joining(", "));
            throw new UnsupportedOperationException("Unsupported server. | Supported Servers <" + platforms + ">");
        }
    }

    private class Task extends BukkitRunnable {

        private final int stay;
        private final String message;

        private int tick;

        private Task(int stay, @NotNull String message) {
            this.tick = 0;
            this.stay = stay;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                if (!getSBPlayer().isOnline() || tick++ >= stay) {
                    cancel();
                }
                send(getSBPlayer(), isCancelled() ? "" : message);
            } catch (Exception e) {
                cancel();
            }
        }
    }
}