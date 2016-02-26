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
import cma.fa.tc.impl.business.entity.tax.SimpleTax;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author christophe
 */
public class Taxes extends Base<Tax> {
    
    private SimpleCsvReader reader;
    
    public Taxes(SimpleCsvReader reader) {
        this.reader = reader;
    }
    
    @Override
    protected Class getClazz () {
        return Tax.class;
    }
    
    @Override
    protected Set<Tax> doAll() {
        return Collections.unmodifiableSet((LinkedHashSet) this
            .reader
            .read()
            .stream()
            .map(line -> {
                return new SimpleTax(
                    line.get(0),
                    Float.parseFloat(line.get(1)));
            })
            .collect(Collectors.toCollection(LinkedHashSet::new)))
        ;
        
    }
    
}
