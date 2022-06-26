/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CRUD;
import javax.swing.table.DefaultTableModel;
import CRUD.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import org.bson.Document;
/**
 *
 * @author Rama
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    private DefaultTableModel tabmodel;
    private final karyawanMain utama = new karyawanMain();
    
    public GUI() {
        initComponents();
        try {
            showData();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Get database is successful");
//        model = new DefaultTableModel();
//        tbldata.setModel(model);
//        model.addColumn("NIK");
//        model.addColumn("Nama");
//        model.addColumn("Jenes ");
//        model.addColumn("Nama Usaha");
//        model.addColumn("Alamat Usaha");
//        model.addColumn("Pendapatan Perbulan");
    }
		
		
		
    private void searchData() {
        try {
            String key = txtCari.getText();
            if(key.isEmpty()){
                showData(); 
            }else {
                MongoClient mongo = MongoClients.create("mongodb://127.0.0.1:27017");
                MongoDatabase db = mongo.getDatabase("uas");
                MongoCollection<Document> playerColl = db.getCollection("karyawan");

                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("nama", txtCari.getText());

                FindIterable<Document> cursor = playerColl.find(whereQuery);
                MongoCursor<Document> iterator = cursor.iterator();
                while(iterator.hasNext()) {
                    System.out.println(iterator.next());
}
            }
            
        } catch (Exception e) {
            
        }
    }
        
    // Mengeksport database ke dalam bentuk JSON file
    public void eksportjson(){
        JFileChooser chooser = new JFileChooser();
        int file =chooser.showSaveDialog(this);
        if(file == chooser.APPROVE_OPTION){
            try{
                File f = chooser.getSelectedFile();
                //fileWriter = new FileWriter(new File(chooser.getSelectedFile());           
                Runtime.getRuntime().exec("C:\\Program Files\\MongoDB\\Server\\5.0\\bin\\mongoexport --host localhost --port 27017 --db uas --collection karyawan --out "+f.getPath()+".json");
                JOptionPane.showMessageDialog(this, "Database Berhasil Di Export");
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
            
    }
        
    // Menampilkan Data
    public void showData() throws Exception {
        DefaultTableModel model = (DefaultTableModel)tbldata.getModel();
        Object[] column = {"NIK","Nama", "Jenis kelamin", "Usia", "Alamat", "Gaji"};
        List<karyawan> anggotas = utama.getAnggotas();
        Object[][] data = new Object[anggotas.size()][7];
        int i = 0;
        for(karyawan a : anggotas){
            Object[] arr_data = {a.getNik(), a.getNama(), a.getJk(), a.getUsia(), a.getAlamat(), a.getGaji()};
            data[i] = arr_data;
            i++;
        }
        tabmodel = new DefaultTableModel(data, column);
        tbldata.setModel(tabmodel);
    }
    
    public void ambilData(){
        try {
            //setting model tabel
            DefaultTableModel tbl = new DefaultTableModel();
//            tbl.addColumn("id");
            tbl.addColumn("NIK");
            tbl.addColumn("Nama");
            tbl.addColumn("Jenis Kelamin");
            tbl.addColumn("Usia");
            tbl.addColumn("Alamat");
            tbl.addColumn("Gaji");
            tbldata.setModel(tbl);
            
            //menghubungkan ke mongodb
            MongoDatabase database = config.sambungDB();
            
            //membuat variable untuk menangkap key dari txtCari
            String key = txtCari.getText();
            
            //menghubungkan ke collection 'tanaman' sekaligus cari berdasarkan 'key'
            FindIterable<Document> findIt = database.getCollection("karyawan").find(or
            (
                eq("nik", new Document("$regex",key)),
                eq("nama", new Document("$regex",key)),
                eq("jk", new Document("$regex",key)),
                eq("usia", new Document("$regex",key)),
                eq("alamat", new Document("$regex",key)),
                eq("gaji", new Document("$regex",key))
            )
            );
            
          MongoCursor <Document> cursor = findIt.iterator();
        
            //mengambil data dari cursor dan menampilkan ke table
            while(cursor.hasNext()){
                Document obj = (Document) cursor.next();
                String nik = (String) obj.get("NIK");
                String nama = (String) obj.get("Nama");
                String kelamin = (String) obj.get("Jenis kelamin");
                String alamat = (String) obj.get("Alamat");
                String gaji = (String) obj.get("Gaji");
                
            
                //Object[] data = {username,password,nama,no_hp,alamat};
                //tbl.addRow(data);
            
                Object[] data = {nik, nama, kelamin, alamat, gaji};
                tbl.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
		
		
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnkelamin = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnik = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        cmbkelamin = new javax.swing.JComboBox<>();
        txtusia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        txtgaji = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldata = new javax.swing.JTable();
        btntambah = new javax.swing.JToggleButton();
        btnhapus = new javax.swing.JToggleButton();
        btnedit = new javax.swing.JToggleButton();
        btnlogout = new javax.swing.JToggleButton();
        btnexcel = new javax.swing.JToggleButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REKAPITULASI KARYAWAN CV. PUSAKA HIDUP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel4.setText("NIK");

        jLabel3.setText("Nama");

        jLabel5.setText("Jenis Kelamin");

        jLabel6.setText("Usia");

        jLabel7.setText("Alamat");

        jLabel8.setText("Pendapatan Perbulan");

        cmbkelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Item ---", "Laki-Laki", "Perempuan" }));
        cmbkelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbkelaminActionPerformed(evt);
            }
        });

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        tbldata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldata);

        btntambah.setBackground(new java.awt.Color(102, 102, 255));
        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(255, 0, 51));
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnedit.setBackground(new java.awt.Color(255, 204, 102));
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnlogout.setBackground(new java.awt.Color(204, 0, 0));
        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        btnexcel.setBackground(new java.awt.Color(51, 255, 51));
        btnexcel.setText("Export JSON");
        btnexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexcelActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(txtusia, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnik)
                            .addComponent(txtnama)
                            .addComponent(txtgaji)
                            .addComponent(cmbkelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnhapus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnlogout, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                            .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCari)
                                .addGap(18, 18, 18)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(44, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(cmbkelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtusia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtgaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(726, 667));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        try {
            karyawan a = new karyawan();
            a.setNik(Integer.parseInt(txtnik.getText()));
            a.setNama(txtnama.getText());
            a.setJk(cmbkelamin.getSelectedItem().toString());
//            String JenKel="";
//                if (rlaki.isSelected()){
//                    JenKel = rlaki.getText();
//                }else{
//                    JenKel = rperempuan.getText();
//                }
//            a.setJk(JenKel);
            a.setUsia(Integer.parseInt(txtusia.getText()));
            a.setAlamat(txtalamat.getText());
            a.setGaji(Integer.parseInt(txtgaji.getText()));
            utama.save(a);
            this.dispose();
            new GUI().setVisible(true);
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambah");
            
        } catch (Exception e) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        try { 
        karyawan a = new karyawan();
            a.setNik(Integer.parseInt(txtnik.getText()));
            a.setNama(txtnama.getText());
            a.setJk(cmbkelamin.getSelectedItem().toString());
            a.setUsia(Integer.parseInt(txtusia.getText()));
            a.setAlamat(txtalamat.getText());
            a.setGaji(Integer.parseInt(txtgaji.getText()));
             
//            try {
                utama.update(a);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            new GUI().setVisible(true);
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Ubah");
    }//GEN-LAST:event_btneditActionPerformed

    private void tbldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldataMouseClicked
        
        TableModel model = tbldata.getModel();
        int indeks = tbldata.getSelectedRow();
        if (indeks != -1) {

            //            String _id = (String) model.getValueAt(indeks, 0);
            String nik = model.getValueAt(indeks, 0).toString();
            String nama = model.getValueAt(indeks, 1).toString();
            String jk = model.getValueAt(indeks, 2).toString();
            String usia = model.getValueAt(indeks, 3).toString();
            String alamat = model.getValueAt(indeks, 4).toString();
            String gaji = model.getValueAt(indeks, 5).toString();

            txtnik.setText(nik);
            txtnama.setText(nama);
            cmbkelamin.setSelectedItem(jk);
            txtusia.setText(usia);
            txtalamat.setText(alamat);
            txtgaji.setText(gaji);

            setVisible(true);
            
            pack();

        }
    }//GEN-LAST:event_tbldataMouseClicked

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try {
            utama.delete(Integer.parseInt(txtnik.getText()));
            showData();
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        this.dispose();
        new GUI().setVisible(true);
        JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus");
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        int pesan = JOptionPane.showConfirmDialog(null, "Apakah anda ingin logout?",
                    "Konfirmasi logout",JOptionPane.OK_CANCEL_OPTION);
        if(pesan == JOptionPane.OK_OPTION){
            try {
                new login().setVisible(true);
                dispose();
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        ambilData();
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        ambilData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexcelActionPerformed
        eksportjson();
    }//GEN-LAST:event_btnexcelActionPerformed

    private void cmbkelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbkelaminActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JToggleButton btnedit;
    private javax.swing.JToggleButton btnexcel;
    private javax.swing.JToggleButton btnhapus;
    private javax.swing.ButtonGroup btnkelamin;
    private javax.swing.JToggleButton btnlogout;
    private javax.swing.JToggleButton btntambah;
    private javax.swing.JComboBox<String> cmbkelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbldata;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtgaji;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnik;
    private javax.swing.JTextField txtusia;
    // End of variables declaration//GEN-END:variables
}
