package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setWalkSpeed(2f);

        if (player.hasPermission(Lobbyutils.getPlugin().getName() + ".fly")) {
            player.setFlying(true);
            player.setFlySpeed(2f);
        }
    }
}
