package com.zjw.smallioc.io;

import java.net.URL;

public class ResourceLoader {

    /**
     * 获取URL
     * @param location
     * @return
     */
    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
