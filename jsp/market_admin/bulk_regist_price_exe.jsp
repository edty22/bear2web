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
//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

//String marketCd = request.getParameter("market_cd");
//String bulkOption = request.getParameter("bulk_option");

String marketCd = null;
String bulkOption = null;



//=== データ登録 ===
PriceMgr pm = new PriceMgr();

try {

	   File file ;
	   int maxFileSize = 5000 * 1024;
	   int maxMemSize = 5000 * 1024;
	   ServletContext context = pageContext.getServletContext();
	   //String filePath = context.getInitParameter("file-upload");
	   String filePath = "c:/work/fileupload/";
	   String fileName = null;


	   // Verify the content type
	   String contentType = request.getContentType();
	   if ((contentType.indexOf("multipart/form-data") >= 0)) {

	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:/work/fileupload"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      try{
	         // Parse the request to get file items.
	         List fileItems = upload.parseRequest(request);

	         // Process the uploaded file items
	         Iterator i = fileItems.iterator();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>ファイルアップロード</title>
</head>
<body>

<%
				while (i.hasNext()) {

					FileItem fi = (FileItem) i.next();

					if (!fi.isFormField()) {

						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						fileName = fi.getName();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();

						// Write the file
						if (fileName.lastIndexOf("\\") >= 0) {

							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));

						} else {

							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));

						}

						fi.write(file);
						out.println("アップロードファイル: " + filePath + fileName + "<br>");

					}

					//普通のパラメータ
					if (fi.isFormField()) {

						String paraName = fi.getFieldName();
						String paraValue = fi.getString();

						if (paraName.equals("market_cd")) {
							marketCd = paraValue;
						}

						if (paraName.equals("bulk_option")) {
							bulkOption = paraValue;
						}

					}

				}

				//ここからDB更新
				PriceFileLoader pfl = new PriceFileLoader();
				pfl.setFilePath(filePath + fileName);
				pfl.setMarketCode(marketCd);
				pfl.setBulkOption(bulkOption);
				pfl.loadPriceFile();

				out.println("データ更新終了: <br>");
%>

<br><br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">価格一覧へ</a>
<br>
<a href="market_list.jsp">マーケット一覧へ</a>

</body>
</html>

<%
	      }catch(Exception ex) {
	         System.out.println(ex);
	      }
	   }else{
%>

<html>
<head>
<title>ファイルアップロード</title>
</head>
<body>
<p>ファイルが存在しませんでした</p>

<br><br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">価格一覧へ</a>
<br>
<a href="market_list.jsp">マーケット一覧へ</a>

</body>
</html>

<%

	   }

	//登録完了 一覧画面へ
	//response.sendRedirect("price_list.jsp?market_cd=" + marketCd + "&msg_code=001");

} catch (Exception e) {
	e.printStackTrace();

	//TODO エラー処理
}

%>
