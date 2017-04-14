package com.local.labs.parser.common.model.parser.rule;

public class PropResult {

  private Prop prop;

  private Object value;

  public PropResult(Prop prop, Object value) {
    super();
    this.prop = prop;
    this.value = value;
  }

  public Prop getProp() {
    return prop;
  }

  public void setProp(Prop prop) {
    this.prop = prop;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
