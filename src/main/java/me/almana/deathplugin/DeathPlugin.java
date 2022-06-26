package me.almana.deathplugin;

import me.almana.deathplugin.commands.CheatDeathCommand;
import me.almana.deathplugin.listeners.DeathListener;
import me.almana.deathplugin.listeners.ReviveListener;
import me.almana.deathplugin.listeners.VehicleListener;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public final class DeathPlugin extends JavaPlugin {

    private final Team dead = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("dead");
    private ArmorStand as;

    @Override
    public void onEnable() {

        dead.color(NamedTextColor.RED);
        Bukkit.getPluginManager().registerEvents(new DeathListener(dead, as), this);
        Bukkit.getPluginManager().registerEvents(new ReviveListener(dead, as), this);
        Bukkit.getPluginManager().registerEvents(new VehicleListener(dead), this);
        Bukkit.getPluginCommand("cheatdeath").setExecutor(new CheatDeathCommand(dead, as));
        getLogger().info("Plugin Enabled.");
    }


}
