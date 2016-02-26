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
package cma.fa.tc.def.business.service.repository;

import cma.fa.tc.impl.utils.exception.TechnicalException;

/**
 *
 * @author christophe
 * @param <T>
 */
public interface Returned<T> {
    
    /**
     * throws an exception if return do not exist and default value is not defined
     * 
     * @return
     * @throws TechnicalException 
     */
    public T get () throws TechnicalException;
    
    public boolean failed ();
    
    public boolean success ();
    
    public boolean exists ();
    
    Returned<T> or (T orValue);
    
    Returned<T> fail(Returned.Fail onFail);
    
    Returned<T> not(Returned.Not onNotFound);
    
    Returned<T> found(Returned.Found<T> onFound);
    
    interface Fail {
        public void operation (Exception exception);
    }
    
    interface Not {
        public void operation ();
    }
    
    interface Found<T> {
        public void operation (T t);
    }
}
