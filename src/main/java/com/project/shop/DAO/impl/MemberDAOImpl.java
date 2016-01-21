package com.project.shop.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.MemberDAO;
import com.project.shop.domain.Employee;
import com.project.shop.domain.Member;


@Repository("memberDAO")
public class MemberDAOImpl extends GenericDAOImpl<Member, String> implements MemberDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(Member obj) {
		String[] propertyName=new String[3];
		Object[] value=new Object[3];
		String[] ralation=new String[]{"=","=","=","="};
		String orderDesc=" order by memberRegtime desc";
		int i=0;
		if(obj!=null){
		if (obj.getMemberEmail()!=null&&!obj.getMemberEmail().equals("")) {
			propertyName[i] = "memberEmail";
			value[i] = obj.getMemberEmail();
			i++;
		}
		if (obj.getMemberNickname()!=null&&!obj.getMemberNickname().equals("")) {
			propertyName[i] = "memberNickname";
			value[i] = obj.getMemberNickname();
			i++;
		}
		if (obj.getMemberType()!=null&&!obj.getMemberType().equals("6")) {
			propertyName[i] = "memberType";
			value[i] = obj.getMemberType();
			i++;
		}
		}
		else {
			obj=new Member();
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}