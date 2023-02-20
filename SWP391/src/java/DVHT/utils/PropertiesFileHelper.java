/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author User
 */
public class PropertiesFileHelper {
     public static Properties getSiteMaps(String filePath, ServletContext context)
            throws IOException {
        if (filePath == null) {
            return null;
        }
        if (filePath.trim().isEmpty()) {
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;

        try {
            is = context.getResourceAsStream(filePath);
            result.load(is);

            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
     }
}

