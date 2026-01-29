package dao;

import db.DBConnection;
import java.sql.*;
import java.time.*;

public class AttendanceDAO {
    public void markAttendance(String rollNo) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "INSERT INTO attendance(roll_no,date,time,status) VALUES (?,?,?,?)"
        );
        ps.setString(1, rollNo);
        ps.setDate(2, Date.valueOf(LocalDate.now()));
        ps.setTime(3, Time.valueOf(LocalTime.now()));
        ps.setString(4, "PRESENT");
        ps.executeUpdate();
        con.close();
    }
}
