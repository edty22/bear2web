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
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");

String marketCd = request.getParameter("market_cd");
String registDate = request.getParameter("regist_date");
String startPrice = request.getParameter("start_price");
String highPrice = request.getParameter("high_price");
String lowPrice = request.getParameter("low_price");
String endPrice = request.getParameter("end_price");
String volume = request.getParameter("volume");


//=== �I�u�W�F�N�g�ɃZ�b�g ===
Price price = new Price();
price.setMarketCd( marketCd );
price.setRegistDate( registDate  );
price.setStartPrice( Double.parseDouble( startPrice ) );
price.setHighPrice( Double.parseDouble( highPrice ) );
price.setLowPrice( Double.parseDouble( lowPrice ) );
price.setEndPrice( Double.parseDouble( endPrice ) );
price.setVolume( Double.parseDouble( volume ) );

//=== �f�[�^�o�^ ===
PriceMgr pm = new PriceMgr();

try {

	pm.updatePrice(price);

	//�o�^���� �ꗗ��ʂ�
	response.sendRedirect("price_list.jsp?market_cd=" + marketCd + "&msg_code=001");

} catch (Exception e) {
	e.printStackTrace();

	//TODO �G���[����
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
<body>




<font color="red"><b>�o�^�Ɏ��s���܂���</b></font>

</body>
</html>