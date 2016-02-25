/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cma.fa.tc.def.business.entity;

/**
 *
 * @author christophe
 */
public interface Price {
    
    public float includingTaxes ();
    
    public float taxAmount ();
    
    public float taxRates ();
    
    public float excludingTaxes ();
    
}
