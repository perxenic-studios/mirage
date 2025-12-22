package dev.perxenic.mirage.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

import static dev.perxenic.mirage.Mirage.MODID;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ARMADILLO = ARMOR_MATERIALS.register("armadillo", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 2);
                attribute.put(ArmorItem.Type.LEGGINGS, 5);
                attribute.put(ArmorItem.Type.CHESTPLATE, 6);
                attribute.put(ArmorItem.Type.HELMET, 2);
                attribute.put(ArmorItem.Type.BODY, 5);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_WOLF,
            () -> Ingredient.of(Items.ARMADILLO_SCUTE),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MODID, "armadillo"))),
            0f,
            0.0f
    ));

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
}
