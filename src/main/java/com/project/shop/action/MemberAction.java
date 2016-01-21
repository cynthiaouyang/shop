package com.project.shop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.shop.domain.Member;
import com.project.shop.service.MemberService;
import com.project.shop.service.impl.MemberServiceImpl;
import com.project.shop.utils.Page;

@Controller
@Scope("prototype")
@ParentPackage("member")
@InterceptorRefs( {
	@InterceptorRef(value = "loginStack") })
public class MemberAction {

	private Member member;
	private Member memberHelper;
	private String frontPage;
	private Page page;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMemberHelper() {
		return memberHelper;
	}

	public void setMemberHelper(Member memberHelper) {
		this.memberHelper = memberHelper;
	}

	public String getFrontPage() {
		return frontPage;
	}

	public void setFrontPage(String frontPage) {
		this.frontPage = frontPage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Action(value = "memberAction")
	public String register() {
		MemberService memberService = new MemberServiceImpl();
		member.setMemberRank(0);
		member.setMemberRegtime(new Date());
		member.setMemberType("0");
		member.setMoneyLeft(0.0);
		memberService.addEntity(member);
		return "main";
	}

	public String getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
			page = new Page(Integer.parseInt(request.getParameter("pageNo")));
		else
			page = new Page();
		MemberService memberService = new MemberServiceImpl();
		page = memberService.getList(page, memberHelper);
		return "list";
	}

	public void checkEmail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		MemberService memberService = new MemberServiceImpl();
		Member member = new Member();
		String memberEmail = request.getParameter("memberEmail").trim();
		member = memberService.getEntity(memberEmail);
		PrintWriter out;
		try {
			out = response.getWriter();
			if (member == null) {
				if (memberEmail.indexOf("@") > 1
						&& memberEmail.indexOf("@") < memberEmail.indexOf(".")
						&& memberEmail.indexOf(".") < memberEmail.length())
					out.print("<span style=\"color:green;\">right<span>");
				else
					out.print("<span style=\"color:red;\">inValid Email address<span>");

			} else
				out.print("<span style=\"color:red;\">already occupied<span>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("loginMember");
		session.invalidate();
		return "main";
	}

	public void checkPwd() {
		// System.out.println("======check");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			member = (Member) request.getSession().getAttribute("loginMember");
			String pwd = request.getParameter("oldMemberPwd");
			if (!member.getMemberPwd().equals(pwd))
				out.print("Old Password is not correct");	
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEntity() throws ServletException {
		MemberService memberService = new MemberServiceImpl();
		Member loginMember = new Member();
		HttpServletRequest request = ServletActionContext.getRequest();
		loginMember = memberService.getEntity(member.getMemberEmail());
		if (loginMember != null) {
			if (loginMember.getMemberPwd().equals(member.getMemberPwd())) {
				request.getSession().setAttribute("loginMember", loginMember);
				if (StringUtils.isNotEmpty(frontPage)) {
					if (frontPage.equals("pay")) {
						return "payPage";
					} else if (frontPage.equals("message")) {
						return "message";
					}
				}
				return "main";
			}
		}
		request.setAttribute("loginError",
				"The email or password entered is incorrect");
		return "login";
	}

	public String myInfo() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		member = (Member) session.getAttribute("loginMember");
		ServletActionContext.getRequest().setAttribute("infoActive", "acc-menu-active");
		return "myInfo";
	}
	
	public String preUptPwd(){
		ServletActionContext.getRequest().setAttribute("pwdActive", "acc-menu-active");
		return "preUptPwd";
	}

	public String otherInfo() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		member = (Member) session.getAttribute("loginMember");
		ServletActionContext.getRequest().setAttribute("otherActive", "acc-menu-active");
		return "otherInfo";
	}

	public String uptPwd() {
		MemberService memberService = new MemberServiceImpl();
		Member m = new Member();
		HttpSession session = ServletActionContext.getRequest().getSession();
		m = (Member) session.getAttribute("loginMember");
		m.setMemberPwd(member.getMemberPwd());
		m = memberService.uptEntity(m);
		session.setAttribute("loginMember", m);
		return "main";
	}

	public String uptEntity() {
		MemberService memberService = new MemberServiceImpl();
		Member m = new Member();
		HttpSession session = ServletActionContext.getRequest().getSession();
		m = (Member) session.getAttribute("loginMember");
		m.setMemberBirth(member.getMemberBirth());
		m.setMemberNickname(member.getMemberNickname());
		m.setMemberSex(member.getMemberSex());
		m.setMemberLocation(member.getMemberLocation());
		m = memberService.uptEntity(m);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("loginMember", m);

		return "main";
	}
}