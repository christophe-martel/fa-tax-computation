/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.calculator.Price;

import cma.fa.tc.def.business.entity.Price;

/**
 *
 * @author christophe
 * @param <T>
 */
public interface PriceCalculator<T> {
    
    public Price calculate (T target);
    
    public boolean canApply (Object target);
    
    
}
