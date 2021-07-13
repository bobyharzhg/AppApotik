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
public class Transaksi {
    private int id, jml;
    private String obat;
    private Date tgl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }
    
    public boolean tambah() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

         try{
            String sql="INSERT INTO transaksi VALUES('"+getId()+"',"
                     + "'"+df.format(getTgl())+"',"
                    + "'"+getObat()+"',"
                    + "'"+getJml()+"')";
                    System.out.println(sql);

             java.sql.Connection conn=(Connection)Connector.configDB();
             java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
             pstm.execute();

        }catch (HeadlessException | SQLException e){
               System.out.println("Error");
        }
//        String sql = "insert into transaksi values (?,?,?,?)";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, this.id);
//        ps.setDate(2, (java.sql.Date) this.tgl);
//        ps.setString(3, this.obat);
//        ps.setInt(4, this.jml);
//        ps.execute();
//        
        return true;
    }
    
    public boolean edit() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

         try{
            String sql="update transaksi set tgl_transaksi='"+df.format(getTgl())+"', nama_obat = "
                    + "'"+getObat()+"', jml_jual = "
                    + "'"+getJml()+"' where id = '"+getId()+"'";
                    System.out.println(sql);

             java.sql.Connection conn=(Connection)Connector.configDB();
             java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
             pstm.execute();

        }catch (HeadlessException | SQLException e){
               System.out.println("Error");
        }
//        String sql = "update transaksi set tgl_transaksi = ?, nama_obat = ?, jml_jual = ? where id = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(4, this.id);
//        ps.setDate(1, (java.sql.Date) this.tgl);
//        ps.setString(2, this.obat);
//        ps.setInt(3, this.jml);
//        ps.execute();
//        
        return true;
    }
    
    public boolean hapus() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try{
            String sql="delete from transaksi where id = '"+getId()+"'";
            System.out.println(sql);

            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();

        }catch (HeadlessException | SQLException e){
               System.out.println("Error"+getId());
        }
//        String sql = "delete from obat where id_obat = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, getId());
//        ps.execute();
          return true;
//        String sql = "delete from obat where id_obat = ?";
//        boolean del = false;        
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, getId());
//        del = ps.executeUpdate() > 0;
//        return true;
    }
        
//    public boolean tambahObat() throws SQLException {
//        String sql = "update transaksi set jml_jual = ? where nama_obat = ?";
//        String sql2 = "select jml_jual from transaksi where nama_obat = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement pst = con.prepareStatement(sql2);
//        pst.setString(1, this.obat);
//        ResultSet res = pst.executeQuery();
//        res.absolute(1);
//        
//        int obt = this.jml + res.getInt(1);
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
//        String sql = "update transaksi set jml_jual = ? where nama_obat = ?";
//        String sql2 = "select jml_jual from transaksi where nama_obat = ?";
//                
//        Connection con = (Connection)Connector.configDB();
//        PreparedStatement pst = con.prepareStatement(sql2);
//        pst.setString(1, this.obat);
//        ResultSet res = pst.executeQuery();
//        res.absolute(1);
//        
//        int obt = res.getInt(1) - this.jml;
//        
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, obt);
//        ps.setString(2, this.obat);
//        ps.execute();
//        
//        return true;
//    }
}
