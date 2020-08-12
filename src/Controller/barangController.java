/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Interface.barangInterface;
import DAO.Interface.barangInterface.lokasiInterface;
import DAO.Interface.barangInterface.merkInterface;
import Model.barangModel;
import Model.barangModel.lokasiModel;
import Model.barangModel.merkModel;
import Model.tabelbarangModel;
import Model.tabelbarangModel.tabellokasiModel;
import Model.tabelbarangModel.tabelmerkModel;
import View.barangView;
import View.caribarangView;
import View.lokasiView;
import View.merkView;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import koneksi.koneksi;

/**
 *
 * @author HPrmn
 */
public class barangController {

    barangInterface bi;
    barangView bv;
    barangModel bm;
    List<barangModel> lbm;
    caribarangView cbv;

    //untuk merk
    merkInterface mi;
    merkView mv;
    merkModel mm;
    List<merkModel> lmm;

    //untuk lokasi penyimpanan
    lokasiInterface li;
    lokasiView lv;
    lokasiModel lm;
    List<lokasiModel> llm;

    //konstruktor untuk barang
    public barangController(barangView bv) {
        this.bv = bv;
        bi = koneksi.getbarangInterface();
        lbm = bi.getAll();
        warna();
        bAwal();
        bv.setLocationRelativeTo(bv);
    }

    public barangController(caribarangView cbv) {
        this.cbv = cbv;
        bi = koneksi.getbarangInterface();
        lbm = bi.getAll();
        cbv.setLocationRelativeTo(cbv);
    }

    //konstruktor untuk merk
    public barangController(merkView mv) {
        this.mv = mv;
        mi = koneksi.getmerkInterface();
        lmm = mi.getAll();
        mv.setLocationRelativeTo(mv);
        mAwal();

    }

    //konstruktor untuk lokasi penyimpanan
    public barangController(lokasiView lv) {
        this.lv = lv;
        li = koneksi.getlokasiInterface();
        llm = li.getAll();
        lv.setLocationRelativeTo(lv);
        lAwal();
    }

    public void getMerk() {
        bi.getMerk(bv.getMerk_txt());
    }

    public void getLokasi() {
        bi.getLokasi(bv.getLokasi());
    }

    //awal untuk barang
    public void bhapusTeks() {
        bv.getNama_barang_txt().setText("");
        bv.getMerk_txt().setSelectedItem("Pilih");
        bv.getStok().setValue(0);
        bv.getHarga_beli().setText("0");
        bv.getHarga_jual().setText("0");
        bv.getLokasi().setSelectedItem("Pilih");
    }

    public void bAwal() {
        bv.getBtn_databaru().requestFocus();
        bv.getBtn_databaru().setEnabled(true);
        bv.getBtn_batal().setEnabled(false);
        bv.getBtn_tambah().setEnabled(false);
        bv.getBtn_edit().setEnabled(false);
        bv.getBtn_hapus().setEnabled(false);
        teksAktifNonaktif(false);
        bhapusTeks();
    }

    public void teksAktifNonaktif(Boolean nama) {
        bv.getNama_barang_txt().setEnabled(nama);
        bv.getMerk_txt().setEnabled(nama);
        bv.getMerk_button().setEnabled(nama);
        bv.getLokasi().setEnabled(nama);
        bv.getLokasi_button().setEnabled(nama);
        bv.getStok().setEnabled(nama);
        bv.getHarga_beli().setEnabled(nama);
        bv.getHarga_jual().setEnabled(nama);
    }

    public void bdataBaru() {
        bv.getNama_barang_txt().requestFocus();
        teksAktifNonaktif(true);
        bv.getBtn_databaru().setEnabled(false);
        bv.getBtn_tambah().setEnabled(true);
        bv.getBtn_batal().setEnabled(true);
    }

