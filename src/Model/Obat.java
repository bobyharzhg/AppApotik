package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Obat {
    private int oid, harga;
    private String obat;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public boolean tambah() throws SQLException {
        Connection con = (Connection)Connector.configDB();
        String sq="alter table obat auto_increment=0";
        java.sql.PreparedStatement pst=con.prepareStatement(sq);
        pst.execute();
        
        String sql = "insert into obat values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.oid);
        ps.setString(2, this.obat);
        ps.setInt(3, this.harga);
        ps.execute();
        
        return true;
    }
    
    public boolean edit() throws SQLException {
        String sql = "update obat set nama_obat = ?, harga = ? where id_obat = ?";
                
        Connection con = (Connection)Connector.configDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(3, this.oid);
        ps.setString(1, this.obat);
        ps.setInt(2, this.harga);
        ps.execute();
        
        return true;
    }
    
    public boolean hapus() throws SQLException {
        String sql = "delete from obat where id_obat = ?";
                
        Connection con = (Connection)Connector.configDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.oid);
        ps.execute();
        
        return true;
    }
    
//    public boolean tambahObat() throws SQLException {
//        String sql = "update obat set harga = ? where nama_obat = ?";
//        String sql2 = "select harga from obat where nama_obat = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement pst = con.prepareStatement(sql2);
//        pst.setString(1, this.obat);
//        ResultSet res = pst.executeQuery();
//        res.absolute(1);
//        
//        int obt = this.harga + res.getInt(1);
//        
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, obt);
//        ps.setString(2, this.obat);
//        ps.execute();
//        
//        return true;
//    }
//    
//    public boolean kurangObat() throws SQLException {
//        String sql = "update obat set harga = ? where nama_obat = ?";
//        String sql2 = "select harga from obat where nama_obat = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement pst = con.prepareStatement(sql2);
//        pst.setString(1, this.obat);
//        ResultSet res = pst.executeQuery();
//        res.absolute(1);
//        
//        int obt = res.getInt(1) - this.harga;
//        
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, obt);
//        ps.setString(2, this.obat);
//        ps.execute();
//        
//        return true;
//    }
}

