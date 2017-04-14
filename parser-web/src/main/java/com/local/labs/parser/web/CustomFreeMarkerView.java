package com.local.labs.parser.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import freemarker.template.SimpleHash;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class CustomFreeMarkerView extends FreeMarkerView {

  protected SimpleHash buildTemplateModel(Map<String,Object> model, HttpServletRequest request,
      HttpServletResponse response) {
    SimpleHash fmModel = super.buildTemplateModel(model, request, response);
    fmModel.put("RawRequest", request);
    return fmModel;
  }
}