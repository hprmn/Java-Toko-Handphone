/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Interface.transaksiInterface;
import Model.tabeldetailpenjualanModel;
import Model.tabelpenjualanModel;
import Model.transaksiModel;
import View.detailpenjualanView;
import View.penjualanView;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import koneksi.koneksi;

/**
 *
 * @author HPrmn
 */
public class transaksiController {

    transaksiModel tm;
    penjualanView pv;
    detailpenjualanView dp;
    transaksiInterface ti;
    ArrayList<transaksiModel> atm = new ArrayList<>();
    DefaultTableModel tabelmodel;
    List<transaksiModel> lpm;

    public transaksiController(penjualanView pv) {
        this.pv = pv;
        ti = koneksi.getTransaksiInterface();
        pv.getTanggal().setText(getTanggal("dd-MMM-yyyy"));
    }

    public transaksiController(detailpenjualanView dp) {
        this.dp = dp;
        ti = koneksi.getTransaksiInterface();
        ti.getAll();
    }

    public void getTabelPenjualan() {
        lpm = ti.getAll();
        tabelpenjualanModel tpm = new tabelpenjualanModel(lpm);
        TableRowSorter sorter = new TableRowSorter(tpm);
        dp.getT_penjualan().setRowSorter(sorter);
        dp.getT_penjualan().setModel(tpm);
    }

    public void cariDetail(String cari) {
        lpm = ti.cariKodePenjualan(cari);
        tabeldetailpenjualanModel tdpm = new tabeldetailpenjualanModel(lpm);
        TableRowSorter sorter = new TableRowSorter(tdpm);
        dp.getT_detail_penjualan().setRowSorter(sorter);
        dp.getT_detail_penjualan().setModel(tdpm);
    }

    public void autoID() {
        tm = new transaksiModel();
        ti.autoKode(tm, getTanggal("yyyyMMdd"));
        pv.getKodeTransaksi().setText(tm.getKode_transaksi());
    }

    public static String getTanggal(String nama) {
        //membuat format tanggal
        DateFormat dateFormat = new SimpleDateFormat(nama);
        Date date = new Date();
        return dateFormat.format(date);

    }

    public void tambah() {
        tm = new transaksiModel();
        tm.setJumlah(Integer.valueOf(pv.getJml_unit().getText()));
        tm.setStok(Integer.valueOf(pv.getStok().getText()));
        if (tm.getJumlah() > tm.getStok()) {
            JOptionPane.showMessageDialog(null, "Jumlah Unit Tidak Boleh Melebihi Stok");
            pv.getJml_unit().requestFocus();
        } else {
            tm.setKode_barang(pv.getKd_barang().getText());
            tm.setNama_barang(pv.getNama_barang().getText());
            tm.setHarga_jual(Double.parseDouble(pv.getHarga_satuan().getText().replace(".", "")));
            tm.setJumlah(Integer.parseInt(pv.getJml_unit().getText()));
            System.out.println(tm.getHarga_akhir());
            atm.add(tm);
            updateStok("kurang");
            System.out.println(getStok(tm.getKode_barang()));
            //getGrandtotal();
            tampilsementara();
            refresh();
        }

    }

    public void tampilsementara() {
        String kolom[] = {"Kode Barang", "Nama Barang", "Harga Satuan", "Jumlah Unit", "Sub Total", "Aksi"};
        Object objData[][] = new Object[atm.size()][4];
        DecimalFormat df = new DecimalFormat();
        int i = 0;
        for (transaksiModel dtm : atm) {
            Object[] arrData = {dtm.getKode_barang(), dtm.getNama_barang(), df.format(dtm.getHarga_jual()), dtm.getJumlah(), df.format(dtm.getHarga_akhir()), "Hapus"};
            objData[i] = arrData;
            i++;
        }
        tabelmodel = new DefaultTableModel(objData, kolom);
        pv.getT_penjualan().setModel(tabelmodel);
        pv.getT_penjualan().setRowHeight(30);
        pv.getT_penjualan().getColumnModel().getColumn(5).setCellRenderer(new buttonRenderer());
        pv.getT_penjualan().getColumnModel().getColumn(5).setCellEditor(new buttonEditor(new JCheckBox()));
    }

    public void getGrandtotal() {
        int jum = pv.getT_penjualan().getRowCount();
        tm = new transaksiModel();
        tm.setTotal(0.0);
        for (int i = 0; i < jum; i++) {
            tm.setJumlah(Integer.valueOf(pv.getT_penjualan().getValueAt(i, 3).toString()));
            tm.setHarga_jual(Double.valueOf(pv.getT_penjualan().getValueAt(i, 2).toString().replace(".", "")));
            System.out.println(tm.getTotal());
            System.out.println(tm.getHarga_akhir());
            tm.setTotal(tm.getTotal() + tm.getHarga_akhir());
        System.out.println(pv.getT_penjualan().getValueAt(i, 2)+"Hadi");
        }
        DecimalFormat df = new DecimalFormat();
        System.out.println(df.format(tm.getTotal()));
        
        pv.getTotalharga().setText(df.format(tm.getTotal()));
    }

    public void refresh() {
        pv.getKd_barang().setText("");
        pv.getStok().setText("");
        pv.getHarga_satuan().setText("0");
        pv.getJml_unit().setText("");
        pv.getNama_barang().setText("");
    }

