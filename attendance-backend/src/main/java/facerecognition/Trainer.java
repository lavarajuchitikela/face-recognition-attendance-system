package facerecognition;

import dao.StudentDAO;
import model.StudentFace;

import java.nio.IntBuffer;
import java.util.List;

import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_core.*;

public class Trainer {

    public static void trainModel() throws Exception {

        List<StudentFace> faces = new StudentDAO().getAllFaces();

        if (faces.isEmpty()) {
            System.out.println("No faces in DB to train");
            return;
        }

        MatVector images = new MatVector(faces.size());
        Mat labels = new Mat(faces.size(), 1, CV_32SC1);
        IntBuffer labelBuf = labels.createBuffer();

        int counter = 0;
        for (StudentFace f : faces) {
            Mat img = FaceEngine.byteToMat(f.faceData);
            images.put(counter, img);
            labelBuf.put(counter, Integer.parseInt(f.roll));
            counter++;
        }

        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.train(images, labels);
        recognizer.save("model.yml");

        System.out.println("✅ Model Trained Successfully!");
    }
}
