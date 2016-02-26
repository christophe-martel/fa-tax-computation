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
package cma.fa.tc.impl.utils.math;

import cma.fa.tc.def.utils.math.Rounder;
import cma.fa.tc.impl.utils.exception.BusinessException;

/**
 *
 * @author christophe
 */
public class NearestRound implements Rounder {
    
    private final double toNearest;
    
    public NearestRound (double toNearest) {
        if (toNearest <= 0) {
            throw new BusinessException(String.format(
                "toNearest must be positive, encountered %f",
                toNearest));
        }
        
        this.toNearest = 1 / toNearest;
    }
    
    @Override
    public double round (double d) {
        return Math.ceil(d * this.toNearest)
            / this.toNearest;
        
        
    }
    
    @Override
    public float round (float d) {
        return (float)this.round((double)d);
    }
}
