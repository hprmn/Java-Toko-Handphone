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
public class tabeluserModel extends AbstractTableModel{

    private List<userModel> lum =null;

    public tabeluserModel(List<userModel> lum) {
        this.lum = lum;
    } 
    @Override
    public int getRowCount() {
        return lum.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0  : return lum.get(rowIndex).getKode_user();
            case 1  : return lum.get(rowIndex).getNip();
            case 2  : return lum.get(rowIndex).getUsername();
            case 3  : return lum.get(rowIndex).getPassword();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0  : return "Kode";
            case 1  : return "NIP";
            case 2  : return "Username";
            case 3  : return "Password";
            default : return null;
        }
    }
    public userModel get(int index){
        return lum.get(index);
    }
    
}
