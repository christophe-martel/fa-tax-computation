/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
