package com.liuil.springboot.converter;

import com.liuil.springboot.request.Color;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter implements Converter<String, Color> {
    @Override
    public Color convert(String value) {
        return Color.findBy(value);
    }
}
