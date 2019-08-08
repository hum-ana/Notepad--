package notepad;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class Fuente extends JDialog implements ItemListener, ActionListener {

    JComboBox<String> cmbFuente;
    JComboBox<String> cmbEstilo;
    JComboBox<String> cmbTamaño;
    String[] fuentes = {"Arial", "Comic Sans", "Consolas", "Georgia", "Verdana"};
    String[] estilos = {"Normal", "Cursiva", "Negrita", "Cursiva negrita"};
    String[] tamaño = {"Grande", "Pequeña"};

    Fuente(blocNotas f) {
        super(f, true);
        setLayout(new FlowLayout());
        setTitle("Fuente");

        cmbFuente = new JComboBox<String>(fuentes);
        cmbFuente.setMaximumRowCount(5);
        cmbFuente.addItemListener(this);
        add(cmbFuente);

        cmbEstilo = new JComboBox<String>(estilos);
        cmbEstilo.setMaximumRowCount(5);
        cmbEstilo.addItemListener(this);
        add(cmbEstilo);

        cmbTamaño = new JComboBox<String>(tamaño);
        cmbTamaño.setMaximumRowCount(5);
        cmbTamaño.addItemListener(this);
        add(cmbTamaño);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        blocNotas f = (blocNotas) this.getOwner();
        if (e.getSource() == cmbFuente) {
            cmbFuente.getSelectedIndex();
            String x = fuentes[cmbFuente.getSelectedIndex()];
//            f.cambiarFuente = x;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        blocNotas f = (blocNotas) this.getOwner();
        if (cmbFuente.getSelectedIndex() == 0) {

        }
        if (cmbFuente.getSelectedIndex() == 1) {

        }
        if (cmbFuente.getSelectedIndex() == 2) {

        }
        if (cmbFuente.getSelectedIndex() == 3) {

        }
        if (cmbFuente.getSelectedIndex() == 4) {

        }

        if (cmbEstilo.getSelectedIndex() == 0) {

        }
        if (cmbEstilo.getSelectedIndex() == 1) {

        }
        if (cmbEstilo.getSelectedIndex() == 2) {

        }
        if (cmbEstilo.getSelectedIndex() == 3) {

        }

        if (cmbTamaño.getSelectedIndex() == 0) {

        }
        if (cmbTamaño.getSelectedIndex() == 1) {

        }
    }
}
