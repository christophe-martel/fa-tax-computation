/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.service.calculator.reference;

import cma.fa.tc.def.business.entity.PricedOrder;

/**
 *
 * @author christophe
 */
public interface InvoiceReference {
    
    public String compute (PricedOrder order);
    
}
