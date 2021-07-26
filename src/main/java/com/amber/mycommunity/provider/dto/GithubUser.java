package com.amber.mycommunity.provider.dto;

import lombok.Data;

/**
 * @author amber
 * @date 2021/7/23 16:32
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
