<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//�N�b�L�[����������
//���[�U�[�����擾���āA���j���[�ɔ�΂�
// �N�b�L�[�̕\��
Cookie[] cookies = request.getCookies();
if (cookies != null) {

	int userAccountId = 0;

	for(int i = 0;i < cookies.length;i++) {
		Cookie c = cookies[i];
		if (c.getName().equals("user_account_id")) {
			userAccountId = Integer.parseInt(c.getValue());
			break;
		}

	}

	UserAccountMgr uam = new UserAccountMgr();
	UserAccount ua = uam.getUserAccountById( userAccountId );

	if (ua != null) {

		//���O�C������
		//�ꗗ��
		session.setAttribute("account" ,ua);
		application.getRequestDispatcher("/jsp/analysis_menu.jsp")
				.forward(request,response);

	}
}

String error_code = request.getParameter("error_code");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���̓V�X�e�� ���O�C��</title>
</head>
<body>

<h2>���̓V�X�e�� ���O�C��</h2>

<form action="login_exe.jsp" method="post">

<%
if (error_code != null) {

	String errorMsg = "";
	if (error_code.equals("001")) {
		errorMsg = "���O�C���ł��܂���ł����A�A�J�E���g�������̓p�X���[�h���Ԉ���Ă��܂��B";
	} else if (error_code.equals("002")) {
		errorMsg = "���O�C�����Ă��܂���";
	}
%>

<font color="red"><b><%= errorMsg %></b></font>

<% } %>

<table border="1">

<tr>
	<td bgcolor="#006699"><font color="#F0F8FF" size="-1"><b>�A�J�E���g</b></font></td>
	<td><input type="text" size="20" name="user_account" value=""></td>
</tr>

<tr>
	<td bgcolor="#006699"><font color="#F0F8FF" size="-1"><b>�p�X���[�h</b></font></td>
	<td><input type="password" size="20" name="user_passwd" value=""></td>
</tr>


</table>
<br>
<input type="submit" value="���O�C��">

</form>

</body>
</html>