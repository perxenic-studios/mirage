package dev.perxenic.mirage.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.perxenic.mirage.Config;
import dev.perxenic.mirage.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Armadillo.class)
public class ArmadilloScaredMixin {
    @Inject(
            method = "isScaredBy(Lnet/minecraft/world/entity/LivingEntity;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noScareArmadilloArmor(CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) LivingEntity entity) {
        if (Config.armadilloUnafraidArmor) {
            entity.getArmorSlots().forEach(itemStack -> {
                if (itemStack.is(ModItems.ARMADILLO_CHESTPLATE)) {
                    cir.setReturnValue(false);
                }
            });
        }
    }
}
