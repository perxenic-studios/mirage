package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.content.MirageItemTags;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MirageItemTagProvider extends ItemTagsProvider {
    public MirageItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Mirage.MIRAGE_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(MirageItems.ARMADILLO_CHESTPLATE.get());

        tag(ItemTags.SAND)
                .add(MirageItems.SUSPICIOUS_RED_SAND.get());

        tag(ItemTags.DECORATED_POT_INGREDIENTS).add(
                MirageItems.CRACKED_POTTERY_SHERD.get(),
                MirageItems.BLANK_POTTERY_SHERD.get(),
                MirageItems.HIDE_POTTERY_SHERD.get(),
                MirageItems.BARREN_POTTERY_SHERD.get()
        );
        tag(ItemTags.DECORATED_POT_SHERDS).add(
                MirageItems.CRACKED_POTTERY_SHERD.get(),
                MirageItems.BLANK_POTTERY_SHERD.get(),
                MirageItems.HIDE_POTTERY_SHERD.get(),
                MirageItems.BARREN_POTTERY_SHERD.get()
        );

        tag(MirageItemTags.BADLANDS_RUINS_SHERDS).add(
                Items.SKULL_POTTERY_SHERD,
                Items.PLENTY_POTTERY_SHERD,
                MirageItems.HIDE_POTTERY_SHERD.get()
        );
        tag(MirageItemTags.DESERT_RUINS_SHERDS).add(
                Items.SKULL_POTTERY_SHERD,
                Items.DANGER_POTTERY_SHERD,
                MirageItems.BARREN_POTTERY_SHERD.get()
        );
        tag(MirageItemTags.PLATEAU_RUINS_SHERDS).add(
                Items.PRIZE_POTTERY_SHERD,
                Items.PLENTY_POTTERY_SHERD,
                Items.MINER_POTTERY_SHERD,
                MirageItems.HIDE_POTTERY_SHERD.get()
        );
    }
}
