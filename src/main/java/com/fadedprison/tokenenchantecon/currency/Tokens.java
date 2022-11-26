package com.fadedprison.tokenenchantecon.currency;

import com.fadedprison.tokenenchantecon.Settings;
import com.insurgencenetwork.playershops.api.CurrencyModel;
import com.insurgencenetwork.playershops.shop.PlayerShop;
import com.insurgencenetwork.playershops.shop.ShopItem;
import com.vk2gpz.tokenenchant.api.TokenEnchantAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Tokens extends CurrencyModel {

    private static final TokenEnchantAPI tokenEnchantAPI = TokenEnchantAPI.getInstance();

    public Tokens() {
        super(Settings.CURRENCY_TYPE, Settings.CURRENCY_SYMBOL);
    }

    @Override
    public void registerCurrency() {
        getCurrencyProvider().registerCurrency(getCurrencyType(), this);
    }

    @Override
    public void sellItem(Player player, PlayerShop playerShop, ShopItem shopItem, SellItemCallback sellItemCallback) {
        tokenEnchantAPI.removeTokens(player, shopItem.getPrice());
        tokenEnchantAPI.addTokens(Bukkit.getOfflinePlayer(playerShop.getOwner()), shopItem.getPrice());
    }

    @Override
    public void buyItem(Player player, PlayerShop playerShop, double v, SellItemCallback sellItemCallback) {
        tokenEnchantAPI.addTokens(player, v);
        tokenEnchantAPI.removeTokens(Bukkit.getOfflinePlayer(playerShop.getOwner()), v);
    }

    @Override
    public boolean hasEnough(OfflinePlayer offlinePlayer, long l) {
        return tokenEnchantAPI.getTokens(offlinePlayer) >= l;
    }
}
