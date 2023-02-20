/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.listener;

import DVHT.utils.PropertiesFileHelper;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author User
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying......");
        //1. take servletcontext
        ServletContext context = sce.getServletContext();
        //2. create road map
        String siteMapFile = context.getInitParameter("SITE_MAP_PATH");

        try {
            //3. get site map
            Properties siteMapProperty
                    = PropertiesFileHelper.getSiteMaps(siteMapFile, context);
            //4. set site map
            if (siteMapProperty != null) {
                context.setAttribute("SITE_MAP", siteMapProperty);
            }
        } catch (IOException ex) {
            context.log("My context Listener __IO" + ex.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("BYE");
    }
}
