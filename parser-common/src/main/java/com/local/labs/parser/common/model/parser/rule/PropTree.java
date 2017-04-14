package com.local.labs.parser.common.model.parser.rule;

import java.util.ArrayList;
import java.util.List;

public class PropTree {

  Prop prop;

  List<ExtraConfig> extraConfigs;

  public PropTree() {}

  public PropTree(Prop prop) {
    this.prop = prop;
    this.extraConfigs = new ArrayList<>();
  }

  public Prop getProp() {
    return prop;
  }

  public void setProp(Prop prop) {
    this.prop = prop;
  }

  public List<ExtraConfig> getExtraConfigs() {
    return extraConfigs;
  }

  public void setExtraConfigs(List<ExtraConfig> extraConfigs) {
    this.extraConfigs = extraConfigs;
  }
}
