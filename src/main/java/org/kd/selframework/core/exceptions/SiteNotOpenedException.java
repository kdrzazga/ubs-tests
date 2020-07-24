package org.kd.selframework.core.exceptions;

import org.kd.selframework.core.utils.PropertiesReader;

public class SiteNotOpenedException extends RuntimeException {

    public SiteNotOpenedException(String url, int timeout){
        super(PropertiesReader.readFromConfig("exception.cantload") + url
                + PropertiesReader.readFromConfig("exception.timeout.message") + timeout);
    }
}
