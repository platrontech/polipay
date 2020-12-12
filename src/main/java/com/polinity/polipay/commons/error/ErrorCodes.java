package com.polinity.polipay.commons.error;

public enum ErrorCodes {
    API_SERVER_ERROR(100, "Serverda bir hata oluştu", true),
    UNAUTHORIZED(101, "Ulasmak istenen API'ye yetkiniz yoktur", true),
    UNAUTHORIZED_OPERATION(102, "Bu işlem için yetkiniz yoktur", true),

    CARD_ERROR(200, "Kart işlemi sırasında bir hata oluştu", true),
    GENERIC_PAYMENT_ERROR(300, "Ödeme sırasında bir hata oluştu", true),
    PAYMENT_CARD_LIMIT_ERROR(300, "Limit yetersiz", true);

    private final int code;
    private final String error;
    private boolean logError;

    ErrorCodes(int code, String error, boolean logError) {
        this.code = code;
        this.error = error;
        this.logError = logError;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public boolean isLogError() {
        return logError;
    }

    public void setLogError(boolean logError) {
        this.logError = logError;
    }

    }
