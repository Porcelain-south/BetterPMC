package com.zhilizhan.bhtpvz.client;

import com.hungteen.pvz.client.model.baked.PVZBakedModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class BHTPvZClientRegister {
    public BHTPvZClientRegister() {
    }



    @SubscribeEvent
    public static void reigsterRenderType(FMLClientSetupEvent ev) {
        // ItemBlockRenderTypes.setRenderLayer((Block) BHTPvZBlocks.STEEL_PUMPKIN.get(), RenderType.translucent());//有bug
        // ItemBlockRenderTypes.setRenderLayer((Block) BHTPvZBlocks.SCREEN_DOOR.get(), RenderType.translucent());//半透明
    }

    private static Pair<BakedModel, ModelResourceLocation> getBakedModel(ModelBakeEvent ev, Item item) {
        Map<ResourceLocation, BakedModel> modelRegistry = ev.getModelRegistry();
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        BakedModel model = (BakedModel)modelRegistry.get(location);
        if (model == null) {
            throw new RuntimeException("Did not find Obsidian Hidden in registry");
        } else if (model instanceof PVZBakedModel) {
            throw new RuntimeException("Tried to replaceObsidian Hidden twice");
        } else {
            return Pair.of(model, location);
        }
    }
}
