/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class Main_page extends javax.swing.JFrame {

    /**
     * Creates new form Student_module
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
    public Main_page() {
        initComponents();
    }
    public void open_student(String p){
    Student_module s=new Student_module();
    s.primary_key(p);
    s.setVisible(true);
    dispose();
    }
    
    public void open_faculty(String p){
    faculty_module f=new faculty_module();
    f.primary_key(p);
    f.setVisible(true);
    dispose();
    
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STUDENT DATABASE MANAGEMENT SYSTEM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 1360, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("login");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 240, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Password:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Username:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 110, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 160, 40));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 160, 40));

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 160, 50));

        jButton2.setText("forgot");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "student", "faculty", "bank", "manager" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 160, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1360, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    
    Connection conn=ConnerDB();
    if(jComboBox1.getSelectedIndex()==0){
         int flag=0;
         String user="";
         try{
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("SELECT STUDENT_ID,STUDENT_PASSWORD,STUDENT_BACKUP FROM STUDENT");
         while(rs.next()){
         System.out.println(rs.getString(1));
         System.out.println(rs.getString(2));
         System.out.println(rs.getString(3));
         if((jTextField2.getText().equals(rs.getString(1)))&&((jTextField1.getText().equals(rs.getString(2)))||(jTextField1.getText().equals(rs.getString(3))))){
         System.out.println("nice");
         flag=1;
         user=rs.getString(1);
         break;
         }
         else{System.out.println("not nice");}
         } 
         rs.close();
         if(flag==0){
         JOptionPane.showMessageDialog(null, "Unable to find");
         }
         if(flag==1){
         open_student(user);
         }
     }catch(Exception e){
         e.printStackTrace();
     }
    }
        if(jComboBox1.getSelectedIndex()==1){
         try{
         int flag=0;
         String user="";
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("SELECT FACULTY_ID,FACULTY_PASSWORD,FACULTY_BACKUP FROM FACULTY");
         while(rs.next()){
         System.out.println(rs.getString(1));
         System.out.println(rs.getString(2));
         System.out.println(rs.getString(3));
         if((jTextField2.getText().equals(rs.getString(1)))&&((jTextField1.getText().equals(rs.getString(2)))||(jTextField1.getText().equals(rs.getString(3))))){
         System.out.println("nice");
         flag=1;
         user=rs.getString(1);
         break;
         }
         else{System.out.println("not nice");}
         } 
         rs.close();
         if(flag==0){
         JOptionPane.showMessageDialog(null, "Unable to find");
         }
         if(flag==1){
         open_faculty(user);
         }
     }catch(Exception e){
         e.printStackTrace();
     }
    }
        if(jComboBox1.getSelectedIndex()==2){
        if(jTextField2.getText().equals("BANK")&&jTextField1.getText().equals("1234")){
        ACCOUNT A=new ACCOUNT();
        A.setVisible(true);
    dispose();
        }
        }
                if(jComboBox1.getSelectedIndex()==3){
        if(jTextField2.getText().equals("MANAGER")&&jTextField1.getText().equals("1234")){
        MANAGER M=new MANAGER();
        M.setVisible(true);
    dispose();
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
