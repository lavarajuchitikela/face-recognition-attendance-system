import React, { useRef, useState } from "react";
import WebcamCapture from "./WebcamCapture";

function Register() {
  const webcamRef = useRef(null);
  const [name, setName] = useState("");
  const [roll, setRoll] = useState("");

  const captureAndRegister = async () => {
    const imageSrc = webcamRef.current.getScreenshot();
    const blob = await fetch(imageSrc).then(res => res.blob());

    const formData = new FormData();
    formData.append("name", name);
    formData.append("roll", roll);
    formData.append("image", blob);

    const res = await fetch("http://localhost:9090/attendance-backend/register", {
      method: "POST",
      body: formData
    });

    const text = await res.text();
    alert(text);
  };

  return (
    <div>
      <h2>Register Student</h2>

      <WebcamCapture webcamRef={webcamRef} />

      <input 
        value={name}
        onChange={(e)=>setName(e.target.value)}
        placeholder="Name"
      />

      <input 
        value={roll}
        onChange={(e)=>setRoll(e.target.value)}
        placeholder="Roll"
      />

      <button onClick={captureAndRegister}>Register</button>
    </div>
  );
}

export default Register;
