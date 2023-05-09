/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication17.StudentEditor.ConnerDB;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tahsin Mubarak
 */
public class ACCOUNT extends javax.swing.JFrame {
static String firstWords;
static String secondWords;
    public static Connection ConnerDB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student", "1234");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
    
        static String getAlphaNumericString(int n)
    {
  
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
  
        String randomString
            = new String(array, Charset.forName("UTF-8"));
  
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
  
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
  
            char ch = randomString.charAt(k);
  
            if (((ch >= 'a' && ch <= 'z')
                 || (ch >= 'A' && ch <= 'Z')
                 || (ch >= '0' && ch <= '9'))
                && (n > 0)) {
  
                r.append(ch);
                n--;
            }
        }
  
        // return the resultant string
        return r.toString();
    }
    
   public void updateDB()
   {
   Connection conn=ConnerDB();
     try{
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
         DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
         RecordTable.setRowCount(0);
         while(rs.next()){
         Vector columnData=new Vector();
         columnData.add(rs.getInt(1));
         columnData.add(rs.getString(2));
         columnData.add(rs.getString(4));
         RecordTable.addRow(columnData);
         } 
         rs.close();
     }catch(Exception e){
         e.printStackTrace();
     }    
   }
   public void disabledTable(){
   jTextField2.setText("");
   Connection conn=ConnerDB();
   jTextField2.setEditable(false);
        jComboBox2.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(true);
        jLabel14.setText(null);
     try{
         DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
    int selectedRow=jTable1.getSelectedRow();
   Object found=RecordTable.getValueAt(selectedRow, 0);
   String f=found.toString();
   System.out.println(f);
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select * from pays where pays_student_id="+f+"and (select student_term from student where student_id="+f+")=(select course_term from course where course_code=pays_course_code)");
         DefaultTableModel RecordTable1=(DefaultTableModel)jTable2.getModel();
         RecordTable1.setRowCount(0);
         while(rs.next()){
         Vector columnData=new Vector();
         columnData.add(rs.getInt(1));
         columnData.add(rs.getString(3));
         columnData.add(rs.getString(4));
         columnData.add(rs.getString(5));
         try{
         Statement st1=conn.createStatement();
         String s="SELECT * FROM PAYMENT WHERE PAYMENT_ID="+rs.getString(1);
         ResultSet rs1=st1.executeQuery(s);
         rs1.next();
         columnData.add(rs1.getString(2));
         columnData.add(rs1.getDate(3));
         }catch(Exception e){
         e.printStackTrace();
     }
         RecordTable1.addRow(columnData);
         } 
         rs.close();
     }catch(Exception e){
         e.printStackTrace();
     }
   }
   
   public void enabledtable(){
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yy");  
   LocalDateTime now = LocalDateTime.now();  
   String date=dtf.format(now); 
       jTextField2.setEditable(true);
        jComboBox2.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(false);
        jLabel14.setText(date);
   DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
   DefaultTableModel RecordTable1=(DefaultTableModel)jTable2.getModel();
   Connection conn=ConnerDB();
   int selectedRow=jTable1.getSelectedRow();
   Object found=RecordTable.getValueAt(selectedRow, 0);
   String f=found.toString();
   System.out.println(f);
   String sql1="SELECT *FROM ENROLL WHERE E_STUDENT_ID="+f;
   RecordTable1.setRowCount(0);
     try{
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery(sql1);
         while(rs.next()){
         Vector columnData=new Vector();
         columnData.add("NULL");
         columnData.add(rs.getString(1));
         
         try{
         Statement st1=conn.createStatement();
         String s="SELECT COURSE_COST FROM COURSE WHERE COURSE_CODE='"+rs.getString(1)+"'";
         ResultSet rs1=st1.executeQuery(s);
         rs1.next();
         columnData.add(rs1.getString(1));
         }catch(Exception e){
         e.printStackTrace();
     }
         columnData.add("UNPAID");
         columnData.add("UNPAID");
         columnData.add("NULL");
         RecordTable1.addRow(columnData); 
         }
         rs.close();
    }catch(Exception e){
         e.printStackTrace();
     }
   }
   
   public int dontupdateST(){
   DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
   Connection conn=ConnerDB();
   int selectedRow=jTable1.getSelectedRow();
   Object found=RecordTable.getValueAt(selectedRow, 0);
   String f=found.toString();
   System.out.println(f);
   String sql1="SELECT count(*) FROM PAYS WHERE PAYS_STUDENT_ID="+f;
   sql1=sql1+" AND (PAYS_COURSE_CODE=";
     String sql="SELECT *FROM ENROLL WHERE E_STUDENT_ID="+f;
   try{
   Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery(sql);
         while(rs.next()){
         System.out.println(rs.getString(1));
         sql1=sql1+" '"+rs.getString(1)+"' OR PAYS_COURSE_CODE=";
         }

    firstWords = sql1.substring(0, sql1.lastIndexOf(" "));
    secondWords=firstWords.substring(0, firstWords.lastIndexOf(" "));
    secondWords=secondWords+")";
    System.out.println(secondWords);
   }catch(Exception e){
         e.printStackTrace();
     }
   //String sql1="SELECT count(*) FROM PAYS WHERE PAYS_STUDENT_ID=201914045 AND PAYS_COURSE_CODE='CSE-101'";
     try{
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery(secondWords);
         rs.next();
         System.out.println(rs.getInt(1));
         int logic=rs.getInt(1);
         rs.close();
         if(logic==0) return 0;
         else return 1;
    }catch(Exception e){
         e.printStackTrace();
     }
     return -1;
   }

    /**
     * Creates new form BANK
     */
    public ACCOUNT() {
                try{Connection con=ConnerDB();
        con.commit();}catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        initComponents();
        updateDB();
        jTextField2.setEditable(false);
        jComboBox2.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jLabel14.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BANK");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAME" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jComboBox1))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NAME:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 13, 128, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 16, 280, 32));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ID:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 58, 35));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 237, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("DEPERTMENT:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 59, 161, 41));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 198, 38));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("TYPE: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 70, 27));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 150, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("GENDER:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 120, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 970, 200));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NAME", "DEPT"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 110, 410, 590));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "pay_id", "course code", "amount", "status", "PAYMENT TYPE", "PAYMENT_DATE"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 210));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 970, 210));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("PAYMENT_ID:");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 22, 154, 48));
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 22, 213, 48));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("PAYMENT_DATE:");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 76, -1, 42));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("null");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 213, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("PAYMENT_TYPE:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 160, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CHECK", "CREDITCARD", "ONLINE" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 90, 30));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 560, 170));

        jButton2.setText("ADD PAYMENT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 550, 210, 40));

        jButton3.setText("DELETE PAYMENT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 600, 210, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
        Connection conn=ConnerDB();
        if(jComboBox1.getSelectedIndex()==0){
        try {
            String sql = "SELECT * from student  WHERE STUDENT_ID like '%"+jTextField1.getText()+"%'";
            System.out.println(sql);
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            RecordTable.setRowCount(0);
            
         while(rs.next()){
         Vector columnData=new Vector();
         columnData.add(rs.getInt(1));
         columnData.add(rs.getString(2));
         columnData.add(rs.getString(4));
         RecordTable.addRow(columnData);
         System.out.println(rs.next());
         }
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
        jTextField1.setText("");
        }
        if(jComboBox1.getSelectedIndex()==1){
        try {
            String sql = "SELECT * from student  WHERE STUDENT_NAME like '%"+jTextField1.getText()+"%'";
            System.out.println(sql);
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            RecordTable.setRowCount(0);
            
         while(rs.next()){
         Vector columnData=new Vector();
         columnData.add(rs.getInt(1));
         columnData.add(rs.getString(2));
         columnData.add(rs.getString(4));
         RecordTable.addRow(columnData);
         System.out.println(rs.next());
         }
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
        jTextField1.setText("");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
       int selectedRow=jTable1.getSelectedRow();
               Connection conn = ConnerDB();
        try {
            String sql = "select * from student where STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(selectedRow, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
            jLabel5.setText(Integer.toString(rs.getInt(1)));
            jLabel3.setText(rs.getString(2));
            jLabel9.setText(rs.getString(3));
            jLabel7.setText(rs.getString(4));
            jLabel11.setText(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
        if(dontupdateST()==1){
        disabledTable();
        }
    if(dontupdateST()==0){
    enabledtable();
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         Connection conn = ConnerDB();
       if("".equals(jTextField2.getText())){
       JOptionPane.showMessageDialog(null, "Can not leave textField empty!");
       return;
       };
               try {
            String sql = "select PAYMENT_ID from PAYMENT where PAYMENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1,jTextField2.getText());
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
            JOptionPane.showMessageDialog(null, "payment id already exists!");
            return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yy");  
       LocalDateTime now = LocalDateTime.now();  
        String date=dtf.format(now); 
        DefaultTableModel RecordTable1=(DefaultTableModel)jTable2.getModel();
        System.out.println(RecordTable1. getRowCount());
        System.out.println(RecordTable1. getColumnCount());
        for(int i=0;i<RecordTable1.getRowCount();i++){
        System.out.println(RecordTable1.getValueAt(i,1));
        }
        
       
        try{
        String sql = "INSERT INTO PAYMENT VALUES(?,?,?)";
        PreparedStatement pst = null;
        pst = conn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(jTextField2.getText()));
        pst.setString(2,jComboBox2.getSelectedItem().toString());
        pst.setString(3,date);
        pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        }
        for(int i=0;i<RecordTable1.getRowCount();i++){
        System.out.println(RecordTable1.getValueAt(i,1));
       try{
       String sql="INSERT INTO PAYS VALUES(?,?,?,?,?)";
       PreparedStatement pst = null;
       pst = conn.prepareStatement(sql);
       pst.setInt(1, Integer.parseInt(jTextField2.getText()));
       pst.setInt(2, Integer.parseInt(jLabel5.getText()));
       pst.setString(3, RecordTable1.getValueAt(i,1).toString());
       pst.setString(4, RecordTable1.getValueAt(i,2).toString());
       pst.setString(5, "PAID");
       pst.executeUpdate();
       }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        }
        }
        disabledTable();
        JOptionPane.showMessageDialog(null, "Payment added");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
DefaultTableModel RecordTable=(DefaultTableModel)jTable2.getModel();
DefaultTableModel RecordTable1=(DefaultTableModel)jTable1.getModel();
            Connection conn = ConnerDB();
            try {
            String sql = "DELETE FROM PAYS WHERE PAYS_PAYMENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(0, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
            
            
        try {
            String sql = "DELETE FROM PAYMENT WHERE PAYMENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(0, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
        
        try {
            String sql = "DELETE FROM ATTEND WHERE C_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable1.getValueAt(0, 0);
            int Search=(int) found;
            System.out.println("seradsfasdf");
            System.out.println(Search);
            System.out.println("seradsfasdf");
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
        enabledtable();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACCOUNT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
