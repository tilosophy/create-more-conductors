package io.github.tilosophy.moreconductors;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

/** Explicit v1 support: no scanning tags or guessing casing names. */
public enum ConductorVariant {
    ANDESITE("andesite", "create:andesite_casing"),
    BRASS("brass", "create:brass_casing"),
    COPPER("copper", "create:copper_casing"),
    TRAIN("train", "create:railway_casing"),
    SHADOW("shadow", "create:shadow_steel_casing"),
    RADIANT("radiant", "create:refined_radiance_casing"),
    ZINC("zinc", "createcasing:zinc_casing"),
    BLAZE("blaze", "blazinghot:blaze_casing"),
    STURDY("sturdy", "blazinghot:sturdy_casing"),
    INDUSTRIAL("industrial", "dndesires:industrial_casing"),
    OVERBURDEN("overburden", "dndesires:overburden_casing"),
    BLACKSTONE("blackstone", "createthrusters:blackstone_casing"),
    PLATINUM("platinum", "createpropulsion:platinum_casing");

    private static final Map<String, ConductorVariant> BY_ID = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(ConductorVariant::id, Function.identity()));
    private static final Map<ResourceLocation, ConductorVariant> BY_CASING = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(ConductorVariant::casing, Function.identity()));
    private final String id;
    private final ResourceLocation casing;
    private final ResourceLocation texture;

    ConductorVariant(String id, String casing) {
        this.id = id;
        this.casing = ResourceLocation.parse(casing);
        this.texture = id.equals("andesite")
                ? ResourceLocation.fromNamespaceAndPath("railways", "textures/entity/conductor.png")
                : ResourceLocation.fromNamespaceAndPath(MoreConductors.MOD_ID, "textures/entity/conductor/" + id + ".png");
    }

    public String id() { return id; }
    public ResourceLocation casing() { return casing; }
    public ResourceLocation texture() { return texture; }

    /** Missing/unknown data from an older or newer save must remain a usable conductor. */
    public static ConductorVariant fromId(String id) { return BY_ID.getOrDefault(id, ANDESITE); }

    /** An absent optional mod cannot match a real block. Never resolve missing IDs to air. */
    public static ConductorVariant forBlock(BlockState state) {
        return BY_CASING.get(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
    }
}
