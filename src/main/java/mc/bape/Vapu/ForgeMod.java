package mc.bape.Vapu;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@Mod(modid="Bape", name="Bape", version="2.04", acceptedMinecraftVersions="[1.8.9]")
public class ForgeMod {
	@SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException {
        new Client();
    }
	
	@SideOnly(Side.SERVER)
    @Mod.EventHandler
    public void initServer(FMLPreInitializationEvent event) {
        System.out.println("Fuck you bro");
    }
}
