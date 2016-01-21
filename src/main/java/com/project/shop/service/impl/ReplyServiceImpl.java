package com.project.shop.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.shop.DAO.ReplyDAO;
import com.project.shop.domain.Reply;
import com.project.shop.service.ReplyService;
import com.project.shop.utils.Page;

public class ReplyServiceImpl implements ReplyService 
{
	private ApplicationContext ac;
	private ReplyDAO replyDAO; 
	public ReplyServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		replyDAO = (ReplyDAO)ac.getBean("replyDAO");
	}
	public Reply addEntity(Reply obj) {
		 
		return replyDAO.addEntity(obj);
	}

	public Reply delEntity(Integer id) {
		 
		return replyDAO.delEntity(id);
	}

	public Reply getEntity(Integer id) {
		 
		return replyDAO.getEntity(id);
	}

	public Reply uptEntity(Reply obj) {
		 
		return replyDAO.uptEntity(obj);
	}

	public Page getList(Page page, Reply helper) {
	//	final String hql=null;		
		page.setTotalRecNum((long)(replyDAO.countByCondition(helper)));
		page.setPageContent(replyDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}
	public List<Reply> queryEntity(Reply obj) {
	//	String[] propertyName=null;
	//	Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return replyDAO.getPackingList(obj);
	}
	public Reply mergeEntity(Reply obj) {
		// TODO Auto-generated method stub
		return replyDAO.mergeEntity(obj);
	}
}