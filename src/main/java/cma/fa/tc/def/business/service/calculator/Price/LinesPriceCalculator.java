/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.calculator.Price;

import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.PricedLines;

/**
 *
 * @author christophe
 */
public interface LinesPriceCalculator extends PriceCalculator<PricedLines> {
    
    @Override
    public Price calculate (PricedLines lines);
    
}
