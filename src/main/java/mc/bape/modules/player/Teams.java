package mc.bape.modules.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import mc.bape.Manager.ModuleManager;
import mc.bape.modules.Module;
import mc.bape.Vapu.ModuleType;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

public class Teams extends Module {
    public Teams() {
        super("Teams", Keyboard.KEY_NONE, ModuleType.Other,"Not attack player you team");
    }

    public static boolean isOnSameTeam(Entity entity) {
        //��Ҫɾ��null���
        //�����
        if (!Objects.requireNonNull(ModuleManager.getModule("Teams").getState()))
            return false;
        if (mc.thePlayer == null)
            return false;
        EntityPlayer entity1 = (EntityPlayer) entity;
        if (mc.thePlayer.getTeam() != null && entity1.getTeam() != null &&
                mc.thePlayer.getTeam().isSameTeam(entity1.getTeam())
        ) {
            return true;
        }
        if (mc.thePlayer.getDisplayName() != null && entity1.getDisplayName() != null) {
            String targetName = entity1.getDisplayName().getFormattedText().replace("��r", "");
            String clientName = mc.thePlayer.getDisplayName().getFormattedText().replace("��r", "");
            if (targetName.startsWith("T") && clientName.startsWith("T")) {
                return targetName.charAt(1) == clientName.charAt(1);
            }
        }
        if (mc.thePlayer.getDisplayName() != null && entity1.getDisplayName() != null) {
            String targetName = entity1.getDisplayName().getFormattedText().replace("��r", "");
            String clientName = mc.thePlayer.getDisplayName().getFormattedText().replace("��r", "");
            return targetName.startsWith("��" + clientName.charAt(1));
        }
        return false;
    }
}