package com.amber.mycommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

  private long id;
  private String title;
  private String description;
  private long gmtCreate;
  private long gmtModified;
  private long creator;
  private long commentCount;
  private long viewCount;
  private long likeCount;
  private String tag;
  private long sticky;

}
