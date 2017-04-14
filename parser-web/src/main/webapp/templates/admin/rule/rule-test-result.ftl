<#include "../header.ftl">
<div align="center"><h2>Test Result</h2></div>
<table class="table table-bordered table-striped">
    <tr>
        <td style="width:15%">Rule:</td>
        <td>${parseRule}</td>
    </tr>
    <tr>
        <td>结果:</td>
        <td><textarea id="RawJson" rows="20">${parseResult}</textarea></td>
    </tr>
    <tr>
        <td style="text-align:left"></td>
        <td style="text-align:right">
            <button type="submit" class="btn btn-primary" onclick="FormatJson()">格式化</button>
        </td>
    </tr>
</table>

<div id="FormatedJson" class="FormatedJson"></div>

<#include "../footer.ftl">