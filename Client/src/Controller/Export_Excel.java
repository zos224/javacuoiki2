package Controller;

import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.text.View;
import View.Export;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export_Excel{
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static XSSFRow row = null;
	static Cell cell = null;
	static int row_count = 1;
	static int num_info_patient = 10;
	static boolean write_col_name = false, write_data = false;
	public static void Export(String filepath)
	{
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Hồ sơ bệnh án");
			// Tao hang tieu de
			Client.get_col_name();
			Thread.sleep(100);
			// Du lieu
			Date getdate;
			if (Export.get_status_theongay())
			{
				if (Export.get_ngaytf().equals(""))
				{
					Client.loadData_export("SELECT * FROM HoSoBenhAn");
					Thread.sleep(100);
				}
				else {
					getdate = Date.valueOf(Export.get_ngaytf());
					Client.loadData_export("SELECT * FROM HoSoBenhAn WHERE NgayKham = '" + getdate + "'");
					Thread.sleep(100);
				}
			}
			else if (Export.get_status_theomhs())
			{
				if (Export.get_mhstf().equals(""))
				{
					String sql = "SELECT * FROM HoSoBenhAn";
					Client.loadData_export(sql);
					Thread.sleep(100);
				}
				else {
					var chuoimhs = Export.get_mhstf().trim();
                    String[] num = chuoimhs.split("[^0-9\\-]");
                    for (int i = 0; i < num.length; i++)
                    {
                        if (num[i].indexOf('-') >= 0)
                        {
                            String[] nums = num[i].split("[^0-9]");
                            Client.loadData_export("SELECT * FROM HoSoBenhAn WHERE MaHoSo BETWEEN " + nums[0] + " AND " + nums[1]);
                            Thread.sleep(100);
                        }
                        else
                        {
                        	Client.loadData_export("SELECT * FROM HoSoBenhAn WHERE MaHoSo = " + num[i]);
                        	Thread.sleep(100);
                        }
                    }
				}
			}
			for (int i = 0; i < num_info_patient; i++)
            {
                sheet.autoSizeColumn(i);
            }
			FileOutputStream out = new FileOutputStream(filepath + ".xlsx");
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null,"Đã xuất thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
           
	}
	
	public static void create_col_name(ArrayList<String> arrayList) 
	{
		row = sheet.createRow(0);
		for (int i = 0; i < arrayList.size(); i++)
        {
            cell = row.createCell(i,CellType.STRING);
            cell.setCellValue(arrayList.get(i));
        }
	}
	
	public static void set_data_export(Vector vector) 
	{
		for (int i = 0; i < vector.size();)
		{
			row = sheet.createRow(row_count);
			for (int j = 0; j < num_info_patient; j++, i++)
			{
				if (vector.get(i).equals(" null"))
				{
					row.createCell(j).setCellValue("");
				}
				else {
					row.createCell(j).setCellValue(vector.get(i).toString());
				}
			}
			row_count += 1;
		}
        
	}

}
