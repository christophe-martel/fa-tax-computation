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

package cma.fa.tc.impl.business.entity.invoice;


import cma.fa.tc.def.business.entity.Historisable;
import cma.fa.tc.impl.business.entity.TcTax;
import cma.fa.tc.def.business.entity.Tax;
import java.time.LocalDateTime;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@EqualsAndHashCode(of={"createdAt"}, callSuper=true)
@ToString(callSuper=true)
@Slf4j
public class FinalTax extends TcTax implements Historisable {
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final LocalDateTime createdAt;
    
    public FinalTax (String code, float rate, LocalDateTime createdAt) {
        super(code, rate);
        this.createdAt = createdAt;
    }
    
    public FinalTax (Tax tax, LocalDateTime createdAt) {
        this(tax.code(), tax.rate(), createdAt);
    }
    
    public FinalTax (Tax tax) {
        this(tax.code(), tax.rate(), LocalDateTime.now());
    }
    
}
