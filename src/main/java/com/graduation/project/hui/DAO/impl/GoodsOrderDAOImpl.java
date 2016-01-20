package com.graduation.project.hui.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.graduation.project.hui.DAO.GoodsOrderDAO;
import com.graduation.project.hui.domain.GoodsOrder;


@Repository("goodsOrderDAO")
public class GoodsOrderDAOImpl extends GenericDAOImpl<GoodsOrder, Integer> implements GoodsOrderDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Query packing(GoodsOrder obj) {
		String[] propertyName=new String[5];
		Object[] value=new Object[5];
		String[] ralation=new String[]{"=","=","=","=","="};
		String orderDesc=" order by orderStatus asc,orderNo desc";
		int i = 0;
		if(obj!=null){
		if (obj.getOrderNo()!=null&&!obj.getOrderNo().equals("")) {
			propertyName[i] = "orderNo";
			value[i] = obj.getOrderNo();
			i++;
		}
		if (obj.getMember()!=null&&obj.getMember().getMemberEmail() != null&&!obj.getMember().getMemberEmail().equals("")) {
			propertyName[i] = "member.memberEmail";
			value[i] = obj.getMember().getMemberEmail();
			i++;
		}
		if (obj.getEmployee()!=null&&obj.getEmployee().getEmpNo() != null&&!obj.getEmployee().getEmpNo().equals("")) {
			propertyName[i] = "employee.empNo";
			value[i] = obj.getEmployee().getEmpNo();
			i++;
		}
		if (obj.getOrderStatus()!=null&&!obj.getOrderStatus().equals("5")) {
			propertyName[i] = "orderStatus";
			value[i] = obj.getOrderStatus();
			i++;
		}
		
		}
		else {
			obj=new GoodsOrder();
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}