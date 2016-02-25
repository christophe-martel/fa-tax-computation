/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.impl.utils.math;

import cma.fa.tc.def.utils.math.Rounder;


/**
 *
 * @author christophe
 */
public class NoRound implements Rounder {
    
    public NoRound () {}
    
    @Override
    public double round (double d) {
        return d;
    }
    
    @Override
    public float round (float d) {
        return d;
    }
}
