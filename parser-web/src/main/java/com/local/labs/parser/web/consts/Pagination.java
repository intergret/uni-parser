package com.local.labs.parser.web.consts;

public class Pagination {

  private int pageTotal; // 总页数

  private int pageNum; // 当前页码

  private int pageSize; // 每页记录数

  private int startIndex; // 起始位置

  private long totalRows; // 总记录数

  private String pageUrl; // 页面Url

  public Pagination(int pageNum, int pageSize, String pageUrl) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.pageUrl = pageUrl;
    setStartIndex(pageNum, pageSize);
  }

  public Pagination(int pageNum, int pageSize, long totalRows, String pageUrl) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.pageUrl = pageUrl;

    setStartIndex(pageNum, pageSize);
    setTotalRowsAndPageTotal(totalRows);
  }

  public void setTotalRowsAndPageTotal(long totalRows) {
    this.totalRows = totalRows;
    if (totalRows % (long) pageSize == 0) {
      this.pageTotal = (int) (totalRows / (long) pageSize);
    } else {
      this.pageTotal = (int) (totalRows / (long) pageSize) + 1;
    }
  }

  public int getPageTotal() {
    return pageTotal;
  }

  public void setPageTotal(int pageTotal) {
    this.pageTotal = pageTotal;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  public void setStartIndex(int pageNum, int pageSize) {
    this.startIndex = (pageNum - 1) * pageSize;
  }

  public long getTotalRows() {
    return totalRows;
  }

  public void setTotalRows(long totalRows) {
    this.totalRows = totalRows;
  }

  public String getPageUrl() {
    return pageUrl;
  }

  public void setPageUrl(String pageUrl) {
    this.pageUrl = pageUrl;
  }
}
