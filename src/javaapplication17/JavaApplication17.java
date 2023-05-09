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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tahsin Mubarak
 */
public class JavaApplication17 {
static String firstWords;
static String secondWords;

public String datesetter(String fate){
   String yyyy="";
   String mm="";
   String dd="";
   String mmm="";
   for(int i=0;i<4;i++){
   yyyy+=fate.charAt(i);
   }
  System.out.println(yyyy);
     for(int i=5;i<7;i++){
   mm+=fate.charAt(i);
   }
   System.out.println(mm);
     for(int i=8;i<10;i++){
   dd+=fate.charAt(i);
   }
     System.out.println(dd);
   if(Integer.parseInt(mm)==1){
   mmm="Jan";
   }
   if(Integer.parseInt(mm)==2){
   mmm="Feb";
   }
   if(Integer.parseInt(mm)==3){
   mmm="Mar";
   }
   if(Integer.parseInt(mm)==4){
   mmm="Apr";
   }
   if(Integer.parseInt(mm)==5){
   mmm="May";
   }
   if(Integer.parseInt(mm)==6){
   mmm="Jun";
   }
   if(Integer.parseInt(mm)==7){
   mmm="Jul";
   }
   if(Integer.parseInt(mm)==8){
   mmm="Aug";
   }
   if(Integer.parseInt(mm)==9){
   mmm="Sep";
   }
   if(Integer.parseInt(mm)==10){
   mmm="Oct";
   }
   if(Integer.parseInt(mm)==11){
   mmm="Nov";
   }
   if(Integer.parseInt(mm)==10){
   mmm="Dec";
   }
   System.out.println(mmm);
   String date=dd+"-"+mmm+"-"+yyyy;
   System.out.println(date);   
return date;
}
    /**
     * @return 
     */
    public static Connection ConnerDB(){
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","student","1234");
        return con;
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,e);
        return null;
    }
        
}
    public static void main(String args[]){
   
    }
}