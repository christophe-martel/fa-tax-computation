/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.impl.business.builder.product.SimpleBuilder;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.business.entity.tax.SimpleTax;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import java.util.Arrays;
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
        return this
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
            .collect(Collectors.toCollection(LinkedHashSet::new))
        ;
    }
    
}
