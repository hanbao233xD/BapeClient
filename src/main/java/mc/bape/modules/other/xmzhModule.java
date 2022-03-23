package mc.bape.modules.other;

import mc.bape.value.Option;
import org.lwjgl.input.Keyboard;
import mc.bape.Vapu.ModuleType;
import mc.bape.modules.Module;
import mc.bape.util.utils.Helper;

public class xmzhModule extends Module {
    private int counter = 0;
    private boolean warnAlready = false;
    private Option<Boolean> autoL = new Option<Boolean>("AutoL","AutoL", false);
    public xmzhModule(){
        super("xmzh", Keyboard.KEY_NONE, ModuleType.Other,"A little cat");
        Chinese="xmzh";
    }

    @Override
    public void enable() {
        Helper.sendMessage("xmzh is a cat!");
    }
}
