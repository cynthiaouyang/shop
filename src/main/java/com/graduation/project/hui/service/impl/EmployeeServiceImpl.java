package com.graduation.project.hui.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.graduation.project.hui.DAO.EmployeeDAO;
import com.graduation.project.hui.domain.Employee;
import com.graduation.project.hui.service.EmployeeService;
import com.graduation.project.hui.utils.Page;


public class EmployeeServiceImpl implements EmployeeService
{

	private ApplicationContext ac;
	private EmployeeDAO employeeDAO; 
	public EmployeeServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		employeeDAO = (EmployeeDAO)ac.getBean("employeeDAO");
	}
	public Employee addEntity(Employee obj) {
		
		return employeeDAO.addEntity(obj);
	}

	public Employee delEntity(Integer id) {
		
		return employeeDAO.delEntity(id);
	}

	public Employee getEntity(Integer id) {
		 
		return employeeDAO.getEntity(id);
	}

	public Employee uptEntity(Employee obj) {
		 
		return employeeDAO.uptEntity(obj);
	}
	public Page getList(Page page, Employee helper) {
		//final String hql="from Employee e  order by e.empNo";		
		page.setTotalRecNum((long)(employeeDAO.countByCondition(helper)));
		page.setPageContent(employeeDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}
	public List<Employee> queryEntity(Employee obj) {
	//	String[] propertyName=null;
	//	Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return employeeDAO.getPackingList(obj);
	}
	public Employee mergeEntity(Employee obj) {
		// TODO Auto-generated method stub
		return  employeeDAO.mergeEntity(obj);
	}

	}