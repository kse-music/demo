<#macro showPage page totalpage pageSize toUrl> 
	<#assign url=toUrl> 
	<#if url == "/startups">
			<#assign url = url+"?"> 
		<#else>
			<#assign url = url+"&"> 
	</#if>

    <#assign prev="上一页"> 
    <#assign next="下一页"> 
    <#assign current=page?number> 
    <#assign pageCount=(((totalpage / pageSize)?number)?ceiling)> 
    
    <#-- 上一页处理 -->
    <#if (current == 1)>
		<span class="disabled">${prev}</span>
	  <#else>
		<a href="${url}cpage=${current-1}"class="prevPage">${prev}</a>
	 </#if>
	 
	 <#-- 中间页码 -->
	 <#if (current != 1 && current >= 4 && pageCount != 4)>
	 	<a href="${url}cpage=1" class="tcdNumber">1</a>
	 </#if>
	 <#if (current-2 > 2 && current <= pageCount && pageCount > 5)>
	 	<span>...</span>
	 </#if>
	 
	 <#assign start = current - 2>
	 <#assign end = current + 2>
	 <#if ((start > 1 && current < 4)||current == 1)>
	  	<#assign end = end + 1>
	 </#if>
	 <#if (current > pageCount-4 && current >= pageCount)>
	  	<#assign start = start - 1>
	 </#if>
	
	  <#list start..end as i>
	  	<#if (i <= pageCount && i >= 1)>
		    <#if (i != current)>
				<a href="${url}cpage=${i}" class="tcdNumber">${i}</a>
		  	<#else>
				<span class="current">${i}</span>    
		    </#if>
	  	</#if>
	  </#list>
	 
	 <#if (current + 2 < pageCount - 1 && current >= 1 && pageCount > 5)>
	  	<span>...</span>
	 </#if>
	 <#if (current != pageCount && current < pageCount -2  && pageCount != 4)>
	  	<a href="${url}cpage=${pageCount}" class="tcdNumber">${pageCount}</a>
	 </#if>
    

	 
	 <#-- 下一页处理 -->
    <#if (current < pageCount)>
		<a href="${url}cpage=${current+1}" class="nextPage">${next}</a>
	 <#else>
		<span class="disabled">${next}</span>
	 </#if>
	 
</#macro>

<#macro css >
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../libs/metronic/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
</#macro>
<#macro js >
    <script src="../libs/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="../libs/metronic/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
</#macro>