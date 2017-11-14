<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <title>项目详情|nodeDATA</title>
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
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/timeline.css"/>
    <link href="../libs/metronic/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css">
    <link href="../bower_components/ladda/dist/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE STYLES -->
    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="../libs/metronic/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
    <link href="../css/custom.css" rel="stylesheet" type="text/css">
    <style>
        .items{
            min-height: 25px;
        }
        .items li {
            min-width:80px;
            font-size: 14px;
            text-align: center;
            background: #f4f4f4;
            padding: 5px 0;
            margin-bottom: 10px;
            margin-right: 10px;
            color: #5b9bd1;
            float: left;
        }
    </style>
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


                                </ul>
                            </li>
                        </ul>
                    </li>
                    <!-- BEGIN NOTIFICATION DROPDOWN -->
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
                    <!-- END NOTIFICATION DROPDOWN -->

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
    <!-- BEGIN PAGE HEAD -->
    <!--<div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>Face++ <small></small></h1>
            </div>
        </div>
    </div>-->
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
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <!-- BEGIN PAGE BREADCRUMB -->
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    <a href="/">首页</a><i class="fa fa-circle"></i>
                </li>
                <li>
                    <a href="/startups">项目</a><i class="fa fa-circle"></i>
                </li>
                <li class="active">

                </li>
            </ul>
            <!-- END PAGE BREADCRUMB -->
            <!-- BEGIN PAGE CONTENT INNER -->

            <div class="row">
                <div class="col-md-2">
                    <div class="portlet light">
                        <div class="portlet-body" >
                            <img id="img-profile_img" class="profile-image" src="">
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <div class="portlet light">
                        <div class="portlet-title"><input type="hidden" name="startupId">
                            <div class="caption">
                                <span class="caption-subject bold " id="content-name" data-container="body" data-toggle="popover" data-placement="left" data-content="公司名称" style="color: #333;"></span>
                                &nbsp;

                            </div>
                            <div class="actions">
                                <a id="edit_basic" class="btn btn-circle btn-default hide"  data-toggle="modal" data-target="#modal-basic-info">
                                    <i class="fa fa-pencil"></i> 编辑 </a>
                                <button type="button" class="btn btn-circle btn-success btn-sm"><i class="fa fa-plus"></i> 收藏</button>
                                <a class="btn btn-circle btn-primary" data-toggle="modal" data-target="#modal-claim">
                                    <i class="fa fa-pencil"></i> 认领 </a>
                            </div>
                        </div>
                        <div class="portlet-body">


                            <ul class="list-inline items tags margin-top-10">
                                <i class="fa fa-tags"></i>
                                <span style="margin-right:10px;">TAG</span>
                            </ul>
                            <!--<p id="content-elevator_pitch" data-container="body" data-toggle="popover" data-placement="left" data-content="一句话说明">-->

                            <!--</p>-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-folder-o"></i>
                                <span class="caption-subject bold uppercase"> 基本信息</span>
                                <span class="caption-helper"></span>
                            </div>
                            <div class="actions">
                                <span id="edit-product" class="profile-edit btn btn-circle btn-default hide"  data-toggle="modal" data-target="#modal-info">
                                <i class="fa fa-pencil"></i> 编辑 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="block">
                                <p id="content-product" class="profile-content" data-container="body" data-toggle="popover" data-placement="top" data-content="公司简介">
                                </p>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                        </div>
                    </div>
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-file-image-o"></i>
                                <span class="caption-subject bold uppercase"> 产品图片</span>
                                <span class="caption-helper"></span>
                            </div>
                            <div class="actions">
                                <span id="upload-product-img" class="profile-edit btn btn-circle btn-default hide"  data-toggle="modal" data-target="#modal-upload_product_img">
                                <i class="fa fa-upload"></i> 上传 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="block">
                                <p id="product-imgs" class="profile-content" data-container="body" data-toggle="popover" data-placement="top" >
                                </p>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                        </div>
                    </div>
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <!--<div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-users"></i>
                                <span class="caption-subject bold uppercase"> 创始人</span>
                            </div>
                            <div class="actions">
                                <span class="btn btn-circle btn-default hide" id="edit-founders" data-toggle="modal" data-target="#modal-founders">
                                <i class="fa fa-plus"></i> 添加 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <tbody id="founders_container">

                                    </tbody>
                                </table>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                            <div class="text-center hide" id="showAllFounders">
                                <a href="javascript:void(0);"><i class="fa fa-angle-double-down"></i>&nbsp;加载全部</a>
                                <a href="javascript:void(0);" class="hide"><i class="fa fa-angle-double-up"></i>&nbsp;收 起</a>
                            </div>
                        </div>
                    </div>-->
                    <!-- END SAMPLE TABLE PORTLET-->
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-users"></i>
                                <span class="caption-subject bold uppercase"> 团队信息</span>
                            </div>
                            <div class="actions">
                                <span class="btn btn-circle btn-default hide" id="edit-colleagues" data-toggle="modal" data-target="#modal-colleagues">
                                <i class="fa fa-plus"></i> 添加 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <!--<thead>
                                    <tr class="uppercase">
                                        <th colspan="2">
                                            姓名
                                        </th>
                                        <th colspan="2">
                                            职位
                                        </th>
                                        <th colspan="2">
                                            关注领域
                                        </th>
                                    </tr>
                                    </thead>-->
                                    <tbody id="colleagues_container">
                                    
                        
                                    
                                    </tbody>
                                </table>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                            <div class="text-center hide" id="showAllTeams">
                                <a href="javascript:void(0);"><i class="fa fa-angle-double-down"></i>&nbsp;加载全部</a>
                                <a href="javascript:void(0);" class="hide"><i class="fa fa-angle-double-up"></i>&nbsp;收 起</a>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-map-marker"></i>
                                <span class="caption-subject bold uppercase"> 获投信息</span>
                            </div>
                            <div class="actions">
                                <span class="btn btn-circle btn-default hide" id="edit-investors" data-toggle="modal" data-target="#modal-investors">
                                <i class="fa fa-pencil"></i> 添加 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                   <!-- <thead>
                                    <tr class="uppercase">
                                        <th>
                                            时间
                                        </th>
                                        <th>
                                            金额
                                        </th>
                                        <th>
                                            轮次
                                        </th>
                                        <th>
                                            投资方
                                        </th>
                                        <th>
                                        </th>
                                    </tr>
                                    </thead>-->
                                    <tbody id="investors_container">

                                    </tbody>
                                </table>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                            <div class="text-center hide" id="showAllInvestors">
                                <a href="javascript:void(0);"><i class="fa fa-angle-double-down"></i>&nbsp;加载全部</a>
                                <a href="javascript:void(0);" class="hide"><i class="fa fa-angle-double-up"></i>&nbsp;收 起</a>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-dot-circle-o"></i>
                                <span class="caption-subject bold uppercase">里程碑</span>
                            </div>
                            <div class="actions">
                                <span class="btn btn-circle btn-default hide" id="edit-timeline" data-toggle="modal" data-target="#modal-timeline">
                                    <i class="fa fa-pencil"></i> 编辑 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="timeline">
                                <!-- TIMELINE ITEM -->

                                <!-- END TIMELINE ITEM -->
                            </div>
                            <div class="text-center no-result hide">未收录相关信息</div>
                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->
                    <div class="portlet light" id="reports">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-newspaper-o"></i>
                                <span class="caption-subject bold uppercase">新闻</span>
                            </div>
                            <div class="actions">
                                <span class="btn btn-circle btn-default hide" id="edit-reports" data-toggle="modal" data-target="#modal-reports">
                                <i class="fa fa-pencil"></i> 编辑 </span>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <ul class="media-list">

                                </ul>
                                <div class="text-center no-result hide">未收录相关信息</div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- END PAGE CONTENT INNER -->
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
<div class="modal fade" id="modal-upload_profile_img" tabindex="-1" role="dialog" aria-labelledby="modal-label-upload_profile_img" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-upload_profile_img">上传项目LOGO</h4>
            </div>
            <div class="modal-body">
                <input class="btn" id="file" name="file" type="file">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <!--<input class="btn btn-primary pull-right" id="btn_upload" type="button" value="上 传">-->
                <button class="btn btn-primary pull-right" id="btn_upload" type="button">上 传</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-upload_product_img" tabindex="-1" role="dialog" aria-labelledby="modal-label-upload_profile_img" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-upload_product_img">上传产品图片</h4>
            </div>
            <div class="modal-body">
                <input class="btn" id="file2" name="file" type="file">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <!--<input class="btn btn-primary pull-right" id="btn_upload" type="button" value="上 传">-->
                <button class="btn btn-primary pull-right" id="btn_upload2" type="button">上 传</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-basic-info" tabindex="-1" role="dialog" aria-labelledby="modal-label-basic-info" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-basic-info">基础信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 control-label">名称</label>
                            <div class="col-md-10">
                                <input type="text" name="name" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">微博</label>
                            <div class="col-md-10">
                                <input type="text" name="weibo" class="form-control" placeholder="如：weibo.com/p/1008086855614">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">微信</label>
                            <div class="col-md-10">
                                <input type="text" name="wechat" class="form-control" placeholder="如：dh3773">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">网址</label>
                            <div class="col-md-10">
                                <input type="text" name="website" class="form-control" placeholder="如：www.baidu.com">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">标签</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" placeholder="点击提示标签即添加" id="tag-prompt">
                                <div id="tag-items" class="dropdown-menu suggestion-list" style="left: 16px;top: 23px;">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label"></label>
                            <div class="col-md-10">
                                <ul class="list-inline items tags-box">
                                </ul>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">地区</label>
                            <div class="col-md-5">
                                <select class="form-control" name="province">

                                </select>
                            </div>
                            <div class="col-md-5">
                                <select class="form-control" name="city">

                                </select>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-primary" id="save-basic-info">保 存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-info" tabindex="-1" role="dialog" aria-labelledby="modal-label-basic-info" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-info">基本信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <!--<label class="col-md-2 control-label">简介</label>-->
                            <div class="col-md-12">
                                <textarea  name="brief" class="form-control" rows="6" style="resize: vertical;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-primary" id="save-info">保 存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-founders" tabindex="-1" role="dialog" aria-labelledby="modal-label-founders" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-founders">添加创始人</h4>
            </div>
            <div class="modal-body">
                <div id="modal_founders_contain">

                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-xs-4">
                            <div class="preview_pic_contain hide"><img src="" class="pre_pic"><div class="pre_txt"></div></div>
                            <input class="user_name form-control user-search" ty="1" id="search-users" type="text" value="" placeholder="姓名">
                            <div id="suggestion-founders" class="col-md-12 suggestion-list hide"  style="top:0"></div>
                        </div>
                        <div class="col-xs-6">
                            <div class="input-icon right">
                                <i class="fa " title="请输入正确的邮箱地址"></i>
                                <input class="form-control" placeholder="未注册的用户请输入邮箱邀请" name="invite_email">
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <input class="add_user btn btn-link hide" id="search-add-founders" type="button" value="添加">
                            <input class="btn btn-link invite hide" id="search-invite-founders" type="button" value="邀请">
                            <input class="invite_user btn btn-link hide" type="button" value="邀请">
                            <input class="user_invited btn btn-link hide" type="button" value="re add Invited">
                            <span class="already_exist hide">已存在</span>
                        </div>
                    </div><br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
                <!--<button type="button" class="btn btn-primary" id="save-founders">保 存</button>-->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-colleagues" tabindex="-1" role="dialog" aria-labelledby="modal-label-colleagues" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-colleagues">添加团队信息</h4>
            </div>
            <div class="modal-body">
                <div id="modal_colleagues_contain"></div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3 col-xs-3">
                            <div class="preview_pic_contain hide"><img src="" class="pre_pic"><div class="pre_txt"></div></div>
                            <input class="user_name form-control user-search"  ty="1" id="search-users-member" type="text" value="" placeholder="姓名">
                            <div id="suggestion-colleagues" class="col-md-12 suggestion-list hide"  style="top:0;width: 270px;"></div>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            <input class="form-control" placeholder="职位" name="invite_postion">
                        </div>
                        <div class="col-md-4 col-xs-3">
                            <div class="input-icon right">
                                <i class="fa " title="请输入正确的邮箱地址"></i>
                                <input class="form-control" placeholder="邮箱" name="invite_email">
                            </div>
                        </div>
                        <div class="col-md-2 col-xs-3">
                            <input class="add_user btn btn-link hide" id="search-add-colleagues" type="button" value="添加">
                            <input class="btn btn-link invite hide" id="search-invite-colleagues" type="button" value="邀请">
                            <input class="invite_user btn btn-link hide" type="button" value="邀请">
                            <input class="user_invited btn btn-link hide" type="button" value="re add Invited">
                            <span class="already_exist hide">已存在</span>
                        </div>
                    </div><br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
                <!--button type="button" class="btn btn-primary" id="save-colleagues">保 存</button>-->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-investors"  tabindex="-1" role="dialog" aria-labelledby="modal-label-investors" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-investors">添加获投信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 control-label">获投时间<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-9">
                                <input class="form-control input-lg funding_date" name="investmentTime" size="16" type="text" readonly placeholder="请选择时间">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">投资金额<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-lg" name="investmentMoney" placeholder="如：6000万美元">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">获投轮次<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-9">
                                <select class="form-control input-lg" name="round">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">投资方<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-lg user-search" ty="3" name="investorName" placeholder="请输入投资方">
                                <div class="col-md-12 suggestion-list suggestion-investors hide" style="top: 0;"></div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-minus-circle del-inv-event-input hide"></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <div  class="col-md-12 text-center">
                                <span class="add-inv-event-input">再添加一个投资方</span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                 <button type="button" class="btn btn-primary" id="save-investors">保 存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-timeline" tabindex="-1" role="dialog" aria-labelledby="modal-label-timeline" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-timeline">编辑里程碑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row source">
                        <div class="form-group" style="margin-left: 15px;">
                            <div class="col-xs-4">
                                <input class="timeline_title form-control" type="text" name="timeline_title" placeholder="标题">
                            </div>
                            <div class="col-xs-4">
                                <input class="timeline_url form-control" type="text" name="timeline_url" placeholder="URL">
                            </div>
                            <div class="input-group date form-date col-xs-2 pull-left" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control timeline_date" size="16" type="text" name="timeline_date" readonly="" placeholder="选择日期">
                            </div>
                            <div class="col-xs-1">
                                <input class="add_timeline btn btn-link" type="button" value="添加">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-primary" id="save-timeline">保 存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-reports" tabindex="-1" role="dialog" aria-labelledby="modal-label-reports" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-reports">编辑新闻</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row source">
                        <div class="form-group" style="margin-left: 15px;">
                            <div class="col-xs-4">
                                <input class="timeline_title form-control" type="text" name="press_title" placeholder="标题">
                            </div>
                            <div class="col-xs-4">
                                <input class="timeline_url form-control" type="text" name="press_url" placeholder="URL">
                            </div>
                            <div class="input-group date form-date col-xs-2 pull-left" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control press_date" size="16" type="text" name="press_date" readonly="" placeholder="选择日期">
                            </div>
                            <div class="col-xs-1">
                                <input class="add_press btn btn-link" type="button" value="添加">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-primary" id="save-reports">保 存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-claim" tabindex="-1" role="dialog" aria-labelledby="modal-label-claim" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal-label-claim">认领项目</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="claim-form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="control-label col-md-2">真实姓名<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-4">
                                <input type="text" name="name" class="form-control" placeholder="">
                            </div>
                            <label class="control-label col-md-2">邮箱<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-4">
                                <input type="text" name="email" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">手机号码<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-4">
                                <input type="text" name="mobile" class="form-control" placeholder="">
                            </div>
                            <label class="control-label col-md-2">职位<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-4">
                                <input type="text" name="position" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">个人名片<span class="required" aria-required="true">*</span></label>
                            <div class="col-md-4">
                                <span class="btn green fileinput-button">
										<span>
										选择照片</span>
										<input id="fileupload" type="file" name="fileData">
								</span>
                                <input type="hidden" name="ownerCard"/>
                            </div>
                        </div>
                        <div class="form-group mp" id="claim-mp">

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <input class="btn btn-primary pull-right" id="claim-save" type="button" value="认 领">
            </div>
        </div>
    </div>
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
<script src="../libs/metronic/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/fancybox/lib/jquery.mousewheel-3.0.6.pack.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../libs/jquery.showLoading.min.js"></script>
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
<script src="../js/datarea.js" type="text/javascript"></script>
<script src="../js/utils.js" type="text/javascript"></script>
<script src="../js/language.js" type="text/javascript"></script>
<script src="../js/configs.js" type="text/javascript"></script>
<script src="../js/hiekn.ajax.js" type="text/javascript"></script>
<script src="../js/default.js" type="text/javascript"></script>
<script src="../js/startup.js" type="text/javascript"></script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>