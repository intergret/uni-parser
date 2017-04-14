<#include "../header.ftl">
<div align="center"><h2>Add Rule</h2></div>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="AddRule">新增Rule</a>
                <button onclick="activateRuleHelp()" style="float:right;">Help</button>
            </h4>
        </div>
        <div>
            <form name ="ruleForm" action="/admin/rule/" method="post" onsubmit="return submitRule();">
                <table class="table table-bordered">
                    <tr>
                        <td style="width:30%">简介(description)*:</td>
                        <td><input type="text" name="description" style="width:95%"/></td>
                    </tr>
                    <tr>
                        <td><span class="pattern">模式(pattern)*</span></td>
                        <td><input type="text" name="pattern" style="width:95%"/></td>
                    </tr>
                    <tr>
                        <td><span class="instance">实例(instance)*:</span></td>
                        <td><input type="text" name="instance" style="width:95%"/></td>
                    </tr>
                    <tr>
                        <td><span class="parserType">解析类型(parserType):</span></td>
                        <td>
                            <select name="parserType">
                            <#list parserTypeStrList as parserTypeStr>
                                <option value='${parserTypeStr}'>${parserTypeStr}</option>
                            </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="pageType">页面类型(pageType):</span></td>
                        <td>
                            <select name="pageType">
                            <#list pageTypeStrList as pageTypeStr>
                                <option value='${pageTypeStr}'>${pageTypeStr}</option>
                            </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>作者(author)*:</td>
                        <td><input type="text" name="author"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:right">
                            <button type="submit" class="btn btn-primary" style="margin-right:15px">Submit</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<#include "../footer.ftl">
