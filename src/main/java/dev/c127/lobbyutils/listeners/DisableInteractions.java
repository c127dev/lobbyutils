package dev.c127.lobbyutils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class DisableInteractions implements Listener {
    @EventHandler
    public void handleInteraction(PlayerInteractEvent e) {
        if (!e.getPlayer().hasPermission("lobbyutils.world.interact")) {
            e.setCancelled(true);
        }
    }
}
