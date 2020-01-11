
package ru.avalon.java.j30.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class QueryProvider {
    
    public String getQuery(String path) throws IOException{
        path = "properties/sql/" + path + ".sql";
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream stream = classLoader.getResourceAsStream(path)){
            try(Reader reader = new InputStreamReader(stream)){
                try(BufferedReader in = new BufferedReader(reader)){
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while((line = in.readLine()) != null){
                        builder.append(line)
                               .append(System.lineSeparator());
                    }
                    return builder.toString();
                }
                
            }
        }        
    }
}
