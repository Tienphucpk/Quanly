package giaodien;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.w3c.dom.css.Counter;

import DBConnection.DBConnection;
import DBConnection.DBConnection.DiachiDAO;
import DBConnection.DBConnection.DoanCS;

public class QLDV_View extends JFrame {
	// Tạo phương thức ramdom cho Mã Đơn Vị
		private String generateRandomMadv() {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuilder randomMadv = new StringBuilder();
	        
	        Random random = new Random();
	        for (int i = 0; i < 5; i++) {
	            int index = random.nextInt(characters.length());
	            randomMadv.append(characters.charAt(index));
	        }
	        return randomMadv.toString();
	    }
	
	private JTextField tfcvd;
	private JTextField tfmadv;
	private JTextField tfdantoc;
	private JTextField tfhovaten;
	private JTextField tfemail;
	private JTextField tfdate;
	private JTextField tfphone;
	Statement stmt;
	public ButtonGroup btg;
	String gt;
	
	public JPanel getPanel() {
        return (JPanel) this.getContentPane();
    }
	public QLDV_View() {
		this.qldv();
	}
	private void qldv() {
		this.setTitle("Đoàn Viên");
		this.setSize(1500,900);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
//		String[] unitnames = {
//				" ","Chi Đoàn Công ty Cổ Phần Cơ Khí","Chi Đoàn Công ty Cổ Phần Nhựa","Chi Đoàn Công ty Cổ Phần Bột Giặt",
//				"Chi Đoàn Công ty Cổ Phần Gạo","Chi Đoàn Công ty Cổ Phần Bao Bì","Chi Đoàn Công ty Cổ Phần Bê Tông",
//				"Chi Đoàn Công ty Cổ Phần Sơn","Đoàn Trường Đại Học CNTT và TT Việt Hàn","Đoàn Trường Đại Học Y Khoa",
//				"Đoàn Trường Đại Học Tôn Đức Thắng","Đoàn Trường Đại Học Sư Phạm Kỹ Thuật","Đoàn Trường Đại Học Bách Khoa",
//				"Đoàn Trường Đại Học Duy Tân","Đoàn Trường THPT Phan Châu Tring","Đoàn Trường THPT Trần Phú","Đoàn Trường THPT Ngũ Hành Sơn",
//				"Đoàn Trường THPT Lê Hồng Phong","Đoàn Trường THPT Trần Đại Nghĩa","Sở Y tế","Sở Công thương","Sở Kế hoạch và Đầu tư",
//				"Sở Tư pháp","Sở Nội vụ","Sở Ngoại vụ","Sở Tài nguyên và Môi trường","Sở Giáo dục và Đào tạo","Sở Tài chính"
//		};
		
		
		URL url_hhd = QLDV_View.class.getResource("HHD.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		
		// Tạo Panel chứa thông tin Đoàn Viên
		JPanel pn_thongtin = new JPanel();
		pn_thongtin.setLayout(new GridLayout(9, 2, 10, 10));
		
		Border border_ttdv = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_ttdv = new TitledBorder(border_ttdv, "Thông Tin Đoàn Viên");
		pn_thongtin.setBorder(titledBorder_ttdv);
		
		JLabel lbdoancs = new JLabel("Đoàn cơ sở");
		// Lấy danh sách Đoàn cơ sở từ cơ sở dữ liệu
        List<String> donviList = DoanCS.getDonviList();

        // Tạo JComboBox và thiết lập mô hình
        JComboBox<String> doancs = new JComboBox<>(donviList.toArray(new String[0]));
		JLabel lbcvd = new JLabel("Chức vụ Đoàn");
		tfcvd = new JTextField();
		JLabel lbmadv = new JLabel("Mã Đoàn Viên");
		tfmadv = new JTextField(50);
		JLabel lbhovaten = new JLabel("Họ và Tên");
		tfhovaten = new JTextField(50);
		JLabel lbdate = new JLabel("Ngày sinh");
		tfdate = new JTextField(50);
		JLabel lbdantoc = new JLabel("Dân tộc");
		tfdantoc = new JTextField(50);
		JLabel lbemail = new JLabel("Email");
		tfemail = new JTextField(50);
		JLabel lbphone = new JLabel("Điện thoại");
		tfphone = new JTextField(50);
			
		
		//thêm thông tin Đoàn viên
		pn_thongtin.add(lbdoancs);
		pn_thongtin.add(doancs);
		pn_thongtin.add(lbcvd);
		pn_thongtin.add(tfcvd);
		pn_thongtin.add(lbmadv);
		pn_thongtin.add(tfmadv);
		pn_thongtin.add(lbhovaten);
		pn_thongtin.add(tfhovaten);
		pn_thongtin.add(lbdate);
		pn_thongtin.add(tfdate);
		pn_thongtin.add(lbdantoc);
		pn_thongtin.add(tfdantoc);
		pn_thongtin.add(lbemail);
		pn_thongtin.add(tfemail);
		pn_thongtin.add(lbphone);
		pn_thongtin.add(tfphone);
		
		
		// Tạo Phần Địa chỉ
				JPanel pn_diachi = new JPanel();
				pn_diachi.setLayout(new GridLayout(4, 2, 10, 10));

				// Tạo viền và tiêu đề
				Border border_diachi = BorderFactory.createLineBorder(Color.BLACK);
				TitledBorder titledBorder_diachi = new TitledBorder(border_diachi, "Địa Chỉ");
				pn_diachi.setBorder(titledBorder_diachi);
				
				// Lấy danh sách tỉnh/thành phố từ cơ sở dữ liệu
		        List<String> tinhList = DiachiDAO.getTinhList();

		        // Tạo JComboBox và thiết lập mô hình
		        JComboBox<String> diachi = new JComboBox<>(tinhList.toArray(new String[0]));

				JLabel lb_tinh = new JLabel("Tỉnh/ Thành Phố");
				JLabel lb_huyen = new JLabel("Quận/Huyện");
				JTextField huyen = new JTextField(30);
				JLabel lb_xa = new JLabel("Xã/ Phường/ Thị Trấn");
				JTextField xa = new JTextField(30);

				pn_diachi.add(lb_tinh);
				pn_diachi.add(diachi);
				pn_diachi.add(lb_huyen);
				pn_diachi.add(huyen);
				pn_diachi.add(lb_xa);
				pn_diachi.add(xa);

				// Tạo phần giới tính
				JPanel pn_gioitinh = new JPanel();
				pn_gioitinh.setLayout(new FlowLayout(10, 50, 0));

				// Tạo viền và tiêu đề cho Giới tính
				Border border_gioitinh = BorderFactory.createLineBorder(Color.BLACK);
				TitledBorder titledBorder_gioitinh = new TitledBorder(border_gioitinh, "Giới Tính");
				pn_gioitinh.setBorder(titledBorder_gioitinh);

				// Tạo hai RadioButton
				JRadioButton nam = new JRadioButton("Nam");
				JRadioButton nu = new JRadioButton("Nữ");
				
				// Nhóm các RadioButton vào btg
				btg = new ButtonGroup();
				btg.add(nam);
				btg.add(nu);
				pn_gioitinh.add(nam);
				pn_gioitinh.add(nu);
				
				// Tạo chức năng lựa chọn giới tính cho JRadiobutton 
				nam.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gt = "Nam";
					}
				});
				
				nu.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gt = "Nữ";
					}
				});
				
				
				
				// Tạo panel chứa chức năng
				JPanel pn_chucnang = new JPanel();
				pn_chucnang.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
				Border border_luachon = BorderFactory.createLineBorder(Color.BLACK);
				TitledBorder titledBorder_chucnang = new TitledBorder(border_luachon, "");
				pn_chucnang.setBorder(titledBorder_chucnang);
				
				JButton bt_timkiem = new JButton("Tìm kiếm");
				JButton bt_them = new JButton("Thêm");
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
				

				// Tạo panel để chứa phần địa chỉ và giới tính và chức ngăng
				JPanel pn4 = new JPanel();
				pn4.setLayout(new BorderLayout());
				pn4.add(pn_diachi, BorderLayout.CENTER);
				pn4.add(pn_gioitinh, BorderLayout.NORTH);
				pn4.add(pn_chucnang, BorderLayout.SOUTH);


				// Tạo Panel chứa bảng
				JPanel pn_table = new JPanel();
				pn_table.setLayout(new BorderLayout());
				Border border_table = BorderFactory.createLineBorder(Color.BLACK);
				TitledBorder titledBorder_table = new TitledBorder(border_table, "Danh Sách Đoàn Viên");
				pn_table.setBorder(titledBorder_table);
				
				
				// Tạo bảng
				DefaultTableModel dm = new DefaultTableModel();
				dm.addColumn("Mã Đoàn Viên");
				dm.addColumn("Họ và Tên");
				dm.addColumn("Giới Tính");
				dm.addColumn("Ngày Sinh");
				dm.addColumn("Địa Chỉ");
				dm.addColumn("Dân Tộc");
				dm.addColumn("Email");
				dm.addColumn("Điện Thoại");
				dm.addColumn("Chức Vụ Đoàn");
				
				//Kết nối CSDL
				DBConnection con = new DBConnection();
				ResultSet rs = con.Print();
				
				try {
				while (rs.next()){
					dm.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)});
				}
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				JTable table_tt = new JTable(dm);
				
				JScrollPane jScrollPane_table = new JScrollPane(table_tt);
				pn_table.add(jScrollPane_table, BorderLayout.CENTER);
				
				tfhovaten.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        // Khi nhập tên đơn vị, tạo mã đơn vị ngẫu nhiên và đặt giá trị vào tfmadonvi
				        String randomMadv = generateRandomMadv();
				        tfmadv.setText(randomMadv);
				    }
				});
				
				// Tạo chức năng tìm kiếm
				bt_timkiem.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						//Hiển thị cửa sổ tìm kiếm khi nhấn nút tìm kiếm
						timkiemQLDV timkiem = new timkiemQLDV();
					}
				});
				
				// Tạo chức năng thêm
				bt_them.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dm.addRow(new String[] {tfmadv.getText(),tfhovaten.getText(),gt,tfdate.getText(),(String) diachi.getSelectedItem(),tfdantoc.getText(),tfemail.getText(),tfphone.getText(),tfcvd.getText()});
						String sql = "INSERT INTO quanly_doanvien.qldv VALUES (?,?,?,?,?,?,?,?,?)";
		                try {
							Connection con = new DBConnection().getCon();
							PreparedStatement stm = con.prepareStatement(sql);
							
								stm.setString(1, tfmadv.getText());
		                    	stm.setString(2, tfhovaten.getText());
		                    	stm.setString(3, gt);
		    			      	stm.setString(4, tfdate.getText()); 
		    			      	stm.setString(5, (String) diachi.getSelectedItem());
		    			      	stm.setString(6, tfdantoc.getText());
		    			      	stm.setString(7, tfemail.getText());
		    			      	stm.setString(8, tfphone.getText());
		    			      	stm.setString(9, tfcvd.getText());
		    					stm.execute();
		                    	JOptionPane.showMessageDialog(null, "Đã thêm Đoàn Viên");
		                   

						} catch (SQLException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Lỗi khi thêm Đoàn Viên. Vui lòng điền đầy đủ thông tin");
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
				            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Đoàn Viên để xóa.");
				        }
				    }
				    private void xoa(String id) {
			    	    // Thực hiện câu lệnh SQL để xóa Đoàn Viên có mã là maDV từ cơ sở dữ liệu
			    	    String sql = "DELETE FROM quanly_doanvien.qldv WHERE id = ?";
			    	    try {
			    	        Connection con = new DBConnection().getCon();
			    	        PreparedStatement stm = con.prepareStatement(sql);
			    	        stm.setString(1, id);
			    	        stm.executeUpdate();
			    	        JOptionPane.showMessageDialog(null, "Đã xóa Đoàn Viên có mã " + id);
			    	    } catch (SQLException ex) {
			    	        ex.printStackTrace();
			    	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa Đoàn Viên.");
			    	    }
			    	}
				});
				
				// Tạo chức năng sửa
				bt_sua.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        int selectedRow = table_tt.getSelectedRow();
				        if (selectedRow != -1) {
				            // Lấy ID của Đoàn Viên đã chọn
				            String id = (String) table_tt.getValueAt(selectedRow, 0);

				            // Cập nhật dòng trong bảng
				            table_tt.setValueAt(tfhovaten.getText(), selectedRow, 1);

				            // Lấy giới tính từ RadioButton
				            String gioiTinh = "";
				            if (nam.isSelected()) {
				                gioiTinh = "Nam";
				            } else if (nu.isSelected()) {
				                gioiTinh = "Nữ";
				            }
				            table_tt.setValueAt(gioiTinh, selectedRow, 2);

				            table_tt.setValueAt(tfdate.getText(), selectedRow, 3);
				            table_tt.setValueAt(diachi.getSelectedItem(), selectedRow, 4);
				            table_tt.setValueAt(tfdantoc.getText(), selectedRow, 5);
				            table_tt.setValueAt(tfemail.getText(), selectedRow, 6);
				            table_tt.setValueAt(tfphone.getText(), selectedRow, 7);
				            table_tt.setValueAt(tfcvd.getText(), selectedRow, 8);

				            // Cập nhật bản ghi trong cơ sở dữ liệu
				            updateDatabase(id, gioiTinh);
				        } else {
				            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Đoàn Viên để cập nhật.");
				        }
				    }

				    private void updateDatabase(String id, String gioiTinh) {
				        // Thực hiện logic để cập nhật Đoàn Viên trong cơ sở dữ liệu
				        String sql = "UPDATE quanly_doanvien.qldv SET hovaten=?, gioitinh=?, ngaysinh=?, diachi=?, dantoc=?, email=?, dienthoai=?, chucvudoan=? WHERE id=?";
				        try {
				            Connection con = new DBConnection().getCon();
				            PreparedStatement stm = con.prepareStatement(sql);
				            stm.setString(1, tfhovaten.getText());
				            
				            // Xử lý trường hợp gioiTinh có thể là null
				            if (gioiTinh == null) {
				                stm.setNull(2, java.sql.Types.VARCHAR);
				            } else {
				                stm.setString(2, gioiTinh);
				            }

				            stm.setString(3, tfdate.getText());
				            stm.setString(4, (String) diachi.getSelectedItem());
				            stm.setString(5, tfdantoc.getText());
				            stm.setString(6, tfemail.getText());
				            stm.setString(7, tfphone.getText());
				            stm.setString(8, tfcvd.getText());
				            stm.setString(9, id);
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
				            tfmadv.setText((String) table_tt.getValueAt(selectedRow, 0));
				            tfhovaten.setText((String) table_tt.getValueAt(selectedRow, 1));
				            // Lấy giới tính từ bảng và chọn RadioButton tương ứng
				            String gioiTinh = (String) table_tt.getValueAt(selectedRow, 2);
				            if ("Nam".equals(gioiTinh)) {
				                nam.setSelected(true);
				            } else if ("Nữ".equals(gioiTinh)) {
				                nu.setSelected(true);
				            }
				            tfdate.setText((String) table_tt.getValueAt(selectedRow, 3));
				            diachi.setToolTipText((String) table_tt.getValueAt(selectedRow, 4));
				            tfdantoc.setText((String) table_tt.getValueAt(selectedRow, 5));
				            tfemail.setText((String) table_tt.getValueAt(selectedRow, 6));
				            tfphone.setText((String) table_tt.getValueAt(selectedRow, 7));
				            tfcvd.setText((String) table_tt.getValueAt(selectedRow, 8));
				        }
				    }
				});	
				
				
		this.add(pn_thongtin,BorderLayout.WEST);
		this.add(pn4, BorderLayout.CENTER);
		this.add(pn_table,BorderLayout.SOUTH);
		this.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLDV_View frame = new QLDV_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
