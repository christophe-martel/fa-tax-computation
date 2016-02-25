package cma.fa.tc.impl.business.service.calculator.price;


import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.PricedLine;
import cma.fa.tc.def.business.entity.PricedLines;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.LinePriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.LinesPriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.def.utils.math.Rounder;
import cma.fa.tc.impl.business.entity.TcPrice;
import cma.fa.tc.impl.business.entity.order.TcLine;
import cma.fa.tc.impl.business.entity.order.TcPricedLine;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.utils.math.NearestRound;

/**
 *
 * @author christophe
 */
public class TcLinesPriceCalculator implements LinesPriceCalculator {
    
    public TcLinesPriceCalculator() {}
    
    @Override
    public boolean canApply (Object target) {
        return target instanceof PricedLines;
    }
    
    @Override
    public Price calculate (PricedLines lines) {
        return lines.lines().stream()
            .map(l -> l.price())
            .reduce(null, (carry, current) -> {
                if (null == carry) {
                    return new TcPrice(current);
                }
                
                return new TcPrice(current).add(carry);
            })
        ;
        
    }
}
