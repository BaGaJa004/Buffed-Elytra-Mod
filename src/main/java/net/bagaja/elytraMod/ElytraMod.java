package net.bagaja.elytraMod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.TypedActionResult;

public class ElytraMod implements ModInitializer {
    public static boolean IS_ROCKET_DAMAGE = false;
    @Override
    public void onInitialize() {
        // Add event for rocket usage
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack heldItem = player.getStackInHand(hand);
            ItemStack elytra = player.getEquippedStack(EquipmentSlot.CHEST);

            // Check if player is using a firework rocket while wearing an elytra
            if (heldItem.getItem() == Items.FIREWORK_ROCKET &&
                    elytra.getItem() == Items.ELYTRA) {
                // Add 1 damage to the elytra
                elytra.damage(1, player, EquipmentSlot.CHEST);
            }

            // Return unmodified result to allow normal rocket usage
            return TypedActionResult.pass(heldItem);
        });
    }
}