/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.service.repository.Returned;
import cma.fa.tc.impl.utils.exception.TechnicalException;

/**
 *
 * @author christophe
 * @param <T>
 */
public class ReturnedValue<T> implements Returned<T> {
    
    private final Exception exception;
    
    private final T returned;
    
    private T orValue = null;
    
    private ReturnedValue (Exception exception, T returned) {
        this.exception = exception;
        this.returned = returned;
    }
    
    public ReturnedValue (Exception exception) {
        this(exception, null);
    }
    
    public ReturnedValue (T returned) {
        this(null, returned);
    }
    
    public ReturnedValue () {
        this(null, null);
    }
    
    @Override
    public T get () {
        if (this.exists()) {
            return this.returned;
        }
        
        if (null != this.orValue) {
            return this.orValue;
        }
        
        throw new TechnicalException("Returned value and default value are not defined");
    }
    
    public ReturnedValue or (T orValue) {
        this.orValue = orValue;
        return this;
    }
    
    @Override
    public boolean failed () {
        return null != this.exception;
    }
    

    @Override
    public boolean success () {
        return true != this.failed();
    }
    
    @Override
    public boolean exists () {
        return this.success()
                && (null != this.returned);
    }

    @Override
    public Returned<T> fail(Returned.Fail onFail) {
        if (true != this.failed()) {
            return this;
        }
        onFail.operation(this.exception);
        return this;
    }

    @Override
    public Returned<T> not(Returned.Not onNotFound) {
        if (this.failed() || this.exists()) {
            return this;
        }
        onNotFound.operation();
        return this;
    }

    @Override
    public Returned<T> found(Returned.Found<T> onFound) {
        if (true != this.success()) {
            return this;
        }
        onFound.operation(this.returned);
        return this;
    }
    
    
    
    
}
