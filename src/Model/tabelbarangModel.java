/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.barangModel.lokasiModel;
import Model.barangModel.merkModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HPrmn
 */
public class tabelbarangModel extends AbstractTableModel {

    private List<barangModel> lbm=null;

    public tabelbarangModel() {
    }
    
    public tabelbarangModel(List<barangModel> lbm) {
        this.lbm = lbm;
    }
    
    @Override
    public int getRowCount() {
        return lbm.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0  : return lbm.get(rowIndex).getKode_barang();
            case 1  : return lbm.get(rowIndex).getNama_barang();
            case 2  : return lbm.get(rowIndex).getMerk();
            case 3  : return lbm.get(rowIndex).getStok();
            case 4  : return String.format("%,.0f",lbm.get(rowIndex).getHarga_beli());
            case 5  : return String.format("%,.0f",lbm.get(rowIndex).getHarga_jual());
            case 6  : return lbm.get(rowIndex).getLokasi();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0  : return "Kode Barang";
            case 1  : return "Nama Barang";
            case 2  : return "Merk";
            case 3  : return "Stok";
            case 4  : return "Harga Beli";
            case 5  : return "Harga Jual";
            case 6  : return "Lokasi";
            default : return null;
        }
    }
    
    public barangModel get(int index){
        return lbm.get(index);
    }
    //model tabel untuk merk
    public class tabelmerkModel extends AbstractTableModel{

        private List<merkModel> lmm;

        public tabelmerkModel(List<merkModel> lmm) {
            this.lmm = lmm;
        }
        
        @Override
        public int getRowCount() {
            return lmm.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        @Override
        public String getColumnName(int column){
            switch(column){
                case 0 : return "Kode";
                case 1 : return "Nama Merk";
                default : return null;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0  : return lmm.get(rowIndex).getKodeMerk();
                case 1  : return lmm.get(rowIndex).getNamaMerk();
                default : return null;
            }
        }
        
        public merkModel get(int index){
            return lmm.get(index);
        }
        
    }
    //model tabel untuk lokasi penyimpanan
    public class tabellokasiModel extends AbstractTableModel{

        private final List<lokasiModel> llm;

        public tabellokasiModel(List<lokasiModel> llm) {
            this.llm = llm;
        }
        
        
        @Override
        public int getRowCount() {
            return llm.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        @Override
        public String getColumnName(int column){
            switch(column){
                case 0  : return "Kode";
                case 1  : return "Nama Lokasi";
                default : return null;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0  : return llm.get(rowIndex).getKodeLokasi();
                case 1  : return llm.get(rowIndex).getNamaLokasi();
                default : return null;
            }
        }
        public lokasiModel get(int index){
            return llm.get(index);
        }
        
    }
    
}
