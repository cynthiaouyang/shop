package com.project.shop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.shop.domain.Category;
import com.project.shop.domain.Employee;
import com.project.shop.service.CategoryService;
import com.project.shop.service.impl.CategoryServiceImpl;
import com.project.shop.utils.Page;

@Controller
@Scope("prototype")
@ParentPackage("category")
public class CategoryAction {
	private Category category;
	private List<Category> firstCategories = new ArrayList<Category>();
	private List<Category> childrenCtg = new ArrayList<Category>();
	private Page page;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Category> getFirstCategories() {
		return firstCategories;
	}

	public void setFirstCategories(List<Category> firstCategories) {
		this.firstCategories = firstCategories;
	}

	public List<Category> getChildrenCtg() {
		return childrenCtg;
	}

	public void setChildrenCtg(List<Category> childrenCtg) {
		this.childrenCtg = childrenCtg;
	}

	@Action(value = "ctgAction")
	public String preUpt() {
		CategoryService categoryService = new CategoryServiceImpl();
		category = categoryService.getEntity(category.getCtgNo());
		return "operate";
	}

	public String preAdd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("add", "add");
		CategoryService categoryService = new CategoryServiceImpl();
		Category ctg = new Category();
		ctg = categoryService.getEntity(category.getCtgNo());
		category.setCategory(ctg);
		int num = categoryService.queryEntity(category).size() + 1;
		if (ctg.getCtgNo().equals("0"))
			category.setCtgNo(num + "");
		else
			category.setCtgNo(ctg.getCtgNo() + "." + num);
		return "operate";
	}

	public String addEntity() {
		CategoryService categoryService = new CategoryServiceImpl();
		category.setCategory(categoryService.getEntity(category.getCategory()
				.getCtgNo()));
		category.getCategory().setCtgAmount(
				category.getCategory().getCtgAmount() + 1);
		categoryService.uptEntity(category.getCategory());
		category.setCtgCreateTime(new Date());
		category.setCtgAmount(0);
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		category.setEmployee(employee);
		categoryService.addEntity(category);
		return getList();
	}

	public String delEntity() {
		CategoryService categoryService = new CategoryServiceImpl();
		Category pareCtg = new Category();
		pareCtg = categoryService.getEntity(category.getCategory().getCtgNo());
		pareCtg.setCtgAmount(pareCtg.getCtgAmount() - 1);
		categoryService.uptEntity(pareCtg);
		categoryService.delEntity(category.getCtgNo());
		return getList();
	}

	public void getVoidEntity() {
		CategoryService categoryService = new CategoryServiceImpl();
		category = categoryService.getEntity(category.getCtgNo());
	}

	public String uptEntity() {
		CategoryService categoryService = new CategoryServiceImpl();
		Category ctg = new Category();
		ctg = categoryService.getEntity(category.getCtgNo());
		ctg.setCtgMemo(category.getCtgMemo());
		ctg.setCtgName(category.getCtgName());
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		ctg.setEmployee(employee);
		ctg.setCtgCreateTime(new Date());
		categoryService.uptEntity(ctg);
		return getList();
	}

	public String getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Category qryCtg = new Category();
		Category qryChildrenCtg = new Category();
		boolean haveChild = true;
		boolean queryself = false;
		if (request.getParameter("act") != null
				&& request.getParameter("act").equals("query"))
			queryself = true;
		if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
			page = new Page(Integer.parseInt(request.getParameter("pageNo")));
		else
			page = new Page();
		if (request.getParameter("act") != null
				&& ((request.getParameter("act").equals("querychild") || queryself))) {
			String ctg4 = request.getParameter("forthCtg");
			String ctg3 = request.getParameter("thirdCtg");
			String ctg2 = request.getParameter("secondCtg");
			String ctg1 = request.getParameter("firstCtg");
			if (ctg4 != null && !ctg4.equals("xx")) {
				qryCtg.setCtgNo(ctg4);
				haveChild = false;
			} else if (ctg3 != null && !ctg3.equals("xx")) {
				qryCtg.setCtgNo(ctg3);
				haveChild = false;
				String parCtg = ctg3.split("\\.")[0];
				if (parCtg.equals("3") || parCtg.equals("4")) {
					haveChild = true;
					qryChildrenCtg.setCategory(qryCtg);
				}
			} else if (ctg2 != null && !ctg2.equals("xx")) {
				qryCtg.setCtgNo(ctg2);
				qryChildrenCtg.setCategory(qryCtg);
			} else if (ctg1 != null && !ctg1.equals("xx")) {
				qryCtg.setCtgNo(ctg1);
				qryChildrenCtg.setCategory(qryCtg);
			}
		}
		CategoryService categoryService = new CategoryServiceImpl();
		if (queryself || !haveChild)
			category = categoryService.getEntity(qryCtg.getCtgNo());
		else
			page = categoryService.getList(page, qryChildrenCtg);
		Category pareCtg = new Category();
		pareCtg.setCtgNo("0");
		Category ctg = new Category();
		ctg.setCategory(pareCtg);
		firstCategories = categoryService.queryEntity(ctg);
		return "list";
	}

	public void getChildren() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		CategoryService categoryService = new CategoryServiceImpl();
		Category pareCtg = new Category();
		pareCtg.setCtgNo(request.getParameter("ctgNo"));
		Category ctg = new Category();
		ctg.setCategory(pareCtg);
		childrenCtg = categoryService.queryEntity(ctg);
		StringBuffer context = new StringBuffer();
		if (childrenCtg != null) {
			PrintWriter out;
			try {
				out = response.getWriter();
				context.append("<option value='xx'>--Select--</option>");
				for (int i = 0; i < childrenCtg.size(); i++)
					context.append("<option value='")
							.append(childrenCtg.get(i).getCtgNo()).append("'>")
							.append(childrenCtg.get(i).getCtgName())
							.append("</option>");
				out.print(context.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}