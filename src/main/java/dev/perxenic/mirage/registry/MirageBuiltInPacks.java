package dev.perxenic.mirage.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

@EventBusSubscriber
public class MirageBuiltInPacks {
    @SubscribeEvent
    public static void addFeaturePacks(final AddPackFindersEvent event) {
        event.addPackFinders(
                mirageLoc("data/"+MIRAGE_ID+"/datapacks/badlands_surface"),
                PackType.SERVER_DATA,
                Component.literal("Mirage: Badlands Surface"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
        event.addPackFinders(
                mirageLoc("data/"+MIRAGE_ID+"/datapacks/desert_surface"),
                PackType.SERVER_DATA,
                Component.literal("Mirage: Desert Surface"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
        event.addPackFinders(
                mirageLoc("data/"+MIRAGE_ID+"/datapacks/desert_underground"),
                PackType.SERVER_DATA,
                Component.literal("Mirage: Desert Underground"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
    }
}
