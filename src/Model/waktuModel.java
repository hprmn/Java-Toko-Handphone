package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class waktuModel {
    //Deklarasi Atribut
    private Calendar cal;
    private Integer h,m,s;
    
    //Konstruktor model dengan parameter JFrame
    public waktuModel(JFrame tengah){
        //untuk mengatur posisi Jframe tepat ditengah layar
        tengah.setLocationRelativeTo(tengah);
    }
    //Konstruktor model dengan parameter JDialog
    public waktuModel(JDialog tengah){
        //untuk mengatur posisi JDialog tepat ditengah layar
        tengah.setLocationRelativeTo(tengah);
    }
    //method jam dengan parameter JLabel
    public void jamSekarang(JLabel namaclass){
        //Deklarasi class thread dengan objek jam sekarang
        Thread jamSekarang = new Thread(){
            //Deklarasi ulang method run dengan merubah valuenya
            @Override
            public void run(){
                //mencoba menjalankan syntax
                try{
                    
                    while(true){
                        //Deklarasi cal dengan Konstruktor GregorianCalendar
                        //GregorianCalendar : sistem kalender standar
                        cal = new GregorianCalendar();
                        //Deklarasi Atribut
                        h = cal.get(Calendar.HOUR_OF_DAY);
                        m = cal.get(Calendar.MINUTE);
                        s = cal.get(Calendar.SECOND);

                        namaclass.setText(h+" : "+m+" : "+s);
                    }
                    //menangkap kesalahan pada saat mencoba menjalankan syntax
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        };
        //memangil method start yang ada di class thread 
        jamSekarang.start();
    }
    //method menampilkan tanggal sekarang
    public String gettanggal(){
        //membuat format tanggal
        DateFormat dateFormat = new SimpleDateFormat("EEEE:dd/MM/yyyy"); 
        Date date = new Date(); 
        return dateFormat.format(date); 
        
    }
    
}
