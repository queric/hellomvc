<!--顶部导航begin-->
<div class="top-navbar navbar navbar-default" style="margin-bottom: 0;" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">导航栏</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="/main.aspx"><span class="navbar-brand"><span class="glyphicon glyphicon-home"></span>&nbsp;${config_system_name}</span></a>
    </div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <!--左侧导航预留位置-->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user" style="position:relative;top: 3px;"></span>
                    admin  <i class="glyphicon glyphicon-triangle-bottom"></i>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">帮助文档</a></li>
                    <li><a href="/repasswd.aspx">修改密码</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="/logout.aspx">退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!--顶部导航end-->
<!--左侧边栏begin-->
<div class="sidebar-nav">
    <ul id="nav-items"></ul>
</div>
<!--左侧边栏end-->