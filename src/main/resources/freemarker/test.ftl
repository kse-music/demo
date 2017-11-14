<html>
<#assign upper = "com.hiekn.demo.util.FtlDirective"?new()>
<body>
    <h1>Welcome ${test}</h1>
    
    <#assign a="ksemusic" />
    ${a[0..2]}
    

    <#assign b=2>
    <#assign c=5>
    <#if b < 3>
    	haha
    </#if>
    
    
   <@upper>
	  bar
	  <#-- All kind of FTL is allowed here -->
	  <#list ["red", "green", "blue"] as color>
	    ${color}
	  </#list>
	  baaz
	</@upper>
	
	<#list File.listRoots() as fileSystemRoot>
		${fileSystemRoot}
	</#list>
	
	${statics["java.lang.System"].currentTimeMillis()}
	
	${enums["java.math.RoundingMode"].UP}
	
	<#assign x = "<test>">
	<#macro m1>
	  m1: ${x}
	</#macro>
	
	<#escape x as x?html>
	  <#macro m2>m2: ${x}</#macro>
	  ${x}
	  <@m1/>
	</#escape>
	${x}
	<@m2/>
	
	<#macro list title items>
	  <p>${title?cap_first}:
	  <ul>
	    <#list items as x>
	      <li>${x?cap_first}
	    </#list>
	  </ul>
	</#macro>
	<@list items=["mouse", "elephant", "python"] title="animals"/>
	
	<#macro repeat count>
	  <#list 1..count as x>
	    <#nested x, x/2, x==count>
	  </#list>
	</#macro>
	
	<@repeat count=4 ; c, halfc, last>
	  ${c}. ${halfc}<#if last> Last!</#if>
	</@repeat>
	
	${request.requestURL}
</body>
</html> 