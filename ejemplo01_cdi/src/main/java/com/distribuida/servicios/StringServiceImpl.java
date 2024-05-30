package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringServiceImpl implements  StringService {
    @Override
    public String convert(String src) {
        return src.toUpperCase();
    }
}
