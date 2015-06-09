/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwoodward.projectsearch;

/**
 *
 * @author Paul Woodward
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class ProjectDB
{

    final static ResourceBundle rb = ResourceBundle.getBundle("com.pwoodward.projectsearch.version");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int buildNumber = Integer.parseInt(getRbToken("BUILD"));
        String jarPath = ProjectDB.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        // OUTPUT JAR LOCATION TO CONSOLE
        System.out.println("JAR Location: " + jarPath);

        // OUTPUT USERS PROFILE LOCATION SO WE CAN STORE SO USER PREFERENCES
        System.out.println(System.getProperty("user.home"));
        
        // INIT MAIN APPLICATION WINDOW
        frmMain mainApp = new frmMain(buildNumber);
        mainApp.setVisible(true);
        
        // ATTEMPT TO CALL HOME FOR USAGE METRICS
        logUsage();
    }

    // Incrementing a Build Number:
    // http://forums.netbeans.org/ptopic6692.html
    public static String getRbToken(String propToken)
    {
        String msg;
        msg = rb.getString(propToken);

        return msg;
    }

    private static void logUsage()
    {
        String windowsUsername = System.getProperty("user.name");
        String url = "http://pwoodward.com/metrics/project-search.php?log&user=" + windowsUsername;
        try
        {
            URL myURL = new URL(url);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            String content = myURLConnection.getContent().toString();
            System.out.println("Usage Logged - " + url);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Unable to Log Usage Metrics - Malformed URL");
        }
        catch (IOException e)
        {
            System.out.println("Unable to Log Usage Metrics - Unknown Error");
        }
    }
}
