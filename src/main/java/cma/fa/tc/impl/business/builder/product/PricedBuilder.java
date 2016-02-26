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
package cma.fa.tc.impl.business.builder.product;

import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.def.business.service.calculator.Price.PriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.impl.business.entity.TcPrice;
import cma.fa.tc.impl.business.entity.product.PricedProduct;
import cma.fa.tc.impl.business.service.calculator.price.TcProductPriceCalculator;
import cma.fa.tc.impl.utils.exception.BusinessException;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import cma.fa.tc.impl.utils.math.NearestRound;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class PricedBuilder {
    
    private final Set<ProductPriceCalculator> calculators;
    
    private Product product = null;
    
    public PricedBuilder(Set<ProductPriceCalculator> calculators) {
        this.calculators = calculators;
    }
    
    
    public PricedBuilder(ProductPriceCalculator calculator) {
        this(Arrays.asList(calculator).stream().collect(Collectors.toSet()));
    }
    
    public PricedBuilder product (Product product) {
        this.product = product;
        return this;
    }
    
    public PricedProduct build () {
        return this
            .create()
        ;
        
    }
    
    private PricedProduct create () {
        return new PricedProduct(this.product, this.computePrice(this.product));
    }
    
    private Price computePrice (Product p) {
        ProductPriceCalculator calculator = this
            .calculators
            .stream()
            .filter(c -> c.canApply(p))
            .reduce(null, (carry, current) -> { return null != carry
                    ? carry
                    : current;})
        ;
        
        if (null == calculator) {
            throw new TechnicalException("Cannot found valid price calculators");
        }
        
        return calculator.calculate(p);
    }
    
}
