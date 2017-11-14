<h3> boolean函数</h3>
<#-- then-->
<#assign x = 10>
<#assign y = 20>
${100 + (x > y)?then(x, y)}

<h3> 字符串函数</h3>
<#-- boolean-->
${(1!=1)?c}

<#-- cap_first,capitalize,uncap_first-->
${"  green mouse"?cap_first}
${"  GReen MOuse"?uncap_first}
${"GreEN mouse"?cap_first}
${"- green mouse"?cap_first}
${"  green  mouse"?capitalize}
${"GreEN mouse"?capitalize}

<#-- contains -->
<#if "piceous"?contains("ice")>It contains "ice"</#if>

<#-- date, time, datetime -->
<#-- <#assign someDate = "Oct 25, 1995"?date>
<#assign someTime = "3:05:30 PM"?time>
<#assign someDatetime = "Oct 25, 1995 03:05:00 PM"?datetime>
${someDate} ${someTime} ${someDatetime}
<#assign someDate = "10/25/1995"?date("MM/dd/yyyy")>
<#assign someTime = "15:05:30"?time("HH:mm:ss")>
<#assign someDatetime = "1995-10-25 03:05 PM"?datetime("yyyy-MM-dd hh:mm a")>
${someDate} ${someTime} ${someDatetime} -->

<#-- ends_with,starts_with -->
${("ahead"?ends_with("head"))?string("true","false")}
${("redirect"?starts_with("red"))?string("true","false")}

<#-- ensure_ends_with,ensure_starts_with -->
${"foo"?ensure_ends_with("/")}
${"foo/"?ensure_ends_with("/")}
${"foo"?ensure_starts_with("/")}
${"foo/"?ensure_starts_with("/")}

<#-- html -->
<#-- index_of,last_index_of -->
${"abcabc"?index_of("bc")}
${"abcabc"?index_of("bc",2)}
${"abcabc"?last_index_of("bc")}

<#-- j_string,js_string,json_string -->
<#assign beanName = 'The "foo" bean.'>
String BEAN_NAME = "${beanName?j_string}";
<#assign user = "Big Joe's \"right hand\"">
<script>
  alert("Welcome ${user?js_string}!");
</script>

<#-- keep_after,keep_after_last,keep_before,keep_before_last -->
${"abcdefgh"?keep_after("de")}
${"foo.bar.txt"?keep_after_last(".")}
${"abcdefgh"?keep_before("de")}
${"foo.bar.txt"?keep_before_last(".")}

<#-- left_pad,right_pad -->
[${""?left_pad(5, "-")}]
[${"a"?left_pad(5, "-")}]
[${"ab"?left_pad(5, "-")}]
[${"abc"?left_pad(5, "-")}]
[${"abcd"?left_pad(5, "-")}]
[${"abcde"?left_pad(5, "-")}]
[${""?right_pad(5, "-")}]
[${"a"?right_pad(5, "-")}]
[${"ab"?right_pad(5, "-")}]
[${"abc"?right_pad(5, "-")}]
[${"abcd"?right_pad(5, "-")}]
[${"abcde"?right_pad(5, "-")}]

<#-- length,lower_case,upper_case -->
<#-- matches -->
${("fooo"?matches('fo*'))?string("yes","no")}

<#-- number -->
<#-- replace -->
${"this is a car acarus"?replace("car", "bulldozer")}

<#-- remove_beginning,remove_ending  -->
${"abcdef"?remove_beginning("abc")}
${"foobar"?remove_beginning("abc")}
${"abcdef"?remove_ending("def")}
${"foobar"?remove_ending("def")}

<#-- rtf:字符串作为富文本(RTF 文本) -->
<#-- split -->
<#list "someMOOtestMOOtext"?split("MOO") as x>
- ${x}
</#list>
<#list "some,,test,text,"?split(",", "r") as x>
- "${x}"
</#list>
<#-- string  -->
<#-- trim -->
(${"  green mouse  "?trim})

<#-- url ,url_path-->
<#setting url_escaping_charset='utf-8'>
<#assign x = 'a/b c'>
${x?url}
${x?url_path}
<#-- <a href="foo.cgi?x=${x?url('ISO-8895-2')}">foo</a> -->

<#-- word_list -->
<#assign words = "   a bcd, .   1-2-3"?word_list>
<#list words as word>[${word}]</#list>

<#-- xhtml,xml -->
<#-- 通用标志 -->
<#assign s = 'foo bAr baar'>
${s?replace('bA', 'XY')}
i: ${s?replace('ba', 'XY', 'i')}
if: ${s?replace('ba', 'XY', 'if')}
r: ${s?replace('ba*', 'XY', 'r')}
ri: ${s?replace('ba*', 'XY', 'ri')}
rif: ${s?replace('ba*', 'XY', 'rif')}