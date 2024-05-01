package dev.c127.lobbyutils;

import dev.c127.lobbyutils.commands.Blind;
import dev.c127.lobbyutils.listeners.DisableInteractions;
import dev.c127.lobbyutils.listeners.OnDamage;
import dev.c127.lobbyutils.listeners.OnExitArea;
import dev.c127.lobbyutils.listeners.OnJoin;
import dev.c127.lobbyutils.placeholders.PlaceholdersManager;
import me.clip.placeholderapi.libs.kyori.adventure.text.format.NamedTextColor;
import me.clip.placeholderapi.libs.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Lobbyutils extends JavaPlugin {
    private static Lobbyutils instance;
    private final ArrayList<Listener> events = new ArrayList<>();

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
            throw new RuntimeException("Could not find PlaceholderAPI!");
        new PlaceholdersManager(this).register();
        this.registerEvents();
        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        this.events.add(new OnDamage());
        this.events.add(new OnJoin());
        this.events.add(new OnExitArea());
        //this.events.add(new DisableInteractions());

        for (Listener event : this.events)
            Bukkit.getServer().getPluginManager().registerEvents(event, this);
    }

    private void registerCommands() {
        this.getCommand("blind").setExecutor(new Blind());
    }

    public static Lobbyutils getPlugin() {
        return instance;
    }


    public static String format(String text, Object ...args) {
        return new Formatter().format(text, args).toString();
    }
}
