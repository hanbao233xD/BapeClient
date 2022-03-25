import java.io.IOException;

import javax.swing.JOptionPane;

public class SimInject {
    public static void inject() throws IOException {
        new mc.bape.Vapu.Client();
        System.out.println("Inject successful");
    }
}