<%@page pageEncoding="utf-8"%>
<body>
<script>
	$(function(){
		$('#t').tree({
			data:[{
				text:'基本信息管理',
				children:[{
					text:'商品管理',
					href:'go_spgl1.html'
				},{
					text:'供应商管理',
					href:''
				},{
					text:'库房管理',
					href:''
				}]
			},{text:'经营管理',
				children:[{
					text:'进货管理',
					href:''
				},{
					text:'退货管理',
					href:''
				},{
					text:'销售管理',
					href:''
				}]
			},{}],
			animate:true,
			singleSelect:true,
			onClick:function(node){
				if(node.children==null){
					$('#center').panel({
						title:node.text,
						fit:true,
						href:node.href
					})
				}else{
					$('#t').tree('toggle',node.target)
				}
			}
		})
	})
</script>
	<div id="t"></div>
</body>