package io.github.tilosophy.moreconductors.mixin;

import com.railwayteam.railways.content.conductor.ConductorEntity;
import io.github.tilosophy.moreconductors.ConductorVariant;
import io.github.tilosophy.moreconductors.VariantConductor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ConductorEntity.class, remap = false)
public abstract class ConductorEntityMixin implements VariantConductor, IEntityWithComplexSpawn {
    @Unique private ConductorVariant moreconductors$variant = ConductorVariant.ANDESITE;
    @Unique private static final String MORECONDUCTORS_VARIANT_KEY = "moreconductors:variant";

    @Override public ConductorVariant moreconductors$getVariant() { return moreconductors$variant; }
    @Override public void moreconductors$setVariant(ConductorVariant variant) {
        moreconductors$variant = variant == null ? ConductorVariant.ANDESITE : variant;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void moreconductors$save(CompoundTag tag, CallbackInfo ci) {
        tag.putString(MORECONDUCTORS_VARIANT_KEY, moreconductors$variant.id());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void moreconductors$load(CompoundTag tag, CallbackInfo ci) {
        moreconductors$variant = ConductorVariant.fromId(tag.getString(MORECONDUCTORS_VARIANT_KEY));
    }

    // NeoForge bundles this with entity pairing, including when a player re-enters tracking range.
    // Do not allocate SynchedEntityData IDs on a third-party entity.
    @Override public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        buffer.writeUtf(moreconductors$variant.id(), 32);
    }

    @Override public void readSpawnData(RegistryFriendlyByteBuf buffer) {
        moreconductors$variant = ConductorVariant.fromId(buffer.readUtf(32));
    }
}
