package notepad;

import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class blocNotas extends JFrame implements ActionListener {

    JTextArea cajaTexto;
    JScrollPane barraTexto;
    JMenuBar mnuBarra;
    JMenu mnuArchivo, mnuEdición, mnuFormato, mnuVer, mnuAyuda;
    JMenuItem mnuNuevo, mnuAbrir, mnuGuardar, mnuGuardarComo, mnuSalir;
    JMenuItem mnuCortar, mnuCopiar, mnuPegar, mnuEliminar, mnuSeleccionarTodo;
    JMenuItem mnuFuente;
    JMenuItem mnuInstrucciones, mnuAcerca;

    String abrirGuardar;
    boolean comprobarAbrir = false;
    boolean comprobarGuardar = false;
    boolean salir = true;

    public blocNotas() {
        super();
        this.setLayout(null);
        setTitle("Bloc de Notas");

        cajaTexto = new JTextArea();
        cajaTexto.setLineWrap(true);
        cajaTexto.addKeyListener(new KeyHandler());
        barraTexto = new JScrollPane(cajaTexto);
        barraTexto.setBounds(0, 0, 980, 535);
        add(barraTexto);

        //Menu Archivo
        mnuNuevo = new JMenuItem("Nuevo");
        mnuNuevo.setMnemonic('N');
        mnuNuevo.addActionListener(this);

        mnuAbrir = new JMenuItem("Abrir");
        mnuAbrir.setMnemonic('A');
        mnuAbrir.addActionListener(this);

        mnuGuardar = new JMenuItem("Guardar");
        mnuGuardar.setMnemonic('G');
        mnuGuardar.addActionListener(this);

        mnuGuardarComo = new JMenuItem("Guardar como");
        mnuGuardarComo.setMnemonic('C');
        mnuGuardarComo.addActionListener(this);

        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);

        mnuArchivo = new JMenu("Archivo");
        mnuArchivo.setMnemonic('A');
        mnuArchivo.add(mnuNuevo);
        mnuArchivo.add(mnuAbrir);
        mnuArchivo.add(mnuGuardar);
        mnuArchivo.add(mnuGuardarComo);
        mnuArchivo.addSeparator();
        mnuArchivo.add(mnuSalir);

        //Menú Edición
        mnuCortar = new JMenuItem("Cortar");
        mnuCortar.setMnemonic('C');
        mnuCortar.addActionListener(this);

        mnuCopiar = new JMenuItem("Copiar");
        mnuCopiar.setMnemonic('O');
        mnuCopiar.addActionListener(this);

        mnuPegar = new JMenuItem("Pegar");
        mnuPegar.setMnemonic('P');
        mnuPegar.addActionListener(this);

        mnuEliminar = new JMenuItem("Eliminar");
        mnuEliminar.setMnemonic('E');
        mnuEliminar.addActionListener(this);

        mnuSeleccionarTodo = new JMenuItem("Seleccionar todo");
        mnuSeleccionarTodo.setMnemonic('T');
        mnuSeleccionarTodo.addActionListener(this);

        mnuEdición = new JMenu("Edición");
        mnuEdición.setMnemonic('E');
        mnuEdición.add(mnuCortar);
        mnuEdición.add(mnuCopiar);
        mnuEdición.add(mnuPegar);
        mnuEdición.add(mnuEliminar);
        mnuEdición.add(mnuSeleccionarTodo);

        //Menú Formato
        mnuFuente = new JMenuItem("Fuente");
        mnuFuente.setMnemonic('F');
        mnuFuente.addActionListener(this);

        mnuFormato = new JMenu("Formato");
        mnuFormato.setMnemonic('R');
        mnuFormato.add(mnuFuente);

        //Menú Ayuda
        mnuInstrucciones = new JMenuItem("Instrucciones");
        mnuInstrucciones.setMnemonic('I');
        mnuInstrucciones.addActionListener(this);

        mnuAcerca = new JMenuItem("Acerca de...");
        mnuAcerca.setMnemonic('D');
        mnuAcerca.addActionListener(this);

        mnuAyuda = new JMenu("Ayuda");
        mnuAyuda.setMnemonic('A');
        mnuAyuda.add(mnuInstrucciones);
        mnuAyuda.add(mnuAcerca);

        //Menu Principal
        mnuBarra = new JMenuBar();
        mnuBarra.add(mnuArchivo);
        mnuBarra.add(mnuEdición);
        mnuBarra.add(mnuFormato);
        mnuBarra.add(mnuAyuda);
        this.setJMenuBar(mnuBarra);

        addWindowListener(new CierreVentana());
        this.addKeyListener(new KeyHandler());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Menú Archivo
        if (e.getSource() == mnuGuardarComo) {
            guardarComo();
        }
        if (e.getSource() == mnuAbrir) {
            try {
                abrir();
            } catch (IOException ex) {
            }
        }
        if (e.getSource() == mnuGuardar && comprobarAbrir) {
            guardar();
        }
        if (e.getSource() == mnuGuardar && !comprobarAbrir) {
            guardarComo();
        }
        if (e.getSource() == mnuNuevo) {
            if (cajaTexto.getText().equals("") && !comprobarGuardar) {
                cajaTexto.setText("");
            } else {
                int res = JOptionPane.showConfirmDialog(null, "Guardar antes de crear un archivo nuevo?", "Nuevo archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.NO_OPTION) {
                    cajaTexto.setText("");
                }
                if (res == JOptionPane.YES_OPTION) {
                    if (salir) {
                        guardarComo();
                    } else {
                        guardar();
                    }
                    cajaTexto.setText("");
                }
            }
            comprobarAbrir = false;
            salir = true;
        }
        if (e.getSource() == mnuSalir) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        //Menú Edición
        if (e.getSource() == mnuEliminar) {
            cajaTexto.replaceSelection("");
        }
        if (e.getSource() == mnuCortar) {
            cajaTexto.cut();
        }
        if (e.getSource() == mnuCopiar) {
            cajaTexto.copy();
        }
        if (e.getSource() == mnuPegar) {
            cajaTexto.paste();
        }
        if (e.getSource() == mnuSeleccionarTodo) {
            cajaTexto.selectAll();
        }

        //Menú Formato
        if (e.getSource() == mnuFuente) {
            Fuente f = new Fuente(blocNotas.this);
            f.pack();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setVisible(true);
        }

        //Menú Ayuda
        if (e.getSource() == mnuInstrucciones) {
            JOptionPane.showMessageDialog(null, "EN PROCESO", "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == mnuAcerca) {
            JOptionPane.showMessageDialog(null, "BLOC DE NOTAS. @AnaBarja - 2018", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void guardarComo() {
        int respuesta;
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Texto", "txt");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(filtro);
        respuesta = fileChooser.showSaveDialog(null);
        try {
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                //try (BufferedWriter archivo = new BufferedWriter(new FileWriter(JFC))) {
                try (PrintWriter archivo = new PrintWriter(JFC)) {
                    cajaTexto.write(archivo);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Error.", JOptionPane.ERROR_MESSAGE);
        }
        comprobarGuardar = false;
        salir = false;
    }

    private void guardar() {
        //try (BufferedWriter f = new BufferedWriter(new FileWriter(abrirGuardar, false))) {
        try (PrintWriter f = new PrintWriter(new FileWriter(abrirGuardar, false))) {
            cajaTexto.write(f);
        } catch (IOException e) {
            System.err.println("Error de acceso al archivo");
        }
        comprobarGuardar = false;
        salir = false;
    }

    private void abrir() throws IOException {
        String texto = "";
        int respuesta;
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Texto", "txt");
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(filtro);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        respuesta = fc.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            abrirGuardar = fc.getSelectedFile().getPath();
            cajaTexto.setText(null);
            try (Scanner f = new Scanner(fc.getSelectedFile())) {
                while (f.hasNext()) {
                    texto += f.nextLine() + "\n";
                    cajaTexto.setText(texto);
                }
            } catch (Exception ea) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Compruebe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            comprobarAbrir = true;
            salir = false;
        }
    }

    class CierreVentana extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            if (cajaTexto.getText().equals("") && !comprobarGuardar) {
                cajaTexto.setText("");
                e.getWindow().dispose();
            } else if (comprobarGuardar) {
                int res = JOptionPane.showConfirmDialog(null, "Guardar antes de salir?", "Salir.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.NO_OPTION) {
                    e.getWindow().dispose();
                }
                if (res == JOptionPane.YES_OPTION) {
//                  if (abrirGuardar.endsWith(".txt")) { guardar(); }
                    if (salir) {
                        guardarComo();
                    } else {
                        guardar();
                    }
                }
            } else {
                e.getWindow().dispose();
            }
        }
    }

    public void cambiarFuente() {

    }

    class KeyHandler extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            comprobarGuardar = true;
        }
    }
}
