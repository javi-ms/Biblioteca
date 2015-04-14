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
public class Tabla {
    Libros listaLibros =new Libros();
    //creacion de modelo de tabla
    class MyTableModel extends AbstractTableModel {

        //

        private String columnNames = "";

        //se usara para escribir el nombre de la columna
        @Override
        public String getColumnName(int columna) {
            switch (columna) {
                case 1:
                    columnNames = "ID";
                    break;
                case 2:
                    columnNames = "Nombre";
                    break;
                case 3:
                    columnNames = "Autor";
                    break;
                case 4:
                    columnNames = "ISBN";
                    break;
                case 5:
                    columnNames = "Fecha Publicación";
                    break;
                case 6:
                    columnNames = "Nº Edición";
                    break;
                case 7:
                    columnNames = "Editorial";
                    break;
                case 8:
                    columnNames = "Genero";
                    break;
                case 9:
                    columnNames = "Colegio";
                    break;

            }
            return columnNames;
        }
//

        @Override
        public int getRowCount() {
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

        }
    }
}
