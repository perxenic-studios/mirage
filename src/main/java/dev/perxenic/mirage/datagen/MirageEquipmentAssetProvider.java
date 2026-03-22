package dev.perxenic.mirage.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import dev.perxenic.mirage.registry.MirageArmorMaterials;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageEquipmentAssetProvider extends EquipmentAssetProvider {
    public MirageEquipmentAssetProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(
                MirageArmorMaterials.ARMADILLO_ASSET,
                EquipmentClientInfo.builder()
                        .addMainHumanoidLayer(mirageLoc("armadillo"), false)
                        .build()
        );
    }
}
