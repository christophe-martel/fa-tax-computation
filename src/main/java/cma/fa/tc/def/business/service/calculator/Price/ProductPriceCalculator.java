/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.calculator.Price;

import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.Product;

/**
 *
 * @author christophe
 */
public interface ProductPriceCalculator extends PriceCalculator<Product> {
    
    @Override
    public Price calculate (Product product);
}
