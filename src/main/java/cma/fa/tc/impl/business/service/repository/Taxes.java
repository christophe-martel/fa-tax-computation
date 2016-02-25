/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.impl.business.entity.tax.SimpleTax;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
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
        return this
            .reader
            .read()
            .stream()
            .map(line -> {
                return new SimpleTax(
                    line.get(0),
                    Float.parseFloat(line.get(1)));
            })
            .collect(Collectors.toSet())
        ;
        
    }
    
}
