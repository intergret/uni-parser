<#macro show page total baseUrl>
    <#if (page > 0) && (total > 0) && (page > 1 || page != total)>
        <#assign param = "page"/>
        <#assign win = 5>
        <#assign left = (win/2)?floor>
        <#assign right = ((win-1)/2)?floor>

        <#if (total - page < right) >
            <#assign start = total-win+1>
        <#else>
            <#assign start = page-left>
        </#if>

        <#if (start < 1)>
            <#assign start = 1>
        </#if>

        <#if (total-start < win)>
            <#assign count = total-start+1>
        <#else>
            <#assign count = win>
        </#if>

    <div class="pagination-wp clearfix">
        <#if (page > 1)>
            <a class="page-item prev-page" href="${urlParamHelper(baseUrl,param,page-1)}">&laquo;上一页</a>
        </#if>

        <#if page != 1>
            <a class="page-item" href="${urlParamHelper(baseUrl,param,1)}">1</a>
        <#else>
            <a class="page-item current" href="javascript:;">1</a>
        </#if>

        <#if start = 1>
            <#assign start = start+1>
            <#assign count = count-1>
        <#elseif (start > 2)>
            <a class="page-item" href="javascript:;">...</a>
        </#if>

        <#assign end = start+count-1>
        <#if end == total>
            <#assign end = end-1>
        </#if>

        <#if (start <= end)>
            <#list start..end as idx>
                <#if page != idx>
                    <a class="page-item" href="${urlParamHelper(baseUrl,param,idx)}">${idx}</a>
                <#else>
                    <a class="page-item current" href="javascript:;">${idx}</a>
                </#if>
            </#list>
        </#if>

        <#if (end < total-1)>
            <a class="page-item" href="javascript:;">...</a>
        </#if>

        <#if page != total>
            <a class="page-item" href="${urlParamHelper(baseUrl,param,total)}">${total}</a>
        <#else>
            <a class="page-item current" href="javascript:;">${total}</a>
        </#if>

        <#if (page < total)>
            <a class="page-item next-page" href="${urlParamHelper(baseUrl,param,page+1)}">下一页&raquo;</a>
        </#if>
    </div>
    </#if>
</#macro>