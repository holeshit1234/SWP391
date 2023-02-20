/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class DBHelpers implements Serializable{
    public static Connection getConnection() throws 
            NamingException, SQLException{
        //1. get Server Context
        Context context = new InitialContext();
        //2. look up Datasource 
        Context tomcatcontext = (Context)context.lookup("java:comp/env");
        //3. lookup define dataource
        DataSource ds = (DataSource)tomcatcontext.lookup("MrBean");
        //4. open connection
        Connection con = ds.getConnection();
        
        return con;
    }
}
