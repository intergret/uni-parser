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

    <#if start < 1>
        <#assign start = 1>
    </#if>

    <#if (total-start < win)>
        <#assign count = total-start+1>
    <#else>
        <#assign count = win>
    </#if>
<div class="pagination">
    <div class="page-wp roboto">

        <a class="page-item prev-page <#if (page == 1)>prev-disabled</#if>" href="<#if (page != 1)>${urlParamHelper(baseUrl,param,page-1)}<#else>javascript:;</#if>">上一页</a>

        <#if page != 1>
            <a class="page-item" href="${urlParamHelper(baseUrl,param,1)}">1</a>
        <#else>
            <a class="page-item current" href="${urlParamHelper(baseUrl,param,1)}"><strong>1</strong></a>
        </#if>

        <#if start = 1>
            <#assign start = start+1>
            <#assign count = count-1>
        <#elseif (start > 2)>
        </#if>

        <#assign end = start+count-1>
        <#if end == total>
            <#assign end = end-1>
        </#if>

        <#if (start <= end)>
            <#if start != 2>
                <a class="page-item" href="">.</a>
            </#if>
            <#list start..end as idx>
                <#if page != idx>
                    <a class="page-item" href="${urlParamHelper(baseUrl,param,idx)}">${idx}</a>
                <#else>
                    <a class="page-item current" href="${urlParamHelper(baseUrl,param,idx)}"><strong>${idx}</strong></a>
                </#if>
            </#list>
            <#if end != total - 1>
                <a class="page-item" href="">.</a>
            </#if>
        </#if>

        <#if page != total>
            <a class="page-item" href="${urlParamHelper(baseUrl,param,total)}">${total}</a>
        <#else>
            <a class="page-item current" href="${urlParamHelper(baseUrl,param,total)}"><strong>${total}</strong></a>
        </#if>

        <a class="page-item next-page <#if (page == total)>next-disabled</#if>" href="<#if page != total>${urlParamHelper(baseUrl,param,page+1)}<#else>javascript:;</#if>">下一页</a>

    </div>
</div>
</#if>
</#macro>