package com.graduation.project.hui.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.graduation.project.hui.domain.Category;
import com.graduation.project.hui.service.CategoryService;
import com.graduation.project.hui.service.impl.CategoryServiceImpl;


public class IndexServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("-------------------------------log1---------------------------------");
		List<Category> firstCategories=new ArrayList<Category>();		
		Category allCtg=new Category();
		CategoryService categoryService = new CategoryServiceImpl();
		Category pareCtg=new Category();
		pareCtg.setCtgNo("0");
		Category ctg=new Category();
		ctg.setCategory(pareCtg);
		firstCategories=categoryService.queryEntity(ctg);	
		allCtg=categoryService.getEntity("0");
		allCtg.setCategories(firstCategories);	
//		for(Category ct:allCtg.getCategories()){
//			Category ctgChild1=new Category();
//			ctgChild1.setCategory(ct);
//			ct.setCategories(categoryService.queryEntity(ctgChild1));
//			  for(Category ctt:ct.getCategories()){
//				  Category ctChild2=new Category();
//				  ctChild2.setCategory(ctt);
//				  ctt.setCategories(categoryService.queryEntity(ctChild2));
//				  for(Category cttt:ctt.getCategories()){
//					  if(cttt.getCtgAmount()!=0){
//						Category ctgChild3=new Category();
//						ctgChild3.setCategory(cttt);
//						  System.out.println("---------------------"+cttt.getCtgName());
//						cttt.setCategories(categoryService.queryEntity(ctgChild3));
//					  }
//				  }
//				  
//			  }
//		}   
		System.out.println("==============finish!");
		ServletContext sc = getServletContext();
		sc.setAttribute("allCtg", allCtg);
		Map map=new HashMap<String,List>();
		String[] l1={"FAQ","SIZE", "CHARTS","ORDER HELP","SHIPPING & HANDLING","RETURNS & EXCHANGES","TRACK AN ORDER","GIFT CARDS & MERCHANDISE CREDIT","CUSTOMER SERVICE"};

		String[] l2={"BRAND","PROTECTION","CAREERS","CA SUPPLY CHAINS ACT"};
		String[] l3={"PRIVACY / AD COOKIES","CA PRIVACY RIGHTS","SALE TERM","TEXT TERMS & PRIVACY POLICY","CLUB CALI TERMS","WEBSITE TERMS OF USE","ENDORSEMENTS SOCIAL MEDIA ENGAGEMENT","SITE MAP"};

		map.put("NEED HELP?", Arrays.asList(l1));
		map.put("ABOUT US", Arrays.asList(l2));
		map.put("PRIVACY & TERMS", Arrays.asList(l3));
	    sc.setAttribute("data", map);
	}

}
