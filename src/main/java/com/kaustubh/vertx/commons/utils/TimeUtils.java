package com.kaustubh.vertx.commons.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtils {
    public static Date ago(long amountToSubtract, ChronoUnit timeUnit){
        return new Date(Instant.now().minus(amountToSubtract,timeUnit).toEpochMilli());
    }

    public static Date fromNow(long amountToAdd, ChronoUnit timeUnit){
        return new Date(Instant.now().plus(amountToAdd,timeUnit).toEpochMilli());
    }
}
