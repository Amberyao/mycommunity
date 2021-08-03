package com.amber.mycommunity.dto;

import lombok.Data;

/**
 * @author amber
 * @date 2021/8/3 20:59
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
