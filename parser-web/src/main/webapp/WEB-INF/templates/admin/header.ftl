<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <script src="http://static.wdjimg.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://static.wdjimg.com/ajax/libs/twitter-bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://v2.bootcss.com/assets/js/bootstrap-typeahead.js"></script>
    <script src="/admin/static/js/json-format.js"></script>
    <script src="/admin/static/js/form.js"></script>
    <script src="/admin/static/js/main.js"></script>

    <link type="text/css" rel="stylesheet" href="http://static.wdjimg.com/ajax/libs/twitter-bootstrap/2.0.4/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="http://s.wdjimg.com/style/b/css/biz/mms/admin.css">
    <link type="text/css" rel="stylesheet" href="/admin/static/css/bootstrap-plugin.css"/>
    <link type="text/css" rel="stylesheet" href="/admin/static/css/json-format.css"/>
    <title>通用爬虫</title>
</head>

<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">规则管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/rule/skeleton">添加新的规则</a></li>
                <li><a href="/admin/rule/index">管理现有规则</a></li>
                <li><a href="/admin/rule/import">导入现有规则</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">种子管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/seed/skeleton">添加新的种子</a></li>
                <li><a href="/admin/seed/index">管理现有种子</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">站点管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/site/skeleton">添加新的站点</a></li>
                <li><a href="/admin/site/index">管理现有站点</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">代理管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/proxy/skeleton">添加新的代理</a></li>
                <li><a href="/admin/proxy/index">查看现有代理</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">链接管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/link">添加新的链接</a></li>
                <li><a href="/admin/rerunlink">重刷链接</a></li>
                <li class="divider"></li>
                <li><a href="/admin/link/deadpattern/skeleton">添加新的死链模式</a></li>
                <li><a href="/admin/link/deadpattern/index">管理现有死链模式</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">爬虫管理</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/worker">控制爬虫</a></li>
                <li><a href="/admin/worker/index">查看爬虫</a></li>
                <li><a href="/admin/worker/queue">查看Queue</a></li>
            </ul>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="#" class="dropdown-toggle">其他工具</a>
            <ul class="dropdown-menu">
                <li><a href="/admin/tool/result">查询解析结果</a></li>
                <li class="divider"></li>
                <li><a href="/admin/authority/index">管理权限</a></li>
                <li><a href="/admin/authority/audit/index">查看操作记录</a></li>
                <li class="divider"></li>
                <li><a href="/admin/tool/xpath/lookup">查询XPath</a></li>
                <li class="divider"></li>
                <li><a target="_blank" href="/admin/tool/json/format">Json格式化</a></li>
                <li><a target="_blank" href="http://www.freeformatter.com/html-formatter.html">Html格式化</a></li>
                <li><a target="_blank" href="http://www.freeformatter.com/xml-formatter.html">Xml格式化</a></li>
            </ul>
        </li>
        <form id="ruleSearch" action="/admin/rule/index" method="get" style="text-align:right">
            <input type="text" name="searchWord" placeholder="Search Rule" tabindex="1"/>
        </form>
    </ul>
