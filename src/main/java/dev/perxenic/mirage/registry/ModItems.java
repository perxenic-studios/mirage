package dev.perxenic.mirage.registry;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.perxenic.mirage.Mirage.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<BundleItem> ARMADILLO_BASKET = ITEMS.register("armadillo_basket", () -> new BundleItem(
            new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).requiredFeatures(FeatureFlags.BUNDLE)
    ));

    public static final DeferredItem<ArmorItem> ARMADILLO_CHESTPLATE = ITEMS.register("armadillo_chestplate", () -> new ArmorItem(
            ModArmorMaterials.ARMADILLO,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(5))
    ));

    public static final DeferredItem<BlockItem> WHITE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.WHITE_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIGHT_GRAY_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.LIGHT_GRAY_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> GRAY_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.GRAY_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> BLACK_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.BLACK_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> BROWN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.BROWN_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> RED_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.RED_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> ORANGE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.ORANGE_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> YELLOW_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.YELLOW_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIME_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.LIME_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> GREEN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.GREEN_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> CYAN_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.CYAN_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> LIGHT_BLUE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.LIGHT_BLUE_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> BLUE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.BLUE_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> PURPLE_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.PURPLE_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> MAGENTA_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.MAGENTA_FADED_TERRACOTTA);
    public static final DeferredItem<BlockItem> PINK_FADED_TERRACOTTA = ITEMS.registerSimpleBlockItem(ModBlocks.PINK_FADED_TERRACOTTA);

    public static final DeferredItem<BlockItem> SUSPICIOUS_RED_SAND = ITEMS.registerSimpleBlockItem(ModBlocks.SUSPICIOUS_RED_SAND);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            simpleInsertAfter(event, Items.TURTLE_HELMET, ARMADILLO_CHESTPLATE);
        }
        else if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            simpleInsertAfter(event, Items.TERRACOTTA, WHITE_FADED_TERRACOTTA);
            simpleInsertAfter(event, WHITE_FADED_TERRACOTTA, LIGHT_GRAY_FADED_TERRACOTTA);
            simpleInsertAfter(event, LIGHT_GRAY_FADED_TERRACOTTA, GRAY_FADED_TERRACOTTA);
            simpleInsertAfter(event, GRAY_FADED_TERRACOTTA, BLACK_FADED_TERRACOTTA);
            simpleInsertAfter(event, BLACK_FADED_TERRACOTTA, BROWN_FADED_TERRACOTTA);
            simpleInsertAfter(event, BROWN_FADED_TERRACOTTA, RED_FADED_TERRACOTTA);
            simpleInsertAfter(event, RED_FADED_TERRACOTTA, ORANGE_FADED_TERRACOTTA);
            simpleInsertAfter(event, ORANGE_FADED_TERRACOTTA, YELLOW_FADED_TERRACOTTA);
            simpleInsertAfter(event, YELLOW_FADED_TERRACOTTA, LIME_FADED_TERRACOTTA);
            simpleInsertAfter(event, LIME_FADED_TERRACOTTA, GREEN_FADED_TERRACOTTA);
            simpleInsertAfter(event, GREEN_FADED_TERRACOTTA, CYAN_FADED_TERRACOTTA);
            simpleInsertAfter(event, CYAN_FADED_TERRACOTTA, LIGHT_BLUE_FADED_TERRACOTTA);
            simpleInsertAfter(event, LIGHT_BLUE_FADED_TERRACOTTA, BLUE_FADED_TERRACOTTA);
            simpleInsertAfter(event, BLUE_FADED_TERRACOTTA, PURPLE_FADED_TERRACOTTA);
            simpleInsertAfter(event, PURPLE_FADED_TERRACOTTA, MAGENTA_FADED_TERRACOTTA);
            simpleInsertAfter(event, MAGENTA_FADED_TERRACOTTA, PINK_FADED_TERRACOTTA);
        }
        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES && event.getFlags().contains(FeatureFlags.BUNDLE)) {
            simpleInsertAfter(event, Items.BUNDLE, ARMADILLO_BASKET);
        }
        else if (event.getTabKey() == CreativeModeTabs.SEARCH) {
            event.accept(SUSPICIOUS_RED_SAND);
        }
    }

    public static void simpleInsertAfter(BuildCreativeModeTabContentsEvent event, ItemLike existing, ItemLike after) {
        event.insertAfter(new ItemStack(existing), new ItemStack(after), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
