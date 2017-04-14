<#if exception?exists>
    ${exception}
    <#list exception.stackTrace as st>
        ${st}
    </#list>
<#else>
    <#if javax?exists && javax.servlet?exists && javax.servlet.error?exists && javax.servlet.error.exception?exists>
        Servlet Exception:<p>
        ${javax.servlet.error.exception} <br>
        ${javax.servlet.error.exception.message?default("")} <br>
        <#list javax.servlet.error.exception.stackTrace as st>
            ${st}<br>
        </#list>
    </#if>
</#if>