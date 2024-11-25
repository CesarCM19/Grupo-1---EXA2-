package Vista;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class FrmEliminar extends JFrame {
    private JTextField txtBuscarIdentificacion;
    private JButton btnEliminar, btnRegresar;

    public FrmEliminar() {
        setTitle("Eliminar Usuario");
        setSize(800, 500); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(247, 255, 255, 180));
        cuadro.setLayout(null);
        cuadro.setBounds(100, 60, 600, 200);

        ImageIcon fondo = new ImageIcon("Imagenes\\tecnologia.jpg");
        JLabel imagenFondo = new JLabel(fondo);
        imagenFondo.setSize(fondo.getIconWidth(), fondo.getIconHeight());

        JLabel etiquetaTitulo = new JLabel("Eliminar Usuario");
        etiquetaTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        etiquetaTitulo.setBounds(230, 20, 320, 20);
        etiquetaTitulo.setForeground(new Color(184, 38, 38));
        cuadro.add(etiquetaTitulo);

        JLabel lblBuscarIdentificacion = new JLabel("Identificación del Usuario:");
        lblBuscarIdentificacion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblBuscarIdentificacion.setBounds(70, 80, 180, 30);
        cuadro.add(lblBuscarIdentificacion);

        txtBuscarIdentificacion = new JTextField();
        txtBuscarIdentificacion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtBuscarIdentificacion.setBounds(250, 80, 250, 30);
        cuadro.add(txtBuscarIdentificacion);

        btnEliminar = crearBoton("Eliminar", 400, 150, "Eliminar usuario por identificación");
        cuadro.add(btnEliminar);

        btnRegresar = crearBoton("Regresar", 100, 150, "Regresar al menú principal");
        cuadro.add(btnRegresar);

        // Acciones de los botones
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        add(cuadro);
        add(imagenFondo);
        setVisible(true);
    }

    private JButton crearBoton(String texto, int x, int y, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        boton.setBounds(x, y, 150, 40);
        boton.setBackground(new Color(203, 105, 105));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(140, 99, 99), 1, true));
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);
        boton.setToolTipText(tooltip);
        return boton;
    }

    private void eliminarUsuario() {
        String idUsuario = txtBuscarIdentificacion.getText();

        if (idUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una identificación.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar al usuario con ID: " + idUsuario + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/github", "root", "root")) {
                CallableStatement stmt = conn.prepareCall("{CALL EliminarUsuario(?)}");
                stmt.setInt(1, Integer.parseInt(idUsuario));
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtBuscarIdentificacion.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un usuario con esa identificación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La identificación debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
