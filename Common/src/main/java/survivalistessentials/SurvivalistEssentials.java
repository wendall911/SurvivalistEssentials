package survivalistessentials;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import technology.roughness.whitenoise.config.WhiteNoiseConfig;
import technology.roughness.whitenoise.config.WhiteNoiseConfigLoader;
import technology.roughness.whitenoise.platform.Services;

import survivalistessentials.config.ConfigHandler;

public class SurvivalistEssentials {

    public static final String MODID = "survivalistessentials";
    public static final String MOD_NAME = "SurvivalistEssentials";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final Random RANDOM = new Random();

    public static void init() {
    }

    public static void initConfig() {
        WhiteNoiseConfig commonConfig = WhiteNoiseConfigLoader.add(WhiteNoiseConfig.Type.COMMON, ConfigHandler.COMMON_SPEC, SurvivalistEssentials.MODID);
        commonConfig.addLoadListener((config, flags) -> {
            ConfigHandler.init();
        });
        if (Services.WN_PLATFORM.isPhysicalClient()) {
            WhiteNoiseConfigLoader.add(WhiteNoiseConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC, SurvivalistEssentials.MODID);
        }
    }

}
