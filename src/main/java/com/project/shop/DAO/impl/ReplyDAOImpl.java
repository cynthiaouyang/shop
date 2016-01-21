package com.project.shop.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.ReplyDAO;
import com.project.shop.domain.Reply;

@Repository("replyDAO")
public class ReplyDAOImpl extends GenericDAOImpl<Reply, Integer> implements ReplyDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(Reply obj) {
		String[] propertyName=new String[1];
		Object[] value=new Object[1];
		String[] ralation=new String[]{"=","="};
		String orderDesc="";
		int i = 0;
		if (obj.getGoodsComment()!=null&&obj.getGoodsComment().getCommentNo() != null) {
			propertyName[i] = "goodsComment.commentNo";
			value[i] = obj.getGoodsComment().getCommentNo();
			i++;
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}