<#include "../header.ftl">
<div align="center"><h2>Property Location Info</h2></div>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Rule</h4>
        </div>
        <table class="table table-bordered">
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
            <h4 class="panel-title">Rule->Node</h4>
        </div>
        <table class="table table-bordered">
            <tr>
                <td style="width:30%">id:</td>
                <td>${node.id}</td>
            </tr>
            <tr>
                <td>节点类型(nodeType):</td>
                <td>${node.nodeType!""}</td>
            </tr>
            <tr>
                <td>标签名(label):</td>
                <td>${node.label!""}</td>
            </tr>
            <tr>
                <td>父节点ID(parentNode):</td>
                <td>${node.parentNode!""}</td>
            </tr>
            <tr>
                <td style="width:30%">配置项:</td>
                <td>
                    <table class="table table-bordered table-striped">
                        <tr>
                            <td style="width:30%">配置项id(extraConfigId):</td>
                            <td>${nodeExtraConfig.id}</td>
                        </tr>
                        <tr>
                            <td>输入类型(inputType):</td>
                            <td>${nodeExtraConfig.inputType!""}</td>
                        </tr>
                        <tr>
                            <td>条件(condition):</td>
                            <td>${nodeExtraConfig.cond!""}</td>
                        </tr>
                        <tr>
                            <td>选项(value):</td>
                            <td>${nodeExtraConfig.value!""}</td>
                        </tr>
                        <tr>
                            <td>解析器类型(extractorType):</td>
                            <td>${nodeExtraConfig.extractorType!""}</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>

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
        </table>
    <#else>
        <table class="table table-bordered">
            <thead>
            <td>id</td>
            <td>标签</td>
            <td>导出类型</td>
            <th>是否必须</th>
            <td>是否为多个</td>
            <th>详情</th>
            </thead>
            <#list propList as prop>
                <tr>
                    <td>${prop.id}</td>
                    <td>${prop.label!""}</td>
                    <td>${prop.resultType!""}</td>
                    <td>${isRequiredStrList[prop_index]}</td>
                    <td>${isMultiplyStrList[prop_index]}</td>
                    <td>
                        <form id="propShow" action="/admin/rule/prop/${prop.id}#Property" method="get"
                                style="display:inline">
                            <button type="submit" class="btn btn-primary">Detail</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </table>
    </#if>
    </div>
</div>

<ul class="nav nav-tabs"></ul>
<div align="center"><h2>Add Property Configure</h2></div>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="AddProp">新增Property</a>
                <button onclick="activatePropHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered">
            <form name="propForm" action="/admin/rule/prop" method="post" onsubmit="return submitProp();">
                <tr>
                    <td><span class="propLable">标签(label)*:</span></td>
                    <td>
                        <input type="text" name="label"/>
                    </td>
                </tr>
                <tr>
                    <td><span class="isRequired">是否必须(isRequired):</span></td>
                    <td>
                        <select name="isRequired">
                            <option value="1">是</option>
                            <option value="0" selected="selected">否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="isMultiply">是否为多个(isMultiply):</span></td>
                    <td>
                        <select name="isMultiply">
                            <option value="0" selected="selected">否</option>
                            <option value="1">是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="resultType">导出类型(resultType):</span></td>
                    <td>
                        <select id="resultType" name="resultType" onchange="toggleLinkSetting();">
                        <#list resultTypeStrList as resultTypeStr>
                            <option value='${resultTypeStr}'
                                <#if ('${resultTypeStr}'?? && '${resultTypeStr}' == "TEXT") >selected="selected"</#if>>${resultTypeStr}
                            </option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr id="linkSetting">
                    <td>Link配置项:</td>
                    <td>
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td><span class="httpMethod">请求类型(httpMethod):</span></td>
                                <td>
                                    <select name="httpMethod">
                                        <option value="" selected="selected"></option>
                                        <#list httpMethodStrList as httpMethodStr>
                                            <option value='${httpMethodStr}'
                                                <#if ('${httpMethodStr}'?? && '${httpMethodStr}' == "GET") >selected="selected"</#if>>${httpMethodStr}
                                            </option>
                                        </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="parserType">解析类型(parserType):</span></td>
                                <td>
                                    <select name="parserType">
                                    <#list parserTypeStrList as parserTypeStr>
                                        <option value='${parserTypeStr}'
                                            <#if ('${parserTypeStr}'?? && '${parserTypeStr}' == "IMAGE_MAGICK") >selected="selected"</#if>>${parserTypeStr}
                                        </option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:left">
                        <a class="btn btn-primary" href="/admin/rule/${rule.id}#Rule">返回Rule</a>
                        <a class="btn btn-primary" href="/admin/rule/node/${node.id}#Node">返回Node</a>
                    </td>
                    <td style="text-align:right">
                        <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                    </td>
                </tr>
                <input type="hidden" name="ruleId" value='${rule.id}'/>
                <input type="hidden" name="nodeId" value='${node.id}'/>
            </form>
        </table>
    </div>
</div>
<br><br><br><br><br><br>
<#include "../footer.ftl">

