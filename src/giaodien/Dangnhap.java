package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Dangnhap extends JFrame {
	private String username;
    private String password;
	private JTextField tftendn1;
	private JPasswordField tfmatkhau1;
	private Giaodienchinh1 giaodienchinh1;
	public Dangnhap() {
		this.dn();
	}
	private void dn() {
		this.setTitle("Đăng nhập");
		this.setSize(500,550);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		URL url_hhd = Dangky.class.getResource("HHD.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		JPanel pn_ttdn = new JPanel();
		
		JLabel lb_anhdoanvien = new JLabel();
		lb_anhdoanvien.setIcon(new ImageIcon(Dangky.class.getResource("doanvien.png")));
		
		JLabel empty = new JLabel();
		
		
		Font font = new Font("Arial",Font.BOLD,12);
		JLabel lbtendn1 = new JLabel("Tên đăng nhập");
		lbtendn1.setFont(font);
		tftendn1 = new JTextField(20);
		JLabel lbmatkhau1 = new JLabel("Mật khẩu");
		lbmatkhau1.setFont(font);
		tfmatkhau1 = new JPasswordField(20);
		
		JButton bt_dangnhap = new JButton("Đăng nhập");
		bt_dangnhap.setFont(new Font("Arial",Font.BOLD,15));
		bt_dangnhap.setBackground(Color.CYAN);
		bt_dangnhap.setOpaque(true);
		bt_dangnhap.setBorderPainted(false);
		
		// Thêm lắng nghe sự kiện cho nút Đăng nhập
        bt_dangnhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm thực hiện chức năng đăng nhập
                dangNhap();
            }
        });
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new GridLayout(3,2,-80,40));
		
		pn1.add(lbtendn1);
		pn1.add(tftendn1);
		pn1.add(lbmatkhau1);
		pn1.add(tfmatkhau1);
		pn1.add(empty);
		pn1.add(bt_dangnhap);
		
		pn_ttdn.add(lb_anhdoanvien,BorderLayout.SOUTH);
		pn_ttdn.add(pn1,BorderLayout.CENTER);
		
		this.add(pn_ttdn);
		this.setVisible(true);
	}
	
	private void dangNhap() {
        String username = tftendn1.getText();
        char[] passwordChars = tfmatkhau1.getPassword();
        String password = new String(passwordChars);

        // Kiểm tra tính hợp lệ của dữ liệu đăng nhập
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Thực hiện kiểm tra đăng nhập
        if (kiemTraDangNhap(username, password)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            Giaodienchinh gdc = new Giaodienchinh();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean kiemTraDangNhap(String username, String password) {
        // Kết nối đến cơ sở dữ liệu và kiểm tra thông tin đăng nhập
        String url = "jdbc:mysql://localhost:3306/quanly_doanvien";
        String user = "root";
        String passwordd = "Votienphuc123";

        try (Connection connection = DriverManager.getConnection(url, user, passwordd)) {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                // Nếu có ít nhất một bản ghi tồn tại, đăng nhập thành công
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
			new Dangnhap();
			} catch (Exception e) {
				e.printStackTrace();
				}
	}
	
}
