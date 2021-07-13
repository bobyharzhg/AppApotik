package Controller;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
public class Controller implements ActionListener{
    Obat mo;
    Transaksi mt;
    Admin ma;
    Login vl;
    Menu vm;
    ObatIn vo;
    TransIn vt;
    trnController ct;
    obtController co;
    lgController cl;

    public Controller(Obat mo, Transaksi mt, Admin ma, Login vl, Menu vm, 
            ObatIn vo, TransIn vt, trnController ct, obtController co, lgController cl) {
        this.mo = mo;
        this.mt = mt;
        this.ma = ma;
        this.vl = vl;
        this.vm = vm;
        this.vo = vo;
        this.vt = vt;
        this.ct = ct;
        this.co = co;
        this.cl = cl;
        this.vl.tamLogin.addActionListener(this);
        this.vm.dataTransaksi.addActionListener(this);
        this.vm.dataObat.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vl.tamLogin) {
            String uname = this.vl.username.getText();
            String pass = new String(this.vl.password.getPassword());
            
//            this.cl.log(uname,pass);
            
            if (uname.equals("a") && pass.equals("a")) {
                JOptionPane.showMessageDialog(vl, "Login berhasil");
                this.vl.setVisible(false);
                this.ct.namaObat();
                this.vm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vl, "Username atau password salah.");
            } //                try{
//                    java.sql.Connection conn=(Connection)Connector.configDB();
//                    java.sql.Statement stm=conn.createStatement();
//                    String sql="SELECT username, password FROM login WHERE username='"+uname+"' and password='"+pass+"'";
//                    java.sql.ResultSet res=stm.executeQuery(sql);
//                
//                if (res.next()){
//                    JOptionPane.showMessageDialog(null, "Login Berhasil");
//                    Menu vm =new Menu();
//                    vm.setVisible(true);
//                    vl.setVisible(false);
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Login Gagal");
//                    cl.reset();
//                }
//            }
//            catch(Exception ae){
//                JOptionPane.showMessageDialog(null, ae);
//            }
        } else if (e.getSource() == this.vm.dataTransaksi) {
            this.vm.setVisible(false);
            this.ct.tampil();
            this.ct.reset();
            this.vt.setVisible(true);
        } else if (e.getSource() == this.vm.dataObat) {
            this.vm.setVisible(false);
            this.co.tampil();
            this.co.reset();
            this.vo.setVisible(true);
        }
    }  
}
