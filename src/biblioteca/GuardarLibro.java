package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class GuardarLibro extends javax.swing.JFrame {

    Libros libros = new Libros();

//    private CategoriasContenedor categoriasContenedor = new CategoriasContenedor();
//    private ProductosContenedor productosContenedor = new ProductosContenedor()
    private ListaEditorial editoriales = new ListaEditorial();
    private ListaGenero generos = new ListaGenero();
    private ArchivoTableModel archivoTableModel;
    private static EntityManager entityManager;
    private Query consultaLibro;
    //para que por fuerza debas cerrar la ventana(hacerlo modal)
    Historial jDialogHistorial = new Historial(this, true);

    public GuardarLibro() {
        initComponents();

        entityManager = Persistence.createEntityManagerFactory("BibliotecaPU").createEntityManager();
        consultaLibro = entityManager.createNamedQuery("Libro.findAll");
        libros.setListaLibros(consultaLibro.getResultList());

        //quiero que el date se convierta en string hay que hacerlo cuando se muestre en pantalla
        //añadir editoriales
        Editorial editorial;
        editorial = new Editorial(1, "Salamandra");
        editoriales.getListaEditorial().add(editorial);
        editorial = new Editorial(1, "Minotauro");
        editoriales.getListaEditorial().add(editorial);

        //añadir generos
        Genero genero;
        genero = new Genero(1, "Fantasia");
        generos.getListaGenero().add(genero);
        genero = new Genero(1, "Ciencia ficción");
        generos.getListaGenero().add(genero);
        //rellenar los jComboBox
        jComboBox1.setModel(new DefaultComboBoxModel(editoriales.getListaEditorial().toArray()));
        //jComboBox1.setRenderer(new ListasDeplegablesRenderer());
        jComboBox2.setModel(new DefaultComboBoxModel(generos.getListaGenero().toArray()));
        //jComboBox2.setRenderer(nw ListasDeplegablesRenderer());

        // Permitir sólo una fila seleccionada
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Añadir un detector de cambio de selección en la tabla
        jTable1.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        detalleTablas();
                    }
                }
                
        );
        //creacion del contenedor de los libros
//        //se crea un libro individual
//        libro = new Libro(1, "ESDLA", "Tolkien", 0000000001, Date.from(Instant.now()), 2, editorial, genero, true, "El libro va de ");
//        //aqui se añade el libro a la lista
//        libros.getListaLibros().add(libro);
//        libro = new Libro(2, "Harry Potter", "Rowling", 0000000002, Date.from(Instant.now()), 2, editorial, genero, false, "el libro trata de");
//        //aqui se añade el libro a la lista
//        libros.getListaLibros().add(libro);
//        jTable1.setValueAt(libro, 0, 0);
        //
        archivoTableModel = new ArchivoTableModel(libros);
        //
        jTable1.setModel(archivoTableModel);
        //jTable1.getColumnModel().getColumn(4).setCellRenderer(new FechaRenderer());
        //aqui tengo que poner el tamaño ya sea por numero, variable o desde una clase
//        jTextField1.setDocument(new MaxLengthDocument(15));
        jComboBox1.setRenderer(new ListasDeplegablesRenderer());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new FechaRenderer());
        //jTable1.getColumnModel().getColumn(6).setCellRenderer(new ListasDeplegablesRenderer());
        jTextField2.setDocument(new MaxLengthDocument(50));
        jTextField3.setDocument(new MaxLengthDocument(50));
        jTextField4.setDocument(new ControlNumerico(10));
        jTextField5.setDocument(new MaxLengthDocument(2));
        //llama al metodo creado
//        this.ListaTabla();
    }

    public void detalleTablas() {
        //selecciona una sola fila
        int indexSelectedRow = jTable1.getSelectedRow();
        if (indexSelectedRow < 0) {
            //muestra los textos "vacios"
            //jTextField1.setText("Sin selección");
            jTextField2.setText("Sin selección");
            jTextField3.setText("Sin selección");
            jTextField4.setText("Sin selección");
            jTextField5.setText("Sin selección");
            jTextArea1.setText("Sin seleccion");
//            jDateChooser1.setDate(null);
//            jComboBox1.setDefaultLocale(getLocale());
        } else {
            //Nos permite mostrar los datos 
            //jTextField1.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getIDlibro()));
            jTextField2.setText(libros.getListaLibros().get(indexSelectedRow).getNombreLibro());
            jTextField3.setText(libros.getListaLibros().get(indexSelectedRow).getAutor());
            jTextField4.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getIsbn()));
            jTextField5.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getNuEdicion()));
            //jComboBox1.setText((String.valueOf(libros.getListaLibros().get(indexSelectedRow).getEditorial())));
            jDateChooser1.setDate(libros.getListaLibros().get(indexSelectedRow).getPublicacion());
            jTextArea1.setText(libros.getListaLibros().get(indexSelectedRow).getSinopsis());
            //añadirlo al libro
        }
    }

    /**
     * Insertar un libro
     */
    public void insertLibro(Libro l) {

        entityManager.getTransaction().begin();
        entityManager.persist(l);
        entityManager.getTransaction().commit();

        // Añadir el objeto a la lista de libros
        libros.getListaLibros().add(l);
        //
    }

    /**
     * editar un libro
     */
    public void actualizarLibro(Libro libro, int posLista) {

        entityManager.getTransaction().begin();
        entityManager.merge(libro);
        entityManager.getTransaction().commit();

        libros.getListaLibros().set(posLista, libro);

    }

