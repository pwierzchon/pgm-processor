/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import static pl.destino.pgmprocessor.PgmProcessor.loadImage;
import static pl.destino.pgmprocessor.PgmProcessor.printImage;
import static pl.destino.pgmprocessor.PgmProcessor.rotateImageLeft;
import static pl.destino.pgmprocessor.PgmProcessor.rotateImageRight;
import pl.destino.pgmprocessor.ui.Window;

/**
 *
 * @author Patryk.Wierzchon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Window();
            }
        });
    }

}
