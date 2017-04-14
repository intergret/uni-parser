<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="queue">Queue [${queueName!""}] Consumer List</a>
            </h4>
        </div>
    <#if !queueInstanceInfoList?? || queueInstanceInfoList?size <= 0>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="text-align:center"><h4>没有Consumer</h4></td>
            </tr>
        </table>
    <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>worker</th>
            </tr>
            </thead>
            <#list queueInstanceInfoList as queueInstanceInfo>
                <tr>
                    <td>${queueInstanceInfo.workerPath!""}</td>
                </tr>
            </#list>
        </table>
    </#if>
    </div>
</div>
<#include "../footer.ftl">