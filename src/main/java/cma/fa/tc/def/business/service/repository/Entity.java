/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.repository;

import java.util.Set;

/**
 *
 * @author christophe
 * @param <T>
 */
public interface Entity<T> {
    
    public Returned<T> byCode (String code);
    
    public Returned<T> one (String property, String value);
    
    public Returned<Set<T>> by (String property, String value);
    
    public Returned<Set<T>> all ();
    
}
