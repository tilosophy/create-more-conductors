package io.github.tilosophy.moreconductors.mixin;

import com.railwayteam.railways.content.conductor.ConductorCapItem;
import io.github.tilosophy.moreconductors.ConductorCreation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ConductorCapItem.class, remap = false)
public abstract class ConductorCapItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void moreconductors$useCasing(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        InteractionResult result = ConductorCreation.tryCreate(context);
        if (result != null) cir.setReturnValue(result);
    }
}
