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
package cma.fa.tc.impl.controller;


import cma.fa.tc.def.utils.exception.TcException;
import cma.fa.tc.def.controller.Controller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class TcController implements Controller {
    
    private long startTime;
    private long endTime;
    
    public TcController () {
    }
    
    @Override
    public TcController init (String[] args) throws TcException {
        log.debug("init");
        
        return this;
    }
    
    @Override
    public TcController run () throws TcException {
        
        this.startTime = System.currentTimeMillis();
        
        log.debug("run");
        
        
        this.endTime = System.currentTimeMillis();
        
        return this;
    }
    
    @Override
    public TcController finish () {
        
        log.info("Duration process is {} ms",
            this.endTime - this.startTime);
        
        log.debug("end");
        
        return this;
    }
    
    
}
