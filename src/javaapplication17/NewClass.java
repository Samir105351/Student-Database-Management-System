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
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication17.JavaApplication17.ConnerDB;
import javax.swing.JOptionPane;
import oracle.net.aso.e;

/**
 *
 * @author Tahsin Mubarak
 */
public class NewClass {
    public static Connection ConnerDB(){
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system1","1234");
        return con;
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,e);
        return null;
    }
}
        public static void main(String args[]){
     Connection conn=ConnerDB();
     try{
         String sql="insert into student  values (?)";
         PreparedStatement pst=null;
         pst=conn.prepareStatement(sql);
         pst.setFloat(1, (float) 3.29);
         pst.executeUpdate();
     }  catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
