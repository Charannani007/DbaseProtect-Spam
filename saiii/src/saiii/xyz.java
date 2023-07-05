package saiii;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class xyz extends JFrame {
	private JFrame frame;
	public xyz() {
		
        frame = new JFrame("Protect Spam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        // Create a panel for the picture
        ImageIcon img=new ImageIcon(getClass().getResource("6.jpg"));
        JLabel x=new JLabel(img);
        frame.add(x);
        frame.pack();
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create the "Inbox" menu
        JMenu inboxMenu = new JMenu("Inbox");

        // Create the "Insert" menu item
        JMenuItem insertInboxItem = new JMenuItem("Insert");
        insertInboxItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for inserting email details
                JTextField idField = new JTextField();
                JTextField toField = new JTextField();
                JTextField fromField = new JTextField();
                JTextField typeField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(4, 2));
                panel.add(new JLabel("ID:"));
                panel.add(idField);
                panel.add(new JLabel("To:"));
                panel.add(toField);
                panel.add(new JLabel("From:"));
                panel.add(fromField);
                panel.add(new JLabel("Type:"));
                panel.add(typeField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Insert Email", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String id = idField.getText();
                    String to = toField.getText();
                    String from = fromField.getText();
                    String type = typeField.getText();

                
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

            try {
                // Load the JDBC driver
                Class.forName(driverClassName);

                // Establish a connection to the database
                Connection con = DriverManager.getConnection(url, username, pass);

                // Perform database operations using the connection
    			Statement stmt=con.createStatement();  
    			int a=stmt.executeUpdate("insert into inbox values('"+to+"','"+from+"','"+id+"','"+type+"')");  
				if(a>0) {
					JOptionPane.showMessageDialog(null, "Inserted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
					ImageIcon img1=new ImageIcon(getClass().getResource("1.jpg"));
			        JLabel y=new JLabel(img1);
			        frame.add(y);
			        frame.setVisible(true);
				}
				
				else
					JOptionPane.showMessageDialog(null, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
                // Close the connection
                con.close();

                System.out.println("Connection closed successfully.");
            } catch (ClassNotFoundException s) {
                System.err.println("Failed to load JDBC driver: " + s.getMessage());
            } catch (SQLException s) {
                System.err.println("Failed to connect to the database: " + s.getMessage());
            }


                }
            }
        });

        // Create the "Update" menu item
        JMenuItem updateInboxItem = new JMenuItem("Update");
        updateInboxItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for updating email details
                JTextField idField = new JTextField();
                JTextField toField = new JTextField();
                JTextField fromField = new JTextField();
                JTextField typeField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(4, 2));
                panel.add(new JLabel("ID:"));
                panel.add(idField);
                panel.add(new JLabel("To:"));
                panel.add(toField);
                panel.add(new JLabel("From:"));
                panel.add(fromField);
                panel.add(new JLabel("Type:"));
                panel.add(typeField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Email", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String id = idField.getText();
                    String to = toField.getText();
                    String from = fromField.getText();
                    String type = typeField.getText();

                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

            try {
                // Load the JDBC driver
                Class.forName(driverClassName);

                // Establish a connection to the database
                Connection con = DriverManager.getConnection(url, username, pass);

                // Perform database operations using the connection
    			Statement stmt=con.createStatement();  
    			int a = stmt.executeUpdate("UPDATE inbox SET t_o='" + to + "',from_ ='" + from + "',type='" + type + "'WHERE id='" + id + "'"); 
				if(a>0)
					JOptionPane.showMessageDialog(null, "Updated successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "ID Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                // Close the connection
                con.close();
                System.out.println("Connection closed successfully.");
            } catch (ClassNotFoundException s) {
                System.err.println("Failed to load JDBC driver: " + s.getMessage());
            } catch (SQLException s) {
                System.err.println("Failed to connect to the database: " + s.getMessage());
            }
                }
            }
        });

        // Create the "Delete" menu item
        JMenuItem deleteInboxItem = new JMenuItem("Delete");
        deleteInboxItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for deleting email by ID
                JTextField idField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(new JLabel("ID:"));
                panel.add(idField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Email", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String id = idField.getText();


                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

            try {
                // Load the JDBC driver
                Class.forName(driverClassName);

                // Establish a connection to the database
                Connection con = DriverManager.getConnection(url, username, pass);

                // Perform database operations using the connection
    			Statement stmt=con.createStatement();  
    			int a=stmt.executeUpdate("delete from inbox where id='"+id+"'");  
				if(a>0)
					JOptionPane.showMessageDialog(null, "Deleted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "ID Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                // Close the connection
                con.close();

                System.out.println("Connection closed successfully.");
            } catch (ClassNotFoundException s) {
                System.err.println("Failed to load JDBC driver: " + s.getMessage());
            } catch (SQLException s) {
                System.err.println("Failed to connect to the database: " + s.getMessage());
            }
                }
            }
        });

        inboxMenu.add(insertInboxItem);
        inboxMenu.add(updateInboxItem);
        inboxMenu.add(deleteInboxItem);

        // Create the "Spam" menu
        JMenu spamMenu = new JMenu("Spam");

        // Create the "Insert" menu item
        JMenuItem insertSpamItem = new JMenuItem("Insert");
        insertSpamItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for inserting spam details
                JTextField resolveIdField = new JTextField();
                JTextField typeOfSpamField = new JTextField();
                JTextField spamMsgField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);
                panel.add(new JLabel("Type of Spam:"));
                panel.add(typeOfSpamField);
                panel.add(new JLabel("Spam Message:"));
                panel.add(spamMsgField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Insert Spam", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();
                    String typeOfSpam = typeOfSpamField.getText();
                    String spamMsg = spamMsgField.getText();

                 // Example logic: Display the inbox message
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a=stmt.executeUpdate("insert into spam values('"+resolveId+"','"+typeOfSpam+"','"+spamMsg+"')");  
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Inserted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }

                }
            }
        });

        // Create the "Update" menu item
        JMenuItem updateSpamItem = new JMenuItem("Update");
        updateSpamItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for updating spam details
                JTextField resolveIdField = new JTextField();
                JTextField typeOfSpamField = new JTextField();
                JTextField spamMsgField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);
                panel.add(new JLabel("Type of Spam:"));
                panel.add(typeOfSpamField);
                panel.add(new JLabel("Spam Message:"));
                panel.add(spamMsgField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Spam", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();
                    String typeOfSpam = typeOfSpamField.getText();
                    String spamMsg = spamMsgField.getText();

                 // Example logic: Display the inbox message
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a = stmt.executeUpdate("UPDATE spam SET type_of_spam='" + typeOfSpam + "',spam_msg ='" + spamMsg + "'WHERE resolve_id='" + resolveId + "'"); 
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Updated successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "ResolveId Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }

                }
            }
        });

        // Create the "Delete" menu item
        JMenuItem deleteSpamItem = new JMenuItem("Delete");
        deleteSpamItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for deleting spam by Resolve ID
                JTextField resolveIdField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Spam", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();

                 // Example logic: Display the inbox message
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a=stmt.executeUpdate("delete from spam where resolve_id='"+resolveId+"'");  
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Deleted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "ResolveId Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }

                }
            }
        });

        spamMenu.add(insertSpamItem);
        spamMenu.add(updateSpamItem);
        spamMenu.add(deleteSpamItem);

        // Create the "Resolve" menu
        JMenu resolveMenu = new JMenu("Resolve");

        // Create the "Insert" menu item
        JMenuItem insertResolveItem = new JMenuItem("Insert");
        insertResolveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for inserting resolve details
                JTextField resolveIdField = new JTextField();
                JTextField resolveMsgField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);
                panel.add(new JLabel("Resolve Message:"));
                panel.add(resolveMsgField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Insert Resolve", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();
                    String resolveMsg = resolveMsgField.getText();

                    // Example logic: Display the resolve details
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a=stmt.executeUpdate("insert into resolve values('"+resolveId+"','"+resolveMsg+"')");  
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Inserted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }

                }
            }
        });

        // Create the "Update" menu item
        JMenuItem updateResolveItem = new JMenuItem("Update");
        updateResolveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for updating resolve details
                JTextField resolveIdField = new JTextField();
                JTextField resolveMsgField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);
                panel.add(new JLabel("Resolve Message:"));
                panel.add(resolveMsgField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Resolve", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();
                    String resolveMsg = resolveMsgField.getText();

                 // Example logic: Display the resolve details
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a = stmt.executeUpdate("UPDATE resolve SET description='" + resolveMsg + "'WHERE resolve_id='" + resolveId + "'"); 
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Upadted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "ResolveId Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }
                }
            }
        });

        // Create the "Delete" menu item
        JMenuItem deleteResolveItem = new JMenuItem("Delete");
        deleteResolveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show input dialog for deleting resolve by Resolve ID
                JTextField resolveIdField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(new JLabel("Resolve ID:"));
                panel.add(resolveIdField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Resolve", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String resolveId = resolveIdField.getText();

                    // Example logic: Display the resolve details
                    String driverClassName = "oracle.jdbc.driver.OracleDriver";
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "saicharan";
                    String pass = "saicharan";

                    try {
                        // Load the JDBC driver
                        Class.forName(driverClassName);

                        // Establish a connection to the database
                        Connection con = DriverManager.getConnection(url, username, pass);

                        // Perform database operations using the connection
            			Statement stmt=con.createStatement();  
            			int a=stmt.executeUpdate("delete from resolve where resolve_id='"+resolveId+"'"); 
            			stmt.executeUpdate("commit");
        				if(a>0)
        					JOptionPane.showMessageDialog(null, "Deleted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(null, "ResolveId Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                        // Close the connection
                        con.close();

                        System.out.println("Connection closed successfully.");
                    } catch (ClassNotFoundException s) {
                        System.err.println("Failed to load JDBC driver: " + s.getMessage());
                    } catch (SQLException s) {
                        System.err.println("Failed to connect to the database: " + s.getMessage());
                    }
                }
            }
        });
        
        JMenuItem viewResolveItem = new JMenuItem("View");
        viewResolveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Example logic: Display the resolve details
                String driverClassName = "oracle.jdbc.driver.OracleDriver";
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                String username = "saicharan";
                String pass = "saicharan";

                try {
                    // Load the JDBC driver
                    Class.forName(driverClassName);

                    // Establish a connection to the database
                    Connection con = DriverManager.getConnection(url, username, pass);

                    // Perform database operations using the connection
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM resolve");
                    StringBuilder sb = new StringBuilder();

                    while (rs.next()) {
                        String x = rs.getString("resolve_id");
                        String m = rs.getString("description");

                        sb.append(x).append(": ").append(m).append("\n");
                    }
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));

                    //JFrame frame = new JFrame(); // Add this line to define the frame

                    JOptionPane.showMessageDialog(frame, scrollPane, "Spam Solutions", JOptionPane.PLAIN_MESSAGE);
                    con.close();

                    System.out.println("Connection closed successfully.");
                } catch (ClassNotFoundException s) {
                    System.err.println("Failed to load JDBC driver: " + s.getMessage());
                } catch (SQLException s) {
                    System.err.println("Failed to connect to the database: " + s.getMessage());
                }
            }
        });


        resolveMenu.add(insertResolveItem);
        resolveMenu.add(updateResolveItem);
        resolveMenu.add(deleteResolveItem);
        resolveMenu.add(viewResolveItem);
        

        menuBar.add(inboxMenu);
        menuBar.add(spamMenu);
        menuBar.add(resolveMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new xyz().setVisible(true);
                
            }
        });
    }
}