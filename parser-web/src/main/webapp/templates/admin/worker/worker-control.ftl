<#include "../header.ftl">
<#import "/spring.ftl" as spring/>

<div align="center"><h2>Worker Control</h2></div>
<form action="/admin/worker" method="post">
    <table class="table table-bordered">
        <thead>
        <tr>
            <td style="width:40%">worker</td>
            <td style="width:20%">站点名</td>
            <td>队列类型</td>
            <td>页面类型</td>
            <td>操作</td>
            <td>提交</td>
        </tr>
        </thead>
        <tr>
            <td>
                <select name="worker" style="width:98%">
                <#list workerStrList as worker>
                    <option value='${worker}'>${worker}</option>
                </#list>
                </select>
            </td>
            <td><input type="text" name="siteName" value='' style="width:98%"></td>
            <td>
                <select name="queueType" style="width: auto">
                <#list queueTypeStrList as queueType>
                    <option value='${queueType}'>${queueType}</option>
                </#list>
                </select>
            </td>
            <td>
                <select name="pageType" style="width: auto">
                <#list pageTypeStrList as pageType>
                    <option value='${pageType}'>${pageType}</option>
                </#list>
                </select>
            </td>
            <td>
                <select name="controlType" style="width: auto">
                <#list controlTypeStrList as controlType>
                    <option value='${controlType}'>${controlType}</option>
                </#list>
                </select>
            </td>
            <td style="text-align:left">
                <button type="submit" class="btn btn-primary">submit</button>
            </td>
        </tr>
    </table>
    <input type="hidden" name="_method" value="put"/>
</form>

<#if (results??)>
    <#if (results?? && results?size <= 0)>
    <table class="table table-bordered table-striped">
        <tr>
            <td style="text-align:center"><h4>无操作结果</h4></td>
        </tr>
    </table>
    <#else>
    <table class="table table-bordered">
        <#list results as result>
            <tr>
                <td>名称:</td>
                <td>${result.worker!""}</td>
            </tr>
            <tr>
                <td>站点名:</td>
                <td>${siteName!""}</td>
            </tr>
            <tr>
                <td>队列类型:</td>
                <td>${queueType!""}</td>
            </tr>
            <tr>
                <td>网页类型:</td>
                <td>${pageType!""}</td>
            </tr>
            <tr>
                <td>操作:</td>
                <td>${result.controlType!""}</td>
            </tr>
            <tr>
                <td>结果:</td>
                <td>${result.operateResult!""}</td>
            </tr>
            <tr>
                <td>说明:</td>
                <td>${result.comment!""}</td>
            </tr>
        </#list>
    </table>
    </#if>
</#if>
<#include "../footer.ftl">