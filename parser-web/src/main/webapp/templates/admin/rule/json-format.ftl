<#include "../header.ftl">
<div align="center"><h2>Json Format</h2></div>
<table class="table table-bordered table-striped">
    <tr>
        <td>Json:</td>
        <td><textarea id="RawJson" rows="15" style="width:98%"></textarea></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align:right">
            <button type="submit" class="btn btn-primary" onclick="FormatJson()">格式化</button>
        </td>
    </tr>
</table>

<div id="FormatedJson" class="FormatedJson"></div>

<#include "../footer.ftl">