package com.local.labs.parser.common.model.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.crawler.CrawlerTask;
import com.local.labs.parser.common.model.parser.rule.Rule;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:48
 */
public class ParseData {

  private List<NodeResult> nodeResults = new ArrayList<>();

  private List<CrawlerTask> crawlerTasks = new ArrayList<>();

  private Map<String,String> contextInfo = new HashMap<>();

  private Map<String,String> deliveryInfo = new HashMap<>();

  private Map<String,String> responseHeaders = new HashMap<>();

  private Date time;

  private Rule rule;

  private String storagePath;

  public ParseData() {}

  public List<NodeResult> getNodeResults() {
    return nodeResults;
  }

  public void setNodeResults(List<NodeResult> nodeResults) {
    this.nodeResults = nodeResults;
  }

  public List<CrawlerTask> getCrawlerTasks() {
    return crawlerTasks;
  }

  public void setCrawlerTasks(List<CrawlerTask> crawlerTasks) {
    this.crawlerTasks = crawlerTasks;
  }

  public Rule getRule() {
    return rule;
  }

  public void setRule(Rule rule) {
    this.rule = rule;
  }
}
