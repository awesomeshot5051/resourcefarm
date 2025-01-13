package com.awesomeshot5051.resourceFarm.entity;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.gossip.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.trading.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

public class EasyVillagerEntity extends Villager {

    public long lastRestockGameTime;
    public boolean increaseProfessionLevelOnUpdate;

    public EasyVillagerEntity(EntityType<? extends Villager> type, Level worldIn) {
        super(type, worldIn);
    }

    public EasyVillagerEntity(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType) {
        super(type, worldIn, villagerType);
    }

    public static int getReputation(Villager villager) {
        if (Main.SERVER_CONFIG.universalReputation.get()) {
            return getUniversalReputation(villager);
        } else {
            return 0;
        }
    }

    public static int getUniversalReputation(Villager villager) {
        return villager.getGossips().getGossipEntries().keySet().stream().map(uuid -> villager.getGossips().getReputation(uuid, EasyVillagerEntity::isPositive)).reduce(0, Integer::sum);
    }

    public static boolean isPositive(GossipType gossipType) {
        return switch (gossipType) {
            case MAJOR_NEGATIVE, MINOR_NEGATIVE -> false;
            default -> true;
        };
    }

    public static void recalculateOffers(Villager villager) {
        resetOffers(villager);
        calculateOffers(villager);
    }

    private static void resetOffers(Villager villager) {
        for (MerchantOffer merchantoffer : villager.getOffers()) {
            merchantoffer.resetSpecialPriceDiff();
        }
    }

    private static void calculateOffers(Villager villager) {
        int i = getReputation(villager);
        if (i != 0) {
            for (MerchantOffer merchantoffer : villager.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-Mth.floor((float) i * merchantoffer.getPriceMultiplier()));
            }
        }
    }

    @Override
    public int getPlayerReputation(Player player) {
        if (Main.SERVER_CONFIG.universalReputation.get()) {
            return getUniversalReputation(this);
        } else {
            return super.getPlayerReputation(player);
        }
    }

    public void recalculateOffers() {
        resetOffers(this);
        calculateOffers(this);
    }

    @Override
    public int getAge() {
        if (level().isClientSide) {
            return super.getAge() < 0 ? -24000 : 1;
        } else {
            return age;
        }
    }

    @Override
    public @NotNull Component getName() {
        if (hasCustomName()) {
            return super.getName();
        }
        VillagerData villagerData = getVillagerData();
        VillagerProfession profession = villagerData.getProfession();
        if (profession.equals(VillagerProfession.NONE)) {
            return EntityType.VILLAGER.getDescription().copy();
        } else {
            return getTypeName();
        }
    }

    public Component getAdvancedName() {
        return Component.translatable("tooltip.mob_farms.villager_profession", getName().copy(), Component.translatable("merchant.level." + getVillagerData().getLevel())).withStyle(ChatFormatting.GRAY);
    }

    public void increaseMerchantCareer() {
        this.setVillagerData(this.getVillagerData().setLevel(this.getVillagerData().getLevel() + 1));
        this.updateTrades();
    }

    public boolean shouldIncreaseLevel() {
        int i = this.getVillagerData().getLevel();
        return VillagerData.canLevelUp(i) && this.getVillagerXp() >= VillagerData.getMaxXpPerLevel(i);
    }

}
