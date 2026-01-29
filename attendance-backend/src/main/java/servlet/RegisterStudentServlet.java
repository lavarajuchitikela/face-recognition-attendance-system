package servlet;

import dao.StudentDAO;
import facerecognition.FaceEngine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/register")
@MultipartConfig
public class RegisterStudentServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Register Servlet Running");
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String name = req.getParameter("name");
        String roll = req.getParameter("roll");

        Part imagePart = req.getPart("image");   // <-- correct
        InputStream imageStream = imagePart.getInputStream();

        byte[] faceData = FaceEngine.extractFaceEncoding(imageStream);

        try {
            new StudentDAO().saveStudent(name, roll, faceData);
            resp.getWriter().print("Student Registered Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("DB Error");
        }
    }
}
