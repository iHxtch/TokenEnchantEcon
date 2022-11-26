package com.fadedprison.tokenenchantecon;

import com.insurgencenetwork.playershops.lib.settings.YamlConfig;
import com.insurgencenetwork.playershops.utils.configutils.AddonConfigUtil;

public class Settings extends YamlConfig {

    public static String CURRENCY_TYPE;
    public static String CURRENCY_SYMBOL;

    public Settings() {
        AddonConfigUtil.saveResource(TokenEnchantEcon.getInstance(), "", "tokenenchant-econ-settings.yml", "tokenenchant-econ-settings", false);
        loadConfiguration(NO_DEFAULT, "addons/TokenEnchantEcon/tokenenchant-econ-settings.yml");
    }

    @Override
    protected void onLoad() {
        CURRENCY_TYPE = getString("Currency_Type");
        CURRENCY_SYMBOL = getString("Currency_Symbol");
    }
}
