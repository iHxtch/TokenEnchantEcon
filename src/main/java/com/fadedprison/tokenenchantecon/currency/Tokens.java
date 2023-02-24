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
    public void sellItem(Player buyer, PlayerShop playerShop, ShopItem shopItem, SellItemCallback sellItemCallback) {
        if (!hasEnough(buyer, shopItem.getPrice().longValue())){
            sellItemCallback.onSell(false);
            return;
        }

        tokenEnchantAPI.removeTokens(buyer, shopItem.getPrice());
        tokenEnchantAPI.addTokens(Bukkit.getOfflinePlayer(playerShop.getOwner()), shopItem.getPrice());
        sellItemCallback.onSell(true);
    }

    @Override
    public void buyItem(Player seller, PlayerShop playerShop, double price, SellItemCallback sellItemCallback) {
        OfflinePlayer shopOwner = Bukkit.getOfflinePlayer(playerShop.getOwner());
        if (!hasEnough(shopOwner, (long) price)){
            sellItemCallback.onSell(false);
            return;
        }

        tokenEnchantAPI.addTokens(seller, price);
        tokenEnchantAPI.removeTokens(shopOwner, price);
        sellItemCallback.onSell(true);
    }

    @Override
    public boolean hasEnough(OfflinePlayer offlinePlayer, long amount) {
        return tokenEnchantAPI.getTokens(offlinePlayer) >= amount;
    }
}
