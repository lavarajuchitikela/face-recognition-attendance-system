package facerecognition;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_core.*;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;

import java.io.*;

public class FaceEngine {

    private static final String MODEL_FILE = "model.yml";

    // Convert InputStream → Mat
    private static Mat toMat(InputStream imageStream) throws IOException {
        byte[] data = imageStream.readAllBytes();

        Mat buf = new Mat(1, data.length, CV_8U);
        buf.data().put(data);

        Mat mat = imdecode(buf, IMREAD_GRAYSCALE);
        resize(mat, mat, new Size(200, 200));
        return mat;
    }

    // ===== Registration =====
    public static byte[] extractFaceEncoding(InputStream imageStream) throws IOException {
        Mat face = toMat(imageStream);
        BytePointer buffer = new BytePointer();
        imencode(".jpg", face, buffer);

        byte[] bytes = new byte[(int) buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }

    // ===== Recognition =====
    public static String recognize(InputStream imageStream) throws Exception {

        File model = new File(MODEL_FILE);
        if (!model.exists()) return null;

        Mat testFace = toMat(imageStream);

        FaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.read(MODEL_FILE);

        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);

        recognizer.predict(testFace, label, confidence);

        if (confidence.get(0) < 80)
            return "ROLL" + label.get(0);
        else
            return null;
    }
  
    public static Mat byteToMat(byte[] data) {
        Mat mat = imdecode(new Mat(new BytePointer(data)), IMREAD_GRAYSCALE);
        resize(mat, mat, new Size(200,200));
        return mat;
    }


    
}
