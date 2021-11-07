/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbanking_2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alokd
 */
public class dashboard_admin extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    
    
    //default border for menu items
    Border default_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255,255,255));
        
    //yellow border for menu items
    Border yellow_border = BorderFactory.createMatteBorder(1, 1, 1, 1,  Color.YELLOW);
    
    // create an array of jlabels
    JLabel[] menuLabels = new JLabel[3];
    
    // create an array of jforms
    JPanel[] panels = new JPanel[3];
    
    public dashboard_admin() {
        initComponents();
        fetch();
        fetch2();
        
        fetch3();
        fetch_log_account();
        fetch_log_employee();
        
        clear();
        clear2();
        
        jPanel_account2.setVisible(true);
        jPanel_employee.setVisible(false);
        jPanel_log3.setVisible(false);
        //
        jComboBox_user.setVisible(false);
        
        
        /*///////////
        jTable_account2.getTableHeader().setFont(new Font("Segeo UI", Font.BOLD, 12));
        jTable_account2.getTableHeader().setOpaque(true);
        jTable_account2.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_account2.getTableHeader().setForeground(new Color(51, 51, 54));
        jTable_account2.setRowHeight(25);
        
        jTable_employee.getTableHeader().setFont(new Font("Segeo UI", Font.BOLD, 12));
        jTable_employee.getTableHeader().setOpaque(true);
        jTable_employee.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_employee.getTableHeader().setForeground(new Color(51, 51, 54));
        jTable_employee.setRowHeight(25);
        
        jTable_log3.getTableHeader().setFont(new Font("Segeo UI", Font.BOLD, 12));
        jTable_log3.getTableHeader().setOpaque(true);
        jTable_log3.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_log3.getTableHeader().setForeground(new Color(51, 51, 54));
        jTable_log3.setRowHeight(25);*/
        
        
        // populate the menulabels array
        // you can use a for loop to do that
        menuLabels [0] = jLabel_menuitem1;
        menuLabels [1] = jLabel_menuitem3;
        menuLabels [2] = jLabel_menuitem4;
        
        
        // populate the panels array
        panels[0] = jPanel_account2;
        panels[1] = jPanel_employee;
        panels[2] = jPanel_log3;
        
        addActionToMenuLabels();
        
    }
    
    private void clear()
    {
        jTextField_acc_no_account2.setText("");
        jTextField_name_account2.setText("");
        jTextField_balance_account2.setText("");
    }
    
        //Employee jform
    
    private void clear2()
    {        
        jTextField_name_employee.setText("");
        jTextField_position_employee.setText("");
        jTextField_branch_employee.setText("");
    }
    
    public void fetch()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_customer_details";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_account2.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("account_no"));
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("balance"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
        //For Employee tab
    
    public void fetch2()
    {    
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_reg";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_employee.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("position"));
                    v2.add(rs.getString("branch"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }    
    }
    
    public void fetch3()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_log";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_log3.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("log"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void fetch_log_account()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_log_account";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_account_log.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("log"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void fetch_log_account__admin()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_log_account where user='admin'";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_account_log.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("log"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void fetch_log_account__employee()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_log_account where user='employee'";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_account_log.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("log"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void fetch_log_employee()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql="select * from tbl_log_employee";
            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dl=(DefaultTableModel)jTable_employee_log.getModel();
            dl.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2=new Vector();
                    
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("log"));
                    
                    dl.addRow(v2);
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    // create a function to set the label background color
    public void setLabelBackground(JLabel label)
    {
        //reset labels to their default design
        for (JLabel menuItem : menuLabels) {
            // change the jlabel background color to a color
            menuItem.setBackground(new Color(255,255,255));
            // change the jlabel Foreground color to a color
            menuItem.setForeground(new Color(0, 0, 0));
        }
        
        
        // change the jlabel background color to a color
        label.setBackground(new Color(102, 153, 255));
        // change the jlabel Foreground color to a color
        label.setForeground(new Color(255,255,255));
    }
    
    
    
    // create a function to show the selected panel
    public void showPanel(JPanel panel)
    {
        // hide panels
        for (JPanel pnl : panels)
        {
            pnl.setVisible(false);
        }
        
        // and show only this panel
        panel.setVisible(true);
    }
    

    public void addActionToMenuLabels()
    {
        //get labels in the jpanel menu
        Component[] components = jPanel_menu.getComponents();
        
        for (Object component : components) {
            if(component instanceof JLabel)
            {
                JLabel label = (JLabel) component;
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        // change the jlabel background and forground
                        setLabelBackground(label);
                        
                        //display the selected panel
                        switch (label.getText().trim()){
                            case "ACCOUNT DETAILS":
                                showPanel(jPanel_account2);
                                break;
                                    
                            case "EMPLOYEE DETAILS":
                                showPanel(jPanel_employee);
                                break;
                                
                            case "LOG HISTORY":
                                showPanel(jPanel_log3);
                                break;
                                            
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                        //set the border to yellow
                        label.setBorder(yellow_border);
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                        //set to the default border
                        label.setBorder(default_border);
                    
                    }
                });
            }
    }}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel_menu = new javax.swing.JPanel();
        jLabel_menuitem3 = new javax.swing.JLabel();
        jLabel_menuitem1 = new javax.swing.JLabel();
        jLabel_menuitem4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_logout = new javax.swing.JLabel();
        jPanel_pink = new javax.swing.JPanel();
        jPanel_account2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_account2 = new javax.swing.JTable();
        jTextField_acc_no_account2 = new javax.swing.JTextField();
        jTextField_name_account2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_balance_account2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton_delete_account2 = new javax.swing.JButton();
        jButton_update_account2 = new javax.swing.JButton();
        jButton_insert_account2 = new javax.swing.JButton();
        jButton_refresh_account2 = new javax.swing.JButton();
        jPanel_employee = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_employee = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField_name_employee = new javax.swing.JTextField();
        jTextField_position_employee = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_branch_employee = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton_update_employee = new javax.swing.JButton();
        jButton_insert_employee = new javax.swing.JButton();
        jButton_delete_employee = new javax.swing.JButton();
        jButton_refresh_account3 = new javax.swing.JButton();
        jPanel_log3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_log3 = new javax.swing.JTable();
        jButton_refresh_log_transaction = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable_employee_log = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_account_log = new javax.swing.JTable();
        jComboBox_user = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel_menu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_menuitem3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_menuitem3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel_menuitem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuitem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/administrator-2-24.png"))); // NOI18N
        jLabel_menuitem3.setText("EMPLOYEE DETAILS");
        jLabel_menuitem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuitem3.setOpaque(true);

        jLabel_menuitem1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel_menuitem1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel_menuitem1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuitem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuitem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user-4-24.png"))); // NOI18N
        jLabel_menuitem1.setText("ACCOUNT DETAILS");
        jLabel_menuitem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuitem1.setOpaque(true);

        jLabel_menuitem4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_menuitem4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel_menuitem4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuitem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/text-center-24.png"))); // NOI18N
        jLabel_menuitem4.setText("LOG HISTORY");
        jLabel_menuitem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuitem4.setOpaque(true);
        jLabel_menuitem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_menuitem4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_menuitem1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menuLayout.createSequentialGroup()
                        .addComponent(jLabel_menuitem4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel_menuitem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_menuitem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_menuitem3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_menuitem4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("iBanking");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bank-3-32.png"))); // NOI18N

        jLabel_logout.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel_logout.setForeground(new java.awt.Color(240, 0, 0));
        jLabel_logout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout-16.png"))); // NOI18N
        jLabel_logout.setText("Logout");
        jLabel_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_logoutMouseClicked(evt);
            }
        });

        jPanel_pink.setBackground(new java.awt.Color(255, 0, 255));

        jPanel_account2.setBackground(new java.awt.Color(240, 246, 246));
        jPanel_account2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable_account2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable_account2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Account number", "Name", "Balance"
            }
        ));
        jTable_account2.setFocusable(false);
        jTable_account2.setGridColor(new java.awt.Color(102, 102, 102));
        jTable_account2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_account2.setRowHeight(25);
        jTable_account2.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_account2.setShowVerticalLines(false);
        jTable_account2.getTableHeader().setReorderingAllowed(false);
        jTable_account2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_accountMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_account2);
        if (jTable_account2.getColumnModel().getColumnCount() > 0) {
            jTable_account2.getColumnModel().getColumn(0).setPreferredWidth(11);
        }

        jTextField_acc_no_account2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_acc_no_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_acc_no_accountActionPerformed(evt);
            }
        });

        jTextField_name_account2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_name_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_name_accountActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel11.setText("Account Number");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel12.setText("Name");

        jTextField_balance_account2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel13.setText("Balance");

        jButton_delete_account2.setBackground(new java.awt.Color(32, 136, 203));
        jButton_delete_account2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_delete_account2.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete_account2.setText("Delete");
        jButton_delete_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_accountActionPerformed(evt);
            }
        });

        jButton_update_account2.setBackground(new java.awt.Color(32, 136, 203));
        jButton_update_account2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_update_account2.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update_account2.setText("Update");
        jButton_update_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_update_accountActionPerformed(evt);
            }
        });

        jButton_insert_account2.setBackground(new java.awt.Color(32, 136, 203));
        jButton_insert_account2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_insert_account2.setForeground(new java.awt.Color(255, 255, 255));
        jButton_insert_account2.setText("Insert");
        jButton_insert_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_insert_accountActionPerformed(evt);
            }
        });

        jButton_refresh_account2.setBackground(new java.awt.Color(0, 204, 204));
        jButton_refresh_account2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_refresh_account2.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh_account2.setText("Refresh");
        jButton_refresh_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refresh_account2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_account2Layout = new javax.swing.GroupLayout(jPanel_account2);
        jPanel_account2.setLayout(jPanel_account2Layout);
        jPanel_account2Layout.setHorizontalGroup(
            jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_account2Layout.createSequentialGroup()
                .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_account2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_update_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_insert_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_delete_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_account2Layout.createSequentialGroup()
                        .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton_refresh_account2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_acc_no_account2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_name_account2)
                    .addComponent(jTextField_balance_account2))
                .addContainerGap())
        );
        jPanel_account2Layout.setVerticalGroup(
            jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_account2Layout.createSequentialGroup()
                .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_account2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_acc_no_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_name_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_balance_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE))
                    .addGroup(jPanel_account2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_insert_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_refresh_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_employee.setBackground(new java.awt.Color(240, 246, 246));
        jPanel_employee.setPreferredSize(new java.awt.Dimension(721, 489));

        jTable_employee.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable_employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Position", "Branch"
            }
        ));
        jTable_employee.setFocusable(false);
        jTable_employee.setGridColor(new java.awt.Color(102, 102, 102));
        jTable_employee.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_employee.setRowHeight(25);
        jTable_employee.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_employee.setShowVerticalLines(false);
        jTable_employee.getTableHeader().setReorderingAllowed(false);
        jTable_employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_employeeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_employee);
        if (jTable_employee.getColumnModel().getColumnCount() > 0) {
            jTable_employee.getColumnModel().getColumn(0).setPreferredWidth(11);
        }

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel14.setText("Name");

        jTextField_name_employee.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_name_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_name_employeeActionPerformed(evt);
            }
        });

        jTextField_position_employee.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_position_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_position_employeeActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel15.setText("Position");

        jTextField_branch_employee.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel16.setText("Branch");

        jButton_update_employee.setBackground(new java.awt.Color(32, 136, 203));
        jButton_update_employee.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_update_employee.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update_employee.setText("Update");
        jButton_update_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_update_employeeActionPerformed(evt);
            }
        });

        jButton_insert_employee.setBackground(new java.awt.Color(32, 136, 203));
        jButton_insert_employee.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_insert_employee.setForeground(new java.awt.Color(255, 255, 255));
        jButton_insert_employee.setText("Insert");
        jButton_insert_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_insert_employeeActionPerformed(evt);
            }
        });

        jButton_delete_employee.setBackground(new java.awt.Color(32, 136, 203));
        jButton_delete_employee.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_delete_employee.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete_employee.setText("Delete");
        jButton_delete_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_employeeActionPerformed(evt);
            }
        });

        jButton_refresh_account3.setBackground(new java.awt.Color(0, 204, 204));
        jButton_refresh_account3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_refresh_account3.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh_account3.setText("Refresh");
        jButton_refresh_account3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refresh_account3jButton_delete_accountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_employeeLayout = new javax.swing.GroupLayout(jPanel_employee);
        jPanel_employee.setLayout(jPanel_employeeLayout);
        jPanel_employeeLayout.setHorizontalGroup(
            jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_employeeLayout.createSequentialGroup()
                .addGroup(jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_employeeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_update_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_insert_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_name_employee)
                    .addComponent(jTextField_position_employee)
                    .addComponent(jTextField_branch_employee)
                    .addGroup(jPanel_employeeLayout.createSequentialGroup()
                        .addGroup(jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton_refresh_account3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_employeeLayout.setVerticalGroup(
            jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_employeeLayout.createSequentialGroup()
                .addGroup(jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_employeeLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_name_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_position_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_branch_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE))
                    .addGroup(jPanel_employeeLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_insert_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_refresh_account3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_log3.setBackground(new java.awt.Color(240, 246, 246));

        jTable_log3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable_log3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Log"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_log3.setFocusable(false);
        jTable_log3.setGridColor(new java.awt.Color(102, 102, 102));
        jTable_log3.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_log3.setRowHeight(25);
        jTable_log3.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane6.setViewportView(jTable_log3);
        if (jTable_log3.getColumnModel().getColumnCount() > 0) {
            jTable_log3.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        jButton_refresh_log_transaction.setBackground(new java.awt.Color(0, 204, 204));
        jButton_refresh_log_transaction.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_refresh_log_transaction.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh_log_transaction.setText("Refresh");
        jButton_refresh_log_transaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refresh_log_transactionActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transaction log", "Account log", "Employee log" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable_employee_log.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable_employee_log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Log"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_employee_log.setFocusable(false);
        jTable_employee_log.setGridColor(new java.awt.Color(102, 102, 102));
        jTable_employee_log.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_employee_log.setRowHeight(25);
        jTable_employee_log.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane9.setViewportView(jTable_employee_log);
        if (jTable_employee_log.getColumnModel().getColumnCount() > 0) {
            jTable_employee_log.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        jTable_account_log.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable_account_log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Log"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_account_log.setFocusable(false);
        jTable_account_log.setGridColor(new java.awt.Color(102, 102, 102));
        jTable_account_log.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_account_log.setRowHeight(25);
        jTable_account_log.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_account_log.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTable_account_log);
        if (jTable_account_log.getColumnModel().getColumnCount() > 0) {
            jTable_account_log.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        jComboBox_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Employee", "Admin" }));

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_log3Layout = new javax.swing.GroupLayout(jPanel_log3);
        jPanel_log3.setLayout(jPanel_log3Layout);
        jPanel_log3Layout.setHorizontalGroup(
            jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
            .addGroup(jPanel_log3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jComboBox_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_log3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_refresh_log_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(293, 293, 293))
            .addGroup(jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE))
            .addGroup(jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE))
        );
        jPanel_log3Layout.setVerticalGroup(
            jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_log3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jComboBox_user)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_refresh_log_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
            .addGroup(jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_log3Layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(57, Short.MAX_VALUE)))
            .addGroup(jPanel_log3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_log3Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel_pinkLayout = new javax.swing.GroupLayout(jPanel_pink);
        jPanel_pink.setLayout(jPanel_pinkLayout);
        jPanel_pinkLayout.setHorizontalGroup(
            jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_account2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_employee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_log3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_pinkLayout.setVerticalGroup(
            jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_account2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_employee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_log3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ADMIN");
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addGap(74, 74, 74)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_pink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_pink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_logoutMouseClicked
        login l=new login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel_logoutMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton_insert_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insert_accountActionPerformed
        String ac_no=jTextField_acc_no_account2.getText();
        System.out.println(ac_no);
        String name=jTextField_name_account2.getText();
        System.out.println(name);
        String bal=jTextField_balance_account2.getText();
        System.out.println(bal);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "insert into tbl_customer_details(account_no,name,balance)values('"+ac_no+"','"+name+"','"+bal+"')";
            System.out.println(sql);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);
            System.out.println(rs);
            if(rs==1)
            {
                JOptionPane.showMessageDialog(this, "Added Successfully");
                
                //log
                String log_statement = "(Admin) A/c added where A/c number = " + ac_no + " , name = " + name + " and balance = " + bal;
                String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','admin')";
                System.out.println(sql_log);
                Statement stmt_log=con.createStatement();
                int rs_log=stmt_log.executeUpdate(sql_log);
                System.out.println(rs_log);
                //log end
                
                fetch();
                clear();
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_insert_accountActionPerformed

    private void jButton_update_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_update_accountActionPerformed
        DefaultTableModel dl=(DefaultTableModel)jTable_account2.getModel();
        int index=jTable_account2.getSelectedRow();
        int id=Integer.parseInt(dl.getValueAt(index,0).toString());

        String ac_no=jTextField_acc_no_account2.getText();
        System.out.println(ac_no);
        String name=jTextField_name_account2.getText();
        System.out.println(name);
        String bal=jTextField_balance_account2.getText();
        System.out.println(bal);

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            
            //log
            String updatelog = "select * from tbl_customer_details where id='"+id+"'";
            
            System.out.println(updatelog);
            PreparedStatement pst2 = con.prepareStatement(updatelog);
            ResultSet logg;
            logg = pst2.executeQuery();
            System.out.println(logg);
            if(logg.next())
            {
                String account = logg.getString("account_no");
                String customername = logg.getString("name");
                String rupee = logg.getString("balance");
                System.out.println(account);
                System.out.println(customername);
                System.out.println(rupee);
                
                System.out.println(!ac_no.equals(account));
                System.out.println(!name.equals(customername));
                System.out.println(!bal.equals(rupee));
                if(!ac_no.equals(account))
                {
                    String log_statement = "(Admin) A/c number changed from " + account + " to " + ac_no + " where id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
                if(!name.equals(customername))
                {
                    String log_statement = "(Admin) Name changed from " + customername + " to " + name + " where A/c number = " +ac_no+ " and id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
                if(!bal.equals(rupee))
                {
                    String log_statement = "(Admin) Balance changed from " + rupee + " to " + bal + " where A/c number = " +ac_no+ " and id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
            }
            //log end
            
            String sql = "update tbl_customer_details set account_no='"+ac_no+"',name='"+name+"',balance='"+bal+"' where id='"+id+"'";
            System.out.println(sql);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);
            System.out.println(rs);
            if(rs==1)
            {
                JOptionPane.showMessageDialog(this, "Updated Successfully");
                fetch();
                clear();
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_update_accountActionPerformed

    private void jButton_delete_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete_accountActionPerformed
        DefaultTableModel dl=(DefaultTableModel)jTable_account2.getModel();
        int index=jTable_account2.getSelectedRow();
        int id=Integer.parseInt(dl.getValueAt(index,0).toString());
        
        //for log
        String ac_no=jTextField_acc_no_account2.getText();
        System.out.println(ac_no);
        String name=jTextField_name_account2.getText();
        System.out.println(name);
        //

        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to delete","warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult==JOptionPane.YES_OPTION)
        {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
                String sql="delete from tbl_customer_details where id = '"+id+"'";
                System.out.println(sql);
                Statement stmt=con.createStatement();
                int rs=stmt.executeUpdate(sql);
                System.out.println(rs);
                if(rs==1){
                    JOptionPane.showMessageDialog(this, "Deleted Successfully");
                    
                    //log
                    String log_statement = "(Admin) A/c deleted where A/c number = " + ac_no + " and id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                    //log end
                    
                    fetch();
                    clear();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton_delete_accountActionPerformed

    private void jTextField_name_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_name_accountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_name_accountActionPerformed

    private void jTextField_acc_no_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_acc_no_accountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_acc_no_accountActionPerformed

    private void jTable_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_accountMouseClicked
        DefaultTableModel d1=(DefaultTableModel)jTable_account2.getModel();
        int selectIndex=jTable_account2.getSelectedRow();
        jTextField_acc_no_account2.setText(d1.getValueAt(selectIndex, 1).toString());
        jTextField_name_account2.setText(d1.getValueAt(selectIndex, 2).toString());
        jTextField_balance_account2.setText(d1.getValueAt(selectIndex, 3).toString());
    }//GEN-LAST:event_jTable_accountMouseClicked

    private void jTable_employeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_employeeMouseClicked
        DefaultTableModel d1=(DefaultTableModel)jTable_employee.getModel();
        int selectIndex=jTable_employee.getSelectedRow();
        jTextField_name_employee.setText(d1.getValueAt(selectIndex, 1).toString());
        jTextField_position_employee.setText(d1.getValueAt(selectIndex, 2).toString());
        jTextField_branch_employee.setText(d1.getValueAt(selectIndex, 3).toString());
    }//GEN-LAST:event_jTable_employeeMouseClicked

    private void jTextField_name_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_name_employeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_name_employeeActionPerformed

    private void jTextField_position_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_position_employeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_position_employeeActionPerformed

    private void jButton_update_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_update_employeeActionPerformed
        DefaultTableModel dl=(DefaultTableModel)jTable_employee.getModel();
        int index=jTable_employee.getSelectedRow();
        int id=Integer.parseInt(dl.getValueAt(index,0).toString());

        String name=jTextField_name_employee.getText();
        System.out.println(name);
        String position=jTextField_position_employee.getText();
        System.out.println(position);
        String branch=jTextField_branch_employee.getText();
        System.out.println(branch);

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            
            //log
            String updatelog = "select * from tbl_reg where id='"+id+"'";
            
            System.out.println(updatelog);
            PreparedStatement pst2 = con.prepareStatement(updatelog);
            ResultSet logg;
            logg = pst2.executeQuery();
            System.out.println(logg);
            if(logg.next())
            {
                String em_name = logg.getString("name");
                String pos = logg.getString("position");
                String br = logg.getString("branch");
                System.out.println(em_name);
                System.out.println(pos);
                System.out.println(br);
                
                System.out.println(!name.equals(em_name));
                System.out.println(!position.equals(pos));
                System.out.println(!branch.equals(br));
                if(!name.equals(em_name))
                {
                    String log_statement = "(Admin) Name changed from " + em_name + " to " + name + " where id = " + id;
                    String sql_log = "insert into tbl_log_employee(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
                if(!position.equals(pos))
                {
                    String log_statement = "(Admin) Position changed from " + pos + " to " + position + " where id = " + id;
                    String sql_log = "insert into tbl_log_employee(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
                if(!branch.equals(br))
                {
                    String log_statement = "(Admin) Branch changed from " + br + " to " + branch + " where id = " + id;
                    String sql_log = "insert into tbl_log_employee(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
            }
            //log end
            
            String sql = "update tbl_reg set name='"+name+"',position='"+position+"',branch='"+branch+"' where id='"+id+"'";
            System.out.println(sql);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);
            System.out.println(rs);
            if(rs==1)
            {
                JOptionPane.showMessageDialog(this, "Updated Successfully");
                fetch2();
                clear2();
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_update_employeeActionPerformed

    private void jButton_insert_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insert_employeeActionPerformed
        String name=jTextField_name_employee.getText();
        System.out.println(name);
        String position=jTextField_position_employee.getText();
        System.out.println(position);
        String branch=jTextField_branch_employee.getText();
        System.out.println(branch);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "insert into tbl_reg(name,position,branch,email,password)values('"+name+"','"+position+"','"+branch+"','null','null')";
            System.out.println(sql);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);
            System.out.println(rs);
            if(rs==1)
            {
                JOptionPane.showMessageDialog(this, "Added Successfully");
                
                //log
                String log_statement = "(Admin) Employee added where name = " + name + " , position = " + position + " and branch = " + branch;
                String sql_log = "insert into tbl_log_employee(log,user)values('"+log_statement+"','admin')";
                System.out.println(sql_log);
                Statement stmt_log=con.createStatement();
                int rs_log=stmt_log.executeUpdate(sql_log);
                System.out.println(rs_log);
                //log end
                
                fetch2();
                clear2();
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_insert_employeeActionPerformed

    private void jButton_delete_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete_employeeActionPerformed
        DefaultTableModel dl=(DefaultTableModel)jTable_employee.getModel();
        int index=jTable_employee.getSelectedRow();
        int id=Integer.parseInt(dl.getValueAt(index,0).toString());
        
        //for log
        String name=jTextField_name_employee.getText();
        System.out.println(name);
        String branch=jTextField_branch_employee.getText();
        System.out.println(branch);
        //
        
        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to delete","warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult==JOptionPane.YES_OPTION)
        {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
                String sql="delete from tbl_reg where id = '"+id+"'";
                System.out.println(sql);
                Statement stmt=con.createStatement();
                int rs=stmt.executeUpdate(sql);
                System.out.println(rs);
                if(rs==1){
                    JOptionPane.showMessageDialog(this, "Deleted Successfully");
                    
                    //log
                    String log_statement = "(Admin) Employee account deleted where name = " + name + " , branch = " + branch + " and id = " + id;
                    String sql_log = "insert into tbl_log_employee(log,user)values('"+log_statement+"','admin')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                    //log end
                    
                    fetch2();
                    clear2();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton_delete_employeeActionPerformed

    private void jButton_refresh_account2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refresh_account2ActionPerformed
        JOptionPane.showMessageDialog(this, "Refresh Completed");
        fetch();
        clear();
    }//GEN-LAST:event_jButton_refresh_account2ActionPerformed

    private void jButton_refresh_account3jButton_delete_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refresh_account3jButton_delete_accountActionPerformed
        JOptionPane.showMessageDialog(this, "Refresh Completed");
        fetch2();
        clear2();
    }//GEN-LAST:event_jButton_refresh_account3jButton_delete_accountActionPerformed

    private void jButton_refresh_log_transactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refresh_log_transactionActionPerformed
        JOptionPane.showMessageDialog(this, "Refresh Completed");
        fetch3();
        fetch_log_account();
        fetch_log_employee();
    }//GEN-LAST:event_jButton_refresh_log_transactionActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String log = jComboBox1.getSelectedItem().toString();
        if (log == "Transaction log") {
            jTable_log3.setVisible(true);
            jTable_account_log.setVisible(false);
            jTable_employee_log.setVisible(false);
            
            jComboBox_user.setVisible(false);
            //JOptionPane.showMessageDialog(this, "Done");
            //fetch3();
        }else if (log == "Account log") {
            jTable_account_log.setVisible(true);
            jTable_log3.setVisible(false);
            jTable_employee_log.setVisible(false);
            jComboBox_user.setVisible(true);
            //JOptionPane.showMessageDialog(this, "Done");
            //fetch_log_account();
        }else if (log == "Employee log") {
            jTable_employee_log.setVisible(true);
            jTable_log3.setVisible(false);
            jTable_account_log.setVisible(false);
            
            jComboBox_user.setVisible(false);
            //JOptionPane.showMessageDialog(this, "Done");
            //fetch_log_employee();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jLabel_menuitem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_menuitem4MouseClicked
        
    }//GEN-LAST:event_jLabel_menuitem4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user = jComboBox_user.getSelectedItem().toString();
        String log = jComboBox1.getSelectedItem().toString();
        if (log == "Transaction log" && user == "Employee") {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }else if (log == "Transaction log" && user == "Admin") {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }else if (log == "Employee log" && user == "Employee") {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }else if (log == "Employee log" && user == "Admin") {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }
        /////////////////////////////////////////////
        else if (user == "All") {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }else if (user == "Employee") {
            fetch_log_account__employee();
        }else if (user == "Admin") {
            fetch_log_account__admin();
        }else {
            fetch3();
            fetch_log_account();
            fetch_log_employee();
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_delete_account2;
    private javax.swing.JButton jButton_delete_employee;
    private javax.swing.JButton jButton_insert_account2;
    private javax.swing.JButton jButton_insert_employee;
    private javax.swing.JButton jButton_refresh_account2;
    private javax.swing.JButton jButton_refresh_account3;
    private javax.swing.JButton jButton_refresh_log_transaction;
    private javax.swing.JButton jButton_update_account2;
    private javax.swing.JButton jButton_update_employee;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_logout;
    private javax.swing.JLabel jLabel_menuitem1;
    private javax.swing.JLabel jLabel_menuitem3;
    private javax.swing.JLabel jLabel_menuitem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_account2;
    private javax.swing.JPanel jPanel_employee;
    private javax.swing.JPanel jPanel_log3;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_pink;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable_account2;
    private javax.swing.JTable jTable_account_log;
    private javax.swing.JTable jTable_employee;
    private javax.swing.JTable jTable_employee_log;
    private javax.swing.JTable jTable_log3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_acc_no_account2;
    private javax.swing.JTextField jTextField_balance_account2;
    private javax.swing.JTextField jTextField_branch_employee;
    private javax.swing.JTextField jTextField_name_account2;
    private javax.swing.JTextField jTextField_name_employee;
    private javax.swing.JTextField jTextField_position_employee;
    // End of variables declaration//GEN-END:variables

    

//    private void initComponents() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
