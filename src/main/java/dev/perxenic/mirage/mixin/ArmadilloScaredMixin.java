package dev.perxenic.mirage.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.perxenic.mirage.MirageConfig;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.world.entity.EquipmentSlot;
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
        if (MirageConfig.booleanDict.get("armadilloUnafraidArmor")) {
            if (entity.getItemBySlot(EquipmentSlot.CHEST).is(MirageItems.ARMADILLO_CHESTPLATE)) {
                cir.setReturnValue(false);
            }
        }
    }
}
