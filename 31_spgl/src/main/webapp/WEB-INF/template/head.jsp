<%@page pageEncoding="utf-8" %>
<html>
	<head>
		<base href="<%= request.getContextPath()%>/"/><!-- serverName:serverPort/ContextPath/servletPath -->
		<link rel="stylesheet" type="text/css" href="a/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="a/themes/icon.css"/>
		<script src="a/jquery.min.js"></script>
		<script src="a/jquery.easyui.min.js"></script>
		<script src="a/locale/easyui-lang-zh_CN.js"></script>
		<script>
			$(document).ready(function(){
				$('#lo').layout({
					fit:true,
				})
				$('#lo').layout('add',{
					region:'north',
					title:'欢迎登陆',
					height:120
				})
				$('#lo').layout('add',{
					title:'菜单',
					region:'west',
					collapsible:false,
					width:120,
					href:'go_tree1.html'
				})
				$('#lo').layout('add',{
					region:'center',
					border:false,
					content:'<div id="center"></div>',
					onOpen:function(){
						$('#center').panel({fit:true})
					}
				})
			})		
		</script>
	</head>
	<body>
		<div id="lo"></div>
		
	
	</body>
</html>