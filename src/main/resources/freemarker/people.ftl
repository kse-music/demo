<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.1.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8">
    <title>创业者列表页|nodeDATA</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE STYLES -->
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/admin/pages/css/todo.css"/>
    <link href="../bower_components/ladda/dist/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE STYLES -->
    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="../libs/metronic/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
    <link href="../css/custom.css" rel="stylesheet" type="text/css">
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-menu-fixed" class to set the mega menu fixed  -->
<!-- DOC: Apply "page-header-top-fixed" class to set the top menu fixed  -->
<body>
<!-- BEGIN HEADER -->
<div class="page-header">
    <!-- BEGIN HEADER TOP -->
    <div class="page-header-top">
        <div class="container">
            <!-- BEGIN LOGO -->
            <div class="page-logo">
                <a href="/"><img src="../images/logo.png" alt="logo" class="logo-default"></a>
            </div>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="menu-toggler"></a>
            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN TOP NAVIGATION MENU -->
            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown dropdown-extended dropdown-dark dropdown-notification" id="products">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <i class="icon-grid"></i>
                            <i class="fa fa-close hide" style="font-size: 20px;"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="external">
                                <h3>动点科技旗下其它产品</h3>
                                <a href="javascript:;"></a>
                            </li>
                            <li>
                                <ul class="dropdown-menu-list scroller" style="height: 168px;" data-handle-color="#637283">
                                    <!--<li>
                                        <a href="http://cn.technode.com">
                                            <span class="time">Go</span>
										<span class="details">
										<span class="label label-sm label-icon label-success">
										<i class="fa fa-plus"></i>
										</span>动点科技 </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="http://www.lengjing.io">
                                            <span class="time">Go</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>数据棱镜 - 创业投资数据分析工具</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="http://hiekn.hiekn.com:8400/angelnode_activity/index.html">
                                            <span class="time">Go</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>创业者大赛</span>
                                        </a>
                                    </li>-->

                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li class="dropdown dropdown-extended dropdown-dark dropdown-notification" id="header_notification_bar">
                        <a href="/message" class="dropdown-toggle" data-toggle="" data-hover="" data-close-others="true">
                            <i class="icon-bell"></i>
                            <span class="badge badge-default"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="external">
                                <h3>You have <strong>12 pending</strong> tasks</h3>
                                <a href="javascript:;">view all</a>
                            </li>
                            <li>
                                <ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">just now</span>
										<span class="details">
										<span class="label label-sm label-icon label-success">
										<i class="fa fa-plus"></i>
										</span>
										New user registered. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">3 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Server #12 overloaded. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">10 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Server #2 not responding. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">14 hrs</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										Application error. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">2 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Database overloaded 68%. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">3 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										A user IP blocked. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">4 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Storage Server #4 not responding dfdfdfd. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">5 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										System Error. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">9 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Storage server failed. </span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li class="dropdown dropdown-dark">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <i class="icon-globe"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li>
                                <a href="/?lang=zh_CN">
                                    简体中文 </a>
                            </li>
                            <li>
                                <a href="/?lang=en_US">
                                    English </a>
                            </li>
                        </ul>
                    </li>
                    <li class="droddown dropdown-separator">
                        <span class="separator"></span>
                    </li>
                    <li>
                        <a href="/login">登陆</a>
                    </li>
                    <li>
                        <a href="/signup">注册</a>
                    </li>
                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown dropdown-user dropdown-dark hide">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" class="img-circle" src="../libs/metronic/assets/admin/layout3/img/avatar9.jpg">
                            <span class="username username-hide-mobile"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li>
                                <a href="/profile">
                                    <i class="icon-user"></i> 个人中心</a>
                            </li>
                            <li>
                                <a href="javascript:angelnode.logout();">
                                    <i class="icon-key"></i>退出</a>
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
            </div>
            <!-- END TOP NAVIGATION MENU -->
        </div>
    </div>
    <!-- END HEADER TOP -->
    <!-- BEGIN HEADER MENU -->
    <div class="page-header-menu">
        <div class="container">
            <!-- BEGIN HEADER SEARCH BOX -->
            <div class="search-form has-feedback form-group">
                <div class="input-group">
                    <input type="text" class="search form-control" placeholder="搜索">
                        <span class="input-group-btn">
                         <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
                        </span>
                </div>
                <div id="suggestion-search" class="dropdown-menu suggestion-list hide"></div>
            </div>
            <!-- END HEADER SEARCH BOX -->
            <!-- BEGIN MEGA MENU -->
            <!-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background -->
            <!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover -->
            <div class="hor-menu ">
                <ul class="nav navbar-nav">
                    <li class="">
                        <a href="/">首页</a>
                    </li>
                    <li class="">
                        <a href="/startups">项目</a>
                    </li>
                    <li class="">
                        <a href="/investors">投资方</a>
                    </li>
                    <li class="active">
                        <a href="/people">创业者</a>
                    </li>
                </ul>
            </div>
            <!-- END MEGA MENU -->
        </div>
    </div>
    <!-- END HEADER MENU -->
