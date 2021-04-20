package com.myclass.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.myclass.bus.TaiKhoanBUS;
import com.myclass.dto.TaiKhoanDTO;

import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Application extends JFrame {

	private JPanel contentPane;
	private CardLayout cardLayout;
	private DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private TaiKhoanBUS taiKhoanBUS;
	public static Application appInstance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setLocationRelativeTo(null);
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
	public Application() {
		taiKhoanBUS = new TaiKhoanBUS();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sidePane = new JPanel();
		sidePane.setBackground(Color.GRAY);
		sidePane.setBounds(0, 0, 250, 600);
		contentPane.add(sidePane);
		sidePane.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard", SwingConstants.CENTER);
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblDashboard.setBounds(0, 50, 250, 100);
		sidePane.add(lblDashboard);
		
		JPanel cardsPane = new JPanel(new CardLayout());
		cardsPane.setBackground(Color.WHITE);
		cardsPane.setBounds(250, 0, 750, 600);
		contentPane.add(cardsPane);
		
		JPanel cardAdminMgmt = new JPanel();
		cardsPane.add(cardAdminMgmt, "name_4535705721900");
		cardAdminMgmt.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin card");
		lblNewLabel.setBounds(29, 5, 368, 98);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		cardAdminMgmt.add(lblNewLabel);
		
		JPanel cardTourMgmt = new JPanel();
		cardsPane.add(cardTourMgmt, "name_4568411886400");
		cardTourMgmt.setLayout(null);
		
		JLabel lblTourCard = new JLabel("Tour card");
		lblTourCard.setBounds(31, 22, 286, 59);
		lblTourCard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		cardTourMgmt.add(lblTourCard);
		
		cardLayout = (CardLayout) cardsPane.getLayout();
		cardLayout.addLayoutComponent(cardAdminMgmt, "cardAdminMgmt");
		cardAdminMgmt.setBounds(75, 250, 600, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 250, 300, 300);
		cardAdminMgmt.add(scrollPane);
		
		String[] colsName = {"Tài khoản", "Mật khẩu", "Quyền"};
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowHeight(50);
		model = new DefaultTableModel(
				new Object[][] {
				},
				colsName
			);
		table.setModel(model);
		loadData();
		
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setBounds(29, 206, 93, 23);
		cardAdminMgmt.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(125, 206, 142, 23);
		cardAdminMgmt.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<TaiKhoanDTO> listKQ = taiKhoanBUS.getByTenTK(textField.getText());
				model.setRowCount(0); // xoa tat ca row
				for(TaiKhoanDTO dto : listKQ) {
					model.addRow(new Object[] {
							dto.getTenTK(), dto.getMatKhau(), dto.getQuyen()
					});
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(271, 206, 85, 21);
		cardAdminMgmt.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tải lại");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				loadData();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(434, 253, 85, 21);
		cardAdminMgmt.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaiKhoanFrame addFrame = new AddTaiKhoanFrame();
				addFrame.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(435, 284, 85, 21);
		cardAdminMgmt.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Xóa");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				String tenTK = String.valueOf(table.getValueAt(selectedRow, 0));
				
				taiKhoanBUS.deleteByTenTK(tenTK);
				model.removeRow(selectedRow);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(435, 315, 85, 21);
		cardAdminMgmt.add(btnNewButton_3);
		
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(Application.class.getResource("/com/myclass/gui/IMG/exit-1852366-1573369 (1).png")));
		btnExit.setBounds(703, 5, 25, 29);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse=JOptionPane.showConfirmDialog(rootPane, "Do you want to exit ?","Exit",JOptionPane.YES_NO_OPTION);
	        	if(reponse==0)
	        	{
	        		System.exit(0);
	        	}else
	        	{
	        		return;
	        	}	
	        	
			}
			
		});
		cardAdminMgmt.add(btnExit);
		cardLayout.addLayoutComponent(cardTourMgmt, "cardTourMgmt");
		
		JPanel menuSidePane = new JPanel();
		menuSidePane.setBackground(Color.GRAY);
		menuSidePane.setBounds(0, 200, 250, 400);
		sidePane.add(menuSidePane);
		menuSidePane.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblAdminMgmt = new JLabel("Quản lý quyền hệ thống");
		lblAdminMgmt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardsPane, "cardAdminMgmt");
			}
		});
		lblAdminMgmt.setForeground(Color.WHITE);
		lblAdminMgmt.setFont(new Font("Consolas", Font.BOLD, 16));
		menuSidePane.add(lblAdminMgmt);
		
		JLabel lblTourMgmt = new JLabel("Quản lý tour");
		lblTourMgmt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardsPane, "cardTourMgmt");
			}
		});
		lblTourMgmt.setForeground(Color.WHITE);
		lblTourMgmt.setFont(new Font("Consolas", Font.BOLD, 16));
		menuSidePane.add(lblTourMgmt);
		
		JLabel lblPersonnelMgmt = new JLabel("Qu\u1EA3n l\u00FD h\u01B0\u1EDBng d\u1EABn vi\u00EAn");
		lblPersonnelMgmt.setForeground(Color.WHITE);
		lblPersonnelMgmt.setFont(new Font("Consolas", Font.BOLD, 16));
		menuSidePane.add(lblPersonnelMgmt);
		
		JLabel lblFinanceMgmt = new JLabel("Qu\u1EA3n l\u00FD t\u00E0i ch\u00EDnh");
		lblFinanceMgmt.setForeground(Color.WHITE);
		lblFinanceMgmt.setFont(new Font("Consolas", Font.BOLD, 16));
		menuSidePane.add(lblFinanceMgmt);
		
		JLabel lblVehicleMgmt = new JLabel("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		lblVehicleMgmt.setForeground(Color.WHITE);
		lblVehicleMgmt.setFont(new Font("Consolas", Font.BOLD, 16));
		menuSidePane.add(lblVehicleMgmt);
	}
	
	public void loadData() {
		for(TaiKhoanDTO dto : TaiKhoanBUS.listTaiKhoanDTO) {
			model.addRow(new Object[] {
					dto.getTenTK(), dto.getMatKhau(), dto.getQuyen()
			});
		}
	}

    public static Application getAppInstance() {
        if(appInstance == null) 
            appInstance = new Application();
        return appInstance;
    }
    
}