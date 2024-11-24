package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MostrarDAO {
        // Método para obtener los usuarios desde la base de datos
        public List<Object[]> obtenerUsuarios() {
            List<Object[]> usuarios = new ArrayList<>();
            String sql = "Select * from usuarios"; // Consulta SP para obtener los usuarios
    
            try (Connection conn = Conexion.conectar(); 
                 CallableStatement stmt = conn.prepareCall(sql); 
                 ResultSet rs = stmt.executeQuery()) {
    
                while (rs.next()) {
                    Object[] usuario = new Object[7]; // 7 columnas de la tabla "usuarios"
                    usuario[0] = rs.getInt("idUsuarios");
                    usuario[1] = rs.getString("Primer_Nombre");
                    usuario[2] = rs.getString("Segundo_Nombre");
                    usuario[3] = rs.getString("Primer_Apellido");
                    usuario[4] = rs.getString("Segundo_Apellido");
                    usuario[5] = rs.getString("Login");
                    usuario[6] = rs.getString("Clave");
                    usuarios.add(usuario); // Agregar fila a la lista
                }
    
            } catch (SQLException e) {
                System.out.println("Error al obtener los usuarios: " + e.getMessage());
            }
    
            return usuarios;
        }
    
}
