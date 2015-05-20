/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ModelosTablas;

import biblioteca.Libro;
import biblioteca.Libros;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class HistorialTableModel extends AbstractTableModel {

    private Libros listaLibros;
    private Date dateFormat;

    public HistorialTableModel(Libros libros) {
        this.listaLibros = libros;
    }
    private String columnNames = "";

    @Override
    public String getColumnName(int columna) {
        switch (columna) {

            case 0:
                columnNames = "Nombre";
                break;
            case 1:
                columnNames = "Autor";
                break;
            case 2:
                columnNames = "ISBN";
                break;
            case 3:
                columnNames = "Editorial";
                break;
            case 4:
                columnNames = "Genero";
                break;
            case 5:
                columnNames = "Prestado A";
                break;

        }
        return columnNames;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Libro datosLibro = listaLibros.getListaLibros().get(rowIndex);

        //aqui retornara lo que se quiere mostrar en la tabla, solo dar los datos
        switch (columnIndex) {
//            case 0:
//                return datosLibro.getIDlibro();
            case 0:
                return datosLibro.getNombreLibro();
            case 1:
                return datosLibro.getAutor();
            case 2:
                return datosLibro.getIsbn();
            case 3:
                return datosLibro.getEditorial();
            case 4:
                return datosLibro.getGenero();
            case 5:
                return datosLibro.getPrestadoA();
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        //aqui indicaremos el tamaño de las filas 
        return listaLibros.getSize();
    }

    @Override
    public int getColumnCount() {
        //aqui le indicaremos la cantidad de columnas que va a ver
        return 6;
    }

}
