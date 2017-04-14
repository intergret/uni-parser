package com.local.labs.parser.service;

import java.util.List;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;

public interface ExtraConfigService {

  ExtraConfig get(Long id);

  void update(ExtraConfig extraConfig);

  long insert(ExtraConfig extraConfig);

  void delete(ExtraConfig extraConfig);

  List<ExtraConfig> listByProp(Long propId);

}
