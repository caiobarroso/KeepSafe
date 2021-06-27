import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addModal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfPassword;
	private JTextField tfEmail;
	private JTextField tfPlatform;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addModal dialog = new addModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addModal() {
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBounds(380, 200, 205, 240);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Plataforma");
			lblNewLabel.setForeground(Color.DARK_GRAY);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 11, 77, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("E-mail");
			lblNewLabel_1.setForeground(Color.DARK_GRAY);
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(10, 67, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Senha");
			lblNewLabel_2.setForeground(Color.DARK_GRAY);
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel_2.setBounds(10, 123, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPassword.setBackground(Color.WHITE);
		tfPassword.setBounds(10, 148, 185, 20);
		contentPanel.add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setForeground(new Color(50, 205, 50));
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(10, 175, 185, 14);
		contentPanel.add(lblAviso);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		tfEmail.setBounds(10, 92, 185, 20);
		contentPanel.add(tfEmail);
		tfEmail.setColumns(10);
		
		
		tfPlatform = new JTextField();
		tfPlatform.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPlatform.setBounds(10, 36, 185, 20);
		contentPanel.add(tfPlatform);
		tfPlatform.setColumns(10);
		
		lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.setForeground(new Color(255, 51, 51));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(174, 0, 31, 27);
		contentPanel.add(lblNewLabel_3);
		
		btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (tfPlatform.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
					
					lblAviso.setText("Preencha todos os campos");
					lblAviso.setForeground(Color.red);
					
				} else {
					
				
				try {
					
					
					Connection con = connection.Conexao.faz_conexao();
					
					String sql = "insert into passdata(Plataforma,Email,Senha) value (?,?,?)";  //Insere dados no banco de dados (insert)
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfPlatform.getText());
					stmt.setString(2, tfEmail.getText());
					stmt.setString(3, tfPassword.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					lblAviso.setForeground(Color.gray);
					lblAviso.setText("Informações cadastradas !");
					
					tfPlatform.setText(null);
					tfPassword.setText(null);
					tfEmail.setText(null);
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(10, 195, 185, 34);
		btnNewButton.setBackground(new Color(255, 51, 51));
		contentPanel.add(btnNewButton);
		

	}
}
