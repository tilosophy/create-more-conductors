package io.github.tilosophy.moreconductors.test;

import io.github.tilosophy.moreconductors.ConductorVariant;
import io.github.tilosophy.moreconductors.VariantConductor;

import com.railwayteam.railways.content.conductor.ConductorCapItem;
import com.railwayteam.railways.content.conductor.ConductorEntity;
import com.railwayteam.railways.registry.CREntities;
import com.railwayteam.railways.registry.CRItems;
import io.netty.buffer.Unpooled;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@Mod("moreconductors_tests")
@GameTestHolder("moreconductors")
@PrefixGameTestTemplate(false)
public class ConductorGameTests {
    private static final BlockPos SPOT = new BlockPos(3, 1, 3);
    private static final String KEY = "moreconductors:variant";

    private static ConductorEntity conductor(GameTestHelper h) {
        return new ConductorEntity(CREntities.CONDUCTOR.get(), h.getLevel());
    }
    private static VariantConductor variant(ConductorEntity c) { return (VariantConductor)c; }
    private static Player player(GameTestHelper h, boolean creative, DyeColor color) {
        Player p = h.makeMockPlayer(creative ? GameType.CREATIVE : GameType.SURVIVAL);
        p.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(CRItems.ITEM_CONDUCTOR_CAP.get(color).get()));
        return p;
    }
    private static InteractionResult use(GameTestHelper h, Player p) {
        BlockPos pos = h.absolutePos(SPOT);
        UseOnContext ctx = new UseOnContext(p, InteractionHand.MAIN_HAND,
                new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false));
        return ((ConductorCapItem)p.getMainHandItem().getItem()).useOn(ctx);
    }
    private static ConductorEntity created(GameTestHelper h) {
        var list = h.getLevel().getEntitiesOfClass(ConductorEntity.class, new AABB(h.absolutePos(SPOT)).inflate(.2));
        h.assertTrue(list.size() == 1, "Expected exactly one conductor, got " + list.size());
        return list.getFirst();
    }

    @GameTest(template = "empty")
    public static void installedCasingsCreateCorrectConductors(GameTestHelper h) {
        int checked = 0;
        for (ConductorVariant v : ConductorVariant.values()) {
            if (!BuiltInRegistries.BLOCK.containsKey(v.casing())) continue;
            h.setBlock(SPOT, BuiltInRegistries.BLOCK.get(v.casing()));
            Player p = player(h, false, DyeColor.RED);
            h.assertTrue(use(h,p).consumesAction(), v.id()+": interaction failed");
            ConductorEntity c = created(h);
            h.assertTrue(variant(c).moreconductors$getVariant() == v, v.id()+": wrong variant");
            h.assertTrue(c.getColor() == DyeColor.RED, "Cap colour lost");
            h.assertTrue(c.getItemBySlot(EquipmentSlot.HEAD).getCount() == 1, "Cap not equipped once");
            h.assertTrue(p.getMainHandItem().isEmpty(), "Survival cap not consumed");
            h.assertTrue(h.getBlockState(SPOT).isAir(), "Casing not consumed");
            c.discard();
            checked++;
        }
        h.assertTrue(checked >= 6, "Core Create casings missing");
        System.out.println("[More Conductors] Verified casing creation for " + checked + " installed variants.");
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void creativeAndEveryCapColour(GameTestHelper h) {
        for (DyeColor color : DyeColor.values()) {
            h.setBlock(SPOT, BuiltInRegistries.BLOCK.get(ConductorVariant.BRASS.casing()));
            Player p = player(h, true, color);
            use(h,p);
            ConductorEntity c = created(h);
            h.assertTrue(c.getColor() == color, "Wrong cap colour: "+color);
            h.assertTrue(p.getMainHandItem().getCount() == 1, "Creative consumed cap");
            h.assertTrue(variant(c).moreconductors$getVariant() == ConductorVariant.BRASS, "Cap changes body");
            c.discard();
        }
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void everyVariantSurvivesSaveLoad(GameTestHelper h) {
        for (ConductorVariant v : ConductorVariant.values()) {
            ConductorEntity original = conductor(h);
            variant(original).moreconductors$setVariant(v);
            original.setColor(DyeColor.PURPLE);
            CompoundTag saved = original.saveWithoutId(new CompoundTag());
            ConductorEntity restored = conductor(h);
            restored.load(saved);
            h.assertTrue(variant(restored).moreconductors$getVariant() == v, "Save/load lost "+v.id());
            h.assertTrue(restored.getColor() == DyeColor.PURPLE, "Save/load changed colour");
        }
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void everyVariantSurvivesSpawnPayload(GameTestHelper h) {
        for (ConductorVariant v : ConductorVariant.values()) {
            ConductorEntity original = conductor(h), received = conductor(h);
            variant(original).moreconductors$setVariant(v);
            RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(Unpooled.buffer(), h.getLevel().registryAccess());
            try {
                ((IEntityWithComplexSpawn)original).writeSpawnData(buffer);
                ((IEntityWithComplexSpawn)received).readSpawnData(buffer);
                h.assertTrue(variant(received).moreconductors$getVariant() == v, "Spawn payload lost "+v.id());
                h.assertTrue(buffer.readableBytes() == 0, "Unread payload bytes");
            } finally { buffer.release(); }
        }
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void oldAndUnknownDataFallsBackToAndesite(GameTestHelper h) {
        for (String id : new String[]{"", "unknown_future_variant"}) {
            ConductorEntity c = conductor(h);
            variant(c).moreconductors$setVariant(ConductorVariant.SHADOW);
            CompoundTag tag = conductor(h).saveWithoutId(new CompoundTag());
            if (id.isEmpty()) tag.remove(KEY); else tag.putString(KEY,id);
            c.load(tag);
            h.assertTrue(variant(c).moreconductors$getVariant() == ConductorVariant.ANDESITE, "Unsafe fallback");
        }
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void unsupportedBlocksStayUntouched(GameTestHelper h) {
        for (var block : new net.minecraft.world.level.block.Block[]{Blocks.STONE,Blocks.AIR,Blocks.CHEST}) {
            h.setBlock(SPOT,block);
            Player p = player(h,false,DyeColor.BLUE);
            use(h,p);
            h.assertTrue(h.getBlockState(SPOT).is(block), "Unsupported block changed");
            h.assertTrue(p.getMainHandItem().getCount() == 1, "Unsupported block consumed cap");
        }
        h.assertEntityNotPresent(CREntities.CONDUCTOR.get());
        h.succeed();
    }

    @GameTest(template = "empty")
    public static void cancelledSpawnPreservesMaterials(GameTestHelper h) {
        h.setBlock(SPOT,BuiltInRegistries.BLOCK.get(ConductorVariant.RADIANT.casing()));
        Player p = player(h,false,DyeColor.YELLOW);
        Consumer<EntityJoinLevelEvent> cancel = event -> {
            if (event.getEntity() instanceof ConductorEntity && event.getEntity().blockPosition().equals(h.absolutePos(SPOT)))
                event.setCanceled(true);
        };
        NeoForge.EVENT_BUS.addListener(cancel);
        try {
            h.assertTrue(use(h,p) == InteractionResult.FAIL, "Cancelled spawn reported success");
            h.assertTrue(h.getBlockState(SPOT).is(BuiltInRegistries.BLOCK.get(ConductorVariant.RADIANT.casing())), "Lost casing");
            h.assertTrue(p.getMainHandItem().getCount() == 1, "Lost cap");
            h.assertEntityNotPresent(CREntities.CONDUCTOR.get());
        } finally { NeoForge.EVENT_BUS.unregister(cancel); }
        h.succeed();
    }
}
