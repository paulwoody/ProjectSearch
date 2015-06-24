/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwoodward.projectsearch;

import java.awt.Desktop;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * This class extends JFrame and presents the user with a Windows Form
 * <p>
 * A dialog is presented to thew user with three Lists, the main list shows a
 * list of directories in the Base Path, to the right of this listing we show a
 * list of directories for the selected directory in the main directory list.
 * <p>
 * Below both these lists is a recently opened directory listing for quick
 * access.
 *
 * @author PaulW
 */
public class frmMain extends javax.swing.JFrame
{

    private String basePath;
    private DefaultListModel folderListing;     // The Main Directory Listing
    private DefaultListModel searchListing;     //
    private DefaultListModel recentlyOpened;    // List of Recently Opened Directories
    private DefaultListModel currentDirectory;  // A List of the Folders within the Currently Selected Directory

    /**
     * Creates new form frmMain
     */
    public frmMain(int appBuild)
    {
        initComponents();
        this.setLocationRelativeTo(null);

        // Place Build Number on Form
        appBuild = appBuild + 1000;
        this.lblAppBuildNumber.setText(Integer.toString(appBuild));

        // Retrieve Windows Login Name
        lblUsername.setText(System.getProperty("user.name"));

        // Create Default List Models
        folderListing = new DefaultListModel();
        searchListing = new DefaultListModel();
        recentlyOpened = new DefaultListModel();
        currentDirectory = new DefaultListModel();

        // Set Title
        this.setTitle("CJR Project Search");

        // LOAD DIRECTORY LISTING
        basePath = "P:\\";
        DirectoryListing(basePath, true, this.folderListing);
        this.lstFolders.setModel(this.folderListing);
        this.lstRecent.setModel(this.recentlyOpened);
        this.lstCurrentFolder.setModel(this.currentDirectory);
    }

    /**
     * This function returns a Boolean on whether a particular directory is a
     * valid Sub Directory and thus requires scanning and the contents listing.
     * <p>
     * A Directory is considered a valid Sub Directory (and hence also scanned
     * and the contents listed) if it contains a dash '-' within the directories
     * name and this dash is within the first 10 characters of the directory
     * name.
     *
     * @author Paul Woodward
     * @param folderName
     * @return
     */
    private boolean isSubFolder(String folderName)
    {
        int dashPosition = folderName.indexOf("-");

        // IF FIRST DASH IS AFTER 10TH CHARCTER IGNORE
        if (dashPosition == -1 || dashPosition > 9)
        {
            return false;
        }

        return true;
    }

    private void DirectoryListing(String path, boolean subFolderScan, DefaultListModel listModel)
    {
        // ITERATE FOLDERS
        File dir = new File(path);

        // TODO: SET BUSY CURSOR
        // TODO - ADD EXCEPTION IF PATH DOES NOT EXIST
        if (dir.exists() == false)
        {
            JOptionPane.showMessageDialog(this, "You need to Map your \"P:\\\" Drive before running this application!");
            System.exit(0);
        }

        // ITERATE FOLDERS
        for (File child : dir.listFiles())
        {
            if (child.isDirectory())
            {
                // listModel.addElement(child.getName());
                listModel.addElement(new Directory(child.getName(), path));

                // IF THIS IS A VALID SUB FOLDER THEN LIST THE SUB FOLDERS
                if (subFolderScan && isSubFolder(child.toString()) == true)
                {
                    DirectoryListing(child.toString(), false, this.folderListing);
                }
            }
        }

        // TODO: SET DEFAULT CURSOR
    }

    private void openDirectory(String folder)
    {
        // ATTEMPT TO OPEN FOLDER
        File folderPath = new File(folder);
        Desktop desktop = null;

        if (Desktop.isDesktopSupported())
        {
            desktop = Desktop.getDesktop();
        }

        if (desktop != null)
        {
            try
            {
                desktop.open(folderPath);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Unable to Open Folder " + folder, "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            }
        }

    }

    private void searchForProject(String searchText)
    {
        int elementCount = this.folderListing.size();

        // CLEAR ANY EXISTING RESULTS IN SEARCH LISTING
        this.searchListing.clear();

        // FORCE SEARCH TEXT TO LOWER CASE FOR COMPARISON
        searchText = searchText.toLowerCase();

        // ITERATE FOLDER LISTING LOOK FOR MATCHING PROJECTS
        for (int i = 0; i < elementCount; i++)
        {
            if (this.folderListing.elementAt(i).toString().toLowerCase().contains(searchText))
            {
                this.searchListing.addElement(this.folderListing.elementAt(i));
            }
        }

        // SWITCH LISTS OVER
        this.lstFolders.setModel(searchListing);
    }

    private class Directory
    {

        private String name;
        private String parent;

        public Directory(String name, String parent)
        {
            this.name = name;
            this.parent = parent;
        }

        public String fullPath()
        {
            return this.parent + "\\" + this.name;
        }

