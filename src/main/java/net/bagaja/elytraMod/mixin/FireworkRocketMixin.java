package net.bagaja.elytraMod.mixin;

import net.bagaja.elytraMod.ElytraMod;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ElytraItem;
import net.minecraft.entity.EquipmentSlot;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketMixin {
    @Inject(method = "use", at = @At("RETURN"))
    private void damageElytraOnRocket(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!world.isClient) {
            ItemStack elytraStack = user.getEquippedStack(EquipmentSlot.CHEST);
            if (user.isFallFlying() && elytraStack.getItem() instanceof ElytraItem) {
                ElytraMod.IS_ROCKET_DAMAGE = true;
                elytraStack.damage(1, user, EquipmentSlot.CHEST);
                ElytraMod.IS_ROCKET_DAMAGE = false;
            }
        }
    }
}