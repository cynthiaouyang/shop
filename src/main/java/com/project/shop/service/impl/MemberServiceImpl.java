package com.project.shop.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.shop.DAO.MemberDAO;
import com.project.shop.domain.Member;
import com.project.shop.service.MemberService;
import com.project.shop.utils.Page;



public class MemberServiceImpl implements MemberService 
{

	private ApplicationContext ac;
	private MemberDAO memberDAO; 
	public MemberServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		memberDAO = (MemberDAO)ac.getBean("memberDAO");
	}
	public Member addEntity(Member obj) {
		
		return memberDAO.addEntity(obj);
	}

	public Member delEntity(String id) {
		 
		return memberDAO.delEntity(id);
	}

	public Member getEntity(String id) {
		 
		return memberDAO.getEntity(id);
	}

	public Member uptEntity(Member obj) {
		 
		return memberDAO.uptEntity(obj);
	}

	public Page getList(Page page, Member helper) {
	//	final String hql=null;		
		page.setTotalRecNum((long)(memberDAO.countByCondition(helper)));
		page.setPageContent(memberDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}
	public List<Member> queryEntity(Member obj) {
	//	String[] propertyName=null;
	//	Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return memberDAO.getPackingList(obj);
	}
	public Member mergeEntity(Member obj) {
		// TODO Auto-generated method stub
		return memberDAO.mergeEntity(obj);
	}
}