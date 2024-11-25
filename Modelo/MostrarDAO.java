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
            String sql = "{CALL MostrarUsuarios()}"; // Consulta SP para obtener los usuarios
    
            try (Connection conn = Conexion.conectar(); 
                CallableStatement stmt = conn.prepareCall(sql); 
                ResultSet rs = stmt.executeQuery()) {
    
                while (rs.next()) {
                    Object[] usuario = new Object[8]; // 8 columnas de la tabla "usuarios"
                    usuario[0] = rs.getInt("id_usuario");
                    usuario[1] = rs.getString("primer_nombre");
                    usuario[2] = rs.getString("segundo_nombre");
                    usuario[3] = rs.getString("primer_apellido");
                    usuario[4] = rs.getString("segundo_apellido");
                    usuario[5] = rs.getString("login");
                    usuario[6] = rs.getString("clave");
                    usuario[7] = rs.getString("fecha_creacion");
                    usuarios.add(usuario); // Agregar fila a la lista
                }
    
            } catch (SQLException e) {
                System.out.println("Error al obtener los usuarios: " + e.getMessage());
            }
    
            return usuarios;
        }
    
}
