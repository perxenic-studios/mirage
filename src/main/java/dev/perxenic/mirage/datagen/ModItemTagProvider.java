package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.content.MirageItemTags;
import dev.perxenic.mirage.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Mirage.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ItemTags.DECORATED_POT_INGREDIENTS).add(
                ModItems.SHATTERED_POTTERY_SHERD.get(),
                ModItems.BLANK_POTTERY_SHERD.get(),
                ModItems.ARMADILLO_POTTERY_SHERD.get(),
                ModItems.CACTUS_POTTERY_SHERD.get()
        );
        tag(ItemTags.DECORATED_POT_SHERDS).add(
                ModItems.SHATTERED_POTTERY_SHERD.get(),
                ModItems.BLANK_POTTERY_SHERD.get(),
                ModItems.ARMADILLO_POTTERY_SHERD.get(),
                ModItems.CACTUS_POTTERY_SHERD.get()
        );

        tag(MirageItemTags.BADLANDS_RUINS_SHERDS).add(
                Items.SKULL_POTTERY_SHERD,
                Items.PLENTY_POTTERY_SHERD,
                ModItems.ARMADILLO_POTTERY_SHERD.get()
        );
        tag(MirageItemTags.DESERT_RUINS_SHERDS).add(
                Items.SKULL_POTTERY_SHERD,
                Items.DANGER_POTTERY_SHERD,
                ModItems.ARMADILLO_POTTERY_SHERD.get()
        );
        tag(MirageItemTags.PLATEAU_RUINS_SHERDS).add(
                Items.PRIZE_POTTERY_SHERD,
                Items.PLENTY_POTTERY_SHERD,
                Items.MINER_POTTERY_SHERD,
                ModItems.ARMADILLO_POTTERY_SHERD.get()
        );
    }
}
