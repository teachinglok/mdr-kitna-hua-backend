package com.zepiox.mdr.util;

import java.security.SecureRandom;

public class OTPGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a 6-digit OTP.
     *
     * @return A randomly generated 6-digit OTP as a String.
     */
    public static String generateOTP() {
        int otp = secureRandom.nextInt(1_000_000);
        return String.format("%06d", otp);
    }
}
