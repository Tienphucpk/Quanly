package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Giaodienchinh1 extends JFrame{
	public Giaodienchinh1() {
		this.gdc();
	}
	private void gdc() {
		setTitle("Đoàn Viên");
		this.setSize(1500,900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		URL url_hhd = Giaodienchinh.class.getResource("HHD.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		JLabel lb_anhtc = new JLabel();
		lb_anhtc.setIcon(new ImageIcon(Giaodienchinh.class.getResource("anhtrangchu.png")));
		
		//Tạo menubar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Tạo menu Trang chủ
		JMenu Menutc = new JMenu("Trang chủ");
		menuBar.add(Menutc);
		Menutc.setFont(new Font("Arial", Font.BOLD, 15));
		
		JMenuItem Menuitemdn = new JMenuItem("Đăng nhập");
		Menutc.add(Menuitemdn);
		Menuitemdn.setFont(new Font("Arial", Font.BOLD, 12));
		Menuitemdn.setBackground(Color.YELLOW);
		Menuitemdn.setForeground(Color.BLUE);
		
		// Hiển thị giao diện đăng nhập khi chọn 'Đăng nhập'
		Menuitemdn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                Dangnhap dangnhap = new Dangnhap();
            }
        });
		
		JMenuItem Menuitemdk = new JMenuItem("Đăng ký");
		Menutc.add(Menuitemdk);
		Menuitemdk.setFont(new Font("Arial", Font.BOLD, 12));
		Menuitemdk.setBackground(Color.YELLOW);
		Menuitemdk.setForeground(Color.BLUE);
		
		// Hiển thị giao diện đăng ký khi chọn 'Đăng ký'
		Menuitemdk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dangky dangky = new Dangky();
                
            }
        });
		
		
		//Tạo menu Quản lý đoàn viên
		JMenu Menuqldv = new JMenu("Quản lý Đoàn Viên");
		JMenuItem Menuitemqldv = new JMenuItem("Open");
		Menuqldv.add(Menuitemqldv);
		menuBar.add(Menuqldv);
		Menuqldv.setFont(new Font("Arial", Font.BOLD, 15));
		Menuitemqldv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Giaodienchinh1.this,"Vui lòng đăng nhập trước khi sử dụng chức năng này.", "Yêu cầu đăng nhập", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		
		//Tạo menu Quản lý cơ sở đoàn
		JMenu Menuqlcsd = new JMenu("Quản lý Cơ Sở Đoàn");
		JMenuItem Menuitemqlcsd = new JMenuItem("Open");
		Menuqlcsd.add(Menuitemqlcsd);
		menuBar.add(Menuqlcsd);
		Menuqlcsd.setFont(new Font("Arial", Font.BOLD, 15));
		Menuitemqlcsd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Giaodienchinh1.this,"Vui lòng đăng nhập trước khi sử dụng chức năng này.", "Yêu cầu đăng nhập", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		
		this.add(lb_anhtc);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giaodienchinh1 frame = new Giaodienchinh1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
