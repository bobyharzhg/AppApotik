package Controller;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class lgController implements ActionListener, MouseListener{
    private Admin ma; 
    private Login vl;

    public lgController(Admin ma, Login vl) {
        this.ma = ma;
        this.vl = vl;
        this.vl.username.addActionListener(this);
        this.vl.password.addActionListener(this);
    }

    public void reset(){
        vl.username.setText(null);
        vl.password.setText(null);
    }
    
    public void log(String uname, String pass){
        try{
                java.sql.Connection conn=(Connection)Connector.configDB();
                java.sql.Statement stm=conn.createStatement();
                String sql="SELECT username, password FROM login WHERE username='"+uname+"' "
                        + "and password='"+pass+"'";
                java.sql.ResultSet res=stm.executeQuery(sql);
                
                if (res.next()){
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    Menu vm =new Menu();
                    vm.setVisible(true);
                    vl.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                    reset();
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==vl.tamLogin){
            try{
                java.sql.Connection conn=(Connection)Connector.configDB();
                java.sql.Statement stm=conn.createStatement();
                String sql="SELECT username, password FROM login WHERE username='"
                        +vl.username.getText()+"' and password='"+vl.password.getPassword()+"'";
                java.sql.ResultSet res=stm.executeQuery(sql);
                
                if (res.next()){
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    Menu vm =new Menu();
                    vm.setVisible(true);
                    vl.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                    reset();
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            this.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
