/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Interface.pegawaiInterface;
import DAO.Interface.pegawaiInterface.jabatanInterface;
import Model.pegawaiModel;
import Model.pegawaiModel.jabatanModel;
import Model.tabelpegawaiModel;
import Model.tabelpegawaiModel.tabelJabatanModel;
import View.caripegawaiView;
import View.jabatanView;
import View.pegawaiView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;
import koneksi.koneksi;

/**
 *
 * @author HPrmn
 */
public class pegawaiController {

    //inisialisasi untuk data pegawai
    pegawaiInterface pi;
    pegawaiModel pm;
    pegawaiView pv;
    List<pegawaiModel> lpm;
    caripegawaiView cpv;

    //inisialisasi untuk data jabatan
    jabatanInterface ji;
    jabatanModel jm;
    jabatanView jv;
    List<jabatanModel> ljm;

    //kontruktor untuk data pegawai
    public pegawaiController(pegawaiView pv) {
        this.pv = pv;
        pi = koneksi.getpegawaiInterface();
        lpm = pi.getAll();
    }
    
    public pegawaiController(caripegawaiView cpv) {
        this.cpv = cpv;
        pi = koneksi.getpegawaiInterface();
        lpm = pi.getSebagian();
        cpv.setLocationRelativeTo(cpv);
    }

    //konstruktor untuk data jabatan
    public pegawaiController(jabatanView jv) {
        this.jv = jv;
        ji = koneksi.getjabatanInterface();
        ljm = ji.getAll();
        jv.setLocationRelativeTo(jv);
    }

    //untuk jabatan
    public void jTeks(Boolean nama) {
        jv.getTxt_nama_jabatan().setEnabled(nama);
    }

    //untuk jabatan
    public void pFokus(javax.swing.JComponent nama) {
        nama.requestFocus();
    }
    
    public void pTeksHapus() {
        pv.getTxt_nama().setText("");
        pv.getTxt_jk().setSelectedItem("Pilih");
        pv.getJabatan().setSelectedItem("Pilih");
        pv.getTxt_alamat().setText("");
    }
    
    public void pTeks(Boolean nama) {
        pv.getTxt_nama().setEnabled(nama);
        pv.getTxt_jk().setEnabled(nama);
        pv.getBtn_jabatan().setEnabled(nama);
        pv.getTxt_alamat().setEnabled(nama);
        pv.getJabatan().setEnabled(nama);
        pv.getTahun_masuk().setEnabled(nama);
    }
    
    public void pAwal() {
        pTeksHapus();
        autoID();
        pv.setLocationRelativeTo(pv);
        pFokus(pv.getBtn_databaru());
        pv.getBtn_databaru().setEnabled(true);
        pv.getBtn_simpan().setEnabled(false);
        pv.getBtn_batal().setEnabled(false);
        pv.getBtn_edit().setEnabled(false);
        pv.getBtn_hapus().setEnabled(false);
        pTeks(false);
    }
    
    public void pDataBaru() {
        pTeks(true);
        pFokus(pv.getTxt_nama());
        pv.getBtn_databaru().setEnabled(false);
        pv.getBtn_simpan().setEnabled(true);
        pv.getBtn_batal().setEnabled(true);
    }
    
    public void pEditHapus() {
        pTeks(true);
        pFokus(pv.getTxt_nama());
        pv.getBtn_databaru().setEnabled(false);
        pv.getBtn_simpan().setEnabled(false);
        pv.getBtn_edit().setEnabled(true);
        pv.getBtn_hapus().setEnabled(true);
        pv.getBtn_batal().setEnabled(true);
    }
    
    public void jAwal() {
        jv.setLocationRelativeTo(jv);
        pFokus(jv.getBtn_databaru());
        jv.getBtn_databaru().setEnabled(true);
        jv.getBtn_simpan().setEnabled(false);
        jv.getBtn_batal().setEnabled(false);
        jv.getBtn_ubah().setEnabled(false);
        jv.getBtn_hapus().setEnabled(false);
        jTeks(false);
        jv.getTxt_kd_jabatan().setText("");
        jv.getTxt_nama_jabatan().setText("");
        
    }
    
