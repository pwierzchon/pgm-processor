/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pl.destino.pgmprocessor.PgmProcessor.loadImage;
import static pl.destino.pgmprocessor.PgmProcessor.printImage;
import static pl.destino.pgmprocessor.PgmProcessor.rotateImageLeft;
import static pl.destino.pgmprocessor.PgmProcessor.rotateImageRight;

/**
 *
 * @author Patryk.Wierzchon
 */
public class Main {

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
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
