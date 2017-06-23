/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            String st;
            String header = br.readLine();
            System.out.println("header: " + header);
            String[] split = header.split(" ");
            int width = Integer.parseInt(split[1]);
            int height = Integer.parseInt(split[2]);
            PgmFile image = new PgmFile("earth", width, height, Integer.parseInt(split[3]));
            int lineNo = 0;
            int rowInd = 0;
            PgmRow row = new PgmRow(width);
            while ((st = br.readLine()) != null) {
                if (lineNo < height) {
                    if (rowInd < width) {
                        image.setPixel(rowInd, lineNo, st);
                        rowInd++;
                    } else {
                        rowInd = 0;
                        System.out.println("line number " + lineNo + " created");
                        lineNo++;
                    }
                }else{
                    System.out.println("file height exceeded");
                }

                //System.out.println(st);
            }
            
            //rotate the file left
            System.out.println("rotate image left");
            PgmFile imageR = new PgmFile("earth-L", image.getHeight(), image.getWidth(), image.getMaxVal());
            for(int i = 0; i < image.getHeight(); i++){
                for(int j = 0; j < image.getWidth(); j++){
                    imageR.setPixel(i, imageR.getHeight()-(i+1),image.getPixel(j, i));
                }
            }
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PgmProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PgmProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
