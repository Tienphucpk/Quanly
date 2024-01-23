package giaodien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DBConnection.DBConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class timkiemQLDV extends JFrame {
    private JTable table_tt;
    private JTextField tfSearch;
    private JComboBox cbb_timkiem;

    public timkiemQLDV() {
        tkdv();
    }

    public void tkdv() {

        setTitle("Tìm Kiếm");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        URL url_hhd = timkiemQLDV.class.getResource("HHD.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
        setIconImage(img);
        
        String[] tk = {
        	"Tên Đoàn Viên","Giới tính","Địa chỉ","Mã Đoàn Viên"
        };

        // Tạo Panel tìm kiếm
        JPanel pn_timkiem = new JPanel();

        cbb_timkiem = new JComboBox(tk);
        tfSearch = new JTextField(30);
        tfSearch.setFont(new Font("Arial", Font.BOLD, 15));

        // Tạo icon tìm kiếm
        JButton bt_timkiem = new JButton("");
        URL iconURL = timkiemQLDV.class.getResource("icon_search.png");
        ImageIcon icon = new ImageIcon(iconURL);
        bt_timkiem.setIcon(icon);

        pn_timkiem.add(cbb_timkiem);
        pn_timkiem.add(tfSearch);
        pn_timkiem.add(bt_timkiem);

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

        // Kết nối CSDL
        DBConnection con = new DBConnection();
        ResultSet rs = con.Print();

        try {
            while (rs.next()) {
                dm.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }
        } catch (SQLException e) {
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

        // Lấy tiêu chí tìm kiếm đã chọn từ combo box
        String luachon = (String) cbb_timkiem.getSelectedItem();

        // Xác định chỉ số cột dựa trên tiêu chí tìm kiếm đã chọn
        int columnIndex = -1;
        switch (luachon) {
            case "Tên Đoàn Viên":
                columnIndex = 1; // Giả sử cột "Họ và Tên" có chỉ số 1
                break;
            case "Giới tính":
                columnIndex = 2; // Giả sử cột "Giới Tính" có chỉ số 2
                break;
            case "Địa chỉ":
                columnIndex = 4; // Giả sử cột "Địa Chỉ" có chỉ số 4
                break;
            case "Mã Đoàn Viên":
                columnIndex = 0; // Giả sử cột "Mã Đoàn Viên" có chỉ số 0
                break;
            // Thêm thêm trường hợp nếu cần thiết cho các tiêu chí khác
        }

        // Thiết lập bộ lọc dựa trên tiêu chí tìm kiếm đã chọn và văn bản tìm kiếm
        if (columnIndex != -1) {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Ví dụ sử dụng
                new timkiemQLDV();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
