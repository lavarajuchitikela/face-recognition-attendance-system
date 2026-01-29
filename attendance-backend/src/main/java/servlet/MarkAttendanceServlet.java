package servlet;

import facerecognition.FaceEngine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

import dao.AttendanceDAO;

@WebServlet("/mark")
@MultipartConfig
public class MarkAttendanceServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Part imagePart = req.getPart("image");
        InputStream imageStream = imagePart.getInputStream();

        String result = null;
		try {
			result = FaceEngine.recognize(imageStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(result == null) {
            resp.getWriter().println("Face Not Recognized");
            return;
        }

        try {
            new AttendanceDAO().markAttendance(result);
            resp.getWriter().println("Attendance Marked for: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("DB Error");
        }
    }
}
