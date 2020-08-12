/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import Model.barangModel;
import Model.transaksiModel;
import java.util.List;

/**
 *
 * @author HPrmn
 */
public interface transaksiInterface {
    void autoKode(transaksiModel tm, String nama);
    void simpanDetail(transaksiModel tm);
    void simpan(transaksiModel tm);
    void updateStok (transaksiModel tm);
    Integer getStok (transaksiModel tm);
    List<transaksiModel> getAll();
    List<transaksiModel> cariKodePenjualan(String kode);
}
