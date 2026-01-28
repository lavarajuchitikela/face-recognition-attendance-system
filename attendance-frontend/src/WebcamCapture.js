import React from "react";
import Webcam from "react-webcam";

const WebcamCapture = ({ webcamRef }) => {
  return (
    <Webcam
      audio={false}
      ref={webcamRef}
      screenshotFormat="image/jpeg"
      width={320}
    />
  );
};

export default WebcamCapture;
