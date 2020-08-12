/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author HPrmn
 */
public class pegawaiModel {
    private String nama,alamat,jk,jabatan;
    private Integer nip,tahun_masuk;

    public Integer getNip() {
        return nip;
    }

    public void setNip(Integer nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    public Integer getTahun_masuk() {
        return tahun_masuk;
    }

    public void setTahun_masuk(Integer tahun_masuk) {
        this.tahun_masuk = tahun_masuk;
    }
    
    
    public class jabatanModel{
        Integer kodeJabatan;
        String namaJabatan;

        public Integer getKodeJabatan() {
            return kodeJabatan;
        }

        public void setKodeJabatan(Integer kodeJabatan) {
            this.kodeJabatan = kodeJabatan;
        }

        public String getNamaJabatan() {
            return namaJabatan;
        }

        public void setNamaJabatan(String namaJabatan) {
            this.namaJabatan = namaJabatan;
        }
    }
    
}
