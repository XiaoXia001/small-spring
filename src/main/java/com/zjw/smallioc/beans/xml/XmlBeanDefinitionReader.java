package com.zjw.smallioc.xml;

import com.zjw.smallioc.AbstractBeanDefinitionReader;
import com.zjw.smallioc.BeanDefinition;
import com.zjw.smallioc.BeanReference;
import com.zjw.smallioc.PropertyValue;
import com.zjw.smallioc.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 根据给的xml文件，来获取BeanDefinitions
     * @param location
     * @throws Exception
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    /**
     * 流文件转为Document形式
     * @param inputStream
     * @throws Exception
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinitions(document);
        inputStream.close();
    }

    /**
     * 获取根元素
     * @param document
     */
    public void registerBeanDefinitions(Document document){
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root){
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node instanceof Element){
                Element element = (Element)node;
                processBeanDefinition(element);

            }
        }
    }

    protected void processBeanDefinition(Element element){
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(element, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    /**
     *
     * @param element 一个xml标签
     * @param beanDefinition
     */
    private void processProperty(Element element, BeanDefinition beanDefinition){
        NodeList nodeList = element.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node instanceof Element){
                Element element1 = (Element) node;
                String name = element1.getAttribute("name");
                String value = element1.getAttribute("value");
                if (value != null && value.length() > 0){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }else {
                    String ref = element1.getAttribute("ref");
                    if (ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }

            }
        }
    }

}
