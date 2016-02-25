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

package cma.fa.tc.impl.utils.files;

import cma.fa.tc.application.Constant;
import cma.fa.tc.def.utils.files.CsvReader;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class SimpleCsvReader implements CsvReader {
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final String path;
    
    @Accessors(chain = true, fluent = true)
    @Getter
    private final String charset;
    
    public SimpleCsvReader (String path, String charset) {
        this.path = path;
        this.charset = charset;
    }
    
    public SimpleCsvReader (String path) {
        this(path, Constant.CHARSET);
    }
    
    @Override
    public List<List<String>> read () {
        List<List<String>> result = new ArrayList<>();
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream(this.path),
                    this.charset))) {
            Iterable<CSVRecord> parser = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : parser) {
                result.add(Lists.newArrayList(record.iterator()));
                
            }
            
        } catch (Exception ex) {
            log.error("Cannot read {}", ex);
        }
        
        return result;
    }
    
}
