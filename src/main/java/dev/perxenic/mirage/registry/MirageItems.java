package dev.perxenic.mirage.registry;

import dev.perxenic.mirage.MirageConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;

public class MirageItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MIRAGE_ID);

    public static final DeferredItem<BundleItem> ARMADILLO_BASKET = ITEMS.register("armadillo_basket", () -> new BundleItem(
            new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).requiredFeatures(FeatureFlags.BUNDLE)
    ));

    public static final DeferredItem<ArmorItem> ARMADILLO_CHESTPLATE = ITEMS.register("armadillo_chestplate", () -> new ArmorItem(
            MirageArmorMaterials.ARMADILLO,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(5))
    ));

    public static final DeferredItem<Item> CRACKED_POTTERY_SHERD = ITEMS.register("cracked_pottery_sherd",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> BLANK_POTTERY_SHERD = ITEMS.register("blank_pottery_sherd",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> HIDE_POTTERY_SHERD = ITEMS.register("hide_pottery_sherd",
            () -> new Item(new Item.Properties())
    );
    public static final DeferredItem<Item> BARREN_POTTERY_SHERD = ITEMS.register("barren_pottery_sherd",
            () -> new Item(new Item.Properties())
    );

    public static final DeferredItem<BlockItem> WHITE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_SUN_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIGHT_GRAY_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_MODERN_TERRACOTTA);
    public static final DeferredItem<BlockItem> GRAY_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_CROSS_TERRACOTTA);
    public static final DeferredItem<BlockItem> BLACK_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_CRAWL_TERRACOTTA);
    public static final DeferredItem<BlockItem> BROWN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_TARGET_TERRACOTTA);
    public static final DeferredItem<BlockItem> RED_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_POTION_TERRACOTTA);
    public static final DeferredItem<BlockItem> ORANGE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_FISH_TERRACOTTA);
    public static final DeferredItem<BlockItem> YELLOW_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_FLOWER_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIME_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_SPOKE_TERRACOTTA);
    public static final DeferredItem<BlockItem> GREEN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_PLANT_TERRACOTTA);
    public static final DeferredItem<BlockItem> CYAN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_CREEP_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIGHT_BLUE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_GEO_TERRACOTTA);
    public static final DeferredItem<BlockItem> BLUE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_FAN_TERRACOTTA);
    public static final DeferredItem<BlockItem> PURPLE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_BLADE_TERRACOTTA);
    public static final DeferredItem<BlockItem> MAGENTA_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_ARROW_TERRACOTTA);
    public static final DeferredItem<BlockItem> PINK_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(MirageBlocks.FADED_LEAF_TERRACOTTA);

    public static final DeferredItem<BlockItem> SUSPICIOUS_RED_SAND = ITEMS.registerSimpleBlockItem(MirageBlocks.SUSPICIOUS_RED_SAND);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            simpleInsertAfter(event, Items.TURTLE_HELMET, ARMADILLO_CHESTPLATE);
        }
        else if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            if (MirageConfig.fadedTerracottaCreative) {
                simpleInsertAfter(event, Items.TERRACOTTA, MirageBlocks.FADED_TERRACOTTA.getFirst());

                for (int i = 1; i < MirageBlocks.FADED_TERRACOTTA.size(); i++)
                    simpleInsertAfter(
                            event,
                            MirageBlocks.FADED_TERRACOTTA.get(i - 1),
                            MirageBlocks.FADED_TERRACOTTA.get(i)
                    );
            }
        }
        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES && event.getFlags().contains(FeatureFlags.BUNDLE)) {
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
        }
    }

    public static void simpleInsertBefore(BuildCreativeModeTabContentsEvent event, ItemLike existing, ItemLike after) {
        event.insertBefore(new ItemStack(existing), new ItemStack(after), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    public static void simpleInsertAfter(BuildCreativeModeTabContentsEvent event, ItemLike existing, ItemLike after) {
        event.insertAfter(new ItemStack(existing), new ItemStack(after), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
