/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbanking_2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alokd
 */
public class dashboard extends javax.swing.JFrame {

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
    
    public dashboard() {
        initComponents();
        fetch();
        clear();
        clear2();
        clear3();
        
        
        
        jPanel_account2.setVisible(true);
        jPanel_transact.setVisible(false);
        jPanel_transfer.setVisible(false);
        
        ///////////
        jTable_account2.getTableHeader().setFont(new Font("Segeo UI", Font.BOLD, 12));
        jTable_account2.getTableHeader().setOpaque(true);
        jTable_account2.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_account2.getTableHeader().setForeground(new Color(51, 51, 54));
        jTable_account2.setRowHeight(25);
        
        
        // populate the menulabels array
        // you can use a for loop to do that
        menuLabels [0] = jLabel_menuitem1;
        //menuLabels [1] = jLabel_menuitem2;
        menuLabels [1] = jLabel_menuitem3;
        menuLabels [2] = jLabel_menuitem4;
        
        
        
        // populate the panels array
        panels[0] = jPanel_account2;
        panels[1] = jPanel_transact;
        panels[2] = jPanel_transfer;
        
        addActionToMenuLabels();
        
    }
    
    //Timer to hide the message panel
    Timer timerUp = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_account_details.getHeight() != 0)
            {
                jPanel_account_details.setBounds(jPanel_account_details.getX(), jPanel_account_details.getY(), jPanel_account_details.getWidth(), jPanel_account_details.getHeight() - 5);
                
            }
            else
            {
                timerUp.stop();
            }
        }
    });
    
    //Timer to show the message panel
    Timer timerDown = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_account_details.getHeight() != 180)
            {
                jPanel_account_details.setBounds(jPanel_account_details.getX(), jPanel_account_details.getY(), jPanel_account_details.getWidth(), jPanel_account_details.getHeight() + 5);
                
            }
            else
            {
                timerDown.stop();
            }
        }
    });
    
    //////////////////////////////////////////////////////////////////////////////
    
    //Timer2 to hide the message panel
    Timer timerUp2 = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_error.getHeight() != 0)
            {
                jPanel_error.setBounds(jPanel_error.getX(), jPanel_error.getY(), jPanel_error.getWidth(), jPanel_error.getHeight() - 5);
                
            }
            else
            {
                timerUp2.stop();
            }
        }
    });
    
    //Timer2 to show the message panel
    Timer timerDown2 = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_error.getHeight() != 70)
            {
                jPanel_error.setBounds(jPanel_error.getX(), jPanel_error.getY(), jPanel_error.getWidth(), jPanel_error.getHeight() + 5);
                
            }
            else
            {
                timerDown2.stop();
            }
        }
    });
    //////////
    //Timer3 to hide the message panel
    Timer timerUp3_transfer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_error2_transfer.getHeight() != 0)
            {
                jPanel_error2_transfer.setBounds(jPanel_error2_transfer.getX(), jPanel_error2_transfer.getY(), jPanel_error2_transfer.getWidth(), jPanel_error2_transfer.getHeight() - 5);
                
            }
            else
            {
                timerUp3_transfer.stop();
            }
        }
    });
    
    //Timer3 to show the message panel
    Timer timerDown3_transfer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(jPanel_error2_transfer.getHeight() != 70)
            {
                jPanel_error2_transfer.setBounds(jPanel_error2_transfer.getX(), jPanel_error2_transfer.getY(), jPanel_error2_transfer.getWidth(), jPanel_error2_transfer.getHeight() + 5);
                
            }
            else
            {
                timerDown3_transfer.stop();
            }
        }
    });
    
    private void clear()
    {
        jTextField_acc_no_account2.setText("");
        jTextField_name_account2.setText("");
    }
    
        //Employee jform
    
    private void clear2()
    {        
        jTextField_accno.setText("");
        jTextField_amount.setText("");
    }
    
    //for fund transfer
    private void clear3()
    {        
        jTextField_from.setText("");
        jTextField_to.setText("");
        jTextField_amount2.setText("");
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
                                    
                            case "WITHDRAW / DEPOSIT":
                                showPanel(jPanel_transact);
                                break;
                                
                            case "FUND TRANSFER":
                                showPanel(jPanel_transfer);
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
        jLabel3 = new javax.swing.JLabel();
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
        jButton_delete_account2 = new javax.swing.JButton();
        jButton_update_account2 = new javax.swing.JButton();
        jButton_insert_account2 = new javax.swing.JButton();
        jButton_refresh_account2 = new javax.swing.JButton();
        jPanel_transact = new javax.swing.JPanel();
        jTextField_accno = new javax.swing.JTextField();
        jTextField_amount = new javax.swing.JTextField();
        jButton_deposit = new javax.swing.JButton();
        jButton_withdraw = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_checkAcc = new javax.swing.JButton();
        jPanel_account_details = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_accno = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_name = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel_bal = new javax.swing.JLabel();
        jPanel_error = new javax.swing.JPanel();
        jLabel_error_message = new javax.swing.JLabel();
        jLabel_up = new javax.swing.JLabel();
        jPanel_transfer = new javax.swing.JPanel();
        jTextField_from = new javax.swing.JTextField();
        jTextField_to = new javax.swing.JTextField();
        jButton_transfer = new javax.swing.JButton();
        jLabel_switch = new javax.swing.JLabel();
        jTextField_amount2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel_error2_transfer = new javax.swing.JPanel();
        jLabel_error_message2 = new javax.swing.JLabel();
        jLabel_up2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

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

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("EMPLOYEE");
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_menu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_menuitem3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_menuitem3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel_menuitem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuitem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/moneybox-24.png"))); // NOI18N
        jLabel_menuitem3.setText("WITHDRAW / DEPOSIT");
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
        jLabel_menuitem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/banknotes-24.png"))); // NOI18N
        jLabel_menuitem4.setText("FUND TRANSFER");
        jLabel_menuitem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuitem4.setOpaque(true);

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_menuitem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_menuitem3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(jLabel_menuitem4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                jButton_refresh_account2jButton_delete_accountActionPerformed(evt);
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
                    .addComponent(jTextField_name_account2)
                    .addComponent(jTextField_acc_no_account2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_account2Layout.createSequentialGroup()
                        .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton_refresh_account2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_account2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_insert_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_refresh_account2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_transact.setBackground(new java.awt.Color(240, 246, 246));
        jPanel_transact.setPreferredSize(new java.awt.Dimension(721, 489));

        jTextField_accno.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_accno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_accnoActionPerformed(evt);
            }
        });

        jTextField_amount.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        jButton_deposit.setBackground(new java.awt.Color(32, 136, 203));
        jButton_deposit.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_deposit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_deposit.setText("Deposit");
        jButton_deposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_depositActionPerformed(evt);
            }
        });

        jButton_withdraw.setBackground(new java.awt.Color(32, 136, 203));
        jButton_withdraw.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_withdraw.setForeground(new java.awt.Color(255, 255, 255));
        jButton_withdraw.setText("Withdraw");
        jButton_withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_withdrawActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel2.setText("Account no");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel4.setText("Amount");

        jButton_checkAcc.setBackground(new java.awt.Color(32, 136, 203));
        jButton_checkAcc.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_checkAcc.setForeground(new java.awt.Color(255, 255, 255));
        jButton_checkAcc.setText("Check Account Details");
        jButton_checkAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_checkAccActionPerformed(evt);
            }
        });

        jPanel_account_details.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel_account_details.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jPanel_account_details.setPreferredSize(new java.awt.Dimension(243, 198));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Account Number :");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel_accno.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel_accno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_accno.setText("                ************");
        jLabel_accno.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Name :");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel_name.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel_name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_name.setText("                ************");
        jLabel_name.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Balance :");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel_bal.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel_bal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_bal.setText("                ************");
        jLabel_bal.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel_account_detailsLayout = new javax.swing.GroupLayout(jPanel_account_details);
        jPanel_account_details.setLayout(jPanel_account_detailsLayout);
        jPanel_account_detailsLayout.setHorizontalGroup(
            jPanel_account_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_account_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_account_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_account_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_accno, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_bal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel_account_detailsLayout.setVerticalGroup(
            jPanel_account_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_account_detailsLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_accno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_bal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_error.setBackground(new java.awt.Color(255, 204, 204));
        jPanel_error.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel_error_message.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        jLabel_error_message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_error_message.setText("test message");

        jLabel_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/arrow-141-16.png"))); // NOI18N
        jLabel_up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_upMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_errorLayout = new javax.swing.GroupLayout(jPanel_error);
        jPanel_error.setLayout(jPanel_errorLayout);
        jPanel_errorLayout.setHorizontalGroup(
            jPanel_errorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_errorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_error_message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_errorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel_up, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_errorLayout.setVerticalGroup(
            jPanel_errorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_errorLayout.createSequentialGroup()
                .addComponent(jLabel_up, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel_error_message)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_transactLayout = new javax.swing.GroupLayout(jPanel_transact);
        jPanel_transact.setLayout(jPanel_transactLayout);
        jPanel_transactLayout.setHorizontalGroup(
            jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_transactLayout.createSequentialGroup()
                .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_transactLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_transactLayout.createSequentialGroup()
                                .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel_transactLayout.createSequentialGroup()
                                        .addComponent(jButton_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(jButton_withdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField_amount))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_account_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_transactLayout.createSequentialGroup()
                                .addComponent(jTextField_accno, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton_checkAcc))))
                    .addGroup(jPanel_transactLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_error, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_transactLayout.setVerticalGroup(
            jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_transactLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_accno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton_checkAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel_account_details, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addGroup(jPanel_transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_withdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jPanel_error, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        jPanel_transfer.setBackground(new java.awt.Color(240, 246, 246));
        jPanel_transfer.setPreferredSize(new java.awt.Dimension(721, 489));

        jTextField_from.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField_from.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_fromActionPerformed(evt);
            }
        });

        jTextField_to.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        jButton_transfer.setBackground(new java.awt.Color(32, 136, 203));
        jButton_transfer.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_transfer.setForeground(new java.awt.Color(255, 255, 255));
        jButton_transfer.setText("Transfer");
        jButton_transfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_transferActionPerformed(evt);
            }
        });

        jLabel_switch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/two-way-arrows.png"))); // NOI18N
        jLabel_switch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_switch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_switchMouseClicked(evt);
            }
        });

        jTextField_amount2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel7.setText("From A/c");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel10.setText("To A/c");

        jPanel_error2_transfer.setBackground(new java.awt.Color(255, 204, 204));
        jPanel_error2_transfer.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel_error_message2.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        jLabel_error_message2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_error_message2.setText("test message");

        jLabel_up2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/arrow-141-16.png"))); // NOI18N
        jLabel_up2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_up2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_up2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_error2_transferLayout = new javax.swing.GroupLayout(jPanel_error2_transfer);
        jPanel_error2_transfer.setLayout(jPanel_error2_transferLayout);
        jPanel_error2_transferLayout.setHorizontalGroup(
            jPanel_error2_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_error2_transferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_error_message2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_error2_transferLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel_up2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_error2_transferLayout.setVerticalGroup(
            jPanel_error2_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_error2_transferLayout.createSequentialGroup()
                .addComponent(jLabel_up2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel_error_message2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel14.setText("Amount");

        javax.swing.GroupLayout jPanel_transferLayout = new javax.swing.GroupLayout(jPanel_transfer);
        jPanel_transfer.setLayout(jPanel_transferLayout);
        jPanel_transferLayout.setHorizontalGroup(
            jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_transferLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_transferLayout.createSequentialGroup()
                        .addComponent(jTextField_from, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel_switch))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_to, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(40, 40, 40))
            .addGroup(jPanel_transferLayout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jButton_transfer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_transferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_error2_transfer, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_transferLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jTextField_amount2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(259, 259, 259))
        );
        jPanel_transferLayout.setVerticalGroup(
            jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_transferLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel_transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_from, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_to, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_switch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel_error2_transfer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_amount2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton_transfer, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout jPanel_pinkLayout = new javax.swing.GroupLayout(jPanel_pink);
        jPanel_pink.setLayout(jPanel_pinkLayout);
        jPanel_pinkLayout.setHorizontalGroup(
            jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_account2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_transact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_transfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_pinkLayout.setVerticalGroup(
            jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_account2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_transact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_pinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_transfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel_pink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "insert into tbl_customer_details(account_no,name,balance)values('"+ac_no+"','"+name+"','0')";
            System.out.println(sql);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);
            System.out.println(rs);
            if(rs==1)
            {
                JOptionPane.showMessageDialog(this, "Added Successfully");
                
                //log
                String log_statement = "A/c added where A/c number = " + ac_no + " and name = " + name;
                String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','employee')";
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
                System.out.println(account);
                System.out.println(customername);
                
                System.out.println(!ac_no.equals(account));
                System.out.println(!name.equals(customername));
                if(!ac_no.equals(account))
                {
                    String log_statement = "A/c number changed from " + account + " to " + ac_no + " where id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','employee')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
                if(!name.equals(customername))
                {
                    String log_statement = "Name changed from " + customername + " to " + name + " where A/c number = " +ac_no+ " and id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','employee')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                }
            }
            //log end
            
            String sql = "update tbl_customer_details set account_no='"+ac_no+"',name='"+name+"' where id='"+id+"'";
            
            
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
                    String log_statement = "A/c deleted where A/c number = " + ac_no + " and id = " + id;
                    String sql_log = "insert into tbl_log_account(log,user)values('"+log_statement+"','employee')";
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
        //jTextField_balance_account2.setText(d1.getValueAt(selectIndex, 3).toString());
    }//GEN-LAST:event_jTable_accountMouseClicked

    private void jTextField_accnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_accnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_accnoActionPerformed

    private void jButton_depositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_depositActionPerformed
        String account_no=jTextField_accno.getText();
        System.out.println(account_no);
        String amount=jTextField_amount.getText();
        System.out.println(amount);
        
        if(amount.equals(""))
        {
            jLabel_error_message.setText("Please enter an amount");
            //show jpanel message
            timerDown2.start();
        }
        
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "select balance from tbl_customer_details where account_no='"+account_no+"'";

            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            if(rs.next())
            {
                String am = rs.getString("balance");
                Integer f1=Integer.parseInt(amount);
                Integer f2=Integer.parseInt(am);
                Integer f3=f2+f1;
                String s=String.valueOf(f3);
                System.out.println(s);

                String sq = "update tbl_customer_details set balance='"+s+"' where account_no='"+account_no+"'";
                System.out.println(sq);
                Statement stmt=con.createStatement();
                int rs2=stmt.executeUpdate(sq);
                System.out.println(rs2);
                if(rs2==1)
                {
                    //log
                    String log = "Rs. "+amount+" deposited to A/c "+account_no;
                    String sql_log = "insert into tbl_log(log)values('"+log+"')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                    //////
                    
                    JOptionPane.showMessageDialog(this, "Deposit Successfully");
                    clear2();
                    
                    // hide the jpanel message
                    timerUp.start();
                    // hide the jpanel message
                    timerUp2.start();
                }
            }else
            {
                //for data validation
                if(account_no.equals(""))
                {
                    jLabel_error_message.setText("Please enter an account number");
                    //show jpanel message
                    timerDown2.start();
                }/*else if(amount.equals(""))
                {
                    jLabel_error_message.setText("Please enter an amount");
                    //show jpanel message
                    timerDown2.start();
                }*/else
                {
                    jLabel_error_message.setText("Incorrect account number");
                    //show jpanel message
                    timerDown2.start();
                }
                //////////////////////
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_depositActionPerformed

    private void jButton_withdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_withdrawActionPerformed

        String account_no=jTextField_accno.getText();
        System.out.println(account_no);
        String amount=jTextField_amount.getText();
        int a = Integer.parseInt(amount);
        System.out.println(amount);
        
        if(amount.equals(""))
        {
            jLabel_error_message.setText("Please enter an amount");
            //show jpanel message
            timerDown2.start();
        }
        
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "select balance from tbl_customer_details where account_no='"+account_no+"'";

            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            if(rs.next())
            {
                
                String am = rs.getString("balance");
                int b = Integer.parseInt(am);
                
                if(a < b){
                Integer f1=Integer.parseInt(amount);
                Integer f2=Integer.parseInt(am);
                Integer f3=f2-f1;
                String s=String.valueOf(f3);
                System.out.println(s);
                

                String sq = "update tbl_customer_details set balance='"+s+"' where account_no='"+account_no+"'";
                System.out.println(sq);
                Statement stmt=con.createStatement();
                int rs2=stmt.executeUpdate(sq);
                System.out.println(rs2);
                if(rs2==1)
                {
                    //log
                    String log = "Rs. "+amount+" withdrawn from A/c "+account_no;
                    String sql_log = "insert into tbl_log(log)values('"+log+"')";
                    System.out.println(sql_log);
                    Statement stmt_log=con.createStatement();
                    int rs_log=stmt_log.executeUpdate(sql_log);
                    System.out.println(rs_log);
                    //////
                    
                    JOptionPane.showMessageDialog(this, "Withdrawal Successfully");
                    clear2();
                    
                    // hide the jpanel message
                    timerUp.start();
                    // hide the jpanel message
                    timerUp2.start();
                }
                }else{
                    //JOptionPane.showMessageDialog(this, "Amount exceeds available balance");
                    jLabel_error_message.setText("Amount exceeds maximum withdrawal limit for this account");
                    //show jpanel message
                    timerDown2.start();
                }
            }else
            {
                //for data validation
                if(account_no.equals(""))
                {
                    jLabel_error_message.setText("Please enter an account number");
                    //show jpanel message
                    timerDown2.start();
                }/*else if(amount.equals(""))
                {
                    jLabel_error_message.setText("Please enter an amount");
                    //show jpanel message
                    timerDown2.start();
                }*/else
                {
                    jLabel_error_message.setText("Incorrect account number");
                    //show jpanel message
                    timerDown2.start();
                }
                //////////////////////
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_withdrawActionPerformed

    private void jButton_refresh_account2jButton_delete_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refresh_account2jButton_delete_accountActionPerformed
        JOptionPane.showMessageDialog(this, "Refresh Completed");
        fetch();
        clear();
    }//GEN-LAST:event_jButton_refresh_account2jButton_delete_accountActionPerformed

    private void jButton_checkAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_checkAccActionPerformed
        String account_no=jTextField_accno.getText();
        System.out.println(account_no);
        
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "select * from tbl_customer_details where account_no='"+account_no+"'";

            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            if(rs.next())
            {
                String an = rs.getString("account_no");
		String name = rs.getString("name");
		String bal = rs.getString("balance");
                
                jLabel_accno.setText("      "+an);
		jLabel_name.setText("      "+name);
		jLabel_bal.setText("      "+bal);
                
                // hide the jpanel message
                timerUp2.start();
                //show jpanel message
                timerDown.start();
            }else
            {
                //for data validation
                if(account_no.equals(""))
                {
                    jLabel_error_message.setText("Please enter an account number");
                    //show jpanel message
                    timerDown2.start();
                }else
                {
                    jLabel_error_message.setText("Incorrect account number");
                }
                
                //show jpanel message
                timerDown2.start();
                timerUp.start();
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        
        
    }//GEN-LAST:event_jButton_checkAccActionPerformed

    private void jLabel_upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_upMouseClicked
        // hide the jpanel message
        timerUp2.start();

    }//GEN-LAST:event_jLabel_upMouseClicked

    private void jTextField_fromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_fromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fromActionPerformed

    private void jButton_transferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_transferActionPerformed
        String from=jTextField_from.getText();
        System.out.println(from);
        String to=jTextField_to.getText();
        System.out.println(to);
        String amount=jTextField_amount2.getText();
        int amou = Integer.parseInt(amount);
        System.out.println(amount);
        
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_internetbanking","root","");
            String sql = "select * from tbl_customer_details where account_no='"+from+"'";
            String sql2 = "select * from tbl_customer_details where account_no='"+to+"'";

            System.out.println(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);

            System.out.println(sql2);
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            System.out.println(rs2);

            if(rs.next() && rs2.next())// && amount.equals("")
            {
                String bal1 = rs.getString("balance");
                int bala = Integer.parseInt(bal1);
                
                if(amou < bala){
                Integer f1=Integer.parseInt(bal1);
                Integer f2=Integer.parseInt(amount);
                Integer f3=f1-f2;
                String s=String.valueOf(f3);
                System.out.println(s);

                String sq = "update tbl_customer_details set balance='"+s+"' where account_no='"+from+"'";
                System.out.println(sq);
                Statement stmt=con.createStatement();
                int rs3=stmt.executeUpdate(sq);
                System.out.println(rs3);

                //////////////////////////////////////

                String bal2 = rs2.getString("balance");
                Integer g1=Integer.parseInt(bal2);
                Integer g2=Integer.parseInt(amount);
                Integer a=g1+g2;
                String s2=String.valueOf(a);
                System.out.println(s2);

                String sq2 = "update tbl_customer_details set balance='"+s2+"' where account_no='"+to+"'";
                System.out.println(sq2);
                Statement stmt2=con.createStatement();
                int rs4=stmt2.executeUpdate(sq2);
                System.out.println(rs4);
                
                /////////////////////////////////////
                //log
                
                String log = "Rs. "+amount+" transfered from A/c "+from+" to A/c "+to;
                String sql_log = "insert into tbl_log(log)values('"+log+"')";
                System.out.println(sql_log);
                Statement stmt_log=con.createStatement();
                int rs_log=stmt_log.executeUpdate(sql_log);
                System.out.println(rs_log);
                
                JOptionPane.showMessageDialog(this, "Fund Transfer Successfully");
                // hide the jpanel message
                timerUp3_transfer.start();
                clear3();
                }else{
                    //JOptionPane.showMessageDialog(this, "Amount exceeds available balance");
                    jLabel_error_message2.setText("Insufficient balance");
                    //show jpanel message
                    timerDown3_transfer.start();
                }
                
            }else {
                jLabel_error_message2.setText("Please enter Correct account number");
                //show jpanel message
                timerDown3_transfer.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        //for data validation
        if(from.equals("") && to.equals(""))
        {
            jLabel_error_message2.setText("Please enter account number");
            //show jpanel message
            timerDown3_transfer.start();
        }else if(from.equals(""))
        {
            jLabel_error_message2.setText("Please enter Sender account number");
            //show jpanel message
            timerDown3_transfer.start();
        }else if(to.equals(""))
        {
            jLabel_error_message2.setText("Please enter Payee account number");
            //show jpanel message
            timerDown3_transfer.start();
        }else if(amount.equals(""))
        {
            jLabel_error_message2.setText("Please enter an amount to transfer");
            //show jpanel message
            timerDown3_transfer.start();
        }
        //////////////////////

        //////////////////////
    }//GEN-LAST:event_jButton_transferActionPerformed

    private void jLabel_switchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_switchMouseClicked
        String payee=jTextField_from.getText();
        System.out.println(payee);
        String recipient=jTextField_to.getText();
        System.out.println(recipient);

        jTextField_from.setText(recipient);
        jTextField_to.setText(payee);
    }//GEN-LAST:event_jLabel_switchMouseClicked

    private void jLabel_up2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_up2MouseClicked
        // hide the jpanel message
        timerUp3_transfer.start();
    }//GEN-LAST:event_jLabel_up2MouseClicked

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_checkAcc;
    private javax.swing.JButton jButton_delete_account2;
    private javax.swing.JButton jButton_deposit;
    private javax.swing.JButton jButton_insert_account2;
    private javax.swing.JButton jButton_refresh_account2;
    private javax.swing.JButton jButton_transfer;
    private javax.swing.JButton jButton_update_account2;
    private javax.swing.JButton jButton_withdraw;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_accno;
    private javax.swing.JLabel jLabel_bal;
    private javax.swing.JLabel jLabel_error_message;
    private javax.swing.JLabel jLabel_error_message2;
    private javax.swing.JLabel jLabel_logout;
    private javax.swing.JLabel jLabel_menuitem1;
    private javax.swing.JLabel jLabel_menuitem3;
    private javax.swing.JLabel jLabel_menuitem4;
    private javax.swing.JLabel jLabel_name;
    private javax.swing.JLabel jLabel_switch;
    private javax.swing.JLabel jLabel_up;
    private javax.swing.JLabel jLabel_up2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_account2;
    private javax.swing.JPanel jPanel_account_details;
    private javax.swing.JPanel jPanel_error;
    private javax.swing.JPanel jPanel_error2_transfer;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_pink;
    private javax.swing.JPanel jPanel_transact;
    private javax.swing.JPanel jPanel_transfer;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_account2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_acc_no_account2;
    private javax.swing.JTextField jTextField_accno;
    private javax.swing.JTextField jTextField_amount;
    private javax.swing.JTextField jTextField_amount2;
    private javax.swing.JTextField jTextField_from;
    private javax.swing.JTextField jTextField_name_account2;
    private javax.swing.JTextField jTextField_to;
    // End of variables declaration//GEN-END:variables

//    private void initComponents() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
