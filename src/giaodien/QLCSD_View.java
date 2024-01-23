package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DBConnection.DBConnection;
import DBConnection.DBConnection.DiachiDAO;

public class QLCSD_View extends JFrame {
	// Tạo phương thức ramdom cho Mã Đơn Vị
	private String generateRandomMadonvi() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomMadonvi = new StringBuilder();
        
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            randomMadonvi.append(characters.charAt(index));
        }
        return randomMadonvi.toString();
    }
	
	private JTextField tfmadonvi;
	private JTextField tftendonvi;
	private JComboBox cbbtructhuoc;
	private JTextField tfbithu;
	private JTextField tfphone;
	private JTextField tftongdv;
	
	public QLCSD_View() {
		this.qlcsd();
	}
	private void qlcsd() {
		this.setTitle("Đoàn Viên");
		this.setSize(1500,900);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		String[] under = {
				"Khối Doanh Nghiệp","Khối Đại Học","Khối Trường THPT","Khối Sở trực thuộc"
		};
		
		URL url_hhd = QLCSD_View.class.getResource("HHD.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
				
		// Tạo Panel chứa thông tin Cơ Sở Đoàn
		JPanel pn_thongtin1 = new JPanel();
		pn_thongtin1.setLayout(new GridLayout(7, 2, 10, 10));
				
		Border border_ttdcs = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_ttdcs = new TitledBorder(border_ttdcs, "Thông Tin Đoàn Cơ Sở");
		pn_thongtin1.setBorder(titledBorder_ttdcs);
		
		JLabel lbmadonvi = new JLabel("Mã đơn vị");
		tfmadonvi = new JTextField(50);
		JLabel lbtendonvi = new JLabel("Tên đơn vị");
		tftendonvi = new JTextField(50);
		JLabel lbtructhuoc = new JLabel("Trực thuộc");
		cbbtructhuoc = new JComboBox<>(under);
		JLabel lbdiachi = new JLabel("Địa chỉ");
		// Lấy danh sách tỉnh/thành phố từ cơ sở dữ liệu
        List<String> tinhList = DiachiDAO.getTinhList();

        // Tạo JComboBox và thiết lập mô hình
        JComboBox<String> diachi = new JComboBox<>(tinhList.toArray(new String[0]));
		JLabel lbbithu = new JLabel("Bí thư");
		tfbithu = new JTextField(50);
		JLabel lbphone = new JLabel("Điện thoại");
		tfphone = new JTextField(50);
		JLabel lbtongdv = new JLabel("Tổng Đoàn Viên");
		tftongdv = new JTextField(50);
		
		JLabel lb_anhdoanvien = new JLabel();
		lb_anhdoanvien.setIcon(new ImageIcon(Dangky.class.getResource("anhdoanvien.png")));
		
		//Thêm thông tin Cơ Sở Đoàn
		pn_thongtin1.add(lbmadonvi);
		pn_thongtin1.add(tfmadonvi);
		pn_thongtin1.add(lbtendonvi);
		pn_thongtin1.add(tftendonvi);
		pn_thongtin1.add(lbtructhuoc);
		pn_thongtin1.add(cbbtructhuoc);
		pn_thongtin1.add(lbdiachi);
		pn_thongtin1.add(diachi);
		pn_thongtin1.add(lbbithu);
		pn_thongtin1.add(tfbithu);
		pn_thongtin1.add(lbphone);
		pn_thongtin1.add(tfphone);
		pn_thongtin1.add(lbtongdv);
		pn_thongtin1.add(tftongdv);
		
		// Tạo panel chứa chức năng
		JPanel pn_chucnang = new JPanel();
		pn_chucnang.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		Border border_luachon = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titledBorder_chucnang = new TitledBorder(border_luachon, "");
		pn_chucnang.setBorder(titledBorder_chucnang);
		
		JButton bt_timkiem = new JButton("Tìm kiếm");
		JButton bt_them = new JButton("thêm");
		JButton bt_sua = new JButton("Sửa");
		JButton bt_xoa = new JButton("Xóa");
		pn_chucnang.add(bt_timkiem);
		pn_chucnang.add(bt_them);
		pn_chucnang.add(bt_sua);
		pn_chucnang.add(bt_xoa);
		
		URL iconURL_search = timkiemQLDV.class.getResource("icon_search.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_search);
	    bt_timkiem.setIcon(icon0);
		
		URL iconURL_add = timkiemQLDV.class.getResource("icon_add.png");
        ImageIcon icon1 = new ImageIcon(iconURL_add);
        bt_them.setIcon(icon1);
        
        URL iconURL_fix = timkiemQLDV.class.getResource("icon_fix.png");
        ImageIcon icon2 = new ImageIcon(iconURL_fix);
        bt_sua.setIcon(icon2);
		
		URL iconURL_exit = timkiemQLDV.class.getResource("icon_exit.png");
        ImageIcon icon3 = new ImageIcon(iconURL_exit);
        bt_xoa.setIcon(icon3);
		
		JPanel pn4 = new JPanel();
		pn4.setLayout(new BorderLayout());
		pn4.add(pn_thongtin1, BorderLayout.CENTER);
		pn4.add(pn_chucnang, BorderLayout.SOUTH);
		
		
		// Tạo Panel chứa Danh sách quản lý Cơ Sở Đoàn
		JPanel pn_table = new JPanel();
		pn_table.setLayout(new BorderLayout());
		Border border_table = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titledBorder_table = new TitledBorder(border_table, "");
		pn_table.setBorder(titledBorder_table);
		
		
		// Tạo bảng
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Mã đơn vị");
		dm.addColumn("Tên đơn vị");
		dm.addColumn("Trực thuộc");
		dm.addColumn("Địa chỉ");
		dm.addColumn("Bí thư ");
		dm.addColumn("Điện thoại");
		dm.addColumn("Tổng Đoàn Viên");
		
		//kết nối CSDL
		DBConnection con = new DBConnection();
		ResultSet rs1 = con.Print1();
		
		try {
		while (rs1.next()){
			dm.addRow(new String[] {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7)});
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JTable table_tt = new JTable(dm);
		
		JScrollPane jScrollPane_table = new JScrollPane(table_tt);
		pn_table.add(jScrollPane_table, BorderLayout.CENTER);
		
		
		tftendonvi.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Khi nhập tên đơn vị, tạo mã đơn vị ngẫu nhiên và đặt giá trị vào tfmadonvi
		        String randomMadonvi = generateRandomMadonvi();
		        tfmadonvi.setText(randomMadonvi);
		    }
		});
		
		// Tạo chức năng tìm kiếm
		bt_timkiem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Hiển thị cửa sổ tìm kiếm khi nhấn nút tìm kiếm
				timkiemQLCSD timkiem = new timkiemQLCSD();
			}
		});
		
		// Tạo chức năng thêm
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tftendonvi.getText().isEmpty() || tfbithu.getText().isEmpty() || tfphone.getText().isEmpty() || tftongdv.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
		        } else {
		            // Thêm vào bảng
		            dm.addRow(new String[] {tfmadonvi.getText(), tftendonvi.getText(), (String) cbbtructhuoc.getSelectedItem(), 
		                                     (String) diachi.getSelectedItem(), tfbithu.getText(), tfphone.getText(), tftongdv.getText()});
				String sql = "INSERT INTO quanly_doanvien.qlcsd VALUES (?,?,?,?,?,?,?)";
                try {
					Connection con = new DBConnection().getCon();
					PreparedStatement stm = con.prepareStatement(sql);
					
						stm.setString(1, tfmadonvi.getText());
                    	stm.setString(2, tftendonvi.getText());
                    	stm.setString(3, (String) cbbtructhuoc.getSelectedItem());
    			      	stm.setString(4, (String) diachi.getSelectedItem()); 
    			      	stm.setString(5, tfbithu.getText());
    			      	stm.setString(6, tfphone.getText());
    			      	stm.setString(7, tftongdv.getText());
    					stm.execute();
                    	JOptionPane.showMessageDialog(null, "Đã thêm Đơn vị");
                   

				} catch (SQLException ex) {
					ex.printStackTrace();
					}
					}
				}
			});
		
		
		// Tạo chức năng xóa
		bt_xoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_tt.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy mã Đoàn Viên từ hàng được chọn
		            String id = (String) table_tt.getValueAt(selectedRow, 0);
		            
		            // Xóa khỏi bảng
		            ((DefaultTableModel) table_tt.getModel()).removeRow(selectedRow);
		            
		            // Gọi phương thức xóa từ cơ sở dữ liệu
		            xoa(id);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Đơn vị để xóa.");
		        }
		    }
		    private void xoa(String id) {
	    	    // Thực hiện câu lệnh SQL để xóa Đoàn Viên có mã là maDV từ cơ sở dữ liệu
	    	    String sql = "DELETE FROM quanly_doanvien.qlcsd WHERE id = ?";
	    	    try {
	    	        Connection con = new DBConnection().getCon();
	    	        PreparedStatement stm = con.prepareStatement(sql);
	    	        stm.setString(1, id);
	    	        stm.executeUpdate();
	    	        JOptionPane.showMessageDialog(null, "Đã xóa Đơn vị có mã " + id);
	    	    } catch (SQLException ex) {
	    	        ex.printStackTrace();
	    	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa Đơn vị");
	    	    }
	    	}
		});
		
		bt_sua.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_tt.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy ID của đơn vị đã chọn
		            String id = (String) table_tt.getValueAt(selectedRow, 0);

		            // Cập nhật dòng trong bảng
		            table_tt.setValueAt(tftendonvi.getText(), selectedRow, 1);
		            table_tt.setValueAt(cbbtructhuoc.getSelectedItem(), selectedRow, 2);
		            table_tt.setValueAt(diachi.getSelectedItem(), selectedRow, 3);
		            table_tt.setValueAt(tfbithu.getText(), selectedRow, 4);
		            table_tt.setValueAt(tfphone.getText(), selectedRow, 5);
		            table_tt.setValueAt(tftongdv.getText(), selectedRow, 6);
		         // Cập nhật bản ghi trong cơ sở dữ liệu
		            updateDatabase(id);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Đoàn Viên để cập nhật.");
		        }
		    }
		    private void updateDatabase(String id) {
		        // Thực hiện logic để cập nhật Đoàn Viên trong cơ sở dữ liệu
		        String sql = "UPDATE quanly_doanvien.qlcsd SET tendonvi=?, tructhuoc=?, diachi=?, bithu=?, dienthoai=?, tongdoanvien=? WHERE id=?";
		        try {
		            Connection con = new DBConnection().getCon();
		            PreparedStatement stm = con.prepareStatement(sql);     
                	stm.setString(1, tftendonvi.getText());
                	stm.setString(2, (String) cbbtructhuoc.getSelectedItem());
			      	stm.setString(3, (String) diachi.getSelectedItem()); 
			      	stm.setString(4, tfbithu.getText());
			      	stm.setString(5, tfphone.getText());
			      	stm.setString(6, tftongdv.getText());
			      	stm.setString(7, id);
					stm.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Đã cập nhật Đoàn Viên có mã " + id);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật Đoàn Viên." + ex.getMessage());
		        }
		    }
		});
		
		table_tt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        int selectedRow = table_tt.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy thông tin từ bảng và hiển thị nó trong JTextFields
		            tfmadonvi.setText((String) table_tt.getValueAt(selectedRow, 0));
		            tftendonvi.setText((String) table_tt.getValueAt(selectedRow, 1));
		            cbbtructhuoc.setToolTipText((String) table_tt.getValueAt(selectedRow, 2));
		            diachi.setToolTipText((String) table_tt.getValueAt(selectedRow, 3));
		            tfbithu.setText((String) table_tt.getValueAt(selectedRow, 4));       
		            tfphone.setText((String) table_tt.getValueAt(selectedRow, 5));
		            tftongdv.setText((String) table_tt.getValueAt(selectedRow, 6));
		        }
		    }
		});	
		
		
		this.add(pn4,BorderLayout.WEST);
		this.add(lb_anhdoanvien,BorderLayout.CENTER);
		this.add(pn_table,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLCSD_View frame = new QLCSD_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
