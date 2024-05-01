package dev.c127.lobbyutils.placeholders;

import dev.c127.lobbyutils.Lobbyutils;
import dev.c127.lobbyutils.commands.Blind;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholdersManager extends PlaceholderExpansion {

    private final Lobbyutils plugin;

    public PlaceholdersManager(Lobbyutils plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "c127";
    }

    @Override
    public String getIdentifier() {
        return "lobbyutils";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.startsWith("blinded__")) {
            String[] p = params.split("__");
            String ifc = p.length >1?p[1]:"";
            String elc = p.length >2?p[2]:"";
            return Blind.blindPlayers.contains(player.getUniqueId().toString())?ifc:elc;
        }
        if (params.equalsIgnoreCase("blinded")) {
            return String.valueOf(Blind.blindPlayers.contains(player.getUniqueId().toString()));
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
