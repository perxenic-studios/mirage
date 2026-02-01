package dev.perxenic.mirage.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.perxenic.mirage.Mirage.MODID;

public class MirageBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final BlockBehaviour.Properties FADED_TERRACOTTA_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_ORANGE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.4F)
            .pushReaction(PushReaction.PUSH_ONLY);

    public static final DeferredBlock<GlazedTerracottaBlock> FADED_SUN_TERRACOTTA = fadedTerracottaBlock("faded_sun_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_MODERN_TERRACOTTA = fadedTerracottaBlock("faded_modern_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_CROSS_TERRACOTTA = fadedTerracottaBlock("faded_cross_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_CRAWL_TERRACOTTA = fadedTerracottaBlock("faded_crawl_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_TARGET_TERRACOTTA = fadedTerracottaBlock("faded_target_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_POTION_TERRACOTTA = fadedTerracottaBlock("faded_potion_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_FISH_TERRACOTTA = fadedTerracottaBlock("faded_fish_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_FLOWER_TERRACOTTA = fadedTerracottaBlock("faded_flower_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_SPOKE_TERRACOTTA = fadedTerracottaBlock("faded_spoke_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_PLANT_TERRACOTTA = fadedTerracottaBlock("faded_plant_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_CREEP_TERRACOTTA = fadedTerracottaBlock("faded_creep_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_GEO_TERRACOTTA = fadedTerracottaBlock("faded_geo_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_FAN_TERRACOTTA = fadedTerracottaBlock("faded_fan_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_BLADE_TERRACOTTA = fadedTerracottaBlock("faded_blade_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_ARROW_TERRACOTTA = fadedTerracottaBlock("faded_arrow_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> FADED_LEAF_TERRACOTTA = fadedTerracottaBlock("faded_leaf_terracotta");

    public static final DeferredBlock<BrushableBlock> SUSPICIOUS_RED_SAND = BLOCKS.register("suspicious_red_sand", () -> new BrushableBlock(
            Blocks.RED_SAND,
            SoundEvents.BRUSH_SAND,
            SoundEvents.BRUSH_SAND_COMPLETED,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(0.25F)
                    .sound(SoundType.SUSPICIOUS_SAND)
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static DeferredBlock<GlazedTerracottaBlock> fadedTerracottaBlock(String name) {
        return BLOCKS.register(name, () -> new GlazedTerracottaBlock(FADED_TERRACOTTA_PROPERTIES));
    }

    public static void onBlockEntityTypeAddBlocksEvent(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.BRUSHABLE_BLOCK, SUSPICIOUS_RED_SAND.get());
    }
}
