package nextstep.utils;

public class StringUtils {

    public static boolean isEmpty(String target) {
        if(target == null || "".equals(target))
            return true;

        return false;
    }
}
