/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patryk.Wierzchon
 */
public class PgmFile {

    private String fileName;

    private int width;
    private int height;
    private int maxVal;

    //private List<PgmRow> rows;

    private String[][] rows;
    
    public PgmFile(String fileName, int width, int height, int maxVal) {
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.maxVal = maxVal;
        rows = new String[width][height];
    }
/*
    public void setRow(PgmRow row) {
        this.rows.add(row);
    }

    public PgmRow getRow(int index) {
        return this.rows.get(index);
    }
*/
    public String getHeader(){
        return "P2 "+String.valueOf(width)+" "+String.valueOf(height)+" "+String.valueOf(maxVal);
    }
    
    public String getPixel(int x, int y){
        return rows[x][y];
    }
    
    public void setPixel(int x, int y, String val){
        rows[x][y]=val;
    }
        
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }
    
    

}
