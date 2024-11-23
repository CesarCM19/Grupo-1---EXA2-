import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/github"; // Cambiar a la base de
                                                                               // datos del examen
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424"; // Cambiar a contraseña de la base de datos del examen

    public Login() {
        setTitle("Usuario");
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
        ImageIcon loginIcon = new ImageIcon("Imagenes/ingresarUsuario.png");
        ImageIcon registerIcon = new ImageIcon("Imagenes/agregarUsuario.png");
        JButton loginButton = new JButton(loginIcon);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);

        JButton registerSwitchButton = new JButton(registerIcon);
        registerSwitchButton.setFocusPainted(false);
        registerSwitchButton.setContentAreaFilled(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 200)); // Botones principales
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerSwitchButton);
        loginPanel.add(buttonPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {// Abrir la ventana de logueo con campos de usuario y contraseña
                openLoginWindow();
            }
        });

        registerSwitchButton.addActionListener(e -> openRegisterWindow());

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

    private void openLoginWindow() {
        // Crear una nueva ventana para ingresar usuario y contraseña
        JFrame loginWindow = new JFrame("Login de Usuario");
        loginWindow.setSize(400, 300);
        loginWindow.setLocationRelativeTo(null); // Centrar la ventana
        loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel con el mismo fondo
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Imagenes/Fondo.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);

        // Crear los campos de texto para el usuario y la contraseña
        JTextField userField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Crear un botón para iniciar sesión con una imagen
        ImageIcon loginIcon = new ImageIcon("Imagenes/ingresarUsu.png"); // Cargar la imagen para el botón
        JButton loginButton = new JButton(loginIcon);
        loginButton.setFocusPainted(false); // Quitar el borde cuando el botón está presionado
        loginButton.setContentAreaFilled(false); // Eliminar el fondo blanco del botón

        // Crear un layout para posicionar los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        // Acción del botón Ingresar
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                // Verificar las credenciales
                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(loginWindow, "Inicio de sesión exitoso");

                    // Cerrar la ventana de login
                    loginWindow.dispose();

                    // Crear y mostrar la ventana del menú
                    Menu menuWindow = new Menu();
                    menuWindow.setVisible(true); // Mostrar la ventana Menu
                    setVisible(false); // Cerrar la ventana de login de manera explícita
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Usuario o contraseña incorrectos");
                }
            }
        });

        // Añadir el panel al JFrame
        loginWindow.add(loginPanel);
        loginWindow.setVisible(true);
    }

    private void openRegisterWindow() {
        JFrame registerWindow = new JFrame("Registro de Nuevo Usuario");
        registerWindow.setSize(500, 600);
        registerWindow.setLocationRelativeTo(null);
        registerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel registerPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Imagenes/Fondo.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        registerPanel.setOpaque(false);

        // Crear campos de texto para los datos
        JTextField firstNameField = new JTextField(20);
        JTextField secondNameField = new JTextField(20);
        JTextField firstLastNameField = new JTextField(20);
        JTextField secondLastNameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Crear botones para registrar y volver
        JButton registerButton = new JButton(new ImageIcon("Imagenes/logUsu.png"));
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);

        JButton backButton = new JButton(new ImageIcon("Imagenes/Salir.png"));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        // Diseño del formulario
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Añadir etiquetas y campos al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(new JLabel("Primer Nombre:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(new JLabel("Segundo Nombre:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(secondNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(new JLabel("Primer Apellido:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(firstLastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(new JLabel("Segundo Apellido:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(secondLastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        registerPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        registerPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1;
        gbc.gridy = 6;
        registerPanel.add(buttonPanel, gbc);

        // Acción del botón Registrar
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String secondName = secondNameField.getText();
            String firstLastName = firstLastNameField.getText();
            String secondLastName = secondLastNameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validar campos vacíos
            if (firstName.isEmpty() || firstLastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(registerWindow, "Por favor, complete todos los campos obligatorios.");
                return;
            }

            // Intentar registrar al usuario
            if (registerUser(firstName, secondName, firstLastName, secondLastName, username, password)) {
                JOptionPane.showMessageDialog(registerWindow, "Registro exitoso");
                registerWindow.dispose();
            } else {
                JOptionPane.showMessageDialog(registerWindow, "Error en el registro: El usuario puede que ya exista.");
            }
        });

        // Acción del botón Volver
        backButton.addActionListener(e -> registerWindow.dispose());

        registerWindow.add(registerPanel);
        registerWindow.setVisible(true);
    }

    private boolean checkCredentials(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM usuarios WHERE User_Name = ? AND Password = ?";
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

    private boolean registerUser(String firstName, String secondName, String firstLastName,
            String secondLastName, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "{CALL registrarUsuario(?, ?, ?, ?, ?, ?)}";// Cambiar nombre segun el SP
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, firstLastName);
            statement.setString(4, secondLastName);
            statement.setString(5, username);
            statement.setString(6, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de error para duplicado
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
        });
    }
}
