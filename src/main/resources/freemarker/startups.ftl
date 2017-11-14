<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <title>项目列表页|nodeDATA</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
     <#import "macros.ftl" as angelnode>
     <#import "utils.ftl" as utils>
     <@angelnode.css /> 
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="../libs/metronic/assets/admin/pages/css/todo.css"/>
    <link href="../bower_components/ladda/dist/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="../libs/metronic/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
    <link href="../css/custom.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="/favicon.ico">
 
</head>
<body>
<#include "header.ftl"/>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>项目 <small>共 <span id="pcount">${rsCount}</span> 项 </small></h1>
            </div>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
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
                </div>
            </div>
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    <a href="/">首页</a><i class="fa fa-circle"></i>
                </li>
                <li class="active">
                    项目
                </li>
            </ul>
            <div class="row">
                <div class="col-md-9">
                    <div class="portlet light">
                        <div class="portlet-body todo-project-list-content">
                            <div class="todo-project-list">
                                <div class="row">
                                    <div class="col-md-1 col-xs-3">
                                        <span style="cursor: default">行业</span>
                                    </div>
                                    <div class="col-md-10  col-xs-7">
                                        <ul class="list-inline c-filter" id="industry">
                                            <li nid="-1"  class="active"><a class="role">全部</a></li>

                                        </ul>
                                    </div>
                                    <div class="col-md-1  col-xs-2">
                                        <span class="more-con-items">更多</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1 col-xs-3">
                                        <span  style="cursor: default">地区</span>
                                    </div>
                                    <div class="col-md-10 col-xs-7" >
                                        <ul class="list-inline c-filter" id="province" >
                                            <li nid="-1"  class="active"><a class="role">全部</a></li>

                                        </ul>
                                    </div>
                                    <div class="col-md-1 col-xs-2" >
                                        <span class="more-con-items">更多</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1 col-xs-3">
                                        <span class="con-name">获投状态</span>
                                    </div>
                                    <div class="col-md-10  col-xs-7">
                                        <ul class="list-inline c-filter" id="acquire_stage">
                                            <li nid="-1"  class="active"><a class="role">全部</a></li>

                                        </ul>
                                    </div>
                                    <div class="col-md-1  col-xs-2">
                                        <span class="more-con-items">更多</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1 col-xs-3">
                                        <span class="con-name">成立时间</span>
                                    </div>
                                    <div class="col-md-10  col-xs-7">
                                        <ul class="list-inline c-filter" id="time">
                                            <li nid="-1"  class="active"><a class="role">全部</a></li>

                                        </ul>
                                    </div>
                                    <div class="col-md-1  col-xs-2">
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
                                                名称
                                            </th>
                                            <th>
                                                成立时间
                                            </th>
                                            <th>
                                                轮次
                                            </th>
                                            <th class="text-center" width="50px">
                                                收藏
                                            </th>
                                        </tr>
                                        </thead>
                                       
                                        <tbody id="startups">
											<#list rsData as v>
												<tr class="m-list">
					                             <td class="fit" style="padding-right: 10px;">
					                             <a href="/startup/${v.id}">
					                             <#if v.image??>
						                             <img class="user-pic" src="${IMG_SRC}${v.image}?imageView2/1/w/55/h/55">
					                             	<#else>
						                             <img class="user-pic" src="${IMG_SRC}${v.companyId}.jpg?imageView2/1/w/55/h/55">
					                             </#if>
					                             </a>
					                             </td>
					                             <td>
					                             <a href="/startup/${v.id}" class="primary-link">${v.name}</a>
					                             <div style="color: #666"> ${utils.cutStr(v.brief,29)}</div>
					                             </td>
					                             <td>
					                             ${v.foundedTime?string("yyyy.MM.dd")}   
					                             </td>
					                             <td>
					                             <#list dicts as x>
					                            	<#if v.phases?? && x.key == v.phases>
					                            		${x.value}
					                            	</#if>
					                             </#list>
					                             </td>
					                             <td class="text-center">
					                             <a href="javascript:void(0);" eid=" ${v.id}'" class="addCollect"><i class="fa fa-star-o m-theme-color"></i></a>
					                             </td>
					                             </tr>
											</#list>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="text-center">
                                	<#if rsCount == 0>
	                                    <div class="no-result">
	                                        	抱歉，没有相关的结果
	                                    </div>
	                                    <#else>
	                                    <ul class="tcdPageCode">
		                                    <@angelnode.showPage page=pageNow totalpage=rsCount pageSize=10 toUrl=toUrl/>
	                                    </ul>
                                    </#if>
                                </div>
                            </div>
                        </div>
                </div>
                <div class="col-md-3">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-suitcase m-hot-i"></i>
                                <span class="caption-subject uppercase m-hot-v">热门项目</span>
                                <span class="caption-helper visible-sm-inline-block visible-xs-inline-block"></span>
                            </div>
                        </div>
                        <div class="portlet-body todo-project-list-content" >
                            <div class="todo-project-list">
                                <ul class="nav nav-pills nav-stacked" id="hot-startup">
                                <#list hotData as v>
									<li title="${v.name}">
										<#assign color="#DFDFDF">
										<#if (v_index == 0) >
											<#assign color="#D92B57">
										<#elseif  (v_index == 1) >
											<#assign color="#E95D54">
										<#elseif  (v_index == 2) >
											<#assign color="#F9953E">
										</#if>
										<#assign bg=color>
										<#if (v_index > 2) >
											<#assign color="#383838">
										</#if>
										<a href="/startup/${v.id}" class="role line-hidden" style="color:${color};">
											<span class="hot-items" style="background:${bg};">${v_index+1}</span>${v.name}<span class="pull-right"></span>
										</a>
									</li>
                                </#list>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl"/>
<@angelnode.js /> 


<script src="../libs/metronic/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/spin.min.js" type="text/javascript"></script>
<script src="../bower_components/ladda/dist/ladda.min.js" type="text/javascript"></script>

<script src="../libs/metronic/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/layout.js" type="text/javascript"></script>
<script src="../libs/metronic/assets/admin/layout3/scripts/demo.js" type="text/javascript"></script>
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
<script src="../js/startups.js" type="text/javascript"></script>
</body>
</html>