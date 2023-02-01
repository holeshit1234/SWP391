/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author dunghm
 */
public class DBHelper implements Serializable {
    public static Connection createConnection() 
            throws /*ClassNotFoundException,*/ SQLException, NamingException{
        //1. get server context
        Context context = new InitialContext();
        //2.lookup datasource in container
        Context tomcatcontext = (Context)context.lookup("java:comp/env");
        //3. lookup define dataource
        DataSource ds = (DataSource)tomcatcontext.lookup("MrBean");
        //4. open connection
        Connection con = ds.getConnection();
        
        return con;
        
        
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        //2. create url connection
//       String url = "jdbc:sqlserver://localhost:1433;"
//               + "databaseName=SE1603;instanceName=MinhDung";
//       
//       //3.Open Connection
//       Connection con = DriverManager.getConnection(url,"sa","12345");
//       
//       return con;
    }
}
