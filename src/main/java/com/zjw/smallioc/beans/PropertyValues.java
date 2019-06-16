package com.zjw.smallioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues(){

    }

    public void addPropertyValue(PropertyValue propertyValue)
    {
//        for (PropertyValue pv : propertyValueList)
//        {
//            if ((pv.getName()).equals(propertyValue.getName())){
//                System.out.println("This value has already existed!");
//                return;
//            }
//        }
        propertyValueList.add(propertyValue);

    }

    public List<PropertyValue> getPropertyValueList(){
        return this.propertyValueList;
    }
}
