<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<div align="center"><h2>Rule Validate For Rule${ruleId}</h2></div>

<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>级别</th>
        <th>标签</th>
        <th>信息</th>
    </tr>
    </thead>
<#if (results?size <= 0)>
    <tr>
        <td style="text-align:center" colspan="3"><h4>无错误信息</h4></td>
    </tr>
<#else>
    <#list results as result>
        <tr>
            <#if ("${result.level}" == "ERROR" || "${result.level}" == "FATAL") >
                <td><font color=#FF0000>${result.level}</font></td>
                <td><font color=#FF0000>${result.label}</font></td>
                <td><font color=#FF0000>${result.description}</font></td>
            <#else>
                <td>${result.level}</td>
                <td>${result.label}</td>
                <td>${result.description}</td>
            </#if>

        </tr>
    </#list>
</#if>
</table>

<#include "../footer.ftl">