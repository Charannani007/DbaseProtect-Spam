package abc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date; 

public class abc extends JFrame {
    private JTextField signUpUsernameField;
    private JPasswordField signUpPasswordField;
    private JTextField signUpEmailField;
    private JTextField signUpDOBField;
    private JTextField signUpPhoneNumberField;
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    // Database to store player information (In-memory storage for simplicity)
    private Map<String, Player> playerDatabase;

    public abc() {
    	
        // Initialize the database
        playerDatabase = new HashMap<>();

        setTitle("Dbase-Protect Spam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create panels for sign up and login components
        JPanel signUpPanel = createSignUpPanel();
        JPanel loginPanel = createLoginPanel();

        // Create tabbed pane to switch between sign up and login
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sign Up", signUpPanel);
        tabbedPane.addTab("Login", loginPanel);

        // Add tabbed pane to the frame
        getContentPane().add(tabbedPane);
    }

    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Sign Up components
        JLabel signUpUsernameLabel = new JLabel("Username:");
        signUpUsernameField = new JTextField(20);
        JLabel signUpPasswordLabel = new JLabel("Password:");
        signUpPasswordField = new JPasswordField(20);
        JLabel signUpEmailLabel = new JLabel("Email:");
        signUpEmailField = new JTextField(20);
        JLabel signUpDOBLabel = new JLabel("Date of Birth:");
        signUpDOBField = new JTextField(20);
        JLabel signUpPhoneNumberLabel = new JLabel("Phone Number:");
        signUpPhoneNumberField = new JTextField(20);

        JButton signUpButton = new JButton("Sign Up");

        // Sign Up button action listener
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSignIn();
            }
        });

        // Add components to the sign up panel
        panel.add(signUpUsernameLabel);
        panel.add(signUpUsernameField);
        panel.add(signUpPasswordLabel);
        panel.add(signUpPasswordField);
        panel.add(signUpEmailLabel);
        panel.add(signUpEmailField);
        panel.add(signUpDOBLabel);
        panel.add(signUpDOBField);
        panel.add(signUpPhoneNumberLabel);
        panel.add(signUpPhoneNumberField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(signUpButton);

        return panel;
    }


    private void handleSignIn() {
                String username = signUpUsernameField.getText();
                String password = new String(signUpPasswordField.getPassword());
                String email = signUpEmailField.getText();
                String dob = signUpDOBField.getText();
                String phoneNumber = signUpPhoneNumberField.getText();


        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || dob.isEmpty() || phoneNumber.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
        	String driverClassName = "oracle.jdbc.driver.OracleDriver";

        	        try {
        	            // Load the JDBC driver
        	            Class.forName(driverClassName);

        	            // Establish a connection to the database
        	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "saicharan", "saicharan");

        	            // Perform database operations using the connection
        				Statement stmt=con.createStatement();  
        				int a=stmt.executeUpdate("insert into user_details values('"+username+"','"+email+"','"+phoneNumber+"','"+password+"','"+dob+"')");  
        				if(a>0)
        					JOptionPane.showMessageDialog(this, "Inserted successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        				else
        					JOptionPane.showMessageDialog(this, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
        	            // Close the connection
        	            con.close();

        	            System.out.println("Connection closed successfully.");
        	        } catch (ClassNotFoundException e) {
        	            System.err.println("Failed to load JDBC driver: " + e.getMessage());
        	        } catch (SQLException e) {
        	            System.err.println("Failed to connect to the database: " + e.getMessage());
        	        }
        	    }
 
 
        JOptionPane.showMessageDialog(this, "Sign In Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear sign in fields
        signUpUsernameField.setText("");
        signUpPasswordField.setText("");
        signUpEmailField.setText("");
        signUpDOBField.setText("");
        signUpPhoneNumberField.setText("");
    }


    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Login components
        JLabel loginUsernameLabel = new JLabel("Username:");
        loginUsernameField = new JTextField(20);
        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Add components to the login panel
        panel.add(loginUsernameLabel);
        panel.add(loginUsernameField);
        panel.add(loginPasswordLabel);
        panel.add(loginPasswordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        return panel;
    }


    private void handleLogin() {
            String username = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ID and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
        String driverClassName = "oracle.jdbc.driver.OracleDriver";

        try {
            // Load the JDBC driver
            Class.forName(driverClassName);
            
            // Establish a connection to the database
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "saicharan", "saicharan");

            // Perform database operations using the connection
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select password from user_details where username='"+username+"'and password='"+password+"'");  
			if(rs.next())
			{
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			    Date date = new Date();  
				int a=stmt.executeUpdate("insert into login_details values('"+username+"','"+password+"','"+formatter.format(date)+"')");
				if(a>0)
					JOptionPane.showMessageDialog(this, "Inserted successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
					
				JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
            	new xyz();
			}
			else
				JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            // Close the connection
            con.close();

            System.out.println("Connection closed successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

            
        
        // Clear login fields
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new abc().setVisible(true);
            }
        });
    }
}

class Player {
    private String username;
    private String password;
    private String email;
    private String dob;
    private String phoneNumber;

    public Player(String username, String password, String email, String dob, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDOB() {
        return dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
