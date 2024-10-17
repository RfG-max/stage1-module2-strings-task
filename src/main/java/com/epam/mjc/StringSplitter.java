package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        Iterator<String> iter = delimiters.iterator();
        StringBuilder delimeters = new StringBuilder();
        while(iter.hasNext()){
            delimeters.append(iter.next());
            if(iter.hasNext()) delimeters.append("|");
        }

        String[] result = source.split(delimeters.toString());
        List<String> resultList = Arrays.stream(result)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        return resultList;
    }
}
