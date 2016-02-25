/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.entity.tax;

import cma.fa.tc.def.business.entity.Tax;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class SimpleTaxTest extends TestCase {
    
    public SimpleTaxTest(String testName) {
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
     * 
     */
    public void testSet () {
        Set<Tax> taxes = Arrays
            .asList(
                "regular:10",
                "food:0",
                "book:0",
                "drug:0",
                "import:5")
            .stream()
            .map(str -> {
                List<String> parts = Arrays.asList(str.split(":", 2));
                return new SimpleTax(
                    parts.get(0),
                    Float.parseFloat(parts.get(1)));
            })
            .collect(Collectors.toSet())
        ;
        
        assertEquals(5, taxes.size());
        
    }
    
    
    
    
    
}
