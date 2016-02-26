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
