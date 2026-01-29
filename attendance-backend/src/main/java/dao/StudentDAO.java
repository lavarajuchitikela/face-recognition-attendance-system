package dao;

import db.DBConnection;
import model.StudentFace;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    // Save student
    public void saveStudent(String name, String roll, byte[] faceData) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO students(name, roll, face_data) VALUES(?,?,?)"
        );
        ps.setString(1, name);
        ps.setString(2, roll);
        ps.setBytes(3, faceData);
        ps.executeUpdate();
        con.close();
    }

    // Get all faces for training
    public List<StudentFace> getAllFaces() throws Exception {
        List<StudentFace> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT roll, face_data FROM students");

        while(rs.next()) {
            String roll = rs.getString("roll");
            byte[] data = rs.getBytes("face_data");
            list.add(new StudentFace(roll, data));
        }

        con.close();
        return list;
    }
}
