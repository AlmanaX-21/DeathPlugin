package me.almana.deathplugin.listeners;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class ReviveListener implements Listener {

    private final Team dead;
    private final MiniMessage mm = MiniMessage.miniMessage();
    private final ArmorStand as;

    public ReviveListener(Team dead, ArmorStand as) {
        this.dead = dead;
        this.as = as;
    }

    @EventHandler
    public void onReviveAttempt(PlayerInteractAtEntityEvent e) {

        Player player = e.getPlayer();

        if (!(e.getRightClicked() instanceof Player dudBoi)) return;
        if (dead.hasPlayer(dudBoi) && player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_APPLE) {

            dead.removePlayer(dudBoi);
            dudBoi.removePotionEffect(PotionEffectType.BLINDNESS);
            dudBoi.removePotionEffect(PotionEffectType.GLOWING);
            dudBoi.clearTitle();
            dudBoi.sendMessage(mm.deserialize("<green>You have been resurrected by ").append(player.displayName().color(NamedTextColor.GREEN)));
            as.removePassenger(dudBoi);
            as.remove();
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }
}
