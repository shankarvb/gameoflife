/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shankarb.tunein.gameoflife.core.Grid.CellStatus;

public class TransitionRule {

    @JsonProperty("currentStatus")
    @Getter
    private final CellStatus currentStatus;
    
    @JsonProperty("newStatus")
    @Getter
    private final CellStatus newStatus;
    
    @JsonProperty("liveNeighborCounts")
    @Getter
    private final int[] liveNeighborCounts;
    
    @JsonCreator
    public TransitionRule(
            @JsonProperty("currentStatus") CellStatus currentStatus,
            @JsonProperty("newStatus") CellStatus newStatus,
            @JsonProperty("liveNeighborCounts") int[] liveNeighborCounts) {
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
        this.liveNeighborCounts = liveNeighborCounts;
    }
}
