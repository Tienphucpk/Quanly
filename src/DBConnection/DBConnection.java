package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

	public class DBConnection {
	     Connection con;
	     Statement stmt;
	     
	   public Connection getCon() {
			return con;
		}
	   //Kết nối CSDL
	public DBConnection() {
		   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","Votienphuc123");
				this.stmt = con.createStatement();
				System.out.println("Successfully");
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	   }
			//Kết nối table quanly_doanvien.qldv
	     public ResultSet Print() {
	    	 String sql = "SELECT * FROM quanly_doanvien.qldv;";
	    	 ResultSet rs = null;
	    	 try {
				rs = stmt.executeQuery(sql);
				System.out.println(sql);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	 return rs;
	     }
	     	//Kết nối table quanly_doanvien.qlcsd
	     public ResultSet Print1() {
	    	 String sql = "SELECT * FROM quanly_doanvien.qlcsd;";
	    	 ResultSet rs1 = null;
	    	 try {
				rs1 = stmt.executeQuery(sql);
				System.out.println(sql);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	 return rs1;
	     }
	     	//Kết nối table tinh để sử dụng JCombobox diachi
	     public class DiachiDAO {
	    	    public static List<String> getTinhList() {
	    	        List<String> tinhList = new ArrayList<>();

	    	        try (Connection con = new DBConnection().getCon();
	    	             Statement stm = con.createStatement();
	    	             ResultSet rs = stm.executeQuery("SELECT * FROM quanly_doanvien.tinh;")) {

	    	            while (rs.next()) {
	    	                tinhList.add(rs.getString("tinh"));
	    	            }

	    	        } catch (SQLException e) {
	    	            e.printStackTrace();
	    	        }

	    	        return tinhList;
	    	    }
	    	}
	     	//Kết nối table quanly_doanvien.qlcsd để sử dụng JCombobox doancs
	     public class DoanCS {
	    	 public static List<String> getDonviList(){
	    		 List<String> donviList = new ArrayList<>();
	    		 
	    		 try(Connection con = new DBConnection().getCon();
	    				 Statement stm = con.createStatement();
	    				 ResultSet rs = stm.executeQuery("SELECT tendonvi FROM quanly_doanvien.qlcsd;")) {
	    			 while (rs.next()) {
	    	                donviList.add(rs.getString("tendonvi"));
	    	            }

	    	        } catch (SQLException e) {
	    	            e.printStackTrace();
	    	        }

	    	        return donviList;
	    		 }	
	     }
	     
	     public static void main(String[] args) {
	    	 DBConnection db = new DBConnection();
	 		ResultSet rs = db.Print();
	 		try {
	 			while(rs.next()) {
	 				
	 			}
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 	}
		
	}

