<#include "../header.ftl">
<div align="center"><h2> Node Configure Detail</h2></div>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Rule</h4>
        </div>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="width:30%">id:</td>
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
                <a name="Node">Rule->Node</a>
                <button onclick="activateNodeHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered table-striped">
            <form name="nodeForm" action="/admin/rule/node/${node.id}" method="post" onsubmit="return submitNode();">
                <tr>
                    <td style="width:30%">id:</td>
                    <td>${node.id}</td>
                </tr>
                <tr>
                    <td><span class="nodeLable">标签名(label)*</span></td>
                    <td><input type="text" name="label" value='${node.label!""}'></td>
                </tr>
                <tr>
                    <td><span class="nodeType">节点类型(nodeType):</span></td>
                    <td>
                        <select name="nodeType">
                            <option value="single"
                            <#if (node.nodeType?? && node.nodeType == "single")>selected="selected"</#if>>single
                            </option>
                            <option value="multiply"
                            <#if (node.nodeType?? && node.nodeType == "multiply")>selected="selected"</#if>>multiply
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="parentNode">父节点(parentNode):</span></td>
                    <td>
                        <select name="parentNodeId">
                        <#list parentNodeStrList as parentNodeStr>
                            <option value='${parentNodeStr}'
                                <#if (node.parentNode?? && node.parentNode?string == "${parentNodeStr}" ) >selected="selected"</#if>>${parentNodeStr}
                            </option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>解释器配置项:</td>
                    <td>
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td><span class="inputType">输入类型(inputType):</span></td>
                                <td>
                                    <select name="inputType">
                                    <#list extractorInputTypeStrList as extractorInputTypeStr>
                                        <option value='${extractorInputTypeStr}'
                                            <#if (nodeExtraConfig.inputType?? && nodeExtraConfig.inputType == "${extractorInputTypeStr}" ) >selected="selected"</#if>>${extractorInputTypeStr}</option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:30%"><span class="extractorType">类型(extractorType):</span></td>
                                <td>
                                    <select id="extractorType" name="extractorType" onchange="toggleExtractorSetting();">
                                    <#list extractorTypeStrList as extractorTypeItem>
                                        <option value='${extractorTypeItem}'
                                            <#if (nodeExtraConfig.extractorType?? && nodeExtraConfig.extractorType == "${extractorTypeItem}" ) >selected="selected"</#if>>${extractorTypeItem}
                                        </option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="condition">条件(condition):</span></td>
                                <td>
                                    <textarea name="condition" rows="1" style="width:98%">${nodeExtraConfig.cond!""}</textarea>
                                    <span id="conditionTips"></span>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="value">选项(value):</span></td>
                                <td>
                                    <input type="text" name="valueStr" value='${nodeExtraConfig.value!""}' style="width:98%">
                                    <span id="valueTips"></span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:left">
                        <a class="btn btn-primary" href="/admin/rule/${rule.id}#Rule">返回Rule页</a>
                    </td>
                    <td style="text-align:right">
                        <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                    </td>
                </tr>
                <input type="hidden" name="ruleId" value='${rule.id}'/>
                <input type="hidden" name="nodeId" value='${node.id}'/>
                <input type="hidden" name="_method" value="put"/>
            </form>
        </table>
    </div>
</div>

<#if (updateState ??)>
    <#if (updateState ?? && updateState == "success")>
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Node Success!</strong>
    </div>
    <#elseif (updateState ?? && updateState == "failed")>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Node Failed!</strong>
    </div>
    </#if>
</#if>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Rule->Node->Property</h4>
        </div>
        <#if (propList?size <= 0)>
            <table class="table table-bordered table-striped">
                <tr>
                    <td style="text-align:center"><h4>没有Prop纪录</h4></td>
                </tr>
                <tr>
                    <td style="text-align:center">
                        <a href="/admin/rule/prop/skeleton?nodeId=${node.id}#AddProp"><h4>新增Prop</h4></a>
                    </td>
                </tr>
            </table>
        <#else>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>粘合标记</th>
                    <th>标签</th>
                    <th>引用页</th>
                    <th>导出类型</th>
                    <th>可见范围</th>
                    <th>是否必须</th>
                    <th>是否为多个</th>
                    <th>查看</th>
                    <th>删除</th>
                </tr>
                </thead>
                <#list propList as prop>
                    <tr>
                        <td>${prop.id}</td>
                        <td>${prop.glue!""}</td>
                        <td>${prop.label!""}</td>
                        <td>${prop.refer!""}</td>
                        <td>${prop.resultType!""}</td>
                        <td>${prop.scopeType!""}</td>
                        <td>${isRequiredStrList[prop_index]}</td>
                        <td>${isMultiplyStrList[prop_index]}</td>
                        <td>
                            <form id="propShow" action="/admin/rule/prop/${prop.id}#Property" method="get"
                                    style="display:inline">
                                <button type="submit" class="btn btn-primary">Detail</button>
                            </form>
                        </td>
                        <td>
                            <form id="propDelete" action="/admin/rule/prop/${prop.id}" method="post"
                                    style="display:inline">
                                <input id="ruleId" name="ruleId" type="hidden" value='${rule.id}'/>
                                <input id="nodeId" name="nodeId" type="hidden" value='${node.id}'/>
                                <input type="hidden" name="_method" value="delete"/>
                                <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">
                                    Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </#list>
                <tr>
                    <td colspan="10" style="text-align:center">
                        <a href="/admin/rule/prop/skeleton?nodeId=${node.id}#AddProp"><h4>新增Prop</h4></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="10" style="text-align:center">
                        <form id="propCopy" action="/admin/rule/prop" method="post" style="display:inline">
                            <input type="text" name="propIds" placeholder="PropId1,PropId2,..."/>
                            <input id="ruleId" name="ruleId" type="hidden" value='${rule.id}'/>
                            <input id="nodeId" name="nodeId" type="hidden" value='${node.id}'/>
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-primary" style="margin-right:15px">删除Prop</button>
                        </form>
                    </td>
                </tr>
            </table>
        </#if>
    </div>
</div>

<br><br><br><br><br><br>
<#include "../footer.ftl">