    public void bEditHapus() {
        bv.getBtn_databaru().setEnabled(false);
        bv.getBtn_tambah().setEnabled(false);
        bv.getBtn_batal().setEnabled(true);
        bv.getBtn_edit().setEnabled(true);
        bv.getBtn_hapus().setEnabled(true);
        teksAktifNonaktif(true);
    }

    //akhir untuk barang
    //awal untuk merk
    public void mAwal() {
        mv.getBtn_databaru().requestFocus();
        mv.getBtn_databaru().setEnabled(true);
        mv.getBtn_batal().setEnabled(false);
        mv.getBtn_hapus().setEnabled(false);
        mv.getBtn_ubah().setEnabled(false);
        mv.getBtn_simpan().setEnabled(false);
        mHapusTeks();
        mNonAktif(false);
    }

    public void mDataBaru() {
        mv.getTxt_nama_merk().requestFocus();
        mv.getBtn_databaru().setEnabled(false);
        mv.getBtn_simpan().setEnabled(true);
        mv.getBtn_batal().setEnabled(true);
        mNonAktif(true);
    }

    public void mEditHapus() {
        mv.getTxt_nama_merk().requestFocus();
        mv.getBtn_databaru().setEnabled(false);
        mv.getBtn_simpan().setEnabled(false);
        mv.getBtn_hapus().setEnabled(true);
        mv.getBtn_ubah().setEnabled(true);
        mv.getBtn_batal().setEnabled(true);
        mNonAktif(true);
    }

    public void mNonAktif(Boolean nama) {
        mv.getTxt_nama_merk().setEnabled(nama);
    }

    public void mHapusTeks() {
        mv.getTxt_kd_merk().setText("");
        mv.getTxt_nama_merk().setText("");
    }

    public void isiMerk(int row) {
        mv.getTxt_kd_merk().setText(String.valueOf(lmm.get(row).getKodeMerk()));
        mv.getTxt_nama_merk().setText(lmm.get(row).getNamaMerk());
        mEditHapus();
    }

    public void gettabelMerk() {
        lmm = mi.getAll();
        tabelbarangModel tbm = new tabelbarangModel();
        tabelmerkModel tmm = tbm.new tabelmerkModel(lmm);
        TableRowSorter<tabelmerkModel> sorter = new TableRowSorter<>(tmm);
        mv.getT_merk().setRowSorter(sorter);
        mv.getT_merk().setModel(tmm);
    }

