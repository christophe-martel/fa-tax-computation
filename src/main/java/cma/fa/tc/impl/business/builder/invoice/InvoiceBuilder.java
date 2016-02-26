/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
