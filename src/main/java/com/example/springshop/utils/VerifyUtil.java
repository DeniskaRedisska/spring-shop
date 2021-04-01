package com.example.springshop.utils;

import com.example.springshop.exceptions.VerificationException;

public class VerifyUtil {
    public static void verifyNotNull(Object o) {
        if (o == null) throw new VerificationException("Null reference!");
    }
}
