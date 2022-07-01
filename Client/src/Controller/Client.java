package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.util.Base64;
import java.util.Vector;

import javax.crypto.Cipher;

import View.LoginGUI;
import View.MainGUI;


public class Client {
	public static LoginGUI loginGUI;
	public static MainGUI mainGUI;
	public static SocketHandle socketHandle;
	public static void SendAccount(String username, String password)
	{
		String account_encrypt = Encryption.encrytp(username + "," + password);
		try {
			socketHandle.getDataOutputStream().writeUTF("login-request," + account_encrypt);
			socketHandle.getDataOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void loadData()
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("load data");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void delete_request(String id)
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("delete-request," + id);
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void insert_patient(Vector vector)
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("insert-request," + vector);
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void update_patient(Vector vector)
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("update-request," + vector);
			socketHandle.getDataOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void get_year()
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("get year");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void loadData_dieutri() 
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("load data dieutri,");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void load_data_dieutri_theonam(String nam) 
	{	
		try {
			socketHandle.getDataOutputStream().writeUTF("load data dieutri theonam," + nam);
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void load_data_month()
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("load data month");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void load_data_year()
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("load data year");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void get_col_name() {
		try {
			socketHandle.getDataOutputStream().writeUTF("load col name");
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void loadData_export(String sql)
	{
		try {
			socketHandle.getDataOutputStream().writeUTF("load data export," + sql);
			socketHandle.getDataOutputStream().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void init()                               
	{
		loginGUI.openGUI();
		socketHandle = new SocketHandle();
		socketHandle.run();
	}
	public static void main(String[] args) {
		new Client().init();
	}
}
