/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.impl.business.builder.product.PricedBuilder;
import cma.fa.tc.impl.business.builder.product.SimpleBuilder;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.Set;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class SimpleProductsTest extends TestCase {
    
    public SimpleProductsTest(String testName) {
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
     * Test of getClazz method, of class SimpleProducts.
     */
    public void testAll () {
        
        SimpleProducts products = new SimpleProducts(
            new SimpleCsvReader("/data/products.csv"),
            new SimpleBuilder(new Taxes(new SimpleCsvReader("/data/taxes.csv"))));
        
        
        
        assertEquals(9, products.all().get().size());
        
    }
    
}
