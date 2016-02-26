
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
