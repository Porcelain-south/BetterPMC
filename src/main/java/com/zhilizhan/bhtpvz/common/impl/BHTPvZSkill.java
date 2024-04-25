package com.zhilizhan.bhtpvz.common.impl;

import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.ArrayUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BHTPvZSkill extends SkillTypes {
    private static final Map<String, ISkillType> SKILL_MAP = new HashMap<>();
    public static final String SKILL_TAG = "paz_skill_tag";
    public static final int COOL_DOWN_LEVEL = 5;
    public static final ISkillType GRASS_CARP_HEAL_SPEED = new SkillType("grass_carp_heal_speed", ArrayUtil.getAverageArray(3, 6.0F, 2.0F), Arrays.asList(5,7));
    public static final ISkillType GRASS_CARP_HEAL_RANGE = new SkillType("grass_carp_heal_rage", ArrayUtil.getAverageArray(4, 4F, 7F), Arrays.asList(4, 6, 8));
    public static final ISkillType GRASS_CARP_MORE_HEALTH = new SkillType("grass_carp_more_health", ArrayUtil.getAverageArray(3, 30.0F, 50.0F), Arrays.asList(2, 3));

    public BHTPvZSkill() {
    }

    public static void registerSkillType(ISkillType type) {
        SKILL_MAP.put(type.getIdentity(), type);
    }

    public static int getSkillLevel(CompoundTag nbt, ISkillType type) {
        return nbt.contains(type.getIdentity()) ? nbt.getInt(type.getIdentity()) : 0;
    }

    public static int getSkillLevel(ItemStack stack, ISkillType type) {
        if (stack.getOrCreateTag().contains("paz_skill_tag")) {
            CompoundTag tmp = stack.getOrCreateTag().getCompound("paz_skill_tag");
            if (tmp.contains(type.getIdentity())) {
                return tmp.getInt(type.getIdentity());
            }
        }

        return 0;
    }

    public static void addSkillLevel(ItemStack stack, ISkillType type, int lvl) {
        CompoundTag tmp;
        if (stack.getOrCreateTag().contains("paz_skill_tag")) {
            tmp = stack.getOrCreateTag().getCompound("paz_skill_tag");
            tmp.putInt(type.getIdentity(), lvl);
            stack.getOrCreateTag().put("paz_skill_tag", tmp);
        } else {
            tmp = new CompoundTag();
            tmp.putInt(type.getIdentity(), lvl);
            stack.getOrCreateTag().put("paz_skill_tag", tmp);
        }

    }

    public static void addSkillLevel(CompoundTag nbt, ISkillType type, int lvl) {
        CompoundTag tmp;
        if (nbt.contains("paz_skill_tag")) {
            tmp = nbt.getCompound("paz_skill_tag");
            tmp.putInt(type.getIdentity(), lvl);
            nbt.put("paz_skill_tag", tmp);
        } else {
            tmp = new CompoundTag();
            tmp.putInt(type.getIdentity(), lvl);
            nbt.put("paz_skill_tag", tmp);
        }

    }

    public static ISkillType getSkillType(String name) {
        return (ISkillType)SKILL_MAP.get(name);
    }

}