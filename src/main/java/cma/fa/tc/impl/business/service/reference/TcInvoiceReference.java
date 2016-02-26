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
