package com.graduation.project.hui.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.graduation.project.hui.DAO.AddrBookDAO;
import com.graduation.project.hui.domain.AddrBook;
import com.graduation.project.hui.service.AddrBookService;
import com.graduation.project.hui.utils.Page;

public class AddrBookServiceImpl implements AddrBookService
{
	private ApplicationContext ac;
	private AddrBookDAO addrBookDAO; 
	public AddrBookServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		addrBookDAO = (AddrBookDAO)ac.getBean("addrBookDAO");
	}

	public AddrBook addEntity(AddrBook obj) {

		return addrBookDAO.addEntity(obj);
	}

	public AddrBook delEntity(Integer id) {
		// TODO Auto-generated method stub
		return addrBookDAO.delEntity(id);
	}

	public AddrBook getEntity(Integer id) {
		// TODO Auto-generated method stub
		return addrBookDAO.getEntity(id);
	}

	public AddrBook uptEntity(AddrBook obj) {
		// TODO Auto-generated method stub
		return addrBookDAO.uptEntity(obj);
	}


	public Page getList(Page page, AddrBook helper) {
		page.setTotalRecNum((long)(addrBookDAO.countByCondition(helper)));
		page.setPageContent(addrBookDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}

	public List<AddrBook> queryEntity(AddrBook obj) {
/*	//	String[] propertyName=obj.getClass().getProtectionDomain();
		String[] propertyName=null;
		Object[] value=null;
		BeanInfo bi;
		try {
			bi = Introspector.getBeanInfo(AddrBook.class);
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();
	          for (int i = 0,j=0; i < pds.length; i++) {
	        	  if(obj)
	        	  propertyName[j] = pds[i].getName();
	              System.out.println(propertyName[j]);
	          }
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		if (obj.getBookNo() != null) {
			propertyName[i] = "bookNo";
			value[i] = obj.getBookNo();
			i++;
		}
		if (obj.getMember().getMemberEmail() != null) {
			propertyName[i] = "member.memberEmail";
			value[i] = obj.getMember().getMemberEmail();
			i++;
		} */ 		
		return addrBookDAO.getPackingList(obj);
	}

	public AddrBook mergeEntity(AddrBook obj) {
		// TODO Auto-generated method stub
		return addrBookDAO.mergeEntity(obj);
	}


	
}