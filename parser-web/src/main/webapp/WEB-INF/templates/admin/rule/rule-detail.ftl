<#include "../header.ftl">
<div align="center"><h2>Rule Configure Detail</h2></div>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="Rule">Rule</a>
                <button onclick="activateRuleHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered table-striped">
            <form name="ruleForm" action="/admin/rule/${rule.id}" method="post" onsubmit="return submitRule();">
                <tr>
                    <td style="width:30%">id:</td>
                    <td>${rule.id}</td>
                </tr>
                <tr>
                    <td>简介(description):</td>
                    <td><input type="text" name="description" value='${rule.description!""}' style="width:95%"/></td>
                </tr>
                <tr>
                    <td><span class="pattern">模式(pattern)</span></td>
                    <td><input type="text" name="pattern" value='${rule.pattern!""}' style="width:95%"/></td>
                </tr>
                <tr>
                    <td><span class="instance">实例(instance)*:</span></td>
                    <td><input type="text" name="instance" value='${rule.instance!""}' style="width:95%"/></td>
                </tr>
                <tr>
                    <td><span class="parserType">解析类型(parserType):</span></td>
                    <td>
                        <select name="parserType">
                        <#list parserTypeStrList as parserTypeItem>
                            <option value='${parserTypeItem}'
                                <#if (rule.parserType ?? && rule.parserType == "${parserTypeItem}")
                                >selected="selected"</#if>>${parserTypeItem}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="pageType">页面类型(pageType):</span></td>
                    <td>
                        <select name="pageType">
                        <#list pageTypeStrList as pageTypeItem>
                            <option value='${pageTypeItem}'
                                <#if (rule.pageType ?? && rule.pageType == "${pageTypeItem}" )
                                >selected="selected"</#if>>${pageTypeItem}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="state">状态(state):</span></td>
                    <td>
                        <select name="state">
                        <#list stateStrList as stateStr>
                            <option value='${stateStr}'
                                <#if (rule.state ?? && rule.state == "${stateStr}" )
                                >selected="selected"</#if>>${stateStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>作者(author):</td>
                    <td><input type="text" name="author" value='${rule.author!""}'/></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:right">
                        <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                    </td>
                </tr>
                <input type="hidden" name="ruleId" value='${rule.id}'/>
                <input type="hidden" name="_method" value="put"/>
            </form>
        </table>
    </div>
</div>

<#if (updateState ??)>
    <#if (updateState ?? && updateState == "success")>
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Rule Success!</strong>
    </div>
    <#elseif (updateState ?? && updateState == "failed")>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Rule Failed!</strong>
    </div>
    </#if>
</#if>

<table class="table table-bordered table-striped">
    <tr>
        <td style="text-align:right">
            <form id="ruleDelete" action="/admin/rule/${rule.id}" method="post" style="display:inline">
                <input type="hidden" name="_method" value="delete"/>
                <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">删除</button>
            </form>
            <form id="ruleCopy" action="/admin/rule/${rule.id}/copy" method="post" style="display:inline">
                <button type="submit" class="btn btn-primary" onClick="return confirm('确定复制吗?');">复制</button>
            </form>
            <form id="ruleExport" action="/admin/rule/${rule.id}/export" method="post" style="display:inline">
                <button type="submit" class="btn btn-primary" onClick="return confirm('确定导出吗?');">导出</button>
            </form>
            <form id="ruleTest" action="/admin/rule/${rule.id}/test#RuleTest" method="get" style="display:inline">
                <button type="submit" class="btn btn-primary" style="margin-right:15px">测试</button>
            </form>
        </td>
    </tr>
</table>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Rule->Node</h4>
        </div>
    <#if (nodelist?size <= 0)>
        <table class="table table-bordered table-striped">
            <tr><td style="text-align:center"><h4>没有Node的纪录</h4></td></tr>
            <tr><td style="text-align:center"><a href="/admin/rule/node/skeleton?ruleId=${rule.id}#AddNode"><h4>新增Node</h4></a></td></tr>
        </table>
    <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>标签名</th>
                <th>节点类型</th>
                <th>父节点ID</th>
                <th>查看</th>
                <th>删除</th>
            </tr>
            </thead>
            <#list nodelist as node>
                <tr>
                    <td>${node.id}</td>
                    <td>${node.label!""}</td>
                    <td>${node.nodeType!""}</td>
                    <td>${node.parentNode}</td>
                    <td>
                        <form id="nodeShow" action="/admin/rule/node/${node.id}#Node" method="get" style="display:inline">
                            <button type="submit" class="btn btn-primary">Detail</button>
                        </form>
                    </td>
                    <td>
                        <form id="nodeDelete" action="/admin/rule/node/${node.id}" method="post" style="display:inline">
                            <input id="ruleId" name="ruleId" type="hidden" value='${rule.id}'/>
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
            <tr><td colspan="8" style="text-align:center"><a href="/admin/rule/node/skeleton?ruleId=${rule.id}#AddNode"><h4>新增Node</h4></a></td></tr>
        </table>
    </#if>
    </div>
</div>
<#include "../footer.ftl">