        @Override
        public String toString()
        {
            return name;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnResetSearch = new javax.swing.JButton();
        lblAppBuildCaption = new javax.swing.JLabel();
        lblAppBuildNumber = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        spMain = new javax.swing.JSplitPane();
        scRecentList = new javax.swing.JScrollPane();
        lstRecent = new javax.swing.JList();
        spFolders = new javax.swing.JSplitPane();
        scFoldersList = new javax.swing.JScrollPane();
        lstFolders = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCurrentFolder = new javax.swing.JList();
        jMenuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuFile_BasePath = new javax.swing.JMenuItem();
        mnuFile_RefreshFolders = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuFile_Exit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project Search © 2014 Paul Woodward");
        setMinimumSize(new java.awt.Dimension(400, 300));

        txtSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                keyPress(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSearchActionPerformed(evt);
            }
        });

        btnResetSearch.setText("Reset");
        btnResetSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnResetSearchActionPerformed(evt);
            }
        });

        lblAppBuildCaption.setForeground(new java.awt.Color(204, 204, 204));
        lblAppBuildCaption.setText("Application Build: ");

        lblAppBuildNumber.setForeground(new java.awt.Color(204, 204, 204));
        lblAppBuildNumber.setText("0000");

        lblUsername.setForeground(new java.awt.Color(204, 204, 204));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("<Username>");

        spMain.setDividerLocation(250);
        spMain.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        spMain.setResizeWeight(1.0);
        spMain.setToolTipText("");

        lstRecent.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Recently Open Folders" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstRecent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstRecent.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lstRecentMouseClicked(evt);
            }
        });
        scRecentList.setViewportView(lstRecent);

        spMain.setRightComponent(scRecentList);

        spFolders.setDividerLocation(350);
        spFolders.setResizeWeight(0.5);
        spFolders.setLastDividerLocation(0);

        lstFolders.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Loading Directories..." };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstFolders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstFolders.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                folderClick(evt);
            }
        });
        lstFolders.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                lstFoldersValueChanged(evt);
            }
        });
        scFoldersList.setViewportView(lstFolders);

        spFolders.setLeftComponent(scFoldersList);

        lstCurrentFolder.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Sub-Directories" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstCurrentFolder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lstCurrentFolderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstCurrentFolder);

        spFolders.setRightComponent(jScrollPane1);

        spMain.setLeftComponent(spFolders);

        mnuFile.setText("File");

        mnuFile_BasePath.setText("Open Path");
        mnuFile_BasePath.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mnuFile_BasePathActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFile_BasePath);

        mnuFile_RefreshFolders.setText("Refresh Folders");
        mnuFile_RefreshFolders.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mnuFile_RefreshFoldersActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFile_RefreshFolders);
        mnuFile.add(jSeparator1);

        mnuFile_Exit.setText("Exit");
        mnuFile_Exit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mnuFile_ExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFile_Exit);

        jMenuBar.add(mnuFile);

        jMenu1.setText("Help");

        jMenuItem1.setText("paulwoody@yahoo.com");
        jMenuItem1.setActionCommand("");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("07843 231916");
        jMenu1.add(jMenuItem2);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spMain)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAppBuildCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAppBuildNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnResetSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spMain, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAppBuildNumber)
                        .addComponent(lblUsername))
                    .addComponent(lblAppBuildCaption))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSearchActionPerformed
    {//GEN-HEADEREND:event_btnSearchActionPerformed
        this.currentDirectory.clear();
        this.searchForProject(this.txtSearch.getText());
        this.txtSearch.requestFocusInWindow();
        this.txtSearch.selectAll();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtSearchActionPerformed
    {//GEN-HEADEREND:event_txtSearchActionPerformed
        this.currentDirectory.clear();
        this.searchForProject(this.txtSearch.getText());
        this.txtSearch.requestFocusInWindow();
        this.txtSearch.selectAll();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnResetSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnResetSearchActionPerformed
    {//GEN-HEADEREND:event_btnResetSearchActionPerformed
        this.currentDirectory.clear();
        this.txtSearch.setText("");
        this.lstFolders.setModel(folderListing);
        this.txtSearch.requestFocusInWindow();
    }//GEN-LAST:event_btnResetSearchActionPerformed

    private void keyPress(java.awt.event.KeyEvent evt)//GEN-FIRST:event_keyPress
    {//GEN-HEADEREND:event_keyPress
        if (evt.getKeyCode() == 27)
        {
            // IF SEARCH TERM EXISTS RESET HE SEARCH
            if (this.txtSearch.getText().length() > 0)
            {
                this.txtSearch.setText("");
                this.lstFolders.setModel(folderListing);
            } else
            {
                this.setExtendedState(ICONIFIED);
            }
        }
    }//GEN-LAST:event_keyPress

    private void folderClick(java.awt.event.MouseEvent evt)//GEN-FIRST:event_folderClick
    {//GEN-HEADEREND:event_folderClick
        Directory selectedDir = (Directory) this.lstFolders.getSelectedValue();

        // SINGLE CLICK SHOWS UPDATES SUB FOLDERS
        if (evt.getClickCount() == 1)
        {
            // LIST CURRENTLY SELECTED DIRECTORY
            this.currentDirectory.clear();
            DirectoryListing(selectedDir.fullPath(), false, this.currentDirectory);
            return;
        }

        // ATTEMPT TO OPEN DIRECTORY
        openDirectory(selectedDir.fullPath());

        // STORE IN THE RECENTLY OPENED LIST
        addToRecentFolderList(selectedDir);

    }//GEN-LAST:event_folderClick

    private void addToRecentFolderList(Directory folder)
    {
        // CHECK IF ALREADY EXISTS
        if (this.recentlyOpened.contains(folder) == true)
        {
            this.recentlyOpened.removeElement(folder);
        }

        this.recentlyOpened.add(0, folder);

    }

    private void mnuFile_ExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuFile_ExitActionPerformed
    {//GEN-HEADEREND:event_mnuFile_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuFile_ExitActionPerformed

    private void mnuFile_RefreshFoldersActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuFile_RefreshFoldersActionPerformed
    {//GEN-HEADEREND:event_mnuFile_RefreshFoldersActionPerformed
        this.folderListing.clear();
        this.DirectoryListing("P:\\", true, this.folderListing);
        this.lstFolders.setModel(this.folderListing);
    }//GEN-LAST:event_mnuFile_RefreshFoldersActionPerformed

    private void lstRecentMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lstRecentMouseClicked
    {//GEN-HEADEREND:event_lstRecentMouseClicked

        // DIREGARD SINGLE CLICKS
        if (evt.getClickCount() == 2)
        {
            // GET TYPE OF SELECTED ITEM FROM LISTVIEW
            System.out.println("Test");
            Directory selectedDir = (Directory) this.lstRecent.getSelectedValue();

            // ATTEMPT TO OPEN DIRECTORY
            openDirectory(selectedDir.fullPath());

            // REMOVE FOLDER FROM LIST
            this.recentlyOpened.remove(this.recentlyOpened.indexOf(selectedDir));

            // ADD SELECTED FOLDER TO TOP OF LIST
            this.recentlyOpened.add(0, selectedDir);
        }
    }//GEN-LAST:event_lstRecentMouseClicked

    private void lstCurrentFolderMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lstCurrentFolderMouseClicked
    {//GEN-HEADEREND:event_lstCurrentFolderMouseClicked
        if (evt.getClickCount() == 1)
        {
            Directory selectedDir = (Directory) this.lstCurrentFolder.getSelectedValue();
            Directory parentDir = (Directory) this.lstFolders.getSelectedValue();
            openDirectory(selectedDir.fullPath());

            // ADD TO RECENT FOLDER LISTING
            //String folder = this.lstFolders.getSelectedValue().toString();
            //addToRecentFolderList(folder);
            addToRecentFolderList(parentDir);
        }
    }//GEN-LAST:event_lstCurrentFolderMouseClicked

    private void mnuFile_BasePathActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuFile_BasePathActionPerformed
    {//GEN-HEADEREND:event_mnuFile_BasePathActionPerformed
        String newBasePath = JOptionPane.showInputDialog(rootPane, "Enter Path", basePath);

        // ENSURE PATH ENDS WITH A BACKSLASH
        if (newBasePath.endsWith("\\") == false)
        {
            newBasePath = newBasePath + "\\";
        }

        // CHECK PATH IS VALID
        File dir = new File(newBasePath);
        if (dir.exists() && dir.isDirectory())
        {
            // TODO COUNT DIRECTORIES FOR A PROGRESS BAR

            // UPDATE BASE PATH
            this.basePath = newBasePath;

            // CLEAR EXISTING FOLDER LIST
            this.folderListing.clear();

            // POPULATE FOLDER LIST
            this.DirectoryListing(basePath, true, this.folderListing);
        } else
        {
            JOptionPane.showMessageDialog(rootPane, "'" + newBasePath + "' appears to be an invalid file path!");
        }


    }//GEN-LAST:event_mnuFile_BasePathActionPerformed

    private void lstFoldersValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_lstFoldersValueChanged
    {//GEN-HEADEREND:event_lstFoldersValueChanged
        if (this.lstFolders.getSelectedIndex() != -1)
        {
            Directory selectedDir = (Directory) this.lstFolders.getSelectedValue();
            this.currentDirectory.clear();

            // LIST CURRENTLY SELECTED DIRECTORY
            DirectoryListing(selectedDir.fullPath(), false, this.currentDirectory);
        }

    }//GEN-LAST:event_lstFoldersValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetSearch;
    private javax.swing.JButton btnSearch;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblAppBuildCaption;
    private javax.swing.JLabel lblAppBuildNumber;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList lstCurrentFolder;
    private javax.swing.JList lstFolders;
    private javax.swing.JList lstRecent;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFile_BasePath;
    private javax.swing.JMenuItem mnuFile_Exit;
    private javax.swing.JMenuItem mnuFile_RefreshFolders;
    private javax.swing.JScrollPane scFoldersList;
    private javax.swing.JScrollPane scRecentList;
    private javax.swing.JSplitPane spFolders;
    private javax.swing.JSplitPane spMain;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
