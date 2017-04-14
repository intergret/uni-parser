package com.local.labs.parser.common.model.crawler;

import java.util.Map;
import java.util.Set;

import com.local.labs.parser.common.model.parser.PageType;
import com.local.labs.parser.common.model.parser.ParserType;

public class LinkMeta {

  private String md5;
  private String url;
  private String siteName;
  private QueueType queueType;
  private ParserType parserType;
  private PageType pageType;
  private HttpMethod httpMethod;
  private Map<String,String> headers;
  private Map<String,String> forms;
  private int statusCode;
  private String lastRefer;
  private Set<String> allRefers;
  private String state;
  private long createTime;
  private long lastVisitTime;
  private long nextVisitTime;
  private int failedCount;
  private int successCount;
  private double weight;

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public QueueType getQueueType() {
    return queueType;
  }

  public void setQueueType(QueueType queueType) {
    this.queueType = queueType;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public String getLastRefer() {
    return lastRefer;
  }

  public void setLastRefer(String lastRefer) {
    this.lastRefer = lastRefer;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public Long getLastVisitTime() {
    return lastVisitTime;
  }

  public void setLastVisitTime(Long lastVisitTime) {
    this.lastVisitTime = lastVisitTime;
  }

  public int getFailedCount() {
    return failedCount;
  }

  public void setFailedCount(int failedCount) {
    this.failedCount = failedCount;
  }

  public int getSuccessCount() {
    return successCount;
  }

  public void setSuccessCount(int successCount) {
    this.successCount = successCount;
  }
}
