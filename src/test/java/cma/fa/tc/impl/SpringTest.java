
package cma.fa.tc.impl;

import cma.fa.tc.application.Main;
import cma.fa.tc.impl.business.service.repository.Orders;
import cma.fa.tc.impl.business.service.repository.OrdersTest;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class SpringTest extends TestCase {
    
    protected ApplicationContext context = null;
    
    public SpringTest(String testName) {
        super(testName);
        
    }
    
    @Override
    protected void setUp() throws Exception {
        try {
        this.context = new ClassPathXmlApplicationContext(
                "/configuration/spring.xml",
                Main.class);
        } catch (Exception e) {
            log.info("Oups, {}", e);
        }
    }
    
    @Override
    protected void tearDown() throws Exception {
        ((ConfigurableApplicationContext) context).close();
    }
    
    
    /**
     * Test of start method, of class PricedBuilder.
     */
    public void testAll () {
        
        Object bean = this.context.getBean("repository.orders");
        
        assertEquals(true, bean instanceof Orders);
        
        Orders orders = (Orders) bean;
        
        assertEquals(3, orders.all().get().size());
        
        OrdersTest.executeAll(orders);
    }
    
    
}
