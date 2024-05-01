package dev.c127.lobbyutils.commands;

import dev.c127.lobbyutils.Lobbyutils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Blind implements CommandExecutor {

    public static final ArrayList<String> blindPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (blindPlayers.contains(player.getUniqueId().toString())) {
            blindPlayers.remove(player.getUniqueId().toString());
            for (Player p : player.getServer().getOnlinePlayers())
                if (!p.equals(player))
                    player.showPlayer(Lobbyutils.getPlugin(), p);
            player.sendMessage("¡Ahora puedes ver a los demás jugadores!");
        } else {
            blindPlayers.add(player.getUniqueId().toString());
            for (Player p : player.getServer().getOnlinePlayers()) {
                //if (!p.equals(player) && (!p.hasPermission("lobbyutils.bypass.blind") || !p.hasPermission("lobbyutils.bypass.blind." + player.getUniqueId().toString())))
                if (!p.equals(player))
                    player.hidePlayer(Lobbyutils.getPlugin(), p);
            }
            player.sendMessage("¡Ahora no puedes ver a los demás jugadores!");
        }

        return true;
    }

}
