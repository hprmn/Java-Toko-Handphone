/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import Model.userModel;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author HPrmn
 */
public interface userInterface {
    void insert(userModel um);
    void update(userModel um);
    void delete(userModel um);
    void cekAkses(userModel um);
    Boolean cekLogin(userModel um);
    List<userModel> getAll();
    List<userModel> cariUser(String cari);
}
