<%@page pageEncoding="GB18030"%>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>外汇业务信息管理平台_用户登录</title>
		<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			overflow:hidden;
		}
		.STYLE1 {
			font-size: 12px;
			color: #a1c8c6;
		}
		.STYLE4 {color: #FFFFFF; font-size: 12px; }
		-->
		</style>
	</head>
	<script src="/js/egov.js" charset="utf-8"></script>
	<script>
		function login() {
			var flag = checkForm();
			if (flag) {
				document.getElementById("loginForm").submit();
			}
		}

		function checkForm() {

			var formItem1 = new FormItem("机构类型","orgtype");
			var formItem2 = new FormItem("用户代码","usercode");
			var formItem3 = new FormItem("用户密码","userpwd");
			var formItems = [formItem1,formItem2,formItem3];
			return $.isEmpty(formItems);

		}
	</script>
	<body>
	<form action="/login" method="post" id="loginForm">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr><td bgcolor="035551">&nbsp;</td></tr>
			<tr>
				<td height="311" background="img/login_03.gif">
					<table width="758" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr><td height="210" background="img/login1.jpg">&nbsp;</td></tr>
						<tr>
							<td height="101">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
		            					<td width="446" height="101" background="img/login_06.jpg">&nbsp;</td>
		            					<td width="156">
		            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
							                		<td width="40%" height="22"><span class="STYLE4">机构类型</span></td>
							                		<td width="71" height="22">
										            <select name="orgtype" style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                                                                          <option value=""></option>
                                                                                          <option value="0" selected>外汇管理局</option>
                                                                                          <option value="1">银行</option>
										            </select>
							                		</td>
							              		</tr>
												<tr>
							                		<td height="22"><span class="STYLE4">用户代码</span></td>
							                		<td width="71" height="22"><input type="text" name="usercode" value="dingrm" id="usercode" style="width:100px; height:20px; border:solid 1px #035551; color:#000000"></td>
							              		</tr>
							              		<tr>
							                		<td height="22"><span class="STYLE4">用户密码</span></td>
							                		<td height="22"><input type="password" name="userpwd" value="123" id="userpwd" style="width:100px; height:22px; border:solid 1px #035551; color:#000000"></td>
							              		</tr>
							              		<tr>
							                		<td height="25">&nbsp;</td>
							                		<td height="25">
														<button style="width:40px;height:25px" onclick="login()">
															<img src="img/dl.gif" width="37" height="19" border="0">
														</button>
														<button style="width:40px;height:25px">
															<img src="img/qx.gif" width="37" height="19">
														</button>
														<%
															String msg = (String)request.getAttribute("msg");
															if (msg != null) {
														%>
														<span><%=msg%></span>
														<%
															}
														%>
													</td>
							                	</tr>
											</table>
										</td>
										<td width="156" background="img/login_09.gif">&nbsp;</td>

		          					</tr>
		        				</table>
		        			</td>
		      			</tr>
		    		</table>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td bgcolor="1f7d78">&nbsp;</td>
		  	</tr>
		  	<tr>
		    	<td bgcolor="1f7d78"><div align="center"><span class="STYLE1">-- 北京动力节点 2012 --</span></div></td>
		  	</tr>
		</table>
	</form>
	</body>
</html>
