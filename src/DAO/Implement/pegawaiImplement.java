/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implement;

import DAO.Interface.pegawaiInterface;
import Model.pegawaiModel;
import Model.pegawaiModel.jabatanModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author HPrmn
 */
public class pegawaiImplement implements pegawaiInterface {

    private Connection conn = null;
    PreparedStatement pst;
    ResultSet res;

    public pegawaiImplement() {
    }

    public pegawaiImplement(Connection conn) {
        this.conn = conn;
    }

    String insert = "insert into t_pegawai (nip,nama_p,jenis_kelamin,kode_jabatan,tahun_masuk,alamat_p) values (?,?,?,?,?,?)";
    String update = "update t_pegawai set nama_p=?,jenis_kelamin=?,kode_jabatan=?,tahun_masuk=?,alamat_p=? where nip=?";
    String delete = "delete from t_pegawai where nip=?";

    @Override
    public void insert(pegawaiModel pm) {
        try {
            pst = conn.prepareStatement(insert);
            pst.setInt(1, pm.getNip());
            pst.setString(2, pm.getNama());
            pst.setString(3, pm.getJk());
            pst.setString(4, pm.getJabatan());
            pst.setInt(5, pm.getTahun_masuk());
            pst.setString(6, pm.getAlamat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(pegawaiModel pm) {
        try {
            pst = conn.prepareStatement(update);

            pst.setString(1, pm.getNama());
            pst.setString(2, pm.getJk());
            pst.setString(3, pm.getJabatan());
            pst.setInt(4, pm.getTahun_masuk());
            pst.setString(5, pm.getAlamat());
            pst.setInt(6, pm.getNip());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(pegawaiModel pm) {
        try {
            pst = conn.prepareStatement(delete);
            pst.setInt(1, pm.getNip());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<pegawaiModel> getAll() {
        String SQL = "select * from t_pegawai inner join t_jabatan "
                + "on t_pegawai.kode_jabatan=t_jabatan.kode_jabatan";
        List<pegawaiModel> lpm = null;
        try {
            lpm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                pegawaiModel pm = new pegawaiModel();
                pm.setNip(res.getInt("nip"));
                pm.setNama(res.getString("nama_p"));
                pm.setJk(res.getString("jenis_kelamin"));
                pm.setJabatan(res.getString("nama_jabatan"));
                pm.setTahun_masuk(res.getInt("tahun_masuk"));
                pm.setAlamat(res.getString("alamat_p"));
                lpm.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return lpm;
    }

    @Override
    public List<pegawaiModel> getcariPegawai(String cari) {
        List<pegawaiModel> lpm = null;
        String SQL = "select * from t_pegawai inner join t_jabatan on "
                + "t_pegawai.kode_jabatan = t_jabatan.kode_jabatan where nip like ? "
                + "or nama_p like ? or alamat_p like ? or jenis_kelamin like ? or "
                + "tahun_masuk like ? or nama_jabatan like ?";
        try {
            lpm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            pst.setString(3, "%" + cari + "%");
            pst.setString(4, "%" + cari + "%");
            pst.setString(5, "%" + cari + "%");
            pst.setString(6, "%" + cari + "%");
            res = pst.executeQuery();
            while (res.next()) {
                pegawaiModel pm = new pegawaiModel();
                pm.setNip(res.getInt("nip"));
                pm.setNama(res.getString("nama_p"));
                pm.setJk(res.getString("jenis_kelamin"));
                pm.setTahun_masuk(res.getInt("tahun_masuk"));
                pm.setAlamat(res.getString("alamat_p"));
                pm.setJabatan(res.getString("nama_jabatan"));
                lpm.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lpm;
    }

    @Override
    public void getjabatan(JComboBox nama) {
        String SQL = "select * from t_jabatan";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                nama.addItem(res.getString("nama_jabatan"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Integer getkodeJabatan(String nama) {
        Integer kode = null;
        String SQL = "select * from t_jabatan where nama_jabatan=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, nama);
            res = pst.executeQuery();
            if (res.next()) {
                kode = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kode;
    }

    @Override
    public void AutoId(pegawaiModel pm) {
        String SQL = "select max(right(nip,4)) from t_pegawai";
        try {
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                if (res.first() == false) {
                    pm.setNip(12340001);
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                    for (int i = 0; i < 4 - noLong; i++) {
                        no = "0" + no;
                    }
                    pm.setNip(Integer.valueOf(1234 + no));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<pegawaiModel> getSebagian() {
        String SQL = "select * from t_pegawai inner join t_jabatan on t_pegawai.kode_jabatan=t_jabatan.kode_jabatan left join t_user on t_user.nip=t_pegawai.nip where kode_user is null";
        List<pegawaiModel> lpm = null;
        try {
            lpm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                pegawaiModel pm = new pegawaiModel();
                pm.setNip(res.getInt("nip"));
                pm.setNama(res.getString("nama_p"));
                pm.setJk(res.getString("jenis_kelamin"));
                pm.setJabatan(res.getString("nama_jabatan"));
                pm.setTahun_masuk(res.getInt("tahun_masuk"));
                pm.setAlamat(res.getString("alamat_p"));
                lpm.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return lpm;
    }

    @Override
    public List<pegawaiModel> getcariSebagian(String cari) {
        List<pegawaiModel> lpm = null;
        String SQL = "select * from t_pegawai inner join t_jabatan on "
                + "t_pegawai.kode_jabatan = t_jabatan.kode_jabatan left join t_user on t_user.nip = t_pegawai.nip where kode_user is null and (t_pegawai.nip like ? "
                + "or nama_p like ? or alamat_p like ? or jenis_kelamin like ? or "
                + "tahun_masuk like ? or nama_jabatan like ?)";

        try {
            lpm = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            pst.setString(3, "%" + cari + "%");
            pst.setString(4, "%" + cari + "%");
            pst.setString(5, "%" + cari + "%");
            pst.setString(6, "%" + cari + "%");
            res = pst.executeQuery();
            while (res.next()) {
                pegawaiModel pm = new pegawaiModel();
                pm.setNip(res.getInt("t_pegawai.nip"));
                pm.setNama(res.getString("nama_p"));
                pm.setJk(res.getString("jenis_kelamin"));
                pm.setJabatan(res.getString("nama_jabatan"));
                pm.setTahun_masuk(res.getInt("tahun_masuk"));
                pm.setAlamat(res.getString("alamat_p"));
                lpm.add(pm);

            }
        } catch (SQLException ex) {
            Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lpm;
    }

    //Implementasi Interface Jabatan
    public class jabatanImplement implements jabatanInterface {

        private final Connection conn;

        public jabatanImplement(Connection conn) {
            this.conn = conn;
        }

        @Override
        public void insert(jabatanModel jm) {
            String SQL = "insert into t_jabatan (nama_jabatan) values (?)";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, jm.getNamaJabatan());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public void update(jabatanModel jm) {
            String SQL = "update t_jabatan set nama_jabatan=? where kode_jabatan=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, jm.getNamaJabatan());
                pst.setInt(2, jm.getKodeJabatan());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public void delete(jabatanModel jm) {
            String SQL = "delete from t_jabatan where kode_jabatan=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setInt(1, jm.getKodeJabatan());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        @Override
        public List<jabatanModel> getAll() {
            String SQL = "select * from t_jabatan";
            List<jabatanModel> ljm = null;
            try {
                ljm = new ArrayList<>();
                pst = conn.prepareStatement(SQL);
                res = pst.executeQuery();
                while (res.next()) {
                    pegawaiModel pm = new pegawaiModel();
                    jabatanModel jm = pm.new jabatanModel();
                    jm.setKodeJabatan(res.getInt("kode_jabatan"));
                    jm.setNamaJabatan(res.getString("nama_jabatan"));
                    ljm.add(jm);
                }
            } catch (SQLException ex) {
                Logger.getLogger(pegawaiImplement.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ljm;
        }

    }

}
