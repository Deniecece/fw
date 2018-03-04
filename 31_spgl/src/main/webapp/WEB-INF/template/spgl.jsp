<%@page pageEncoding="utf-8"%>
<html>
	<head></head>
	<body>
		<script>
			$(function(){
				$('#table').datagrid({
					url:'go_data.html',
					columns:[[
					          {field:'pno',title:'商品编号',width:'200',align:'center'},
					          {field:'pname',title:'商品名称',width:'200',align:'center'},
					          {field:'zjm',title:'助记码',width:'200',align:'center'},
					          {field:'dw',title:'单位',width:'180',align:'center'},
					          {field:'xh',title:'型号',width:'150',align:'center'}
					          ]],
					fit:true,
					fitColumns:true,
					rownumbers:true,
					pagination:true,
					striped:true,
					frozenColumns:1,
					toolbar:[
					         {
					        	 id:'excelbtn',
					        	 text:'批量导入',
					        	 iconCls:'icon-edit',
					        	 onClick:function(){
					        		 $('#fileUpload').dialog({
					        			 title:'批量导入',
					        		 	 width:300,
					        		 	 height:100,
					        		 	 buttons:[{
					        		 		 text:'提交',
					        		 	 	 iconCls:'icon-ok',
					        		 	 	 onClick:function(){
					        		 	 		 $('#fileForm').form('submit',{
					        		 	 			 //回调函数
					        		 	 			 success:function(){
					        		 	 				 $.messager.show({
					        		 	 					 title:'提示',
					        		 	 				 	msg:'导入成功'
					        		 	 				 })
					        		 	 				 $('#table').datagrid('load')
					        		 	 			 }
					        		 	 		 })
					        		 	 	 }
					        		 	 }],
					        		 	 //打开窗口
					        		 	 onOpen:function(){
					        		 		 $('#file').filebox({
					        		 			 required:true
					        		 		 })
					        		 	 }
					        		 })
					        		 
					        	 }
					         },{
					        	 id:'removebtn',
					        	 text:'删除',
					        	 iconCls:'icon-remove',
					        	 onClick:function(){
					        		 $.messager.confirm('提示','是否确认删除？',function(f){
					        			 if(f){
					        				 var url = 'go_delete.html?';
							        		 var rs = $('#table').datagrid('getSelections');
							        		 for(var i=0;i<rs.length;i++){
							        			 url+='pno='+rs[i].pno+'&';
							        		 }
							        		 alert(url)
							        		$.post(url,{},function(){
							        			$.messager.show({
							        				title:'提示',
							        				msg:'删除成功'
							        			})
							        			$('#table').datagrid('reload')
							        		})
					        			 }
					        		 })
					        	 }
					         }],
					   onLoadSuccess:function(){
					   		$('#removebtn').linkbutton('disable')
					   },
					   onClickRow:function(){
						   var rows = $('#table').datagrid('getSelections');
					   		if(rows.length==0){
					   			$('#removebtn').linkbutton('disable')
					   		}else{
					   			$('#removebtn').linkbutton('enable')
					   		}
					   }
				})
			})
		</script>
		<table id="table"></table>
		<div id="fileUpload">
			<form id="fileForm" action="go_productUpload.html" method="post" enctype="multipart/form-data">
				<input id="file" name="file"/>
			</form>
		</div>
	</body>
</html>