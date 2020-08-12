/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.pegawaiModel.jabatanModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HPrmn
 */
public class tabelpegawaiModel extends AbstractTableModel{
    
    private final List<pegawaiModel> lpm;

    public tabelpegawaiModel(List<pegawaiModel> lpm) {
        this.lpm = lpm;
    }
    

    @Override
    public int getRowCount() {
        return lpm.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0  : return lpm.get(rowIndex).getNip();
            case 1  : return lpm.get(rowIndex).getNama();
            case 2  : return lpm.get(rowIndex).getJk();
            case 3  : return lpm.get(rowIndex).getJabatan();
            case 4  : return lpm.get(rowIndex).getTahun_masuk();
            case 5  : return lpm.get(rowIndex).getAlamat();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0  : return "NIP";
            case 1  : return "Nama Pegawai";
            case 2  : return "Jenis Kelamin";
            case 3  : return "Jabatan";
            case 4  : return "Tahun Masuk";
            case 5  : return "Alamat";
            default : return null;
        }
    }
    
    public pegawaiModel get(int index){
        return lpm.get(index);
    }
    
    public class tabelJabatanModel extends AbstractTableModel{
        
        private final List<jabatanModel> ljm;

        public tabelJabatanModel(List<jabatanModel> ljm) {
            this.ljm = ljm;
        }
        

        @Override
        public int getRowCount() {
            return ljm.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0  : return ljm.get(rowIndex).getKodeJabatan();
                case 1  : return ljm.get(rowIndex).getNamaJabatan();
                default : return null;
            }
        }
        
        @Override
        public String getColumnName(int column){
            switch(column){
                case 0  : return "Kode";
                case 1  : return "Nama Jabatan";
                default : return null;
            }
        }
        
        public jabatanModel get(int index){
            return ljm.get(index);
        }
        
    }
    
}
