/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import Model.pegawaiModel;
import Model.pegawaiModel.jabatanModel;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author HPrmn
 */
public interface pegawaiInterface {
    
    void insert(pegawaiModel pm);
    void update(pegawaiModel pm);
    void delete(pegawaiModel pm);
    void getjabatan(JComboBox nama);
    void AutoId(pegawaiModel pm);
    Integer getkodeJabatan(String nama);
    List<pegawaiModel> getAll();
    List<pegawaiModel> getSebagian();
    List<pegawaiModel> getcariPegawai(String cari);
    List<pegawaiModel> getcariSebagian(String cari);
    
    public interface jabatanInterface{
        void insert(jabatanModel jm);
        void update(jabatanModel jm);
        void delete(jabatanModel jm);
        List<jabatanModel> getAll();
    }
    
}
