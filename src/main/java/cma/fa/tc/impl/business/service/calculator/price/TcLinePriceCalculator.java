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
package cma.fa.tc.impl.business.service.calculator.price;


import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.LinePriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.def.utils.math.Rounder;
import cma.fa.tc.impl.business.entity.TcPrice;
import cma.fa.tc.impl.business.entity.order.TcLine;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.utils.math.NearestRound;

/**
 *
 * @author christophe
 */
public class TcLinePriceCalculator implements LinePriceCalculator {
    
    public TcLinePriceCalculator() {}
    
    @Override
    public boolean canApply (Object target) {
        return target instanceof TcLine;
    }
    
    @Override
    public Price calculate (Line line) {
        return new TcPrice(line.product().price(), line.quantity());
    }
}
