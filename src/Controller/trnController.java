package Controller;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
public class trnController implements ActionListener, MouseListener{
    Menu vm;
    TransIn vt;
    Transaksi mt;

    public trnController(Menu vm, TransIn vt, Transaksi mt) {
        this.vm = vm;
        this.vt = vt;
        this.mt= mt;
        this.vt.batal.addActionListener(this);
        this.vt.ubah.addActionListener(this);
        this.vt.kembali.addActionListener(this);
        this.vt.simpan.addActionListener(this);
        this.vt.delete.addActionListener(this);
        this.vt.table.addMouseListener(this);
    }

    public void reset(){
        vt.id.setEditable(true);
        vt.id.setText(null);
        vt.member.setSelectedIndex(0);
        vt.tgl.setDate(null);
        vt.jml.setText(null);
    }
    
    public void namaObat() {
        String sql = "SELECT nama_obat FROM obat";

        try {
            Connection con = (Connection)Connector.configDB();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            int itemCount = vt.obat.getItemCount();
            for(int i = 0; i < itemCount; i++){
                vt.obat.removeItemAt(0);
            }
            while (res.next()) {
                this.vt.obat.addItem(res.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vt, e);
        }
    }
    
    public void idMember() {
        String sql = "SELECT tingkat_member FROM member";

        try {
            Connection con = (Connection) Connector.configDB();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            int itemCount = vt.member.getItemCount();
            for(int i = 0; i < itemCount; i++){
                vt.member.removeItemAt(0);
            }
            while (res.next()) {
                this.vt.member.addItem(res.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vt, e);
        }
    }

    public void tampil() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Tingkatan member");
        model.addColumn("Tanggal Jual");
        model.addColumn("Nama Obat");
        model.addColumn("Jumlah jual");
        model.addColumn("Total harga");
        
        try{
            String sql="Select id, t.tingkat_member, tgl_transaksi,t.nama_obat,jml_jual,(jml_jual*(harga-(harga*(diskon/100))))"
                    + "from transaksi t, obat o, member m where t.nama_obat = o.nama_obat and t.tingkat_member = m.tingkat_member ORDER BY id asc";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getFloat(6)});
            }
            vt.table.setModel(model);
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vt.kembali) {
            this.vt.setVisible(false);
            this.vm.setVisible(true);
        } else if (e.getSource() == this.vt.batal) {
            this.reset();
        } else if (e.getSource() == this.vt.simpan) {
            mt.setObat((String) vt.obat.getSelectedItem());
            mt.setMember((String) vt.member.getSelectedItem());
            mt.setTgl(vt.tgl.getDate());
            mt.setJml(Integer.parseInt(vt.jml.getText()));
            
            try {
                if (this.mt.tambah()) {
                    JOptionPane.showMessageDialog(vt, "Data berhasil ditambah.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vt, ae);
            }
        } else if (e.getSource() == this.vt.ubah) {
            mt.setId(Integer.parseInt(vt.id.getText()));
            mt.setObat((String) vt.obat.getSelectedItem());
            mt.setMember((String) vt.member.getSelectedItem());
            mt.setTgl(vt.tgl.getDate());
            mt.setJml(Integer.parseInt(vt.jml.getText()));
            
            try {
                if (this.mt.edit()) {
                    JOptionPane.showMessageDialog(vt, "Data berhasil diedit.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vt, ae);
            }
        } else if (e.getSource() == this.vt.delete){
            mt.setId(Integer.parseInt(vt.id.getText()));
            mt.setObat((String) vt.obat.getSelectedItem());
            mt.setMember((String) vt.member.getSelectedItem());
            mt.setTgl(vt.tgl.getDate());
            mt.setJml(Integer.parseInt(vt.jml.getText()));
            
            try {
                if (this.mt.hapus()) {
                    JOptionPane.showMessageDialog(vt, "Data berhasil dihapus.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vt, ae);
            }
        } else {
            System.out.println("Error");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me){
        if(me.getSource()==vt.table){
            vt.id.setEditable(false);
            int baris=vt.table.rowAtPoint(me.getPoint());
            String id=vt.table.getValueAt(baris,0).toString();
            vt.id.setText(id);
            String member=vt.table.getValueAt(baris,1).toString();
            vt.member.setSelectedItem(member);
            String obat=vt.table.getValueAt(baris,3).toString();
            vt.obat.setSelectedItem(obat);
            String tl=(String)vt.table.getModel().getValueAt(baris, 2);
            try{
            SimpleDateFormat tls = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggals=tls.parse(tl);
            vt.tgl.setDate(tanggals);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            String jml=vt.table.getValueAt(baris,4).toString();
            vt.jml.setText(jml);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
