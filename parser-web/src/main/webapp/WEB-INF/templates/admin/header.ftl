<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <script src="/admin/static/js/jquery.min.js"></script>
    <script src="/admin/static/js/bootstrap.min.js"></script>
    <script src="/admin/static/js/bootstrap-typeahead.js"></script>
    <script src="/admin/static/js/json-format.js"></script>
    <script src="/admin/static/js/form.js"></script>
    <script src="/admin/static/js/main.js"></script>

    <link type="text/css" rel="stylesheet" href="/admin/static/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="/admin/static/css/admin.css">
    <link type="text/css" rel="stylesheet" href="/admin/static/css/bootstrap-plugin.css"/>
    <link type="text/css" rel="stylesheet" href="/admin/static/css/json-format.css"/>
    <title>通用解析</title>
</head>

<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li class="dropdown" data-dropdown="dropdown">
            <a href="/admin/rule/skeleton">添加规则</a>
        </li>
        <li class="dropdown" data-dropdown="dropdown">
            <a href="/admin/rule/index">管理规则</a>
        </li>
        <form id="ruleSearch" action="/admin/rule/index" method="get" style="text-align:right">
            <input type="text" name="searchWord" placeholder="Search Rule" tabindex="1"/>
        </form>
    </ul>
