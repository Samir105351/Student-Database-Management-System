/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication17.JavaApplication17.ConnerDB;
import static javaapplication17.NewClass.ConnerDB;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tahsin Mubarak
 */
public class StudentEditor extends javax.swing.JFrame {

    /**
     * Creates new form StudentEditor
     */
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
   public boolean copychecker(){
       Vector enroll=new Vector();
       Vector common=new Vector();
       Connection conn=ConnerDB();
       
   try{
   String Data=jTextField3.getText();
   String sql="SELECT E_COURSE_CODE FROM ENROLL WHERE E_STUDENT_ID="+Data;
   Statement st=conn.createStatement();
   ResultSet rs=st.executeQuery(sql);
 
   while(rs.next()){
        System.out.println(rs.getString(1));
         enroll.add(rs.getString(1));
   }
   rs.close();
   }catch(Exception e){
         e.printStackTrace();
   }
   
   try{
   String Data=jTextField3.getText();
   String sql="SELECT COURSE_CODE FROM COURSE WHERE COURSE_TERM=(SELECT STUDENT_TERM FROM student WHERE STUDENT_ID="+Data+") AND COURSE_LEVEL=(SELECT STUDENT_LEVEL FROM student WHERE STUDENT_ID="+Data+")";
   Statement st=conn.createStatement();
   ResultSet rs=st.executeQuery(sql);
   while(rs.next()){
        System.out.println(rs.getString(1));
         common.add(rs.getString(1));
   }
   rs.close();
   }catch(Exception e){
         e.printStackTrace();
   }
   Collections.sort(enroll);
   Collections.sort(common);
   System.out.println(enroll.size());
   System.out.println(enroll.size());
   System.out.println(enroll.equals(common));
   return enroll.equals(common);
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
         columnData.add(rs.getString(3));
         columnData.add(rs.getString(4));
         columnData.add(rs.getString(5));
         RecordTable.addRow(columnData);
         } 
         rs.close();
     }catch(Exception e){
         e.printStackTrace();
     }    
   }
   
   public void enroll(){
   Connection conn=ConnerDB();
     try{
     CallableStatement myStmt=null;
     myStmt=conn.prepareCall("{call ADDENROLL(?)}");
     myStmt.setInt(1,Integer.parseInt(jTextField3.getText()));
     myStmt.execute();
     }catch(Exception e){
         e.printStackTrace();
     }
   }
   
   public void updateEnroll(){
   if(copychecker()){
   return;
   }
   Connection conn1=ConnerDB();
   try {
            String sql = "DELETE FROM ATTEND WHERE C_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn1.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(jTextField3.getText()));
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
   
    try {
            String sql = "DELETE FROM ENROLL where E_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn1.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(jTextField3.getText()));
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }  
     enroll();
   }
        
    public StudentEditor() {
        try{Connection con=ConnerDB();
        con.commit();}catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        }        
        
        initComponents();
        updateDB();
        jTextField10.setText("123456#");
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
        jLabel6 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 0, 0), null, null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Student Record");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 960, 40));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAME" }));
        jPanel1.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 80, 40));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 160, 40));

        jButton3.setText("SEARCH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 20, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 70));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 0, 0), null, null));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Type", "Dept", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 550));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 730, 550));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 0, 0), null, null));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 13, -1, 40));

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 40));

        jButton4.setText("Delete All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 90, 40));

        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 90, 40));

        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 110, 40));

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 40));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 1360, 100));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 3));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Student Name:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 210, 30));
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 350, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Student ID:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 210, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 40, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Student Type:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 210, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REGULAR", "IRREGULAR" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 350, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Dept:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 210, 30));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE", "EECE", "AE", "CE", "ME", "EWCE", "BME", "PME", "NAME", "ARCH", "NSE" }));
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 350, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Gender:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 210, 30));
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 350, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("EMAIL:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 210, 30));
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 350, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("FATHER'S NAME:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 210, 30));
        jPanel4.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 350, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("MOTHER'S NAME:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 210, 30));
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 230, 30));
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("DATE OF BIRTH:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 210, 30));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 350, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("YEAR:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 60, 30));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DHAKA", "KHULNA", "RAJSHAHI", "BARISHAL", "CHITAGONG", "SYLHET", "RANGPUR", "KUMILLA" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("TERM:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 70, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("NATIONALITY:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 730, 220, 30));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FALL", "SPRING" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 60, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("BOARD:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 80, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("RESULT:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 80, 30));

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 650, 290, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("SSC:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 60, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("BOARD:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 80, 30));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DHAKA", "KHULNA", "RAJSHAHI", "BARISHAL", "CHITAGONG", "SYLHET", "RANGPUR", "KUMILLA" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 80, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("RESULT:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 80, 30));
        jPanel4.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 70, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("YEAR:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 60, 30));
        jPanel4.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 80, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("PHONE:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 80, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("HSC:");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 60, 30));
        jPanel4.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 70, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("PRESENT ADDRESS:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 190, 30));

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, 290, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("LEVEL:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 70, 30));
        jPanel4.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 350, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("PARMANANT ADDRESS:");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 220, 30));

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, 290, 30));
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 870, 580, 70));

        jDateChooser1.setDateFormatString("dd-MMM-yy");
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 350, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("SET PASSWORD:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 220, 30));

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 690, 290, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("SET PASSWORD:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 220, 30));

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 730, 290, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("RELIGION:");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 220, 30));

        jScrollPane2.setViewportView(jPanel4);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 560));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1360, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        updateDB();
        jTextField2.setText("");
            jTextField3.setText("");
            jComboBox2.setSelectedItem("REGULAR");
            jComboBox3.setSelectedItem("CSE");
            jComboBox4.setSelectedItem("MALE");
            jTextField4.setText("");
            jTextField6.setText("");
            jTextField5.setText("");
            jTextField15.setText("");
            jDateChooser1.setDate(null);
            jComboBox6.setSelectedItem("FALL");
            jComboBox1.setSelectedItem("1");
            jComboBox7.setSelectedItem("DHAKA");
            jTextField11.setText("");
            jTextField12.setText("");
            jComboBox5.setSelectedItem("DHAKA");
            jTextField13.setText("");
            jTextField8.setText("");
            jTextField14.setText("");
            jTextField16.setText("");
            jTextField10.setText("");
            jTextField18.setText("");
            jTextField19.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MANAGER M=new MANAGER();
        M.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Connection conn = ConnerDB();
        try {
            String sql = "insert into student  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(jTextField3.getText()));
            pst.setString(2, jTextField2.getText());
            pst.setString(3, (String) jComboBox2.getSelectedItem());
            pst.setString(4, (String) jComboBox3.getSelectedItem());
            pst.setString(5, (String) jComboBox4.getSelectedItem());
            pst.setString(6, jTextField4.getText());
            pst.setString(7, jTextField6.getText());
            pst.setString(8, jTextField5.getText());
            pst.setString(9, jTextField15.getText());
            pst.setString(10, ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            pst.setString(11, (String) jComboBox6.getSelectedItem());
            pst.setInt(12, Integer.parseInt((String) jComboBox1.getSelectedItem()));
            pst.setString(13, (String) jComboBox7.getSelectedItem());
            pst.setFloat(14, Float.parseFloat(jTextField11.getText()));
            pst.setInt(15, Integer.parseInt(jTextField12.getText()));
            pst.setString(16, (String) jComboBox5.getSelectedItem());
            pst.setFloat(17, Float.parseFloat(jTextField13.getText()));
            pst.setInt(18, Integer.parseInt(jTextField8.getText()));
            pst.setString(19, jTextField14.getText());
            pst.setString(20, jTextField16.getText());
            pst.setString(21, jTextField10.getText());
            pst.setString(22, getAlphaNumericString(7));
            pst.setString(23, jTextField18.getText());
            pst.setString(24, jTextField19.getText());
            pst.executeUpdate();
            enroll();
            updateDB();
            JOptionPane.showMessageDialog(null, "Added Successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
       int selectedRow=jTable1.getSelectedRow();
        Connection conn = ConnerDB();
        try {
            String sql = "update student set STUDENT_ID=?,STUDENT_NAME=?,STUDENT_TYPE=?,STUDENT_DEPT=?,STUDENT_GENDER=?,STUDENT_EMAIL=?,STUDENT_PHONE=?,STUDENT_FATHER=?,STUDENT_MOTHER=?,STUDENT_BIRTH=?,STUDENT_TERM=?,STUDENT_LEVEL=?,SSC_BOARD=?,SSC_RESULT=?,SSC_YEAR=?,HSC_BOARD=?,HSC_RESULT=?,HSC_YEAR=?,STUDENT_PRESENTAD=?,STUDENT_PERMANANT=?,STUDENT_PASSWORD=?,STUDENT_RELIGION=?,STUDENT_NATIONALITY=? where STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(selectedRow, 0);
            int Search=(int) found;
            pst.setInt(1, Integer.parseInt(jTextField3.getText()));
            pst.setString(2, jTextField2.getText());
            pst.setString(3, (String) jComboBox2.getSelectedItem());
            pst.setString(4, (String) jComboBox3.getSelectedItem());
            pst.setString(5, (String) jComboBox4.getSelectedItem());
            pst.setString(6, jTextField4.getText());
            pst.setString(7, jTextField6.getText());
            pst.setString(8, jTextField5.getText());
            pst.setString(9, jTextField15.getText());
            pst.setString(10, ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            pst.setString(11, (String) jComboBox6.getSelectedItem());
            pst.setInt(12, Integer.parseInt((String) jComboBox1.getSelectedItem()));
            pst.setString(13, (String) jComboBox7.getSelectedItem());
            pst.setFloat(14, Float.parseFloat(jTextField11.getText()));
            pst.setInt(15, Integer.parseInt(jTextField12.getText()));
            pst.setString(16, (String) jComboBox5.getSelectedItem());
            pst.setFloat(17, Float.parseFloat(jTextField13.getText()));
            pst.setInt(18, Integer.parseInt(jTextField8.getText()));
            pst.setString(19, jTextField14.getText());
            pst.setString(20, jTextField16.getText());
            pst.setString(21, jTextField10.getText());
            pst.setString(22, jTextField18.getText());
            pst.setString(23, jTextField19.getText());
            pst.setInt(24, Search);
            pst.executeUpdate();
            updateDB();
             updateEnroll();
            JOptionPane.showMessageDialog(null, "Added Successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add");
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);  
        } 
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
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
            jTextField3.setText(Integer.toString(rs.getInt(1)));
            jTextField2.setText(rs.getString(2));
            jComboBox2.setSelectedItem(rs.getString(3));
            jComboBox3.setSelectedItem(rs.getString(4));
            jComboBox4.setSelectedItem(rs.getString(5));
            jTextField4.setText(rs.getString(6));
            jTextField6.setText(rs.getString(7));
            jTextField5.setText(rs.getString(8));
            jTextField15.setText(rs.getString(9));
            jDateChooser1.setDate(rs.getDate(10));
            jComboBox6.setSelectedItem(rs.getString(11));
            jComboBox1.setSelectedItem(rs.getString(12));
            jComboBox7.setSelectedItem(rs.getString(13));
            jTextField11.setText(Float.toString(rs.getFloat(14)));
            jTextField12.setText(Integer.toString(rs.getInt(15)));
            jComboBox5.setSelectedItem(rs.getString(16));
            jTextField13.setText(Float.toString(rs.getFloat(17)));
            jTextField8.setText(Integer.toString(rs.getInt(18)));
            jTextField14.setText(rs.getString(19));
            jTextField16.setText(rs.getString(20));
            jTextField10.setText(rs.getString(21));
            jTextField18.setText(rs.getString(23));
            jTextField19.setText(rs.getString(24));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

            Connection conn = ConnerDB();
            /*try {
            String sql = "DELETE FROM ATTEND WHERE C_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(jTextField3.getText()));
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }
            
            
            try {
            String sql = "DELETE FROM PAYS where PAYS_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(selectedRow, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }   
         
        try {
            String sql = "DELETE FROM ENROLL where E_STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(selectedRow, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }    
            
        try {
            String sql = "DELETE FROM student where STUDENT_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            Object found=RecordTable.getValueAt(selectedRow, 0);
            int Search=(int) found;
            pst.setInt(1,Search);
            ResultSet rs=pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to find");
        }*/
      try{
     CallableStatement myStmt=null;
     myStmt=conn.prepareCall("{call deletestu(?)}");
     myStmt.setInt(1,Integer.parseInt(jTextField3.getText()));
     myStmt.execute();
     }catch(Exception e){
         e.printStackTrace();
     }
                    
            jTextField2.setText("");
            jTextField3.setText("");
            jComboBox2.setSelectedItem("REGULAR");
            jComboBox3.setSelectedItem("CSE");
            jComboBox4.setSelectedItem("MALE");
            jTextField4.setText("");
            jTextField6.setText("");
            jTextField5.setText("");
            jTextField15.setText("");
            jDateChooser1.setDate(null);
            jComboBox6.setSelectedItem("FALL");
            jComboBox1.setSelectedItem("1");
            jComboBox7.setSelectedItem("DHAKA");
            jTextField11.setText("");
            jTextField12.setText("");
            jComboBox5.setSelectedItem("DHAKA");
            jTextField13.setText("");
            jTextField8.setText("");
            jTextField14.setText("");
            jTextField16.setText("");
            jTextField10.setText("123456#");
            jTextField18.setText("");
            jTextField19.setText("");
            updateDB();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
        Connection conn=ConnerDB();
        if(jComboBox8.getSelectedIndex()==0){
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
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
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
        if(jComboBox8.getSelectedIndex()==1){
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
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
     Connection conn=ConnerDB();
     try{
     CallableStatement myStmt=null;
     myStmt=conn.prepareCall("{call DeleteaAllStu}");
     myStmt.execute();
     }catch(Exception e){
         e.printStackTrace();
     }
     updateDB();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
