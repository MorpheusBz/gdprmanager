package eu.dsflat.dashboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;


public class Login {

	private JFrame frame;
	private final JPanel loginRightPanel = new JPanel();
	private JTextField usernameTextField;
	private JPasswordField pwdTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel dashBoardPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, dashBoardPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, dashBoardPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, dashBoardPanel, 571, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, dashBoardPanel, 1022, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(dashBoardPanel);
		dashBoardPanel.setLayout(null);
		
		JPanel loginLeftPanel = new JPanel();
		loginLeftPanel.setBackground(UIManager.getColor("RadioButton.highlight"));
		loginLeftPanel.setBounds(0, 0, 512, 571);
		dashBoardPanel.add(loginLeftPanel);
		loginLeftPanel.setLayout(null);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(0, 0, 0, 200));
		logoPanel.setBounds(0, 0, 512, 571);
		loginLeftPanel.add(logoPanel);
		logoPanel.setLayout(null);
		
		JLabel lblInfoTechSolution = new JLabel("Info Tech Solution");
		lblInfoTechSolution.setForeground(new Color(255, 228, 181));
		lblInfoTechSolution.setBounds(86, 162, 351, 92);
		logoPanel.add(lblInfoTechSolution);
		lblInfoTechSolution.setFont(new Font("Lato", Font.BOLD, 42));
		
		JLabel lblGdprManager = new JLabel("GDPR - Manager");
		lblGdprManager.setForeground(new Color(255, 228, 181));
		lblGdprManager.setFont(new Font("Lato", Font.BOLD, 42));
		lblGdprManager.setBounds(99, 306, 351, 92);
		logoPanel.add(lblGdprManager);
		
		JLabel loginLeftBackground = new JLabel("");
		loginLeftBackground.setBounds(0, 0, 512, 571);
		loginLeftPanel.add(loginLeftBackground);
		loginLeftBackground.setIcon(new ImageIcon(Login.class.getResource("/lib/img/login_bkg.jpg")));
		loginRightPanel.setForeground(new Color(255, 250, 240));
		loginRightPanel.setBackground(new Color(0, 0, 0));
		loginRightPanel.setBounds(512, 0, 510, 571);
		dashBoardPanel.add(loginRightPanel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(66, 97, 90, 35);
		lblUsername.setFont(new Font("Lato", Font.BOLD, 14));
		lblUsername.setForeground(new Color(255, 228, 181));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(66, 239, 90, 35);
		lblPassword.setForeground(new Color(255, 228, 181));
		lblPassword.setFont(new Font("Lato", Font.BOLD, 14));
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(66, 144, 421, 29);
		usernameTextField.setForeground(new Color(255, 228, 181));
		usernameTextField.setBackground(new Color(0, 0, 0));
		usernameTextField.setColumns(10);
		usernameTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		pwdTextField = new JPasswordField();
		pwdTextField.setBounds(63, 286, 403, 30);
		pwdTextField.setForeground(new Color(255, 228, 181));
		pwdTextField.setBackground(new Color(0, 0, 0));
		pwdTextField.setEchoChar('*');
		pwdTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(66, 176, 413, 4);
		loginRightPanel.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(66, 316, 413, 4);
		loginRightPanel.add(separator_1);
		separator_1.setForeground(new Color(255, 255, 255));
		loginRightPanel.add(lblPassword);
		loginRightPanel.add(pwdTextField);
		loginRightPanel.add(lblUsername);
		loginRightPanel.add(usernameTextField);
		loginRightPanel.add(separator);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SqlLitleConnect.select();
				Connection connection = null;  
			    ResultSet resultSet = null;  
			    Statement statement = null;
			    String guessUser = usernameTextField.getText().toLowerCase().toString();
			    String guessPass = pwdTextField.getPassword().toString().toLowerCase();
			    
			    try 
			    {  
			        Class.forName("org.sqlite.JDBC");  
			        connection = DriverManager.getConnection("jdbc:sqlite:/home/mihail/eclipse-workspace/GDPR Manager/lib/db/itsmanager.db");  
			        statement = connection.createStatement();  
			        resultSet = statement.executeQuery("SELECT user, password FROM users");  
			        while (resultSet.next()) 
			        {  
			            System.out.println("User NAME: " + resultSet.getString("user") + " Pass: " + resultSet.getString("password"));
			        }  
			    } 
			    catch (Exception e1) 
			    {  
			        e1.printStackTrace();  
			    }
			    finally 
			    {  
			        try 
			        {  
			            resultSet.close();  
			            statement.close();  
			            connection.close();  
			        } 
			        catch (Exception e1) 
			        {  
			            e1.printStackTrace();  
			        }  
			    }
			    
				
			}
		});
		btnLogin.setBounds(66, 416, 413, 41);
		loginRightPanel.add(btnLogin);
	}
}
