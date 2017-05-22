/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import lombok.Getter;

public class Grid {

    @Getter
    private final int totalColumns;
    
    @Getter
    private final int totalRows;
    
    @Getter
    private final byte[] cellsStatus;
    
    public Grid(int totalRows, int totalColumns, byte[] cellsStatus) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.cellsStatus = cellsStatus;
    }
    
    public CellStatus getCellStatus(int row, int column, boolean isEvenGen) {
        return parseCurrentValue(cellsStatus[row * totalColumns + column], isEvenGen);
    }
    
    public void setCellStatus(int row, int column, CellStatus newStatus, CellStatus oldStatus, boolean isEvenGen) {
        byte cellValue = getNewCellValue(newStatus, oldStatus, isEvenGen);
        cellsStatus[row * totalColumns + column] = cellValue;
    }
    
    public int getLiveNeighborCount(int row, int column, boolean isEvenGen) {
        return 
                isCellAlive(row-1, column-1, isEvenGen) +
                isCellAlive(row, column-1, isEvenGen) +
                isCellAlive(row+1, column-1, isEvenGen) +
                isCellAlive(row+1, column, isEvenGen) +
                isCellAlive(row+1, column+1, isEvenGen) +
                isCellAlive(row, column+1, isEvenGen) +
                isCellAlive(row-1, column+1, isEvenGen) +
                isCellAlive(row-1, column, isEvenGen);
    }
    
    private int isCellAlive(int row, int column, boolean isEvenGen) {
        if(row >= 0 && column >= 0 && row < totalRows && column < totalColumns) {
            CellStatus status = parseCurrentValue(cellsStatus[row * totalColumns + column], isEvenGen);
            return status == CellStatus.LIVE ? 1 : 0; 
        }
        return 0;
    }
    
    private CellStatus parseCurrentValue(byte currentValue, boolean isEvenGen) {
        if(isEvenGen) {
            return (currentValue & 0x01) > 0 ? CellStatus.LIVE : CellStatus.DEAD;
        } 
        return (currentValue & 0x10) > 0 ? CellStatus.LIVE : CellStatus.DEAD;
    }
    
    private byte getNewCellValue(CellStatus newStatus, CellStatus oldStatus, boolean isEvenGen) {
        if(isEvenGen) {
            return (byte) (newStatus.oddGenValue | oldStatus.value);
        }
        return (byte) (oldStatus.oddGenValue | newStatus.value);
    }
    
    public enum CellStatus {
        DEAD((byte) 0),
        LIVE((byte) 1);
         
        @Getter
        private final byte value;
        private final byte oddGenValue;
        
        private CellStatus(byte value) {
            this.value = value;
            this.oddGenValue = (byte) (value << 4);
        }
        
        public static boolean isValid(byte value) {
            return value == 0 || value == 1;
        }
    }
}
