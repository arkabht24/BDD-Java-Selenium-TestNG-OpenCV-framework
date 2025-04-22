package com.orangehrm.image_handling;

import java.io.*;

import static com.orangehrm.listeners.WebDriverListener.comparedScreenshotDir;
import static com.orangehrm.listeners.WebDriverListener.dirForScreenshotsCapture;

public class PythonRunner {
    public void runPythonImageComparison(String pythonScriptPath, String imageName) {
        String imagePath1 =  dirForScreenshotsCapture.get().replace("temporarySS","baselineSS")+imageName;
        String imagePath2 = dirForScreenshotsCapture.get()+imageName;
        String imagePath3 = comparedScreenshotDir.get()+imageName;
        try {

            ProcessBuilder builder = new ProcessBuilder("python3", pythonScriptPath);
            builder.redirectErrorStream(true);
            Process process = builder.start();


            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(imagePath1);
                writer.newLine();
                writer.write(imagePath2);
                writer.newLine();
                writer.write(imagePath3);
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

}
