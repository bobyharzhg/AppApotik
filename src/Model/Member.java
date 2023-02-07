package Model;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
public class Member {
    private int id, diskon;
    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }
    
    
    
    public boolean tambah() throws SQLException {
        Connection con = (Connection)Connector.configDB();
        String sq="alter table member auto_increment=0";
        java.sql.PreparedStatement pst=con.prepareStatement(sq);
        pst.execute();
        
        String sql = "insert into member values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.id);
        ps.setString(2, this.nama);
        ps.setInt(3, this.diskon);
        ps.execute();
        
        return true;
    }
    
    public boolean edit() throws SQLException {
        String sql = "update member set tingkat_member = ?, diskon = ? where id_member = ?";
                
        Connection con = (Connection)Connector.configDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(3, this.id);
        ps.setString(1, this.nama);
        ps.setInt(2, this.diskon);
        ps.execute();
        
        return true;
    }
    
    public boolean hapus() throws SQLException {
        String sql = "delete from member where id_member = ?";
                
        Connection con = (Connection)Connector.configDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.id);
        ps.execute();
        
        return true;
    }
}
