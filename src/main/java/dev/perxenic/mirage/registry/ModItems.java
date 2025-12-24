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

    public static final DeferredItem<BlockItem> SUSPICIOUS_RED_SAND = ITEMS.registerSimpleBlockItem(ModBlocks.SUSPICIOUS_RED_SAND);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            simpleInsertAfter(event, Items.TURTLE_HELMET, ARMADILLO_CHESTPLATE);
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
