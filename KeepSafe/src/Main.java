import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.MouseInfo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ScrollPaneConstants;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfPesquisa;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel addLabel;

	int xMouse;
	int yMouse;

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 781, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 82, 760, 327);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setIgnoreRepaint(true);
		table.setFocusable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(169, 169, 169));
		tableHeader.setForeground(Color.white);
		tableHeader.setReorderingAllowed(false);
		tableHeader.setPreferredSize(new Dimension(0, 30));
		tableHeader.setEnabled(false);

		table.setFillsViewportHeight(true);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setSelectionForeground(Color.white);
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));

		table.setDefaultEditor(Object.class, null); // Deixa a table sem editar
		table.setBorder(null);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Plataforma", "Email", "Senha" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(233);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(233);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(233);

		scrollPane.setViewportView(table);

		updateTable();
		
		
		
		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pesquisa();
			}

			private void pesquisa() {
				String sql = "select ID,Plataforma,Email,Senha from passdata where Plataforma like ? ";

				try {
					con = connection.Conexao.faz_conexao();

					stmt = con.prepareStatement(sql);

					stmt.setString(1, tfPesquisa.getText() + "%");

					rs = stmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				format();
			}

		});
		tfPesquisa.setBounds(10, 41, 237, 30);
		contentPane.add(tfPesquisa);
		tfPesquisa.setColumns(10);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/assets/iconfinder_search_126577.png")));
		lblNewLabel.setBounds(257, 41, 50, 30);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();

			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				setLocation(x - xMouse, y - yMouse);

			}
		});
		panel.setBounds(0, 0, 781, 30);
		panel.setBackground(new Color(255, 51, 51));
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel_2 = new JLabel("\u00D7");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(750, 0, 30, 25);
		panel.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("_");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(720, -5, 35, 25);
		panel.add(lblNewLabel_3);

		addLabel = new JLabel("+");
		addLabel.setToolTipText("");
		addLabel.setForeground(new Color(255, 51, 51));
		addLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addModal modal = new addModal();
				modal.setModal(true);
				modal.setVisible(true);
				updateTable();
				format();
			}

		});
		addLabel.setFont(new Font("Calibri Light", Font.PLAIN, 35));
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addLabel.setBounds(735, 38, 46, 41);
		contentPane.add(addLabel);

		lblNewLabel_4 = new JLabel("Keep Safe");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(8, 8, 60, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\u00A9");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(70, 3, 18, 15);
		panel.add(lblNewLabel_5);

		JLabel minusLabel = new JLabel("_");
		minusLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int row = table.getSelectedRow();

				if (!table.isRowSelected(row)) {
					JOptionPane.showMessageDialog(null, "Selecione uma célula para remover.", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					String cell = table.getModel().getValueAt(row, 0).toString();
					String cell2 = table.getModel().getValueAt(row, 1).toString();

					String sql = "delete from passdata where ID=" + cell;
					

					int confirma = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja remover os dados de " + cell2 + " ?", "Atenção",
							JOptionPane.YES_NO_OPTION);

					if (confirma == JOptionPane.YES_OPTION) {
						try {
							con = connection.Conexao.faz_conexao();

							stmt = con.prepareStatement(sql);
							stmt.execute();

							JOptionPane.showMessageDialog(null, "Deletado com sucesso !");
							updateTable();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
						

						format();
					}
				}
			}

		});

		minusLabel.setToolTipText("");
		minusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minusLabel.setForeground(new Color(255, 51, 51));
		minusLabel.setFont(new Font("Calibri Light", Font.PLAIN, 40));
		minusLabel.setBounds(700, 22, 46, 41);
		contentPane.add(minusLabel);
	}


	protected void format() {
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(233);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(233);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(233);
	}

	protected void updateTable() {
		String sql = "select * from passdata";
		try {
			con = connection.Conexao.faz_conexao();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				rs.close();
				stmt.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		format();
	}
	

}
