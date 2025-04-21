package com.orangehrm.utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class OpenCVTest {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void compareImages(String imagePath1, String imagePath2, String outputPath) {
        Mat img1 = Imgcodecs.imread(imagePath1);
        Mat img2 = Imgcodecs.imread(imagePath2);

        if (img1.empty() || img2.empty()) {
            System.out.println("Could not read one or both images.");
            return;
        }

        // Convert to grayscale
        Mat gray1 = new Mat();
        Mat gray2 = new Mat();
        Imgproc.cvtColor(img1, gray1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(img2, gray2, Imgproc.COLOR_BGR2GRAY);

        // Compute absolute difference
        Mat diff = new Mat();
        Core.absdiff(gray1, gray2, diff);

        // Threshold the difference
        Mat thresh = new Mat();
        Imgproc.threshold(diff, thresh, 30, 255, Imgproc.THRESH_BINARY);

        // Dilate the threshold image to make contours more visible
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Imgproc.dilate(thresh, thresh, kernel);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw rectangles around differences on img2
        for (MatOfPoint contour : contours) {
            Rect boundingRect = Imgproc.boundingRect(contour);
            Imgproc.rectangle(img2, boundingRect, new Scalar(0, 0, 255), 2); // red rectangle
        }

        // Save the result image
        Imgcodecs.imwrite(outputPath, img2);
        System.out.println("Comparison complete. Output saved at: " + outputPath);
    }

    public static void main(String[] args) {
        String image1 = "resources/Image1.png";
        String image2 = "resources/Image2.png";
        String output = "resources/diff_output.png";

        compareImages(image1, image2, output);
    }
}

