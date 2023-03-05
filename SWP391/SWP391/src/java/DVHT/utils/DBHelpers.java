/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
    
     public static boolean deleteFile(String path) {
        boolean f = false;
        try {

            File file = new File(path);
            f = file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

    public static boolean saveFile(InputStream is, String path) {
        boolean f = false;

        try {
            byte b[] = new byte[is.available()];

            is.read(b);

            FileOutputStream fos = new FileOutputStream(path);

            fos.write(b);

            fos.flush();
            fos.close();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}
