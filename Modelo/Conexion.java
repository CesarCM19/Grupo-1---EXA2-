package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
          // Datos de conexión a la base de datos

    private static final String URL = "jdbc:mysql://localhost:3306/github";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

        // Método para establecer la conexión a la base de datos

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

}
