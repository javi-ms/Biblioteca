/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Date;
import java.text.DateFormat;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Javier
 */
public class ArchivoTableModel extends AbstractTableModel {

    private Libros listaLibros;
    private Date dateFormat;
    DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.SHORT);

    public ArchivoTableModel(Libros libros) {
        this.listaLibros = libros;
    }

    //creacion de modelo de tabla
    private String columnNames = "";

    //se usara para escribir el nombre de la columna
    @Override
    public String getColumnName(int columna) {
        switch (columna) {
//            case 0:
//                columnNames = "ID";
//                break;
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
                columnNames = "Fecha Publicación";
                break;
            case 4:
                columnNames = "Nº Edición";
                break;
            case 5:
                columnNames = "Editorial";
                break;
            case 6:
                columnNames = "Genero";
                break;
            case 7:
                columnNames = "Prestado";
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
        return 8;
    }
//

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
//                DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.SHORT);
//               dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //return fechaFormato.format(datosLibro.getPublicacion());
            return datosLibro.getPublicacion();
            case 4:
                return datosLibro.getNuEdicion();
            case 5:
                return datosLibro.getEditorial();
            case 6:
                return datosLibro.getGenero();
            case 7:
                return datosLibro.getPrestado();
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
//            case 0:
//                libro.setIDlibro(Integer.valueOf((String) aValue));
//                break;
            case 0:
                libro.setNombreLibro((String) aValue);
                break;
            case 1:
                libro.setAutor((String) aValue);
                break;
            case 2:
                libro.setIsbn((String) aValue);
                break;
            case 3:
                libro.setPublicacion((Date) aValue);
                break;
            case 4:
                libro.setNuEdicion((int) aValue);
                break;
            case 5:
                libro.setEditorial((String) aValue);
                break;
            case 6:
                libro.setGenero((String) aValue);
                break;
            case 7:
                libro.setPrestado((boolean) aValue);
                break;
        }
    }
}
