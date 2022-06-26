package me.almana.deathplugin.listeners;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.scoreboard.Team;

public class VehicleListener implements Listener {

    private final Team dead;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public VehicleListener(Team dead) {
        this.dead = dead;
    }

    @EventHandler
    public void onDisbound(VehicleExitEvent e) {

        if (!(e.getExited() instanceof Player player)) return;
        if (dead.hasPlayer(player) && e.getVehicle().getType() == EntityType.ARMOR_STAND) {

            e.setCancelled(true);
            player.sendActionBar(mm.deserialize("<red>You cant move as a soul."));
        }
    }
}
