/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implement;

import DAO.Interface.barangInterface;
import Model.barangModel;
import Model.barangModel.merkModel;
import Model.barangModel.lokasiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author HPrmn
 */
public class barangImplement implements barangInterface {

    private Connection conn = null;
    Statement st;
    PreparedStatement pst;
    ResultSet res;

    public barangImplement() {
    }

    public barangImplement(Connection conn) {
        this.conn = conn;
    }

    String insert = "insert into t_barang (kode_barang,nama_barang,stok,harga_beli"
            + ",harga_jual,kode_merk,kode_lokasi) values (?,?,?,?,?,?,?)";
    String update = "update t_barang set nama_barang=?,stok=?,harga_beli=?"
            + ",harga_jual=?,kode_merk=?,kode_lokasi=? where kode_barang=?";
    String delete = "delete from t_barang where kode_barang=?";
    String caribarang = "select * from t_barang inner join t_merk "
            + "inner join t_lokasi on t_barang.kode_merk = t_merk.kode_merk and "
            + "t_barang.kode_lokasi = t_lokasi.kode_lokasi where kode_barang like ? "
            + "or nama_barang like ? or nama_merk like ? or nama_lokasi like ? or stok like ?";

    @Override
    public void insert(barangModel bm) {
        try {
            pst = conn.prepareStatement(insert);
            pst.setString(1, bm.getKode_barang());
            pst.setString(2, bm.getNama_barang());
            pst.setInt(3, bm.getStok());
            pst.setDouble(4, bm.getHarga_beli());
            pst.setDouble(5, bm.getHarga_jual());
            pst.setString(6, bm.getMerk());
            pst.setString(7, bm.getLokasi());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(barangModel bm) {
        try {
            pst = conn.prepareStatement(update);
            pst.setString(1, bm.getNama_barang());
            pst.setInt(2, bm.getStok());
            pst.setDouble(3, bm.getHarga_beli());
            pst.setDouble(4, bm.getHarga_jual());
            pst.setString(5, bm.getMerk());
            pst.setString(6, bm.getLokasi());
            pst.setString(7, bm.getKode_barang());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(barangModel bm) {
        try {
            pst = conn.prepareStatement(delete);

            pst.setString(1, bm.getKode_barang());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<barangModel> getAll() {
        String SQL = "select * from t_barang inner join t_merk inner join t_lokasi "
                + "on t_barang.kode_merk=t_merk.kode_merk and t_barang.kode_lokasi="
                + "t_lokasi.kode_lokasi";
        //String SQL1="select * from t_barang";
        List<barangModel> lbm = null;
        try {
            lbm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                barangModel bm = new barangModel();

                bm.setKode_barang(res.getString("kode_barang"));
                bm.setNama_barang((res.getString("nama_barang")));
                bm.setMerk(res.getString("nama_merk"));
                bm.setStok(res.getInt("stok"));
                bm.setHarga_beli(res.getDouble("harga_beli"));
                bm.setHarga_jual(res.getDouble("harga_jual"));
                bm.setLokasi(res.getString("nama_lokasi"));

                lbm.add(bm);
            }

        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lbm;
    }

    @Override
    public List<barangModel> getcariBarang(String cari) {
        List<barangModel> lbm = null;
        try {
            lbm = new ArrayList<>();
            pst = conn.prepareStatement(caribarang);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            pst.setString(3, "%" + cari + "%");
            pst.setString(4, "%" + cari + "%");
            pst.setString(5, "%" + cari + "%");
            res = pst.executeQuery();

            while (res.next()) {
                barangModel bm = new barangModel();

                bm.setKode_barang(res.getString("kode_barang"));
                bm.setNama_barang((res.getString("nama_barang")));
                bm.setMerk(res.getString("nama_merk"));
                bm.setStok(res.getInt("stok"));
                bm.setHarga_beli(res.getDouble("harga_beli"));
                bm.setHarga_jual(res.getDouble("harga_jual"));
                bm.setLokasi(res.getString("nama_lokasi"));

                lbm.add(bm);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lbm;
    }

    @Override
    public void getMerk(JComboBox gm) {
        String SQL = "select * from t_merk";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                gm.addItem(res.getString("nama_merk"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getLokasi(JComboBox gl) {
        String SQL = "select * from t_lokasi";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                gl.addItem(res.getString("nama_lokasi"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Integer getkodeMerk(String nama) {
        Integer kode = null;
        String SQL = "select * from t_merk where nama_merk=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, nama);
            res = pst.executeQuery();
            if (res.next()) {
                kode = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kode;
    }

    @Override
    public Integer getkodeLokasi(String nama) {
        Integer kode = null;
        String SQL = "select * from t_lokasi where nama_lokasi=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, nama);
            res = pst.executeQuery();
            if (res.next()) {
                kode = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kode;
    }

    @Override
    public void autoID(barangModel bm) {
        String SQL = "select max(right(kode_barang,4)) from t_barang";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                if (res.first() == false) {
                    bm.setKode_barang("HP0001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                    for (int i = 0; i < 4 - noLong; i++) {
                        no = "0" + no;
                    }
                    bm.setKode_barang("HP" + no);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<barangModel> getAll1() {
        String SQL = "select * from t_barang inner join t_merk inner join t_lokasi "
                + "on t_barang.kode_merk=t_merk.kode_merk and t_barang.kode_lokasi="
                + "t_lokasi.kode_lokasi where not stok=0";
        //String SQL1="select * from t_barang";
        List<barangModel> lbm = null;
        try {
            lbm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                barangModel bm = new barangModel();

                bm.setKode_barang(res.getString("kode_barang"));
                bm.setNama_barang((res.getString("nama_barang")));
                bm.setMerk(res.getString("nama_merk"));
                bm.setStok(res.getInt("stok"));
                bm.setHarga_beli(res.getDouble("harga_beli"));
                bm.setHarga_jual(res.getDouble("harga_jual"));
                bm.setLokasi(res.getString("nama_lokasi"));

                lbm.add(bm);
            }

        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lbm;
    }

    @Override
    public List<barangModel> getcariBarangSebagian(String cari) {
        List<barangModel> lbm = null;
        String SQL = "select * from t_barang inner join t_merk "
                + "inner join t_lokasi on t_barang.kode_merk = t_merk.kode_merk and "
                + "t_barang.kode_lokasi = t_lokasi.kode_lokasi where not stok=0 and (kode_barang like ? "
                + "or nama_barang like ? or nama_merk like ? or nama_lokasi like ? or stok like ?)";
        try {
            lbm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            pst.setString(3, "%" + cari + "%");
            pst.setString(4, "%" + cari + "%");
            pst.setString(5, "%" + cari + "%");
            res = pst.executeQuery();
            while (res.next()) {
                barangModel bm = new barangModel();

                bm.setKode_barang(res.getString("kode_barang"));
                bm.setNama_barang((res.getString("nama_barang")));
                bm.setMerk(res.getString("nama_merk"));
                bm.setStok(res.getInt("stok"));
                bm.setHarga_beli(res.getDouble("harga_beli"));
                bm.setHarga_jual(res.getDouble("harga_jual"));
                bm.setLokasi(res.getString("nama_lokasi"));

                lbm.add(bm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lbm;
    }

    //mengimplementasikan Interface Merk
    public class merkImplement implements merkInterface {

        private final Connection conn;

        public merkImplement(Connection conn) {
            this.conn = conn;
        }

        @Override
        public void insert(merkModel mm) {
            try {
                String SQL = "insert into t_merk (nama_merk) values (?)";
                pst = conn.prepareStatement(SQL);
                pst.setString(1, mm.getNamaMerk());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public void update(merkModel mm) {
            String SQL = "update t_merk set nama_merk=? where kode_merk=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, mm.getNamaMerk());
                pst.setInt(2, mm.getKodeMerk());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void delete(merkModel mm) {
            String SQL = "delete from t_merk where kode_merk=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setInt(1, mm.getKodeMerk());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public List<merkModel> getAll() {
            String SQL = "select * from t_merk";
            List<merkModel> lmm = null;
            try {
                lmm = new ArrayList<>();
                pst = conn.prepareStatement(SQL);
                res = pst.executeQuery();
                while (res.next()) {
                    barangModel bm = new barangModel();
                    merkModel mm = bm.new merkModel();
                    mm.setKodeMerk(res.getInt("kode_merk"));
                    mm.setNamaMerk(res.getString("nama_merk"));
                    lmm.add(mm);
                }
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }

            return lmm;
        }

    }

    //
    public class lokasiImplement implements lokasiInterface {

        private final Connection conn;

        public lokasiImplement(Connection conn) {
            this.conn = conn;
        }

        @Override
        public void insert(lokasiModel lm) {
            String SQL = "insert into t_lokasi (nama_lokasi) values (?)";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, lm.getNamaLokasi());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public void update(lokasiModel lm) {
            String SQL = "update t_lokasi set nama_lokasi=? where kode_lokasi=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, lm.getNamaLokasi());
                pst.setInt(2, lm.getKodeLokasi());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void delete(lokasiModel lm) {
            String SQL = "delete from t_lokasi where kode_lokasi=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setInt(1, lm.getKodeLokasi());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public List<lokasiModel> getAll() {
            String SQL = "select * from t_lokasi";
            List<lokasiModel> llm = null;
            try {
                llm = new ArrayList<>();
                pst = conn.prepareStatement(SQL);
                res = pst.executeQuery();
                while (res.next()) {
                    barangModel bm = new barangModel();
                    lokasiModel lm = bm.new lokasiModel();
                    lm.setKodeLokasi(res.getInt("kode_lokasi"));
                    lm.setNamaLokasi(res.getString("nama_lokasi"));
                    llm.add(lm);
                }
            } catch (SQLException ex) {
                Logger.getLogger(barangImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return llm;
        }

    }

}
