/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class konfigurasi {

    public void edit(String nama, String nama1) {
        try {
            FileInputStream input = new FileInputStream("lib/database.ini");
            Properties prop = new Properties();
            prop.load(input);
            input.close();

            FileOutputStream output = new FileOutputStream("lib/database.ini");
            prop.setProperty(nama, nama1);
            prop.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(konfigurasi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(konfigurasi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
