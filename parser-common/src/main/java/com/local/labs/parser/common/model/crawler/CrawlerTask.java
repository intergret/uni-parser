package com.local.labs.parser.common.model.crawler;

import java.util.ArrayList;
import java.util.List;

import com.local.labs.parser.common.model.parser.PageType;

public class CrawlerTask {

  private String siteName;

  private PageType pageType;

  private List<HttpRequest> httpRequests;

  public CrawlerTask() {}

  public CrawlerTask(String siteName, PageType pageType) {
    this.siteName = siteName;
    this.pageType = pageType;
  }

  public List<HttpRequest> getHttpRequests() {
    return httpRequests;
  }

  public void setHttpRequests(List<HttpRequest> httpRequests) {
    this.httpRequests = httpRequests;
  }

  public void addHttpRequest(HttpRequest httpRequest) {
    if (httpRequests == null) {
      httpRequests = new ArrayList<>();
    }
    httpRequests.add(httpRequest);
  }

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public PageType getPageType() {
    return pageType;
  }

  public void setPageType(PageType pageType) {
    this.pageType = pageType;
  }
}
