package isac.galvao.validator.validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public final class Validators {

    // Array validators
    public static boolean isMaxSize(Collection<?> obj, int maxSize) {
        return obj.size() <= maxSize;
    }

    public static boolean isMaxSize(Object[] obj, int maxSize) {
        return obj.length <= maxSize;
    }

    public static boolean isMinSize(Collection<?> obj, int minSize) {
        return obj.size() >= minSize;
    }

    public static boolean isMinSize(Object[] obj, int minSize) {
        return obj.length >= minSize;
    }

    public static boolean isArrayUnique(Collection<?> obj) {
        return new HashSet<>(obj).size() == obj.size();
    }

    public static boolean isArrayUnique(Object[] obj) {
        return new HashSet<>(List.of(obj)).size() == obj.length;
    }

    // Common validators
    public static boolean isEmpty(String obj) {
        return obj.isBlank();
    }

    public static boolean isEmpty(Collection<?> obj) {
        return obj.isEmpty();
    }

    public static boolean isEmpty(Object[] obj) {
        return obj.length == 0;
    }

    public static boolean isEmpty(Map<?, ?> obj) {
        return obj.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !str.isBlank();
    }

    public static boolean isNotEmpty(Collection<?> obj) {
        return !obj.isEmpty();
    }

    public static boolean isNotEmpty(Object[] obj) {
        return obj.length > 0;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !map.isEmpty();
    }

    public static boolean isIn(String str, Object[] values) {
        return Arrays.asList(values).contains(str);
    }

    public static boolean isNotIn(String str, Object[] values) {
        return !Arrays.asList(values).contains(str);
    }

    public static boolean isLatLng(String str, boolean checkDMS) {
        Pattern lat = Pattern.compile("^\\(?[+-]?(90(\\.0+)?|[1-8]?\\d(\\.\\d+)?)$");
        Pattern lng = Pattern.compile("^\\s?[+-]?(180(\\.0+)?|1[0-7]\\d(\\.\\d+)?|\\d{1,2}(\\.\\d+)?)\\)?$");

        Pattern latDMS = Pattern.compile("^(([1-8]?\\d)\\D+([1-5]?\\d|60)\\D+([1-5]?\\d|60)(\\.\\d+)?|90\\D+0\\D+0)\\D+[NSns]?$", Pattern.CASE_INSENSITIVE);
        Pattern lngDMS = Pattern.compile("^\\s*([1-7]?\\d{1,2}\\D+([1-5]?\\d|60)\\D+([1-5]?\\d|60)(\\.\\d+)?|180\\D+0\\D+0)\\D+[EWew]?$", Pattern.CASE_INSENSITIVE);

        if (!str.contains(",")) return false;
        String[] pair = str.split(",");
        if ((pair[0].startsWith("(") && !pair[1].endsWith(")")) || (pair[1].endsWith(")") && !pair[0].startsWith("(")))
            return false;

        if (checkDMS)
            return latDMS.matcher(pair[0]).matches() && lngDMS.matcher(pair[1]).matches();

        return lat.matcher(pair[0]).matches() && lng.matcher(pair[1]).matches();
    }

    public static boolean isLatLng(String str) {
        return isLatLng(str, false);
    }

    public static boolean isLatLngDMS(String str) {
        return isLatLng(str, true);
    }

    public static boolean isLatitude(String str) {
        return isLatLng(String.format("%s,0", str), false);
    }

    public static boolean isLongitude(String str) {
        return isLatLng(String.format("0,%s", str),false);
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isLength(String str, int min, int max) {
        return str.length() <= max && str.length() >= min;
    }

    public static boolean isLength(Collection<?> obj, int min, int max) {
        return obj.size() <= max && obj.size() >= min;
    }

    public static boolean isLength(Objects[] obj, int min, int max) {
        return obj.length <= max && obj.length >= min;
    }

    public static boolean isLength(Map<?, ?> map, int min, int max) {
        return map.size() <= max && map.size() >= min;
    }

    // Date validators
    public static boolean maxDate(Date date, int day, int month, int year) {
        LocalDate userDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate maxDate = LocalDate.of(year, month, day);
        return maxDate.isAfter(userDate) || maxDate.isEqual(userDate);
    }

    public static boolean minDate(Date date, int day, int month, int year) {
        LocalDate userDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate maxDate = LocalDate.of(year, month, day);
        return maxDate.isBefore(userDate) || maxDate.isEqual(userDate);
    }
}
