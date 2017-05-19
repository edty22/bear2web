<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

String patternCd = request.getParameter("pattern_cd");
String patternName = request.getParameter("pattern_name");
String targetMarketCd = request.getParameter("target_market_cd");
String targetAtype = request.getParameter("target_atype");
String memo = request.getParameter("memo");

//=== オブジェクトにセット ===
Pattern ptn = new Pattern();
ptn.setPatternCd(patternCd);
ptn.setPatternName(patternName);
ptn.setTargetMarketCd(targetMarketCd);
ptn.setTargetAtype(targetAtype);
ptn.setMemo(memo);

//=== データ登録 ===
PatternMgr ptm = new PatternMgr();

try {

	ptm.registPattern(ptn);

	//登録完了 一覧画面へ
	response.sendRedirect("/bear2Web/jsp/pattern/pattern_list.jsp");

} catch (Exception e) {
	e.printStackTrace();

	//TODO エラー処理
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
<body>




<font color="red"><b>登録に失敗しました</b></font>

</body>
</html>