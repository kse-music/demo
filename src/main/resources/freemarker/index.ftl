<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <title>nodeDATA</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description"> 
    <meta content="" name="author">
     <#import "macros.ftl" as angelnode>
     <@angelnode.css /> 
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/global/plugins/select2/select2.css"/>
    <link href="../bower_components/ladda/dist/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="../libs/metronic/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
    <link href="../css/custom.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="/favicon.ico">
   	
   	<#-- ftl的注释标签   -->
</head>
<body>
<div style="display: none;">
    <svg width="120" height="140">
        <symbol id="sym01" viewBox="0 0 200 25">
            <text x="0" y="15" fill="red">CBNODE</text>
        </symbol>
        <symbol viewBox="0 0 25 25" id="icon-external">
            <path d="M19 14.6c.3.2.7.4 1 .5.4.1.8.2 1.2.2v3.1c0 .5-.1.9-.3 1.4-.2.4-.4.8-.8 1.1-.3.3-.7.6-1.1.8-.4.2-.9.3-1.4.3H5.5c-.5 0-.9-.1-1.4-.3s-.8-.4-1.1-.8c-.3-.3-.6-.7-.8-1.1-.1-.4-.2-.8-.2-1.3V8c0-.5.1-.9.3-1.4.1-.4.4-.8.7-1.1.3-.3.7-.6 1.1-.8.5-.1.9-.2 1.4-.2h6.6v2.1H5.5c-.4.1-.7.2-.9.5-.3.2-.4.5-.4.9v10.5c0 .4.1.7.4.9.2.3.5.4.9.4h12.2c.4 0 .7-.1.9-.4s.4-.6.4-.9v-3.9zm4-9.9v5.6c0 .2-.1.4-.2.5-.1.1-.3.2-.5.2h-1c-.2 0-.3-.1-.5-.2-.1-.1-.2-.3-.2-.5v-3l-8.8 8.8c-.1.1-.3.2-.5.2s-.4-.1-.5-.2l-.7-1c-.1-.1-.2-.3-.2-.5s0-.3.2-.5l8.8-8.8h-3c-.2 0-.3-.1-.5-.2-.1-.1-.2-.3-.2-.5v-1c0-.2 0-.3.2-.5 0 .1.2 0 .4 0h6.5c.2 0 .3.1.5.2.1.1.2.3.2.5v.9z"></path>
        </symbol>
    </svg>
</div>

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
                                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 250px;"><ul class="dropdown-menu-list scroller" style="height: 250px; overflow: hidden; width: auto;" data-handle-color="#637283" data-initialized="1">
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
                                </ul><div class="slimScrollBar" style="background: rgb(99, 114, 131); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(234, 234, 234); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
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
                    <li class="active">
                        <a href="/">首页</a>
                    </li>
                    <li class="">
                        <a href="/startups">项目</a>
                    </li>
                    <li class="">
                        <a href="/investors">投资方</a>
                    </li>
                    <li class="">
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
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN PAGE BREADCRUMB -->
            <ul class="page-breadcrumb breadcrumb">
            </ul>
            <!-- END PAGE BREADCRUMB -->
            <!-- BEGIN PAGE CONTENT INNER -->
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-suitcase m-hot-i"></i>
                                <span class="caption-subject uppercase m-hot">热门项目</span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row"  id="hot-project">


                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <!--<i class="fa fa-cogs font-green-sharp"></i>-->
                                <img src="../images/hot-inv.png" alt="">
                                <span class="caption-subject  m-hot uppercase">热门投资方</span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="featured table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2">
                                            投资方
                                        </th>
                                        <th width="130px" >
                                            投资事件
                                        </th>
                                        <th width="50px" class="text-center">
                                            收藏
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="hot-investor" ty="3">



                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <!--<i class="fa fa-cogs font-green-sharp"></i>-->
                                <img src="../images/hot-people.png" alt="">
                                <span class="caption-subject  m-hot uppercase">热门创业者</span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="featured table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2">
                                            创业者
                                        </th>
                                        <th width="130px">
                                            标签
                                        </th>
                                        <th width="50px" class="text-center">
                                            收藏
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="hot-people" ty="1">



                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END PAGE CONTENT INNER -->
        </div>
    </div>
    <nav role="navigation" class="c-quicklinks hide">
        <div class="c-quicklinks__inner">
            <div class="o-grid">
                <h2>旗下其它热门产品</h2>
                <ul class="c-quicklinks__list">
                    <li>
                        <a href="http://market.envato.com/?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=market_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>
                    <li>
                        <a href="http://studio.envato.com/?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=studio_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>

                    <li>
                        <a href="http://sites.envato.com/beta?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=sites_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>
                    <li>
                        <a href="https://elements.envato.com/?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=elements_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>
                    <li>
                        <a href="https://elements.envato.com/?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=elements_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>
                    <li>
                        <a href="https://elements.envato.com/?utm_source=envatocom&amp;utm_medium=promos&amp;utm_campaign=elements_envatocom_quicknav&amp;utm_content=env_quicknav">
                            <svg class="c-logo c-logo--envato-market">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#sym01"></use>
                            </svg>
                            <svg class="icon-external">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-external"></use>
                            </svg>
                        </a>
                    </li>

                </ul>
            </div>
        </div>

    </nav>
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
<!-- BEGIN PAGE PLUGINS & SCRIPTS -->
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/select2/select2.min.js"></script>
<script src="../libs/metronic/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/spin.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/ladda.min.js" type="text/javascript"></script>
<!-- END PAGE PLUGINS & SCRIPTS -->
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
<script src="../js/datarea.js" type="text/javascript"></script>
<script src="../js/utils.js" type="text/javascript"></script>
<script src="../js/language.js" type="text/javascript"></script>
<script src="../js/configs.js" type="text/javascript"></script>
<script src="../js/hiekn.ajax.js" type="text/javascript"></script>
<script src="../js/default.js" type="text/javascript"></script>
<script src="../js/index.js" type="text/javascript"></script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>