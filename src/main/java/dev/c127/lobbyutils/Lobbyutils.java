package dev.c127.lobbyutils;

import dev.c127.lobbyutils.listeners.OnDamage;
import dev.c127.lobbyutils.listeners.OnJoin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Lobbyutils extends JavaPlugin {
    private static Lobbyutils instance;
    private ArrayList<Listener> events = new ArrayList<Listener>();

    @Override
    public void onLoad() {
        instance = this;

        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        this.events.add(new OnDamage());
        this.events.add(new OnJoin());

        for (Listener event : this.events)
            Bukkit.getServer().getPluginManager().registerEvents(event, this);
    }

    public static Lobbyutils getPlugin() {
        return instance;
    }
}
