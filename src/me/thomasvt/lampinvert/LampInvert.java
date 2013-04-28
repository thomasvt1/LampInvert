package me.thomasvt.lampinvert;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LampInvert extends JavaPlugin implements Listener {

	public void onEnable() {
		getLogger().info("This plugin is made by: thomasvt and wiigor");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler()
	public void onRedstoneEvent(BlockRedstoneEvent event) {
		Block block = event.getBlock();
		if (block.getType() != Material.DAYLIGHT_DETECTOR)
			return; // only listen to daylight detectors redstone events
		Location l = block.getLocation().add(0, -1, 0);
		Material m = l.getBlock().getType();
		if (m != Material.REDSTONE_LAMP_OFF && m != Material.REDSTONE_LAMP_ON)
			return; // no lamp was found below the daylight detector
		int currentChange = event.getNewCurrent() - event.getOldCurrent();
		event.setNewCurrent(currentChange*= -1);
		//currentChange*= -1; //invert the currentChange
	}
}