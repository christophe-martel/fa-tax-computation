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

import cma.fa.tc.def.business.entity.Order;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.PricedLine;
import cma.fa.tc.def.business.service.calculator.Price.LinesPriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.PriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.impl.utils.exception.TechnicalException;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@EqualsAndHashCode(of={"number"})
@ToString
@Slf4j
public class TcOrder implements Order {
    @Accessors(chain = true, fluent = true)
    @Getter
    private String number;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private Set<PricedLine> lines;
    
    public TcOrder (
            String number,
            Set<PricedLine> lines) {
        this.number = number;
        this.lines = lines;
    }
    
    public TcOrder (Order order) {
        this(order.number(), order.lines());
    }
    
}