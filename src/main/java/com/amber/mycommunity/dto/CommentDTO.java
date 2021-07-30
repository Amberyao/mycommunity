package com.amber.mycommunity.dto;

import com.amber.mycommunity.model.User;
import lombok.Data;

/**
 * @author amber
 * @date 2021/7/30 14:49
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
