/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import Model.barangModel;
import Model.barangModel.lokasiModel;
import Model.barangModel.merkModel;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author HPrmn
 */
public interface barangInterface {
    void insert(barangModel bm);
    void update(barangModel bm);
    void delete(barangModel bm);
    void autoID(barangModel bm);
    void getMerk(JComboBox gm);
    void getLokasi(JComboBox gl);
    Integer getkodeMerk(String nama);
    Integer getkodeLokasi(String nama);
    List<barangModel> getAll();
    List<barangModel> getAll1();
    List<barangModel> getcariBarang(String cari);
    List<barangModel> getcariBarangSebagian(String cari);
    
    public interface merkInterface{
        void insert(merkModel mm);
        void update(merkModel mm);
        void delete(merkModel mm);
        List<merkModel> getAll();
    }
    
    public interface lokasiInterface{
        void insert(lokasiModel lm);
        void update(lokasiModel lm);
        void delete(lokasiModel lm);
        List<lokasiModel> getAll();
    }
    
}
