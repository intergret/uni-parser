<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>
<div align="center"><h2> Audit Log</h2></div>
<form action="/admin/authority/audit/index" method="post">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th style="width:15%">操作者</th>
            <th style="width:15%">操作对象</th>
            <th style="width:15%">操作类型</th>
            <th style="width:15%">关键字</th>
            <th style="width:10%">查询</th>
        </tr>
        </thead>
        <tr>
            <td><input type="text" name="operator" value='' style="width:90%"></td>
            <td>
                <select name="target" style="width:90%">
                    <option value=''></option>
                    <option value='Rule'>Rule</option>
                    <option value='Node'>Node</option>
                    <option value='Prop'>Prop</option>
                    <option value='ExtraConfig'>ExtraConfig</option>
                </select>
            </td>
            <td>
                <select name="operationType" style="width:90%">
                    <option value=''></option>
                    <option value='INSERT'>Insert</option>
                    <option value='UPDATE'>Update</option>
                    <option value='DELETE'>Delete</option>
                </select>
            </td>
            <td><input type="text" name="keyword" value='' style="width:90%"></td>
            <td colspan="2" style="text-align:left"><button type="submit" class="btn btn-primary">submit</button></td>
        </tr>
    </table>
</form>

</br>

<#if (auditLogs?size <= 0)>
<table class="table table-bordered table-striped">
    <tr>
        <td style="text-align:center"><h4>没有Audit纪录</h4></td>
    </tr>
</table>
<#else>
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th style="width:5%">id</th>
        <th style="width:10%">操作时间</th>
        <th style="width:10%">操作者</th>
        <th style="width:10%">操作对象</th>
        <th style="width:8%">操作类型</th>
        <th style="width:60%">详情(detail)</th>
    </tr>
    </thead>
    <#list auditLogs as auditLog>
        <tr>
            <td>${auditLog.id}</td>
            <td>${auditLog.datetime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>${auditLog.operator!""}</td>
            <td>${auditLog.target!""}</td>
            <td>${auditLog.operationType!""}</td>
            <td>${auditLog.detail!""}</td>
        </tr>
    </#list>
</table>
</#if>

<div class="sourcePage">
<@pagination.show pageNation.pageNum pageNation.pageTotal pageNation.pageUrl/>
</div>
<#include "../footer.ftl">