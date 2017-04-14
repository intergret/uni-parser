package com.local.labs.parser.web.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class UrlParamHelper implements TemplateMethodModel {

  @Override
  public Object exec(List args) throws TemplateModelException {
    // TODO Auto-generated method stub
    if (args != null && args.size() > 0) {
      String url = (String) args.get(0);
      String param = (String) args.get(1);
      String value = (String) args.get(2);
      int n = url.lastIndexOf("?");
      if (n == -1) {
        return url + "?" + param + "=" + value;
      }
      String queryString = url.substring(n + 1, url.length());
      String suburl = url.substring(0, n + 1);
      String[] strings = queryString.split("&");
      for (int i = 0; i < strings.length; i++) {
        String[] keyValues = strings[i].split("=");
        if (keyValues[0].equals(param)) {
          continue;
        }
        suburl += strings[i];
        suburl += "&";
      }
      return suburl + param + "=" + value;
    } else {
      return null;
    }
  }
}
