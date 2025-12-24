package dev.perxenic.mirage.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.DyeColor;
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

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final BlockBehaviour.Properties FADED_TERRACOTTA_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_ORANGE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.4F)
            .pushReaction(PushReaction.PUSH_ONLY);

    public static final DeferredBlock<GlazedTerracottaBlock> WHITE_FADED_TERRACOTTA = fadedTerracottaBlock("white_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> LIGHT_GRAY_FADED_TERRACOTTA = fadedTerracottaBlock("light_gray_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> GRAY_FADED_TERRACOTTA = fadedTerracottaBlock("gray_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> BLACK_FADED_TERRACOTTA = fadedTerracottaBlock("black_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> BROWN_FADED_TERRACOTTA = fadedTerracottaBlock("brown_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> RED_FADED_TERRACOTTA = fadedTerracottaBlock("red_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> ORANGE_FADED_TERRACOTTA = fadedTerracottaBlock("orange_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> YELLOW_FADED_TERRACOTTA = fadedTerracottaBlock("yellow_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> LIME_FADED_TERRACOTTA = fadedTerracottaBlock("lime_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> GREEN_FADED_TERRACOTTA = fadedTerracottaBlock("green_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> CYAN_FADED_TERRACOTTA = fadedTerracottaBlock("cyan_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> LIGHT_BLUE_FADED_TERRACOTTA = fadedTerracottaBlock("light_blue_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> BLUE_FADED_TERRACOTTA = fadedTerracottaBlock("blue_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> PURPLE_FADED_TERRACOTTA = fadedTerracottaBlock("purple_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> MAGENTA_FADED_TERRACOTTA = fadedTerracottaBlock("magenta_faded_terracotta");
    public static final DeferredBlock<GlazedTerracottaBlock> PINK_FADED_TERRACOTTA = fadedTerracottaBlock("pink_faded_terracotta");

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
