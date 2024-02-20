package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {
    @EventHandler
    public void handleDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        DamageType source = e.getDamageSource().getDamageType();

        Player player = (Player) e.getEntity();
        e.setCancelled(true);

        if (source.equals(DamageType.OUT_OF_WORLD)) {
            Bukkit.getRegionScheduler().run(Lobbyutils.getPlugin(), player.getLocation(), tsk-> {
                Location spawn = player.getWorld().getSpawnLocation();
                player.teleport(spawn);
            });
        }
    }
}
