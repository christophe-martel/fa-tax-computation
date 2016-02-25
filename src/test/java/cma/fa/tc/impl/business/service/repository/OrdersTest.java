
package cma.fa.tc.impl.business.service.repository;

import cma.fa.tc.def.business.entity.Price;
import cma.fa.tc.impl.business.builder.order.LineBuilder;
import cma.fa.tc.impl.business.builder.order.OrderBuilder;
import cma.fa.tc.impl.business.builder.product.PricedBuilder;
import cma.fa.tc.impl.business.builder.product.SimpleBuilder;
import cma.fa.tc.impl.business.service.calculator.price.TcLinePriceCalculator;
import cma.fa.tc.impl.business.service.calculator.price.TcLinesPriceCalculator;
import cma.fa.tc.impl.business.service.calculator.price.TcProductPriceCalculator;
import cma.fa.tc.impl.utils.files.SimpleCsvReader;
import cma.fa.tc.impl.utils.math.NearestRound;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author christophe
 */
public class OrdersTest extends TestCase {
    
    public OrdersTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getClazz method, of class Orders.
     */
    public void testAll () {
        
        
        Orders orders = new Orders(
            new SimpleCsvReader("/data/orders.csv"),
            new OrderBuilder(
                new LineBuilder(
                        new PricedBuilder(
                            new TcProductPriceCalculator(new NearestRound(0.05))),
                        new TcLinePriceCalculator()
                ),
                new SimpleProducts(
                    new SimpleCsvReader("/data/products.csv"),
                    new SimpleBuilder(new Taxes(new SimpleCsvReader("/data/taxes.csv")))),
                new TcLinesPriceCalculator()));
        
        
        assertEquals(3, orders.all().size());
        
        
        Stream.of(
                "Input 1:1.5f:29.83f",
                "Input 2:7.65f:65.15f",
                "Input 3:6.70f:74.68f")
            .map(str -> {
                String[] parts = str.split(":", 3);
                return new AbstractMap.SimpleEntry<>(
                    parts[0],
                    Arrays.asList(Float.parseFloat(parts[1]), Float.parseFloat(parts[2])));
            })
            .collect(Collectors.toMap(
                map -> map.getKey(),
                map -> map.getValue()))
            .entrySet()
            .forEach(e -> {
                Price price = orders.one("number", e.getKey()).price();
                assertEquals(e.getValue().get(0), price.taxAmount());
                assertEquals(e.getValue().get(1), price.includingTaxes());
            })
        ;
    }

    
}
