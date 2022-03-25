package mc.bape.modules.other;

import mc.bape.modules.Module;
import mc.bape.Vapu.ModuleType;
import mc.bape.util.utils.TimerUtil;
import mc.bape.Vapu.Client;
import org.lwjgl.input.Keyboard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class IRC extends Module {
    public static Socket socket;
    public static PrintWriter IRCIO;
    static InputStream Stream;
    public BufferedReader reader;
    private TimerUtil timer = new TimerUtil();
    public IRC() {
        super("IRC", Keyboard.KEY_NONE, ModuleType.Other, Client.name + " IRC Chat");
        Chinese=("Debug");
    }
}