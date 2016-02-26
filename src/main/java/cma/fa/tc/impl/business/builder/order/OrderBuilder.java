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

import cma.fa.tc.def.business.entity.Order;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.PricedLine;
import cma.fa.tc.def.business.entity.PricedOrder;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.LinesPriceCalculator;
import cma.fa.tc.impl.business.entity.TcOrder;
import cma.fa.tc.impl.business.entity.order.TcPricedOrder;
import cma.fa.tc.impl.business.service.repository.SimpleProducts;
import cma.fa.tc.impl.business.service.repository.Taxes;
import cma.fa.tc.impl.utils.exception.BusinessException;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
public class OrderBuilder {
    
    private final Set<LinesPriceCalculator> calculators;
    
    private final LineBuilder builder;
    
    private final SimpleProducts productsProvider;
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private String number = null;
    
    @Accessors(chain = true, fluent = true)
    private Set<PricedLine> lines;

    public OrderBuilder(
            LineBuilder builder,
            SimpleProducts productsProvider,
            Set<LinesPriceCalculator> calculators) {
        this.builder = builder;
        this.productsProvider = productsProvider;
        this.calculators = calculators;
        this.lines = new LinkedHashSet<>();
    }
    

    public OrderBuilder(
            LineBuilder builder,
            SimpleProducts productsProvider,
            LinesPriceCalculator calculator) {
        this(
            builder,
            productsProvider,
            Arrays.asList(calculator).stream().collect(Collectors.toSet()));
    }
    
    public OrderBuilder start () {
        this.number = null;
        this.lines = new LinkedHashSet<>();
        return this;
    }
    
    public OrderBuilder product (Product product, int quantity) {
        this.lines.add(this.builder
            .start()
            .product(product)
            .quantity(quantity)
            .build());
        return this;
    }
    
    public OrderBuilder products (Set<Map.Entry<String, Integer>> products) {
        products
            .stream()
            .forEach(e -> this.product(
                this.productsProvider.byCode(e.getKey()).get(),
                e.getValue()));
        
        return this;
    }
    
    public PricedOrder build () {
        return this
            .checkEmptyString("number", this.number)
            .create()
        ;
    }
    
    private PricedOrder create () {
        Order order = this.createOrder();
        return new TcPricedOrder(order, this.computePrice(order));
    }
    
    
    private Order createOrder () {
        return new TcOrder(this.number, this.lines);
    }
    
    private Price computePrice (Order order) {
        LinesPriceCalculator calculator = this
            .calculators
            .stream()
            .filter(c -> c.canApply(order))
            .reduce(null, (carry, current) -> { return null != carry
                    ? carry
                    : current;})
        ;
        
        if (null == calculator) {
            throw new TechnicalException("Cannot found valid price calculators for order");
        }
        
        return calculator.calculate(order);
        
    }
    
    private OrderBuilder checkEmptyString (String property, String value) {
        if ((null == value)
                || (value.trim().isEmpty())) {
            throw new BusinessException(String.format("%s must be provided", property));
        }
        
        return this;
    }
}
