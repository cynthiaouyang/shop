package com.project.shop.action;

import java.util.Date;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.shop.domain.Employee;
import com.project.shop.service.EmployeeService;
import com.project.shop.service.impl.EmployeeServiceImpl;
import com.project.shop.utils.Page;

@Controller
@Scope("prototype")
@ParentPackage("employee")
public class EmployeeAction
{
	
	private Employee employee; 	
	Employee emp;
	private Page page;
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/*	@Actions({
		@Action(value="hello1",interceptorRefs={@InterceptorRef("loginStack")}),
		@Action(value="hello2",interceptorRefs={@InterceptorRef("defaultStack")})
	})*/
	@Action(value="empAction")
	public String checkAdmin() {
		EmployeeService employeeService=new EmployeeServiceImpl();
		Employee getEmp=employeeService.getEntity(employee.getEmpNo());
		HttpServletRequest request = ServletActionContext.getRequest();
		if(getEmp==null){
			request.setAttribute("loginError",
					"The ID or password entered is incorrect");
			return "login";
		}
		else if(getEmp.getEmpPwd().equals(employee.getEmpPwd())){		
			request.getSession().setAttribute("admin", getEmp);
			return "main";
		}
		else {
			request.setAttribute("loginError",
					"The ID or password entered is incorrect");
			return "login";	
		}
	}
	public String logout() {
		HttpSession session= ServletActionContext.getRequest().getSession();			
		session.removeAttribute("admin");
		session.invalidate();
		return "login";			
	}
	public String preAdd() {
		return "addEmp";
	}

	public String addEntity() {
		EmployeeService employeeService=new EmployeeServiceImpl();
		employee.setEmpRegdate(new Date());		
		employeeService.addEntity(employee);
		return getList();
	}

	public String delEntity() {
		EmployeeService employeeService=new EmployeeServiceImpl();
		employeeService.delEntity(employee.getEmpNo());
		//System.out.println("delEntity");		
		return this.getList();
	}


	public String getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		 if(StringUtils.isNotEmpty(request.getParameter("pageNo")))
			 page=new Page(Integer.parseInt(request.getParameter("pageNo")));
		 else
		     page=new Page();
		EmployeeService employeeService=new EmployeeServiceImpl();
		page=employeeService.getList(page, emp);
		return "list";
	}
	public String preUpt() {
		EmployeeService employeeService=new EmployeeServiceImpl();
		employee=employeeService.getEntity(employee.getEmpNo());
		return "preUpt";
		}
	public String uptEntity() {
	 	EmployeeService employeeService=new EmployeeServiceImpl();
	 	employeeService.uptEntity(employee);	 	
		return this.getList();
	}
	}