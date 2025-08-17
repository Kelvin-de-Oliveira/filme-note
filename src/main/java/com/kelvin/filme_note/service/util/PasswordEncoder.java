package com.kelvin.filme_note.service.util;

import java.util.Base64;

public class PasswordEncoder {

    public static String hash(String raw) {
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }

    public static boolean matches(String raw, String hash) {
        return hash(raw).equals(hash);
    }
}
