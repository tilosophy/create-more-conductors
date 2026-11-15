package io.github.tilosophy.moreconductors;

import com.railwayteam.railways.content.conductor.ConductorCapItem;
import com.railwayteam.railways.content.conductor.ConductorEntity;
import com.railwayteam.railways.registry.CREntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public final class ConductorCreation {
    private ConductorCreation() {}

    /** Null means delegate the interaction completely to Steam 'n' Rails. */
    public static InteractionResult tryCreate(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ConductorVariant variant = ConductorVariant.forBlock(level.getBlockState(pos));
        if (variant == null || variant == ConductorVariant.ANDESITE) return null;
        ItemStack held = context.getItemInHand();
        if (!(held.getItem() instanceof ConductorCapItem cap) || held.isEmpty()) return null;
        // Do not consume containers if another mod replaces a supported casing with a block entity.
        if (level.getBlockEntity(pos) != null) return InteractionResult.FAIL;
        if (context.getPlayer() != null && !context.getPlayer().mayUseItemAt(pos, context.getClickedFace(), held)) {
            return InteractionResult.FAIL;
        }
        if (level.isClientSide) return InteractionResult.SUCCESS;

        ConductorEntity conductor = new ConductorEntity(CREntities.CONDUCTOR.get(), level);
        conductor.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        conductor.setColor(cap.color);
        conductor.equipItemIfPossible(held.copyWithCount(1));
        ((VariantConductor) conductor).moreconductors$setVariant(variant);
        // EntityJoinLevelEvent may reject the spawn. Leave both ingredients intact on failure.
        if (!level.addFreshEntity(conductor)) return InteractionResult.FAIL;
        if (!level.removeBlock(pos, false)) {
            conductor.discard();
            return InteractionResult.FAIL;
        }
        if (context.getPlayer() != null && !context.getPlayer().isCreative()) held.shrink(1);
        return InteractionResult.CONSUME;
    }
}
