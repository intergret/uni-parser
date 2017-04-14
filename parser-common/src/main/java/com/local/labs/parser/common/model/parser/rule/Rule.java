package com.local.labs.parser.common.model.parser.rule;

import com.local.labs.parser.common.model.State;
import com.local.labs.parser.common.model.parser.PageType;
import com.local.labs.parser.common.model.parser.ParserType;

public class Rule {

  private Long id;

  private String pattern;

  private String instance;

  private ParserType parserType;

  private PageType pageType;

  private State state;

  private String description;

  private String author;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public String getInstance() {
    return instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }

  public ParserType getParserType() {
    return parserType;
  }

  public void setParserType(ParserType parserType) {
    this.parserType = parserType;
  }

  public PageType getPageType() {
    return pageType;
  }

  public void setPageType(PageType pageType) {
    this.pageType = pageType;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public Rule clone() throws CloneNotSupportedException {
    Rule rule = (Rule) super.clone();
    return rule;
  }
}
