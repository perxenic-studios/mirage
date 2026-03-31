package dev.perxenic.mirage.content.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.perxenic.mirage.MirageConfig;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jetbrains.annotations.NotNull;

public record MirageConfigCondition(String configKey) implements ICondition {
    public static final MapCodec<MirageConfigCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.STRING.fieldOf("config_key").forGetter(MirageConfigCondition::configKey)
    ).apply(inst, MirageConfigCondition::new));

    @Override
    public boolean test(@NotNull IContext iContext) {
        if (MirageConfig.serverBoolDict.containsKey(configKey))
            return MirageConfig.serverBoolDict.get(configKey);

        // It is not recommended to check for common configs as this may cause desync
        if (!MirageConfig.commonBoolDict.containsKey(configKey)) return false;
        return MirageConfig.commonBoolDict.get(configKey);
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
