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
