package core.base.concretes;

import entities.abstracts.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Helper {
    static SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static <T extends Entity<?>> void dumpFieldValuesOfEntities(List<T> entities) {
        for (T entity : entities) {
            System.out.println(".");
            dumpFieldValuesOfEntity(entity);
        }
    }

    public static <T extends Entity<?>> void dumpGetterValuesOfEntities(List<T> entities) {
        for (T entity : entities) {
            System.out.println(".");
            dumpGetterValuesOfEntity(entity);
        }
    }

    public static <T extends Entity<?>> void dumpFieldValuesOfEntity(T entity) {
        for (Field field2 : entity.getClass().getDeclaredFields()) {
            Object val = null;
            try {
                val = field2.get(entity);
            } catch (IllegalArgumentException e) {
                if (Helper.printStactTraceAllowed())
                    e.printStackTrace();
            } catch (IllegalAccessException e) {
                if (Helper.printStactTraceAllowed())
                    e.printStackTrace();
            }
            System.out.printf("%-20s : %-100s\n", field2.getName().toUpperCase(), val);
        }
    }

    public static <T extends Entity<?>> void dumpGetterValuesOfEntity(T entity) {
        for (Method method : Arrays.stream(entity.getClass().getDeclaredMethods())
                .filter(m -> m.getName().startsWith("get")).collect(Collectors.toList())) {

            Object val = null;
            try {
                val = method.invoke(entity);
            } catch (IllegalAccessException e) {
                if (Helper.printStactTraceAllowed())
                    e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (Helper.printStactTraceAllowed())
                    e.printStackTrace();
            }
            System.out.printf("%-20s : %-100s\n", method.getName().substring(3).toUpperCase(), val);
        }
    }

    public static Date stringToDate(String date) {
        try {
            return _dateFormat.parse(date);
        } catch (ParseException e) {
            if (Helper.printStactTraceAllowed())
                e.printStackTrace();
        }
        return null;
    }

    public static String dateToString(Date date) {
        return _dateFormat.format(date);
    }

    public static String getStringSystemDateUTC() {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static ZonedDateTime getZDateTimeSystemDateUTC() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static Date getDateSystemDateUTC() {
        return Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
    }

    public static boolean printStactTraceAllowed() {
        return false;
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}