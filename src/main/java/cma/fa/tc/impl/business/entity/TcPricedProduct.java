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
import cma.fa.tc.def.business.entity.PricedProduct;
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
@EqualsAndHashCode(of={"price"}, callSuper = true)
@ToString
@Slf4j
public abstract class TcPricedProduct
        extends TcProduct
        implements PricedProduct {
    @Accessors(chain = true, fluent = true)
    @Getter
    private final Price price;
    
    public TcPricedProduct (
            String code,
            String label,
            float unitPrice,
            Set<Tax> taxes,
            Price price) {
        super(code, label, unitPrice, taxes);
        this.price = price;
    }
    
    public TcPricedProduct (Product product, Price price) {
        this(
            product.code(),
            product.label(),
            product.unitPrice(),
            product.taxes(),
            price);
        
    }
    
}
