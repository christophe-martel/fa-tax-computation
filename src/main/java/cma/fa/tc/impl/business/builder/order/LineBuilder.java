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
package cma.fa.tc.impl.business.builder.order;

import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.PricedLine;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.LinePriceCalculator;
import cma.fa.tc.impl.business.builder.product.PricedBuilder;
import cma.fa.tc.impl.business.entity.order.TcLine;
import cma.fa.tc.impl.business.entity.order.TcPricedLine;
import cma.fa.tc.impl.utils.exception.BusinessException;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class LineBuilder {
    
    private final Set<LinePriceCalculator> calculators;
    
    private final PricedBuilder pricedBuilder;
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private Product product = null;
    
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private int quantity = 0;
    
    public LineBuilder (
            PricedBuilder pricedBuilder,
            Set<LinePriceCalculator> calculators) {
        this.pricedBuilder = pricedBuilder;
        this.calculators = calculators;
    }
    
    public LineBuilder (
            PricedBuilder pricedBuilder,
            LinePriceCalculator calculator) {
        this(
            pricedBuilder,
            Arrays.asList(calculator).stream().collect(Collectors.toSet()));
    }
    
    public LineBuilder start () {
        this.product = null;
        this.quantity = 0;
        
        return this;
    }
    
    public PricedLine build () {
        return this
            .checkProduct()
            .checkQuantity()
            .create()
        ;
    }
    
    private PricedLine create () {
        Line line = this.createLine();
        return new TcPricedLine(line, this.computePrice(line));
    }
    
    
    private Line createLine () {
        return new TcLine(
            this.pricedBuilder.product(this.product).build(),
            this.quantity);
    }
    
    private Price computePrice (Line line) {
        LinePriceCalculator calculator = this
            .calculators
            .stream()
            .filter(c -> c.canApply(line))
            .reduce(null, (carry, current) -> { return null != carry
                    ? carry
                    : current;})
        ;
        
        if (null == calculator) {
            throw new TechnicalException("Cannot found valid price calculators");
        }
        
        return calculator.calculate(line);
        
    }
    
    private LineBuilder checkProduct () {
        if (null == this.product) {
            throw new BusinessException("Product must be provided");
        }
        
        return this;
    }
    
    private LineBuilder checkQuantity () {
        if (this.quantity < 1) {
            throw new BusinessException("Quantity must be greather than 0");
        }
        
        return this;
    }
}
