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
public class memController implements ActionListener, MouseListener{
    Menu vm;
    MemIn vmm;
    Member mm;
    memController cm;

    public memController(Menu vm, MemIn vmm, Member mm) {
        this.vm = vm;
        this.vmm = vmm;
        this.mm = mm;
        this.vmm.batal.addActionListener(this);
        this.vmm.ubah.addActionListener(this);
        this.vmm.kembali.addActionListener(this);
        this.vmm.simpan.addActionListener(this);
        this.vmm.delete.addActionListener(this);
        this.vmm.table.addMouseListener(this);
    }

    public void reset(){
        vmm.idmem.setEditable(true);
        vmm.idmem.setText(null);
        vmm.mem.setText(null);
    }
    
    public void tampil() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Member");
        model.addColumn("Tingkatan Member");
        model.addColumn("Diskon");
        
        try{
            String sql="Select * from member ORDER BY id_member asc";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3)});
            }
            vmm.table.setModel(model);
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vmm.kembali) {
            this.vmm.setVisible(false);
            this.vm.setVisible(true);
        } else if (e.getSource() == this.vmm.batal) {
            this.reset();
        } else if (e.getSource() == this.vmm.simpan) {
            mm.setNama(vmm.mem.getText());
            mm.setDiskon(Integer.parseInt(vmm.diskon.getText()));
            
            try {
                if (this.mm.tambah()) {
                    JOptionPane.showMessageDialog(vmm, "Data berhasil ditambah.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vmm, ae);
            }
        } else if (e.getSource() == this.vmm.ubah) {
            mm.setId(Integer.parseInt(vmm.idmem.getText()));
            mm.setNama(vmm.mem.getText());
            mm.setDiskon(Integer.parseInt(vmm.diskon.getText()));
            
            try {
                if (this.mm.edit()) {
                    JOptionPane.showMessageDialog(vmm, "Data berhasil diedit.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vmm, ae);
            }
        } else if (e.getSource() == this.vmm.delete) {
            mm.setId(Integer.parseInt(vmm.idmem.getText()));
            
            try {
                if (this.mm.hapus()) {
                    JOptionPane.showMessageDialog(vmm, "Data berhasil dihapus.");
                    this.reset();
                    this.tampil();
                } 
            } catch (SQLException ae) {
                JOptionPane.showMessageDialog(vmm, ae);
            }
        } else {
            System.out.println("Erorr");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me){
        if(me.getSource()==vmm.table){
            vmm.idmem.setEditable(false);
            int baris=vmm.table.rowAtPoint(me.getPoint());
            String id=vmm.table.getValueAt(baris,0).toString();
            vmm.idmem.setText(id);
            String nama=vmm.table.getValueAt(baris,1).toString();
            vmm.mem.setText(nama);
            String diskon=vmm.table.getValueAt(baris,2).toString();
            vmm.diskon.setText(diskon);
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