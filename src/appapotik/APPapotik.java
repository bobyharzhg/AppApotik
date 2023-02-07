package APPapotik;

import Model.*;
import View.*;
import Controller.*;

public class APPapotik {
    public static void main(String[] args) {
        Obat mo = new Obat();
        Transaksi mt = new Transaksi();
        Admin ma = new Admin();
        Member mm = new Member();
        Login vl = new Login();
        Menu vm = new Menu();
        ObatIn vo = new ObatIn();
        TransIn vt = new TransIn();
        MemIn vmm = new MemIn();
        trnController ct = new trnController(vm, vt, mt);
        obtController co = new obtController(vm, vo, mo);
        lgController cl = new lgController(ma, vl);
        memController cm = new memController(vm, vmm, mm);
        
        Controller c = new Controller(mo,mt,ma,mm,vl,vm,vo,vt,vmm,ct,co,cl,cm);
        
        vl.setVisible(true);
    }
}
