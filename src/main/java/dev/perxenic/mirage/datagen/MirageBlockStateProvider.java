package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.util.MirageBlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageBlockStateProvider extends BlockStateProvider {
    public MirageBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MIRAGE_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredBlock<GlazedTerracottaBlock> block : MirageBlocks.FADED_TERRACOTTA) {
            MirageBlockStateHelper.fadedTerracotta(this, block);
        }

        MirageBlockStateHelper.brushableBlock(
                this,
                MirageBlocks.SUSPICIOUS_RED_SAND.get(),
                "suspicious_red_sand"
        );
    }
}
