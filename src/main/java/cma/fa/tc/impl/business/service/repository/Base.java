/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.def.business.service.repository.Entity;
import cma.fa.tc.def.business.service.repository.Returned;
import cma.fa.tc.impl.business.entity.tax.SimpleTax;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author christophe
 * @param <T>
 */
public abstract class Base<T> implements Entity<T> {
    
    private Returned<Set<T>> all = null;
     
    private Set<String> accessibleProperties = null;
    
    @Override
    public Returned<T> byCode (String code) {
        return this.one("code", code);
    }
    
    @Override
    public Returned<T> one (String property, String value) {
        
        Set<T> filtered = this
            .by(property, value)
            .or(new LinkedHashSet<>(0))
            .get();
        
        if (filtered.isEmpty()) {
            return null;
        }
        
        T result = filtered
            .stream()
            .reduce(null, (carry, current) -> {
                return null != carry
                    ? carry
                    : current;})
        ;
        
        if (null == result) {
            return new ReturnedValue<>();
        }
        
        return new ReturnedValue<>(result);
    }
    
    @Override
    public Returned<Set<T>> by (String property, String value) {
        if (this.canAccessProperty(property)) {
            throw new TechnicalException(String.format("Cannot access to property %s", property));
        }
        
        Set<T> result = this
            .all()
            .or(new LinkedHashSet<>(0))
            .get()
            .stream()
            .filter(obj -> value.equals(this.getValue(obj, property)))
            .collect(Collectors.toSet())
        ;
        
        return ((null == result)
                || (result.size() < 1))
            ? new ReturnedValue<>()
            : new ReturnedValue<>(result)
        ;
        
    }
    
    protected boolean canAccessProperty (String property) {
        return this.getAllAccessibleProperties().contains(property);
    }
    
    protected Set<String> getAllAccessibleProperties () {
        if (null != this.accessibleProperties) {
            return this.accessibleProperties;
        }
        
        this.accessibleProperties = Arrays.asList(this
            .getClazz()
            .getDeclaredMethods())
            .stream()
            .filter(f -> f.isAccessible())
            .map(f -> f.getName())
            .collect(Collectors.toSet())
        ;
        
        return this.accessibleProperties;
    }
    
    protected abstract Class getClazz ();
    
    protected Object getValue (T obj, String property) {
        try {
            return obj
                .getClass()
                .getMethod(property, new Class[] {})
                .invoke(obj, new Object [] {});
        } catch (NoSuchMethodException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException exception) {
            throw new TechnicalException(exception);
        }
    }
    
    @Override
    public Returned<Set<T>> all() {
        if (null != this.all) {
            return this.all;
        }
        
        try {
            Set<T> data = this.doAll();
            
            this.all = ((null == data)
                    || (data.size() < 1))
                ? new ReturnedValue<>()
                : new ReturnedValue<>(data)
            ;
            
            return this.all;
            
        } catch (Exception exception) {
            this.all = new ReturnedValue<>(exception);
            return this.all;
        }
    }
    
    protected abstract Set<T> doAll ();
    
}
