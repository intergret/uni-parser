<#include "../header.ftl">
<div align="center"><h2>ExtraConfig Location Info</h2></div>

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
                <td>标签名(label)</td>
                <td>${node.label!""}</td>
            </tr>
            <tr>
                <td>节点类型(nodeType):</td>
                <td>${node.nodeType!""}</td>
            </tr>
            <tr>
                <td>父节点ID(parentNode):</td>
                <td>${node.parentNode!""}</td>
            </tr>
            <tr>
                <td>配置项:</td>
                <td>
                    <table class="table table-bordered table-striped">
                        <tr>
                            <td style="width:30%">配置项id(extraConfigId):</td>
                            <td>${nodeExtraConfig.id}</td>
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
        <table class="table table-bordered">
            <tr>
                <td style="width:30%">id:</td>
                <td>${prop.id}</td>
            </tr>
            <tr>
                <td>标签名(label):</td>
                <td>${prop.label!""}</td>
            </tr>
            <tr>
                <td>是否必须(isRequired):</td>
                <td>${isRequiredStr}</td>
            </tr>
            <tr>
                <td>是否为多个(isMultiply):</td>
                <td>${isMultiplyStr}</td>
            </tr>
            <tr>
                <td>导出类型(resultType):</td>
                <td>${prop.resultType!""}</td>
            </tr>
            <tr>
                <td>Link配置项:</td>
                <td>
                    <table class="table table-bordered table-striped">
                        <tr>
                            <td style="width:30%">请求方式(httpMethod):</td>
                            <td>${prop.httpMethod!""}</td>
                        </tr>
                        <tr>
                            <td>解析类型(parserType):</td>
                            <td>${prop.parserType!""}</td>
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
            <h4 class="panel-title">Rule->Node->Property->ExtraConfig</h4>
        </div>
    <#if (extraConfigList?size <= 0)>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="text-align:center"><h4>没有ExtraConfig的纪录</h4></td>
            </tr>
        </table>
    <#else>
        <table class="table table-bordered">
            <thead>
            <tr>
                <td>id</td>
                <td>输入</td>
                <td>条件</td>
                <td>选项</td>
                <td>解释器类型</td>
                <td>查看</td>
            </tr>
            </thead>
            <#list extraConfigList as extraConfigItem>
                <tr>
                    <td>${extraConfigItem.id}</td>
                    <td>${extraConfigItem.input!""}</td>
                    <td>${extraConfigItem.cond!""}</td>
                    <td>${extraConfigItem.value!""}</td>
                    <td>${extraConfigItem.extractorType!""}</td>
                    <td>
                        <form id="extraConfigShow" action="/admin/rule/extraConfig/${extraConfigItem.id}#ExtraConfig"
                                method="get" style="display:inline">
                            <input id="propId" name="propId" type="hidden" value='${prop.id}'/>
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
<div align="center"><h2>Add ExtraConfig</h2></div>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="AddExtraConfig">新增ExtraConfig</a>
                <button onclick="activateExtraConfigHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered">
            <form name="extraConfigAddForm" action="/admin/rule/extraConfig" method="post">
                <tr>
                    <td style="width:30%"><span class="inputType">输入类型(inputType):</span></td>
                    <td>
                        <select name="inputType">
                        <#list extractorInputTypeStrList as extractorInputTypeStr>
                            <option value='${extractorInputTypeStr}'>${extractorInputTypeStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="transformType">值转换类型(transformType):</span></td>
                    <td>
                        <select name="transformType">
                        <#list transformTypeStrList as transformTypeStr>
                            <option value='${transformTypeStr}'>${transformTypeStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="extractorType">解释器类型(extractorType):</span></td>
                    <td>
                        <select id="extractorType" name="extractorType" onchange="toggleExtractorSetting();">
                        <#list extractorTypeStrList as extractorTypeStr>
                            <option value='${extractorTypeStr}'
                                <#if (defaultExtractorType?? && extractorTypeStr == "${defaultExtractorType}" ) >selected="selected"</#if>>${extractorTypeStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="condition">条件(condition):</span></td>
                    <td>
                        <textarea name="condition" rows="1" style="width:98%"></textarea>
                        <span id="conditionTips"></span>
                    </td>
                </tr>
                <tr>
                    <td><span class="value">选项(value):</span></td>
                    <td>
                        <textarea name="valueStr" rows="1" style="width:98%"></textarea>
                        <span id="valueTips"></span>
                    </td>
                </tr>
                <tr>
                    <td><span class="refExtraConfigId">前置配置项(refExtraConfigId):</span></td>
                    <td>
                        <select name="refExtraConfigId">
                        <#list refExtraConfigStrList as refExtraConfigStr>
                            <option value='${refExtraConfigStr}'
                                    <#if ("${refExtraConfigStr}" == "${suggestRefExtraConfig}" ) >selected="selected"</#if>>${refExtraConfigStr}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:left">
                        <a class="btn btn-primary" href="/admin/rule/${rule.id}#Rule">返回Rule</a>
                        <a class="btn btn-primary" href="/admin/rule/node/${node.id}#Node">返回Node</a>
                        <a class="btn btn-primary" href="/admin/rule/prop/${prop.id}#Property">返回Prop</a>
                    </td>
                    <td style="text-align:right">
                        <button type="submit" class="btn btn-primary" onClick="submitAddExtraConfig('prop')"
                                style="margin-right:15px">
                            Submit And Add Prop
                        </button>
                        <button type="submit" class="btn btn-primary" onClick="submitAddExtraConfig('extraConfig')"
                                style="margin-right:15px">
                            Submit And Add ExtraConfig
                        </button>
                    </td>
                </tr>
                <input type="hidden" name="redirectPage"/>
                <input type="hidden" name="ruleId" value='${rule.id}'/>
                <input type="hidden" name="nodeId" value='${node.id}'/>
                <input type="hidden" name="propId" value='${prop.id}'/>
            </form>
        </table>
    </div>
</div>

<br><br><br><br><br><br>
<#include "../footer.ftl">
