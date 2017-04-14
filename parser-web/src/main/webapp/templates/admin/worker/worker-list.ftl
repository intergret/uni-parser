<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>

<form action="/admin/worker/index" method="get">
    <table id="workerSearchTable" class="table table-bordered" style="display: none">
        <thead>
        <tr>
            <td style="width:40%">worker</td>
            <td style="width:20%">站点名</td>
            <td>队列类型</td>
            <td>页面类型</td>
            <td>查找</td>
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
                    <option value=''>ALL</option>
                    <#list queueTypeStrList as queueType>
                        <option value='${queueType}'>${queueType}</option>
                    </#list>
                </select>
            </td>
            <td>
                <select name="pageType" style="width: auto">
                    <option value=''>ALL</option>
                    <#list pageTypeStrList as pageType>
                        <option value='${pageType}'>${pageType}</option>
                    </#list>
                </select>
            </td>
            <td style="text-align:left"><button type="submit" class="btn btn-primary">search</button></td>
        </tr>
    </table>
</form>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a name="work">Work List</a>
                <button onclick="toggleWorkerSearch();" style="float:right;">Search</button>
            </h4>
        </div>
        <#if (workerInfos?size <= 0)>
        <table class="table table-bordered table-striped">
            <tr><td style="text-align:center"><h4>没有Worker纪录</h4></td></tr>
        </table>
        <#else>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>线程ID</th>
                <th>名称</th>
                <th>站点名</th>
                <th>队列类型</th>
                <th>页面类型</th>
                <th>线程名</th>
                <th>预期速度</th>
                <th>实际速度</th>
                <th>状态</th>
            </tr>
            </thead>
            <#list workerInfos as workerInfo>
                <tr>
                    <td>${workerInfo.threadId}</td>
                    <td style="word-break:break-all">${workerInfo.name!""}</td>
                    <td>${workerInfo.siteName!""}</td>
                    <td>${workerInfo.queueType!""}</td>
                    <td>${workerInfo.pageType!""}</td>
                    <td>${workerInfo.threadName!""}</td>
                    <td>${workerInfo.expectedSpeed!""}</td>
                    <td>${workerInfo.actualSpeed?string('0.00')}</td>
                    <td>${workerInfo.state!""}</td>
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