<#include "../header.ftl">
<div align="center"><h2>Rule Test</h2></div>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Rule</h4>
        </div>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="width:25%">id:</td>
                <td>${rule.id}</td>
            </tr>
            <tr>
                <td>简介(description):</td>
                <td>${rule.description!""}</td>
            </tr>
            <tr>
                <td>模式(pattern):</td>
                <td style="word-break:break-all; word-wrap:break-word;">
                ${rule.pattern!""}
                </td>
            </tr>
            <tr>
                <td>实例(instance):</td>
                <td>${rule.instance!""}</td>
            </tr>
            <tr>
                <td>解析类型(parserType):</td>
                <td>${rule.parserType!""}</td>
            </tr>
            <tr>
                <td>页面类型(pageType):</td>
                <td>${rule.pageType!""}</td>
            </tr>
            <tr>
                <td>状态(state):</td>
                <td>${rule.state!""}</td>
            </tr>
            <tr>
                <td>作者(author):</td>
                <td>${rule.author!""}</td>
            </tr>
        </table>
    </div>
</div>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="RuleTest">Rule测试</a>
                <button onclick="activateTestHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered table-striped">
            <form name="testForm" action="/admin/rule/${rule.id}/test" method="post" onsubmit="return submitTest();">
                <tr>
                    <td style="width:25%"><span class="testUrl">url*:</span></td>
                <#if testUrl?has_content>
                    <td><input type="text" name="testUrl" style="width:95%" value=${testUrl!""}></td>
                <#else>
                    <td><input type="text" name="testUrl" style="width:95%" value=${rule.instance!""}></td>
                </#if>
                </tr>
                <tr>
                    <td><span class="testHttpMethod">请求方式:</span></td>
                    <td>
                        <select id="testHttpMethod" name="testHttpMethod" onchange="toggleTestSetting();">
                        <#list httpMethodStrList as httpMethodStr>
                            <option value='${httpMethodStr}'
                            <#if (testHttpMethod?? && testHttpMethod == "${httpMethodStr}") >selected="selected"</#if>>${httpMethodStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="testContent">测试内容*:</span></td>
                    <td><textarea name="testContent" rows="4" style="width:95%">${testContent!""}</textarea></td>
                </tr>
                <tr>
                    <td><span class="testRefer">引用页(refer):</span></td>
                    <td><input type="text" name="testRefer" style="width:95%" value=${testReferer!""}></td>
                </tr>
                <tr id="testFormsSetting" style="display: none">
                    <td><span class="testForms">Post表单:</span></td>
                    <td><textarea name="testForms" rows="4" style="width:95%">${testForms!""}</textarea></td>
                </tr>
                <tr>
                    <td style="text-align:left">
                        <a class="btn btn-primary" href="/admin/rule/${rule.id}#Rule">返回Rule</a>
                    </td>
                    <td style="text-align:right">
                        <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                    </td>
                </tr>
            </form>
        </table>
    </div>
</div>
<#include "../footer.ftl">
