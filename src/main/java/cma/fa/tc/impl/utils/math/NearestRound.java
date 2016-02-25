/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
