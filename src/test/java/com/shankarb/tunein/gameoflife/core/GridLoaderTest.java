/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class GridLoaderTest {

    @Test
    public void testBasics() throws GolException {
        try (StringReader reader = new StringReader("2 3 0 0 1 1 1 0")) {
            Grid grid = GridLoader.load(reader);
            Assert.assertEquals(2, grid.getTotalRows());
            Assert.assertEquals(3, grid.getTotalColumns());
            byte[] cellsStatusBytes = grid.getCellsStatus();
            Assert.assertEquals(6, cellsStatusBytes.length);
            Assert.assertEquals(1, cellsStatusBytes[2]);
            Assert.assertEquals(0, cellsStatusBytes[5]);
            Assert.assertEquals(CellStatus.LIVE, grid.getCellStatus(0, 2, true));
            Assert.assertEquals(CellStatus.DEAD, grid.getCellStatus(1, 2, true));
        }
    }
    
    @Test(expected = GolException.class)
    public void testIncompleteData() throws GolException {
        try (StringReader reader = new StringReader("2 3 0 0 1 1 1")) {
            GridLoader.load(reader);
        }
    }
    
    @Test(expected = GolException.class)
    public void testWrongData() throws GolException {
        try (StringReader reader = new StringReader("2 3 0 0 1 1 1 2")) {
            GridLoader.load(reader);
        }
    }
}
