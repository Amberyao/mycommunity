package com.amber.mycommunity.enums;

/**
 * @author amber
 * @date 2021/8/2 19:55
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
