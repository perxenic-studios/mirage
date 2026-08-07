package dev.perxenic.mirage.datagen;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import dev.perxenic.mirage.registry.MirageArmorMaterials;
import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
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
        itemModels.generateFlatItem(MirageItems.SANCTUARY_POTTERY_SHERD.get(), ModelTemplates.FLAT_ITEM);

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

        blockModels.createTrivialCube(MirageBlocks.SANDY_STONE.get());
        blockModels.createTrivialCube(MirageBlocks.GILDED_CALCITE.get());

        blockModels.createCrossBlockWithDefaultItem(
                MirageBlocks.SHORT_SCORCHED_GRASS.get(),
                BlockModelGenerators.PlantType.NOT_TINTED
        );
        blockModels.createCrossBlockWithDefaultItem(
                MirageBlocks.TALL_SCORCHED_GRASS.get(),
                BlockModelGenerators.PlantType.NOT_TINTED
        );

        createPointedStone(blockModels, MirageBlocks.POINTED_SANDSTONE.get());
    }

    private void createPointedStone(BlockModelGenerators blockModels, Block block) {
        PropertyDispatch.C2<MultiVariant, Direction, DripstoneThickness> generator = PropertyDispatch.initial(
                BlockStateProperties.VERTICAL_DIRECTION,
                BlockStateProperties.DRIPSTONE_THICKNESS
        );

        for (DripstoneThickness dripstoneThickness : DripstoneThickness.values()) {
            generator.select(
                    Direction.UP,
                    dripstoneThickness,
                    createPointedStoneVariant(blockModels, block, Direction.UP, dripstoneThickness)
            );
            generator.select(
                    Direction.DOWN,
                    dripstoneThickness,
                    createPointedStoneVariant(blockModels, block, Direction.DOWN, dripstoneThickness)
            );
        }

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(generator));
        blockModels.registerSimpleFlatItemModel(block, "_down_tip");
    }

    private MultiVariant createPointedStoneVariant(
            BlockModelGenerators blockModels,
            Block block,
            Direction direction,
            DripstoneThickness dripstoneThickness
    ) {
        String var10000 = direction.getSerializedName();
        String suffix = "_" + var10000 + "_" + dripstoneThickness.getSerializedName();
        TextureMapping texture = TextureMapping.cross(TextureMapping.getBlockTexture(block, suffix));
        return BlockModelGenerators.plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(
                block,
                suffix,
                texture,
                blockModels.modelOutput
        ));
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
