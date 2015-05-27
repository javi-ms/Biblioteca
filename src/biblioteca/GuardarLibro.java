package biblioteca;

import biblioteca.Desplegables.ListaGenero;
import biblioteca.Desplegables.Editorial;
import biblioteca.Desplegables.Genero;
import biblioteca.Desplegables.ListaEditorial;
import biblioteca.ControladoresCamposDeTexto.ControlNumerico;
import biblioteca.ControladoresCamposDeTexto.MaxLengthDocument;
import biblioteca.ModelosTablas.ArchivoTableModel;
import biblioteca.Renderer.EditorialDeplegableRenderer;
import biblioteca.Renderer.FechaRenderer;
import biblioteca.Renderer.GeneroDesplegableRender;
import biblioteca.Renderer.PrestadoRenderer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
        jComboBoxEditorial.setModel(new DefaultComboBoxModel(editoriales.getListaEditorial().toArray())); 
        jComboBoxGenero.setModel(new DefaultComboBoxModel(generos.getListaGenero().toArray()));


        /**
         * Permitir sólo una fila seleccionada
         */
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        /**
         * Añadir un detector de cambio de selección en la tabla
         */
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
        jComboBoxEditorial.setRenderer(new EditorialDeplegableRenderer());
        jComboBoxGenero.setRenderer(new GeneroDesplegableRender());
        jTable1.getColumnModel().getColumn(7).setCellRenderer(new PrestadoRenderer());
        //jTable1.getColumnModel().getColumn(3).setCellRenderer(new FechaRenderer());
        
        /**
         * Con esto controlamos la cantidad de letras que vamos a colocar en los
         * campos
         */
        jTextFieldNombre.setDocument(new MaxLengthDocument(50));
        jTextFieldAutor.setDocument(new MaxLengthDocument(50));
        jTextFieldEdicion.setDocument(new MaxLengthDocument(2));
        /**
         * Con esto controlamos la cantidad de numeros que vamos a colocar
         */
        jTextFieldISBN.setDocument(new ControlNumerico(10));

        //llama al metodo creado
