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
    private String obat, member;
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
    
    
    
    public boolean tambah() throws SQLException {
            java.sql.Connection conn=(Connection)Connector.configDB();
            String sq="alter table transaksi auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

         try{
            String sql="INSERT INTO transaksi VALUES("+getId()+","
                     + "'"+getMember()+"',"
                     + "'"+df.format(getTgl())+"',"
                    + "'"+getObat()+"',"
                    + ""+getJml()+")";
                    System.out.println(sql);


            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();

        }catch (HeadlessException | SQLException e){
               System.out.println("Error");
        }
        return true;
    }
    
    public boolean edit() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

         try{
            String sql="update transaksi set tingkat_member='"+getMember()+"', tgl_transaksi='"+df.format(getTgl())+"', nama_obat = "
                    + "'"+getObat()+"', jml_jual = "
                    + "'"+getJml()+"' where id = '"+getId()+"'";
                    System.out.println(sql);

             java.sql.Connection conn=(Connection)Connector.configDB();
             java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
             pstm.execute();

        }catch (HeadlessException | SQLException e){
               System.out.println("Error");
        }
        return true;
    }
    
    public boolean hapus() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try{
            String sql="delete from transaksi where id = '"+getId()+"'";
            
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            
            pstm.execute();
           

        }catch (HeadlessException | SQLException e){
               System.out.println("Error"+getId());
        }
          return true;
    }
}
