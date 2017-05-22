/* 
 * Created on : 05-21-2017
 * Author     : Shankar Balakrishnan
 *
 */
package com.shankarb.tunein.gameoflife.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.Assert;

import org.junit.Test;

public class GenerationLoggerCallableTest {
    
    @Test
    public void testLoggingEvenGen() throws Exception {
        try (StringWriter stringWriter = new StringWriter()) {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            byte[] cellStatus = new byte[]{1, 1, 0, 0, 1, 0};
            Grid grid = new Grid(2,3,cellStatus);
            GenerationLoggerCallable loggerCallable = new GenerationLoggerCallable(grid, new Generation(0), printWriter);
            loggerCallable.call();
            String loggedData = stringWriter.toString();
            try (Scanner scanner = new Scanner(loggedData)) {
                Assert.assertEquals("PRINTING GENERATION 0", scanner.nextLine());
                Assert.assertEquals("1 1 0 ", scanner.nextLine());
                Assert.assertEquals("0 1 0 ", scanner.nextLine());
            }
        }
    }
    
    @Test
    public void testLoggingOddGen() throws Exception {
        try (StringWriter stringWriter = new StringWriter()) {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            byte[] cellStatus = new byte[]{16, 16, 1, 1, 16, 1};
            Grid grid = new Grid(2,3,cellStatus);
            GenerationLoggerCallable loggerCallable = new GenerationLoggerCallable(grid, new Generation(1), printWriter);
            loggerCallable.call();
            String loggedData = stringWriter.toString();
            try (Scanner scanner = new Scanner(loggedData)) {
                Assert.assertEquals("PRINTING GENERATION 1", scanner.nextLine());
                Assert.assertEquals("1 1 0 ", scanner.nextLine());
                Assert.assertEquals("0 1 0 ", scanner.nextLine());
            }
        }
    }
}
