/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implement;

import DAO.Interface.userInterface;
import Model.userModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author HPrmn
 */
public class userImplement implements userInterface {

    private Connection conn = null;

    public userImplement(Connection conn) {
        this.conn = conn;
    }
    PreparedStatement pst;
    ResultSet res;

    @Override
    public void insert(userModel um) {
        String SQL = "insert into t_user (username,password,nip) values (?,?,?)";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, um.getUsername());
            pst.setString(2, um.getPassword());
            pst.setInt(3, um.getNip());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(userModel um) {
        String SQL = "update t_user set username=?,password=? where kode_user=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, um.getUsername());
            pst.setString(2, um.getPassword());
            pst.setString(3, um.getKode_user());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(userModel um) {
        String SQL = "delete from t_user where kode_user=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, um.getKode_user());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<userModel> getAll() {
        String SQL = "select * from t_user";
        List<userModel> lum = null;
        try {
            lum = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            res = pst.executeQuery();
            while (res.next()) {
                userModel um = new userModel();
                um.setKode_user(res.getString("kode_user"));
                um.setUsername(res.getString("username"));
                um.setPassword(res.getString("password"));
                um.setNip(res.getInt("nip"));
                lum.add(um);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lum;
    }

    @Override
    public List<userModel> cariUser(String cari) {
        String SQL = "select * from t_user where kode_user like ? or username like ? or nip like ? or password like ?";
        List<userModel> lum = null;
        try {
            lum = new ArrayList<>();
            pst = conn.prepareStatement(SQL);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            pst.setString(3, "%" + cari + "%");
            pst.setString(4, "%" + cari + "%");
            res = pst.executeQuery();
            while (res.next()) {
                userModel um = new userModel();
                um.setKode_user(res.getString("kode_user"));
                um.setUsername(res.getString("username"));
                um.setPassword(res.getString("password"));
                um.setNip(res.getInt("nip"));
                lum.add(um);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lum;
    }

    @Override
    public Boolean cekLogin(userModel um) {
        String SQL = "select * from t_user where username=? and password=?";
        try {
            pst = conn.prepareStatement(SQL);
            pst.setString(1, um.getUsername());
            pst.setString(2, um.getPassword());
            res = pst.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public void cekAkses(userModel um) {
        if (cekLogin(um) == true) {
            String SQL = "select * from t_user join t_pegawai join t_jabatan "
                    + "on t_user.nip = t_pegawai.nip and t_pegawai.kode_jabatan = t_jabatan.kode_jabatan where username=?";
            try {
                pst = conn.prepareStatement(SQL);
                pst.setString(1, um.getUsername());
                res = pst.executeQuery();
                if (res.next()) {
                    if (res.getString("nama_jabatan").equals("ADMINISTRATOR")) {
                        System.out.println("Administrator");
                        um.setNip(res.getInt("t_user.nip"));
                        um.setNama(res.getString("nama_p"));
                        um.setJabatan(res.getString("nama_jabatan"));
                    } else if (res.getString("nama_jabatan").equals("KASIR")) {
                        um.setNip(res.getInt("t_user.nip"));
                        um.setNama(res.getString("nama_p"));
                        um.setJabatan(res.getString("nama_jabatan"));
                    }else if (res.getString("nama_jabatan").equals("BAG. GUDANG")){
                        um.setNip(res.getInt("t_user.nip"));
                        um.setNama(res.getString("nama_p"));
                        um.setJabatan(res.getString("nama_jabatan"));                        
                    }else{
                        um.setNip(res.getInt("t_user.nip"));
                        um.setNama(res.getString("nama_p"));
                        um.setJabatan(res.getString("nama_jabatan"));                        
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(userImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            um.setUsername("salah");
        }
    }

}
