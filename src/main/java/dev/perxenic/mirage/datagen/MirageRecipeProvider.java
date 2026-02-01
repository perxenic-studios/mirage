package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.content.conditions.MirageConfigCondition;
import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.registry.MirageItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public static final MirageConfigCondition fadedTerracottaSmeltingCondition =
            new MirageConfigCondition("fadedTerracottaSmelting");

    public static final MirageConfigCondition sherdCraftingCondition =
            new MirageConfigCondition("sherdCrafting");

    public static final MirageConfigCondition sherdConstructingCondition =
            new MirageConfigCondition("sherdConstructing");

    public MirageRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MirageItems.ARMADILLO_BASKET)
                .pattern(" S ")
                .pattern("A A")
                .pattern("AAA")
                .define('S', Items.STRING)
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(recipeOutput, mirageLoc("armadillo_basket"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MirageItems.ARMADILLO_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(recipeOutput, mirageLoc("armadillo_chestplate"));

        fadedTerracottaSmelting(recipeOutput,
                Items.WHITE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_SUN_TERRACOTTA,
                "faded_sun_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIGHT_GRAY_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_MODERN_TERRACOTTA,
                "faded_modern_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.GRAY_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CROSS_TERRACOTTA,
                "faded_cross_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BLACK_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CRAWL_TERRACOTTA,
                "faded_crawl_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BROWN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_TARGET_TERRACOTTA,
                "faded_target_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.RED_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_POTION_TERRACOTTA,
                "faded_potion_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.ORANGE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FISH_TERRACOTTA,
                "faded_fish_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.YELLOW_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FLOWER_TERRACOTTA,
                "faded_flower_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIME_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_SPOKE_TERRACOTTA,
                "faded_spoke_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.GREEN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_PLANT_TERRACOTTA,
                "faded_plant_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.CYAN_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_CREEP_TERRACOTTA,
                "faded_creep_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIGHT_BLUE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_GEO_TERRACOTTA,
                "faded_geo_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BLUE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_FAN_TERRACOTTA,
                "faded_fan_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.PURPLE_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_BLADE_TERRACOTTA,
                "faded_blade_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.MAGENTA_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_ARROW_TERRACOTTA,
                "faded_arrow_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.PINK_GLAZED_TERRACOTTA,
                MirageBlocks.FADED_LEAF_TERRACOTTA,
                "faded_leaf_terracotta"
        );

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ItemTags.DECORATED_POT_SHERDS),
                        RecipeCategory.MISC,
                        MirageItems.CRACKED_POTTERY_SHERD,
                        0f,
                        200)
                .unlockedBy("has_sherd", has(ItemTags.DECORATED_POT_SHERDS))
                .save(
                        recipeOutput.withConditions(new MirageConfigCondition("sherdCracking")),
                        mirageLoc("sherd_cracking")
                );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MirageItems.BLANK_POTTERY_SHERD)
                .requires(MirageItems.CRACKED_POTTERY_SHERD)
                .requires(Items.BRICK)
                .group("pottery_sherd")
                .unlockedBy("has_cracked_sherd", has(MirageItems.CRACKED_POTTERY_SHERD))
                .save(
                        recipeOutput.withConditions(new MirageConfigCondition("sherdRepairing")),
                        mirageLoc("sherd_repairing")
                );

        sherdCrafting(recipeOutput, Items.FISHING_ROD, Items.ANGLER_POTTERY_SHERD, "angler");
        sherdCrafting(recipeOutput, Items.BOW, Items.ARCHER_POTTERY_SHERD, "archer");
        sherdCrafting(recipeOutput, Items.RABBIT_HIDE, Items.ARMS_UP_POTTERY_SHERD, "arms_up");
        sherdCrafting(recipeOutput, Items.STONE_SWORD, Items.BLADE_POTTERY_SHERD, "blade");
        sherdCrafting(recipeOutput, Items.GLASS_BOTTLE, Items.BREWER_POTTERY_SHERD, "brewer");
        sherdCrafting(recipeOutput, Items.CAMPFIRE, Items.BURN_POTTERY_SHERD, "burn");
        sherdCrafting(recipeOutput, Items.GUNPOWDER, Items.DANGER_POTTERY_SHERD, "danger");
        sherdCrafting(recipeOutput, Items.CHISELED_TUFF, Items.FLOW_POTTERY_SHERD, "flow");
        sherdCrafting(recipeOutput, Items.MAP, Items.EXPLORER_POTTERY_SHERD, "explorer");
        sherdCrafting(recipeOutput, Items.EMERALD, Items.FRIEND_POTTERY_SHERD, "friend");
        sherdCrafting(recipeOutput, Items.WIND_CHARGE, Items.GUSTER_POTTERY_SHERD, "guster");
        sherdCrafting(recipeOutput, Items.GOLDEN_APPLE, Items.HEART_POTTERY_SHERD, "heart");
        sherdCrafting(recipeOutput, Items.POISONOUS_POTATO, Items.HEARTBREAK_POTTERY_SHERD, "heartbreak");
        sherdCrafting(recipeOutput, Items.BONE, Items.HOWL_POTTERY_SHERD, "howl");
        sherdCrafting(recipeOutput, Items.STONE_PICKAXE, Items.MINER_POTTERY_SHERD, "miner");
        sherdCrafting(recipeOutput, Items.SCULK , Items.MOURNER_POTTERY_SHERD, "mourner");
        sherdCrafting(recipeOutput, Items.CHEST, Items.PLENTY_POTTERY_SHERD, "plenty");
        sherdCrafting(recipeOutput, Items.DIAMOND, Items.PRIZE_POTTERY_SHERD, "prize");
        sherdCrafting(recipeOutput, Items.STONE_AXE, Items.SCRAPE_POTTERY_SHERD, "scrape");
        sherdCrafting(recipeOutput, Items.WHEAT, Items.SHEAF_POTTERY_SHERD, "sheaf");
        sherdCrafting(recipeOutput, Items.ACACIA_SAPLING, Items.SHELTER_POTTERY_SHERD, "shelter");
        sherdCrafting(recipeOutput, Items.WITHER_SKELETON_SKULL, Items.SKULL_POTTERY_SHERD, "skull");
        sherdCrafting(recipeOutput, Items.PITCHER_POD, Items.SNORT_POTTERY_SHERD, "snort");
        sherdCrafting(recipeOutput, Items.ARMADILLO_SCUTE, MirageItems.HIDE_POTTERY_SHERD, "hide");
        sherdCrafting(recipeOutput, Items.CACTUS, MirageItems.BARREN_POTTERY_SHERD, "barren");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MirageItems.BLANK_POTTERY_SHERD)
                .pattern("#")
                .pattern(".")
                .pattern("#")
                .define('#', Items.BRICK)
                .define('.', Items.GOLD_NUGGET)
                .group("pottery_sherd")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(
                        recipeOutput.withConditions(new MirageConfigCondition("blankSherdConstructing")),
                        mirageLoc("blank_sherd_constructing")
                );
    }

    public void fadedTerracottaSmelting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, String name) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.BUILDING_BLOCKS,
                        output,
                        0.1f,
                        200)
                .unlockedBy("has_glazed_terracotta", has(input))
                .save(recipeOutput.withConditions(fadedTerracottaSmeltingCondition), mirageLoc(name + "_smelting"));
    }

    public void sherdCrafting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, String name) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output)
                .requires(MirageItems.BLANK_POTTERY_SHERD)
                .requires(input)
                .group("pottery_sherd")
                .unlockedBy("has_blank_sherd", has(MirageItems.BLANK_POTTERY_SHERD))
                .save(
                        recipeOutput.withConditions(sherdCraftingCondition),
                        mirageLoc(name+"_sherd_crafting")
                );
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("#")
                .pattern(".")
                .pattern("#")
                .define('#', Items.BRICK)
                .define('.', input)
                .group("pottery_sherd")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(
                        recipeOutput.withConditions(sherdConstructingCondition),
                        mirageLoc(name+"_sherd_constructing")
                );
    }
}
