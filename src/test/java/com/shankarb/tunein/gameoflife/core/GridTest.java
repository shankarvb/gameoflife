/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import org.junit.Assert;
import org.junit.Test;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class GridTest {
    
    @Test
    public void testGetCellStatus() {
        byte[] cellStatus = new byte[]{1, 1, 0, 0, 1, 0};
        Grid grid = new Grid(2,3,cellStatus);
        Assert.assertEquals(CellStatus.DEAD, grid.getCellStatus(0, 2, true));
        Assert.assertEquals(CellStatus.LIVE, grid.getCellStatus(1, 1, true));
    }
    
    @Test
    public void testGetLiveNeighborCount() {
        byte[] cellStatus = new byte[]{1, 1, 0, 0, 1, 0, 1, 0, 0};
        Grid grid = new Grid(3,3,cellStatus);
        Assert.assertEquals(3, grid.getLiveNeighborCount(1, 1, true));
        Assert.assertEquals(2, grid.getLiveNeighborCount(0, 0, true));
        Assert.assertEquals(1, grid.getLiveNeighborCount(3, 0, true));
    }
    
    @Test
    public void testSetCellStatus() {
        byte[] cellStatus = new byte[]{1};
        Grid grid = new Grid(1,1,cellStatus);
        grid.setCellStatus(0, 0, CellStatus.LIVE, CellStatus.LIVE, true);
        Assert.assertEquals(17, grid.getCellsStatus()[0]);
        
        grid.setCellStatus(0, 0, CellStatus.DEAD, CellStatus.LIVE, false);
        Assert.assertEquals(16, grid.getCellsStatus()[0]);
        
        grid.setCellStatus(0, 0, CellStatus.DEAD, CellStatus.DEAD, true);
        Assert.assertEquals(0, grid.getCellsStatus()[0]);
        
        grid.setCellStatus(0, 0, CellStatus.LIVE, CellStatus.DEAD, false);
        Assert.assertEquals(1, grid.getCellsStatus()[0]);
    }
}
