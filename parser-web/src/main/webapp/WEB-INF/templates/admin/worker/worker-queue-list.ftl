<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>

<form action="/admin/worker/queue" method="get">
    <table id="workerQueueSearchTable" class="table table-bordered" style="display: none">
        <thead>
        <tr>
            <td style="width:85%">关键字</td>
            <td>操作</td>
        </tr>
        </thead>
        <tr>
            <td><input type="text" id="searchWord" name="searchWord" style="width:98%" tabindex="2"></td>
            <td style="text-align:left">
                <button type="submit" class="btn btn-primary">search</button>
            </td>
        </tr>
    </table>
</form>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="queue">Queue List</a>
                <button onclick="toggleWorkerQueueSearch();" style="float:right;">Search</button>
            </h4>
        </div>

    <#if (queueInfos?size <= 0)>
        <table class="table table-bordered table-striped">
            <tr>
                <td style="text-align:center"><h4>没有Queue记录</h4></td>
            </tr>
        </table>
    <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>队列名</th>
                <th>队列长度</th>
                <th style="width: 54px;">Trend</th>
                <th style="width: 54px;">Consumer</th>
            </tr>
            </thead>
            <#list queueInfos as queueInfo>
                <tr>
                    <td>${queueInfo.queueName!""}</td>
                    <td>${queueInfo.length!""}</td>
                    <td>
                        <form id="queue_trend"
                              action="/admin/worker/queue/trend/${queueInfo.queueName}"
                              method="get" style="display:inline">
                            <button type="submit" class="btn btn-primary">Trend</button>
                        </form>
                    </td>
                    <td>
                        <form id="queue_consumer"
                              action="/admin/worker/queue/consumer/${queueInfo.queueName}"
                              method="get" style="display:inline">
                            <button type="submit" class="btn btn-primary">Consumer</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </table>
    </#if>
    </div>
</div>
<div class="sourcePage">
<@pagination.show pageNation.pageNum pageNation.pageTotal pageNation.pageUrl/>
</div>
<#include "../footer.ftl">