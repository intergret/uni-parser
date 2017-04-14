<#include "../header.ftl">
<#import "/spring.ftl" as spring/>
<#import "../../macros/pagination.ftl" as pagination>
<div align="center"><h2>Authority</h2></div>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h4 class="panel-title"><a>Search Authority</a></h4>
    </div>
    <form action="/admin/authority/index" method="get">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>uid</th>
                <th>权限</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>查询</th>
            </tr>
            </thead>
            <tr>
                <td><input type="text" name="uid" value=''></td>
                <td>
                    <select name="role" style="width:90%">
                        <option value=''>ALL</option>
                        <#list roleList as role>
                            <option value='${role}'>${role}</option>
                        </#list>
                    </select>
                </td>
                <td><input type="text" name="email" value=''></td>
                <td><input type="text" name="telephone" value=''></td>
                <td colspan="2" style="text-align:left"><button type="submit" class="btn btn-primary">submit</button></td>
            </tr>
        </table>
    </form>
</div>

<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title"><a>Authority List</a></h4>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>uid</th>
                <th>角色(role)</th>
                <th>邮箱(email)</th>
                <th>电话(telephone)</th>
                <th>授权者(assigner)</th>
                <th>删除</th>
            </tr>
            </thead>
        <#if (authorities?size <= 0)>
            <tr><td style="text-align:center" colspan="7"><h4>没有Authority纪录</h4></td></tr>
        <#else>
            <#list authorities as authority>
                <tr>
                    <td>${authority.id}</td>
                    <td>${authority.uid}</td>
                    <td>${authority.role}</td>
                    <td>${authority.email!""}</td>
                    <td>${authority.telephone!""}</td>
                    <td>${authority.assigner!""}</td>
                    <td>
                        <form class="js-form-delete" action="/admin/authority/${authority.id}" method="post">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" onClick="return confirm('确定删除吗?');">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </#if>
        </table>
    </div>
    <div class="sourcePage">
    <@pagination.show pageNation.pageNum pageNation.pageTotal pageNation.pageUrl/>
    </div>
</div>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h4 class="panel-title"><a>Add Authority</a></h4>
    </div>
    <form name ="authorityForm" action="/admin/authority" method="post" onsubmit="return submitAuthority();">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>uid</th>
                <th>角色</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>添加</th>
            </tr>
            </thead>
            <tr>
                <td><input type="text" name="uid" value=''></td>
                <td>
                    <select name="role" style="width:90%">
                        <#list roleList as role>
                            <option value='${role}'
                                <#if (role?? && role == "ROLE_USER" ) >selected="selected"</#if>>${role}
                            </option>
                        </#list>
                    </select>
                </td>
                <td><input type="text" name="email" value=''></td>
                <td><input type="text" name="telephone" value=''></td>
                <td><button type="submit" class="btn btn-primary">submit</button></td>
            </tr>
        </table>
    </form>
</div>
<#include "../footer.ftl">