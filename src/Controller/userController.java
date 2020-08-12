/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Interface.userInterface;
import Model.tabeluserModel;
import Model.userModel;
import View.awalView;
import View.loginView;
import View.userView;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import koneksi.koneksi;

/**
 *
 * @author HPrmn
 */
public class userController {

    private awalView av;
    private userView uv;
    private loginView lv;
    private userInterface ui;
    private userModel um;
    private List<userModel> lum;

    public userController(userView uv) {
        this.uv = uv;
        ui = koneksi.getUserInterface();
        lum = ui.getAll();
    }

    public userController(loginView lv) {
        this.lv = lv;
        ui = koneksi.getUserInterface();
    }

    public userController(awalView av) {
        this.av = av;
    }

    public void userLogin() {
        um = new userModel();
        um.setUsername(lv.getTxt_username().getText());
        um.setPassword(String.valueOf(lv.getTxt_password().getPassword()));
        ui.cekAkses(um);
        if (um.getUsername().equals("salah")) {
            JOptionPane.showMessageDialog(null, "Username atau Password Salah", "Peringatan", JOptionPane.WARNING_MESSAGE);
            lv.getTxt_username().setText("");
            lv.getTxt_password().setText("");
            lv.getTxt_username().requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "SELAMAT DATANG : " + um.getNama() + "", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            lv.nip = String.valueOf(um.getNip());
            lv.nama = um.getNama();
            lv.jabatan = um.getJabatan();
        }
    }

    public void aAwal() {
        av.getIsinip().setText("");
        av.getIsinama().setText("");
        av.getIsibagian().setText("");
        av.getMenu1().setEnabled(false);
        av.getMenu2().setEnabled(false);
        av.getMenu3().setEnabled(false);
        av.getMenu4().setEnabled(false);
    }

    public void aktif(javax.swing.JComponent nama, Boolean bool) {
        nama.setEnabled(bool);
    }

    public void textUser(Boolean nama) {
        uv.getCari_nip().setEnabled(nama);
        uv.getUsername().setEnabled(nama);
        uv.getPassword().setEnabled(nama);
    }

    public void validasiAwal() {
        uv.getBtn_baru().requestFocus();
        uv.getBtn_baru().setEnabled(true);
        textUser(false);
        uv.getKode_user().setText("");
        uv.getNama_pgw().setText("");
        uv.getNip().setText("");
        uv.getUsername().setText("");
        uv.getPassword().setText("");
        uv.getBtn_simpan().setEnabled(false);
        uv.getBtn_edit().setEnabled(false);
        uv.getBtn_hapus().setEnabled(false);
        uv.getBtn_batal().setEnabled(false);
    }

    public void validasiDataBaru() {
        uv.getCari_nip().requestFocus();
        uv.getBtn_baru().setEnabled(false);
        textUser(true);
        uv.getBtn_simpan().setEnabled(true);
        uv.getBtn_batal().setEnabled(true);
    }

    public void validasiEditHapus() {
        uv.getCari_nip().requestFocus();
        uv.getCari_nip().setEnabled(false);
        uv.getBtn_simpan().setEnabled(false);
        uv.getBtn_baru().setEnabled(false);
        uv.getBtn_edit().setEnabled(true);
        uv.getBtn_hapus().setEnabled(true);
        uv.getBtn_batal().setEnabled(true);
        textUser(true);

    }

    public void isiUser(int row) {
        uv.getKode_user().setText(lum.get(row).getKode_user());
        uv.getNip().setText(String.valueOf(lum.get(row).getNip()));
        uv.getUsername().setText(lum.get(row).getUsername());
        uv.getPassword().setText(lum.get(row).getPassword());
        validasiEditHapus();
    }

    public void getTabelUser() {
        lum = ui.getAll();
        tabeluserModel tum = new tabeluserModel(lum);
        TableRowSorter<tabeluserModel> sorter = new TableRowSorter<>(tum);
        uv.getT_user().setRowSorter(sorter);
        uv.getT_user().setModel(tum);
    }

    public void getSimpan() {
        um = new userModel();
        Boolean Check = null;
        System.out.println(Check);
//        System.out.println(uv.getT_user().getValueAt(1, 2));
        if (uv.getNip().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "NIP Belum Diisi");
        } else if (uv.getUsername().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Username Belum Diisi");
        } else if (uv.getPassword().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Password Belum Diisi");
        } else {
            um.setNip(Integer.valueOf(uv.getNip().getText()));
            um.setUsername(uv.getUsername().getText());
            um.setPassword(uv.getPassword().getText());
            System.out.println(um.getPassword());
            int count = uv.getT_user().getRowCount();
            for (int i = 0; i < count; i++) {
                if (um.getUsername().equals(uv.getT_user().getValueAt(i, 2).toString())) {
                    System.out.println("Error");
                    JOptionPane.showMessageDialog(null, "Username " + um.getUsername() + " Sudah Ada", "Error", JOptionPane.WARNING_MESSAGE);
                    validasiAwal();
                    break;
                } else if (i + 1 == count) {
                    ui.insert(um);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                    getTabelUser();
                    validasiAwal();
                }
            }
        }
    }

    public void getEdit() {
        um = new userModel();
        if (uv.getNip().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "NIP Belum Diisi", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (uv.getUsername().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Username Belum Diisi", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (uv.getPassword().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Password Belum Diisi", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            um.setKode_user(uv.getKode_user().getText());
            um.setNip(Integer.valueOf(uv.getNip().getText()));
            um.setUsername(uv.getUsername().getText());
            um.setPassword(uv.getPassword().getText());
            System.out.println(um.getPassword());
            int count = uv.getT_user().getRowCount();
            for (int i = 0; i < count; i++) {
                if (um.getUsername().equals(uv.getT_user().getValueAt(i, 2).toString())&&!uv.getKode_user().getText().equals(uv.getT_user().getValueAt(i, 0).toString())) {
                    System.out.println("Error");
                    JOptionPane.showMessageDialog(null, "Username " + um.getUsername() + " Sudah Ada", "Error", JOptionPane.WARNING_MESSAGE);
                    System.out.println(uv.getT_user().getValueAt(i, 0));
                    //validasiAwal();
                    break;
                } else if (i + 1 == count) {
                    int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data"
                            + "", "Edit", JOptionPane.YES_NO_OPTION);
                    if (p == 0) {
                        ui.update(um);
                        JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                        getTabelUser();
                        validasiAwal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan");
                    }
                }
            }
        }
    }

    public void getHapus() {
        um = new userModel();
        um.setKode_user(uv.getKode_user().getText());
        System.out.println(um.getPassword());
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data"
                + "", "Hapus", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            ui.delete(um);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            getTabelUser();
            validasiAwal();
        } else {
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan");
        }
    }

    public void getCari() {
        lum = ui.cariUser(uv.getCari().getText());
        tabeluserModel tum = new tabeluserModel(lum);
        TableRowSorter<tabeluserModel> sorter = new TableRowSorter<>(tum);
        uv.getT_user().setRowSorter(sorter);
        uv.getT_user().setModel(tum);
    }
}
