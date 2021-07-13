package APPapotik;

import Model.*;
import View.*;
import Controller.*;

public class APPapotik {
    public static void main(String[] args) {
        Obat mo = new Obat();
        Transaksi mt = new Transaksi();
        Admin ma = new Admin();
        Login vl = new Login();
        Menu vm = new Menu();
        ObatIn vo = new ObatIn();
        TransIn vt = new TransIn();
        trnController ct = new trnController(vm, vt, mt);
        obtController co = new obtController(vm, vo, mo);
        lgController cl = new lgController(ma, vl);
        
        Controller c = new Controller(mo,mt,ma,vl,vm,vo,vt,ct,co,cl);
        
        vl.setVisible(true);
    }
}
