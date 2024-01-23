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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DBConnection.DBConnection;

public class timkiemQLCSD extends JFrame{
	    private JTable table_tt;
	    private JTextField tfSearch;

	    public timkiemQLCSD() {
	        tkcsd();
	    }

	    public void tkcsd() {

	        setTitle("Tìm Kiếm");
	        setSize(800, 600);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout());

	        URL url_hhd = timkiemQLCSD.class.getResource("HHD.png");
	        Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
	        setIconImage(img);

	        // Tạo Panel tìm kiếm
	        JPanel pn_timkiem = new JPanel();

	        JLabel lb_tendv = new JLabel("Tên Đơn Vị ");
	        tfSearch = new JTextField(30);
	        tfSearch.setFont(new Font("Arial", Font.BOLD, 15));

	        // Tạo icon tìm kiếm
	        JButton bt_timkiem = new JButton("");
	        URL iconURL = timkiemQLDV.class.getResource("icon_search.png");
	        ImageIcon icon = new ImageIcon(iconURL);
	        bt_timkiem.setIcon(icon);

	        pn_timkiem.add(lb_tendv);
	        pn_timkiem.add(tfSearch);
	        pn_timkiem.add(bt_timkiem);

	        // Tạo Panel chứa bảng
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
			
			table_tt = new JTable(dm);
			
			JScrollPane jScrollPane_table = new JScrollPane(table_tt);
			pn_table.add(jScrollPane_table, BorderLayout.CENTER);

	        add(pn_timkiem, BorderLayout.NORTH);
	        add(pn_table, BorderLayout.CENTER);

	        // Thêm ActionListener cho nút tìm kiếm
	        bt_timkiem.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Lấy giá trị từ tfSearch
	                String searchText = tfSearch.getText();

	                // Thực hiện tìm kiếm trong bảng
	                timKiemTrongBang(searchText);
	            }
	        });

	        setVisible(true);
	    }

	    private void timKiemTrongBang(String searchText) {
	        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table_tt.getModel());
	        table_tt.setRowSorter(rowSorter);

	        // Thiết lập bộ lọc cho tfSearch (Tên Đoàn Viên)
	        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                // Ví dụ sử dụng
	                new timkiemQLCSD();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
	}
