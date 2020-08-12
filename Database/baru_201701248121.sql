-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: db_toko_penjualan_handphone
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `db_toko_penjualan_handphone`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_penjualan_handphone` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_penjualan_handphone`;

--
-- Table structure for table `t_barang`
--

DROP TABLE IF EXISTS `t_barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_barang` (
  `kode_barang` char(8) NOT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `harga_beli` double DEFAULT NULL,
  `harga_jual` double DEFAULT NULL,
  `kode_merk` int(11) DEFAULT NULL,
  `kode_lokasi` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_barang`),
  KEY `kode_merk` (`kode_merk`),
  KEY `kode_lokasi` (`kode_lokasi`),
  CONSTRAINT `t_barang_ibfk_1` FOREIGN KEY (`kode_merk`) REFERENCES `t_merk` (`kode_merk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_barang_ibfk_2` FOREIGN KEY (`kode_lokasi`) REFERENCES `t_lokasi` (`kode_lokasi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_barang`
--

LOCK TABLES `t_barang` WRITE;
/*!40000 ALTER TABLE `t_barang` DISABLE KEYS */;
INSERT INTO `t_barang` VALUES ('HP0001','XPERIA Z3',1,1350000,1450000,1,5),('HP0002','Redmi 2',2,1500000,1600000,4,4),('HP0003','Galaxy J1',3,1100000,1150000,2,4),('HP0004','Galaxy V Plus',2,1000000,1100000,2,5),('HP0005','Redmi 4',2,1500000,1600000,4,5);
/*!40000 ALTER TABLE `t_barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_detail_penjualan`
--

DROP TABLE IF EXISTS `t_detail_penjualan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_detail_penjualan` (
  `kode_penjualan` char(12) DEFAULT NULL,
  `kode_barang` char(8) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  KEY `kode_penjualan` (`kode_penjualan`),
  KEY `kode_barang` (`kode_barang`),
  CONSTRAINT `t_detail_penjualan_ibfk_1` FOREIGN KEY (`kode_penjualan`) REFERENCES `t_penjualan` (`kode_penjualan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_detail_penjualan_ibfk_2` FOREIGN KEY (`kode_barang`) REFERENCES `t_barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_detail_penjualan`
--

LOCK TABLES `t_detail_penjualan` WRITE;
/*!40000 ALTER TABLE `t_detail_penjualan` DISABLE KEYS */;
INSERT INTO `t_detail_penjualan` VALUES ('201701080001','HP0001',2,1450000,2900000),('201701090002','HP0001',2,1450000,2900000),('201701090002','HP0004',1,13123,13123),('201701090003','HP0004',2,13123,26246),('201701090003','HP0005',1,13123,13123),('201701090004','HP0004',1,13123,13123),('201701090004','HP0005',2,13123,26246),('201701230008','HP0001',1,1450000,1450000),('201701230009','HP0001',1,1450000,1450000),('201701230010','HP0003',2,1150000,2300000),('201701230011','HP0002',1,1600000,1600000);
/*!40000 ALTER TABLE `t_detail_penjualan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_jabatan`
--

DROP TABLE IF EXISTS `t_jabatan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_jabatan` (
  `kode_jabatan` int(11) NOT NULL AUTO_INCREMENT,
  `nama_jabatan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode_jabatan`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_jabatan`
--

LOCK TABLES `t_jabatan` WRITE;
/*!40000 ALTER TABLE `t_jabatan` DISABLE KEYS */;
INSERT INTO `t_jabatan` VALUES (8,'KASIR'),(9,'ADMINISTRATOR'),(39,'BAG. GUDANG');
/*!40000 ALTER TABLE `t_jabatan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_lokasi`
--

DROP TABLE IF EXISTS `t_lokasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_lokasi` (
  `kode_lokasi` int(11) NOT NULL AUTO_INCREMENT,
  `nama_lokasi` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kode_lokasi`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_lokasi`
--

LOCK TABLES `t_lokasi` WRITE;
/*!40000 ALTER TABLE `t_lokasi` DISABLE KEYS */;
INSERT INTO `t_lokasi` VALUES (4,'RAK 1'),(5,'RAK 2'),(6,'RAK 3');
/*!40000 ALTER TABLE `t_lokasi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_merk`
--

DROP TABLE IF EXISTS `t_merk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_merk` (
  `kode_merk` int(11) NOT NULL AUTO_INCREMENT,
  `nama_merk` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode_merk`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_merk`
--

LOCK TABLES `t_merk` WRITE;
/*!40000 ALTER TABLE `t_merk` DISABLE KEYS */;
INSERT INTO `t_merk` VALUES (1,'SONY'),(2,'SAMSUNG'),(4,'XIAOMI');
/*!40000 ALTER TABLE `t_merk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pegawai`
--

DROP TABLE IF EXISTS `t_pegawai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pegawai` (
  `nip` int(11) NOT NULL,
  `nama_p` varchar(50) DEFAULT NULL,
  `alamat_p` varchar(255) DEFAULT NULL,
  `jenis_kelamin` char(1) DEFAULT NULL,
  `tahun_masuk` year(4) DEFAULT NULL,
  `kode_jabatan` int(11) DEFAULT NULL,
  PRIMARY KEY (`nip`),
  KEY `kode_jabatan` (`kode_jabatan`),
  CONSTRAINT `t_pegawai_ibfk_1` FOREIGN KEY (`kode_jabatan`) REFERENCES `t_jabatan` (`kode_jabatan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pegawai`
--

LOCK TABLES `t_pegawai` WRITE;
/*!40000 ALTER TABLE `t_pegawai` DISABLE KEYS */;
INSERT INTO `t_pegawai` VALUES (12340001,'hadi permana','bandung','L',2016,9),(12340002,'Reynaldi','bandung','L',2016,39),(12340003,'Asep Wilayana','garut','L',2017,8);
/*!40000 ALTER TABLE `t_pegawai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_penjualan`
--

DROP TABLE IF EXISTS `t_penjualan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_penjualan` (
  `kode_penjualan` char(12) NOT NULL,
  `tanggal_penjualan` date DEFAULT NULL,
  `total_harga` double DEFAULT NULL,
  `tunai` double DEFAULT NULL,
  `kembali` double DEFAULT NULL,
  `nip` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_penjualan`),
  KEY `nip` (`nip`),
  CONSTRAINT `t_penjualan_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `t_pegawai` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_penjualan`
--

LOCK TABLES `t_penjualan` WRITE;
/*!40000 ALTER TABLE `t_penjualan` DISABLE KEYS */;
INSERT INTO `t_penjualan` VALUES ('201701080001','2017-01-08',2900000,3000000,100000,12340001),('201701090002','2017-01-09',2913123,3000000,86877,12340001),('201701090003','2017-01-09',39369,40000,631,12340001),('201701090004','2017-01-09',39369,40000,631,12340001),('201701230005','2017-01-23',2900000,2900000,0,12340001),('201701230006','2017-01-23',2900000,2900000,0,12340001),('201701230007','2017-01-23',2900000,2900000,0,12340001),('201701230008','2017-01-23',1450000,1450000,0,12340001),('201701230009','2017-01-23',1450000,1450000,0,12340001),('201701230010','2017-01-23',2300000,2300000,0,12340001),('201701230011','2017-01-23',1600000,1600000,0,12340001);
/*!40000 ALTER TABLE `t_penjualan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `kode_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nip` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_user`),
  UNIQUE KEY `username` (`username`),
  KEY `nip` (`nip`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `t_pegawai` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'hadi','hadipermana',12340001),(4,'asep','1234',12340003);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-24 20:33:24
