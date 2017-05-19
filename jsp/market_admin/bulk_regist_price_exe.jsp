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

//String marketCd = request.getParameter("market_cd");
//String bulkOption = request.getParameter("bulk_option");

String marketCd = null;
String bulkOption = null;



//=== �f�[�^�o�^ ===
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
<title>�t�@�C���A�b�v���[�h</title>
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
						out.println("�A�b�v���[�h�t�@�C��: " + filePath + fileName + "<br>");

					}

					//���ʂ̃p�����[�^
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

				//��������DB�X�V
				PriceFileLoader pfl = new PriceFileLoader();
				pfl.setFilePath(filePath + fileName);
				pfl.setMarketCode(marketCd);
				pfl.setBulkOption(bulkOption);
				pfl.loadPriceFile();

				out.println("�f�[�^�X�V�I��: <br>");
%>

<br><br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">���i�ꗗ��</a>
<br>
<a href="market_list.jsp">�}�[�P�b�g�ꗗ��</a>

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
<title>�t�@�C���A�b�v���[�h</title>
</head>
<body>
<p>�t�@�C�������݂��܂���ł���</p>

<br><br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">���i�ꗗ��</a>
<br>
<a href="market_list.jsp">�}�[�P�b�g�ꗗ��</a>

</body>
</html>

<%

	   }

	//�o�^���� �ꗗ��ʂ�
	//response.sendRedirect("price_list.jsp?market_cd=" + marketCd + "&msg_code=001");

} catch (Exception e) {
	e.printStackTrace();

	//TODO �G���[����
}

%>
