/*
 * Copyright (C) 2014 Christophe Martel <mail.christophe.martel@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
