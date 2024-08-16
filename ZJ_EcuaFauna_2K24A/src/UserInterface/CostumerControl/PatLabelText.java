package UserInterface.CostumerControl;

import javax.swing.*;

import UserInterface.ZJ_IAStyle;

import java.awt.*;

public class PatLabelText extends JPanel{
    private PatLabel    lblEtiqueta = new PatLabel();
    private PatTextBox  txtContenido= new PatTextBox();

    public PatLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        lblEtiqueta.setCustomizeComponent(  etiqueta, 
                                            ZJ_IAStyle.FONT_SMALL, 
                                            ZJ_IAStyle.COLOR_FONT_LIGHT, 
                                            ZJ_IAStyle.ALIGNMENT_LEFT); 
        //txtContenido.setBorder(txtContenido.createBorderLine());
        txtContenido.setBorderLine();
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }
}
