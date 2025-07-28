package com.example.citizenstrade;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CitizensTradeGUI extends JavaPlugin implements Listener {
    private Economy econ;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe("Vault không được phát hiện!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("CitizensTradeGUI đã bật!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CitizensTradeGUI đã tắt!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    @EventHandler
    public void onNPCClick(NPCClickEvent e) {
        Player player = e.getClicker();
        int id = e.getNPC().getId();
        if (TradeStorage.hasTrade(id)) {
            Inventory gui = Bukkit.createInventory(null, 9, "Giao Dịch NPC #" + id);
            // Tạo item hiển thị (ở đây đơn giản)
            player.openInventory(gui);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length > 0 && args[0].equalsIgnoreCase("edit")) {
            Inventory npcList = Bukkit.createInventory(null, 27, "Chỉnh Sửa NPC Giao Dịch");
            // Đặt item đại diện cho NPC ở đây
            player.openInventory(npcList);
            return true;
        }
        return false;
    }
}
