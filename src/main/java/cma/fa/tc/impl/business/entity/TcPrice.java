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
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@ToString
@Slf4j
public class TcPrice implements Price {
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private float includingTaxes = 0;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private float taxAmount = 0;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private float taxRates = 0;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private float excludingTaxes = 0;

    public TcPrice(
            float includingTaxes,
            float taxAmount,
            float taxRates,
            float excludingTaxes) {
        this.includingTaxes = includingTaxes;
        this.taxAmount = taxAmount;
        this.taxRates = taxRates;
        this.excludingTaxes = excludingTaxes;
    }
    
    public TcPrice(Price price, float scale) {
        this(
            price.includingTaxes() * scale,
            price.taxAmount()* scale,
            price.taxRates(),
            price.excludingTaxes() * scale
        );
    }
    
    public TcPrice(Price price, int scale) {
        this(price, (float) scale);
    }
    
    public TcPrice(Price price) {
        this(
            price.includingTaxes(),
            price.taxAmount(),
            price.taxRates(),
            price.excludingTaxes());
    }
    
    public TcPrice add (Price price) {
        return new TcPrice(
                this.includingTaxes() + price.includingTaxes(),
                this.taxAmount() + price.taxAmount(),
                (this.taxRates() + price.taxRates()) / 2,
                this.excludingTaxes() + price.excludingTaxes()
        );
        
    }
}
