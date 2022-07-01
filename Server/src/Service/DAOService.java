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

import javax.swing.SpinnerListModel;

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
			rs.close();
			stm.close();
			conn.close();
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
			rs.close();
			stm.close();
			conn.close();
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
			rs.close();
			stm.close();
			conn.close();
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
			stm.close();
			conn.close();
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
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Update_Patient(Benhnhan benhnhan)
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		String sql = "EXECUTE usp_update @MaHS = ?,@Hotendem = ?,@TenBN = ?,@Diachi = ?,@Ngaykham = ?,@Bacsi = ?,@Ketluan = ?,@Dieutri = ?,@Ngaynhapvien = ?, @Ngayravien = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, benhnhan.getId());
			ps.setString(2, benhnhan.getHotendem());
			ps.setString(3, benhnhan.getTenbn());
			ps.setString(4, benhnhan.getDiachibn());
			ps.setDate(5, benhnhan.getNgaykham());
			ps.setString(6, benhnhan.getBacsikham());
			ps.setString(7, benhnhan.getKetluan());
			ps.setString(8, benhnhan.getPpdieutri());
			ps.setDate(9, benhnhan.getNgaynhapvien());
			ps.setDate(10, benhnhan.getNgayravien());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static ArrayList<String> getYear()
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT DISTINCT YEAR(NgayKham) FROM HoSoBenhAn");
			while (rs.next()) {
                arrayList.add(rs.getString(1));   
                }
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
	public static ArrayList<String> getData_dieutri() 
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT MaHoso AS N'Mã hồ sơ', HoTenDem AS N'Họ Tên Đệm Bệnh Nhân', TenBN AS N'Tên Bệnh Nhân', DiaChiBN AS N'Địa Chỉ Bệnh Nhân', " +
"NgayKham AS N'Ngày Khám', BacSiKham AS N'Bác Sĩ Khám', KetLuan AS N'Kết Luận', PPDieuTri AS N'Phương Pháp Điều Trị', Ngaynhapvien AS N'Ngày Nhập Viện', "
+ "Ngayravien AS N'Ngày Ra Viện' FROM HoSoBenhAn WHERE Ngaynhapvien IS NOT NULL AND Ngayravien IS NULL");
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
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
	
	public static ArrayList<String> getData_dieutri_theonam(String nam)
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		String sql = "EXECUTE usp_loctheonam @nam = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, nam);
			ResultSet rs = pstm.executeQuery();
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
			rs.close();
			pstm.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
	
	public static Vector getData_thongke_theothang()
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		Vector vector = new Vector<>();
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM uf_tkthang()");
			while (rs.next())
			{	
				vector.add(rs.getString(1));
				vector.add(rs.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	public static Vector getData_thongke_theonam()
	{
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		Vector vector = new Vector<>();
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM uf_tknam()");
			while (rs.next())
			{	
				vector.add(rs.getString(1));
				vector.add(rs.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	public static ArrayList<String> get_col_name()
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
	
	public static ArrayList<String> get_data_export(String sql)
	{
		int Num_info_patient = 10;
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				for (int i = 1; i <= Num_info_patient; i++)
				{
					arrayList.add(rs.getString(i));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}
}
