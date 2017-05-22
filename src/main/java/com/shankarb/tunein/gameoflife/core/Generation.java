/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import lombok.Getter;

public class Generation {
    
    @Getter
    private final int generationNumber;
    
    @Getter
    private final boolean isEvenGen;
    
    public Generation(int generationNumber) {
        this.generationNumber = generationNumber;
        this.isEvenGen = (generationNumber & 1) == 0;
    }
}
