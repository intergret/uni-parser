<#include "../header.ftl">
<div align="center"><h2>XPath LookUp</h2></div>
<form action="/admin/tool/xpath/lookup" method="post">
    <table class="table table-bordered table-striped">
        <tr>
            <td>Xml:</td>
            <td><textarea name="xml" rows="15" style="width:98%">${xml!""}</textarea></td>
        </tr>
        <tr>
            <td>关键字:</td>
            <td><input type="text" name="searchWord" style="width:98%" value=${searchWord!""}></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:right">
                <button type="submit" class="btn btn-primary">Submit</button>
            </td>
        </tr>
    </table>
</form>

<table class="table table-bordered table-striped">
<div align="center"><h2>XPath Find</h2></div>
<#if (xpathList?size > 0)>
    <#list xpathList?keys as xpath>
        <#list xpathList[xpath] as content>
            <tr>
                <td colspan="2">XPATH: ${xpath} </br>VALUE: ${content}</td>
            </tr>
        </#list>
    </#list>
<#else>
    <tr>
        <td colspan="2" style="text-align:center">没有找到对应XPath</td>
    </tr>
</#if>
</table>

<#include "../footer.ftl">