    public void simpanMerk() {
        bm = new barangModel();
        mm = bm.new merkModel();
        int count = mv.getT_merk().getRowCount();
        for (int i = 0; i < count; i++) {
            if (mv.getTxt_nama_merk().getText().equalsIgnoreCase(mv.getT_merk().getValueAt(i, 1).toString())) {
                JOptionPane.showMessageDialog(null, "Merk Sudah Ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                break;
            } else if (i + 1 == count) {
                if (mv.getTxt_nama_merk().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Merk Belum Diisi");
                    mv.getTxt_nama_merk().requestFocus();
                } else {
                    mm.setNamaMerk(mv.getTxt_nama_merk().getText().toUpperCase());
                    mi.insert(mm);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    mAwal();
                }
            }
        }
    }

    public void editMerk() {
        bm = new barangModel();
        mm = bm.new merkModel();
        mm.setKodeMerk(Integer.valueOf(mv.getTxt_kd_merk().getText()));
        int count = mv.getT_merk().getRowCount();
        for (int i = 0; i < count; i++) {
            if (mv.getTxt_nama_merk().getText().equalsIgnoreCase(mv.getT_merk().getValueAt(i, 1).toString())) {
                JOptionPane.showMessageDialog(null, "Merk Sudah Ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                mv.getTxt_nama_merk().requestFocus();
                break;
            } else if (i + 1 == count) {
                if (mv.getTxt_nama_merk().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Merk Belum Diisi");
                    mv.getTxt_nama_merk().requestFocus();
                } else {
                    int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data", "Edit", JOptionPane.YES_NO_OPTION);
                    if (p == 0) {
                        mm.setNamaMerk(mv.getTxt_nama_merk().getText().toUpperCase());
                        mi.update(mm);
                        JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        mAwal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan", "Edit", JOptionPane.INFORMATION_MESSAGE);
                        mv.getBtn_ubah().requestFocus();
                    }
                }
            }
        }
    }

    public void hapusMerk() {
        bm = new barangModel();
        mm = bm.new merkModel();
        mm.setKodeMerk(Integer.valueOf(mv.getTxt_kd_merk().getText()));
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data", "Edit", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            mi.delete(mm);
            mAwal();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            mv.getBtn_hapus().requestFocus();
        }
    }
//akhir untuk merk
    //awal untuk lokasi

    public void lAwal() {
        lv.getBtn_databaru().requestFocus();
        lv.getBtn_databaru().setEnabled(true);
        lv.getBtn_simpan().setEnabled(false);
        lv.getBtn_batal().setEnabled(false);
        lv.getBtn_hapus().setEnabled(false);
        lv.getBtn_ubah().setEnabled(false);
        lNonAktif(false);
        lHapusTeks();
    }

    public void ldataBaru() {
        lv.getBtn_databaru().setEnabled(false);
        lv.getBtn_simpan().setEnabled(true);
        lv.getBtn_batal().setEnabled(true);
        lNonAktif(true);
    }

    public void lNonAktif(Boolean nama) {
        lv.getTxt_nama_lokasi().setEnabled(nama);
    }

    public void lHapusTeks() {
        lv.getTxt_kd_lokasi().setText("");
        lv.getTxt_nama_lokasi().setText("");
    }

    public void lEditHapus() {
        lv.getBtn_batal().setEnabled(true);
        lv.getBtn_hapus().setEnabled(true);
        lv.getBtn_ubah().setEnabled(true);
        lv.getBtn_databaru().setEnabled(false);
        lv.getBtn_simpan().setEnabled(false);
        lNonAktif(true);
    }

    public void isiLokasi(int row) {
        lv.getTxt_kd_lokasi().setText(String.valueOf(llm.get(row).getKodeLokasi()));
        lv.getTxt_nama_lokasi().setText(llm.get(row).getNamaLokasi());
        lEditHapus();
        lv.getTxt_nama_lokasi().requestFocus();
    }

    public void gettabelLokasi() {
        llm = li.getAll();
        tabelbarangModel tbm = new tabelbarangModel();
        tabellokasiModel tlm = tbm.new tabellokasiModel(llm);
        TableRowSorter<tabellokasiModel> sorter = new TableRowSorter<>(tlm);
        lv.getT_lokasi().setRowSorter(sorter);
        lv.getT_lokasi().setModel(tlm);
    }

    public void simpanlokasi() {
        bm = new barangModel();
        lm = bm.new lokasiModel();
        int count = lv.getT_lokasi().getRowCount();
        for (int i = 0; i < count; i++) {
            if (lv.getTxt_nama_lokasi().getText().equalsIgnoreCase(lv.getT_lokasi().getValueAt(i, 1).toString())) {
                JOptionPane.showMessageDialog(null, "Nama Lokasi Sudah Ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                lv.getTxt_nama_lokasi().requestFocus();
                break;
            } else if (i + 1 == count) {
                if (lv.getTxt_nama_lokasi().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Nama Lokasi Belum Diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    lm.setNamaLokasi(lv.getTxt_nama_lokasi().getText().toUpperCase());
                    li.insert(lm);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }

    public void editlokasi() {
        bm = new barangModel();
        lm = bm.new lokasiModel();
        int count = lv.getT_lokasi().getRowCount();
        for (int i = 0; i < count; i++) {
            if (lv.getTxt_nama_lokasi().getText().equalsIgnoreCase(lv.getT_lokasi().getValueAt(i, 1).toString()) && !lv.getTxt_kd_lokasi().getText().equals(lv.getT_lokasi().getValueAt(i, 0).toString())) {
                JOptionPane.showMessageDialog(null, "Nama Lokasi Sudah Ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
                lv.getTxt_nama_lokasi().requestFocus();
                break;
            } else if (i + 1 == count) {
                if (lv.getTxt_nama_lokasi().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Nama Lokasi Belum Diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    lv.getTxt_nama_lokasi().requestFocus();
                } else {
                    int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data", "Edit", JOptionPane.YES_NO_OPTION);
                    if (p == 0) {
                        lm.setKodeLokasi(Integer.valueOf(lv.getTxt_kd_lokasi().getText()));
                        lm.setNamaLokasi(lv.getTxt_nama_lokasi().getText().toUpperCase());
                        li.update(lm);
                        JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        lAwal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        lv.getBtn_ubah().requestFocus();
                    }
                }
            }
        }

    }

    public void hapuslokasi() {
        bm = new barangModel();
        lm = bm.new lokasiModel();
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data", "Hapus", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            lm.setKodeLokasi(Integer.valueOf(lv.getTxt_kd_lokasi().getText()));
            li.delete(lm);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus","Pesan",JOptionPane.INFORMATION_MESSAGE);
            lAwal();
        }else{
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan","Pesan",JOptionPane.INFORMATION_MESSAGE);
            lv.getBtn_hapus().requestFocus();
        }

    }
//akhir untuk lokasi
    //awal untuk barang

    public void isiField(int row) {
        bv.getKode_barang_txt().setText(lbm.get(row).getKode_barang());
        bv.getNama_barang_txt().setText(lbm.get(row).getNama_barang());
        bv.getMerk_txt().setSelectedItem(lbm.get(row).getMerk());
        bv.getStok().setValue(lbm.get(row).getStok());
        bv.getHarga_beli().setText(Double.toString(lbm.get(row).getHarga_beli()).replace(".0", ""));
        //System.out.println(Double.toString(lbm.get(row).getHarga_beli()).replace(".0",""));
        bv.getHarga_jual().setText(Double.toString(lbm.get(row).getHarga_jual()).replace(".0", ""));
        bv.getLokasi().setSelectedItem(lbm.get(row).getLokasi());
        getLokasi();
        getMerk();
        bEditHapus();
    }

    public void getTable() {
        lbm = bi.getAll();
        tabelbarangModel tbm = new tabelbarangModel(lbm);
        TableRowSorter<tabelbarangModel> sorter = new TableRowSorter<>(tbm);
        bv.getTable_barang().setRowSorter(sorter);
        bv.getTable_barang().setModel(tbm);
    }

    public void getTabelCari() {
        lbm = bi.getAll1();
        tabelbarangModel tbm = new tabelbarangModel(lbm);
        TableRowSorter<tabelbarangModel> sorter = new TableRowSorter<>(tbm);
        cbv.getTable_barang().setRowSorter(sorter);
        cbv.getTable_barang().setModel(tbm);
    }

    public void simpanedit(int pilih) {
        bm = new barangModel();
        int count = bv.getTable_barang().getRowCount();
        if (bv.getNama_barang_txt().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Barang Belum Diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getNama_barang_txt().requestFocus();
        } else if (bv.getMerk_txt().getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Belum Memilih Merk", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getMerk_txt().requestFocus();
        } else if (bv.getHarga_beli().getText().equals(0)) {
            JOptionPane.showMessageDialog(null, "Harga Beli Tidak Boleh 0", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getHarga_beli().requestFocus();
        } else if (bv.getHarga_jual().getText().equals(0)) {
            JOptionPane.showMessageDialog(null, "Harga Jual Tidak Boleh 0", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getHarga_jual().requestFocus();
        } else if (Double.valueOf(bv.getHarga_beli().getText().replace(".", "")) > Double.valueOf(bv.getHarga_jual().getText().replace(".", ""))) {
            JOptionPane.showMessageDialog(null, "Harga Jual Tidak Boleh Lebih Kecil Dari Harga Beli", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getHarga_jual().requestFocus();
        } else if (bv.getLokasi().getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Belum Memilih Lokasi Penyimpanan", "Peringatan", JOptionPane.WARNING_MESSAGE);
            bv.getLokasi().requestFocus();
        } else {
            bm.setKode_barang(bv.getKode_barang_txt().getText());
            bm.setNama_barang(bv.getNama_barang_txt().getText());
            bm.setMerk(String.valueOf(bi.getkodeMerk(bv.getMerk_txt().getSelectedItem().toString())));
            bm.setStok((Integer) bv.getStok().getValue());
            bm.setHarga_beli(Double.parseDouble(bv.getHarga_beli().getText().replace(".", "")));
            bm.setHarga_jual(Double.parseDouble(bv.getHarga_jual().getText().replace(".", "")));
            bm.setLokasi(String.valueOf(bi.getkodeLokasi(bv.getLokasi().getSelectedItem().toString())));
            if (pilih == 1) {
                bi.insert(bm);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                autoID();
                bAwal();
            } else if (pilih == 2) {
                int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Mengedit Data", "Edit", JOptionPane.YES_NO_OPTION);
                if (p == 0) {
                    bi.update(bm);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    autoID();
                    bAwal();

                } else {
                    JOptionPane.showMessageDialog(null, "Edit Data Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    bv.getBtn_edit().requestFocus();
                }
            }/*

            for (int i = 0; i < count; i++) {
                if (bm.getNama_barang().equalsIgnoreCase(bv.getTable_barang().getValueAt(i, 1).toString()) && pilih == 1) {
                    bv.getTable_barang().setValueAt(Integer.valueOf(String.valueOf(bv.getStok().getValue())) + Integer.valueOf(String.valueOf(bv.getTable_barang().getValueAt(i, 3))), i, 3);
                    bv.getNama_barang_txt().requestFocus();
                    break;
                } else if (i + 1 == count) {
                }
            }*/
        }
    }

    public void hapus() {
        bm = new barangModel();
        bm.setKode_barang(bv.getKode_barang_txt().getText());
        int p = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data", "Hapus", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            bi.delete(bm);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Hapus", JOptionPane.INFORMATION_MESSAGE);
            bAwal();
            autoID();
        } else {
            JOptionPane.showMessageDialog(null, "Hapus Data Dibatalkan", "Hapus", JOptionPane.INFORMATION_MESSAGE);
            bv.getBtn_hapus().requestFocus();
        }

    }

    public void cari() {

        lbm = bi.getcariBarang(bv.getCari_txt().getText());
        tabelbarangModel tbm = new tabelbarangModel(lbm);
        TableRowSorter<tabelbarangModel> sorter = new TableRowSorter<>(tbm);
        bv.getTable_barang().setRowSorter(sorter);
        bv.getTable_barang().setModel(tbm);
    }

    public void cariBarang() {
        lbm = bi.getcariBarangSebagian(cbv.getCari_txt().getText());
        tabelbarangModel tbm = new tabelbarangModel(lbm);
        TableRowSorter<tabelbarangModel> sorter = new TableRowSorter<>(tbm);
        cbv.getTable_barang().setRowSorter(sorter);
        cbv.getTable_barang().setModel(tbm);
    }

    public void autoID() {
        bm = new barangModel();
        bi.autoID(bm);
        bv.getKode_barang_txt().setText(bm.getKode_barang());

    }

    public void warna() {
        bv.getTable_barang().setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component label = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (bv.getTable_barang().getValueAt(row, 3).equals(0)) {
                    //jika stok 0
                    label.setBackground(Color.RED);
                } else {
                    label.setBackground(null);
                }
                if (isSelected) {
                    label.setBackground(Color.getHSBColor(0.57364345F, 1.0F, 0.84313726F));
                }
                return label;
            }
        });
    }

}
//akhir untuk barang
