package servlet;

import facerecognition.Trainer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/train")
public class TrainModelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Trainer.trainModel();
            resp.getWriter().print("Model trained successfully");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("Training failed");
        }
    }
}
