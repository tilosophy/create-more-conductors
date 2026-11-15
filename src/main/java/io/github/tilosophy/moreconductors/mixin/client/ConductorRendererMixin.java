package io.github.tilosophy.moreconductors.mixin.client;

import com.railwayteam.railways.content.conductor.ConductorEntity;
import com.railwayteam.railways.content.conductor.ConductorRenderer;
import io.github.tilosophy.moreconductors.VariantConductor;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ConductorRenderer.class, remap = false)
public abstract class ConductorRendererMixin {
    @Inject(method = "getTextureLocation(Lcom/railwayteam/railways/content/conductor/ConductorEntity;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("RETURN"), cancellable = true)
    private void moreconductors$bodyTexture(ConductorEntity conductor, CallbackInfoReturnable<ResourceLocation> cir) {
        // Named caps and named-conductor easter-egg skins retain upstream priority.
        if (ConductorRenderer.TEXTURE.equals(cir.getReturnValue())) {
            cir.setReturnValue(((VariantConductor) conductor).moreconductors$getVariant().texture());
        }
    }
}
