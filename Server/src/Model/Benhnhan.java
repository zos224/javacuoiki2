package Model;

import java.sql.Date;

public class Benhnhan {
	private int id;
	private String hotendem, tenbn, diachibn, bacsikham, ketluan, ppdieutri;
	private Date ngaykham, ngaynhapvien, ngayravien;
	public Benhnhan() {}
	public Benhnhan(String hotendem, String tenbn, String diachibn, Date ngaykham, String bacsikham, String ketluan, String ppdieutri, Date ngaynhapvien, Date ngayraviene)
	{
		this.hotendem = hotendem;
		this.tenbn = tenbn;
		this.diachibn = diachibn;
		this.ngaykham = ngaykham;
		this.bacsikham = bacsikham;
		this.ketluan = ketluan;
		this.ppdieutri = ppdieutri;
		this.ngaynhapvien = ngaynhapvien;
		this.ngayravien = ngayravien;
	}
	public Benhnhan(int id, String hotendem, String tenbn, String diachibn, Date ngaykham, String bacsikham, String ketluan, String ppdieutri, Date ngaynhapvien, Date ngayraviene)
	{
		this.id = id;
		this.hotendem = hotendem;
		this.tenbn = tenbn;
		this.diachibn = diachibn;
		this.ngaykham = ngaykham;
		this.bacsikham = bacsikham;
		this.ketluan = ketluan;
		this.ppdieutri = ppdieutri;
		this.ngaynhapvien = ngaynhapvien;
		this.ngayravien = ngayravien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotendem() {
		return hotendem;
	}
	public void setHotendem(String hotendem) {
		this.hotendem = hotendem;
	}
	public String getTenbn() {
		return tenbn;
	}
	public void setTenbn(String tenbn) {
		this.tenbn = tenbn;
	}
	public String getDiachibn() {
		return diachibn;
	}
	public void setDiachibn(String diachibn) {
		this.diachibn = diachibn;
	}
	public String getBacsikham() {
		return bacsikham;
	}
	public void setBacsikham(String bacsikham) {
		this.bacsikham = bacsikham;
	}
	public String getKetluan() {
		return ketluan;
	}
	public void setKetluan(String ketluan) {
		this.ketluan = ketluan;
	}
	public String getPpdieutri() {
		return ppdieutri;
	}
	public void setPpdieutri(String ppdieutri) {
		this.ppdieutri = ppdieutri;
	}
	public Date getNgaykham() {
		return ngaykham;
	}
	public void setNgaykham(Date ngaykham) {
		this.ngaykham = ngaykham;
	}
	public Date getNgaynhapvien() {
		return ngaynhapvien;
	}
	public void setNgaynhapvien(Date ngaynhapvien) {
		this.ngaynhapvien = ngaynhapvien;
	}
	public Date getNgayravien() {
		return ngayravien;
	}
	public void setNgayravien(Date ngayravien) {
		this.ngayravien = ngayravien;
	}
	
}
