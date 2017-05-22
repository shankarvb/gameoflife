/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class TransitionRuleProcessor {
    
    private final CellStatus[] liveStateTransitions = new CellStatus[9];
    private final CellStatus[] deadStateTransitions = new CellStatus[9];
    
    public TransitionRuleProcessor(TransitionRule[] rules) {
        // set defaults
        initializeTransitions(liveStateTransitions, CellStatus.LIVE);
        initializeTransitions(deadStateTransitions, CellStatus.DEAD);
        
        // process defined rules 
        for(TransitionRule rule : rules) {
            switch(rule.getCurrentStatus()) {
            case DEAD:
                initializeTransitions(deadStateTransitions, rule.getLiveNeighborCounts(), rule.getNewStatus());
                break;
            case LIVE:
                initializeTransitions(liveStateTransitions, rule.getLiveNeighborCounts(), rule.getNewStatus());
                break;
            }
        }
    }
    
    public void setNextGenStatus(Grid grid, int row, int column, boolean isEvenGen) {
        CellStatus currentStatus = grid.getCellStatus(row, column, isEvenGen);
        int liveNeighbourCount = grid.getLiveNeighborCount(row, column, isEvenGen);
        switch(currentStatus) {
        case DEAD:
            grid.setCellStatus(row, column, deadStateTransitions[liveNeighbourCount], currentStatus, isEvenGen);
            break;
        case LIVE:
            grid.setCellStatus(row, column, liveStateTransitions[liveNeighbourCount], currentStatus, isEvenGen);
            break;
        }
    }
    
    private void initializeTransitions(CellStatus[] transitions, int[] liveNeighborCounts, CellStatus newStatus) {
        for(int count : liveNeighborCounts) {
            transitions[count] = newStatus;
        }
    }
    
    private void initializeTransitions(CellStatus[] transitions, CellStatus status) {
        for(int count = 0; count < transitions.length; count++) {
            transitions[count] = status;
        }
    }
}
