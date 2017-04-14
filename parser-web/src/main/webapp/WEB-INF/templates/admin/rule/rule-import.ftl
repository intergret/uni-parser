<#include "../header.ftl">
<div align="center" xmlns="http://www.w3.org/1999/html"><h2>Rule Import</h2></div>
<form action="/admin/rule/import" method="post" enctype="multipart/form-data">
    <table class="table table-bordered table-striped">
        <tr>
            <td style="width:25%">导入规则:</td>
            <td>
                <input type="file" name="files" class="btn js-btn" size="50" style="width:70%" multiple/>
                <button type="submit" class="btn js-btn">Submit</button>
            </td>
        </tr>
    </table>
</form>

<#if (updateState ??)>
    <#if (updateState ?? && updateState == "success")>
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Import Rule Success! Rule ID: <#list ruleImportIds as id>${id}<#if id_has_next>,</#if></#list></strong>
    </div>
    <#elseif (updateState ?? && updateState == "failed")>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>Import Rule Failed!</strong>
    </div>
    </#if>
</#if>
<#include "../footer.ftl">