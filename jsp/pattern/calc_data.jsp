<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>

<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

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
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");

String ptnCd = request.getParameter("pattern_cd");

//==�@�v�Z�@===
PatternMgr pm = new PatternMgr();
// �}���`�X���b�h�Ŏ��s
pm.start(ptnCd);

//pm.calcPattern(ptnCd);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>�f�[�^�v�Z</title>
</head>
<body>

<br><br>
�������J�n���܂���

<br>
<a href="pattern_list.jsp">�p�^�[���ꗗ��</a>
<br>
<a href="/bear2Web/jsp/dashbord/statistics_list.jsp">���͈ꗗ</a>
<br>
<a href="/bear2Web/jsp/market_admin/market_list.jsp">�}�[�P�b�g�ꗗ��</a>

</body>
</html>

