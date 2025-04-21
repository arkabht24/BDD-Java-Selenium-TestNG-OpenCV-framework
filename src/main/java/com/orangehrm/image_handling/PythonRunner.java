package com.orangehrm.image_handling;

import java.io.*;

public class PythonRunner {
    public void runPythonImageComparison(String pythonScriptPath, String imagePath1, String imagePath2) {
        try {

            ProcessBuilder builder = new ProcessBuilder("python3", pythonScriptPath);
            builder.redirectErrorStream(true);
            Process process = builder.start();


            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(imagePath1);
                writer.newLine();
                writer.write(imagePath2);
                writer.newLine();
                writer.flush();
            }


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[Python Output] " + line);
                }
            }


            int exitCode = process.waitFor();
            System.out.println("[INFO] Python process exited with code: " + exitCode);

            if (exitCode != 0) {
                throw new RuntimeException("Python script execution failed with exit code " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error executing Python script: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        String pythonScriptPath = "src/main/python/image_compare.py";

        String imagePath1 = "resources/Image1.png";
        String imagePath2 = "resources/Image2.png";


        new PythonRunner().runPythonImageComparison(pythonScriptPath, imagePath1, imagePath2);

    }
}
