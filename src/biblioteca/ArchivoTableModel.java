/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Javier
 */
public class ArchivoTableModel extends AbstractTableModel {

    private Libros listaLibros;
    private Date dateFormat;
    public ArchivoTableModel(Libros libros) {
        this.listaLibros = libros;
    }

    //creacion de modelo de tabla
    private String columnNames = "";

    //se usara para escribir el nombre de la columna
    @Override
    public String getColumnName(int columna) {
        switch (columna) {
            case 0:
                columnNames = "ID";
                break;
            case 1:
                columnNames = "Nombre";
                break;
            case 2:
                columnNames = "Autor";
                break;
            case 3:
                columnNames = "ISBN";
                break;
            case 4:
                columnNames = "Fecha Publicación";
                break;
            case 5:
                columnNames = "Nº Edición";
                break;
            case 6:
                columnNames = "Editorial";
                break;
            case 7:
                columnNames = "Genero";
                break;
            case 8:
                columnNames = "Colegio";
                break;

        }
        return columnNames;
    }
//

    @Override
    public int getRowCount() {
        //aqui indicaremos el tamaño de las filas 
        return listaLibros.getSize();
    }
//

    @Override
    public int getColumnCount() {
        //aqui le indicaremos la cantidad de columnas que va a ver
        return 9;
    }
//

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Libro datosLibro = listaLibros.getListaLibros().get(rowIndex);
        
        //aqui retornara lo que se quiere mostrar en la tabla, solo dar los datos
        switch (columnIndex) {
            case 0:
                return datosLibro.getIDlibro();
            case 1:
                return datosLibro.getNombreLibro();
            case 2:
                return datosLibro.getAutor();
            case 3:
                return datosLibro.getIsbn();
//            case 4:
//                DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.SHORT);
////               dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                return fechaFormato.format(datosLibro.getPublicacion());
                 
            case 5:
                return datosLibro.getNuEdicion();
            case 6:
                return datosLibro.getEditorial();
            case 7:
                return datosLibro.getGenero();
            case 8:
                return datosLibro.getPrestadoA();
            default:
                return null;

        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Libro libro = listaLibros.getListaLibros().get(rowIndex);
        switch (columnIndex) {
            case 0:
                libro.setIDlibro(Integer.valueOf((String) aValue));
                break;
            case 1:
                libro.setNombreLibro((String) aValue);
                break;
            case 2:
                libro.setAutor((String) aValue);
                break;
            case 3:
                libro.setIsbn((String) aValue);
                break;
            case 4:
                libro.setPublicacion((Date) aValue);
                break;
            case 5:
                libro.setNuEdicion((int) aValue);
                break;
            case 6:
                libro.setEditorial((String) aValue);
                break;
            case 7:
                libro.setGenero((String) aValue);
                break;
            case 8:
                libro.setPrestadoA((String) aValue);
                break;
        }

    }
}
