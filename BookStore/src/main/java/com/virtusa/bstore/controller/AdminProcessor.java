package com.virtusa.bstore.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.bstore.dto.AdminLogin;
import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.service.AdminService;


@WebServlet("/AdminProcessor")
public class AdminProcessor extends HttpServlet {
	String bName;
	String bAuthor;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String action=request.getParameter("action");
		String target="";
		String bCategory;
		String image;
		int quantity;
		int price;
		String adEmail;
		AdminService service=new AdminService();
		HttpSession sn=request.getSession();
		
		int bId;
		int uId;
		switch(action) {
		case "adLogin":
			String uname=request.getParameter("uname");
			String psw=request.getParameter("psw");
			if(service.validateAdmin(uname, psw)) {
				sn.setAttribute("adMail",uname);
				target="AdminMenu.jsp";
			}
			else {
				target="AdminLogin.jsp";
				request.setAttribute("flag", 1);
			}
			break;
		case "adsignup":
			String adname=request.getParameter("adName");
			adEmail=request.getParameter("adEmail");
			String adPwd=request.getParameter("adPwd");
			service.addAdmin(adname,adEmail,adPwd);
			target="AdminLogin.jsp";
			break;
		case "viewProfile":
			String adminEmail=(String) sn.getAttribute("adMail");
			AdminLogin profile=service.viewProfile(adminEmail);
			request.setAttribute("profile", profile);
			target="AdminProfile.jsp";
			break;
		case "modifyProfile":
			adEmail=(String) sn.getAttribute("adMail");
			String name=request.getParameter("adname");
			String pw=request.getParameter("password");
			if(service.modifyProfile(name, adEmail, pw)) {
				AdminLogin mprofile=service.viewProfile(adEmail);
				request.setAttribute("profile", mprofile);
				target="AdminProfile.jsp";
			}
			break;
		case "viewAdmins":
			ArrayList<AdminLogin> adminList=service.viewAllAdmins();
			request.setAttribute("adminList", adminList);
			target="AdminList.jsp";
			break;
		case "viewBooks":
			ArrayList<Book> blist=service.getBooks();
			sn.setAttribute("blist", blist);
			target="AdminBook.jsp";
			break;
		case "addBook":
			bId=Integer.parseInt(request.getParameter("bId"));
			String bName=request.getParameter("bName");
			String bAuthor=request.getParameter("bAuthor");
			bCategory=request.getParameter("bCategory");
			image=request.getParameter("image");
			quantity=Integer.parseInt(request.getParameter("quantity"));
			price=Integer.parseInt(request.getParameter("bPrice"));
			if(service.addBook(bId, bName, bAuthor, bCategory,quantity, price,image)) {
				ArrayList<Book> alist=service.getBooks();
				sn.setAttribute("blist", alist);
				request.setAttribute("flag",1);
				target="AdminBook.jsp";
			}
			break;
		case "searchCategory":
			bCategory=request.getParameter("bCategory");
			ArrayList <Book> categoryList=service.searchByCategory(bCategory);
			request.setAttribute("categoryList", categoryList);
			target="searchCategory.jsp";
			break;
		case "removeBook":
			bId=Integer.parseInt(request.getParameter("bId"));
			if(service.removeBook(bId)) {
				target="AdminBook.jsp";
				ArrayList<Book> mlist=service.getBooks();
				sn.setAttribute("blist", mlist);
				request.setAttribute("flag",1);
			}
			else {
				request.setAttribute("flag",0);
				target="removeBook.jsp";
			}
			break;
		case "isBookExist":
			bId=Integer.parseInt(request.getParameter("bId"));
			if(service.isBookExist(bId))
			{
				int bprice=service.getBookPrice(bId);
				request.setAttribute("currentPrice", bprice);
				target="modifyBook.jsp";
				sn.setAttribute("eId", bId);
			}else {
				target="failed.jsp";
			}
			break;
		case "addClicked":
			request.setAttribute("flag",1);
			target="AdminBook.jsp";
			break;
		case "updateClicked":
			bId=Integer.parseInt(request.getParameter("bId"));
			Book b=service.searchBook(bId);
			request.setAttribute("book", b);
			request.setAttribute("flag",2);
			target="AdminBook.jsp";
			break;
		case "update":
			try {
			bId=Integer.parseInt(request.getParameter("bId"));
			bName=request.getParameter("bName");
			bAuthor=request.getParameter("bAuthor");
			bCategory=request.getParameter("bCategory");
			quantity=Integer.parseInt(request.getParameter("quantity"));
			price=Integer.parseInt(request.getParameter("bPrice"));
			if(service.modify(bId,bName,bAuthor,bCategory,quantity,price)) {
				ArrayList<Book> ulist=service.getBooks();
				sn.setAttribute("blist", ulist);
				target="AdminBook.jsp";
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			break;
		case "viewOrders":
			ArrayList<Order> olist=service.viewOrders();
			request.setAttribute("olist",olist);
			target="AdminOrderView.jsp";
			break;
		case "viewUsers":
			ArrayList<User> ulist=service.viewAllUsers();
			request.setAttribute("ulist",ulist);
			target="UserList.jsp";
			break;
		case "viewOrderItems":
			int ordId=Integer.parseInt(request.getParameter("ordId"));
			String bMail=request.getParameter("bMail");
			ArrayList<Order> olist1=service.viewOrders();
			request.setAttribute("olist",olist1);
			ArrayList<OrderItem> orderItemList=service.viewOrderItems(ordId, bMail);
			request.setAttribute("ordItemList", orderItemList);
			target="AdminOrderView.jsp";
			break;
		case "updateOrderStatus":
			String []ordIds=request.getParameterValues("ordId");
			if(service.updateOrderStatus(ordIds)) {
				ArrayList<Order> uolist=service.viewOrders();
				request.setAttribute("olist",uolist);
				target="AdminOrderView.jsp";
			}
			break;
		case "removeUser":
			uId=Integer.parseInt(request.getParameter("uId"));
			if(service.removeUser(uId)) {
				ArrayList<User> rlist=service.viewAllUsers();
				request.setAttribute("ulist",rlist);
				target="UserList.jsp";
			}
			
			break;
		case "signout":
			sn.invalidate();
			target="index.html";
			break;
		}
		RequestDispatcher rd=request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
