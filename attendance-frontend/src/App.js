import React from "react";
import Register from "./Register";
import MarkAttendance from "./MarkAttendance";

function App() {
  return (
    <div>
      <h1 style={{ textAlign: "center" }}>
        Face Recognition Attendance System
      </h1>

      <Register />
      <hr />
      <MarkAttendance />
    </div>
  );
}

export default App;
