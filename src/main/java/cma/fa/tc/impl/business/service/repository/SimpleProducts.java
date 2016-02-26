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
import cma.fa.tc.impl.business.builder.product.SimpleBuilder;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.business.entity.tax.SimpleTax;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class SimpleProducts extends Base<SimpleProduct> {
    
    private SimpleBuilder builder;
    
    private SimpleCsvReader reader;
    
    public SimpleProducts(
            SimpleCsvReader reader,
            SimpleBuilder builder) {
        this.reader = reader;
        this.builder = builder;
    }
    
    
    
    @Override
    protected Class getClazz () {
        return SimpleProduct.class;
    }
    
    @Override
    protected Set<SimpleProduct> doAll() {
        return Collections.unmodifiableSet((LinkedHashSet) this
            .reader
            .read()
            .stream()
            .map(line -> {
                if (line.size() != 4) {
                    return null;
                }
                return this
                    .builder
                    .start()
                    .code(line.get(0))
                    .label(line.get(1))
                    .unitPrice(Float.valueOf(line.get(2)))
                    .taxes(Arrays.asList(line.get(3).split(";")))
                    .build()
                ;
            })
            .filter(sp -> null != sp)
            .collect(Collectors.toCollection(LinkedHashSet::new)))
        ;
    }
    
}