//    public void ListaTabla() {
//        //Crear un ComboBox para que sea el editor de la columna de Categorías 
//        TableColumn generoColum = jTable1.getColumnModel().getColumn(6);
//        JComboBox comboBoxCellCategoria = new JComboBox();
//        comboBoxCellCategoria.setModel(new DefaultComboBoxModel(generos.getListaGenero().toArray()));
//        comboBoxCellCategoria.setRenderer(new ListasDeplegablesRenderer());
//        generoColum.setCellEditor(new DefaultCellEditor(comboBoxCellCategoria));
//        //Aplicar un renderer para mostrar los objetos Categoria en la tabla 
//        jTable1.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
//            @Override
//            protected void setValue(Object value) {
//                Genero genero = (Genero) value;
//                setText(genero.getNombreGenero());
//            }
//        });
//    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel3.setText("ISBN");

        jLabel4.setText("Autor");

        jLabel5.setText("Fecha Publicación");

        jLabel6.setText("Editorial");

        jLabel8.setText("Edicion");

        jLabel9.setText("Genero");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Nuevo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Prestado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addComponent(jButton3))
                        .addGap(130, 130, 130))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jCheckBox1))
                        .addGap(120, 120, 120))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );

        jTabbedPane1.addTab("Individual", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton5.setText("Historial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registro", jPanel2);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Panel de prueba", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Libro libro = new Libro();
        //recoge informacion de los campos de texto y lo pone en la tabla
        //Libro libro = libros.getLibro(jTable1.getSelectedRow());
        // libro.setIDlibro(Integer.valueOf(jTextField1.getText()));
        libro.setNombreLibro(jTextField2.getText());
        libro.setAutor(jTextField3.getText());
        libro.setIsbn(String.valueOf(jTextField4.getText()));
        libro.setPublicacion(jDateChooser1.getDate());
        libro.setNuEdicion(Integer.valueOf(jTextField5.getText()));
        libro.setEditorial(String.valueOf(jComboBox1.getSelectedItem()));
        libro.setGenero(String.valueOf(jComboBox2.getSelectedItem()));
        libro.setPrestado(jCheckBox1.isSelected());
        libro.setSinopsis(jTextArea1.getText());
        archivoTableModel.fireTableRowsUpdated(jTable1.getSelectedRow(), jTable1.getSelectedRow());
        jTable1.setEnabled(true);

        insertLibro(libro);

        archivoTableModel.fireTableRowsInserted(libros.getListaLibros().size() - 1, libros.getListaLibros().size() - 1);
        // tableModel is a extends of AbstractTableModel
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //me devuelve la posicion seleccionada
        if (jTable1.getSelectedRow() > -1) {

            libros.getListaLibros().get(jTable1.getSelectedRow()).setNombreLibro(jTextField2.getText());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setAutor(jTextField3.getText());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setIsbn(String.valueOf(jTextField4.getText()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setPublicacion(jDateChooser1.getDate());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setNuEdicion(Integer.valueOf(jTextField5.getText()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setEditorial(String.valueOf(jComboBox1.getSelectedItem()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setGenero(String.valueOf(jComboBox2.getSelectedItem()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setPrestado(jCheckBox1.isSelected());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setSinopsis(jTextArea1.getText());

        }
        // libros.getListaLibros().get(jTable1.getSelectedRow()) es un libro
        //jTable1.getSelectedRow() es la posicion
        //MIRAR QUE FILA SE HA SELECCIONADO, POSICION DE LA LISTA DEL LIBRO, 
        actualizarLibro(libros.getListaLibros().get(jTable1.getSelectedRow()), jTable1.getSelectedRow());
        archivoTableModel.fireTableRowsUpdated(jTable1.getSelectedRow(), jTable1.getSelectedRow());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int indexSelectedRow = jTable1.getSelectedRow();
        entityManager.getTransaction().begin();
        //borrar el que esta seleccionado en la lista
        entityManager.remove(libros.getListaLibros().get(indexSelectedRow));
        entityManager.getTransaction().commit();

        // Remove object from List
        libros.getListaLibros().remove(indexSelectedRow);

        archivoTableModel.fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);
        // tableModel is a extends of AbstractTableModel assigned to jTable
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        jTable1.setEnabled(false);

//        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);

//        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //aqui al pulsar se tiene que mostrar una tabla con el historial de prestamos
        Libro libro = new Libro();
        //jDialogHistorial.setVisible(true);
        // if (libro.prestado == true) {

        Connection con;
        try {
            //nos conectamos a la base de datos
            con = DriverManager.getConnection("jdbc:mysql://localhost/Biblioteca", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Libro");
            while (rs.next()) {

                String nombre = rs.getString("Nombre_Libro");
                String autor = rs.getString("Autor");
                int isbn = rs.getInt("ISBN");
                String editorial = rs.getString("editorial");
                String genero = rs.getString("Genero");
                String prestadA = rs.getString("PrestadoA");

                libro.setNombreLibro(nombre);
                libro.setAutor(autor);
                libro.setIsbn(String.valueOf(isbn));
                libro.setEditorial(editorial);
                libro.setGenero(genero);
                libro.setPrestadoA(prestadA);

                jTextArea2.append(libro.getNombreLibro() + " " + libro.getAutor() + "\n");
                System.out.println("dsfdfdsfdafds");
                //jTextArea1.append(nombre+autor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GuardarLibro.class.getName()).log(Level.SEVERE, null, ex);

        }

        // }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuardarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuardarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuardarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuardarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuardarLibro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
