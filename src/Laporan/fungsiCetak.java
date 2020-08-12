/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laporan;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HPrmn
 */
public class fungsiCetak {

    Connection conn;

    public fungsiCetak(String namaReport) {
        try {
            conn = koneksi.getConnection();

            File reportFile = new File(namaReport);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
