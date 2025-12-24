package floffy.toffys_hooks;


import jdk.jfr.ContentType;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.text.Text;

import java.lang.annotation.Annotation;

@Config(name = "toffys_hooks")
@Config.Gui.Background("minecraft:textures/block/tuff.png")

public class HooksConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public  GrapplingHookSettings GrapplingHookOpen = new GrapplingHookSettings();
    public static class GrapplingHookSettings {
        public boolean enabled = true;
        public boolean noDurability = true;
        public int MaxDistance = 100;
        public float Speed = 5;
    }
    @ConfigEntry.Gui.CollapsibleObject
    public  MultiToolSettings MultiToolOpen = new MultiToolSettings();
    public static class MultiToolSettings {
        public boolean enabled = true;
        public boolean noDurability = false;
    }
    @ConfigEntry.Gui.CollapsibleObject
    public ClimbingClawsSettings ClimbingClawsOpen = new ClimbingClawsSettings();
    public static class ClimbingClawsSettings {
        public boolean enabled = true;
        public int jumpStrengthMultiplier = 1;
    }
    @ConfigEntry.Gui.CollapsibleObject
    public skatesSettings skatesOpen = new skatesSettings();
    public static class skatesSettings {
        public  boolean enabled = true;
        public float accumulationRate = 0.005f;
        public float maxSpeed = 2;
    }
}
