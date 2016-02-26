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

import cma.fa.tc.def.business.entity.PricedOrder;
import cma.fa.tc.impl.business.builder.order.OrderBuilder;
import cma.fa.tc.impl.business.entity.order.TcPricedOrder;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
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
public class Orders extends Base<PricedOrder> {
    
    private final OrderBuilder builder;
    
    private final SimpleCsvReader reader;
    
    public Orders(
            SimpleCsvReader reader,
            OrderBuilder builder) {
        this.reader = reader;
        this.builder = builder;
    }
    
    @Override
    protected Class getClazz () {
        return TcPricedOrder.class;
    }
    
    @Override
    protected Set<PricedOrder> doAll() {
        return Collections.unmodifiableSet((LinkedHashSet) this
            .reader
            .read()
            .stream()
            .map(line -> {
                if (line.size() != 2) {
                    return null;
                }
                
                return this
                    .builder
                    .start()
                    .number(line.get(0))
                    .products(Arrays.asList(line.get(1).split(";")).stream()
                        .map(str -> {
                            List<String> parts = Arrays.asList(str.split("\\*", 2));
                            return new AbstractMap.SimpleEntry<>(parts.get(1), Integer.parseInt(parts.get(0), 10));
                        })
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                    .build()
                ;
            })
            .filter(sp -> null != sp)
            .collect(Collectors.toCollection(LinkedHashSet::new)))
        ;
    }
    
}
