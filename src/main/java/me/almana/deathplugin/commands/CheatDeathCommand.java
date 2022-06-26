package me.almana.deathplugin.commands;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class CheatDeathCommand implements CommandExecutor {

    private final Team dead;
    private final ArmorStand as;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public CheatDeathCommand(Team dead, ArmorStand as) {
        this.dead = dead;
        this.as = as;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player pl)) return true;
        if (args.length > 0) {

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {

                pl.sendMessage(mm.deserialize("<red> Argument needs to be a player."));
            } else if (!dead.hasPlayer(target)) {

                dead.removePlayer(target);
                target.removePotionEffect(PotionEffectType.BLINDNESS);
                target.removePotionEffect(PotionEffectType.GLOWING);
                target.clearTitle();
                target.sendMessage(mm.deserialize("<green>You have been resurrected by ").append(pl.displayName().color(NamedTextColor.GREEN)));
                as.removePassenger(target);
                as.remove();
            } else {

                pl.sendMessage(mm.deserialize("<red>The targetted player is not dead"));
            }
        } else if (dead.hasPlayer(pl)) {

            dead.removePlayer(pl);
            pl.removePotionEffect(PotionEffectType.BLINDNESS);
            pl.removePotionEffect(PotionEffectType.GLOWING);
            pl.clearTitle();
            pl.sendMessage(mm.deserialize("<green>You resurrected yourself."));
            as.removePassenger(pl);
            as.remove();
        } else {

            pl.sendMessage(mm.deserialize("<red>You arent dead."));
        }
        return true;
    }
}
