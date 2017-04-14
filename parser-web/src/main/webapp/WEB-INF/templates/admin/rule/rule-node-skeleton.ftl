<#include "../header.ftl">
<div align="center"><h2>Node Location Info</h2></div>
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
                <td>文档类型(docType):</td>
                <td>${rule.docType!""}</td>
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
    <#if (nodeList?size <= 0)>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="text-align:center"><h4>没有Node的纪录</h4></td>
            </tr>
        </table>
    <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <th>id</th>
            <th>标签名</th>
            <th>节点类型</th>
            <th>父节点</th>
            <th>查看</th>
            </thead>
            <#list nodeList as node>
                <tr>
                    <td>${node.id}</td>
                    <td>${node.label!""}</td>
                    <td>${node.nodeType}</td>
                    <td>${node.parentNode}</td>
                    <td>
                        <form id="nodeShow" action="/admin/rule/node/${node.id}#Node" method="get"
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
<div align="center"><h2>Add Node Configure</h2></div>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="AddNode">新增Node</a>
                <button onclick="activateNodeHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <table class="table table-bordered table-striped">
            <form name="nodeForm" action="/admin/rule/node/" method="post" onsubmit="return submitNode();">
                <tr>
                    <td style="width:30%"><span class="nodeLable">标签名(label)*</span></td>
                    <td>
                        <input type="text" name="label"/>
                    </td>
                </tr>
                <tr>
                    <td><span class="nodeType">节点类型(nodeType):</span></td>
                    <td>
                        <select name="nodeType">
                            <option value="single">single</option>
                            <option value="multiply" <#if (rule?? && rule.pageType == "LIST" ) >selected="selected"</#if>>multiply</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span class="parentNode">父节点(parentNode):</span></td>
                    <td>
                        <select name="parentNodeId">
                        <#list parentNodeStrList as parentNodeStr>
                            <option value='${parentNodeStr}'>${parentNodeStr}</option>
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
                                        <option value='${extractorInputTypeStr}'>${extractorInputTypeStr}</option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="inputOption">输入选项(inputOption):</span></td>
                                <td>
                                    <input type="text" name="inputOption" style="width:98%"/>
                                </td>
                            </tr>
                            <tr>
                                <td  style="width:30%"><span class="extractorType">类型(extractorType):</span></td>
                                <td>
                                    <select id="extractorType" name="extractorType" onchange="toggleExtractorSetting();">
                                    <#list extractorTypeStrList as extractorTypeStr>
                                        <option value='${extractorTypeStr}'>${extractorTypeStr}</option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="condition">条件(condition):</span></td>
                                <td>
                                    <textarea name="cond" rows="1" style="width:98%"></textarea>
                                    <span id="conditionTips"></span>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="value">选项(value):</span></td>
                                <td>
                                    <input type="text" name="value" style="width:98%"/>
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
            </form>
        </table>
    </div>
</div>

<br><br><br><br><br><br>
<#include "../footer.ftl">
