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

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.service.UserService;

@WebServlet("/UserProcessor")
public class UserProcessor extends HttpServlet {
	int uId;
	String bCategory;
	int price;
	String buyerEmail;
	String bookName;
	String uEmail;
	String psw;
	String ulocation;
	int age;
	String mNumber;
	String dob;
	int bId;
	String email;
	String img;
	int quantity;
	private static final long serialVersionUID = 1L;
    Logger log=Logger.getLogger(UserProcessor.class);
    String contextPath;
    public void init() {
    	contextPath=getServletContext().getRealPath("/");
    	String logFilePath=contextPath + "WEB-INF\\log4j.properties";
    	PropertyConfigurator.configure(logFilePath);
    }
    public UserProcessor() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String action=request.getParameter("action");
		String target="";
		UserService service=new UserService();
		HttpSession sn=request.getSession();
		switch(action)
		{
		case "userLogin":
			uEmail=request.getParameter("uEmail");
			psw=request.getParameter("psw");
			if(service.validateUser(uEmail, psw)) {
				log.info(uEmail +" logged in Success");
				ArrayList<Book> blist=service.getBooks();
				request.setAttribute("blist", blist);
				target="book.jsp";
				sn.setAttribute("buyerMail", uEmail);
			}else {
				request.setAttribute("flag", 1);
				target="UserLogin.jsp";
			}
			break;
		case "signup":
			uId=Integer.parseInt(request.getParameter("uId"));
			uEmail=request.getParameter("uEmail");
			psw=request.getParameter("uPwd");
			ulocation=request.getParameter("ulocation");
			age=Integer.parseInt(request.getParameter("uAge"));
			mNumber=request.getParameter("uMobileNumber");
			dob=request.getParameter("dob");
			if(service.addUser(uId,uEmail, psw, ulocation, age, mNumber,dob)) {
				log.info(uEmail +"Registerd as New User");
				target="UserLogin.jsp";
			}
			break;
		case "viewBooks":
			log.info("User Viewed the Books");
			ArrayList<Book> blist=service.getBooks();
			request.setAttribute("blist", blist);
			target="book.jsp";
			break;
		case "addCart":
			bId=Integer.parseInt(request.getParameter("bookId"));
			bookName=request.getParameter("bookName");
			price=Integer.parseInt(request.getParameter("bookPrice"));
			buyerEmail=request.getParameter("buyerMail");
			img=request.getParameter("image");
			quantity=service.existInCart(bId,buyerEmail);
			if(quantity!=0){ 
				quantity+=1;
				service.updateQuantity(bId,quantity,buyerEmail);
			}else {
				quantity=1;
				service.addCart(bId,bookName, price,quantity, buyerEmail,img);
			}
			log.info("Added One Item To The Cart");
			request.setAttribute("flag", 1);
			ArrayList<Book> b1list=service.getBooks();
			request.setAttribute("blist", b1list);
			target="book.jsp";
			break;
		case "buyNow":
			bId=Integer.parseInt(request.getParameter("bookId"));
			bookName=request.getParameter("bookName");
			price=Integer.parseInt(request.getParameter("bookPrice"));
			buyerEmail=request.getParameter("buyerMail");
			img=request.getParameter("image");
			quantity=service.existInCart(bId,buyerEmail);
			if(quantity!=0){
				quantity+=1;
				service.updateQuantity(bId,quantity,buyerEmail);
			}else {
				quantity=1;
				service.addCart(bId,bookName, price,quantity, buyerEmail,img);
			}
			ArrayList<Cart> alist=service.viewCartItems(buyerEmail);
			request.setAttribute("clist", alist);
			target="cartItems.jsp";
			break;
		case "removeCartItem":
			buyerEmail=(String) sn.getAttribute("buyerMail");
			bId=Integer.parseInt(request.getParameter("bId"));
			if(service.removeCartItem(bId, buyerEmail)) {
				log.info("Removed Cart Item");
				ArrayList<Cart> rlist=service.viewCartItems(buyerEmail);
				request.setAttribute("clist", rlist);
				target="cartItems.jsp";
			}
			break;
		case "viewCart":
			log.info("user Viewed The Cart");
			buyerEmail=request.getParameter("buyer");
			ArrayList<Cart> clist=service.viewCartItems(buyerEmail);
			request.setAttribute("clist", clist);
			target="cartItems.jsp";
			break;
		case "viewOrders":
			email=(String)sn.getAttribute("buyerMail");
			ArrayList<Order> olist=service.viewOrdersByUser(email);
			request.setAttribute("orderList", olist);
			target="userOrderView.jsp";
			break;
		case "viewOrderItems":
			int ordId=Integer.parseInt(request.getParameter("ordId"));
			email=(String)sn.getAttribute("buyerMail");
			ArrayList<OrderItem> orderItemList=service.viewOrderItems(ordId,email);
			request.setAttribute("ordItemList", orderItemList);
			target="viewOrderItemsByUser.jsp";
			break;
		case "searchCategory":
			bCategory=request.getParameter("bCategory");
			log.info("Searching by Category " +bCategory);
			ArrayList <Book> categoryList=service.searchByCategory(bCategory);
			request.setAttribute("categoryList", categoryList);
			target="book.jsp";
			break;
		case "profile":
			email=(String)sn.getAttribute("buyerMail");
			log.info("User Viewd Their Profile");
			User u=service.searchUser(email);
			request.setAttribute("user", u);
			target="userProfile.jsp";
			break;
		case "modifyUser":
			uEmail=(String)sn.getAttribute("buyerMail");
			psw=request.getParameter("password");
			ulocation=request.getParameter("location");
			age=Integer.parseInt(request.getParameter("age"));
			mNumber=request.getParameter("mobileNumber");
			dob=request.getParameter("dob");
			if(service.modifyUser(psw, ulocation, age, mNumber, dob, uEmail)) {
				log.info("User Modified Their Profile");
				User u1=service.searchUser(uEmail);
				request.setAttribute("user", u1);
				target="userProfile.jsp";
			}
			break;
		case "checkOut":
			try {
			buyerEmail=(String) sn.getAttribute("buyerMail");
			int total=Integer.parseInt(request.getParameter("total"));
			ArrayList<Cart> citems=service.viewCartItems(buyerEmail);
			if(service.addingOrder(citems,buyerEmail,total)) {
				target="cartItems.jsp";
				request.setAttribute("flag",2);
				log.info("Placed a New Order");
			}
			}catch(Exception e) {
				target="cartItems.jsp";
				request.setAttribute("flag",1);
			}
			break;
			
		case "signOut":
			log.info(uEmail +" logged Out Success");
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
