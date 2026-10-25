package io.github.kaosreven.aether.achievement;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.item.AetherItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.achievement.AchievementTemplate;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.SideUtil;

import java.util.ArrayList;

public class AetherAchievements {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static int acOff = 800;

    // Achievement Page
    public static AetherAchievementPage achievementPage;
    public static final ArrayList<Achievement> ACHIEVEMENTS = new ArrayList<>();

    // Achievements
    public static Achievement enterAether;
    public static Achievement defeatBronze;
    public static Achievement defeatSilver;
    public static Achievement defeatGold;
    public static Achievement enchanter;
    public static Achievement incubator;
    public static Achievement gravTools;
    public static Achievement blueCloud;
    public static Achievement flyingPig;
    public static Achievement lore;
    public static Achievement loreception;

    public static void giveAchievement(Achievement achievement, PlayerEntity player) {
        SideUtil.run(() -> giveAchievementClient(achievement, player), () -> giveAchievementServer());
    }

    @Environment(EnvType.CLIENT)
    public static void giveAchievementClient(Achievement achievement, PlayerEntity player) {
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft mc) {
            if (!mc.stats.hasAchievement(achievement)) {
                if (player == null) player = mc.player;
                player.incrementStat(achievement);
                if (mc.stats.hasAchievement(enterAether)) {
                    if (achievement == defeatBronze) {
                        mc.soundManager.playSound("aether:other.achievement.achievementbronze", 1.0F, 1.0F);
                    } else if (achievement == defeatSilver) {
                        mc.soundManager.playSound("aether:other.achievement.achievementsilver", 1.0F, 1.0F);
                    } else {
                        mc.soundManager.playSound("aether:other.achievement.achievementgen", 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    @Environment(EnvType.SERVER)
    public static void giveAchievementServer() {
    }

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        enterAether = create("enterAether", new ItemStack(Block.GLOWSTONE),0,0, null);
        defeatBronze = create("defeatBronze", new ItemStack(AetherItems.Key, 1, 0),-2,3, enterAether);
        defeatSilver = create("defeatSilver", new ItemStack(AetherItems.Key, 1, 1),0,4, enterAether);
        defeatGold = create("defeatGold", new ItemStack(AetherItems.Key, 1, 2),2,3, enterAether);
        enchanter = create("enchanter", new ItemStack(AetherBlocks.Enchanter),2, 1, enterAether);
        blueCloud = create("blueCloud", new ItemStack(AetherBlocks.Aercloud, 1, 1),-2,-1, enterAether);
        gravTools = create("gravTools", new ItemStack(AetherItems.PickGravitite),-1, -3, enterAether);
        flyingPig = create("flyingPig", new ItemStack(Item.SADDLE),-2,1, enterAether);
        lore = create("lore", new ItemStack(Item.BOOK),1,-3, enterAether);
        loreception = create("loreception", new ItemStack(Item.BOOK),1,-5, lore);

        event.achievements.addAll(AetherAchievements.ACHIEVEMENTS);
        achievementPage = new AetherAchievementPage(NAMESPACE.id("aether"));
        achievementPage.addAchievements(AetherAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
    }

    @EventListener
    public static void itemCrafted(ItemUsedInCraftingEvent event) {
        Item crafted = event.itemCrafted.getItem();
        if (crafted == AetherBlocks.Enchanter.asItem())
            giveAchievement(enchanter, event.player);
        else if (crafted == AetherItems.SwordGravitite ||
                crafted == AetherItems.PickGravitite ||
                crafted == AetherItems.AxeGravitite ||
                crafted == AetherItems.ShovelGravitite)
            giveAchievement(gravTools, event.player);
    }

    public Achievement create(String name, ItemStack icon, int x, int y, Achievement parent) {
        Achievement achievement = new Achievement(acOff++, "aether." + name, x, y, icon, parent);
        AchievementTemplate.onConstructor(achievement, NAMESPACE.id(name));
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }
}
