package me.rosux.magic;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Magic extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Lets sprinkle a little magic into your world.");
        getServer().getPluginManager().registerEvents(new eventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Lets magically remove the magic from your world.");
    }
}
