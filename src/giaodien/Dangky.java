package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.ldap.LdapName;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Dangky extends JFrame {
	
	private JTextField tftendn; 
	private JPasswordField tfmatkhau;
	private JPasswordField tfxnmatkhau;

	public Dangky() {
		this.dk();
	}
	private void dk() {
		this.setTitle("Đăng ký");
		this.setSize(500,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		URL url_hhd = Dangky.class.getResource("HHD.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		//Tạo Panel Đăng ký
		JPanel pn_ttdk = new JPanel();
		
		JLabel lb_anhdoanvien = new JLabel();
		lb_anhdoanvien.setIcon(new ImageIcon(Dangky.class.getResource("doanvien.png")));
		
		JLabel empty = new JLabel();
		
		//Tạo thông tin đăng ký
		Font font = new Font("Arial",Font.BOLD,12);
		JLabel lbtendn = new JLabel("Tên đăng nhập");
		lbtendn.setFont(font);
		tftendn = new JTextField(20);
		JLabel lbmatkhau = new JLabel("Mật khẩu");
		lbmatkhau.setFont(font);
		tfmatkhau = new JPasswordField(20);
		JLabel lbxnmatkhau = new JLabel("Xác nhận mật khẩu");
		tfxnmatkhau = new JPasswordField(20);
		lbxnmatkhau.setFont(font);
			
		JButton bt_xacnhan = new JButton("Xác nhận");
		bt_xacnhan.setFont(new Font("Arial",Font.BOLD,15));
		bt_xacnhan.setBackground(Color.CYAN);
		bt_xacnhan.setOpaque(true);
		bt_xacnhan.setBorderPainted(false);
		
		//Thêm chức năng xác nhận đăng ký
		bt_xacnhan.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Gọi hàm thực hiện chức năng đăng ký
	            dangky();
	        }
	    });
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new GridLayout(4,2,-50,40));
		
		//Thêm thông tin vào Panel đăng ký
		pn1.add(lbtendn);
		pn1.add(tftendn);
		pn1.add(lbmatkhau);
		pn1.add(tfmatkhau);
		pn1.add(lbxnmatkhau);
		pn1.add(tfxnmatkhau);
		pn1.add(empty);
		pn1.add(bt_xacnhan);
		
		pn_ttdk.add(lb_anhdoanvien,BorderLayout.SOUTH);
		pn_ttdk.add(pn1,BorderLayout.CENTER);
		
		this.add(pn_ttdk);
		this.setVisible(true);
	}
	private void dangky() {
		String username = tftendn.getText();
		char[] passwordChars = tfmatkhau.getPassword();
		String password = new String(passwordChars);
		char[] xnpasswordChars = tfxnmatkhau.getPassword();
		String xnpassword = new String(xnpasswordChars);
		
		// Kiểm tra tính hợp lệ của dữ liệu đăng ký
        if (username.isEmpty() || password.isEmpty() || xnpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (kiemTraTonTai(username)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(xnpassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu và xác nhận mật khẩu không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Thực hiện thêm tài khoản vào cơ sở dữ liệu
        if (themTaiKhoanVaoCSDL(username, password)) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	// Thêm tài khoản vào cơ sở dữ liệu
    public boolean themTaiKhoanVaoCSDL(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/quanly_doanvien";
        String user = "root";
        String passwordd = "Votienphuc123";

        try (Connection connection = DriverManager.getConnection(url, user, passwordd)) {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                int affectedRows = preparedStatement.executeUpdate();

                // Nếu có ít nhất một hàng được ảnh hưởng, đăng ký thành công
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    	// Hàm kiểm tra tên đăng nhập
    private boolean kiemTraTonTai(String tenDangNhap) {
        String url = "jdbc:mysql://localhost:3306/quanly_doanvien";
        String user = "root";
        String password = "Votienphuc123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM account WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tenDangNhap);

                ResultSet resultSet = preparedStatement.executeQuery();

                // Nếu có ít nhất một bản ghi tồn tại, tên đăng nhập đã tồn tại
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Dangky();
			} catch (Exception e) {
				e.printStackTrace();
				}
	}

}
