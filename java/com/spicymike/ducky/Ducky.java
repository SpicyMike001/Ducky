package com.spicymike.ducky;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ducky extends JavaPlugin {

    private String domain;
    private String token;

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        saveResource("README.md", false);
        saveResource("LICENSE.md", false);
        domain = config.getString("duckdns.domain");
        token = config.getString("duckdns.token");
        int intervalMinutes = config.getInt("duckdns.update-interval-minutes", 5);

        int intervalTicks = 20 * 60 * intervalMinutes;

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateDuckDNS, intervalTicks, intervalTicks);

        Bukkit.getScheduler().runTaskAsynchronously(this, this::updateDuckDNS);

        getLogger().info("DuckDNSUpdater enabled. This plugin is NOT affiliated with DuckDNS. Please read README.md");
    }


    private void updateDuckDNS() {
        try {
            String urlString = "https://www.duckdns.org/update?domains=" + domain + "&token=" + token;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (InputStream in = connection.getInputStream()) {
                byte[] response = in.readAllBytes();
                String result = new String(response);
                if (result.contains("OK")) {
                    getLogger().info("DuckDNS update request accepted.");
                } else {
                    getLogger().warning("DuckDNS update failed: " + result);
                }
            }
        } catch (Exception e) {
            getLogger().severe("Error updating DuckDNS: " + e.getMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ducky")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("test")) {
                Bukkit.getScheduler().runTaskAsynchronously(this, this::updateDuckDNS);
                sender.sendMessage("DuckDNS update test triggered!");
                return true;
            }
            sender.sendMessage("Usage: /ducky test");
            return true;
        }
        return false;
    }
}
