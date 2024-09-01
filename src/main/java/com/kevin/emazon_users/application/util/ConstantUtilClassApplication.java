package com.kevin.emazon_users.application.util;

import com.kevin.emazon_users.infraestructure.exception.InvalidDatePattern;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConstantUtilClassApplication {
    private ConstantUtilClassApplication(){}

    public static Date convertStringIntoDate(String string){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return dateFormat.parse(string);
        } catch (Exception e) {
            throw new InvalidDatePattern("No se ha podido dar formato a la fecha, ingrese un formato 'yyyy/MM/dd'");
        }
    }

}
