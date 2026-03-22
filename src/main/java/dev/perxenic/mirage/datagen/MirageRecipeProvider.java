package dev.perxenic.mirage.datagen;

import com.google.common.collect.ImmutableMap;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import dev.perxenic.mirage.content.MirageArmorTrimPatterns;
import dev.perxenic.mirage.content.conditions.MirageConfigCondition;
import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.ItemLike;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static dev.perxenic.mirage.Mirage.mirageLoc;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MirageRecipeProvider extends RecipeProvider {

    public static final MirageConfigCondition fadedTerracottaSmeltingCondition =
            new MirageConfigCondition("fadedTerracottaSmelting");

    public static final MirageConfigCondition sherdCraftingCondition =
            new MirageConfigCondition("sherdCrafting");

    public static final MirageConfigCondition sherdConstructingCondition =
            new MirageConfigCondition("sherdConstructing");

    public static final Map<ItemLike, ResourceKey<TrimPattern>> VANILLA_TRIM_MAP =
            new ImmutableMap.Builder<ItemLike, ResourceKey<TrimPattern>>()
                    .put(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.SENTRY)
                    .put(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.VEX)
                    .put(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.WILD)
                    .put(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.DUNE)
                    .put(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.WAYFINDER)
                    .put(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.RAISER)
                    .put(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.SHAPER)
                    .put(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.HOST)
                    .put(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.WARD)
                    .put(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.SILENCE)
                    .put(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.TIDE)
                    .put(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.SNOUT)
                    .put(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.RIB)
                    .put(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.EYE)
                    .put(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.SPIRE)
                    .put(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.FLOW)
                    .put(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, TrimPatterns.BOLT)
                    .build();

    public MirageRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.TOOLS, MirageItems.ARMADILLO_BASKET)
                .pattern(" S ")
                .pattern("A A")
                .pattern("AAA")
                .define('S', Items.STRING)
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(output, ResourceKey.create(Registries.RECIPE, mirageLoc("armadillo_basket")));

        shaped(RecipeCategory.COMBAT, MirageItems.ARMADILLO_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(output, ResourceKey.create(Registries.RECIPE, mirageLoc("armadillo_chestplate")));

        fadedTerracottaSmelting(
                Items.WHITE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_SUN_TERRACOTTA,
                "faded_sun_terracotta"
        );
        fadedTerracottaSmelting(
                Items.LIGHT_GRAY_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_MODERN_TERRACOTTA,
                "faded_modern_terracotta"
        );
        fadedTerracottaSmelting(
                Items.GRAY_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CROSS_TERRACOTTA,
                "faded_cross_terracotta"
        );
        fadedTerracottaSmelting(
                Items.BLACK_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CRAWL_TERRACOTTA,
                "faded_crawl_terracotta"
        );
        fadedTerracottaSmelting(
                Items.BROWN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_TARGET_TERRACOTTA,
                "faded_target_terracotta"
        );
        fadedTerracottaSmelting(
                Items.RED_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_POTION_TERRACOTTA,
                "faded_potion_terracotta"
        );
        fadedTerracottaSmelting(
                Items.ORANGE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FISH_TERRACOTTA,
                "faded_fish_terracotta"
        );
        fadedTerracottaSmelting(
                Items.YELLOW_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FLOWER_TERRACOTTA,
                "faded_flower_terracotta"
        );
        fadedTerracottaSmelting(
                Items.LIME_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_SPOKE_TERRACOTTA,
                "faded_spoke_terracotta"
        );
        fadedTerracottaSmelting(
                Items.GREEN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_PLANT_TERRACOTTA,
                "faded_plant_terracotta"
        );
        fadedTerracottaSmelting(
                Items.CYAN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CREEP_TERRACOTTA,
                "faded_creep_terracotta"
        );
        fadedTerracottaSmelting(
                Items.LIGHT_BLUE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_GEO_TERRACOTTA,
                "faded_geo_terracotta"
        );
        fadedTerracottaSmelting(
                Items.BLUE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FAN_TERRACOTTA,
                "faded_fan_terracotta"
        );
        fadedTerracottaSmelting(
                Items.PURPLE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_BLADE_TERRACOTTA,
                "faded_blade_terracotta"
        );
        fadedTerracottaSmelting(
                Items.MAGENTA_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_ARROW_TERRACOTTA,
                "faded_arrow_terracotta"
        );
        fadedTerracottaSmelting(
                Items.PINK_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_LEAF_TERRACOTTA,
                "faded_leaf_terracotta"
        );

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(registries.getOrThrow(ItemTags.DECORATED_POT_SHERDS)),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        MirageItems.CRACKED_POTTERY_SHERD,
                        0f,
                        200)
                .unlockedBy("has_sherd", has(ItemTags.DECORATED_POT_SHERDS))
                .save(
                        output.withConditions(new MirageConfigCondition("sherdCracking")),
                        ResourceKey.create(Registries.RECIPE, mirageLoc("sherd_cracking"))
                );

        shapeless(RecipeCategory.MISC, MirageItems.BLANK_POTTERY_SHERD)
                .requires(MirageItems.CRACKED_POTTERY_SHERD)
                .requires(Items.BRICK)
                .group("pottery_sherd")
                .unlockedBy("has_cracked_sherd", has(MirageItems.CRACKED_POTTERY_SHERD))
                .save(
                        output.withConditions(new MirageConfigCondition("sherdRepairing")),
                        ResourceKey.create(Registries.RECIPE, mirageLoc("sherd_repairing"))
                );

        sherdCrafting(Items.FISHING_ROD, Items.ANGLER_POTTERY_SHERD, "angler");
        sherdCrafting(Items.BOW, Items.ARCHER_POTTERY_SHERD, "archer");
        sherdCrafting(Items.RABBIT_HIDE, Items.ARMS_UP_POTTERY_SHERD, "arms_up");
        sherdCrafting(Items.STONE_SWORD, Items.BLADE_POTTERY_SHERD, "blade");
        sherdCrafting(Items.GLASS_BOTTLE, Items.BREWER_POTTERY_SHERD, "brewer");
        sherdCrafting(Items.CAMPFIRE, Items.BURN_POTTERY_SHERD, "burn");
        sherdCrafting(Items.GUNPOWDER, Items.DANGER_POTTERY_SHERD, "danger");
        sherdCrafting(Items.CHISELED_TUFF, Items.FLOW_POTTERY_SHERD, "flow");
        sherdCrafting(Items.MAP, Items.EXPLORER_POTTERY_SHERD, "explorer");
        sherdCrafting(Items.EMERALD, Items.FRIEND_POTTERY_SHERD, "friend");
        sherdCrafting(Items.WIND_CHARGE, Items.GUSTER_POTTERY_SHERD, "guster");
        sherdCrafting(Items.GOLDEN_APPLE, Items.HEART_POTTERY_SHERD, "heart");
        sherdCrafting(Items.POISONOUS_POTATO, Items.HEARTBREAK_POTTERY_SHERD, "heartbreak");
        sherdCrafting(Items.BONE, Items.HOWL_POTTERY_SHERD, "howl");
        sherdCrafting(Items.STONE_PICKAXE, Items.MINER_POTTERY_SHERD, "miner");
        sherdCrafting(Items.SCULK, Items.MOURNER_POTTERY_SHERD, "mourner");
        sherdCrafting(Items.CHEST, Items.PLENTY_POTTERY_SHERD, "plenty");
        sherdCrafting(Items.DIAMOND, Items.PRIZE_POTTERY_SHERD, "prize");
        sherdCrafting(Items.STONE_AXE, Items.SCRAPE_POTTERY_SHERD, "scrape");
        sherdCrafting(Items.WHEAT, Items.SHEAF_POTTERY_SHERD, "sheaf");
        sherdCrafting(Items.ACACIA_SAPLING, Items.SHELTER_POTTERY_SHERD, "shelter");
        sherdCrafting(Items.WITHER_SKELETON_SKULL, Items.SKULL_POTTERY_SHERD, "skull");
        sherdCrafting(Items.PITCHER_POD, Items.SNORT_POTTERY_SHERD, "snort");
        sherdCrafting(Items.ARMADILLO_SCUTE, MirageItems.HIDE_POTTERY_SHERD, "hide");
        sherdCrafting(Items.CACTUS, MirageItems.BARREN_POTTERY_SHERD, "barren");

        shaped(RecipeCategory.MISC, MirageItems.BLANK_POTTERY_SHERD)
                .pattern("#")
                .pattern(".")
                .pattern("#")
                .define('#', Items.BRICK)
                .define('.', Items.GOLD_NUGGET)
                .group("pottery_sherd")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(
                        output.withConditions(new MirageConfigCondition("blankSherdConstructing")),
                        ResourceKey.create(Registries.RECIPE, mirageLoc("blank_sherd_constructing"))
                );

        VANILLA_TRIM_MAP.forEach((trimItem, vanillaPattern) -> {
            SmithingTrimRecipeBuilder.smithingTrim(
                    Ingredient.of(trimItem),
                    Ingredient.of(MirageItems.ARMADILLO_CHESTPLATE),
                    Ingredient.of(registries.getOrThrow(ItemTags.TRIM_MATERIALS)),
                    registries.getOrThrow(MirageArmorTrimPatterns.toArmadillo(vanillaPattern)),
                    RecipeCategory.MISC
            ).unlocks("has_armadillo_chestplate", has(MirageItems.ARMADILLO_CHESTPLATE)).save(
                    output,
                    ResourceKey.create(
                            Registries.RECIPE,
                            mirageLoc(vanillaPattern.identifier().getPath() + "_armadillo_trimming")
                    )
            );
        });
    }

    public void fadedTerracottaSmelting(ItemLike inputItem, ItemLike outputItem, String name) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(inputItem),
                        RecipeCategory.BUILDING_BLOCKS,
                        CookingBookCategory.BLOCKS,
                        outputItem,
                        0.1f,
                        200)
                .unlockedBy("has_glazed_terracotta", has(inputItem))
                .save(
                        output.withConditions(fadedTerracottaSmeltingCondition),
                        ResourceKey.create(Registries.RECIPE, mirageLoc(name + "_smelting"))
                );
    }

    public void sherdCrafting(ItemLike inputItem, ItemLike outputItem, String name) {
        shapeless(RecipeCategory.MISC, outputItem)
                .requires(MirageItems.BLANK_POTTERY_SHERD)
                .requires(inputItem)
                .group("pottery_sherd")
                .unlockedBy("has_blank_sherd", has(MirageItems.BLANK_POTTERY_SHERD))
                .save(
                        output.withConditions(sherdCraftingCondition),
                        ResourceKey.create(Registries.RECIPE, mirageLoc(name + "_sherd_crafting"))
                );
        shaped(RecipeCategory.MISC, outputItem)
                .pattern("#")
                .pattern(".")
                .pattern("#")
                .define('#', Items.BRICK)
                .define('.', inputItem)
                .group("pottery_sherd")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(
                        output.withConditions(sherdConstructingCondition),
                        ResourceKey.create(Registries.RECIPE, mirageLoc(name + "_sherd_constructing"))
                );
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new MirageRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Mirage Recipes";
        }
    }
}