    public void jDataBaru() {
        pFokus(jv.getTxt_nama_jabatan());
        jv.getBtn_databaru().setEnabled(false);
        jv.getBtn_simpan().setEnabled(true);
        jv.getBtn_batal().setEnabled(true);
        jTeks(true);
    }
    
    public void jEditHapus() {
        pFokus(jv.getTxt_nama_jabatan());
        jv.getBtn_simpan().setEnabled(false);
        jv.getBtn_databaru().setEnabled(false);
        jv.getBtn_ubah().setEnabled(true);
        jv.getBtn_hapus().setEnabled(true);
        jv.getBtn_batal().setEnabled(true);
        jTeks(true);
    }
    
    public void getJabatan() {
        pi.getjabatan(pv.getJabatan());
    }
    
    public void isiFieldPegawai(int row) {
        pv.getTxt_nip().setText(Integer.toString(lpm.get(row).getNip()));
        pv.getTxt_nama().setText(lpm.get(row).getNama());
        pv.getTxt_jk().setSelectedItem(lpm.get(row).getJk());
        pv.getJabatan().setSelectedItem(lpm.get(row).getJabatan());
        pv.getTxt_alamat().setText(lpm.get(row).getAlamat());
        pv.getTahun_masuk().setValue(lpm.get(row).getTahun_masuk());
        getJabatan();
        pEditHapus();
    }
    
    public void isiFieldJabatan(int row) {
        jv.getTxt_kd_jabatan().setText(Integer.toString(ljm.get(row).getKodeJabatan()));
        jv.getTxt_nama_jabatan().setText(ljm.get(row).getNamaJabatan());
        jEditHapus();
    }
    
    public void getTabel() {
        lpm = pi.getAll();
        tabelpegawaiModel tbm = new tabelpegawaiModel(lpm);
        TableRowSorter<tabelpegawaiModel> trs = new TableRowSorter<>(tbm);
        pv.getT_pegawai().setRowSorter(trs);
        pv.getT_pegawai().setModel(tbm);
    }
    
    public void getTabelCari() {
        lpm = pi.getSebagian();
        tabelpegawaiModel tbm = new tabelpegawaiModel(lpm);
        TableRowSorter<tabelpegawaiModel> trs = new TableRowSorter<>(tbm);
        cpv.getT_pegawai().setRowSorter(trs);
        cpv.getT_pegawai().setModel(tbm);
    }
    
    public void gettabelJabatan() {
        ljm = ji.getAll();
        System.out.println(ljm);
        tabelpegawaiModel tbm = new tabelpegawaiModel(lpm);
        tabelJabatanModel tjm = tbm.new tabelJabatanModel(ljm);
        jv.getT_jabatan().setModel(tjm);
    }
    
