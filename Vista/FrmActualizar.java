package Vista;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class FrmActualizar extends JFrame {
    private JTextField txtBuscarIdentificacion, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JButton btnBuscar, btnActualizar, btnRegresar;
    private Connection conexion;

    public FrmActualizar() {
        setTitle("Actualizar Usuario");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(247, 255, 255, 180));
        cuadro.setLayout(null);
        cuadro.setBounds(100, 60, 600, 350);

        ImageIcon fondo = new ImageIcon("Imagenes\\tecnologia.jpg");
        JLabel imagenFondo = new JLabel(fondo);
        imagenFondo.setSize(fondo.getIconWidth(), fondo.getIconHeight());

        JLabel etiquetaTitulo = new JLabel("Actualizar Usuario");
        etiquetaTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        etiquetaTitulo.setBounds(230, 20, 320, 20);
        etiquetaTitulo.setForeground(new Color(38, 104, 184));
        cuadro.add(etiquetaTitulo);

        JLabel lblBuscarIdentificacion = new JLabel("Identificación:");
        lblBuscarIdentificacion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblBuscarIdentificacion.setBounds(70, 50, 120, 30);
        cuadro.add(lblBuscarIdentificacion);

        txtBuscarIdentificacion = new JTextField();
        txtBuscarIdentificacion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtBuscarIdentificacion.setBounds(180, 50, 250, 30);
        cuadro.add(txtBuscarIdentificacion);

        btnBuscar = crearBoton("Buscar", 450, 50, "Buscar Usuario");
        cuadro.add(btnBuscar);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNombre1.setBounds(130, 100, 120, 30);
        cuadro.add(lblNombre1);

        txtNombre1 = new JTextField();
        txtNombre1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtNombre1.setBounds(260, 100, 200, 30);
        cuadro.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNombre2.setBounds(130, 140, 150, 30);
        cuadro.add(lblNombre2);

        txtNombre2 = new JTextField();
        txtNombre2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtNombre2.setBounds(260, 140, 200, 30);
        cuadro.add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblApellido1.setBounds(130, 180, 120, 30);
        cuadro.add(lblApellido1);

        txtApellido1 = new JTextField();
        txtApellido1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtApellido1.setBounds(260, 180, 200, 30);
        cuadro.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblApellido2.setBounds(130, 220, 150, 30);
        cuadro.add(lblApellido2);

        txtApellido2 = new JTextField();
        txtApellido2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtApellido2.setBounds(260, 220, 200, 30);
        cuadro.add(txtApellido2);

        // Botones de acción
        btnActualizar = crearBoton("Actualizar", 350, 280, "Actualizar datos");
        cuadro.add(btnActualizar);

        btnRegresar = crearBoton("Regresar", 100, 280, "Regresar al menú principal");
        cuadro.add(btnRegresar);

        btnBuscar.addActionListener(e -> buscarUsuario());

        btnActualizar.addActionListener(e -> actualizarUsuario());

        btnRegresar.addActionListener(e -> {
            dispose();
            Menu menu = new Menu();
            menu.setVisible(true);
        });

        add(cuadro);
        add(imagenFondo);
        setVisible(true);

        establecerConexion();
    }

    private JButton crearBoton(String texto, int x, int y, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        boton.setBounds(x, y, 150, 40);
        boton.setBackground(new Color(105, 149, 203));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true));
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);
        boton.setToolTipText(tooltip);
        return boton;
    }

    private void establecerConexion() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/github", "root", "root");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarUsuario() {
        String idUsuario = txtBuscarIdentificacion.getText();
        String query = "SELECT primer_nombre, segundo_nombre, primer_apellido, segundo_apellido FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(idUsuario));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                txtNombre1.setText(rs.getString("primer_nombre"));
                txtNombre2.setText(rs.getString("segundo_nombre"));
                txtApellido1.setText(rs.getString("primer_apellido"));
                txtApellido2.setText(rs.getString("segundo_apellido"));
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar usuario: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarUsuario() {
        String idUsuario = txtBuscarIdentificacion.getText();
        String primerNombre = txtNombre1.getText();
        String segundoNombre = txtNombre2.getText();
        String primerApellido = txtApellido1.getText();
        String segundoApellido = txtApellido2.getText();

        String query = "CALL ActualizarUsuario(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(idUsuario));
            stmt.setString(2, primerNombre);
            stmt.setString(3, segundoNombre);
            stmt.setString(4, primerApellido);
            stmt.setString(5, segundoApellido);
            stmt.execute();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtBuscarIdentificacion.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
    }
}

