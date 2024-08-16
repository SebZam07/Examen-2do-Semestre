package UserInterface.Form;

import javax.swing.*;

import Business.BusinessLogic.ZJ_HormigaBL;
import DataAcces.DTO.ZJHormiga_DTO;
import UserInterface.ZJ_IAStyle;
import UserInterface.CostumerControl.PatButton;
import UserInterface.CostumerControl.PatLabel;
import UserInterface.CostumerControl.PatTextBox;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SexoPanel  extends JPanel implements ActionListener {
    private Integer rowNum = 0, idRowMaxSexo = 0;
    private ZJ_HormigaBL zjHormigaBL = new ZJ_HormigaBL();
    private ZJHormiga_DTO zjHormigaDAO = null;
    private String[] zjItemsGenoAlimento = {"X", "XX", "XY"};
    private String[] zjItemsIngestaNativa = {"Hervivoro", "Carnivoro", "Omnivoro", "Insectivoro"};
    JComboBox<String> zjComboBoxGenoAlimento = new JComboBox<>(zjItemsGenoAlimento);
    JComboBox<String> zjComboBoxIngestaNativa = new JComboBox<>(zjItemsIngestaNativa);
    
    /************************
    * FormDesing : pat_mic
    ************************/ 
    private PatLabel 
        lblTitulo   = new PatLabel("SEXO"),
        lblrowNum   = new PatLabel(" Codigo:      "),
        lblNombre   = new PatLabel("*Descripción: "),
        lblTotalReg = new PatLabel(" 0 de 0 "),
        lblGenoAlimento  = new PatLabel("Geno - Alimento"),
        lblIngestaNativa = new PatLabel("Ingesta Nativa");
    private PatTextBox 
        txtrowNum   = new PatTextBox(),
        txtNombre   = new PatTextBox();
    private PatButton 
        btnPageIni  = new PatButton(" |< "),
        btnPageAnt  = new PatButton(" << "),
        btnPageSig  = new PatButton(" >> "),
        btnPageFin  = new PatButton(" >| "),
    
        btnRowIni   = new PatButton(" |< "),
        btnRowAnt   = new PatButton(" << "),
        btnRowSig   = new PatButton(" >> "),
        btnRowFin   = new PatButton(" >| "),
    
        btnCrear    = new PatButton("Crear Hormiga Larva"),
        btnAlimeGeno= new PatButton("Crear Hormiga Larva"),
        btnAlimeInge= new PatButton("Crear Hormiga Larva"),
    
        btnGuardar  = new PatButton("Guardar"),
        //btnCancelar = new PatButton("Cancelar"),
        btnEliminar = new PatButton("Eliminar");
    private JPanel 
        pnlTabla    = new JPanel(),
        pnlBtnRow   = new JPanel(new FlowLayout()),
        pnlBtnCrear = new JPanel(new FlowLayout()),
        pnlBtnGenoA = new JPanel(new FlowLayout()),
        pnlBtnInges = new JPanel(new FlowLayout()),
        //pnlBtnPage  = new JPanel(new FlowLayout()),
        pnlBtnCRUD  = new JPanel(new FlowLayout());
    
    public SexoPanel() {
        try {
            customizeComponent();
            loadRow();
            showRow();
            showTable();
    
            btnRowIni.addActionListener(this);
            btnRowAnt.addActionListener(this);
            btnRowSig.addActionListener(this);
            btnRowFin.addActionListener(this);
            
            btnCrear.addActionListener(     e -> {
                try {
                    btnNuevoClick();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            btnGuardar.addActionListener(   e -> btnGuardarClick());
            btnEliminar.addActionListener(  e -> btnEliminarClick());
            btnAlimeGeno.addActionListener(  e -> btnUpdateAlimeGeno());
            btnAlimeInge.addActionListener(  e -> btnUpdateAlimeInge());
        } catch (Exception e) {
            ZJ_IAStyle.showMsg(e.getMessage());
        }
    }
    
    private void btnUpdateAlimeInge() {
        // try {
        //     if (ZJ_IAStyle.showConfirmYesNo("¿Seguro que desea ACTUALIZAR ?")){
        //         zjHormigaDAO.setZjIngestaNativa(zjObtenerDatoComboBox2(zjComboBoxIngestaNativa));
        //         if(zjHormigaBL.zjUpdateIngAliment(zjHormigaDAO)){
        //             ZJ_IAStyle.showMsg("Se actualizó la alimentación genética de la hormiga");
        //         }
        //         loadRow();
        //         showRow();
        //         showTable();
        //     }
        // } catch (Exception e) {
        //     ZJ_IAStyle.showMsgError(e.getMessage());
        // }
        try {
            // Verifica si el caso de estudio es el 7 (C)
            if (ZJ_IAStyle.showConfirmYesNo("¿Seguro que desea ACTUALIZAR ?")) {
                if (zjHormigaDAO != null && zjHormigaDAO.getZjTipoHormiga().equals("Zangano") &&
                    zjComboBoxGenoAlimento.getSelectedItem().equals("XX") &&
                    zjComboBoxIngestaNativa.getSelectedItem().equals("Omnivoro")) {
    
                    // Cambia el GenoAlimento y actualiza en la base de datos
                    zjHormigaDAO.setZjGenoAlimento(zjObtenerDatoComboBox1(zjComboBoxGenoAlimento));
                    
                    if (zjHormigaBL.zjUpdateGenAliment(zjHormigaDAO)) {
                        ZJ_IAStyle.showMsg("Se actualizó la alimentación genética de la hormiga");
    
                        // Actualiza la grilla para reflejar el cambio
                        loadRow();
                        showRow();
                        showTable();
                    }
                } else {
                    ZJ_IAStyle.showMsg("Los datos seleccionados no cumplen con el caso de estudio 7.");
                }
            }
        } catch (Exception e) {
            ZJ_IAStyle.showMsgError(e.getMessage());
        }
    }
    
    private Integer zjObtenerDatoComboBox2(JComboBox<String> zjComboBoxDos) {
        if(zjComboBoxDos.getSelectedItem().equals("Carnivoro")){
            return 4;
        } else if(zjComboBoxDos.getSelectedItem().equals("Hervivoro")){
            return 5;
        } else if(zjComboBoxDos.getSelectedItem().equals("Omnivoro")){
            return 6;
        } else if(zjComboBoxDos.getSelectedItem().equals("Insectivoro")){
            return 7;
        }
        return -1;
    }
    
    private void btnUpdateAlimeGeno() {
        try {
            if (ZJ_IAStyle.showConfirmYesNo("¿Seguro que desea ACTUALIZAR ?")){
                zjHormigaDAO.setZjGenoAlimento(zjObtenerDatoComboBox1(zjComboBoxGenoAlimento));
                if(zjHormigaBL.zjUpdateGenAliment(zjHormigaDAO)){
                    ZJ_IAStyle.showMsg("Se actualizó la alimentación genética de la hormiga");
                }
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            ZJ_IAStyle.showMsgError(e.getMessage());
        }
    }
    
    private Integer zjObtenerDatoComboBox1(JComboBox<String> zjComboBoxUno) {
        if(zjComboBoxUno.getSelectedItem().equals("X")){
            return 8;
        } else if(zjComboBoxUno.getSelectedItem().equals("XX")){
            return 9;
        } else if(zjComboBoxUno.getSelectedItem().equals("XY")){
            return 10;
        }
        return -1;
    }
    
    private void loadRow()  {
        try {
            rowNum = 1;
            zjHormigaDAO = zjHormigaBL.zjGetBy(rowNum);
            idRowMaxSexo = zjHormigaBL.zjGetRowCount();
        } catch (Exception e) {
            ZJ_IAStyle.showMsg(e.getMessage());
        }
    }
    
    ///iMPORTANTE
    private void showRow() {
        boolean zjHormigaNull = (zjHormigaDAO == null);
        txtrowNum.setText((zjHormigaNull) ? " " : zjHormigaDAO.getZjIDHormiga().toString());
        txtNombre.setText((zjHormigaNull) ? " " : zjHormigaDAO.getZjTipoHormiga());
        lblTotalReg.setText(rowNum.toString() + " de " + idRowMaxSexo.toString());
    }
    
    private void btnNuevoClick() throws Exception{
        // zjHormigaBL.zjAdd();
        // //revisar sigulinea
        // zjHormigaDAO = null;
        // loadRow();
        // showRow();
        // showTable();
        int lastRowNum = zjHormigaBL.zjGetRowCount();
        int newRowNum = lastRowNum + 1;
    
        int response = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de crear una Hormiga larva?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION
        );
    
        if (response == JOptionPane.YES_OPTION) {
            ZJHormiga_DTO newHormiga = new ZJHormiga_DTO();
            newHormiga.setZjIDHormiga(newRowNum);
            newHormiga.setZjTipoHormiga("Larva");
            newHormiga.setZjNombreProvincia(TOOL_TIP_TEXT_KEY);;
            newHormiga.setZjNombreSexo("Asexual");
            newHormiga.setZjEstadoCondición("Viva");
            newHormiga.setZjNombreGenoAlimento("X"); // Según caso C
            newHormiga.setZjIngestaNativa(idRowMaxSexo);; // Conversión a Integer
    
            zjHormigaBL.zjAdd();
            loadRow();
            showRow();
            showTable();
        }
    }
    
    private void btnGuardarClick() {
        boolean zjHormigaNull = (zjHormigaDAO == null);
        // String buttonText = ((JButton) e.getSource()).getText();
        try {
            if (ZJ_IAStyle.showConfirmYesNo("¿Seguro que desea " + ((zjHormigaNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))){
                ZJ_IAStyle.showMsg("Guardado Correctamente");
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            ZJ_IAStyle.showMsgError(e.getMessage());
        }
    }
    
    private void btnEliminarClick() {
        try {
            if (ZJ_IAStyle.showConfirmYesNo("Seguro que desea Eliminar?")) {
    
                if (!zjHormigaBL.zjDelete(zjHormigaDAO.getZjIDHormiga()))
                    throw new Exception("Error al eliminar...!");
    
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            ZJ_IAStyle.showMsgError(e.getMessage());
        }
    }
    
    private void btnCancelarClick() {
        try {
            if(zjHormigaDAO == null)
                loadRow();
            showRow();
        } catch (Exception e) {}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            rowNum = 1;
        if (e.getSource() == btnRowAnt && (rowNum > 1))
            rowNum--;
        if (e.getSource() == btnRowSig && (rowNum < idRowMaxSexo))
            rowNum++;
        if (e.getSource() == btnRowFin)
            rowNum = idRowMaxSexo;
        try {
            zjHormigaDAO = zjHormigaBL.zjGetBy(rowNum);  
            showRow(); 
        } catch (Exception ex) {}
    }
    
    private void showTable() throws Exception {
        String[] header = {"RN","ID","Tipo", "Geno Alimento", "Ingesta Nativa","Sexo","Localidad","Estado"};
        Object[][] data = new Object[zjHormigaBL.getAll().size()][8];
        int index = 0;
        for (ZJHormiga_DTO s : zjHormigaBL.getAll()) {
            data[index][0] = s.getZjRowNum();
            data[index][1] = s.getZjIDHormiga();
            data[index][2] = s.getZjTipoHormiga();
            data[index][3] = s.getZjNombreGenoAlimento();
            data[index][4] = s.getZjNombreIngestaNativa();
            data[index][5] = s.getZjNombreSexo();
            data[index][6] = s.getZjNombreProvincia();
            data[index][7] = s.getZjEstadoCondición();
            index++;
        }
        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setPreferredScrollableViewportSize(new Dimension(450, 150));
        table.setFillsViewportHeight(true);

        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strrowNum = table.getModel().getValueAt(row, 0).toString();
                    rowNum = Integer.parseInt(strrowNum);
                    try {
                        zjHormigaDAO = zjHormigaBL.zjGetBy(rowNum);
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strrowNum);
                }
            }
        });
    }

    public void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //txtrowNum.setEnabled(false);
        txtrowNum.setBorderLine();
        txtNombre.setBorderLine();

        //pnlBtnPage.add(btnPageIni);
        //pnlBtnPage.add(btnPageAnt);
        //pnlBtnPage.add(new PatLabel(" Page:( "));
        //pnlBtnPage.add(lblTotalReg); //cambiar
        //pnlBtnPage.add(new PatLabel(" ) "));
        //pnlBtnPage.add(btnPageSig);
        //pnlBtnPage.add(btnPageFin);

        pnlBtnRow.add(btnRowIni);
        pnlBtnRow.add(btnRowAnt);
        pnlBtnRow.add(lblTotalReg);
        pnlBtnRow.add(btnRowSig);
        pnlBtnRow.add(btnRowFin);

        pnlBtnCrear.add(btnCrear);
        pnlBtnGenoA.add(btnAlimeGeno);
        pnlBtnInges.add(btnAlimeInge);


        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.add(btnGuardar);
        //pnlBtnCRUD.add(btnNuevo);
        //pnlBtnCRUD.add(btnCancelar);
        pnlBtnCRUD.setBorder(ZJ_IAStyle.createBorderRect());

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);
//-------------------------------------------------------------------------

        //gbc.gridy = 6;
        //gbc.gridx = 0;
        //gbc.gridwidth = 1;
        //add(new JLabel("■ Sección de datos: "), gbc);
        //gbc.gridy = 6;
        //gbc.gridx = 1;
        //add(pnlBtnPage, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.ipady = 150;
        gbc.ipadx = 450;
        pnlTabla.add(new Label("Loading data..."));
        add(pnlTabla, gbc);
//-------------------------------------------------------------------------
        gbc.ipady = 1;
        gbc.ipadx = 1;

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(50, 0, 0, 0);  // Ajusta el valor superior a 50
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createRigidArea(new Dimension(0, 0)), gbc);

        gbc.insets = new Insets(10, 0, 0, 0);
//-------------------------------------------------------------------------

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de registro: "), gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        add(pnlBtnRow, gbc);
        //---------------------------------------
        
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Hormiguero Virtual: "), gbc);
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(pnlBtnCrear, gbc);
//******************************************************************* */
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(lblrowNum, gbc);
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(txtrowNum, gbc);
//------------------------------------------------------------------
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(lblNombre, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(txtNombre, gbc);

        
        gbc.gridy = 6;
        gbc.gridx = 0;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lblGenoAlimento, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        //gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(zjComboBoxGenoAlimento, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        //gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(pnlBtnGenoA, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lblIngestaNativa, gbc);
        gbc.gridy =10;
        gbc.gridx = 0;
        //gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(zjComboBoxIngestaNativa, gbc);
        gbc.gridy = 11;
        gbc.gridx = 0;
        //gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(pnlBtnInges, gbc);

        gbc.gridy = 12;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnCRUD, gbc);
    }
}   