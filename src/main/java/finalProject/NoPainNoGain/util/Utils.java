package finalProject.NoPainNoGain.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Utils {

    public static LocalDateTime convertLongToLocalDateTime(long time){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                TimeZone.getDefault().toZoneId());
    }

    public static long convertLocalDateTimeToLong(LocalDateTime time){
        return ZonedDateTime.of(time, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
