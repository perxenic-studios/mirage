package dev.perxenic.mirage.datagen;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import dev.perxenic.mirage.registry.MirageArmorMaterials;
import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.stream.Stream;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MirageModelProvider extends ModelProvider {
    public MirageModelProvider(PackOutput output) {
        super(output, MIRAGE_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateTrimmableItem(
                MirageItems.ARMADILLO_CHESTPLATE.get(),
                MirageArmorMaterials.ARMADILLO_ASSET,
                mirageLoc("trims/items/armadillo_trim"),
                false
        );

        itemModels.generateFlatItem(MirageItems.ARMADILLO_BASKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MirageItems.CRACKED_POTTERY_SHERD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MirageItems.BLANK_POTTERY_SHERD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MirageItems.HIDE_POTTERY_SHERD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MirageItems.BARREN_POTTERY_SHERD.get(), ModelTemplates.FLAT_ITEM);

        for (DeferredBlock<GlazedTerracottaBlock> block : MirageBlocks.FADED_TERRACOTTA) {
            blockModels.blockStateOutput.accept(
                    MultiVariantGenerator.dispatch(
                            block.get(),
                            BlockModelGenerators.plainVariant(
                                    TexturedModel.GLAZED_TERRACOTTA.create(block.get(), blockModels.modelOutput)
                            )
                    )
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT)
            );
        }
        blockModels.createBrushableBlock(MirageBlocks.SUSPICIOUS_RED_SAND.get());
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return MirageBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return MirageItems.ITEMS.getEntries().stream();
    }
}
