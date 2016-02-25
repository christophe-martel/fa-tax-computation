/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.utils.math;

import cma.fa.tc.def.utils.math.Rounder;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class NearestRoundTest extends TestCase {
    
    public NearestRoundTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of round method, of class NearestRound.
     */
    public void testRoundDouble () {
        Rounder r= new NearestRound(0.05);
        
        assertEquals(1.0, r.round(0.99));
        assertEquals(1.0, r.round(1.00));
        assertEquals(1.05, r.round(1.01));
        assertEquals(1.05, r.round(1.02));
        
        
    }

}
