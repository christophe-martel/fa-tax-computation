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


import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import cma.fa.tc.def.business.entity.Tax;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@EqualsAndHashCode(of={"code"})
@ToString
@Slf4j
public abstract class TcTax implements Tax {
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final String code;
    
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final float rate;
    
    public TcTax (String code, float rate) {
        this.code = code;
        this.rate = rate;
    }
    
    public TcTax (Tax tax) {
        this(tax.code(), tax.rate());
    }
    
}
