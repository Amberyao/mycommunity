package com.amber.mycommunity.dto;

import lombok.Data;

/**
 * @author amber
 * @date 2021/8/2 20:54
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
