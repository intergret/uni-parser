package com.local.labs.parser.common.model.parser;

public class ParserTask {

  private String siteName;

  private String url;

  private String refer;

  private String location;

  private String content;

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getRefer() {
    return refer;
  }

  public void setRefer(String refer) {
    this.refer = refer;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}