package notepad;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        blocNotas aplicacion = new blocNotas();
        aplicacion.setLocationRelativeTo(null);
        aplicacion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        aplicacion.setSize(1000, 600);
        aplicacion.setVisible(true);
    }
}
