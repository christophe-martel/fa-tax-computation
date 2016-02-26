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
package cma.fa.tc.impl.business.builder.invoice;

import cma.fa.tc.def.business.entity.Invoice;
import cma.fa.tc.def.business.entity.PricedOrder;
import cma.fa.tc.def.business.service.calculator.reference.InvoiceReference;
import cma.fa.tc.impl.business.entity.invoice.FinalLine;
import cma.fa.tc.impl.business.entity.invoice.TcInvoice;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author christophe
 */
@Slf4j
public class InvoiceBuilder {
    
    private final InvoiceReference invoiceReference;
    
    private PricedOrder order = null;

    public InvoiceBuilder(InvoiceReference invoiceReference) {
        this.invoiceReference = invoiceReference;
    }
    
    public InvoiceBuilder order (PricedOrder order) {
        this.order = order;
        return this;
    }
    
    public Invoice build () {
        LocalDateTime now = LocalDateTime.now();
        
        return new TcInvoice(
            this.invoiceReference.compute(this.order),
            this
                .order
                .lines()
                .stream()
                .map(pricedLine -> new FinalLine(pricedLine, now))
                .collect(Collectors.toCollection(LinkedHashSet::new)),
            this.order.price(),
            now
        );
        
        
    }
    
    
}
