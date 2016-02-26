/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.business.service.reference;

import cma.fa.tc.def.business.entity.PricedOrder;
import cma.fa.tc.def.business.service.calculator.reference.InvoiceReference;

/**
 *
 * @author christophe
 */
public class TcInvoiceReference implements InvoiceReference {
    
    @Override
    public String compute (PricedOrder order) {
        return order.number().replaceAll("Input", "Output");
    }
    
    
}
