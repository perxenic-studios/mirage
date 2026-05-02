package dev.perxenic.mirage.registry;

import dev.perxenic.mirage.MirageConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MIRAGE_ID);

    public static final DeferredItem<BundleItem> ARMADILLO_BASKET = ITEMS.register("armadillo_basket",
            () -> new BundleItem(new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)
                    .setId(ResourceKey.create(Registries.ITEM, mirageLoc("armadillo_basket")))
            )
    );

    public static final DeferredItem<Item> ARMADILLO_CHESTPLATE = ITEMS.register("armadillo_chestplate",
            () -> new Item(new Item.Properties()
                    .humanoidArmor(MirageArmorMaterials.ARMADILLO, ArmorType.CHESTPLATE)
                    .setId(ResourceKey.create(Registries.ITEM, mirageLoc("armadillo_chestplate")))
            )
    );

    public static final DeferredItem<Item> CRACKED_POTTERY_SHERD = ITEMS.registerSimpleItem("cracked_pottery_sherd");
    public static final DeferredItem<Item> BLANK_POTTERY_SHERD = ITEMS.registerSimpleItem("blank_pottery_sherd");
    public static final DeferredItem<Item> HIDE_POTTERY_SHERD = ITEMS.registerSimpleItem("hide_pottery_sherd");
    public static final DeferredItem<Item> BARREN_POTTERY_SHERD = ITEMS.registerSimpleItem("barren_pottery_sherd");
    public static final DeferredItem<Item> SANCTUARY_POTTERY_SHERD = ITEMS.registerSimpleItem("sanctuary_pottery_sherd");

    public static final DeferredItem<BlockItem> SUSPICIOUS_RED_SAND = ITEMS.registerSimpleBlockItem(MirageBlocks.SUSPICIOUS_RED_SAND);
    public static final DeferredItem<BlockItem> SANDY_STONE = ITEMS.registerSimpleBlockItem(MirageBlocks.SANDY_STONE);
    public static final DeferredItem<BlockItem> GILDED_CALCITE = ITEMS.registerSimpleBlockItem(MirageBlocks.GILDED_CALCITE);
    public static final DeferredItem<BlockItem> SHORT_SCORCHED_GRASS = ITEMS.registerSimpleBlockItem(MirageBlocks.SHORT_SCORCHED_GRASS);
    public static final DeferredItem<BlockItem> TALL_SCORCHED_GRASS = ITEMS.registerSimpleBlockItem(MirageBlocks.TALL_SCORCHED_GRASS);

    public static void register(IEventBus eventBus) {
        for (DeferredBlock<GlazedTerracottaBlock> block : MirageBlocks.FADED_TERRACOTTA) {
            ITEMS.registerSimpleBlockItem(block);
        }

        ITEMS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            simpleInsertAfter(event, Items.TURTLE_HELMET, ARMADILLO_CHESTPLATE);
        }
        else if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            if (MirageConfig.commonBoolDict.get("fadedTerracottaCreative")) {
                simpleInsertAfter(event, Items.TERRACOTTA, MirageBlocks.FADED_TERRACOTTA.getFirst());

                for (int i = 1; i < MirageBlocks.FADED_TERRACOTTA.size(); i++)
                    simpleInsertAfter(
                            event,
                            MirageBlocks.FADED_TERRACOTTA.get(i - 1),
                            MirageBlocks.FADED_TERRACOTTA.get(i)
                    );
            }
        }
        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            simpleInsertAfter(event, Items.BUNDLE, ARMADILLO_BASKET);
        }
        else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            simpleInsertAfter(event, Blocks.SUSPICIOUS_GRAVEL, SUSPICIOUS_RED_SAND);
        }
        else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            simpleInsertBefore(event, Items.ANGLER_POTTERY_SHERD, BLANK_POTTERY_SHERD);
            simpleInsertBefore(event, BLANK_POTTERY_SHERD, CRACKED_POTTERY_SHERD);
            simpleInsertAfter(event, Items.SNORT_POTTERY_SHERD, HIDE_POTTERY_SHERD);
            simpleInsertAfter(event, HIDE_POTTERY_SHERD, BARREN_POTTERY_SHERD);
            simpleInsertAfter(event, BARREN_POTTERY_SHERD, SANCTUARY_POTTERY_SHERD);
        }
        else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            simpleInsertAfter(event, Items.DRY_SHORT_GRASS, MirageItems.SHORT_SCORCHED_GRASS);
            simpleInsertAfter(event, Items.DRY_TALL_GRASS, MirageItems.TALL_SCORCHED_GRASS);
        }
    }

    public static void simpleInsertBefore(BuildCreativeModeTabContentsEvent event, ItemLike existing, ItemLike after) {
        event.insertBefore(new ItemStack(existing), new ItemStack(after), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    public static void simpleInsertAfter(BuildCreativeModeTabContentsEvent event, ItemLike existing, ItemLike after) {
        event.insertAfter(new ItemStack(existing), new ItemStack(after), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
