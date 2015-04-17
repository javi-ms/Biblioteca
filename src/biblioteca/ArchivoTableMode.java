/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Javier
 */
public class ArchivoTableMode extends AbstractTableModel{

    private Libros listaLibros;

    public ArchivoTableMode(Libros libros) {
        this.listaLibros=libros;
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
            listaLibros.getListaLibros().get(rowIndex);
            Libro datosLibro = Libros.getLibro(rowIndex);
            //aqui retornara lo que se quiere mostrar en la tabla, solo dar los datos
            switch (columnIndex) {
                case 0:
                    return datosLibro.getId();
                case 1:
                    return datosLibro.getNombreLibro();
                case 2:
                    return datosLibro.getAutor();
                case 3:
                    return String.valueOf(datosLibro.getISBN());
                case 4:
                    return datosLibro.getFechaPublicacion();
                case 5:
                    return datosLibro.getNumEdicion();
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
    
}
