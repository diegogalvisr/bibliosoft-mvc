package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobiblioteca.config.BasedeDatos;
import proyectobiblioteca.models.UsuariosDTO;
import proyectobiblioteca.models.adminsDTO;

public class UsuariosDAO {

    public ArrayList<UsuariosDTO> obtenerUsers() {
        ArrayList<UsuariosDTO> users = new ArrayList<>();
        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM usuarios");

            if (resultado != null) { // Verificar que el resultado no sea null
                while (resultado.next()) {
                    int id_usuario = resultado.getInt("id_usuario");
                    int user_generico = resultado.getInt("user_generico");
                    String nombre = resultado.getString("nombre");
                    String apellido = resultado.getString("apellido");
                    String direccion = resultado.getString("direccion");
                    String telefono = resultado.getString("telefono");
                    String telefonoF = resultado.getString("telefonoF");
                    int grado = resultado.getInt("grado");
                    int id_multa = resultado.getInt("id_multa");
                    users.add(new UsuariosDTO(id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa));
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public DefaultTableModel tablaUsers() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono 1");
        modelo.addColumn("Telefono 2");
        modelo.addColumn("Grado");
       /// modelo.addColumn("Multa"); se deshabilita porque no se requiere saber si tiene multa

        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT * FROM usuarios");

            if (resultado != null) { // Verificar que el resultado no sea null
                while (resultado.next()) {
                    int id_usuario = resultado.getInt("id_usuario");
                    int user_generico = resultado.getInt("user_generico");
                    String nombre = resultado.getString("nombre");
                    String apellido = resultado.getString("apellido");
                    String direccion = resultado.getString("direccion");
                    String telefono = resultado.getString("telefono");
                    String telefonoF = resultado.getString("telefonoF");
                    int grado = resultado.getInt("grado");
                    int id_multa = resultado.getInt("id_multa");

                    // Agregamos los datos al modelo
                    Object[] fila = {id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa};
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public DefaultTableModel busqueda(String nombreSeleccion) {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono 1");
        modelo.addColumn("Telefono 2");
        modelo.addColumn("Grado");
        modelo.addColumn("Multa");

        String sql = "SELECT id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa FROM usuarios WHERE user_generico LIKE ? OR nombre LIKE ? OR apellido LIKE ? OR direccion LIKE ? OR telefono LIKE ? OR telefono LIKE ? OR telefonoF LIKE ? OR grado LIKE ? OR id_multa LIKE ?";

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return modelo;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + nombreSeleccion + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            stmt.setString(3, parametro);
            stmt.setString(4, parametro);
            stmt.setString(5, parametro);
            stmt.setString(6, parametro);
            stmt.setString(7, parametro);
            stmt.setString(8, parametro);
            stmt.setString(9, parametro);

            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int id_usuario = result.getInt("id_usuario");
                    int user_generico = result.getInt("user_generico");
                    String nombre = result.getString("nombre");
                    String apellido = result.getString("apellido");
                    String direccion = result.getString("direccion");
                    String telefono = result.getString("telefono");
                    String telefonoF = result.getString("telefonoF");
                    int grado = result.getInt("grado");
                    int id_multa = result.getInt("id_multa");

                    // Agregamos los datos al modelo
                    Object[] fila = {id_usuario, user_generico, nombre, apellido, direccion, telefono, telefonoF, grado, id_multa};
                    modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return modelo;
    }

    public static void insertarUser(UsuariosDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "INSERT INTO usuarios (user_generico, nombre, apellido, direccion, telefono, telefonoF,grado) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, admin.getUser_generico());
            statement.setString(2, admin.getNombre());
            statement.setString(3, admin.getApellido());
            statement.setString(4, admin.getDireccion());
            statement.setString(5, admin.getTelefono());
            statement.setString(6, admin.getTelefonoF());
            statement.setInt(7, admin.getGrado());

            statement.executeUpdate();
            // BasedeDatos.desconectar();
// Crea un nuevo JDialog personalizado

// Muestra un mensaje en el cuadro de diálogo
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el usuario " + admin.getUser_generico());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void actualizarUser(UsuariosDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "UPDATE usuarios SET user_generico = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, telefonoF = ?,grado=? WHERE id_usuario = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, admin.getUser_generico());
            statement.setString(2, admin.getNombre());
            statement.setString(3, admin.getApellido());
            statement.setString(4, admin.getDireccion());
            statement.setString(5, admin.getTelefono());
            statement.setString(6, admin.getTelefonoF());
            statement.setInt(7, admin.getGrado());
            statement.setInt(8, admin.getId_usuario());
            statement.executeUpdate();
         ///   BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null, "Se ha actualizado exitosamente el usuario " + admin.getUser_generico());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void eliminarUser(int id_user) {
        try {
            BasedeDatos.conectar();
            String consulta = "DELETE FROM usuarios WHERE id_usuario = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, id_user);
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el usuario.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
