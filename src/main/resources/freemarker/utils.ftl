 <#function cutStr str len>
 <#if str??>
  <#if (str?length > len)>
  	<#return str[0..len] />
  <#else>
  	<#return str />
  </#if>
  </#if>
</#function>