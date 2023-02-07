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
    Member mm;
    Login vl;
    Menu vm;
    ObatIn vo;
    TransIn vt;
    MemIn vmm;
    trnController ct;
    obtController co;
    lgController cl;
    memController cm;

    public Controller(Obat mo, Transaksi mt, Admin ma, Member mm, Login vl, Menu vm, 
        ObatIn vo, TransIn vt, MemIn vmm, trnController ct, obtController co, lgController cl, memController cm) {
        this.mo = mo;
        this.mt = mt;
        this.ma = ma;
        this.mm = mm;
        this.vl = vl;
        this.vm = vm;
        this.vo = vo;
        this.vt = vt;
        this.vmm = vmm;
        this.ct = ct;
        this.co = co;
        this.cl = cl;
        this.cm = cm;
        
        this.vl.tamLogin.addActionListener(this);
        this.vm.dataTransaksi.addActionListener(this);
        this.vm.dataObat.addActionListener(this);
        this.vm.dataMember.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vl.tamLogin) {
            String uname = this.vl.username.getText();
            String pass = new String(this.vl.password.getText());
            try{
                java.sql.Connection conn=(Connection)Connector.configDB();
                java.sql.Statement stm=conn.createStatement();
                String sql="select * from login where username='"+uname+"' and password='"+pass+"'";
                java.sql.ResultSet res=stm.executeQuery(sql);
                
                if (res.next()){
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    vl.setVisible(false);
                    vm.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Login Gagal ");
                }
            }
            catch(Exception ae){
                JOptionPane.showMessageDialog(null, ae);
            }
        } else if (e.getSource() == this.vm.dataTransaksi) {
            this.vm.setVisible(false);
            this.ct.tampil();
            this.ct.namaObat();
            this.ct.idMember();
            this.ct.reset();
            this.vt.setVisible(true);
        } else if (e.getSource() == this.vm.dataObat) {
            this.vm.setVisible(false);
            this.co.tampil();
            this.co.reset();
            this.vo.setVisible(true);
        } else if (e.getSource() == this.vm.dataMember) {
            this.vm.setVisible(false);
            this.cm.tampil();
            this.cm.reset();
            this.vmm.setVisible(true);
        }
    }  
}
