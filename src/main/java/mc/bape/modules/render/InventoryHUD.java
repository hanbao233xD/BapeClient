package mc.bape.modules.render;

import mc.bape.Gui.font.FontLoaders;
import mc.bape.Vapu.ModuleType;
import mc.bape.modules.Module;
import mc.bape.util.Colors;
import mc.bape.value.Mode;
import mc.bape.value.Numbers;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static mc.bape.util.utils.RenderUtil.*;

public class InventoryHUD extends Module {

    private Numbers<Integer> redValue = new Numbers<Integer>("Red", "Red",255, 1, 255,1);
    private Numbers<Integer> greenValue = new Numbers<Integer>("Green", "Green",255, 1, 255,1);
    private Numbers<Integer> blueValue = new Numbers<Integer>("Blue", "Blue",255, 1, 255,1);
    private Numbers<Integer> RoundedInsideC = new Numbers<Integer>("RoundedInsideC", "RoundedInsideC",255, 0, 255,1);
    private Mode<Enum> mode = new Mode("RenderMode", "RenderMode", (Enum[]) InventoryHUD.RenderMode.values(), (Enum) RenderMode.Rounded);
    private float inventoryRows = 0;
    private IInventory lowerInv;

    public InventoryHUD() {
        super("Inventory HUD", Keyboard.KEY_NONE, ModuleType.Render, "Show your inventory on hud");
        this.addValues(mode,redValue,greenValue,blueValue);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        FontLoaders.F18.drawStringWithShadow("Inventory HUD", 10, 21, new Color(255,255,255).getRGB());
        if (mode.getValue() == RenderMode.Rounded) {
            drawRoundedRect(0f, this.inventoryRows * 18f + 17f, 176f, 96f, new Color(redValue.getValue(), greenValue.getValue(), blueValue.getValue(), 255).getRGB(), RoundedInsideC.getValue());
        }
        if (mode.getValue() == RenderMode.Skeet) {
            autoSkeet(0.0, (double) (this.inventoryRows * 18F + 17F), 176.0, 96.0,1.0);
        }
        if (mode.getValue() == RenderMode.Normal) {
            drawRect(200,200,200,100, new Color(255,255,255,100).getRGB());
        }
        if (lowerInv != null) {
            this.inventoryRows = lowerInv.getSizeInventory();
        }
        renderInventory1(mc.thePlayer);
        renderInventory2(mc.thePlayer);
        renderInventory3(mc.thePlayer);
    }

    private void renderInventory1(EntityPlayer player) {
        ItemStack armourStack = null;
        ItemStack[] renderStack = player.inventory.mainInventory;
        int xOffset = 8;
        renderStack = mc.thePlayer.inventory.mainInventory;
        for (int i = 9; 17 >= i; i++) {
            armourStack = renderStack[i];
            if (armourStack == null) continue;
            this.renderItemStack(armourStack, xOffset, 30);
            xOffset += 18;
        }
    }

    private void renderInventory2(EntityPlayer player) {
        ItemStack armourStack = null;
        ItemStack[] renderStack = player.inventory.mainInventory;
        int xOffset = 8;
        renderStack = mc.thePlayer.inventory.mainInventory;
        for (int i = 18; 26 >= i; i++) {
            armourStack = renderStack[i];
            if (armourStack == null) continue;
            this.renderItemStack(armourStack, xOffset, 48);
            xOffset += 18;
        }
    }

    private void renderInventory3(EntityPlayer player) {
        ItemStack armourStack = null;
        ItemStack[] renderStack = player.inventory.mainInventory;
        int xOffset = 8;
        renderStack = mc.thePlayer.inventory.mainInventory;
        for (int i = 27; 35 >= i; i++) {
            armourStack = renderStack[i];
            if (armourStack == null) continue;
            this.renderItemStack(armourStack, xOffset, 66);
            xOffset += 18;
        }
    }

    private void renderItemStack(ItemStack stack, int x, int y) {
        GlStateManager.pushMatrix();
        mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        GlStateManager.popMatrix();
    }


    private void autoSkeet(double x,double y, double x1, double y1, double size) {
        rectangleBordered(x, y, x1 + size, y1 + size, 0.5, Colors.getColor(90), Colors.getColor(0));
        rectangleBordered(x + 1.0, y + 1.0, x1 + size - 1.0, y1 + size - 1.0, 1.0, Colors.getColor(90), Colors.getColor(61));
        rectangleBordered(x + 2.5, y + 2.5, x1 + size - 2.5, y1 + size - 2.5, 0.5, Colors.getColor(61), Colors.getColor(0));
    }

    private void rectangleBordered (double x,double y,double x1, double y1, double width, int internalColor, int borderColor) {
        rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void rectangle(double x,double y, double x2, double y2, int color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        glColor(color);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    static enum RenderMode {
        Rounded, Skeet, Normal
    }

}