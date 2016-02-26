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
package cma.fa.tc.impl.business.entity.tax;

import cma.fa.tc.impl.business.entity.invoice.FinalTax;
import cma.fa.tc.def.business.entity.Tax;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class FinalTaxTest extends TestCase {
    
    public FinalTaxTest(String testName) {
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
                return new FinalTax(new SimpleTax(
                    parts.get(0),
                    Float.parseFloat(parts.get(1))));
            })
            .collect(Collectors.toSet())
        ;
        
        assertEquals(5, taxes.size());
        
    }
}
