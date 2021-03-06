package org.mswsplex.anticheat.checks.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mswsplex.anticheat.checks.Check;
import org.mswsplex.anticheat.checks.CheckType;
import org.mswsplex.anticheat.data.CPlayer;
import org.mswsplex.anticheat.msws.NOPE;

/**
 * Basically {@link NoFall} but when the player hits an entity
 * 
 * @author imodm
 *
 */
public class Criticals1 implements Check, Listener {

	private NOPE plugin;

	@Override
	public CheckType getType() {
		return CheckType.COMBAT;
	}

	@Override
	public void register(NOPE plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() == null || !(event.getDamager() instanceof Player))
			return;
		Player damager = (Player) event.getDamager();
		CPlayer cp = plugin.getCPlayer(damager);

		if (damager.isOnGround()) // Client says player is on ground
			return;

		if (!cp.isOnGround()) // Player is not actually on the ground
			return;

		cp.flagHack(this, 5);
	}

	@Override
	public String getCategory() {
		return "Criticals";
	}

	@Override
	public String getDebugName() {
		return "Criticals#1";
	}

	@Override
	public boolean lagBack() {
		return false;
	}

	@Override
	public boolean onlyLegacy() {
		// TODO Auto-generated method stub
		return false;
	}
}
