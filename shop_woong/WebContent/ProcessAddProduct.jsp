<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String saveDirectory = application.getRealPath("/resources/images"); 
	int maxPostSize = 1024 * 1024;	// 파일 최대 용량 1MB
	String encoding = "utf-8";
	
	
	MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());

	String productId = mr.getParameter("productId");
	String pname = mr.getParameter("pname");
	int unitPrice = Integer.parseInt(mr.getParameter("unitPrice"));
	String description = mr.getParameter("description");
	String manufacturer = mr.getParameter("manufacturer");
	String category= mr.getParameter("category");
	long unitsInStock= Long.parseLong(mr.getParameter("unitsInStock"));
	String condition= mr.getParameter("condition");
	String productImage = mr.getFilesystemName("productImage");
	
	File photoFile = new File(saveDirectory, File.separator + productImage);
	
	ProductRepository pr = new ProductRepository();
	Product newProduct = new Product();
	
	newProduct.setProductId(productId);
	newProduct.setPname(pname);
	newProduct.setUnitPrice(unitPrice);
	newProduct.setDescription(description);
	newProduct.setManufacturer(manufacturer);
	newProduct.setCategory(category);
	newProduct.setUnitsInStock(unitsInStock);
	newProduct.setCondition(condition);
	newProduct.setProductImage(productImage);
	
	
	pr.addProduct(newProduct);
	response.sendRedirect("products.jsp");
%>