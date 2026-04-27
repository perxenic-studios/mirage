package dev.perxenic.mirage.datagen;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Set;

@MethodsReturnNonnullByDefault
public class MirageBlockLootSubProvider extends BlockLootSubProvider {
    public MirageBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MirageBlocks.BLOCKS.getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    @Override
    protected void generate() {
        for (DeferredBlock<GlazedTerracottaBlock> terracottaBlock : MirageBlocks.FADED_TERRACOTTA) {
            dropSelf(terracottaBlock.get());
        }

        add(MirageBlocks.SUSPICIOUS_RED_SAND.get(), new LootTable.Builder());
        add(MirageBlocks.SANDY_STONE.get(),
                createSingleItemTableWithSilkTouch(MirageBlocks.SANDY_STONE.get(), Items.COBBLESTONE));

        add(
                MirageBlocks.GILDED_CALCITE.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
                        LootItem.lootTableItem(MirageBlocks.GILDED_CALCITE.get()).when(hasSilkTouch())
                                .otherwise(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                                registries.holderOrThrow(Enchantments.SILK_TOUCH),
                                                0.1f, 0.14285715f, 0.25f, 1.0f))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f)))
                                        .otherwise(LootItem.lootTableItem(MirageBlocks.GILDED_CALCITE.get()))
                                        .when(ExplosionCondition.survivesExplosion())
                                )
                ))
        );

        add(MirageBlocks.SHORT_SCORCHED_GRASS.get(), createShearsOnlyDrop(MirageBlocks.SHORT_SCORCHED_GRASS));
        add(MirageBlocks.TALL_SCORCHED_GRASS.get(), createShearsOnlyDrop(MirageBlocks.TALL_SCORCHED_GRASS));
    }
}
