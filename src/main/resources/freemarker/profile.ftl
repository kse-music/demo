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
    <title>个人中心|nodeDATA</title>
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
    <link href="../libs/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
    <link href="../libs/metronic/assets/admin/pages/css/profile.css" rel="stylesheet" type="text/css"/>
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
    <link href="../css/profile.css" rel="stylesheet" type="text/css">
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
                                <a href="javascript:;">查看全部</a>
                            </li>
                            <li>
                                <ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">

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
                         <a href="javascript:void(0);" class="btn submit"><i class="icon-magnifier"></i></a>
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
    <div class="page-head">
        <div class="container">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h1>个人中心 <small></small></h1>
            </div>
            <!-- END PAGE TITLE -->
        </div>
    </div>
    <!-- END PAGE HEAD -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="modal-inv-event" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">投资组合</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">投资项目</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control input-lg" name="startupName" placeholder="投资项目">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">轮次</label>
                                        <div class="col-md-9">
                                            <select class="form-control input-lg" name="round">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">时间</label>
                                        <div class="col-md-9">
                                            <input class="form-control input-lg date-picker" name="investmentTime" size="16" type="text" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">投资金额</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control input-lg" name="investmentMoney" placeholder="如：6000万美元">
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">保 存</button>
                            <button type="button" class="btn default" data-dismiss="modal">取 消</button>
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
            </ul>
            <!-- END PAGE BREADCRUMB -->
            <!-- BEGIN PAGE CONTENT INNER -->
            <div class="row margin-top-10">
                <div class="col-md-12">
                    <!-- BEGIN PROFILE SIDEBAR -->
                    <div class="profile-sidebar">
                        <!-- PORTLET MAIN -->
                        <div class="portlet light profile-sidebar-portlet">
                            <!-- SIDEBAR USERPIC -->
                            <div class="profile-userpic">
                                <img id="img-profile_img" class="img-responsive profile-image" src="" alt="" data-toggle="modal" data-target="#modal-upload_profile_img">
                            </div>
                            <!-- END SIDEBAR USERPIC -->
                            <!-- SIDEBAR USER TITLE -->
                            <div class="profile-usertitle">
                                <div id="content-name" class="profile-usertitle-name" data-container="body" data-toggle="popover" data-content="真实姓名">

                                </div>
                                <div id="content-introduction" class="profile-usertitle-job" data-container="body" data-toggle="popover" data-content="自我介绍">
                                </div>
                            </div>
                            <!-- END SIDEBAR USER TITLE -->
                            <!-- SIDEBAR BUTTONS -->
                            <div class="profile-userbuttons is-myself">
                                <button type="button" class="btn btn-circle green-haze btn-sm">收藏</button>
                            </div>
                            <!-- END SIDEBAR BUTTONS -->
                            <!-- SIDEBAR MENU -->
                            <div class="profile-usermenu">
                                <ul class="nav" id="daohang">
                                    <li class="active hide">
                                        <a href="javascript:angelnode.reload();"><i class="icon-home"></i><span>主页</span></a>
                                    </li>
                                    <li  class="hide">
                                        <a id="edit_basic"><i class="icon-settings"></i><span>编辑个人信息</span> </a>
                                    </li>
                                    <li id="apply-inv" class="hide">
                                        <a href="/investor/apply"><i class="icon-info"></i><span>申请投资方</span> </a>
                                    </li>
                                    <li class="hide">
                                        <a href="javascript:void(0);" id="my-claim" ><i class="fa fa-tasks"></i><span>我的认领</span></a>
                                    </li>
                                    <li class="hide">
                                        <a href="/startup/new"><i class="fa fa-plus-square-o"></i><span>新增项目</span> </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- END MENU -->
                        </div>
                        <!-- END PORTLET MAIN -->
                        <!-- PORTLET MAIN -->
                        <div class="portlet light">
                            <!-- STAT -->
                            <div class="row list-separated profile-stat">
                                <div class="col-md-4 col-sm-4 col-xs-6">
                                    <div class="uppercase profile-stat-title">
                                        <a  id="e-num" href="javascript:void(0);" tid="1"></a>
                                    </div>
                                    <div class="uppercase profile-stat-text">
                                        收藏创业者
                                    </div>
                                </div>

                                <div class="col-md-4 col-sm-4 col-xs-6">
                                    <div class="uppercase profile-stat-title">
                                        <a  id="p-num" href="javascript:void(0);"  tid="2"></a>
                                    </div>
                                    <div class="uppercase profile-stat-text">
                                        收藏项目
                                    </div>
                                </div>

                                <div class="col-md-4 col-sm-4 col-xs-6">
                                    <div class="uppercase profile-stat-title">
                                        <a  id="i-num" href="javascript:void(0);" tid="3"></a>
                                    </div>
                                    <div class="uppercase profile-stat-text">
                                        收藏投资方
                                    </div>
                                </div>
                            </div>
                            <!-- END STAT -->
                            <div>
                                <h4 class="profile-desc-title">关于</h4>
                                <span class="profile-desc-text">
                                </span>
                                <p class="text-center margin-top-10 hide" id="about-text">
                                    <a href="javascript:void(0)">显示全部</a>
                                    <a href="javascript:void(0)" class="hide">收起</a>
                                </p>
                                <div class="margin-top-20 profile-desc-link to_edit hide" target_form="">
                                    <i class="fa fa-wechat"></i>
                                    <span id="personwechat"></span>
                                </div>
                                <div class="margin-top-20 profile-desc-link to_edit" target_form="">
                                    <i class="fa fa-weibo"></i>
                                    <span id="personweibo"></span>
                                </div>
                                <div class="margin-top-20 profile-desc-link to_edit" target_form="">
                                    <i class="fa fa-globe"></i>
                                    <span id="personweb"></span>
                                </div>
                                <div class="margin-top-20 profile-desc-link to_edit" target_form="">
                                    <i class="fa fa-twitter-square"></i>
                                    <span id="twi"></span>
                                </div>
                                <div class="margin-top-20 profile-desc-link to_edit" target_form="">
                                    <i class="fa fa-facebook-square"></i>
                                    <span id="facebook"></span>
                                </div>

                            </div>
                        </div>
                        <!-- END PORTLET MAIN -->
                    </div>
                    <!-- END BEGIN PROFILE SIDEBAR -->
                    <!-- BEGIN PROFILE CONTENT -->
                    <div class="profile-content">

                        <div class="row hide" id="basic_edit">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title tabbable-line">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">个人信息</span>
                                        </div>
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#tab_1_1" data-toggle="tab">基本信息</a>
                                            </li>
                                            <li>
                                                <a href="#tab_1_2" data-toggle="tab">上传头像</a>
                                            </li>
                                            <li>
                                                <a href="#tab_1_3" data-toggle="tab">修改密码</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="portlet-body form">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab_1_1">
                                                <form role="form" action="#">
                                                    <div class="form-body">
                                                        <div class="form-group">
                                                            <label>真实姓名</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" id="edit-name" placeholder="真实姓名" name="name">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>自我介绍</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" id="edit-elevator_pitch" placeholder="自我介绍" name="brief">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>省</label>
                                                            <div class="input-icon right">
                                                                <select class="form-control input-medium" id="province" name="provinceId">

                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>市</label>
                                                            <div class="input-icon right">
                                                                <select class="form-control input-medium" id="city" name="cityId">

                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>微信</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" placeholder="微信" name="wechat">
                                                            </div>
                                                            <label><input type="checkbox" name="isOpen" value="0"> 公开</label>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>微博</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" placeholder="只需填入微博号，如：102154468" name="weibo">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>个人网站</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" id="edit-person_site" placeholder="个人网站" name="personSite">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Twitter</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" id="edit-twitter" placeholder="只需填入Twitter号" name="twitter">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Facebook</label>
                                                            <div class="input-icon right">
                                                                <input type="text" class="form-control" id="edit-facebook" placeholder="只需填入Facebook号" name="facebook">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-actions">
                                                        <a href="javascript:;" class="btn green-haze" id="save_basic">
                                                            保 存 </a>
                                                        <a href="javascript:void(0);" class="btn default cancel_basic">
                                                            取 消 </a>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="tab-pane" id="tab_1_2">
                                                <p>
                                                </p>
                                                <form action="#" role="form">
                                                    <div class="form-group">
                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
                                                            <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                                                                <img src="../images/default.png" alt=""/>
                                                            </div>
                                                            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
                                                            </div>
                                                            <div>
																<span class="btn default btn-file">
																<span class="fileinput-new">
																选择 </span>
																<span class="fileinput-exists">
																更换 </span>
																<input type="file" name="fileData">
																</span>
                                                                <a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput">
                                                                    移除 </a>
                                                            </div>
                                                        </div>
                                                        <!--<div class="clearfix margin-top-10">
                                                            <span class="label label-danger">注意：</span>
                                                            <span>请确保图片类型为jpg、png、jpeg，大小不超过5MB</span>
                                                        </div>-->
                                                    </div>
                                                    <div class="margin-top-10">
                                                        <a href="javascript:;" class="btn green-haze upload-pic">
                                                            提 交 </a>
                                                        <a href="javascript:;" class="btn default cancel_basic">
                                                            取 消 </a>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="tab-pane" id="tab_1_3">
                                                <form class="changePwd" action="#">
                                                    <label class="control-label">原密码</label>
                                                    <div class="form-group">
                                                        <input type="password" name="opwd" class="form-control"/>
                                                    </div>
                                                    <label class="control-label">新密码</label>
                                                    <div class="form-group">
                                                        <input type="password" name="npwd" class="form-control"/>
                                                    </div>
                                                    <label class="control-label">重复新密码</label>
                                                    <div class="form-group">
                                                        <input type="password" name="rnpwd" class="form-control"/>
                                                    </div>
                                                    <div class="margin-top-10">
                                                        <button type="submit" class="btn green-haze">提 交</button>
                                                        <a href="javascript:;" class="btn default cancel_basic">
                                                            取 消 </a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- END PORTLET -->
                            </div>
                        </div>

                        <div class="row" id="basic_info">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">参与项目</span>
                                            <span class="caption-helper hide"></span>
                                        </div>
                                        <div class="input-group input-medium pull-right" id="project-filter">
                                            <input type="text" class="form-control" placeholder="筛选 名称/职位/日期/领域">
                                           <!-- <span class="input-group-btn">
                                              <button type="submit" class="btn green"><i class="fa fa-search"></i></button>
                                            </span>-->
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-scrollable table-scrollable-borderless">
                                            <div class="text-center hide">没有符合筛选文本的数据</div>
                                            <table class="table table-hover table-light">
                                                <!--<thead>
                                                <tr class="uppercase">
                                                    <th colspan="2">
                                                        项目名称
                                                    </th>
                                                    <th>
                                                        职位
                                                    </th>
                                                    <th>
                                                        成立日期
                                                    </th>
                                                    <th  width="80px;">
                                                        轮次
                                                    </th>
                                                </tr>
                                                </thead>-->

                                                <tbody id="startups">






                                                </tbody>
                                            </table>
                                            <div class="text-center no-result hide">未收录相关信息</div>
                                        </div>
                                        <div class="text-center hide" id="showAllStartup">
                                            <a href="javascript:void(0);"><i class="fa fa-angle-double-down" ></i>加载全部</a>
                                            <a href="javascript:void(0);" class="hide"><i class="fa fa-angle-double-up" ></i>收 起</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- END PORTLET -->
                            </div>
                        </div>

                        <div class="row hide" id="claim-startup">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">认领的项目</span>
                                            <span class="caption-helper hide"></span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-scrollable table-scrollable-borderless">
                                            <table class="table table-hover table-light ">
                                                <thead>
                                                <tr class="uppercase">
                                                    <th>
                                                        项目名称
                                                    </th>
                                                    <th>
                                                        申请日期
                                                    </th>
                                                    <th>
                                                        状态
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                            <div class="text-center no-result hide">你还没有认领任何项目</div>
                                        </div>
                                        <div class="text-center">

                                            <ul class="tcdPageCode">
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row hide" id="claim-investor">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">认领的投资方</span>
                                            <span class="caption-helper hide"></span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-scrollable table-scrollable-borderless">
                                            <table class="table table-hover table-light ">
                                                <thead>
                                                <tr class="uppercase">
                                                    <th>
                                                        投资方
                                                    </th>
                                                    <th>
                                                        申请时间
                                                    </th>
                                                    <th>
                                                        状态
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                            <div class="text-center no-result hide">你还没有认领任何投资方</div>
                                        </div>
                                        <div class="text-center">

                                            <ul class="tcdPageCode">
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row hide" id="collect-tb">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase"></span>
                                            <span class="caption-helper hide"></span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-scrollable table-scrollable-borderless">
                                            <table class="table table-hover table-light hide">
                                                <!--<thead>
                                                <tr class="uppercase">
                                                    <th colspan="2">
                                                        创业者
                                                    </th>
                                                </tr>
                                                </thead>-->
                                                <tbody>

                                                </tbody>
                                            </table>
                                            <table class="table table-hover table-light hide">
                                                <!--<thead>
                                                <tr class="uppercase">
                                                    <th colspan="2">
                                                        项目名称
                                                    </th>
                                                    <th>
                                                        成立日期
                                                    </th>
                                                    <th>
                                                        轮次
                                                    </th>
                                                </tr>
                                                </thead>-->
                                                <tbody>

                                                </tbody>
                                            </table>

                                            <table class="table table-hover table-light hide">
                                                <!--<thead>
                                                <tr class="uppercase">
                                                    <th colspan="2">
                                                        投资方
                                                    </th>

                                                </tr>
                                                </thead>-->
                                                <tbody>

                                                </tbody>
                                            </table>
                                            <div class="text-center no-result hide">你还没有任何收藏！</div>
                                        </div>
                                        <div class="text-center">

                                            <ul class="tcdPageCode">
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                                <!-- END PORTLET -->
                            </div>
                        </div>

                        <div class="row hide" id="invest_info">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light ">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">所属机构</span>
                                            <span class="caption-helper hide"></span>
                                        </div>
                                        <div class="input-group input-medium pull-right">
                                            <!--<input type="text" class="form-control" placeholder="筛选 时间/项目/轮次/币种...">-->
                                           <!-- <span class="input-group-btn">
                                              <button type="submit" class="btn green"><i class="fa fa-search"></i></button>
                                            </span>-->
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-scrollable table-scrollable-borderless">
                                            <table class="table table-hover table-light">
                                                <!--<thead>
                                                <tr class="uppercase">
                                                    <th colspan="2">
                                                        投资方
                                                    </th>
                                                </tr>
                                                </thead>-->
                                                <tbody  id="inv_events">

                                                </tbody>

                                            </table>
                                            <div class="text-center no-result hide">未收录相关信息</div>
                                        </div>
                                        <div class="text-center hide" id="showInvestors">
                                            <a href="javascript:void(0);"><i class="fa fa-angle-double-down" ></i>加载全部</a>
                                            <a href="javascript:void(0);" class="hide"><i class="fa fa-angle-double-up" ></i>收 起</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- END PORTLET -->
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light industry">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">兴趣标签</span>
                                            <span class="caption-helper"></span>
                                        </div>
                                        <!--<div class="actions">
                                            <a class="skill-search-edit btn btn-circle btn-default hide" id="edit-domain">
                                                <i class="fa fa-pencil"></i> 编辑 </a>
                                        </div>-->
                                        <div class="input-group input-medium pull-right" id="tag-prompt">
                                            <input type="text" class="form-control" placeholder="请输入要添加的标签">
                                            <div id="tag-items" class="dropdown-menu suggestion-list" style="top: 23px;width: 100%;">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <ul class="list-inline items tags">
                                        </ul>
                                        <!--<div class="form-actions text-center hide">
                                            <button type="button" class="btn default">取消</button>
                                            <button type="submit" class="btn green">保存</button>
                                        </div>-->
                                        <div class="text-center no-result hide">未收录相关信息</div>
                                    </div>
                                </div>
                                <div class="portlet light skill-role">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">技能角色</span>
                                            <span class="caption-helper"></span>
                                        </div>
                                        <div class="actions">
                                            <a class="role-search-edit btn btn-circle btn-default hide" id="edit-skill-role">
                                                <i class="fa fa-pencil"></i> 编辑 </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="text-center no-result hide">未收录相关信息</div>
                                        <ul class="list-inline items skills">
                                        </ul>
                                        <div class="form-actions text-center hide">
                                            <button type="button" class="btn default">取消</button>
                                            <button type="submit" class="btn green">保存</button>
                                        </div>
                                    </div>
                                </div>

                                <!-- END PORTLET -->
                            </div>
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET -->
                                <div class="portlet light">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">工作经历</span>
                                            <span class="caption-helper"></span>
                                        </div>
                                        <div class="actions">
                                            <a class="profile-edit btn btn-circle btn-default hide" data-toggle="modal" id="add_work">
                                                <i class="fa fa-plus"></i> 添加 </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="text-center no-result hide">未收录相关信息</div>
                                        <table class="table table-hover table-light work-content">
                                            <tbody>

                                            </tbody>
                                        </table>
                                        <div role="form" class="work-form hide">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="control-label">公司<span class="required" aria-required="true"> * </span></label>
                                                    <input type="text" class="form-control" id="company" placeholder="如：百度">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">职位<span class="required" aria-required="true"> * </span></label>
                                                    <input type="text" class="form-control" id="position"  placeholder="如：产品经理">
                                                </div>
                                            </div>
                                            <div class="form-actions text-center">
                                                <button type="button" class="btn default">取消</button>
                                                <button type="submit" class="btn green">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet light">
                                    <div class="portlet-title">
                                        <div class="caption caption-md">
                                            <i class="icon-bar-chart theme-font hide"></i>
                                            <span class="caption-subject bold uppercase">教育经历</span>
                                            <span class="caption-helper"></span>
                                        </div>
                                        <div class="actions">
                                            <a class="profile-edit btn btn-circle btn-default hide" data-toggle="modal" id="add_edu">
                                                <i class="fa fa-plus"></i> 添加 </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="text-center no-result hide">未收录相关信息</div>
                                        <table class="table table-hover table-light edu-content">
                                            <tbody>

                                            </tbody>
                                        </table>
                                        <div role="form" class="edu-form hide">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="control-label">学校<span class="required" aria-required="true"> * </span></label>
                                                    <input type="text" class="form-control" id="college" placeholder="如：清华大学">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">专业<span class="required" aria-required="true"> * </span></label>
                                                    <input type="text" class="form-control" id="professional"  placeholder="如：计算机">
                                                </div>
                                            </div>
                                            <div class="form-actions text-center">
                                                <button type="button" class="btn default">取消</button>
                                                <button type="submit" class="btn green">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- END PORTLET -->
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

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="../libs/metronic/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../libs/metronic/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
<script src="../bower_components/ladda/dist/spin.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/ladda.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../libs/metronic/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/layout.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/demo.js" type="text/javascript"></script>

<script src="../js/jquery.page.js" type="text/javascript"></script>
<script src="../js/datarea.js" type="text/javascript"></script>
<script src="../js/utils.js" type="text/javascript"></script>
<script src="../js/language.js" type="text/javascript"></script>
<script src="../js/configs.js" type="text/javascript"></script>
<script src="../js/hiekn.ajax.js" type="text/javascript"></script>
<script src="../js/default.js" type="text/javascript"></script>
<script src="../js/profile.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo(theme settings page)
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>