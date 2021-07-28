package com.amber.mycommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

  private Long id;
  private String title;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private Long creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
  private String tag;
  private Integer sticky;

}
