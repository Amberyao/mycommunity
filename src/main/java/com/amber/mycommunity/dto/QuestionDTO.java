package com.amber.mycommunity.dto;

import com.amber.mycommunity.model.User;
import lombok.Data;

/**
 * @author amber
 * @date 2021/7/27 17:08
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
    private Integer sticky;
}
