package pl.destino.pgmprocessor.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import pl.destino.pgmprocessor.PgmFile;

/**
 *
 * @author Destino
 */
public class PgmCanvas extends JPanel {

    private PgmFile pgmFile;

    public PgmCanvas() {
        super();
    }

    public PgmCanvas(PgmFile pgmFile) {
        this.pgmFile = pgmFile;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (pgmFile != null) {
            BufferedImage img = new BufferedImage(pgmFile.getWidth(), pgmFile.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int rc = 0; rc < pgmFile.getHeight(); rc++) {
                for (int cc = 0; cc < pgmFile.getWidth(); cc++) {
                    // Set the pixel colour of the image n.b. x = cc, y = rc
                    int color = getColor(Integer.valueOf(pgmFile.getPixel(cc, rc)));
                    img.setRGB(cc, rc, new Color(color, color, color).getRGB());
                }//for cols
            }//for rows
            ((Graphics2D) g).drawImage(img, 0, 0, this);
        } else {
            super.paintComponent(g);
        }

    }

    private int getColor(Integer valueOf) {
        int maxVal = pgmFile.getMaxVal();
        int factor = 255/maxVal;
        return valueOf*factor;
    }

}
