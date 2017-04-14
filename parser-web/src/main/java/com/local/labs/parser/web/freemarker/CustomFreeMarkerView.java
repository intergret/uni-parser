package com.local.labs.parser.web.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;

public class CustomFreeMarkerView extends FreeMarkerView {

  protected SimpleHash buildTemplateModel(Map<String,Object> model, HttpServletRequest request,
      HttpServletResponse response) {
    SimpleHash fmModel = super.buildTemplateModel(model, request, response);
    fmModel.put("RawRequest", request);
    return fmModel;
  }
}