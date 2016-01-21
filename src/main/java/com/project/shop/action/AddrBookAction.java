package com.project.shop.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.shop.domain.AddrBook;
import com.project.shop.domain.Member;
import com.project.shop.service.AddrBookService;
import com.project.shop.service.impl.AddrBookServiceImpl;
@Controller
@Scope("prototype")
@ParentPackage("addrBook")
@InterceptorRefs( {
	@InterceptorRef(value = "loginStack") })
public class AddrBookAction 
{
	private AddrBook addrBook;
	private AddrBook uptAddr;
	private List<AddrBook> addrs=new ArrayList<AddrBook>();
	
	
	public AddrBook getAddrBook() {
		return addrBook;
	}

	public void setAddrBook(AddrBook addrBook) {
		this.addrBook = addrBook;
	}


	public List<AddrBook> getAddrs() {
		return addrs;
	}

	public void setAddrs(List<AddrBook> addrs) {
		this.addrs = addrs;
	}
	

	public AddrBook getUptAddr() {
		return uptAddr;
	}

	public void setUptAddr(AddrBook uptAddr) {
		this.uptAddr = uptAddr;
	}

	@Action(value="addrAction")
	public String getMemberAddrs() {		
	 	AddrBookService addrBookService=new AddrBookServiceImpl();
		AddrBook addr=new AddrBook();
		Member member=new Member();
		HttpServletRequest request = ServletActionContext.getRequest();
		member=(Member) request.getSession().getAttribute("loginMember");
		addr.setMember(member);
		addrs=addrBookService.queryEntity(addr);
	    if(StringUtils.isNotEmpty(request.getParameter("forward"))){
	    	request.setAttribute("addrActive", "acc-menu-active");	    	
	    	return "addrBook";
	    }
		return "pay";
	}

	public String addEntity() {
		AddrBookService addrBookService=new AddrBookServiceImpl();
		addrBook.setCreatetime(new Date());		
		Member member=new Member();
		HttpServletRequest request = ServletActionContext.getRequest();
		member=(Member) request.getSession().getAttribute("loginMember");
		addrBook.setMember(member);
		addrBookService.addEntity(addrBook);
		addrs=addrBookService.queryEntity(addrBook);
		return "addrBook";
	}

	public String delEntity() {
		AddrBookService addrBookService=new AddrBookServiceImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer bookNo=Integer.parseInt(request.getParameter("bookNo"));
		addrBookService.delEntity(bookNo);
		
		Member member=new Member();
		member=(Member) request.getSession().getAttribute("loginMember");
		addrBook.setMember(member);
		addrs=addrBookService.queryEntity(addrBook);
		return "addrBook";
	}

	public String uptEntity() {
		AddrBookService addrBookService=new AddrBookServiceImpl();
		uptAddr.setCreatetime(new Date());
		Member member=new Member();
		HttpServletRequest request = ServletActionContext.getRequest();
		member=(Member) request.getSession().getAttribute("loginMember");
		uptAddr.setMember(member);
		addrBookService.uptEntity(uptAddr);
		
		addrs=addrBookService.queryEntity(uptAddr);
		return "addrBook";
	}
	
}