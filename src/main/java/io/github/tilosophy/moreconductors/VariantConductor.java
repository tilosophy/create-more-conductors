package io.github.tilosophy.moreconductors;

/** Implemented on the upstream conductor through a mixin. */
public interface VariantConductor {
    ConductorVariant moreconductors$getVariant();
    /** Set before adding the entity to its level. Variants do not change during normal play. */
    void moreconductors$setVariant(ConductorVariant variant);
}
