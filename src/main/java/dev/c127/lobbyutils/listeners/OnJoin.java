package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Formatter;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setWalkSpeed(0.2f);

        e.setJoinMessage(null);

        player.getInventory().setHeldItemSlot(0);

        if (player.hasPermission(Lobbyutils.getPlugin().getName() + ".fly")) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setFlySpeed(0.2f);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
