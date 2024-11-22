import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Usuario extends JFrame {
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    public Usuario() {
        setTitle("Login de Usuario");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Imagenes/Fondo.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new CardLayout());
        add(backgroundPanel);

        cardLayout = new CardLayout();
        backgroundPanel.setLayout(cardLayout);

        createLoginPanel(backgroundPanel);
        createRegisterPanel(backgroundPanel);

        cardLayout.show(backgroundPanel, "Login");
    }

    private void createLoginPanel(JPanel parentPanel) {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setOpaque(false);

        // Cargar las imágenes para los botones
        ImageIcon loginIcon = new ImageIcon("Imagenes/ingresarUsuario.png");
        ImageIcon registerIcon = new ImageIcon("Imagenes/agregarUsuario.png");

        // Crear los botones con las imágenes
        JButton loginButton = new JButton(loginIcon);
        loginButton.setFocusPainted(false); // Quitar el borde cuando el botón está presionado
        loginButton.setContentAreaFilled(false); // Eliminar el fondo blanco del botón

        JButton registerSwitchButton = new JButton(registerIcon);
        registerSwitchButton.setFocusPainted(false);
        registerSwitchButton.setContentAreaFilled(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 200)); // Botones principales
        buttonPanel.setOpaque(false); // Hacer el panel de botones transparente
        buttonPanel.add(loginButton);
        buttonPanel.add(registerSwitchButton);

        // Añadir el panel de botones al loginPanel
        loginPanel.add(buttonPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica de inicio de sesión
                // JOptionPane.showMessageDialog(null, "Iniciar sesión no implementado");
            }
        });

        registerSwitchButton.addActionListener(e -> cardLayout.show(parentPanel, "Register"));

        parentPanel.add(loginPanel, "Login");
    }

    private void createRegisterPanel(JPanel parentPanel) {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BorderLayout());
        registerPanel.setOpaque(false); // Hacer transparente el panel para que se vea el fondo

        // Cargar las imágenes para los botones
        ImageIcon registerIcon = new ImageIcon("Imagenes/logUsu.png");
        ImageIcon loginSwitchIcon = new ImageIcon("Imagenes/Salir.png");

        // Crear los botones con las imágenes
        JButton registerButton = new JButton(registerIcon);
        registerButton.setFocusPainted(false); // Quitar el borde cuando el botón está presionado
        registerButton.setContentAreaFilled(false); // Eliminar el fondo blanco del botón

        JButton loginSwitchButton = new JButton(loginSwitchIcon);
        loginSwitchButton.setFocusPainted(false);
        loginSwitchButton.setContentAreaFilled(false);

        // Crear un panel para los botones con FlowLayout centrado
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 400)); // Botones de Registro
        buttonPanel.setOpaque(false); // Hacer el panel de botones transparente
        buttonPanel.add(registerButton);
        buttonPanel.add(loginSwitchButton);

        // Añadir el panel de botones al registerPanel
        registerPanel.add(buttonPanel, BorderLayout.CENTER);

        loginSwitchButton.addActionListener(e -> cardLayout.show(parentPanel, "Login"));

        parentPanel.add(registerPanel, "Register");
    }

    private boolean checkCredentials(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM usuario WHERE User_Name = ? AND Password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean registerUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO usuario (User_Name, Password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario loginWindow = new Usuario();
            loginWindow.setVisible(true);
        });
    }
}
