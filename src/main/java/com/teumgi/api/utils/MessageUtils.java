package com.teumgi.api.utils;

import com.google.common.base.Strings;
import org.springframework.context.support.MessageSourceAccessor;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class MessageUtils {

    private static MessageSourceAccessor messageSourceAccessor;

    public static String getMessage(String key) {
        checkState(null != messageSourceAccessor, "MessageSourceAccessor is not initialized.");
        return messageSourceAccessor.getMessage(key);
    }

    public static String getMessage(String key, Object... params) {
        checkState(null != messageSourceAccessor, "MessageSourceAccessor is not initialized.");
        return messageSourceAccessor.getMessage(key, params);
    }

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageUtils.messageSourceAccessor = messageSourceAccessor;
    }
/*
*
*
* @Param message
* */
    public static String makePostpositionByKey(String name, Integer key) {
        if (Strings.isNullOrEmpty(name)) {
         return name;
        }
        char lastName = name.charAt(name.length() - 1);
        String str1;
        String str2;
        if (0 == key) {
            str1 = "이";
            str2 = "가";
        } else if (1 == key) {
            str1 = "은";
            str2 = "는";
        } else if (2 == key) {
            str1 = "을";
            str2 = "를";
        } else {
            throw new IllegalArgumentException("허용되지 않는 형식입니다.");
        }

        //한글이 아닐 시 str2 가/는/를 적용
        if (lastName < 0xAC00 || lastName > 0xD7A3) {
            return name + str2;
        }

        String postposition = (lastName - 0xAC00) % 28 > 0 ? str1 : str2;

        return name + postposition;
    }

/*
    public static String replaceMessageWithExpression(String body, String petName, String userName) {
        body = body.replaceAll("[{][0][,][0][}]", makePostpositionByKey(petName, 0));
        body = body.replaceAll("[{][0][,][1][}]", makePostpositionByKey(petName, 1));
        body = body.replaceAll("[{][0][,][2][}]", makePostpositionByKey(petName, 2));
        body = body.replaceAll("[{][1][,][0][}]", makePostpositionByKey(userName, 0));
        body = body.replaceAll("[{][1][,][1][}]", makePostpositionByKey(userName, 1));
        body = body.replaceAll("[{][1][,][2][}]", makePostpositionByKey(userName, 2));

        body = MessageFormat.format(body, petName, userName);

        return body;
    }
*/


    public static String replaceMessageWithExpression(String body, String... names) {

        final Pattern pattern = Pattern.compile("\\{([0-1]),([0-2])}");
        final Matcher matcher = pattern.matcher(body);

        while (matcher.find()) {
            final String matched = matcher.group();
            final int nameIndex = Integer.parseInt(matcher.group(1));
            final int key = Integer.parseInt(matcher.group(2));
            body = body.replace(matched, makePostpositionByKey(names[nameIndex], key));
        }

        body = MessageFormat.format(body, names);

        return body;
    }
}