package proyectobiblioteca.controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class cambiarColorSegunEstado extends DefaultTableCellRenderer {

    private Map<String, Color> colorMapping;

    public cambiarColorSegunEstado() {
        // Inicializa el mapeo de valores a colores
        colorMapping = new HashMap<>();
        colorMapping.put("PRESTADO", Color.GREEN);
        colorMapping.put("CULMINADO", Color.RED);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 8) {
            // Reemplaza TU_NUMERO_DE_COLUMNA con el número de columna que contiene el estado
            String estado = value.toString();
            Color colorTexto = Color.black;
            Color colorFondo = colorMapping.get(estado);

            if (colorFondo != null) {
                cell.setForeground(colorTexto); // Establece el color del texto en blanco
                cell.setBackground(colorFondo); // Cambia el color de fondo según el estado
            }
        }

        return cell;
    }
}
