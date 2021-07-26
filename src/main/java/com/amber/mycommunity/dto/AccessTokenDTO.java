package com.amber.mycommunity.dto;

import lombok.Data;

/**
 * @author amber
 * @date 2021/7/23 13:54
 */
//dto数据传输对象
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
