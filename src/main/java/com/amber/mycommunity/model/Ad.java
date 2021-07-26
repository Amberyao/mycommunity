package com.amber.mycommunity.model;

import lombok.Data;

@Data
public class Ad {

  private long id;
  private String title;
  private String url;
  private String image;
  private long gmtStart;
  private long gmtEnd;
  private long gmtCreate;
  private long gmtModified;
  private long status;
  private String pos;


}
