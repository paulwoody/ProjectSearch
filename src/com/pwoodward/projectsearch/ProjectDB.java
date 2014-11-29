/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwoodward.projectsearch;

/**
 *
 * @author Paul Woodward
 */
import java.util.ResourceBundle;

public class ProjectDB
{
    final static ResourceBundle rb = ResourceBundle.getBundle("version");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int buildNumber;
        buildNumber = Integer.parseInt(getRbTok("BUILD"));
        
        // INIT MAIN APPLICATION WINDOW
        frmMain mainApp = new frmMain(buildNumber);
        mainApp.setVisible(true);
    }

    // Incrementing a Build Number:
    // http://forums.netbeans.org/ptopic6692.html
    public static String getRbTok(String propToken)
    {
        String msg;
        msg = rb.getString(propToken);

        return msg;
    }
}
