<#include "../header.ftl">
<div align="center"><h2> Parse Result </h2></div>
<form action="/admin/tool/result" method="post">
    <table class="table table-bordered table-striped">
        <tr>
            <td style="width:25%">链接(url):</td>
            <td><input type="text" name="queryUrl" style="width:98%" value=${queryUrl!""}></td>
        </tr>
        <tr>
            <td>存储路径(storagePath):</td>
            <td><input type="text" name="storagePath" style="width:98%" value=${storagePath!""}></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:right">
                <button type="submit" class="btn btn-primary">Submit</button>
            </td>
        </tr>
    </table>

    <table class="table table-bordered table-striped">
    <#if data?? || links??>
        <tr>
            <td style="width:25%">解析时间(time):</td>
            <td>${time?datetime?string("yyyy-MM-dd_HH:mm:ss")}</td>
        </tr>
        <tr>
            <td>匹配的规则:</td>
            <td><textarea  id="RawJson" rows="10">${parseRule!""}</textarea></td>
        </tr>
        <tr>
            <td>解析出的结果:</td>
            <td><textarea  id="RawJson" rows="30">${data!""}</textarea></td>
        </tr>
        <tr>
            <td>解析出的链接:</td>
            <td><textarea  id="RawJson" rows="20">${links!""}</textarea></td>
        </tr>
        <tr>
            <td>解析结果的状态:</td>
            <td><textarea  id="RawJson" rows="5">${state!""}</textarea></td>
        </tr>
        <tr>
            <td>上下文信息:</td>
            <td><textarea  id="RawJson" rows="8">${contextInfo!""}</textarea></td>
        </tr>
        <tr>
            <td>会传递的数据:</td>
            <td><textarea  id="RawJson" rows="8">${deliveryInfo!""}</textarea></td>
        </tr>
    <#else>
        <#if queryUrl??>
            <tr><td colspan="2" style="text-align:center">没有查到解析结果</td></tr>
        </#if>
    </#if>
    </table>
</form>

<#include "../footer.ftl">