package mc.bape.modules.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import mc.bape.Manager.FriendManager;
import mc.bape.modules.Module;
import mc.bape.Vapu.ModuleType;
import mc.bape.modules.combat.AntiBot;
import mc.bape.util.ColorUtil;
import mc.bape.value.Numbers;
import mc.bape.value.Option;
import org.lwjgl.input.Keyboard;

import java.awt.*;

import static mc.bape.modules.player.Teams.isOnSameTeam;
import static mc.bape.modules.render.StorageESP.dtl;

public class Tracers extends Module {
    private boolean states;
    private Numbers<Double> line = new Numbers<Double>("Tracersline", "Tracersline",0.5, 0.1, 5.0,1.0);
    private Option<Boolean> invisible = new Option<Boolean>("Invisible","Invisible", false);
    public Tracers() {
        super("Tracers", Keyboard.KEY_NONE, ModuleType.Other, "Tracers entitles location");
        this.addValues(this.line,this.invisible);
    }

    @Override
    public void enable() {
        this.states = Tracers.mc.gameSettings.viewBobbing;
        if (this.states) {
            Tracers.mc.gameSettings.viewBobbing = false;
        }
    }

    @SubscribeEvent
    public void update(TickEvent.PlayerTickEvent event) {
        if (Tracers.mc.gameSettings.viewBobbing) {
            Tracers.mc.gameSettings.viewBobbing = false;
        }
    }

    @Override
    public void disable() {
        Tracers.mc.gameSettings.viewBobbing = this.states;
    }


    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent ev) {
        for (final EntityPlayer TargetEntity : Tracers.mc.theWorld.playerEntities) {
            if (TargetEntity != Tracers.mc.thePlayer && !AntiBot.isServerBot(TargetEntity)) {
                if (!this.invisible.getValue() && TargetEntity.isInvisible()) {
                    return;
                }
                int rgb = 0;
                if(isOnSameTeam(TargetEntity)){
                    rgb = new Color(255, 255, 255).getRGB();
                } else if (FriendManager.isFriend(TargetEntity.getName())) {
                    rgb = new Color(34, 255, 0, 255).getRGB();
                } else {
                    rgb = new Color(255, 0, 0, 255).getRGB();
                }
                dtl(TargetEntity, rgb, this.line.getValue().floatValue());
            }
        }
    }

}