package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.service.HotelServiceImpl;

import model.Hotel;

/**
 * Servlet implementation class FarmerApplyListGetPic
 */
@WebServlet("/Picture")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Picture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		HotelServiceImpl svc = new HotelServiceImpl();
		Integer id = Integer.valueOf(request.getParameter("id"));
		Hotel bean = svc.findById(id);

		if (bean != null) {
			out.write(bean.getPicture());
		}
	}

}
