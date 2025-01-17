package com.myclass.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.myclass.bus.TaiKhoanBUS;
import com.myclass.dao.TaiKhoanDAO;
import com.myclass.dto.TaiKhoanDTO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTaiKhoanFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenTK;
	private JTextField txtMatKhau;
	private JTextField txtXacNhanMatKhau;
	private TaiKhoanBUS taiKhoanBUS;
	private Application application;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTaiKhoanFrame frame = new AddTaiKhoanFrame();
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
	public AddTaiKhoanFrame() {
		taiKhoanBUS = new TaiKhoanBUS();
		application = new Application();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Th\u00EAm m\u1EDBi t\u00E0i kho\u1EA3n", JLabel.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(0, -1, 591, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblTenTK = new JLabel("Nh\u1EADp t\u00EAn t\u00E0i kho\u1EA3n");
		lblTenTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenTK.setBounds(21, 70, 149, 30);
		contentPane.add(lblTenTK);
		
		txtTenTK = new JTextField();
		txtTenTK.setBounds(179, 70, 252, 32);
		contentPane.add(txtTenTK);
		txtTenTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatKhau.setBounds(21, 117, 149, 30);
		contentPane.add(lblMatKhau);
		
		JLabel lblXacNhanMatKhau = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u");
		lblXacNhanMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXacNhanMatKhau.setBounds(20, 157, 149, 30);
		contentPane.add(lblXacNhanMatKhau);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(179, 115, 252, 32);
		contentPane.add(txtMatKhau);
		
		txtXacNhanMatKhau = new JTextField();
		txtXacNhanMatKhau.setColumns(10);
		txtXacNhanMatKhau.setBounds(181, 157, 252, 32);
		contentPane.add(txtXacNhanMatKhau);
		
		JLabel lblQuyen = new JLabel("Ch\u1ECDn quy\u1EC1n");
		lblQuyen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuyen.setBounds(21, 200, 149, 30);
		contentPane.add(lblQuyen);
		
		String[] roles = {"Admin", "Guest"};
		JComboBox comboBox = new JComboBox(roles);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(185, 202, 101, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("X\u00E1c nh\u1EADn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedItem());
				String tenTK = txtTenTK.getText();
				String matKhau = txtMatKhau.getText();
				String xacNhanMatKhau = txtXacNhanMatKhau.getText();
				String tenQuyen = String.valueOf(comboBox.getSelectedItem());
				int quyen = -1 ;
				if(!matKhau.equals(xacNhanMatKhau)) {
					System.out.println("Nhập lại mật khẩu không đúng!");
					dispose();
				}
				if(tenQuyen.equals("Admin")) {
					quyen = 1;
				}
				else if(tenQuyen.equals("Guest")) {
					quyen = 0;
				}
				TaiKhoanDTO dto = new TaiKhoanDTO(tenTK, matKhau, quyen);
				TaiKhoanBUS.listTaiKhoanDTO.add(dto);
				TaiKhoanDAO dao=new TaiKhoanDAO();
				dao.add(dto);
				Application.getAppInstance().loadTblTaiKhoan();
			}});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(120, 269, 106, 28);
		contentPane.add(btnNewButton);
		
		JButton btnQuayLi = new JButton("Quay l\u1EA1i");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuayLi.setBounds(243, 269, 106, 28);
		contentPane.add(btnQuayLi);
	}
}
