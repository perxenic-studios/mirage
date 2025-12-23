package dev.perxenic.mirage.content;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.perxenic.mirage.registry.ModStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

public class RandomLandStructure extends Structure {

    public static final MapCodec<RandomLandStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    StructureSettings.CODEC.forGetter(s -> s.modifiableStructureInfo().getOriginalStructureInfo().structureSettings()),
                    StructureTemplatePool.CODEC.fieldOf("template_pool").forGetter(s -> s.templatePool)
            ).apply(instance, RandomLandStructure::new)
    );

    private final Holder<StructureTemplatePool> templatePool;

    public RandomLandStructure(StructureSettings settings, Holder<StructureTemplatePool> templatePool){
        super(settings);
        this.templatePool = templatePool;
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context){
        StructurePoolElement poolElement = templatePool.value().getRandomTemplate(context.random());
        if(poolElement == EmptyPoolElement.INSTANCE) return Optional.empty();

        int x = context.chunkPos().getBlockX(context.random().nextInt(16));
        int z = context.chunkPos().getBlockZ(context.random().nextInt(16));

        int y = context.chunkGenerator().getFirstFreeHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - poolElement.getGroundLevelDelta() + 1;

        // Don't generate if at minimum build height in case of floating islands
        if (y <= context.heightAccessor().getMinBuildHeight()) return Optional.empty();

        // If height is above ocean floor, must be in water so do not generate
        if (y > context.chunkGenerator().getFirstFreeHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()) - poolElement.getGroundLevelDelta() + 1) return Optional.empty();

        BlockPos spawnPos = new BlockPos(x, y, z);

        Rotation rotation = Rotation.getRandom(context.random());

        return Optional.of(new GenerationStub(spawnPos, piecesBuilder ->
                piecesBuilder.addPiece(new PoolElementStructurePiece(
                        context.structureTemplateManager(),
                        poolElement,
                        spawnPos,
                        poolElement.getGroundLevelDelta(),
                        rotation,
                        poolElement.getBoundingBox(context.structureTemplateManager(), spawnPos, rotation),
                        LiquidSettings.IGNORE_WATERLOGGING
                ))
        ));
    }

    @Override
    public @NotNull StructureType<?> type(){
        return ModStructureTypes.RANDOM_LAND_STRUCTURE.get();
    }
}