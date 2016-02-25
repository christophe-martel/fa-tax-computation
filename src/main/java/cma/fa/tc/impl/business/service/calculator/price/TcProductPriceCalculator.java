package cma.fa.tc.impl.business.service.calculator.price;


import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.def.utils.math.Rounder;
import cma.fa.tc.impl.business.entity.TcPrice;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.utils.math.NearestRound;

/**
 *
 * @author christophe
 */
public class TcProductPriceCalculator implements ProductPriceCalculator {
    
    private final Rounder rounder;

    public TcProductPriceCalculator(Rounder rounder) {
        this.rounder = rounder;
    }
    
    @Override
    public boolean canApply (Object target) {
        return target instanceof SimpleProduct;
    }
    
    @Override
    public Price calculate (Product product) {
        
        float taxRate = product
            .taxes()
            .stream()
            .map(tax -> tax.rate())
            .reduce(0F, (carry, current) -> carry + current)
        ;
        
        float taxAmount = product.unitPrice() * taxRate / 100;
        
        taxAmount = this.rounder.round(taxAmount);
        
        return new TcPrice(
            product.unitPrice() + taxAmount,
            taxAmount,
            taxRate,
            product.unitPrice())
        ;
    }
}
