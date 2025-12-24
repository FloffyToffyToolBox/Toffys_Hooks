package floffy.toffys_hooks.register;

import floffy.toffys_hooks.HooksConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ModConfig {
    public static HooksConfig CONFIG = new HooksConfig();
    public static void register(){
        AutoConfig.register(HooksConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(HooksConfig.class).getConfig();
    }
}