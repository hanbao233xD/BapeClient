package mc.bape.modules.other;

import mc.bape.value.Option;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.lwjgl.input.Keyboard;
import mc.bape.Vapu.ModuleType;
import mc.bape.modules.Module;
import mc.bape.util.utils.Helper;

import java.util.Random;

import static org.lwjgl.input.Keyboard.KEY_G;
import static org.lwjgl.input.Keyboard.KEY_P;

public class AutoGG extends Module {
//    private final String[] ���Ƕ�ı = new String[]{"OK�ֵ���", "ȫ��Ŀ�����ҿ���", "���ҿ���", "����������", "���Ǹ�{AWESOME}��"};
//    private final String[] �������� = new String[]{"ɵ��","ʥ��", "ʧ��", "���", "�豸", "ʶ��", "�ֱ�", "�ϰ�", "���", "�ֱ�", "˳��", "ɳ��", "�̱�", "�ְ�", "ˮ��", "�籣", "ˮ��", "�ֱ�", "�鱾", "ɴ��", "ɨ��", "ˮ��", "�ձ�"};
    private int counter = 0;
    private boolean warnAlready = false;


    static String[] GGs = {
            "GG",
            "gg",
            "Gg",
            "gG"
            };
    public AutoGG(){
        super("AutoGG", Keyboard.KEY_NONE, ModuleType.Other,"send GG when you press G");
        Chinese="���Զ�GG";
    }

    @Override
    public void enable() {
        Helper.sendMessage("Press G to send GG");
    }

    @SubscribeEvent
    public void keyInput(InputEvent.KeyInputEvent event) {
        if(this.state) {
            if (Keyboard.isKeyDown(KEY_G)) {
                Random r = new Random();
                mc.thePlayer.sendChatMessage(GGs[r.nextInt(GGs.length)]);
            }
        }
    }

}
