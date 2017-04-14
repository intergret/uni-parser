<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>
<script type="text/javascript" src="http://s.wdjimg.com/ajax/libs/jqPlot/1.0.8/jquery.jqplot.min.js"></script>
<#if (queueLengthList?size <= 0)>
    <table class="table table-bordered table-striped">
        <tr>
            <td style="text-align:center"><h4>${queueName}没有Queue记录</h4></td>
        </tr>
    </table>
    <#else>
        <#list queueLengthList as queueLength >
            <input type="hidden" class="queueLength" value="${queueLength}">
        </#list>

    <script>
        $(function () {
            var arr = [], len = $('.queueLength').length;
            for (var i = 0; i < len; i++) {
                arr.push($('.queueLength').eq(i).val());
            }

            var plot1 = $.jqplot('chart1', [arr], {
                title: '[${queueName}] Queue Length Trend Chart',
                axesDefaults: {
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer
                },
                axes: {
                    xaxis: {
                        label: 'recently cycle'
                    },
                    yaxis: {
                        label: 'length'
                    }
                }
            });
        });
    </script>

    <div id="chart1"></div>
</#if>
<#include "../footer.ftl">