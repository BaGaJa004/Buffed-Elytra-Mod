package net.bagaja.elytraMod.mixin;

import net.bagaja.elytraMod.ElytraMod;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.ElytraItem;
import net.minecraft.entity.EquipmentSlot;

@Mixin(ItemStack.class)
public class ElytraMixin {
    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;)V", at = @At("HEAD"), cancellable = true)
    private void preventElytraDamage(int amount, LivingEntity entity, EquipmentSlot slot, CallbackInfo ci) {
        ItemStack self = (ItemStack)(Object)this;
        // Only cancel if it's regular flying damage, not our rocket damage
        if (self.getItem() instanceof ElytraItem &&
                entity.isFallFlying() &&
                !ElytraMod.IS_ROCKET_DAMAGE) {
            ci.cancel();
        }
    }
}