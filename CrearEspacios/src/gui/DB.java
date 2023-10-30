/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   
   public void insertar_clientes(int DNI, String Nom, String Dire,String Tele){
       try{
       PreparedStatement st = cx.prepareStatement("INSERT INTO clientes VALUES (?, ?, ?, ?)");
       st.setInt(1, DNI);
       st.setString(2, Nom);
       st.setString(3, Dire);
       st.setString(4, Tele);
       st.execute();
       } catch (SQLException e) {System.out.println(e);}
   }
   
   public int get_lastReserve() {
       int id = 0;
    try{
       PreparedStatement st = cx.prepareStatement("SELECT p.CodPromo FROM promos as p ORDER BY p.CodPromo DESC LIMIT 1");
       ResultSet rs = st.executeQuery();
       rs.next();
       if (rs.getInt(1) > 0) id = rs.getInt(1);
       } catch (SQLException e) {System.out.println(e);}
    
    return id;
 }
   
   public void insertar_promo(String Contenido, int precio, int stock){
       try {
       PreparedStatement st = cx.prepareStatement("INSERT INTO promos (Contenido, Precio, Stock) VALUES (?,?,?)");
       st.setString(1, Contenido);
       st.setInt(2, precio);
       st.setInt(3, stock);
       st.execute();
       } catch (SQLException e) {System.out.println(e);}
   }
   
   int len;
   
   public String [] get_promos(){
       try {
           PreparedStatement st = cx.prepareStatement("SELECT COUNT(CodPromo) FROM PROMOS");
           ResultSet rs = st.executeQuery();
           rs.next();
           len = rs.getInt(1);
       } catch (SQLException e) {System.out.println(e);}
       String promos[] = new String[len];
       int i = 0;
       while (i < len){
           promos[i] =  String.valueOf(i + 1);
           i++;
       }
       return promos;
   }
   
   String promo_det[] = new String[3];
   
   public String[] get_promoInfo(int promo_id){
       try {
           PreparedStatement st = cx.prepareStatement("SELECT p.Contenido, p.Precio, p.Stock FROM PROMOS as p WHERE p.CodPromo = ?");
           st.setInt(1, promo_id);
           ResultSet rs = st.executeQuery();
           rs.next();
           promo_det[0] = rs.getString(1);
           promo_det[1] = rs.getString(2);
           promo_det[2] = rs.getString(3);
       } catch (SQLException e) {System.out.println(e);}
       return promo_det;
   }
   
   int len_cli = 0;
   
   public String[] getClientNames(){
       try {
        PreparedStatement st = cx.prepareStatement("SELECT COUNT(DNI) FROM Clientes");
        ResultSet rs = st.executeQuery();
           rs.next();
           len_cli = rs.getInt(1);
       } catch (SQLException e) {System.out.println(e);}
       String cli_names[] = new String[len_cli];
       try {
        PreparedStatement st = cx.prepareStatement("SELECT NomApel FROM Clientes");
        ResultSet rs = st.executeQuery();
        int i = 0;
        while(rs.next()){
            cli_names[i] = rs.getString(1);
            System.out.println(i + rs.getString(1));
            i++;
        }
       } catch (SQLException e) {System.out.println(e);}
       return cli_names;
   }
   
   int cli_id;
   
   public int getCliId(String Nombre){
     try {
         PreparedStatement st = cx.prepareStatement("SELECT DNI FROM Clientes WHERE NomApel = ?");
         st.setString(1, Nombre);
         ResultSet rs = st.executeQuery();
         rs.next();
         cli_id = rs.getInt(1);
     }catch (SQLException e) {System.out.println(e);}
     return cli_id;
   }
   
   
   public void insert_reservas(String FechaEntrega, int CodCli, int CodPromo) {
       try {
           PreparedStatement insert = cx.prepareStatement("Insert into reserva (FechaEntrega, CodCli, CodPromo) values(?,?,?)");
           insert.setString(1, FechaEntrega);
           insert.setInt(2, CodCli);
           insert.setInt(3, CodPromo);
           insert.execute();
           
           PreparedStatement get_id = cx.prepareStatement("Select CodRes from reserva Order by CodRes DESC LIMIT 1");
           ResultSet rs = get_id.executeQuery();
           rs.next();
           int res_id = rs.getInt(1);
           
           PreparedStatement set_date = cx.prepareStatement("UPDATE reserva SET FechaRetiro = DATE_ADD(FechaEntrega,INTERVAL 1 DAY) WHERE CodRes = ?");
           set_date.setInt(1, res_id);
           set_date.execute();
           
           } catch (SQLException e) {System.out.println(e);}
   }
   
   int promos_len = 0;
   
   
   public String[] getPromosId(){
       try{
           PreparedStatement st = cx.prepareStatement("SELECT COUNT(CodRes) FROM reserva");
           ResultSet rs = st.executeQuery();
           rs.next();
           promos_len = rs.getInt(1);
       } catch (SQLException e) {System.out.println(e);}
       String promos_id[] = new String[promos_len];
       try{
          PreparedStatement st = cx.prepareStatement("SELECT CodRes FROM reserva");
          ResultSet rs = st.executeQuery();
          int i = 0;
          while(rs.next()){
              promos_id[i] = String.valueOf(rs.getInt(1));
              i++;
          }
       } catch (SQLException e) {System.out.println(e);}
       return promos_id;
   }
   
   int promo_price = 0;
   
   public int getReservePrice(int res_id){
       try{
           PreparedStatement st = cx.prepareStatement("SELECT p.Precio FROM promos as p INNER JOIN reserva as r on r.CodPromo = p.CodPromo WHERE r.CodRes = ?");
           st.setInt(1, res_id);
           ResultSet rs = st.executeQuery();
           rs.next();
           promo_price = rs.getInt(1);
       } catch (SQLException e) {System.out.println(e);}
      return promo_price;
   }
   
   public void insertPago(int Monto, String Metodo, int CodRes){
       try {
           PreparedStatement st = cx.prepareStatement("INSERT INTO pagos (Monto, Metodo,CodRes) VAlUES (?,?,?)");
           st.setInt(1,Monto);
           st.setString(2, Metodo);
           st.setInt(3, CodRes);
           st.execute();
       } catch (SQLException e) {System.out.println(e);}
       
   }
   
}