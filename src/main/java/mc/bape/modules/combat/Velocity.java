package mc.bape.modules.combat;

import mc.bape.Vapu.ModuleType;
import mc.bape.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", Keyboard.KEY_NONE, ModuleType.Player,"This Module is not work LOL");
        Chinese="反击退";
    }

    @SubscribeEvent
    public void c(LivingEvent.LivingUpdateEvent d) {
        if (mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime) {
            // && mc.thePlayer.maxHurtTime > 0
            final double ch = Math.random();
            final EntityPlayerSP thePlayer = Velocity.mc.thePlayer;
            thePlayer.motionX *= 96.0 / 100.0;
            final EntityPlayerSP thePlayer2 = Velocity.mc.thePlayer;
            thePlayer2.motionY *= 1.0;
            final EntityPlayerSP thePlayer3 = Velocity.mc.thePlayer;
            thePlayer3.motionZ *= 1.0;
        }
    }
}
