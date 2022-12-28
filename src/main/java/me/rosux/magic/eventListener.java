package me.rosux.magic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class eventListener implements Listener {
    Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
    private Team getTeam(){
        Team t = score.getTeam("hide");
        if(t == null){
            t = score.registerNewTeam("hide");
        }
        t.setCanSeeFriendlyInvisibles(false);
        t.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        return t;
    }
    public void hidePlayer(Player player){
        getTeam().addPlayer(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 1, false, false));
        Location loc = player.getLocation();
        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 2);
        player.getWorld().spawnParticle(Particle.LAVA, loc, 25, 0, 1, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 0, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 1, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 2, 0);
    }
    public void showPlayer(Player player){
        getTeam().removePlayer(player);
        Location loc = player.getLocation();
        player.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        player.getWorld().spawnParticle(Particle.LAVA, loc, 5, 0, 0, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 0, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 1, 0);
        player.getWorld().spawnParticle(Particle.SCRAPE, loc, 10, 0, 2, 0);
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e){
        if(!e.getPlayer().isSneaking()){
            // hide player
            hidePlayer(e.getPlayer());
        }else{
            // show player
            showPlayer(e.getPlayer());
        }
    }
}