</div>
<!-- END HEADER -->
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
    <!-- BEGIN PAGE HEAD -->
    <div class="page-head">
        <div class="container">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h1>创业者 <small>共 <span id="pcount"></span> 项</small></h1>
            </div>
            <!-- END PAGE TITLE -->
        </div>
    </div>
    <!-- END PAGE HEAD -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            Widget settings form goes here
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">Save changes</button>
                            <button type="button" class="btn default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    <a href="/">首页</a><i class="fa fa-circle"></i>
                </li>
                <li class="active">
                    创业者
                </li>
            </ul>
            <div class="row">
                <div class="col-md-9" >
                    <div class="portlet light">
                        <!--<div class="portlet-title">
                            <div class="caption" >
                                <span class="caption-subject font-green-sharp bold uppercase">创业者筛选</span>
                                <span class="caption-helper visible-sm-inline-block visible-xs-inline-block"></span>
                            </div>
                        </div>-->
                        <div class="portlet-body " >
                            <div class="todo-project-list">
                                <div class="row">
                                    <div class="col-md-1 col-xs-3">
                                        <span style="cursor: default">角色</span>
                                    </div>
                                    <div class="col-md-10 col-xs-7">
                                        <ul class="list-inline c-filter" id="roles">
                                            <li nid="-1"  class="active margin-bottom-10"><a class="role">全部</a></li>

                                        </ul>
                                    </div>
                                    <div class="col-md-1 col-xs-2">
                                        <span class="more-con-items">更多</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="portlet light">
                        <div class="portlet-body">

                            <div class="featured table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2">
                                            创业者
                                        </th>
                                        <th width="110px">
                                            标签
                                        </th>
                                        <th width="50px" class="text-center">
                                            收藏
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="peoples">



                                    </tbody>
                                </table>
                            </div>

                            <div class="text-center">
                                <div class="no-result hide">
                                    抱歉，没有相关的结果
                                </div>
                                <ul class="tcdPageCode">

                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <img src="../images/hot-people.png" alt="">
                                <span class="caption-subject uppercase m-hot-v">热门创业者</span>
                                <span class="caption-helper visible-sm-inline-block visible-xs-inline-block"></span>
                            </div>
                        </div>
                        <div class="portlet-body todo-project-list-content" >
                            <div class="todo-project-list">
                                <ul class="nav nav-pills nav-stacked" id="hot-people">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    <!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->
<!-- BEGIN PRE-FOOTER -->
<div class="page-prefooter">
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                <h2>关于我们</h2>
                <p>
                    nodeDATA是动点旗下创新创业数据库，致力于提供业内最全面的创业项目、创业者、投资机构及投资事件数据，用大数据为创业者和投资者提供最有价值的数据支持。
                </p>
            </div>
            <div class="col-md-3 col-sm-6 col-xs12 footer-block">
                <h2>相关站点</h2>
                <p>
                    TechNode 中文版：<a href="http://cn.technode.com/">cn.technode.com</a><br>
                    TechNode 英文版：<a href="http://technode.com/">technode.com</a><br>
                    TechCrunch 中国版：<a href="http://techcrunch.cn/">techcrunch.cn</a><br>
                </p>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                <h2>合作伙伴</h2>
                <ul class="social-icons">
                    <!--<li>
                        <a href="javascript:;" data-original-title="rss" class="rss"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="facebook" class="facebook"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="twitter" class="twitter"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="googleplus" class="googleplus"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="linkedin" class="linkedin"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="youtube" class="youtube"></a>
                    </li>
                    <li>
                        <a href="javascript:;" data-original-title="vimeo" class="vimeo"></a>
                    </li>-->
                </ul>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                <h2>联系我们</h2>
                <address class="margin-bottom-40">
                    寻求报道: <a href="mailto:tip@technode.com">tip@technode.com</a><br>
                    商务合作: <a href="mailto:tip@technode.com">bd@technode.com</a>
                </address>
            </div>
        </div>
    </div>
</div>
<!-- END PRE-FOOTER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
    <div class="container">
        2016 &copy; TechNode. All Rights Reserved.
    </div>
</div>
<div class="scroll-to-top">
    <i class="icon-arrow-up"></i>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS (Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../libs/metronic/assets/global/plugins/respond.min.js"></script>
<script src="../libs/metronic/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="../libs/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../libs/metronic/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<script src="../libs/metronic/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/spin.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/ladda.min.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../libs/metronic/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/layout.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/demo.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo(theme settings page)
    });
</script>
<script src="../js/jquery.page.js" type="text/javascript"></script>
<script src="../js/datarea.js" type="text/javascript"></script>
<script src="../js/utils.js" type="text/javascript"></script>
<script src="../js/language.js" type="text/javascript"></script>
<script src="../js/configs.js" type="text/javascript"></script>
<script src="../js/hiekn.ajax.js" type="text/javascript"></script>
<script src="../js/default.js" type="text/javascript"></script>
<script src="../js/people.js" type="text/javascript"></script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>