package com.fadedprison.tokenenchantecon;

import com.fadedprison.tokenenchantecon.currency.Tokens;
import com.insurgencenetwork.playershops.PlayerShops;
import com.insurgencenetwork.playershops.api.extension.PlayerShopsAddon;
import com.insurgencenetwork.playershops.api.extension.annotation.AddonInfo;
import com.insurgencenetwork.playershops.settings.Settings;
import org.jetbrains.annotations.NotNull;

@AddonInfo(author = "Hxtch", name = "TokenEnchantEcon", version = "1.0",
        description = "Adds TokenEnchant support to PlayerShops", dependencies = "TokenEnchant")
public class TokenEnchantEcon extends PlayerShopsAddon {

    private static PlayerShopsAddon instance;

    public static PlayerShopsAddon getInstance() {
        return instance;
    }

    @Override
    public void initialize(@NotNull PlayerShops playerShops) {
        instance = this;
        new Settings();
        new Tokens().registerCurrency();
    }
}
