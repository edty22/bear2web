<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%@page import="java.text.*"%>
<%
//���O�C�����̃����o�[�̂݃A�N�Z�X�\
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//���O�C�����s
	//�F�؂��Ă��܂���
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");

//== �R�[�h�̎擾
String marketCd = request.getParameter("market_cd");

MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���̓V�X�e��</title>
</head>
<body>


<table width="100%">
<tr>
  <td nowrap="nowrap">
    <font size="4"><b>���i�ꗗ</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>

<br>
<a href="/bear2Web/jsp/market_admin/market_list.jsp" target="_blank">�}�[�P�b�g�}�X�^�Ǘ�</a>
<br>
<a href="regist_price.jsp?market_cd=<%= marketCd %>"><b>�V�K���i�ǉ�</b></a>

<font size="-1">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>�}�[�P�b�g<br>�R�[�h</th>
    <th nowrap>���t</th>
    <th nowrap>�n�l</th>
    <th nowrap>���l</th>
    <th nowrap>���l</th>
    <th nowrap>�I�l</th>
    <th nowrap>�o����</th>
    <th width="40">&nbsp;</th>
    <th width="40">&nbsp;</th>
  </tr>

<%
PriceMgr pm = new PriceMgr();

List<Price> prices = pm.getAllPricesByCd(marketCd);

//�}�[�P�b�g�ꗗ�\��

for (Iterator<Price> it = prices.iterator(); it.hasNext();) {

	Price price = (Price)it.next();
%>

  <tr>
    <td bgcolor="#FFFFFF" nowrap><%= price.getMarketCd() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getRegistDate() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getStartPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getHighPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getLowPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getEndPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getVolume() != 0 ? price.getVolume() : "&nbsp" %></td>

    <td bgcolor="#FFFFFF">
    	<input type="button" value="�ύX"
        	onClick="document.location.href='update_price.jsp?market_cd=<%= price.getMarketCd() %>&regist_date=<%= price.getRegistDate() %>';">
    </td>
    <td bgcolor="#FFFFFF">
    	<input type="button" value="�폜"
        	onClick="document.location.href='delete_price.jsp?market_cd=<%= price.getMarketCd() %>&regist_date=<%= price.getRegistDate() %>';">
    </td>
  </tr>

<%
}
%>
</table>
</font>

</body>
</html>

