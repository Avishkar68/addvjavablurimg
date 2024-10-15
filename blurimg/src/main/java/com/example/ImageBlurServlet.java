package com.example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.awt.image.BufferedImage;

@WebServlet("/blur")
@MultipartConfig
public class ImageBlurServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("imageFile");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/uploads") + File.separator + fileName;
        filePart.write(uploadPath);

        File uploadedFile = new File(uploadPath);
        BufferedImage image = ImageIO.read(uploadedFile);

        ForkJoinPool pool = new ForkJoinPool();
        ImageBlurTask blurTask = new ImageBlurTask(image, 0, image.getWidth(), 0, image.getHeight());
        pool.invoke(blurTask);

        String outputFileName = "blurred_" + fileName;
        String outputPath = getServletContext().getRealPath("/uploads") + File.separator + outputFileName;
        File output = new File(outputPath);
        ImageIO.write(image, "jpg", output);

        request.setAttribute("outputFileName", outputFileName);
        request.getRequestDispatcher("blur.jsp").forward(request, response);
    }
}
