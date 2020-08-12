/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implement;

import DAO.Interface.transaksiInterface;
import Model.transaksiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HPrmn
 */
public class transaksiImplement implements transaksiInterface {

    private Connection conn = null;
    PreparedStatement pst;
    ResultSet res;

    public transaksiImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void simpanDetail(transaksiModel tm) {
        String SQL = "insert into t_detail_penjualan (kode_penjualan,kode_barang,unit,harga,sub_total) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, tm.getKode_transaksi());
            pst.setString(2, tm.getKode_barang());
            pst.setInt(3, tm.getJumlah());
            pst.setDouble(4, tm.getHarga_jual());
            pst.setDouble(5, tm.getTotal());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void autoKode(transaksiModel tm, String nama) {
        String SQL = "select max(right(kode_penjualan,4)) from t_penjualan";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                if (res.first() == false) {
                    tm.setKode_transaksi(nama + "0001");
                } else {
                    res.last();
                    int auto = res.getInt(1) + 1;
                    String no = String.valueOf(auto);
                    int nolength = no.length();
                    for (int i = 0; i < 4 - nolength; i++) {
                        no = "0" + no;
                    }
                    tm.setKode_transaksi(nama + no);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void simpan(transaksiModel tm) {
        String SQL = "insert into t_penjualan (kode_penjualan,tanggal_penjualan,total_harga,tunai,kembali,nip) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, tm.getKode_transaksi());
            pst.setString(2, tm.getTanggal());
            pst.setDouble(3, tm.getGrandtotal());
            pst.setDouble(4, tm.getBayar());
            pst.setDouble(5, tm.getKembali());
            pst.setInt(6, Integer.valueOf(tm.getKasir()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void updateStok(transaksiModel tm) {
        String SQL="update t_barang set stok=? where kode_barang=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setInt(1, tm.getStok());
            pst.setString(2, tm.getKode_barang());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Integer getStok(transaksiModel tm) {
        Integer stok = null;
        String SQL = "select * from t_barang where kode_barang=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1,tm.getKode_barang());
            res = pst.executeQuery();
            if(res.next()){
                stok = res.getInt("stok");
            }
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stok;
    }

    @Override
    public List<transaksiModel> getAll() {
        String SQL ="select * from t_penjualan";
        List<transaksiModel> ltm=null;
        try {
            ltm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while(res.next()){
                transaksiModel tm = new transaksiModel();
                tm.setKasir(res.getString("nip"));
                tm.setKode_transaksi(res.getString("kode_penjualan"));
                tm.setTanggal(res.getString("tanggal_penjualan"));
                tm.setGrandtotal(res.getDouble("total_harga"));
                tm.setBayar(res.getDouble("tunai"));
                tm.setKembali(res.getDouble("kembali"));
                
                ltm.add(tm);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ltm;
    }

    @Override
    public List<transaksiModel> cariKodePenjualan(String kode) {
        String SQL = "select * from t_detail_penjualan join t_penjualan join t_barang on t_detail_penjualan.kode_penjualan = t_penjualan.kode_penjualan and t_detail_penjualan.kode_barang = t_barang.kode_barang where t_detail_penjualan.kode_penjualan like ?";
        List<transaksiModel> ltm = null;
        try {
            ltm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            pst.setString(1, "%"+kode+"%");
            res = pst.executeQuery();
            while(res.next()){
                transaksiModel tm = new transaksiModel();
                tm.setKode_transaksi(res.getString("t_detail_penjualan.kode_penjualan"));
                tm.setKode_barang(res.getString("t_detail_penjualan.kode_barang"));
                tm.setNama_barang(res.getString("nama_barang"));
                tm.setJumlah(res.getInt("unit"));
                tm.setHarga_jual(res.getDouble("harga"));
                tm.setTotal(res.getDouble("sub_total"));
                ltm.add(tm);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(transaksiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ltm;
    }

}
