package UserInterface.Form;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import UserInterface.ZJ_IAStyle;

public class ZJ_SplashScreenForm {
    private static JFrame       frmSplash;
    private static JProgressBar prbLoading;
    private static ImageIcon    icoImagen;
    private static JLabel       lblSplash;

    public static void show() {
        icoImagen = new ImageIcon(ZJ_IAStyle.URL__SPLASH);
        lblSplash = new JLabel(icoImagen);
        prbLoading = new JProgressBar(0, 100);

        prbLoading.setStringPainted(true);

        frmSplash = new JFrame();
        frmSplash.setUndecorated(true);
        frmSplash.getContentPane().add(lblSplash, BorderLayout.CENTER);
        frmSplash.add(prbLoading, BorderLayout.SOUTH);
        frmSplash.setSize(icoImagen.getIconWidth(),icoImagen.getIconHeight());
        frmSplash.setLocationRelativeTo(null);

        frmSplash.setVisible(true);
        for(int time = 0; time<=100;time++){
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            prbLoading.setValue(time);
        }
        frmSplash.setVisible(false);
    }
}
