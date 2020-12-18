<%@ page import="com.tywh.egov.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tywh.egov.utils.ConfigUtil" %>
<%@ page import="com.tywh.egov.utils.PageModel" %>
<%@page pageEncoding="GB18030"%>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<%
  PageModel<User> pageModel = (PageModel<User>) request.getAttribute("pageModel");
  List<User> userList = pageModel.getDataList();
  Integer totalRecods = pageModel.getTotalRecords();
  Integer totalpages = pageModel.getTotalPages();
  Integer pageSize = pageModel.getPageSize();
  Integer pageNo = pageModel.getPageNo();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12px}

-->
</style>

<script>
    function toPage(pageNo) {
        document.location = "/controller/user/query?pageNo=" + pageNo;
    }

    function chooseAll(field) {
        var checkboxArr = document.getElementsByName("checkbox");
        for (var i=0; i<checkboxArr.length; i++) {
            checkboxArr[i].checked = field.checked;
        }
      chooseOne();
    }

    function chooseOne() {
        var checkboxArr = document.getElementsByName("checkbox");
        var count = 0;

      for (var i=0; i<checkboxArr.length; i++) {
          if (checkboxArr[i].checked) {
              count++;
          }
      }

      if (count == 0) {
        document.getElementById("updateButton").src = "../images/update_disabled.jpg";
        document.getElementById("deleteButton").src = "../images/delete_disabled.jpg";
        document.getElementById("updateButton").disabled = true;
        document.getElementById("deleteButton").disabled = true;
      }

      if (count == 1) {
        document.getElementById("updateButton").src = "../images/update.jpg";
        document.getElementById("deleteButton").src = "../images/delete.jpg";
        document.getElementById("updateButton").disabled = false;
        document.getElementById("deleteButton").disabled = false;

      }

      if (count > 1) {
        document.getElementById("updateButton").src = "../images/update_disabled.jpg";
        document.getElementById("deleteButton").src = "../images/delete.jpg";
        document.getElementById("updateButton").disabled = true;
        document.getElementById("deleteButton").disabled = false;
      }

      if (count == checkboxArr.length) {
          document.getElementById("checkbox62").checked = true;
      } else {
          document.getElementById("checkbox62").checked = false;
      }
    }

    function toUpdate() {
       var checkBoxArr =  document.getElementsByName("checkbox");
       var usercode = "";
       for (var i=0; i<checkBoxArr.length; i++) {
           if (checkBoxArr[i].checked) {
               usercode = checkBoxArr[i].value;
               break;
           }
       }
       document.location = "/controller/user/toupdate?usercode=" + usercode + "&pageNo=" + <%=pageNo%>;
    }

    function refresh() {
        chooseOne();
    }

    function doDelete() {
        if (window.confirm("确认删除吗?")) {
          document.getElementById("userForm").submit();
        }
    }
</script>
</head>

<body onload="refresh()">
<form action="/controller/user/delete" method="post" id="userForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" /> <span class="STYLE4">系统用户列表</span></td>
        <td width="281" background="../images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="../images/add.jpg" style="cursor:hand" onclick="document.location='/system/userAdd.html'"/></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img disabled id="updateButton" src="../images/update_disabled.jpg" style="cursor:hand" onclick="toUpdate()"/></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img disabled id="deleteButton" src="../images/delete_disabled.jpg" style="cursor:hand" onclick="doDelete()"/></div></td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68" >
          <tr>
            <td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><input type="checkbox" name="checkbox62" value="checkbox" id="checkbox62" onclick="chooseAll(this)"/></div></td>
            <td width="8%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="12%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户代码</div></td>
            <td width="24%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">用户姓名</div></td>
            <td width="38%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">机构类型</div></td>
          </tr>
          <%
              int i = 0;
              for(User user : userList) {
                i++;
          %>
              <tr>
                <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
                  <input name="checkbox" type="checkbox" class="STYLE2" value="<%=user.getUsercode()%>" onclick="chooseOne()"/>
                </div></td>
                <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=i%></div></td>
                <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getUsercode()%></div></td>
                <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getUsername()%></div></td>
                <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#"><%=ConfigUtil.getConfigValue(user.getOrgtype(),"com.tywh.egov.properties.orgtype")%></a></div></td>
              </tr>
          <%
              }
          %>


        </table></td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
        <td background="../images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共<%=totalRecods%>条纪录，当前第<%=pageNo%>/<%=totalpages%>页，每页<%=pageSize%>条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/firstPage<%=pageNo>1 ? "" : "Disabled"%>.gif" <%=pageNo<=1 ? "" : "style = 'cursor:pointer' onclick='toPage(1)'"%> /></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/prevPage<%=pageNo>1 ? "" : "Disabled"%>.gif"  <%=pageNo<=1 ? "" : "style = 'cursor:pointer' onclick='toPage(" + (pageNo - 1) + ")'"%> /></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/nextPage<%=pageNo<totalpages ? "" : "Disabled"%>.gif" <%=pageNo>=totalpages ? "" : "style = 'cursor:pointer' onclick='toPage(" + (pageNo + 1) + ")'"%> /></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/lastPage<%=pageNo<totalpages ? "" : "Disabled"%>.gif" <%=pageNo>=totalpages ? "" : "style = 'cursor:pointer' onclick='toPage(" + totalpages + ")'"%> /></div></td>
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="textfield" id="textfield" type="text" class="STYLE1" style="height:14px; width:25px;text-align:right" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><img src="../images/go.gif" width="37" height="15" onclick="toPage(document.getElementById('textfield').value)"/></td>
                </tr>
              </table>
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="../images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
