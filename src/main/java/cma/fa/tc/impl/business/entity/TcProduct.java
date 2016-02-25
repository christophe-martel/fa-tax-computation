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

package cma.fa.tc.impl.business.entity;


import cma.fa.tc.def.business.entity.Price;
import java.util.Set;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.PriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.impl.utils.exception.BusinessException;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import java.util.List;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@EqualsAndHashCode(of={"code"})
@ToString
@Slf4j
public abstract class TcProduct implements Product {
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final String code;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final String label;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final float unitPrice;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final Set<Tax> taxes;
    
    public TcProduct (
            String code,
            String label,
            float unitPrice,
            Set<Tax> taxes) {
        this.code = code;
        this.label = label;
        this.unitPrice = unitPrice;
        this.taxes = taxes;
    }
    
    public TcProduct (Product product) {
        this(
            product.code(),
            product.label(),
            product.unitPrice(),
            product.taxes());
        
    }
    /*
    public Price priceplop () {
        if (null != this.price) {
            return this.price;
        }
        
        PriceCalculator calculator = this
            .calculators
            .stream()
            .filter(pc -> pc.canApply(this))
            .reduce(null, (carry, current) -> { return null != carry
                    ? carry
                    : current;})
        ;
        
        if (null == calculator) {
            throw new TechnicalException("Cannot found valid price calculators");
        }
        
        this.price = calculator.calculate(this);
        
        return this.price;
    }
    */
}
