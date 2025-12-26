package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.ModBlocks;
import dev.perxenic.mirage.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMADILLO_BASKET)
                .pattern(" S ")
                .pattern("A A")
                .pattern("AAA")
                .define('S', Items.STRING)
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(recipeOutput, mirageLoc("armadillo_basket"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMADILLO_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(recipeOutput, mirageLoc("armadillo_chestplate"));

        fadedTerracottaSmelting(recipeOutput,
                Items.WHITE_GLAZED_TERRACOTTA,
                ModBlocks.WHITE_FADED_TERRACOTTA,
                "white_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIGHT_GRAY_GLAZED_TERRACOTTA,
                ModBlocks.LIGHT_GRAY_FADED_TERRACOTTA,
                "light_gray_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.GRAY_GLAZED_TERRACOTTA,
                ModBlocks.GRAY_FADED_TERRACOTTA,
                "gray_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BLACK_GLAZED_TERRACOTTA,
                ModBlocks.BLACK_FADED_TERRACOTTA,
                "black_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BROWN_GLAZED_TERRACOTTA,
                ModBlocks.BROWN_FADED_TERRACOTTA,
                "brown_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.RED_GLAZED_TERRACOTTA,
                ModBlocks.RED_FADED_TERRACOTTA,
                "red_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.ORANGE_GLAZED_TERRACOTTA,
                ModBlocks.ORANGE_FADED_TERRACOTTA,
                "orange_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.YELLOW_GLAZED_TERRACOTTA,
                ModBlocks.YELLOW_FADED_TERRACOTTA,
                "yellow_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIME_GLAZED_TERRACOTTA,
                ModBlocks.LIME_FADED_TERRACOTTA,
                "lime_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.GREEN_GLAZED_TERRACOTTA,
                ModBlocks.GREEN_FADED_TERRACOTTA,
                "green_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.CYAN_GLAZED_TERRACOTTA,
                ModBlocks.CYAN_FADED_TERRACOTTA,
                "cyan_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.LIGHT_BLUE_GLAZED_TERRACOTTA,
                ModBlocks.LIGHT_BLUE_FADED_TERRACOTTA,
                "light_blue_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.BLUE_GLAZED_TERRACOTTA,
                ModBlocks.BLUE_FADED_TERRACOTTA,
                "blue_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.PURPLE_GLAZED_TERRACOTTA,
                ModBlocks.PURPLE_FADED_TERRACOTTA,
                "purple_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.MAGENTA_GLAZED_TERRACOTTA,
                ModBlocks.MAGENTA_FADED_TERRACOTTA,
                "magenta_faded_terracotta"
        );
        fadedTerracottaSmelting(recipeOutput,
                Items.PINK_GLAZED_TERRACOTTA,
                ModBlocks.PINK_FADED_TERRACOTTA,
                "pink_faded_terracotta"
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
                .save(recipeOutput, mirageLoc(name));
    }
}
