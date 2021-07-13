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
public class obtController implements ActionListener, MouseListener{
    Menu vm;
    ObatIn vo;
    Obat mo;
    trnController ct;

    public obtController(Menu vm, ObatIn vo, Obat mo) {
        this.vm = vm;
        this.vo = vo;
        this.mo = mo;
        this.vo.batal.addActionListener(this);
        this.vo.ubah.addActionListener(this);
        this.vo.kembali.addActionListener(this);
        this.vo.simpan.addActionListener(this);
        this.vo.delete.addActionListener(this);
        this.vo.table.addMouseListener(this);
    }

    public void reset(){
        vo.oid.setEditable(true);
        vo.oid.setText(null);
        vo.obat.setText(null);
        vo.harga.setText(null);
    }
    
    public void tampil() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Obat");
        model.addColumn("Harga Obat");
        
        try{
            String sql="Select * from obat ORDER BY id_obat asc";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3)});
            }
            vo.table.setModel(model);
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vo.kembali) {
            this.vo.setVisible(false);
            this.vm.setVisible(true);
        } else if (e.getSource() == this.vo.batal) {
            this.reset();
        } else if (e.getSource() == this.vo.simpan) {
            mo.setObat(vo.obat.getText());
            mo.setHarga(Integer.parseInt(vo.harga.getText()));
            
            try {
                if (this.mo.tambah()) {
                    JOptionPane.showMessageDialog(vo, "Data berhasil ditambah.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vo, ae);
            }
        } else if (e.getSource() == this.vo.ubah) {
            mo.setOid(Integer.parseInt(vo.oid.getText()));
            mo.setObat(vo.obat.getText());
            mo.setHarga(Integer.parseInt(vo.harga.getText()));
            
            try {
                if (this.mo.edit()) {
                    JOptionPane.showMessageDialog(vo, "Data berhasil diedit.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vo, ae);
            }
        } else if (e.getSource() == this.vo.delete) {
            mo.setOid(Integer.parseInt(vo.oid.getText()));
            
            try {
                if (this.mo.hapus()) {
                    JOptionPane.showMessageDialog(vo, "Data berhasil dihapus.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vo, ae);
            }
        } else {
            System.out.println("Eroorrr");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me){
        if(me.getSource()==vo.table){
            vo.oid.setEditable(false);
            int baris=vo.table.rowAtPoint(me.getPoint());
            String oid=vo.table.getValueAt(baris,0).toString();
            vo.oid.setText(oid);
            String obat=vo.table.getValueAt(baris,1).toString();
            vo.obat.setText(obat);
            String harga=vo.table.getValueAt(baris,2).toString();
            vo.harga.setText(harga);
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
