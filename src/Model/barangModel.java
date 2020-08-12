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
public class barangModel {
    //Attribut
    String kode_barang,nama_barang,merk,lokasi;
    Integer stok;
    Double harga_beli;
    Double harga_jual;
    
    //Method Getter Setter

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Double getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(Double harga_beli) {
        this.harga_beli = harga_beli;
    }

    public Double getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(Double harga_jual) {
        this.harga_jual = harga_jual;
    }
    //class merk
    public class merkModel{
        Integer kodeMerk;
        String namaMerk;

        public Integer getKodeMerk() {
            return kodeMerk;
        }

        public void setKodeMerk(Integer kodeMerk) {
            this.kodeMerk = kodeMerk;
        }

        public String getNamaMerk() {
            return namaMerk;
        }

        public void setNamaMerk(String namaMerk) {
            this.namaMerk = namaMerk;
        }
        
    }
    
    //class lokasi
    public class lokasiModel {
        Integer kodeLokasi;
        String namaLokasi;

        public lokasiModel() {
        }
        
        public Integer getKodeLokasi() {
            return kodeLokasi;
        }

        public void setKodeLokasi(Integer kodeLokasi) {
            this.kodeLokasi = kodeLokasi;
        }

        public String getNamaLokasi() {
            return namaLokasi;
        }

        public void setNamaLokasi(String namaLokasi) {
            this.namaLokasi = namaLokasi;
        }


    }    
    
}