    public void getKembalian() {
        tm = new transaksiModel();
        tm.setBayar(Double.valueOf(pv.getBayar().getText().replace(".", "")));
        tm.setGrandtotal(Double.valueOf(pv.getTotalharga().getText().replace(".", "")));
        DecimalFormat df = new DecimalFormat();
        pv.getKembalian().setText(df.format(tm.getBayar() - tm.getGrandtotal()));
        System.out.println(df.format(tm.getBayar() - tm.getGrandtotal()));
    }

    public void hapusAll() {
        updateStok1();
        atm.removeAll(atm);
        pv.getTotalharga().setText("0");
    }

    public Integer getStok(String kode) {
        tm = new transaksiModel();
        tm.setKode_barang(kode);
        //System.out.println(ti.getStok(tm));
        return ti.getStok(tm);
    }

    public void updateStok(String nama) {
        tm = new transaksiModel();
        if (nama.equals("tambah")) {
            int row = pv.getT_penjualan().getSelectedRow();
            tm.setKode_barang(pv.getT_penjualan().getValueAt(row, 0).toString());
            tm.setJumlah(Integer.valueOf(pv.getT_penjualan().getValueAt(row, 3).toString()));
            //System.out.println(tm.getJumlah());
            //System.out.println(getStok(tm.getKode_barang())+"hadi");
            //tm.setStok(getStok(tm.getKode_barang()) + tm.getJumlah());
            Integer jumlah = tm.getJumlah();
            Integer stok = getStok(tm.getKode_barang());
            System.out.println(stok);
            tm.setStok(stok + jumlah);
            System.out.println(tm.getStok());
        } else if (nama.equals("kurang")) {
            tm.setKode_barang(pv.getKd_barang().getText());
            tm.setStok(Integer.valueOf(pv.getStok().getText()));
            tm.setJumlah(Integer.valueOf(pv.getJml_unit().getText()));
            tm.setStok(tm.getStok() - tm.getJumlah());
        }
        ti.updateStok(tm);
    }

    public void updateStok1() {
        tm = new transaksiModel();
        int count = pv.getT_penjualan().getRowCount();
        for (int i = 0; i < count; i++) {
            tm.setKode_barang(pv.getT_penjualan().getValueAt(i, 0).toString());
            tm.setJumlah(Integer.valueOf(pv.getT_penjualan().getValueAt(i, 3).toString()));
            Integer jumlah = tm.getJumlah();
            Integer stok = getStok(tm.getKode_barang());
            tm.setStok(stok + jumlah);
            System.out.println(tm.getStok());
            ti.updateStok(tm);
        }
    }

    public void simpan() {
        tm = new transaksiModel();
        tm.setKasir(pv.getNipkasir().getText());
        tm.setTanggal(getTanggal("yyyy-MM-dd"));
        tm.setKode_transaksi(pv.getKodeTransaksi().getText());
        tm.setGrandtotal(Double.valueOf(pv.getTotalharga().getText().replace(".", "")));
        tm.setBayar(Double.valueOf(pv.getBayar().getText().replace(".", "")));
        tm.setKembali(Double.valueOf(pv.getKembalian().getText().replace(".", "")));
        System.out.println(tm.getKasir());
        ti.simpan(tm);
        simpandetail();
        JOptionPane.showMessageDialog(null, "Transaksi Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
    }

    public void simpandetail() {
        tm = new transaksiModel();
        Integer count = pv.getT_penjualan().getRowCount();
        for (int i = 0; i < count; i++) {
            tm.setKode_transaksi(pv.getKodeTransaksi().getText());
            tm.setKode_barang(pv.getT_penjualan().getValueAt(i, 0).toString());
            tm.setHarga_jual(Double.valueOf(pv.getT_penjualan().getValueAt(i, 2).toString().replace(".", "")));
            tm.setJumlah(Integer.valueOf(pv.getT_penjualan().getValueAt(i, 3).toString()));
            tm.setTotal(Double.valueOf(pv.getT_penjualan().getValueAt(i, 4).toString().replace(".", "")));
            ti.simpanDetail(tm);

        }
    }

    public class buttonEditor extends DefaultCellEditor {

        public JButton btn;
        private String label;
        private Boolean clicked;

        public buttonEditor(JCheckBox textField) {
            super(textField);

            btn = new JButton();
            btn.setOpaque(true);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //ketika button di klik
            btn.addActionListener((ActionEvent e) -> {
                try {
                    fireEditingStopped();
                } catch (ArrayIndexOutOfBoundsException em) {
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            if (isSelected) {
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(table.getBackground());
            }

            label = (value == null) ? "" : value.toString();
            btn.setText(label);
            clicked = true;
            return btn;
        }

        @Override
        public Object getCellEditorValue() {

            if (clicked) {
                DefaultTableModel model = (DefaultTableModel) pv.getT_penjualan().getModel();
                int row = pv.getT_penjualan().getSelectedRow();
                updateStok("tambah");
                atm.remove(row);
                tampilsementara();
                getGrandtotal();
                /*if (model instanceof DefaultTableModel) {
                    ((DefaultTableModel) model).removeRow(row);                
                }*/

            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

    }
}

class buttonRenderer extends JButton implements TableCellRenderer {

    public buttonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());

        return this;
    }

}
