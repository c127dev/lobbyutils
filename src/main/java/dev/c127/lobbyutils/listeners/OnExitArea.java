package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class OnExitArea implements Listener {
    private static int ratio = 128;
    private static ArrayList<String> allowedWorlds = new ArrayList<String>();

    OnExitArea() {
        // TODO: Allowed worlds in config.yml
        allowedWorlds.add("world");
    }

    @EventHandler
    public void onExitSpawn(PlayerChunkLoadEvent e) {
        Location loc = e.getPlayer().getLocation();

        Bukkit.getRegionScheduler().run(Lobbyutils.getPlugin(), loc, tsk->{
            World world = e.getWorld();
            if (!allowedWorlds.contains(world.getName())) return;

            Location spawn = world.getSpawnLocation();
            loc.setY(spawn.getY());

            if (loc.distance(spawn) < ratio) {
                e.getPlayer().teleport(spawn);
                e.getChunk().unload();
            }
        });
    }
}
