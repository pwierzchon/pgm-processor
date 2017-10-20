package pl.destino.pgmprocessor.ui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import pl.destino.pgmprocessor.PgmFile;
import pl.destino.pgmprocessor.PgmProcessor;
import static pl.destino.pgmprocessor.PgmProcessor.loadImage;

/**
 *
 * @author Destino
 */
public class Window extends JFrame {

    private JFrame mainFrame;
    private PgmCanvas canvas;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuEdit;
    private JPanel holder;
    private JMenuItem jmiOpen;
    private JMenuItem jmiRotateL;
    private JMenuItem jmiSave;
    private PgmFile image;
    private JMenuItem jmiRotateR;
    private JMenuItem jmiFlipH;
    private JMenuItem jmiFlipV;

    public Window() {
        init();
    }

    private void init() {
        mainFrame = new JFrame("PGM file editor");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMenu();
        holder = new JPanel();
        mainFrame.add(holder);

        canvas = new PgmCanvas();
        holder.add(canvas);
        mainFrame.pack();
        holder.setVisible(true);
        mainFrame.setVisible(true);
    }

    private void buildMenu() {
        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        menuBar.add(menuFile);
        menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic('E');
        menuBar.add(menuEdit);

        jmiOpen = new JMenuItem("Open");
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiOpen.addActionListener((e) -> {
            selectImage();
        });
        menuFile.add(jmiOpen);

        jmiSave = new JMenuItem("Save");
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiSave.addActionListener((e) -> {
            saveImage();
        });
        menuFile.add(jmiSave);

        jmiRotateL = new JMenuItem("Rotate Left");
        jmiRotateL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiRotateL.addActionListener((e) -> {
            image = PgmProcessor.rotateImageLeft(image);
            displayImage(image);
        });
        menuEdit.add(jmiRotateL);

        jmiRotateR = new JMenuItem("Rotate Right");
        jmiRotateR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiRotateR.addActionListener((e) -> {
            image = PgmProcessor.rotateImageRight(image);
            displayImage(image);
        });
        menuEdit.add(jmiRotateR);

        jmiFlipH = new JMenuItem("Flip Horizontal");
        jmiFlipH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiFlipH.addActionListener((e) -> {
            image = PgmProcessor.flipImageHorizontal(image);
            displayImage(image);
        });
        menuEdit.add(jmiFlipH);

        jmiFlipV = new JMenuItem("Flip Vertical");
        jmiFlipV.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiFlipV.addActionListener((e) -> {
            image = PgmProcessor.flipImageVertical(image);
            displayImage(image);
        });
        menuEdit.add(jmiFlipV);

    }

    private void selectImage() {
        try {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PGM Images", "pgm");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: "
                        + chooser.getSelectedFile().getName());

                image = loadImage(chooser.getSelectedFile());

                displayImage(image);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void displayImage(PgmFile image) {
        holder.removeAll();
        canvas = new PgmCanvas(image);
        holder.add(canvas);
        mainFrame.pack();
    }

    private void saveImage() {
        try {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select file to save");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PGM Images", "pgm");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to save this file: "
                        + chooser.getSelectedFile().getName());

                image.setFileName(chooser.getSelectedFile().getAbsolutePath());
                PgmProcessor.printImage(image);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
