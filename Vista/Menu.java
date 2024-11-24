package Vista;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JPanel Principal;

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);

        // Crear un panel con fondo personalizado
        Principal = new JPanel() {
            private Image fondo = new ImageIcon(getClass().getResource("/Imagenes/Fondo.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        Principal.setToolTipText("Menu Principal");
        Principal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(Principal);
        Principal.setLayout(null);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        JLabel lblBienvenidosAlRegistro = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblBienvenidosAlRegistro.setBounds(80, 11, 350, 23);
        lblBienvenidosAlRegistro.setForeground(Color.WHITE);
        lblBienvenidosAlRegistro.setFont(new Font("Arial", Font.BOLD, 18));
        Principal.add(lblBienvenidosAlRegistro);

        // Botón "Ingresar"
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(204, 204, 255));
        btnIngresar.setForeground(Color.black);
        btnIngresar.setBounds(90, 65, 100, 30);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 12));
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Ingresar presionado");
            }
        });
        Principal.add(btnIngresar);

        // Botón "Actualizar"
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(204, 204, 255));
        btnActualizar.setForeground(Color.black);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setBounds(320, 65, 100, 30);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Actualizar presionado");
            }
        });
        Principal.add(btnActualizar);

        // Botón "Eliminar"
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(204, 204, 255));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setBounds(90, 149, 100, 30);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Eliminar presionado");
            }
        });
        Principal.add(btnEliminar);

        // Botón "Mostrar"
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.setBackground(new Color(204, 204, 255));
        btnMostrar.setForeground(Color.BLACK);
        btnMostrar.setBounds(320, 149, 100, 30);
        btnMostrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Mostrar presionado");
            }
        });
        Principal.add(btnMostrar);

        // Botón "Salir"
        JButton btnSalir = new JButton(new ImageIcon(getClass().getResource("/Imagenes/Salir.png")));
        btnSalir.setFocusPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setBounds(400, 300, 60, 60);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Gracias por preferirnos", "Cerrando sistema",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        });
        Principal.add(btnSalir);
    }
}
