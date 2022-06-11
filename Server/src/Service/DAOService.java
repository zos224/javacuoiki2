package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import Model.Benhnhan;

public class DAOService {
	private static String DB_URL = "jdbc:sqlserver://localhost;databaseName=QLyHosobenhan";
	private static String USER_NAME = "sa";
	private static String PASSWORD = "phong";
	public static Connection getConnection(String db_url, String username, String password) 
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(db_url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static ArrayList<String> getusername() {
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Account");
			while (rs.next()) 
			{
				arrayList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public static ArrayList<String> getpassword() {
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Account");
			while (rs.next()) 
			{
				arrayList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}

	public static ArrayList<String> getData()
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT MaHoso AS N'Mã hồ sơ', HoTenDem AS N'Họ Tên Đệm Bệnh Nhân', TenBN AS N'Tên Bệnh Nhân', DiaChiBN AS N'Địa Chỉ Bệnh Nhân', "
					+ "NgayKham AS N'Ngày Khám', BacSiKham AS N'Bác Sĩ Khám', KetLuan AS N'Kết Luận', PPDieuTri AS N'Phương Pháp Điều Trị', Ngaynhapvien AS N'Ngày Nhập Viện', "
					+ "Ngayravien AS N'Ngày Ra Viện' FROM HoSoBenhAn");
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			for (int i = 1; i <= col; i++)
			{
				arrayList.add(rsmd.getColumnName(i));
			}
			while (rs.next()) {
				for (int i = 1; i <= col; i++)
				{
					arrayList.add(rs.getString(i));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
	
	public static void Delete_Patient(String id)
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		try {
			Statement stm = conn.createStatement();
			String sql = "Delete from HoSoBenhAn Where MaHoso = " + id;
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Insert_Patient(Benhnhan benhnhan) 
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		String sql = "EXECUTE usp_insert @Hotendem = ?,@TenBN = ?,@Diachi = ?,@Ngaykham = ?,@Bacsi = ?,@Ketluan = ?,@Dieutri = ?,@Ngaynhapvien = ?,@Ngayravien = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, benhnhan.getHotendem());
			ps.setString(2, benhnhan.getTenbn());
			ps.setString(3, benhnhan.getDiachibn());
			ps.setDate(4, benhnhan.getNgaykham());
			ps.setString(5, benhnhan.getBacsikham());
			ps.setString(6, benhnhan.getKetluan());
			ps.setString(7, benhnhan.getPpdieutri());
			ps.setDate(8, benhnhan.getNgaynhapvien());
			ps.setDate(9, benhnhan.getNgayravien());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
