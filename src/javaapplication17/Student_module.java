/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tahsin Mubarak
 */
public class Student_module extends javax.swing.JFrame {
    static String primary_key="";
    static String firstWords;
static String secondWords;
    /**
     * Creates new form Student_module
     */
    public Student_module() {
        initComponents();
        //Label_Set();
        //Table_mode();
        //Bottom_Set();
    }
    
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
                    
    public void Label_Set(){
    Connection conn=ConnerDB();
    try{
    Statement st=conn.createStatement();
    String sql="SELECT STUDENT_NAME FROM STUDENT WHERE STUDENT_ID="+primary_key;
    ResultSet rs=st.executeQuery(sql);
    rs.next();
    jLabel1.setText("welcome, "+rs.getString(1));
    }catch(Exception e){
         e.printStackTrace();
     }
    try{
    Statement st=conn.createStatement();
    String sql="SELECT SUM(COURSE_CREDIT) FROM COURSE WHERE COURSE_TERM=(SELECT STUDENT_TERM FROM student WHERE STUDENT_ID="+primary_key+") AND COURSE_LEVEL=(SELECT STUDENT_LEVEL FROM student WHERE STUDENT_ID="+primary_key+")";
    ResultSet rs=st.executeQuery(sql);
    rs.next();
    jLabel5.setText("Total Credit_hr, "+rs.getString(1));
    }catch(Exception e){
         e.printStackTrace();
     }
    }
            
    public void primary_key(String str){
    primary_key=str;
    Label_Set();
    Table_mode();
    Bottom_Set();
    }
    public int table_attend(){
    try{
    Connection conn=ConnerDB();
    String sql="SELECT count(*) FROM ATTEND WHERE C_STUDENT_ID="+primary_key;
    Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery(sql);
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
    
    
    
    
    void Table_mode(){ 
    String sql="SELECT E_COURSE_CODE FROM ENROLL WHERE E_STUDENT_ID="+primary_key;
    DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
    RecordTable.setRowCount(0);
    try{
         Connection conn=ConnerDB();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery(sql);
         while(rs.next()){
         Vector columnData=new Vector();
         
         try{
         Statement st1=conn.createStatement();
         String s="SELECT COURSE_CODE,COURSE_NAME,COURSE_CREDIT FROM COURSE WHERE COURSE_CODE='"+rs.getString(1)+"'";
         ResultSet rs1=st1.executeQuery(s);
         rs1.next();
         columnData.add(rs1.getString(1));
         columnData.add(rs1.getString(2));
         columnData.add(rs1.getString(3));
         }catch(Exception e){
         e.printStackTrace();
     }
         RecordTable.addRow(columnData); 
         }
         rs.close();
    }catch(Exception e){
         e.printStackTrace();
     }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public int dontupdateST(){
   Connection conn=ConnerDB();
   System.out.println(primary_key);
   String sql1="SELECT count(*) FROM PAYS WHERE PAYS_STUDENT_ID="+primary_key;
   sql1=sql1+" AND (PAYS_COURSE_CODE=";
     String sql="SELECT *FROM ENROLL WHERE E_STUDENT_ID="+primary_key;
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
    
    public void Bottom_Set(){
    if(table_attend()==1){
    jLabel3.setVisible(false);
    jButton1.setVisible(false); 
    jLabel4.setText("you are currently registered");
    jButton2.setVisible(true);
    return;
    }
    if(dontupdateST()==0){
    jLabel3.setVisible(false);
    jButton1.setVisible(false); 
    jLabel4.setText("You have yet to complete your payment");
    jButton2.setVisible(false);
    return;
    }
    jLabel3.setVisible(true);
    jButton1.setVisible(true); 
    jLabel4.setText("to complete your registration");
    jButton2.setVisible(false);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1350, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1350, 70));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("STUDENT DATABASE, STUDENT RECORD");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 1370, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 110));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "course_code", "course_name", "credit_hour"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 330));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("ENTER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PRESS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1350, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setText("check attendence");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("RESET PASSWORD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("ABOUT YOU");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(655, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 1340, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1350, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection conn=ConnerDB();
        try{
        Statement st =conn.createStatement();
        String sql="SELECT CLASS_NO FROM CLASS WHERE (select student_term from student where student_id="+primary_key+")=(select course_term from course where course_code=CLASS_course_code)";
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){
        try{
        String sql1="INSERT INTO ATTEND VALUES (?,?,?)";
           PreparedStatement pst1 = null;
           pst1 = conn.prepareStatement(sql1);
           pst1.setString(1,rs.getString(1));
           pst1.setString(2,primary_key);
           pst1.setString(3,"PRESENT");
           pst1.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        }
        }
        catch(Exception e){
        e.printStackTrace();
        }
        Bottom_Set();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ATTENDENCE_CHECK A=new ATTENDENCE_CHECK();
        A.primary_key(primary_key);
        A.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        S_RESET S=new S_RESET();
        S.primary_key(primary_key);
        S.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        primary_key="201914045";
        
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
            java.util.logging.Logger.getLogger(Student_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student_module().setVisible(true);
            }
        });
  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}