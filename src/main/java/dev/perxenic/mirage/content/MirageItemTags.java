package dev.perxenic.mirage.content;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageItemTags {
    public final static TagKey<Item> BADLANDS_RUINS_SHERDS = ItemTags.create(mirageLoc("badlands_ruins_sherds"));
    public final static TagKey<Item> DESERT_RUINS_SHERDS = ItemTags.create(mirageLoc("desert_ruins_sherds"));
    public final static TagKey<Item> PLATEAU_RUINS_SHERDS = ItemTags.create(mirageLoc("plateau_ruins_sherds"));
}
