package com.project.shop.DAO.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.project.shop.DAO.IGenericDAO;



public class GenericDAOImpl<Obj,Id extends Serializable> extends HibernateTemplate implements IGenericDAO<Obj, Id>
{
	
	private Class<Obj> clz;
	
	public GenericDAOImpl()
	{
		 clz = (Class<Obj>) ((ParameterizedType) getClass()
				 .getGenericSuperclass()).getActualTypeArguments()[0];  
	}
	
	
	public Obj addEntity(Obj obj) {
		save(obj);
		return obj;
	}

	
	public Obj delEntity(Id id) {
		Obj obj = getEntity(id);
		delete(obj);
		return obj;
	}

	
	public Obj uptEntity(Obj obj) {
		update(obj);
		return obj;
	}

	public Obj mergeEntity(Obj obj){
		merge(obj);
		return obj;
	}
	
	public Obj getEntity(Id id) {
		Obj obj = get(clz,id);
		return obj;
	}

	public Integer countByCondition(Obj obj) {
		Integer count = 0;
		Query query=this.packing(obj);
		count = (Integer)query.list().size();
		return count;
	}
	public List<Obj> listEntity(Obj obj,final int begin,final int size){
		Query query=this.packing(obj);
		query.setFirstResult(begin-1); 
	    query.setMaxResults(size);
	    List<Obj> objs=new ArrayList<Obj>();
	    objs=query.list();
	    return objs;
	}
	
	public List<Obj> getPackingList(Obj obj) {
		List<Obj> list=new ArrayList<Obj>();
		list=packing(obj).list();
		return list;
	}
	
	public Query packing(Obj obj) {
		String[] propertyName=new String[1];
		Object[] value=new Object[1];
		String[] ralation=new String[]{"=","="};
		String orderAsc="";
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderAsc);
	}
	

	public Query queryEntity(String model,String[] propertyName,Object[] value,String[] ralation,String orderDesc) {
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("from " + model + " as model\n");
		final int len = propertyName.length;
		List<Object> listEntity = new ArrayList<Object>();
		boolean first = true;
		for (int i = 0; i < len; i++) {
			if (value[i] != null) {
				if (first) {
					sqlBuffer.append(" where " + "model." + propertyName[i]
							+ ralation [i]+ " ?\n");
					listEntity.add( value[i]);
					first = false;
				} else {
					sqlBuffer.append(" and " + "model." + propertyName[i]
							+ ralation[i] + " ?\n");
					listEntity.add( value[i]);
				}
			}
		}
		sqlBuffer.append(orderDesc);
		final Query queryObject = this.getSession().createQuery(
				sqlBuffer.toString());

		
		for (int i = 0; i < listEntity.size(); i++) {
			queryObject.setParameter(i, listEntity.get(i));			
		}
		return queryObject;
	}





}
