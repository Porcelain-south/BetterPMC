package com.zhilizhan.bhtpvz.common.item;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BHTPvZSpawnEggItem extends SpawnEggItem {
	protected static final List<BHTPvZSpawnEggItem> PVZ_EGGS = new ArrayList<>();

	private final Lazy<? extends EntityType<?>> entityTypeSupplier;

	public BHTPvZSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder) {
		super(null, primaryColorIn, secondaryColorIn, builder);
		this.entityTypeSupplier=Lazy.of(entityTypeSupplier);
		PVZ_EGGS.add(this);
	}

	/**
	 * Adds all the supplier based spawn eggs to vanilla's map and registers an
	 * IDispenseItemBehavior for each of them as normal spawn eggs have one
	 * registered for each of them during
	 * but supplier based ones won't have had their EntityTypes created yet.
	 */
	public static void initUnaddedEggs() {
		final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
		DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {
			public ItemStack execute(BlockSource source, ItemStack stack) {
				Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
				EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
				entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
				stack.shrink(1);
				return stack;
			}
		};
		for (final SpawnEggItem egg : PVZ_EGGS) {
			EGGS.put(egg.getType(null), egg);
			DispenserBlock.registerBehavior(egg, defaultDispenseItemBehavior);
			// ItemColors for each spawn egg don't need to be registered because this method is called before ItemColors is created
		}
		PVZ_EGGS.clear();
	}

	@Override
	public EntityType<?> getType(CompoundTag nbt) {
		return this.entityTypeSupplier.get();
	}
}
