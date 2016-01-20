package com.graduation.project.hui.DAO.impl;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.graduation.project.hui.DAO.EmployeeDAO;
import com.graduation.project.hui.domain.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericDAOImpl<Employee, Integer> implements  EmployeeDAO{

	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
		
	}
	@Override
	public Query packing(Employee obj) {
		String[] propertyName=new String[3];
		Object[] value=new Object[3];
		String[] ralation=new String[]{"=","=","=","="};
		String orderDesc=" order by empNo desc";
		int i=0;
		if(obj!=null){
		if (obj.getEmpNo()!=null&&!obj.getEmpNo().equals("")) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}
		if (obj.getEmpName()!=null&&!obj.getEmpName().equals("")) {
			propertyName[i] = "empName";
			value[i] = obj.getEmpName();
			i++;
		}
		if (obj.getEmpType()!=null&&!obj.getEmpType().equals("E")) {
			propertyName[i] = "empType";
			value[i] = obj.getEmpType();
			i++;
		}
		}
		else {
			obj=new Employee();
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}