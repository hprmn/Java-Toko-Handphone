/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class tesKoneksi {
    private static String driver,database,host,tes,port,username,password,setnama;
    private static Properties properti;
    private static Connection conn;
        public static String panel(String nama){
        try{
            properti = new Properties();
            properti.load(new FileInputStream("lib/database.ini"));
            setnama = properti.getProperty(nama);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
        }        
        return setnama;
    }
    
    public Connection getConnection(){
        driver = panel("DBDriver");
        tes = panel("DBTest");
        host = panel("DBHost");
        port = panel("DBPort");
        database = panel("DBDatabase");
        username = panel("DBUsername");
        password = panel("DBPassword");
        conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,username,password);
            System.out.println("Koneksi Berhasil");
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
            
        }catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        
        return conn;
    }

}
