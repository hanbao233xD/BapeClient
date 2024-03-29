package mc.bape.modules;

import mc.bape.Manager.ConfigManager;
import mc.bape.Manager.ModuleManager;
import mc.bape.Vapu.ModuleType;
import mc.bape.util.utils.Helper;
import mc.bape.Vapu.Client;
import mc.bape.value.Value;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Module {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public boolean state = false;
    public int key;
    public List<Value> values = new ArrayList<>();
    public static String Chinese;
    public static String About;
    public static boolean NoToggle = false;
    public String name;
    public String Descript;
    public ModuleType category;
    public float optionAnim = 0;// present
    public float optionAnimNow = 0;// present
    public float hoverPercent;
    public float lastHoverPercent;

    public Module(String name, int key, ModuleType category, String Descript) {
        this.name = name;
        this.key = key;
        this.category = category;
        this.Descript = Descript;
    }

    protected void addValues(Value... values) {
        Value[] var5 = values;
        int var4 = values.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Value value = var5[var3];
            this.values.add(value);
        }

    }

    public List<Value> getValues() {
        return this.values;
    }



    public void toggle() {
        if(NoToggle){
            if(Client.MessageON){
                if (this.state) {
                    Helper.sendMessage("Module" + " "+ this.getName() + " Disabled");
                } else {
                    Helper.sendMessage("Module" + " " + this.getName() + " Enabled");
                }
            }
        }
        mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        this.setState(!this.state);
    }

    public void setState(boolean state) {
        mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register(this);
            FMLCommonHandler.instance().bus().register(this);
            enable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
            disable();
        }
    }

    public void setModuleState(boolean state, Module module) {
        mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register(module);
            FMLCommonHandler.instance().bus().register(module);
            enable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(module);
            FMLCommonHandler.instance().bus().unregister(module);
            disable();
        }
    }

    public void enable() {

    }

    public void disable() {

    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return About;
    }

    public String getChinese() {
        return Chinese;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getState() {
        return state;
    }

    public void setKey(int key) {
        this.key = key;
        String content = "";
        Module m;
        for(Iterator var4 = ModuleManager.getModules().iterator(); var4.hasNext(); content = content + String.format("%s:%s%s", new Object[]{m.getName(), Keyboard.getKeyName(m.getKey()), System.lineSeparator()})) {
            m = (Module)var4.next();
        }
        ConfigManager.save(Client.config+"-binds.cfg", content, false);
    }

    public ModuleType getCategory() {
        return category;
    }

    public void setCategory(ModuleType category) {
        this.category = category;
    }

    public void onRenderWorldLast(RenderWorldLastEvent event) {
    }


    public String getDescription() {
        return About;
    }
}