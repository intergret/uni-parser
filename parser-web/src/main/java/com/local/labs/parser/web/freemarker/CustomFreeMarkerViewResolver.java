package com.local.labs.parser.web.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class CustomFreeMarkerViewResolver extends FreeMarkerViewResolver {

  public CustomFreeMarkerViewResolver() {
    setViewClass(requiredViewClass());
  }

  @Override
  protected Class requiredViewClass() {
    return CustomFreeMarkerView.class;
  }
}
