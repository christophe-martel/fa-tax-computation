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

package cma.fa.tc.def.utils.exception;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public abstract class TcException extends RuntimeException {

    public TcException() {
    }

    public TcException (String message) {
        super(message);
        log.error(message);
    }

    public TcException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public TcException(Throwable cause) {
        super(cause);
        log.error(cause.getMessage(), cause);
    }

    public TcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error(message, cause);
    }
    
    
    
}
