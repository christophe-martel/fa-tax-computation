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
        
        
        assertEquals(5, taxes.all().get().size());
        
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
                Tax t = taxes.byCode(e.getKey()).get();
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
