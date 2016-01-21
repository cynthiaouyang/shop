package com.project.shop.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;


public interface IGenericDAO<Obj,Id extends Serializable>
{
	Obj addEntity(Obj obj);                    //增加obj对象
	Obj delEntity(Id id);                     //删除id相符对象
	Obj uptEntity(Obj obj);                  //更新obj对象
	Obj mergeEntity(Obj obj);               //合并更新obj对象
	Obj getEntity(Id id);                  //根据id查询单个对象
	Integer countByCondition(Obj obj);    //查询符和某个对象一些属性的对象的数量
	List<Obj> listEntity(Obj obj,final int begin,final int size);
	//得到符合某个对象一些属性的一段区间的对象列表
	List<Obj> getPackingList(Obj obj);
	//得到符合查询对象的列表
	Query packing(Obj obj);
	//List<Obj> getPackingList(Obj obj);中调用到的一个方法，得到需要查询对象的查询语句的Query
	Query queryEntity(String model,String[]propertyName,Object[] value,String[] ralation,String orderDesc);
	//Query packing(Obj obj);中调用到的一个方法，用于组装sql语句并返回Query
	
}