//        this.ListaTabla();
    }
    /**
     * Nos permite mostrar los datos en los campos
     */
    public void detalleTablas() {
        //selecciona una sola fila
        int indexSelectedRow = jTable1.getSelectedRow();
        if (indexSelectedRow < 0) {
            //muestra los textos "vacios"
            //jTextField1.setText("Sin selección");
            jTextFieldNombre.setText("Sin selección");
            jTextFieldAutor.setText("Sin selección");
            jTextFieldISBN.setText("Sin selección");
            jTextFieldEdicion.setText("Sin selección");
            jTextAreaSinopsis.setText("Sin seleccion");
//            jDateChooser1.setDate(null);
//            jComboBox1.setDefaultLocale(getLocale());
        } else {
            //Nos permite mostrar los datos 
            //jTextField1.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getIDlibro()));
            jTextFieldNombre.setText(libros.getListaLibros().get(indexSelectedRow).getNombreLibro());
            jTextFieldAutor.setText(libros.getListaLibros().get(indexSelectedRow).getAutor());
            jTextFieldISBN.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getIsbn()));
            jTextFieldEdicion.setText(String.valueOf(libros.getListaLibros().get(indexSelectedRow).getNuEdicion()));
            //jComboBox1.setText((String.valueOf(libros.getListaLibros().get(indexSelectedRow).getEditorial())));
            jDateChooserFechaPublicacion.setDate(libros.getListaLibros().get(indexSelectedRow).getPublicacion());
            jTextAreaSinopsis.setText(libros.getListaLibros().get(indexSelectedRow).getSinopsis());
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

        //Actualiza un solo libro
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSinopsis = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldAutor = new javax.swing.JTextField();
        jDateChooserFechaPublicacion = new com.toedter.calendar.JDateChooser();
        jCheckBoxPrestado = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxGenero = new javax.swing.JComboBox();
        jComboBoxEditorial = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEdicion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButtonHistorialPrestado = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaSinopsis.setColumns(20);
        jTextAreaSinopsis.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSinopsis);

        jLabel5.setText("Fecha Publicación");

        jLabel3.setText("ISBN");

        jLabel4.setText("Autor");

        jLabel1.setText("Nombre");

        jButton4.setText("Nuevo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jTextFieldISBN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldISBNFocusGained(evt);
            }
        });
        jTextFieldISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldISBNActionPerformed(evt);
            }
        });

        jCheckBoxPrestado.setText("Prestado");

        jLabel9.setText("Genero");

        jComboBoxGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxEditorial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEditorialActionPerformed(evt);
            }
        });

        jLabel6.setText("Editorial");

        jLabel8.setText("Edicion");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldAutor)
                        .addComponent(jTextFieldISBN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxPrestado))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldEdicion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBoxEditorial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserFechaPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jComboBoxEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxPrestado)))))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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

        jTabbedPane1.addTab("Historial", jPanel3);

        jButtonHistorialPrestado.setText("Historial");
        jButtonHistorialPrestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorialPrestadoActionPerformed(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButtonGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonHistorialPrestado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHistorialPrestado)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonEditar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        Libro libro = new Libro();
        //recoge informacion de los campos de texto y lo pone en la tabla
       
        libro.setNombreLibro(jTextFieldNombre.getText());
        libro.setAutor(jTextFieldAutor.getText());
        libro.setIsbn(String.valueOf(jTextFieldISBN.getText()));
        libro.setPublicacion(jDateChooserFechaPublicacion.getDate());
        libro.setNuEdicion(Integer.valueOf(jTextFieldEdicion.getText()));
        libro.setEditorial(String.valueOf(jComboBoxEditorial.getSelectedItem()));
        libro.setGenero(String.valueOf(jComboBoxGenero.getSelectedItem()));
        libro.setPrestado(jCheckBoxPrestado.isSelected());
        libro.setSinopsis(jTextAreaSinopsis.getText());

        archivoTableModel.fireTableRowsUpdated(jTable1.getSelectedRow(), jTable1.getSelectedRow());
        jTable1.setEnabled(true);

        insertLibro(libro);

        archivoTableModel.fireTableRowsInserted(libros.getListaLibros().size() - 1, libros.getListaLibros().size() - 1);
        // tableModel is a extends of AbstractTableModel
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFieldISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldISBNActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jTextFieldISBNFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldISBNFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldISBNFocusGained

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed

        //me devuelve la posicion seleccionada
        if (jTable1.getSelectedRow() > -1) {

            libros.getListaLibros().get(jTable1.getSelectedRow()).setNombreLibro(jTextFieldNombre.getText());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setAutor(jTextFieldAutor.getText());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setIsbn(String.valueOf(jTextFieldISBN.getText()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setPublicacion(jDateChooserFechaPublicacion.getDate());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setNuEdicion(Integer.valueOf(jTextFieldEdicion.getText()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setEditorial(String.valueOf(jComboBoxEditorial.getSelectedItem()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setGenero(String.valueOf(jComboBoxGenero.getSelectedItem()));
            libros.getListaLibros().get(jTable1.getSelectedRow()).setPrestado(jCheckBoxPrestado.isSelected());
            libros.getListaLibros().get(jTable1.getSelectedRow()).setSinopsis(jTextAreaSinopsis.getText());

        }
        // libros.getListaLibros().get(jTable1.getSelectedRow()) es un libro
        //jTable1.getSelectedRow() es la posicion
        //MIRAR QUE FILA SE HA SELECCIONADO, POSICION DE LA LISTA DEL LIBRO, 
        actualizarLibro(libros.getListaLibros().get(jTable1.getSelectedRow()), jTable1.getSelectedRow());
        archivoTableModel.fireTableRowsUpdated(jTable1.getSelectedRow(), jTable1.getSelectedRow());
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed

        int indexSelectedRow = jTable1.getSelectedRow();
        entityManager.getTransaction().begin();
        //borrar el que esta seleccionado en la lista
        entityManager.remove(libros.getListaLibros().get(indexSelectedRow));
        entityManager.getTransaction().commit();

        // Remove object from List
        libros.getListaLibros().remove(indexSelectedRow);

        archivoTableModel.fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);
        // tableModel is a extends of AbstractTableModel assigned to jTable
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        jTable1.setEnabled(false);

//        jTextField1.setEditable(true);
        jTextFieldNombre.setEditable(true);
        jTextFieldAutor.setEditable(true);
        jTextFieldISBN.setEditable(true);
        jTextFieldEdicion.setEditable(true);

//        jTextField1.setText("");
        jTextFieldNombre.setText("");
        jTextFieldAutor.setText("");
        jTextFieldISBN.setText("");
        jTextFieldEdicion.setText("");
        jCheckBoxPrestado.isSelected();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonHistorialPrestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorialPrestadoActionPerformed

        /*añadido por que mas abajo daba error*/
        //Libro libro = new Libro();
//         if (libro.getPrestado() == true) {

        Connection con;
        try {
            //nos conecta con el archivo 
            con = DriverManager.getConnection("jdbc:mysql://localhost/Biblioteca", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Libro");

            try {
                Map parameters = new HashMap();
                JasperReport jasperReport
                        = JasperCompileManager.compileReport(
                                "Biblioteca/Biblioteca.jrxml");
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport, parameters, new JRResultSetDataSource(rs));
                JasperViewer.viewReport(jasperPrint);
            } catch (JRException ex) {
                ex.printStackTrace();
            }

            while (rs.next()) {
                /*
                 *Es el nombre que tengas puesto en la base de datos 
                 */
//                String nombre = rs.getString("Nombre_Libro");
//                String autor = rs.getString("Autor");
//                int isbn = rs.getInt("ISBN");
//                String editorial = rs.getString("editorial");
//                String genero = rs.getString("Genero");
//                String prestadA = rs.getString("PrestadoA");

//                libro.setNombreLibro(nombre);
//                libro.setAutor(autor);
//                libro.setIsbn(String.valueOf(isbn));
//                libro.setEditorial(editorial);
//                libro.setGenero(genero);
//                libro.setPrestadoA(prestadA);
//
//                jTextArea2.append(libro.getNombreLibro() + " " + libro.getAutor() + "\n");

                //jTextArea1.append(nombre+autor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GuardarLibro.class.getName()).log(Level.SEVERE, null, ex);

        }

//         }
    }//GEN-LAST:event_jButtonHistorialPrestadoActionPerformed

    private void jComboBoxEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEditorialActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuardarLibro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonHistorialPrestado;
    private javax.swing.JCheckBox jCheckBoxPrestado;
    private javax.swing.JComboBox jComboBoxEditorial;
    private javax.swing.JComboBox jComboBoxGenero;
    private com.toedter.calendar.JDateChooser jDateChooserFechaPublicacion;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextAreaSinopsis;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldEdicion;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
