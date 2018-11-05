/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
/**
 *
 * @author Duy.hv150601
 */
public class MySqlHandler {
    
    private static MySqlHandler Instance;// tranh exception thay vi dat bang public, chi su dung static o day
    private final String url= "jdbc:mysql://localhost:3306/ShopApplication";
    private final String password= "";
    private final String user= "root";
    private Connection conn= null;
    private Statement state= null;
   
    private MySqlHandler(){ }// ngan khong cho khoi tao theo cac new MySQLHandler(); 
    public static MySqlHandler getInstance(){//chi su dung static o day nua
        if(Instance== null){
            synchronized(MySqlHandler.class){
                Instance= new MySqlHandler();   
            }
        }
        return Instance;
    }
    
    public boolean connectMySQL(){
        boolean isConnected= false;
        try {
            conn= DriverManager.getConnection(url,user,password);
            isConnected= true;
        } catch (SQLException e) {
            System.out.println("Cannot connect to Database with error: "+ e);
        }
        return isConnected;
    }
    
    public void CloseConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: "+ e);
        }
    }
    
    public void ExecuteQuery(String query){
        try {
            if (connectMySQL()== true) {
                state= conn.createStatement();
                state.execute(query);
            }
        } catch (SQLException e) {
            System.out.println("Execute gets error: "+ e);
        }
    }
    
    public ResultSet ExecuteSelectQuery(String query){
        ResultSet data= null;
        try {
            if (connectMySQL()) {
                state= conn.createStatement();
                data= state.executeQuery(query);
            }
        } catch (SQLException e) {
            System.out.println("Execution gets error: "+ e);
            data= null;
        }
        return data;
    }
}
