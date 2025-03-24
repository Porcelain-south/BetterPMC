package com.zhilizhan.bpmc.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;

public class Utils {
    public static void setWorldTime(ServerWorld serverworld, Integer time) {
        if (time >= 0 && time <= 24000) {
            int days = getTotalDaysPassed(serverworld);
            serverworld.setDayTime(time + days * 24000L);
        }
    }
    public static int getTotalDaysPassed(ServerWorld serverworld) {
        int currenttime = (int) serverworld.getDayTime();
        return (int)Math.floor((double)currenttime / 24000.0);
    }
    public static void sendMessage(PlayerEntity player, String m, TextFormatting colour) {
        if (!m.isEmpty()) {
            StringTextComponent message = new StringTextComponent(m);
            message.withStyle(colour);
            player.sendMessage(message, player.getUUID());
        }
    }
}
