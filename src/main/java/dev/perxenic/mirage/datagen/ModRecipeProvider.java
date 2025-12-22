package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static dev.perxenic.mirage.Mirage.MODID;

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
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID, "armadillo_basket"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMADILLO_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_armadillo_scute", has(Items.ARMADILLO_SCUTE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID, "armadillo_chestplate"));
    }
}
