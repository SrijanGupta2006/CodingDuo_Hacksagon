package com.CodingDupo.projectAPI.Standards;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time {
    //private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
    public static LocalDateTime now(){
        return LocalDateTime.now();
    }
    public static Date afterNow(long millis){
        return new Date(System.currentTimeMillis()+millis);
    }
}
