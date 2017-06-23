/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.pgmprocessor;

/**
 *
 * @author Patryk.Wierzchon
 */
public class PgmRow {
    private String[] row;

    public PgmRow(int size) {
        row = new String[size];
        
    }
    
    public void setElement(String value, int index){
        row[index] = value;
    }
    
    public String getElement(int index){
        return row[index];
    }
}
