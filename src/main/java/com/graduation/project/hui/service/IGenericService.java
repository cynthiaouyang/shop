package com.graduation.project.hui.service;

import java.io.Serializable;
import java.util.List;

import com.graduation.project.hui.utils.Page;


public interface IGenericService<Obj,Id extends Serializable,Helper>
{
	Obj addEntity(Obj obj);                  //对DAO包里的Obj addEntity(Obj obj);进行调用
	Obj delEntity(Id id);                    //对DAO包里的Obj delEntity(Id id); 进行调用
	Obj uptEntity(Obj obj);                  //对DAO包里的Obj uptEntity(Id id); 进行调用
	Obj mergeEntity(Obj obj);                //对DAO包里的Obj mergeEntity(Id id); 进行调用
	Obj getEntity(Id id);	                 //对DAO包里的Obj getEntity(Id id); 进行调用
	Page getList(Page page,Helper helper);   
	//对DAO包里的Integer countByCondition(Obj obj); 和
	//List<Obj> listEntity(Obj obj,final int begin,final int size); 进行调用得到分页页面
    List<Obj> queryEntity(Obj obj);          
    //对DAO包里的List<Obj> getPackingList(Obj obj);进行调用得到查询结果列表
}
