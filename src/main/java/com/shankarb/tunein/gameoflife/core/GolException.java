/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

public class GolException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public GolException(String message) {
        super(message);
    }
    
    public GolException(String message, Throwable t) {
        super(message, t);    
    }
}
