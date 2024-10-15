package com.example;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class ImageBlurTask extends RecursiveAction {
    private static final int THRESHOLD = 100;
    private BufferedImage image;
    private int startX, endX, startY, endY;

    public ImageBlurTask(BufferedImage image, int startX, int endX, int startY, int endY) {
        this.image = image;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    protected void compute() {
        if ((endX - startX) * (endY - startY) <= THRESHOLD) {
            blurArea(startX, endX, startY, endY);
        } else {
            int midX = (endX + startX) / 2;
            int midY = (endY + startY) / 2;

            invokeAll(
                    new ImageBlurTask(image, startX, midX, startY, midY),
                    new ImageBlurTask(image, midX, endX, startY, midY),
                    new ImageBlurTask(image, startX, midX, midY, endY),
                    new ImageBlurTask(image, midX, endX, midY, endY));
        }
    }

    private void blurArea(int startX, int endX, int startY, int endY) {
        int totalRed = 0, totalBlue = 0, totalGreen = 0;
        int allPixels = (endX - startX) * (endY - startY);

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int rgb = image.getRGB(x, y);

                totalRed += (rgb >> 16) & 0xFF;
                totalGreen += (rgb >> 8) & 0xFF;
                totalBlue += (rgb) & 0xFF;
            }
        }

        int averageRed = totalRed / allPixels;
        int averageGreen = totalGreen / allPixels;
        int averageBlue = totalBlue / allPixels;

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int blurredImage = (averageRed << 16) | (averageGreen << 8) | (averageBlue);
                image.setRGB(x, y, blurredImage);
            }
        }
    }
}
