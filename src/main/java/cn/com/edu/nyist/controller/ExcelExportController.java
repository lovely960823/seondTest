package cn.com.edu.nyist.controller;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.edu.nyist.biz.StudentBiz;
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.biz.TeacherBiz;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.TClass;
import cn.com.edu.nyist.model.Teacher;

@Controller
public class ExcelExportController {

	@Autowired
	private TeacherBiz teacherBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private TClassBiz tClassBiz;
	
	@RequestMapping("exportTeacher")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String file ="教师信息.xls";
		response.setContentType(request.getServletContext().getMimeType(file));
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + file + "\"; filename*=utf-8' ' " + URLEncoder.encode(file, "utf-8"));
		//声明一个工作簿
		HSSFWorkbook hwb = new HSSFWorkbook();
		//声明一个单子并命名
		HSSFSheet sheet = hwb.createSheet("教师信息");
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((short)15);
		//生成一个样式
		HSSFCellStyle style = hwb.createCellStyle();
		//创建第一行（也可以成为表头）
		HSSFRow row = sheet.createRow(0);
		//样式字体居中
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)1);
        cell.setCellValue("员工号");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)2);
        cell.setCellValue("入职时间");
        cell.setCellStyle(style);
        List<Teacher> ls= teacherBiz.findAll();
		for(short i=0;i<ls.size();i++){
			row =sheet.createRow(i+1);
			row.createCell(0).setCellValue(ls.get(i).getName());
			row.createCell(1).setCellValue(ls.get(i).getNum());
			row.createCell(2).setCellValue(ls.get(i).getPubdate());
		}
		hwb.write(response.getOutputStream());
		hwb.close();
	}
	
	@RequestMapping("exportStudent")
	public void exportS(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String file ="学生信息.xls";
		response.setContentType(request.getServletContext().getMimeType(file));
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + file + "\"; filename*=utf-8' ' " + URLEncoder.encode(file, "utf-8"));
		//声明一个工作簿
		HSSFWorkbook hwb = new HSSFWorkbook();
		//声明一个单子并命名
		HSSFSheet sheet = hwb.createSheet("学生信息");
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((short)15);
		//生成一个样式
		HSSFCellStyle style = hwb.createCellStyle();
		//创建第一行（也可以成为表头）
		HSSFRow row = sheet.createRow(0);
		//样式字体居中
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)1);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)2);
        cell.setCellValue("入职学时间");
        cell.setCellStyle(style);
        List<Student> ls= studentBiz.findAll();
		for(short i=0;i<ls.size();i++){
			row =sheet.createRow(i+1);
			row.createCell(0).setCellValue(ls.get(i).getName());
			row.createCell(1).setCellValue(ls.get(i).getNum());
			row.createCell(2).setCellValue(ls.get(i).getPubdate());
		}
		hwb.write(response.getOutputStream());
		hwb.close();
	}
	@RequestMapping("exportClass")
	public void exportC(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String file ="课程信息.xls";
		response.setContentType(request.getServletContext().getMimeType(file));
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + file + "\"; filename*=utf-8' ' " + URLEncoder.encode(file, "utf-8"));
		
		//声明一个工作簿
		HSSFWorkbook hwb = new HSSFWorkbook();
		//声明一个单子并命名
		HSSFSheet sheet = hwb.createSheet("课程信息");
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((short)15);
		//生成一个样式
		HSSFCellStyle style = hwb.createCellStyle();
		//创建第一行（也可以成为表头）
		HSSFRow row = sheet.createRow(0);
		//样式字体居中
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//过时了
		style.setAlignment(HorizontalAlignment.CENTER);
		//给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("名称");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)1);
        cell.setCellValue("代号");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)2);
        cell.setCellValue("上课教师");
        cell.setCellStyle(style);
        
        cell = row.createCell((short)3);
        cell.setCellValue("可选总数");
        cell.setCellStyle(style);
        
        List<TClass> ls= tClassBiz.findAll();
		for(short i=0;i<ls.size();i++){
			row =sheet.createRow(i+1);
			row.createCell(0).setCellValue(ls.get(i).getName());
			row.createCell(1).setCellValue(ls.get(i).getNum());
			row.createCell(2).setCellValue(ls.get(i).getTname());
			row.createCell(3).setCellValue(ls.get(i).getCount());
		}
		hwb.write(response.getOutputStream());
		hwb.close();
	}
	
}
