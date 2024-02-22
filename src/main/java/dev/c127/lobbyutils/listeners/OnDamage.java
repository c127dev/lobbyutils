package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {

    public OnDamage() {
        ((CraftServer) Lobbyutils.getPlugin().getServer())
    }

    @EventHandler
    public void handleDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        EntityDamageEvent.DamageCause source = e.getCause();

        Player player = (Player) e.getEntity();
        e.setCancelled(true);

        if (source.equals(EntityDamageEvent.DamageCause.VOID)) {
            Bukkit.getRegionScheduler().run(Lobbyutils.getPlugin(), player.getLocation(), tsk-> {
                Location spawn = player.getWorld().getSpawnLocation();
                player.teleportAsync(spawn);
            });
        }
    }
}
