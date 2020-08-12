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
public class tabeldetailpenjualanModel extends AbstractTableModel{
    private final List<transaksiModel> ltm;

    public tabeldetailpenjualanModel(List<transaksiModel> ltm) {
        this.ltm = ltm;
    }

    @Override
    public int getRowCount() {
        return ltm.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0  : return ltm.get(rowIndex).getKode_barang();
            case 1  : return ltm.get(rowIndex).getJumlah();
            case 2  : return ltm.get(rowIndex).getHarga_jual();
            case 3  : return ltm.get(rowIndex).getTotal();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0  : return "Kode Barang";
            case 1  : return "Jumlah";
            case 2  : return "Harga Satuan";
            case 3  : return "Sub Total";
            default : return null;
        }
    }
    public transaksiModel get(int index){
        return ltm.get(index);
    }
    
    
}