    public void simpanJabatan() {
        pm = new pegawaiModel();
        jm = pm.new jabatanModel();
        if (jv.getTxt_nama_jabatan().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Jabatan Tidak Boleh Kosong", "Warning", JOptionPane.WARNING_MESSAGE);
            pFokus(jv.getTxt_nama_jabatan());
        } else {
            jm.setNamaJabatan(jv.getTxt_nama_jabatan().getText().toUpperCase());
            int count = jv.getT_jabatan().getRowCount();
            for (int i = 0; i < count; i++) {
                if (jm.getNamaJabatan().equals(jv.getT_jabatan().getValueAt(i, 1).toString())) {
                    System.out.println("Tidak ");
                    JOptionPane.showMessageDialog(null, "Nama Sudah Ada", "Warning", JOptionPane.WARNING_MESSAGE);
                    jAwal();
                    break;
                } else if (i + 1 == count) {
                    ji.insert(jm);
                    JOptionPane.showMessageDialog(null, "Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    jAwal();
                }
                
            }
        }
    }
    
    public void editJabatan() {
        pm = new pegawaiModel();
        jm = pm.new jabatanModel();
        if (jv.getTxt_nama_jabatan().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Jabatan Tidak Boleh Kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            pFokus(jv.getTxt_nama_jabatan());
        } else {
            jm.setKodeJabatan(Integer.valueOf(jv.getTxt_kd_jabatan().getText()));
            jm.setNamaJabatan(jv.getTxt_nama_jabatan().getText().toUpperCase());
            int count = jv.getT_jabatan().getRowCount();
            for (int i = 0; i < count; i++) {
                if (jm.getNamaJabatan().equals(jv.getT_jabatan().getValueAt(i, 1).toString())) {
                    System.out.println("Tidak ");
                    JOptionPane.showMessageDialog(null, "Nama Sudah Ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    pFokus(jv.getTxt_nama_jabatan());
                    break;
                } else if (i + 1 == count) {
                    int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data ?", "Edit", JOptionPane.YES_NO_OPTION);
                    if (p == 0) {
                        ji.update(jm);
                        JOptionPane.showMessageDialog(null, "Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        jAwal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        pFokus(jv.getBtn_batal());
                    }
                }
                
            }
        }
    }
    
    public void hapusJabatan() {
        pm = new pegawaiModel();
        jm = pm.new jabatanModel();
        jm.setKodeJabatan(Integer.valueOf(jv.getTxt_kd_jabatan().getText()));
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data ?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            ji.delete(jm);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            jAwal();
        } else {
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            //jAwal();
            pFokus(jv.getBtn_batal());
        }
    }
    
    public void simpanedit(int pilih) {
        pm = new pegawaiModel();
        pm.setNip(Integer.parseInt(pv.getTxt_nip().getText()));
        pm.setNama(pv.getTxt_nama().getText());
        pm.setJabatan(String.valueOf(pi.getkodeJabatan(pv.getJabatan().getSelectedItem().toString())));
        pm.setJk(pv.getTxt_jk().getSelectedItem().toString());
        pm.setTahun_masuk(pv.getTahun_masuk().getValue());
        pm.setAlamat(pv.getTxt_alamat().getText());
        
        if (pv.getTxt_nama().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Masih Kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            pFokus(pv.getTxt_nama());
        } else if (pv.getTxt_jk().getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Belum Memilih Jenis Kelamin", "Peringatan", JOptionPane.WARNING_MESSAGE);
            pFokus(pv.getTxt_jk());
        } else if (pv.getJabatan().getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Belum Memilih Jabatan", "Peringatan", JOptionPane.WARNING_MESSAGE);
            pFokus(pv.getJabatan());
        } else if (pv.getTxt_alamat().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Alamat Masih Kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            pFokus(pv.getTxt_alamat());
        } else if (pilih == 1) {
            pi.insert(pm);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            autoID();
            pAwal();
        } else if (pilih == 2) {
            int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data ?", "Edit", JOptionPane.YES_NO_OPTION);
            if (p == 0) {
                pi.update(pm);
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                autoID();
                pAwal();
            } else {
                JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                pFokus(pv.getBtn_batal());
            }
            
        }
    }
    
    public void hapus() {
        pm = new pegawaiModel();
        pm.setNip(Integer.parseInt(pv.getTxt_nip().getText()));
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data ?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            pi.delete(pm);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            autoID();
            pAwal();
        } else {
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            pFokus(pv.getBtn_batal());
        }
    }
    
    public void autoID() {
        //deklarasi objek dengan memanggil konstruktor
        pm = new pegawaiModel();
        //menampung data
        pi.AutoId(pm);
        //menampilkan data
        pv.getTxt_nip().setText(String.valueOf(pm.getNip()));
    }
    
    public void cari(JTextField nama) {
        lpm = pi.getcariPegawai(nama.getText());
        tabelpegawaiModel tpm = new tabelpegawaiModel(lpm);
        TableRowSorter<tabelpegawaiModel> sorter = new TableRowSorter<>(tpm);
        
        pv.getT_pegawai().setRowSorter(sorter);
        pv.getT_pegawai().setModel(tpm);        
    }

    public void cariSebagian() {
        
        lpm = pi.getcariSebagian(cpv.getCari_txt().getText());
        tabelpegawaiModel tpm = new tabelpegawaiModel(lpm);
        TableRowSorter<tabelpegawaiModel> sorter = new TableRowSorter<>(tpm);
        cpv.getT_pegawai().setRowSorter(sorter);
        cpv.getT_pegawai().setModel(tpm);
    }
}
