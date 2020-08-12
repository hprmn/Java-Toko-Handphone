/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import DAO.Interface.pegawaiInterface;
import DAO.Implement.barangImplement;
import DAO.Implement.pegawaiImplement;
import DAO.Interface.barangInterface;
import DAO.Interface.pegawaiInterface.jabatanInterface;
import DAO.Implement.pegawaiImplement.jabatanImplement;
import DAO.Interface.barangInterface.lokasiInterface;
import DAO.Interface.barangInterface.merkInterface;
import DAO.Implement.barangImplement.merkImplement;
import DAO.Implement.transaksiImplement;
import DAO.Implement.userImplement;
import DAO.Interface.transaksiInterface;
import DAO.Interface.userInterface;
import View.awalView;
import View.loginView;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HPrmn
 */
public class koneksi {
    private static awalView av;
    private static String driver,database,host,tes,port,username,password,setnama;
    private static Properties properti;
    private static barangInterface barangInterface;
    private static merkInterface merkInterface;
    private static lokasiInterface lokasiInterface;
    private static pegawaiInterface pegawaiInterface;
    private static jabatanInterface jabatanInterface;
    private static userInterface userInterface;
    private static transaksiInterface transaksiInterface;
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
    
    public static Connection getConnection(){
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
            
        }catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        
        return conn;
    }
    public static pegawaiInterface getpegawaiInterface(){
        if(pegawaiInterface ==null){
            pegawaiInterface = new pegawaiImplement(getConnection());
        }
        return pegawaiInterface;
    }
    public static jabatanInterface getjabatanInterface(){
        if(jabatanInterface ==null){
            pegawaiImplement pi = new pegawaiImplement();
            jabatanInterface = pi.new jabatanImplement(getConnection());
        }
        return jabatanInterface;
    }
    public static barangInterface getbarangInterface(){
        if(barangInterface ==null){
            barangInterface = new barangImplement(getConnection());
        }
        return barangInterface;
    }
    public static merkInterface getmerkInterface(){
        if(merkInterface==null){
            barangImplement bi = new barangImplement();
            merkInterface = bi.new merkImplement(getConnection());
        }
        return merkInterface;
    }
    public static lokasiInterface getlokasiInterface(){
        if(lokasiInterface ==null){
            barangImplement bi = new barangImplement();
            lokasiInterface = bi.new lokasiImplement(getConnection());
        }
        return lokasiInterface;
    }
    public static userInterface getUserInterface(){
        if(userInterface==null){
            userInterface = new userImplement(getConnection());
        }
        return userInterface;
    }

    public static transaksiInterface getTransaksiInterface(){
        if(transaksiInterface==null){
            transaksiInterface = new transaksiImplement(getConnection());
        }
        return transaksiInterface;
    }
    
    public static void main (String[] args){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat percobaan = new SimpleDateFormat("yyyy");
        System.out.println(percobaan.format(cal.getTime()));
    }
}
