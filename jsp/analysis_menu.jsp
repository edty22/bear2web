<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//���O�C�����̃����o�[�̂݃A�N�Z�X�\
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//���O�C�����s
	//�F�؂��Ă��܂���
	response.sendRedirect("login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");
//request.setCharacterEncoding("UTF-8");


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���̓V�X�e�� ���j���[</title>
</head>
<body>


<h2>���̓V�X�e�� ���j���[</h2>

<!-- ���j���[�̈� -->

<table width="260" bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF"><b>���͊Ǘ�</b></td>
  </tr>
  <tr>
    <td bgcolor="F0F8FF">
      <table>
        <tr>
          <td><a href="dashbord/statistics_list.jsp">���̓��X�g</a></td>
        </tr>
        <tr>
          <td><a href="dashbord/oracle_data.jsp">�\���f�[�^</a></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table width="260" bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF"><b>�Ǘ��c�[��</b></td>
  </tr>
  <tr>
    <td bgcolor="F0F8FF">
      <table>
        <tr>
          <td><a href="market_admin/market_list.jsp" target="_blank">�}�[�P�b�g�}�X�^�Ǘ�</a></td>
        </tr>
        <tr>
          <td><a href="pattern/pattern_list.jsp">���̓p�^�[���Ǘ�</a></td>
        </tr>

        <tr>
          <td><a href="link_list.jsp" target="_blank">�����N�W</a></td>
        </tr>

      </table>
    </td>
  </tr>
</table>



</body>
</html>

