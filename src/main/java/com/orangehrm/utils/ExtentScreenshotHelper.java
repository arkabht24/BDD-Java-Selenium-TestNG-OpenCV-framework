package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentTest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static com.orangehrm.listeners.WebDriverListener.dirForScreenshotsCapture;

public class ExtentScreenshotHelper {

    public void addThreeScreenshotsRow(ExtentTest test, List<String> imageNames) throws IOException {
        if(dirForScreenshotsCapture.get().contains("baselineSS")){
            StringBuilder html = new StringBuilder();
            for (String imageName : imageNames) {
                html.setLength(0);
                html.append("<div style='display: flex; gap: 10px; text-align: center;'>");
                System.out.println("Full Path ---> "+dirForScreenshotsCapture.get()+imageName);
                File file = new File(dirForScreenshotsCapture.get()+imageName);
                String fileName = file.getName();//file.getName().split("/")[file.getName().split("/").length-1].replace(".png","");
                System.out.println( "File name as per Screenshot helper ---> "+fileName);
                String base64 = encodeFileToBase64(file);

                html.append("<div>")
                        .append("<h4>").append(fileName.replace(".png","")).append("</h4>")
                        .append("<img src='data:image/png;base64,")
                        .append(base64)
                        .append("' width='300' height='200' style='border:1px solid #ccc;'/>")
                        .append("</div>")
                        .append("<br>");
                html.append("</div>");
                test.info(html.toString());
            }



        }
        else {
            StringBuilder html = new StringBuilder();
            /*html.append("<div style='display: flex; gap: 10px; text-align: center;'>");*/

            for (String imageName : imageNames) {
                html.append("<h4><center>").append(imageName.replace(".png","")).append("</center></h4>");
                html.setLength(0);
                html.append("<div style='display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; margin-bottom: 30px;'>");

                File file1 = new File(dirForScreenshotsCapture.get()+imageName);
                String fileName1 = file1.getName();
                String base64_1 = encodeFileToBase64(file1);

                File file2 = new File(dirForScreenshotsCapture.get().replace("baselineSS","temporarySS")+imageName);
                String fileName2 = file2.getName();
                String base64_2 = encodeFileToBase64(file2);
                html.append("<div style='text-align: center; flex: 1 1 200px;'>")
                        .append("<img src='data:image/png;base64,")
                        .append(base64_1)
                        .append("' style='width: 100%; max-width: 300px; height: auto; border:1px solid #ccc;'/>")
                        .append("</div>")
                        .append("<br>")


                        /*.append("<h4>").append(fileName2.replace(".png","")).append("</h4>")*/
                        .append("<div style='text-align: center; flex: 1 1 200px;'>")
                        .append("<img src='data:image/png;base64,")
                        .append(base64_2)
                        .append("' style='width: 100%; max-width: 300px; height: auto; border:1px solid #ccc;'/>")
                        .append("</div>")
                        .append("<br>")

                        /*.append("<h4>").append(fileName2.replace(".png","")).append("</h4>")*/
                        .append("<div style='text-align: center; flex: 1 1 200px;'>")
                        .append("<img src='data:image/png;base64,")
                        .append(base64_2)
                        .append("' style='width: 100%; max-width: 300px; height: auto; border:1px solid #ccc;'/>")
                        .append("</div>")
                        .append("<br>");
                html.append("</div>");
                test.info(html.toString());

            }



        }
    }

    private String encodeFileToBase64(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            return Base64.getEncoder().encodeToString(bytes);
        }
    }
}
