/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.util.Scanner;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class GridLoader {

    public static Grid load(Readable reader) throws GolException {
        try (Scanner scan = new Scanner(reader)) {
            int totalRows = getTotalRows(scan);
            int totalColumns = getTotalColumns(scan); 
            int totalCells = getTotalCells(totalRows, totalColumns);
            LogHelper.logInfo(String.format("Loading Grid, totalRows=%d, totalColumns=%d", totalRows, totalColumns)); 
            byte[] cellsStatus = new byte[totalCells];
            for(int i = 0; i < totalCells; i++) {
                cellsStatus[i] = getNextByte(scan); 
            }
            return new Grid(totalRows, totalColumns, cellsStatus);
        }
    }
    
    private static int getTotalRows(Scanner scan) throws GolException {
        if(!scan.hasNextInt()) {
            throw new GolException("Grid file error, totalRows must be specified");
        }
        int totalRows = scan.nextInt();
        if(totalRows <= 0) {
            throw new GolException("Grid file error, totalRows must be greater than zero");
        }
        return totalRows;
    }
    
    private static int getTotalColumns(Scanner scan) throws GolException {
        if(!scan.hasNextInt()) {
            throw new GolException("Grid file error, totalColumns must be specified");
        }
        int totalColumns = scan.nextInt();
        if(totalColumns <= 0) {
            throw new GolException("Grid file error, totalColumns must be greater than zero");
        }
        return totalColumns;
    }
    
    private static int getTotalCells(int totalRows, int totalColumns) throws GolException {
        try {
            return Math.multiplyExact(totalRows, totalColumns);
        } catch (ArithmeticException e) {
            throw new GolException("Grid file error, grid size not supported", e);
        }
    }
    
    private static byte getNextByte(Scanner scan) throws GolException {
        if(scan.hasNextByte()) {
            byte nextByte = scan.nextByte();
            if(!CellStatus.isValid(nextByte)) {
                throw new GolException("Grid file error, cell status bit must be 1 or 0");
            }
            return nextByte;
        } else {
            throw new GolException("Grid file error, number of status bits does not match expected cell count");
        }
    }
}
