package com.graduation.project.hui.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.graduation.project.hui.DAO.AddrBookDAO;
import com.graduation.project.hui.domain.AddrBook;


@Repository("addrBookDAO")
public class AddrBookDAOImpl extends  GenericDAOImpl<AddrBook, Integer> implements AddrBookDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(AddrBook obj) {
		String[] propertyName=new String[1];
		Object[] value=new Object[1];
		String[] ralation=new String[]{"=","="};
		String orderDesc=" order by bookNo desc";
		int i = 0;
		if (obj.getMember()!=null&&obj.getMember().getMemberEmail() != null) {
			propertyName[i] = "member.memberEmail";
			value[i] = obj.getMember().getMemberEmail();
			i++;
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
	
}