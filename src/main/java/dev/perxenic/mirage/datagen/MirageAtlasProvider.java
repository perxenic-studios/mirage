package dev.perxenic.mirage.datagen;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.AtlasIds;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.perxenic.mirage.Mirage.mcLoc;
import static dev.perxenic.mirage.Mirage.mirageLoc;
import static dev.perxenic.mirage.content.MirageArmorTrimPatterns.VANILLA_PATTERNS;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MirageAtlasProvider implements DataProvider {
    private static final Identifier TRIM_PALETTE_KEY = mcLoc("trims/color_palettes/trim_palette");
    private static final Map<String, Identifier> TRIM_PALETTE_VALUES = extractAllMaterialAssets().collect(Collectors.toMap(MaterialAssetGroup.AssetInfo::suffix, (asset) -> Identifier.withDefaultNamespace("trims/color_palettes/" + asset.suffix())));
    private final PackOutput.PathProvider pathProvider;

    public MirageAtlasProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "atlases");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf(DataProvider.saveStable(
                cache,
                SpriteSources.FILE_CODEC,
                List.of(new PalettedPermutations(patternTextures(), TRIM_PALETTE_KEY, TRIM_PALETTE_VALUES)),
                this.pathProvider.json(AtlasIds.ARMOR_TRIMS)
        ));
    }

    private static Stream<MaterialAssetGroup.AssetInfo> extractAllMaterialAssets() {
        return ItemModelGenerators.TRIM_MATERIAL_MODELS.stream().map(ItemModelGenerators.TrimMaterialData::assets).flatMap((asset) -> Stream.concat(Stream.of(asset.base()), asset.overrides().values().stream())).sorted(Comparator.comparing(MaterialAssetGroup.AssetInfo::suffix));
    }

    private static List<Identifier> patternTextures() {
        List<Identifier> result = new ArrayList<>(VANILLA_PATTERNS.size());

        for(ResourceKey<TrimPattern> vanillaPattern : VANILLA_PATTERNS) {
            Identifier assetId = TrimPatterns.defaultAssetId(vanillaPattern);

            result.add(mirageLoc("trims/entity/humanoid/armadillo_" + assetId.getPath()));
        }

        return result;
    }

    public String getName() {
        return "Mirage Atlas Definitions";
    }
}
