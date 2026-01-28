import React, { useRef } from "react";
import WebcamCapture from "./WebcamCapture";

function MarkAttendance() {
  const webcamRef = useRef(null);

  const captureAndMark = async () => {
    const imageSrc = webcamRef.current.getScreenshot();
    const blob = await fetch(imageSrc).then(res => res.blob());

    const formData = new FormData();
    formData.append("image", blob);

    const res = await fetch("http://localhost:9090/attendance-backend/mark", {
      method: "POST",
      body: formData
    });

    const text = await res.text();
    alert(text);
  };

  return (
    <div>
      <h2>Mark Attendance</h2>

      <WebcamCapture webcamRef={webcamRef} />

      <button onClick={captureAndMark}>Mark Attendance</button>
    </div>
  );
}

export default MarkAttendance;
