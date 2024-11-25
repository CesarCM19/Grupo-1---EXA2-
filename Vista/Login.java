package Vista;

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

        // Crear los iconos
        ImageIcon loginIcon = new ImageIcon("Imagenes/ingresarUsuario.png");
        ImageIcon registerIcon = new ImageIcon("Imagenes/agregarUsuario.png");

        // Crear botones con texto y icono
        JButton loginButton = new JButton("Ingresar", loginIcon);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Coloca el texto debajo de la imagen
        loginButton.setHorizontalTextPosition(SwingConstants.CENTER); // Centra el texto horizontalmente

        JButton registerSwitchButton = new JButton("Registrar", registerIcon);
        registerSwitchButton.setFocusPainted(false);
        registerSwitchButton.setContentAreaFilled(false);
        registerSwitchButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Coloca el texto debajo de la imagen
        registerSwitchButton.setHorizontalTextPosition(SwingConstants.CENTER); // Centra el texto horizontalmente

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 200)); // Botones principales
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerSwitchButton);
        loginPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar acción para el botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginWindow();
            }
        });

        // Agregar acción para el botón de registro
        registerSwitchButton.addActionListener(e -> openRegisterWindow());
        parentPanel.add(loginPanel, "Login");
    }

    private void createRegisterPanel(JPanel parentPanel) {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BorderLayout());
        registerPanel.setOpaque(false);

        // Imágenes de botones
        ImageIcon registerIcon = new ImageIcon("Imagenes/logUsu.png");
        ImageIcon loginSwitchIcon = new ImageIcon("Imagenes/Salir.png");

        // Botones con imágenes
        JButton registerButton = new JButton(registerIcon);
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);

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
        ImageIcon loginIcon = new ImageIcon("Imagenes/ingresarUsu.png");
        JButton loginButton = new JButton("Iniciar sesión", loginIcon);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        loginButton.setHorizontalTextPosition(SwingConstants.CENTER);

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
                    loginWindow.dispose();
                    Menu menuWindow = new Menu();
                    menuWindow.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Usuario o contraseña incorrectos");
                }
            }
        });
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
        JButton registerButton = new JButton("Registrar", new ImageIcon("Imagenes/logUsu.png"));
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        registerButton.setHorizontalTextPosition(SwingConstants.CENTER);

        JButton backButton = new JButton("Volver", new ImageIcon("Imagenes/Salir.png"));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
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
                JOptionPane.showMessageDialog(registerWindow, "Error en el registro: El usuario puede que ya existe.");
            }
        });

        // Acción del botón Volver
        backButton.addActionListener(e -> registerWindow.dispose());

        registerWindow.add(registerPanel);
        registerWindow.setVisible(true);
    }

    private boolean checkCredentials(String username, String password) {
        boolean isValid = false;

        // Consulta SQL para validar el login y la clave
        String query = "SELECT * FROM usuarios WHERE login = ? AND clave = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)) {

            // Establecer parámetros en la consulta
            statement.setString(1, username);
            statement.setString(2, password);

            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si el ResultSet tiene resultados, las credenciales son válidas
                isValid = resultSet.next();
            }
        } catch (SQLException e) {
            // Manejo de errores
            e.printStackTrace(); // Para depuración
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
        }

        return isValid;
    }

    private boolean registerUser(String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido, String usuario, String clave) {
        try (Connection conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Verificar si el usuario ya existe
            String consultaVerificar = "SELECT COUNT(*) FROM usuarios WHERE primer_nombre = ? AND segundo_nombre = ? AND primer_apellido = ? AND segundo_apellido = ? AND login = ?";
            PreparedStatement sentenciaVerificar = conexion.prepareStatement(consultaVerificar);
            sentenciaVerificar.setString(1, primerNombre);
            sentenciaVerificar.setString(2, segundoNombre);
            sentenciaVerificar.setString(3, primerApellido);
            sentenciaVerificar.setString(4, segundoApellido);
            sentenciaVerificar.setString(5, usuario);

            ResultSet resultado = sentenciaVerificar.executeQuery();
            if (resultado.next() && resultado.getInt(1) > 0) {
                return false;
            }

            // Insertar nuevo usuario llamando al procedimiento almacenado
            String consultaInsertar = "{CALL InsertarUsuario(?, ?, ?, ?, ?, ?)}";
            PreparedStatement sentenciaInsertar = conexion.prepareStatement(consultaInsertar);
            sentenciaInsertar.setString(1, primerNombre);
            sentenciaInsertar.setString(2, segundoNombre);
            sentenciaInsertar.setString(3, primerApellido);
            sentenciaInsertar.setString(4, segundoApellido);
            sentenciaInsertar.setString(5, usuario);
            sentenciaInsertar.setString(6, clave);

            // Ejecutar el procedimiento almacenado
            sentenciaInsertar.executeUpdate();
            return true;

        } catch (SQLException e) {
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
