/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobiblioteca.config.BasedeDatos;
import proyectobiblioteca.models.adminsDTO;
import proyectobiblioteca.views.GUIPrincipal;

public class adminsDAO {

    public ArrayList<adminsDTO> obtenerAdmins() {
        ArrayList<adminsDTO> admins = new ArrayList<>();
        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM admins");

            if (resultado != null) { // Verificar que el resultado no sea null
                while (resultado.next()) {
                    int id_admin = resultado.getInt("id_admin");
                    String usuario = resultado.getString("usuario");
                    String clave = resultado.getString("clave");
                    String nombres = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellido");
                    String direccion = resultado.getString("direccion");
                    int cargo = resultado.getInt("cargo");
                    admins.add(new adminsDTO(id_admin, usuario, clave, nombres, apellidos, direccion, cargo));
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return admins;
    }

    public DefaultTableModel tablaAdmins() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Dirección");
        modelo.addColumn("Cargo");

        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM admins");
            adminsDTO ADTO = new adminsDTO();
            if (resultado != null) { // Verificar que el resultado no sea null
                while (resultado.next()) {
                    ADTO.setId_admin(resultado.getInt("id_admin"));
                    ADTO.setUsuario(resultado.getString("usuario"));
                    ADTO.setClave(resultado.getString("clave"));
                    ADTO.setNombres(resultado.getString("nombre"));
                    ADTO.setApellidos(resultado.getString("apellido"));
                    ADTO.setDireccion(resultado.getString("direccion"));
                    ADTO.setCargo(resultado.getInt("cargo"));
                    // Agregamos los datos al modelo
                    Object[] fila = {ADTO.getId_admin(),ADTO.getUsuario(), ADTO.getClave(), ADTO.getNombres(), ADTO.getApellidos(), ADTO.getDireccion(), ADTO.getCargo()};
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    public DefaultTableModel busqueda(String nombreSeleccion) {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Cargo");

        String sql = "SELECT id_admin, usuario, clave, nombre, apellido, direccion, cargo FROM admins WHERE id_admin LIKE ? OR usuario LIKE ? OR nombre LIKE ? OR cargo LIKE ? OR apellido LIKE ?";

        try {
            BasedeDatos.conectar();
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + nombreSeleccion + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            stmt.setString(3, parametro);
            stmt.setString(4, parametro);
            stmt.setString(5, parametro);

            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int id_admin = result.getInt("id_admin");
                    String usuario = result.getString("usuario");
                    String clave = result.getString("clave");
                    String nombre = result.getString("nombre");
                    String apellido = result.getString("apellido");
                    String direccion = result.getString("direccion");
                    int cargo = result.getInt("cargo");

                    // Agregamos los datos al modelo
                    Object[] fila = {id_admin, usuario, clave, nombre, apellido, direccion, cargo};
                    modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error consultando admins");
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    public static void insertarAdmin(adminsDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "INSERT INTO admins (usuario, clave, nombre, apellido, direccion, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setString(1, admin.getUsuario());
            statement.setString(2, admin.getClave());
            statement.setString(3, admin.getNombres());
            statement.setString(4, admin.getApellidos());
            statement.setString(5, admin.getDireccion());
            statement.setInt(6, admin.getCargo());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
// Crea un nuevo JDialog personalizado

// Muestra un mensaje en el cuadro de diálogo
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el usuario " + admin.getUsuario());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarAdmin(String usuario) {
    try {
        BasedeDatos.conectar();
        String sql = "SELECT id_admin FROM admins WHERE usuario LIKE ?";
        int id_admin=0;

        try {
            BasedeDatos.conectar();
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + usuario + "%";
            stmt.setString(1, parametro);
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    id_admin = result.getInt("id_admin");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error consultando el usuario");
        } 
        
        // Realizar la eliminación
        String consultaEliminacion = "DELETE FROM admins WHERE id_admin = ?";
        PreparedStatement eliminacionStatement = BasedeDatos.conexion.prepareStatement(consultaEliminacion);
        eliminacionStatement.setInt(1, id_admin);
        eliminacionStatement.executeUpdate();
                                        JOptionPane.showMessageDialog(null, "El id del usuario: "+usuario+" es: "+id_admin);

        System.out.println("Se ha eliminado el admin de la BD.");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        BasedeDatos.desconectar(); // Cerrar la conexión
    }
}


    public static void actualizarAdmin(adminsDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "UPDATE admins SET usuario = ?, clave = ?, nombre = ?, apellido = ?, direccion = ?, cargo = ? WHERE id_admin = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setString(1, admin.getUsuario());
            statement.setString(2, admin.getClave());
            statement.setString(3, admin.getNombres());
            statement.setString(4, admin.getApellidos());
            statement.setString(5, admin.getDireccion());
            statement.setInt(6, admin.getCargo());
            statement.setInt(7, admin.getId_admin());
            statement.executeUpdate();
            ///   BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null, "Se ha actualizado exitosamente el usuario " + admin.getUsuario());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }

    public int cargo = 0;

    public boolean validarUsuario(String user, String pass) {
        List<adminsDTO> usuarios = obtenerAdmins();
        for (adminsDTO usuario : usuarios) {
            if (usuario.getUsuario().equals(user) && usuario.getClave().equals(pass)) {
                cargo = usuario.getCargo();
                GUIPrincipal.logeo = true;
                return true; // el usuario y la clave son correctos
            }
        }
        return false; // el usuario o la clave son incorrectos
    }

}
