package com.zjw.smallioc.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoaderTest {

    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("smallioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
