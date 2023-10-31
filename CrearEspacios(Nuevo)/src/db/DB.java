package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    String url = "jdbc:mysql://localhost:3306/";
    String user="root";   
    String password = "";
    String driver= "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Connection Conectar(String bd) {
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+bd, user, password);
            System.out.println("Se conecto");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conecto");
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        
        }
      return cx;  
    }
    
   public void desconectar() {
        try {
            cx.close();
            System.out.println("Se desconecto");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   
   public void Select() throws SQLException   {
   }
   
   public static void main(String[] args) {
       DB conexion = new DB();
       conexion.Conectar("crear_espacios");
       try {
       PreparedStatement select = conexion.cx.prepareStatement("SELECT * FROM ?");
       select.setString(1,"pago");
       ResultSet rs = select.executeQuery();
       System.out.println("asdasdas");
           while (rs.next()) {
    	    int id = rs.getInt(1);
    	    System.out.println(id);
    	}
       } catch (SQLException e) {System.out.println(e);};
       
   }

    public PreparedStatement prepareStatement(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}