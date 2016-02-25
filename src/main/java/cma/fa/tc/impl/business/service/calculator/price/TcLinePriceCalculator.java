package cma.fa.tc.impl.business.service.calculator.price;


import cma.fa.tc.def.business.entity.Line;
import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.def.business.entity.Product;
import cma.fa.tc.def.business.service.calculator.Price.LinePriceCalculator;
import cma.fa.tc.def.business.service.calculator.Price.ProductPriceCalculator;
import cma.fa.tc.def.utils.math.Rounder;
import cma.fa.tc.impl.business.entity.TcPrice;
import cma.fa.tc.impl.business.entity.order.TcLine;
import cma.fa.tc.impl.business.entity.product.SimpleProduct;
import cma.fa.tc.impl.utils.math.NearestRound;

/**
 *
 * @author christophe
 */
public class TcLinePriceCalculator implements LinePriceCalculator {
    
    public TcLinePriceCalculator() {}
    
    @Override
    public boolean canApply (Object target) {
        return target instanceof TcLine;
    }
    
    @Override
    public Price calculate (Line line) {
        return new TcPrice(line.product().price(), line.quantity());
    }
}
