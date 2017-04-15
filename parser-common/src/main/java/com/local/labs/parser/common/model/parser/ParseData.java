package com.local.labs.parser.common.model.parser;

import java.util.ArrayList;
import java.util.List;

import com.local.labs.parser.common.model.crawler.CrawlerTask;
import com.local.labs.parser.common.model.parser.rule.Rule;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:48
 */
public class ParseData {

  private List<NodeResult> nodeResults = new ArrayList<>();

  private List<CrawlerTask> crawlerTasks = new ArrayList<>();

  private Rule rule;

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
