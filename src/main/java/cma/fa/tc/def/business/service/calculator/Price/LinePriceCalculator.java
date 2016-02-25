/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.calculator.Price;

import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;

/**
 *
 * @author christophe
 */
public interface LinePriceCalculator extends PriceCalculator<Line> {
    
    @Override
    public Price calculate (Line line);
    
}
