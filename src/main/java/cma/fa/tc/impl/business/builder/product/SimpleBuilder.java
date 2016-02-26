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

import cma.fa.tc.def.business.entity.Tax;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.business.service.repository.Taxes;
import cma.fa.tc.impl.utils.exception.BusinessException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class SimpleBuilder {
    
    private final float PRICE_UNIT_MIN = 0.5F;
    
    private final Taxes taxesProvider;
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private String code = null;
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private String label = null;
    
    @Accessors(chain = true, fluent = true)
    @Setter
    private float unitPrice = -1;
    
    private Set<Tax> taxes = new LinkedHashSet<>();
    
    public SimpleBuilder (Taxes taxesProvider) {
        this.taxesProvider = taxesProvider;
    }
    
    public SimpleBuilder start () {
        this.code = null;
        this.label = null;
        this.unitPrice = -1;
        this.taxes = new LinkedHashSet<>();
        
        return this;
    }
    
    public SimpleBuilder tax (Tax tax) {
        this.taxes.add(tax);
        return this;
    }
    
    
    public SimpleBuilder tax (String taxCode) {
        this.taxes.add(this.taxesProvider.byCode(taxCode).get());
        return this;
    }
    
    public SimpleBuilder taxes (List<String> taxCodes) {
        taxCodes.stream().forEach(taxCode -> this.tax(taxCode));
        return this;
    }
    
    public SimpleProduct build () {
        return this
            .checkEmptyString("code", this.code)
            .checkEmptyString("label", this.label)
            .checkMinimalPrice("unit price", this.unitPrice)
            .create()
        ;
        
    }
    
    private SimpleProduct create () {
        return new SimpleProduct(
            this.code,
            this.label,
            this.unitPrice,
            this.taxes);
    }
    
    private SimpleBuilder checkEmptyString (String property, String value) {
        if ((null == value)
                || (value.trim().isEmpty())) {
            throw new BusinessException(String.format("%s must be provided", property));
        }
        
        
        
        return this;
    }
    
    
    private SimpleBuilder checkMinimalPrice (String property, float value) {
        if (value < this.PRICE_UNIT_MIN) {
            throw new BusinessException(String.format(
                "%s must be greather than %f",
                property,
                this.PRICE_UNIT_MIN));
        }
        
        return this;
    }
}
