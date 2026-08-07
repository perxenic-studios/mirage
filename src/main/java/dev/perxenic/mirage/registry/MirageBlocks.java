package dev.perxenic.mirage.registry;

import com.google.common.collect.ImmutableList;
import dev.perxenic.mirage.content.blocks.PointedStoneBlock;
import dev.perxenic.mirage.content.blocks.ShortScorchedGrassBlock;
import dev.perxenic.mirage.content.blocks.TallScorchedGrassBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MIRAGE_ID);

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

    public static final List<DeferredBlock<GlazedTerracottaBlock>> FADED_TERRACOTTA = ImmutableList.of(
            FADED_SUN_TERRACOTTA,
            FADED_MODERN_TERRACOTTA,
            FADED_CROSS_TERRACOTTA,
            FADED_CRAWL_TERRACOTTA,
            FADED_TARGET_TERRACOTTA,
            FADED_POTION_TERRACOTTA,
            FADED_FISH_TERRACOTTA,
            FADED_FLOWER_TERRACOTTA,
            FADED_SPOKE_TERRACOTTA,
            FADED_PLANT_TERRACOTTA,
            FADED_CREEP_TERRACOTTA,
            FADED_GEO_TERRACOTTA,
            FADED_FAN_TERRACOTTA,
            FADED_BLADE_TERRACOTTA,
            FADED_ARROW_TERRACOTTA,
            FADED_LEAF_TERRACOTTA
    );

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
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("suspicious_red_sand")))
    ));

    public static final DeferredBlock<Block> SANDY_STONE = BLOCKS.register("sandy_stone", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("sandy_stone")))
    ));

    public static final DeferredBlock<Block> GILDED_CALCITE = BLOCKS.register("gilded_calcite", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("gilded_calcite")))
    ));

    public static final DeferredBlock<Block> SHORT_SCORCHED_GRASS = BLOCKS.register("short_scorched_grass", () -> new ShortScorchedGrassBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_DRY_GRASS)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("short_scorched_grass")))
    ));

    public static final DeferredBlock<Block> TALL_SCORCHED_GRASS = BLOCKS.register("tall_scorched_grass", () -> new TallScorchedGrassBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_DRY_GRASS)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("tall_scorched_grass")))
    ));

    public static final DeferredBlock<PointedStoneBlock> POINTED_SANDSTONE = BLOCKS.register("pointed_sandstone", () -> new PointedStoneBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE)
                    .mapColor(MapColor.SAND)
                    .setId(ResourceKey.create(Registries.BLOCK, mirageLoc("pointed_sandstone")))
    ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static DeferredBlock<GlazedTerracottaBlock> fadedTerracottaBlock(String name) {
        return BLOCKS.register(name, () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_ORANGE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .strength(1.4F)
                .pushReaction(PushReaction.PUSH_ONLY)
                .setId(ResourceKey.create(Registries.BLOCK, mirageLoc(name))))
        );
    }

    public static void onBlockEntityTypeAddBlocksEvent(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.BRUSHABLE_BLOCK, SUSPICIOUS_RED_SAND.get());
    }
}
