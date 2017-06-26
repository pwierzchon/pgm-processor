/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patryk.Wierzchon
 */
public class PgmProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            File file = new File("C:\\Users\\patryk.wierzchon\\Pictures\\earth.pgm");
            PgmFile image = loadImage(file);

            PgmFile imageR = rotateImageRight(image);
            printImage(imageR);

            PgmFile imageL = rotateImageLeft(image);
            printImage(imageL);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PgmProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PgmProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printImage(PgmFile image) throws IOException {
        File result = new File("C:\\Users\\patryk.wierzchon\\Pictures\\" + image.getFileName());
        BufferedWriter bw = new BufferedWriter(new FileWriter(result));
        bw.write(image.getHeader() + "\n");
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                bw.write(image.getPixel(j, i) + "\n");
            }
        }
        bw.close();
    }

    public static PgmFile rotateImageRight(PgmFile image) {
        //rotate the file right
        System.out.println("rotate image right");
        PgmFile imageR = new PgmFile("earth-R.pgm", image.getHeight(), image.getWidth(), image.getMaxVal());
        for (int i = 0; i < imageR.getHeight(); i++) {
            for (int j = 0; j < imageR.getWidth(); j++) {
                imageR.setPixel(imageR.getWidth() - (j + 1), i, image.getPixel(i, j));
            }
        }
        return imageR;
    }

    public static PgmFile rotateImageLeft(PgmFile image) {
        //rotate the file right
        System.out.println("rotate image left");
        PgmFile imageR = new PgmFile("earth-L.pgm", image.getHeight(), image.getWidth(), image.getMaxVal());
        for (int i = 0; i < imageR.getHeight(); i++) {
            for (int j = 0; j < imageR.getWidth(); j++) {
                imageR.setPixel(j, imageR.getHeight() - (i + 1), image.getPixel(i, j));
            }
        }
        return imageR;
    }

    public static PgmFile loadImage(File file) throws FileNotFoundException, IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));
        String st;
        String header = br.readLine();
        System.out.println("header: " + header);
        String[] split = header.split(" ");
        int width = Integer.parseInt(split[1]);
        int height = Integer.parseInt(split[2]);
        PgmFile image = new PgmFile(file.getName(), width, height, Integer.parseInt(split[3]));
        int lineNo = 0;
        int rowInd = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image.setPixel(j, i, br.readLine());
            }
        }
        
        return image;
    }

}
