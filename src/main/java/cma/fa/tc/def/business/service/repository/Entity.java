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
package cma.fa.tc.def.business.service.repository;

import java.util.Set;

/**
 *
 * @author christophe
 * @param <T>
 */
public interface Entity<T> {
    
    public Returned<T> byCode (String code);
    
    public Returned<T> one (String property, String value);
    
    public Returned<Set<T>> by (String property, String value);
    
    public Returned<Set<T>> all ();
    
}
