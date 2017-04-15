<#include "../header.ftl">
<div align="center"><h2> Prop Configure Detail</h2></div>

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
            <h4 class="panel-title">Rule->Node</h4>
        </div>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="width:30%">id:</td>
                <td>${node.id}</td>
            </tr>
            <tr>
                <td>标签名(label):</td>
                <td>${node.label!""}</td>
            </tr>
            <tr>
                <td>节点类型(nodeType):</td>
                <td>${node.nodeType!""}</td>
            </tr>
            <tr>
                <td>父节点(parentNode):</td>
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
            <h4 class="panel-title">
                <a name="Property">Rule->Node->Property</a>
                <button onclick="activatePropHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered table-striped">
            <form name="propForm" action="/admin/rule/prop/${prop.id}" method="post" onsubmit="return submitProp();">
                <tr>
                    <td style="width:30%">id:</td>
                    <td>${prop.id}</td>
                </tr>
                <tr>
                    <td><span class="propLable">标签(label)*:</span></td>
                    <td><input type="text" name="label" value='${prop.label!""}'></td>
                </tr>
                <tr>
                    <td><span class="isRequired">是否必须(isRequired):</span></td>
                    <td>
                        <select name="isRequired">
                            <option value="0"
                            <#if (isRequiredStr?? && isRequiredStr == "否" ) >selected="selected"</#if>>否
                            </option>
                            <option value="1"
                            <#if (isRequiredStr?? && isRequiredStr == "是" ) >selected="selected"</#if>>是
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="isMultiply">是否为多个(isMultiply):</span></td>
                    <td>
                        <select name="isMultiply">
                            <option value="0"
                            <#if (isMultiplyStr?? && isMultiplyStr == "否" ) >selected="selected"</#if>>否
                            </option>
                            <option value="1"
                            <#if (isMultiplyStr?? && isMultiplyStr == "是" ) >selected="selected"</#if>>是
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="scopeType">可见范围(scopeType):</span></td>
                    <td>
                        <select name="scopeType">
                        <#list scopeTypeStrList as scopeTypeItem>
                            <option value='${scopeTypeItem}'
                                <#if (prop.scopeType?? && prop.scopeType == "${scopeTypeItem}") >selected="selected"</#if>>${scopeTypeItem}
                            </option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="resultType">导出类型(resultType):</span></td>
                    <td>
                        <select id="resultType" name="resultType" onchange="toggleLinkSetting();">
                        <#list resultTypeStrList as resultTypeItem>
                            <option value='${resultTypeItem}'
                                <#if (prop.resultType?? && prop.resultType == "${resultTypeItem}" ) >selected="selected"</#if>>${resultTypeItem}
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
                                <td><span class="httpMethod">请求方式(httpMethod):</span></td>
                                <td>
                                    <select name="httpMethod">
                                        <option value="" selected="selected"></option>
                                        <#list httpMethodStrList as httpMethodItem>
                                            <option value='${httpMethodItem}'
                                                <#if (prop.httpMethod?? && prop.httpMethod == "${httpMethodItem}") >selected="selected"</#if>>${httpMethodItem}
                                            </option>
                                        </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="refer">引用页(refer):</span></td>
                                <td><input type="text" name="refer" value='${prop.refer!""}'></td>
                            </tr>
                            <tr>
                                <td><span class="parserType">解析类型(parserType):</span></td>
                                <td>
                                    <select name="parserType">
                                        <#list parserTypeStrList as parserTypeItem>
                                            <option value='${parserTypeItem}'
                                                <#if (prop.parserType?? && prop.parserType == "${parserTypeItem}") >selected="selected"</#if>>${parserTypeItem}
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
                        <a class="btn btn-primary" href="/admin/rule/${rule.id}#Rule">返回Rule页</a>
                        <a class="btn btn-primary" href="/admin/rule/node/${node.id}#Node">返回Node页</a>
                    </td>
                    <td style="text-align:right">
                        <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                    </td>
                </tr>
                <input type="hidden" name="ruleId" value='${rule.id}'/>
                <input type="hidden" name="nodeId" value='${node.id}'/>
                <input type="hidden" name="propId" value='${prop.id}'/>
                <input type="hidden" name="_method" value="put"/>
            </form>
        </table>
    </div>
</div>

<#if (updateState ??)>
    <#if (updateState ?? && updateState == "success")>
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Property Success!</strong>
    </div>
    <#elseif (updateState ?? && updateState == "failed")>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Update Property Failed!</strong>
    </div>
    </#if>
</#if>

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
            <tr>
                <td style="text-align:center">
                    <a href="/admin/rule/extraConfig/skeleton?propId=${prop.id}#AddExtraConfig"><h4>新增ExtraConfig</h4></a>
                </td>
            </tr>
        </table>
    <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>输入</th>
                <th>值转换类型</th>
                <th>解释器类型</th>
                <th>条件</th>
                <th>选项</th>
                <th>前置配置项</th>
                <th>查看</th>
                <th>删除</th>
            </tr>
            </thead>
            <#list extraConfigList as extraConfig>
                <tr>
                    <td>${extraConfig.id}</td>
                    <td>${extraConfig.inputType!""}</td>
                    <td>${extraConfig.transformType!""}</td>
                    <td>${extraConfig.extractorType!""}</td>
                    <td>${extraConfig.cond!""}</td>
                    <td>${extraConfig.value!""}</td>
                    <td>${extraConfig.refExtraConfigId!""}</td>
                    <td>
                        <form id="extraConfigShow" action="/admin/rule/extraConfig/${extraConfig.id}#ExtraConfig"
                                method="get" style="display:inline">
                            <input type="hidden" name="propId" value='${prop.id}'/>
                            <button type="submit" class="btn btn-primary">Detail</button>
                        </form>
                    </td>
                    <td>
                        <form id="extraConfigDelete" action="/admin/rule/extraConfig/${extraConfig.id}"
                                method="post" style="display:inline">
                            <input type="hidden" name="ruleId" value='${rule.id}'/>
                            <input type="hidden" name="nodeId" value='${node.id}'/>
                            <input type="hidden" name="propId" value='${prop.id}'/>
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
            <tr>
                <td colspan="9" style="text-align:center">
                    <a href="/admin/rule/extraConfig/skeleton?propId=${prop.id}#AddExtraConfig"><h4>新增ExtraConfig</h4></a>
                </td>
            </tr>
        </table>
    </#if>
    </div>
</div>

<br><br><br><br><br><br>
<#include "../footer.ftl">
