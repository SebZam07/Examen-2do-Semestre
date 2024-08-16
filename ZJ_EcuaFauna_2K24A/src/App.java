import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;

import UserInterface.Form.ZJ_SplashScreenForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import UserInterface.Form.MainForm;


public class App {
    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        FlatLightLaf.supportsNativeWindowDecorations();
        try {
                UIManager.setLookAndFeel(new FlatAtomOneDarkIJTheme());
            } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ZJ_SplashScreenForm.show();
        MainForm frmMain = new MainForm("ZJ_EcuaFauna");
    }
}