/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HPrmn
 */
public class tabelpenjualanModel extends AbstractTableModel{

    
    private final List<transaksiModel> ltm;

    public tabelpenjualanModel(List<transaksiModel> ltm) {
        this.ltm = ltm;
    }

    
    
    
    
    
    @Override
    public int getRowCount() {
        return ltm.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0  : return ltm.get(rowIndex).getKasir();
            case 1  : return ltm.get(rowIndex).getKode_transaksi();
            case 2  : return ltm.get(rowIndex).getTanggal();
            case 3  : return ltm.get(rowIndex).getGrandtotal();
            case 4  : return ltm.get(rowIndex).getBayar();
            case 5  : return ltm.get(rowIndex).getKembali();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0  : return "NIP";
            case 1  : return "Kode Transaksi";
            case 2  : return "Tanggal Transaksi";
            case 3  : return "Total Harga";
            case 4  : return "Pembayaran";
            case 5  : return "Kembalian";
            default : return null;
        }
    }
    public transaksiModel get(int index){
        return ltm.get(index);
    }
    
}
