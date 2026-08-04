package dev.perxenic.mirage.mixin;

import dev.perxenic.mirage.registry.MirageDecoratedPotPatterns;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {
    @Inject(
            method = "getPatternFromItem(Lnet/minecraft/world/item/Item;)Lnet/minecraft/resources/ResourceKey;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir) {
        if (MirageDecoratedPotPatterns.EDITABLE_ITEM_TO_POT_TEXTURE.containsKey(BuiltInRegistries.ITEM.getKey(item)))
            cir.setReturnValue(MirageDecoratedPotPatterns.EDITABLE_ITEM_TO_POT_TEXTURE.get(BuiltInRegistries.ITEM.getKey(item)));
    }
}
