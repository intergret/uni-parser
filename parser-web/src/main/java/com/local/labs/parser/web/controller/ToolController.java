package com.local.labs.parser.web.controller;

import java.io.IOException;

import com.local.labs.parser.common.model.crawler.HttpMethod;
import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTestCase;
import com.local.labs.parser.common.serializer.JsonSerializer;
import com.local.labs.parser.engine.core.TextParser;
import com.local.labs.parser.service.RuleService;
import com.local.labs.parser.web.consts.PageConst;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-11 Time: 18:14
 */
@Controller
@RequestMapping("/")
public class ToolController {

  @Autowired
  private JsonSerializer serializer;

  @Autowired
  private TextParser textParser;

  @Autowired
  private RuleService ruleService;

  @RequestMapping(value = "/admin/rule/{ruleId:.+}/test", method = RequestMethod.GET)
  public ModelAndView testAction(final ModelMap model, @PathVariable final Long ruleId) {
    Rule rule = ruleService.get(ruleId);
    RuleTestCase testCase = ruleService.getTestCase(ruleId);
    String testUrl = StringUtils.EMPTY;
    String testContent = StringUtils.EMPTY;
    String testReferer = StringUtils.EMPTY;
    String testRefererInfo = StringUtils.EMPTY;
    String testHttpMethod = HttpMethod.GET.name();
    String testHeaders = StringUtils.EMPTY;
    String testForms = StringUtils.EMPTY;

    if (testCase != null) {
      testUrl = testCase.getUrl();
      testContent = testCase.getTestContent();
      testReferer = testCase.getRefer();
      testRefererInfo = testCase.getReferInfo();
      testHttpMethod = testCase.getHttpMethod().name();
      testHeaders = testCase.getHeaders();
      testForms = testCase.getForms();
    }

    model.put("rule", rule);
    model.put("testUrl", testUrl);
    model.put("testContent", testContent);
    model.put("testReferInfo", testRefererInfo);
    model.put("testRefer", testReferer);
    model.put("testReferInfo", testRefererInfo);
    model.put("testHttpMethod", testHttpMethod);
    model.put("testHeaders", testHeaders);
    model.put("testForms", testForms);
    model.put("httpMethodStrList", PageConst.HTTP_METHOD_LIST);
    return new ModelAndView("/admin/rule/rule-test", model);
  }

  @RequestMapping(value = "/admin/rule/{ruleId:.+}/test", method = RequestMethod.POST)
  public ModelAndView testAction(final ModelMap model, @PathVariable final Long ruleId,
      @RequestParam(value = "testUrl", required = true) String testUrl,
      @RequestParam(value = "testContent", required = true) String testContent,
      @RequestParam(value = "testHttpMethod", required = true) HttpMethod testHttpMethod,
      @RequestParam(value = "testRefer", required = false, defaultValue = "") String testRefer,
      @RequestParam(value = "testForms", required = false, defaultValue = "") String testForms) {
    RuleTestCase testCase = new RuleTestCase();
    testCase.setRuleId(ruleId);
    testCase.setUrl(testUrl);
    testCase.setTestContent(testContent);
    testCase.setRefer(testRefer);
    testCase.setHttpMethod(testHttpMethod);
    testCase.setForms(testForms);
    ruleService.upsertTestCase(testCase);

    ParserTask task = new ParserTask();
    task.setUrl(testUrl);
    task.setRefer(testRefer);
    task.setContent(testContent);

    String parseRule;
    String parseResult;
    try {
      ParseData parseData = textParser.parse(task);
      parseResult = serializer.serializeWithPretty(parseData);
      parseRule = serializer.serializeWithPretty(parseData.getRule());
    } catch (IOException e) {
      parseRule = e.toString();
      parseResult = e.toString();
    }
    model.put("parseRule", parseRule);
    model.put("parseResult", parseResult);
    return new ModelAndView("/admin/rule/rule-test-result", model);
  }
}
