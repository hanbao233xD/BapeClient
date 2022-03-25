package mc.bape.modules.render;

import mc.bape.Vapu.ModuleType;
import mc.bape.modules.Module;
import mc.bape.modules.combat.AntiBot;
import mc.bape.util.RenderHelper;
import mc.bape.util.utils.ColorUtil;
import mc.bape.value.Option;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ESP extends Module {

    private final Option<Boolean> invisible = new Option<>("Invisible", "invisible", false);
    private final Option<Boolean> redOnDalage = new Option<>("RedOnDalage", "redOnDalage", true);

    public ESP() {
        super("ESP", Keyboard.KEY_NONE, ModuleType.Player,"Draw boxes for other player");
        this.addValues(this.invisible,this.redOnDalage);
        Chinese="ExampleModule";
    }

    @SubscribeEvent
    public void onTick(final RenderWorldLastEvent event) {
        for (final EntityPlayer entity : mc.theWorld.playerEntities) {
            if (entity != mc.thePlayer && (!AntiBot.isServerBot(entity))) {
                if (!this.invisible.getValue() && entity.isInvisible()) {
                    return;
                }
                int rgb;
                rgb = ColorUtil.getRainbow().getRGB();
                RenderHelper.drawESP(entity, rgb, this.redOnDalage.getValue(), (int) 2.0);
            }
        }
    }
}
