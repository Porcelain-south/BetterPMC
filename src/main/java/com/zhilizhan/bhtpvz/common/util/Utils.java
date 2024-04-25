package com.zhilizhan.bhtpvz.common.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class Utils {
    public static void setWorldTime(ServerLevel serverworld, Integer time) {
        if (time >= 0 && time <= 24000) {
            int days = getTotalDaysPassed(serverworld);
            serverworld.setDayTime((long)(time + days * 24000L));
        }
    }
    public static int getTotalDaysPassed(ServerLevel serverworld) {
        int currenttime = (int) serverworld.getDayTime();
        return (int)Math.floor((double)currenttime / 24000.0);
    }
    public static void sendMessage(Player player, String m, ChatFormatting colour) {
        if (!m.isEmpty()) {
            TextComponent message = new TextComponent(m);
            message.withStyle(colour);
            player.sendMessage(message, player.getUUID());
        }
    }
}
