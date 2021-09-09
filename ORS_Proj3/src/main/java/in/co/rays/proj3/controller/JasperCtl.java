package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.SessionImpl;

import com.mysql.jdbc.Connection;

import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.util.HIBdatasource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Servlet implementation class Jasper
 * 
 *  
 * @author Session Facade
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name= "JasperCtl" ,urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	public JasperCtl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DoGet Jasper Report");
		try {
			
			JasperReport jasperReport = JasperCompileManager.compileReport("D:\\ORS\\ORS_Proj3\\src\\main\\webapp\\Report\\marksheetmerit.jrxml");
			HttpSession session = request.getSession(true);
			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto.getfirstname();
			dto.getlastname();
			Map<String, Object> map = new HashMap();
			map.put("user", dto.getfirstname() + " " + dto.getlastname());
			Connection conn = null;
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj3.bundle.system");
			String Database = rb.getString("DATABASE");
			if("Hibernate".equalsIgnoreCase(Database)){
				conn = (Connection) ((SessionImpl) HIBdatasource.getsession()).connection();
			}
			/*if ("JDBC".equalsIgnoreCase(Database)) {
				conn = JDBCDataSource.getConnection();
			}*/
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return null;
	}

}
