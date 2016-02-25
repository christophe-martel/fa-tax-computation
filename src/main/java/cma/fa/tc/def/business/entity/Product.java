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
public interface Product extends Taxable {
    
    public String code ();
    
    public String label ();
    
    public float unitPrice ();
    
}
