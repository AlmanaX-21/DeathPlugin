package me.almana.deathplugin.listeners;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class DeathListener implements Listener {

    private final Team dead;
    private ArmorStand as;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public DeathListener(Team dead, ArmorStand as) {
        this.dead = dead;
        this.as = as;
    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e) {

        Player victim = e.getPlayer();
        Player attacker = victim.getKiller();

        if (attacker == null) return;

        as = victim.getWorld().spawn(victim.getLocation().add(0, 50, 0), ArmorStand.class);
        as.setMarker(true);
        as.setInvulnerable(true);
        as.setGravity(true);
        as.setInvisible(true);
        as.teleport(victim.getLocation());

        victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000, 1, true, false, false));
        victim.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10000, 1, true, false, false));
        victim.showTitle(Title.title(mm.deserialize("<red>You died!"), mm.deserialize("<green>Wait for revival.")));

        as.addPassenger(victim);
        dead.addPlayer(victim);
    }
}
