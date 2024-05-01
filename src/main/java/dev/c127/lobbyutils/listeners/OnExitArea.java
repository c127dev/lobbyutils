package dev.c127.lobbyutils.listeners;

import dev.c127.lobbyutils.Lobbyutils;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class OnExitArea implements Listener {
    private final short chunkRatio = 2;
    private final short maxUp = 160;
    private final short maxDown = 32;
    private final static HashMap<Long,Boolean> eChunk = new HashMap<>();

    public OnExitArea() {
        // TODO: Allowed worlds in config.yml
        //allowedWorlds.add("world");
    }

    @EventHandler
    public void onExitSpawn(ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        int X = chunk.getX();
        int Z = chunk.getZ();
        long key = this.getKey(X, Z);

        if (!eChunk.containsKey(key)) Bukkit.getRegionScheduler().run(Lobbyutils.getPlugin(), e.getWorld(), X, Z, tsk-> {
            Boolean empty = emptyChunk(e.getChunk());
            eChunk.put(key, empty);
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Chunk chunk = e.getTo().getChunk();
        int cX = chunk.getX();
        int cZ = chunk.getZ();

        if (eChunk.get(this.getKey(cX, cZ)) && !nearbyToRegion(cX, cZ, chunkRatio))
            e.getPlayer().teleportAsync(e.getFrom().getWorld().getSpawnLocation());
        else {
            double Y = e.getPlayer().getLocation().getY();
            if ( Y > this.maxUp || Y < this.maxDown) e.getPlayer().teleportAsync(e.getFrom().getWorld().getSpawnLocation());
        }
    }

    private boolean nearbyToRegion(final int cX, final int cZ, final short ratio) {
        for (int X=-ratio;X<ratio;X++) {
            int b=ratio-Math.abs(X);

            for (int Z=ratio-b;Z<=ratio+b;Z++)
                if (!eChunk.get(this.getKey(cX+X, cZ+Z)))
                    return true;
        }

        return false;
    }

    public boolean emptyChunk(Chunk chunk) {
        for (int x = 0; x < 16; x++) for (int y = chunk.getWorld().getMinHeight(); y < chunk.getWorld().getMaxHeight(); y++) for (int z = 0; z < 16; z++) if (!chunk.getBlock(x, y, z).isEmpty()) return false;
        return true;
    }

    private long getKey(int X, int Z) {
        return (((long) X) << 32) | (Z & 0xFFFFFFFFL);
    }
}
