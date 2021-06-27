import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JRadioButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField tfPassword;
	private boolean show;
	int xMouse;
	int yMouse;
	
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	private final JRadioButton showPassword = new JRadioButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 480);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				setLocation(x - xMouse, y - yMouse);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
	
		panel.setBackground(new Color(255, 51, 51));
		panel.setBounds(0, 0, 375, 480);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Keep Safe");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(129, 313, 104, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/assets/newIcon.png")));
		lblNewLabel_1.setBounds(108, 71, 150, 246);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_7 = new JLabel("\u00A9");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(230, 310, 20, 14);
		panel.add(lblNewLabel_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(687, 0, 63, 30);
		panel_1.setBackground(new Color(255, 51, 51));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(33, 9, 25, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("_");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(13, 2, 19, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Sign In");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(128, 128, 128));
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblNewLabel_3.setBounds(477, 40, 155, 55);
		contentPane.add(lblNewLabel_3);
		
		tfLogin = new JTextField();
		tfLogin.setToolTipText("");
		tfLogin.setForeground(new Color(0, 0, 0));
		tfLogin.setBounds(429, 171, 269, 43);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setForeground(SystemColor.controlDkShadow);
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(429, 146, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Senha");
		lblNewLabel_4_1.setForeground(SystemColor.controlDkShadow);
		lblNewLabel_4_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(429, 225, 46, 14);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAviso.setBounds(439, 304, 259, 14);
		contentPane.add(lblAviso);
		
		tfPassword = new JPasswordField();
		tfPassword.setEchoChar('*');
		tfPassword.setBounds(429, 250, 269, 43);
		contentPane.add(tfPassword);
		
		JButton btnEntar = new JButton("Entrar");
		btnEntar.setFocusPainted(false);
		btnEntar.setBorderPainted(false);
		btnEntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0 ) {
				try {
				    con = connection.Conexao.faz_conexao();
					
					String sql = "select * from signin where login=? and password=?"; // Consulta dados do banco de dados (select)
					
					stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfLogin.getText());
					stmt.setString(2, new String(tfPassword.getPassword()));
					
				     rs = stmt.executeQuery();
					
					if (rs.next()) {  //se a informacao for encontrada no banco de dados(usuario e senha)
						
						
						Main exibir = new Main();
						
						
						exibir.setVisible(true);
						setVisible(false);
						
						
					} else {
						
							
							lblAviso.setText("usuário/senha inválidos.");
							tfLogin.setText("");
							tfPassword.setText("");
							
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEntar.setForeground(new Color(255, 255, 255));
		btnEntar.setBackground(new Color(255, 51, 51));
		btnEntar.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEntar.setBounds(429, 335, 269, 43);
		contentPane.add(btnEntar);
		
		JLabel lblNewLabel_5 = new JLabel("Ainda n\u00E3o possui uma conta ?");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		lblNewLabel_5.setForeground(new Color(255, 51, 51));
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(429, 389, 269, 14);
		contentPane.add(lblNewLabel_5);
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(showPassword.isSelected()) {
					tfPassword.setEchoChar((char)0);
				} else {
					tfPassword.setEchoChar('*');
				}
				
			}
		});
		showPassword.setBounds(713, 250, 31, 43);
		contentPane.add(showPassword);
		
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
