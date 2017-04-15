<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>

<form action="/admin/rule/index" method="get">
    <table id="ruleSearchTable" class="table table-bordered" style="display: none">
        <thead>
        <tr>
            <td>关键字</td>
            <td>解析类型</td>
            <td>页面类型</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tr>
            <td><input type="text" id="searchWord" name="searchWord" style="width:98%" tabindex="2"></td>
            <td>
                <select name="parserType" style="width: auto">
                    <option value=''>ALL</option>
                    <#list parserTypeStrList as parserTypeStr>
                        <option value='${parserTypeStr}'>${parserTypeStr}</option>
                    </#list>
                </select>
            </td>
            <td>
                <select name="pageType" style="width: auto">
                    <option value=''>ALL</option>
                    <#list pageTypeStrList as pageTypeStr>
                        <option value='${pageTypeStr}'>${pageTypeStr}</option>
                    </#list>
                </select>
            </td>
            <td>
                <select name="state" style="width: auto">
                    <option value=''>ALL</option>
                    <#list stateStrList as stateStr>
                        <option value='${stateStr}'>${stateStr}</option>
                    </#list>
                </select>
            </td>
            <td style="text-align:left">
                <button type="submit" class="btn btn-primary">search</button>
            </td>
        </tr>
    </table>
</form>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a>Rule</a>
                <button onclick="toggleRuleSearch();" style="float:right;">Search</button>
            </h4>
        </div>

        <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>id</th>
                <th>模式</th>
                <th>解析类型</th>
                <th>页面类型</th>
                <th>状态</th>
                <th>详情</th>
                <th>测试</th>
                <th>删除</th>
            </tr>
        </thead>
        <#if (rules?size <= 0)>
            <tr><td style="text-align:center" colspan="8"><h4>没有Rule纪录</h4></td></tr>
        <#else>
            <#list rules as rule>
                <tr>
                    <td>${rule.id}</td>
                    <td style="word-break:break-all">${rule.pattern!""}</td>
                    <td>${rule.parserType!""}</td>
                    <td>${rule.pageType!""}</td>
                    <td>${rule.state!""}</td>
                    <td>
                        <form id="ruleShow" action="/admin/rule/${rule.id}" method="get" style="display:inline">
                            <button type="submit" class="btn btn-primary">Detail</button>
                        </form>
                    </td>
                    <td>
                        <form id="ruleTest" action="/admin/rule/${rule.id}/test#RuleTest" method="get" style="display:inline">
                            <button type="submit" class="btn btn-primary">测试</button>
                        </form>
                    </td>
                    <td>
                        <form id="ruleDelete" action="/admin/rule/${rule.id}" method="post" style="display:inline">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">删除</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </#if>
        </table>
    </div>
    <div class="sourcePage">
        <@pagination.show pageNation.pageNum pageNation.pageTotal pageNation.pageUrl/>
    </div>
</div>
<#include "../footer.ftl">