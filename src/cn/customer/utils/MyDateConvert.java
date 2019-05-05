package cn.customer.utils;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConvert implements Converter {
    @Override
    public Object convert(Class type, Object value) {
        if(type != Date.class)return null;
        if(value == null || value.toString().trim().equals("")) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(value.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
