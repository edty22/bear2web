<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//���O�C�����̃����o�[�̂݃A�N�Z�X�\
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//���O�C�����s
	//�F�؂��Ă��܂���
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== �K�v�ȃf�[�^���o ===
//== �R�[�h�̎擾
String marketCd = request.getParameter("market_cd");

MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

//== �����̓��t
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String strDate = sdf.format(cal.getTime());

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���i�ꊇ�o�^</title>
</head>
<body>


<h2>���i���ꊇ�o�^</h2>

<form name="form1"  enctype="multipart/form-data"  action="bulk_regist_price_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- ���̓t�H�[������ -->
		<td width="500">

			<!-- ��{��� -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�}�[�P�b�g�R�[�h</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="market_cd" size="20" value=<%= marketCd %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�t�@�C��</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="file" name="file"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�A�b�v���[�h�I�v�V����</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="radio" name="bulk_option" value="ALL" checked>�􂢑ւ�
						<input type="radio" name="bulk_option" value="DIFF">����
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>
<br>
<input type="submit" value="�A�b�v���[�h">

</form>

<br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">���i�ꗗ��</a>
<br>
<a href="market_list.jsp">�}�[�P�b�g�ꗗ��</a>



</body>
</html>