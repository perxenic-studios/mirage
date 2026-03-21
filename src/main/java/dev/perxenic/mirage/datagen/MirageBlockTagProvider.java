package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MirageBlockTagProvider extends BlockTagsProvider {
    public MirageBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Mirage.MIRAGE_ID);
    }


    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        Block[] terracottaBlocks = new Block[MirageBlocks.FADED_TERRACOTTA.size()];
        for (int i = 0; i < MirageBlocks.FADED_TERRACOTTA.size(); i++) {
            terracottaBlocks[i] = MirageBlocks.FADED_TERRACOTTA.get(i).get();
        }

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(terracottaBlocks);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        tag(BlockTags.SAND)
                .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        //tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
        //        .add(terracottaBlocks)
        //        .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        tag(BlockTags.AZALEA_GROWS_ON)
                .add(terracottaBlocks)
                .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        //tag(BlockTags.BAMBOO_PLANTABLE_ON)
        //        .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());

        tag(BlockTags.CAMEL_SAND_STEP_SOUND_BLOCKS)
                .add(MirageBlocks.SUSPICIOUS_RED_SAND.get());
    }
}
