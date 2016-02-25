/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class TaxesTest extends TestCase {
    
    public TaxesTest(String testName) {
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
    public void testAll () {
        
        Taxes taxes = new Taxes(new SimpleCsvReader("/data/taxes.csv"));
        
        
        assertEquals(5, taxes.all().size());
        
    }
    
    
    public void testByCode () {
        Taxes taxes = new Taxes(new SimpleCsvReader("/data/taxes.csv"));
        
        this.getTestData()
            .stream()
            .map(str -> {
                String[] parts = str.split(":", 2);
                return new AbstractMap.SimpleEntry<String, Float>(parts[0], Float.parseFloat(parts[1]));
            })
            .collect(Collectors.toMap(
                map -> map.getKey(),
                map -> map.getValue()))
            .entrySet()
            .forEach(e -> {
                Tax t = taxes.byCode(e.getKey());
                assertEquals(e.getValue(), t.rate());
            });
        ;
        
    }
    
    
    protected List<String> getTestData () {
        return Arrays.asList("regular:10",
                "food:0",
                "book:0",
                "drug:0",
                "import:5");
    }
}
