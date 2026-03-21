package dev.perxenic.mirage.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

public class MirageArmorMaterials {
    public static final ResourceKey<EquipmentAsset> ARMADILLO_ASSET = EquipmentAssets.createId("armadillo");

    public static final ArmorMaterial ARMADILLO = new ArmorMaterial(
            15,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 2);
                attribute.put(ArmorType.LEGGINGS, 5);
                attribute.put(ArmorType.CHESTPLATE, 6);
                attribute.put(ArmorType.HELMET, 2);
                attribute.put(ArmorType.BODY, 5);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_WOLF,
            0f,
            0f,
            ItemTags.REPAIRS_WOLF_ARMOR,
            ARMADILLO_ASSET
    );
}
