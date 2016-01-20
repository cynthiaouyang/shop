package com.graduation.project.hui.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.project.hui.domain.Category;
import com.graduation.project.hui.domain.Employee;
import com.graduation.project.hui.domain.Goods;
import com.graduation.project.hui.domain.GoodsComment;
import com.graduation.project.hui.service.CategoryService;
import com.graduation.project.hui.service.GoodsCommentService;
import com.graduation.project.hui.service.GoodsService;
import com.graduation.project.hui.service.impl.CategoryServiceImpl;
import com.graduation.project.hui.service.impl.GoodsCommentServiceImpl;
import com.graduation.project.hui.service.impl.GoodsServiceImpl;
import com.graduation.project.hui.utils.ColorEnum;
import com.graduation.project.hui.utils.Page;
import com.graduation.project.hui.utils.SizeEnum;

@Controller
@Scope("prototype")
@ParentPackage("goods")
public class GoodsAction {

	private Goods goods;
	private Goods goodsHelper;
	private Page page;
	private List<String> picNames;
	private List<File> pics;
	private List<String> oldPics = new ArrayList<String>();
	private List<String> goodsColor = new ArrayList<String>();
	private List<String> goodsSize = new ArrayList<String>();
	private Category category = new Category();
	private List<Category> firstCategories = new ArrayList<Category>();
	private List<GoodsComment> goodsComments = new ArrayList<GoodsComment>();

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoodsHelper() {
		return goodsHelper;
	}

	public void setGoodsHelper(Goods goodsHelper) {
		this.goodsHelper = goodsHelper;
	}

	public List<Category> getFirstCategories() {
		return firstCategories;
	}

	public void setFirstCategories(List<Category> firstCategories) {
		this.firstCategories = firstCategories;
	}

	public List<String> getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(List<String> goodsColor) {
		this.goodsColor = goodsColor;
	}

	public List<String> getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(List<String> goodsSize) {
		this.goodsSize = goodsSize;
	}

	public List<File> getPics() {
		return pics;
	}

	public void setPics(List<File> pics) {
		this.pics = pics;
	}

	public List<String> getOldPics() {
		return oldPics;
	}

	public void setOldPics(List<String> oldPics) {
		this.oldPics = oldPics;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<String> getPicNames() {
		return picNames;
	}

	public void setPicNames(List<String> picNames) {
		this.picNames = picNames;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<GoodsComment> getGoodsComments() {
		return goodsComments;
	}

	public void setGoodsComments(List<GoodsComment> goodsComments) {
		this.goodsComments = goodsComments;
	}

	@Action(value = "goodsAction")
	public String getListGoods() {
		if (goodsHelper == null)
			goodsHelper = new Goods();
		goodsHelper.setGoodsStatus("I");
		this.getPageGoods();
		return "mainlist";

	}

	public String preAdd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("add", "add");
		Category ctg = new Category();
		if(!"xx".equals(request.getParameter("forthCtg").toString())){
			ctg.setCtgNo(request.getParameter("forthCtg"));			
		}else if(!"xx".equals(request.getParameter("thirdCtg").toString())){
			ctg.setCtgNo(request.getParameter("thirdCtg"));	
		}
		for (SizeEnum size : SizeEnum.values())
			goodsSize.add(size.toString());
		for (ColorEnum color : ColorEnum.values())
			goodsColor.add(color.toString());
		goods = new Goods();
		goods.setCategory(ctg);
		return "operate";
	}

	public String addEntity() {
		int i = 0;
		String fileNames = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		for (File pic : pics)
			try {
				String fileName = System.currentTimeMillis() + i + ".jpg";
				File saveFile = new File(System.getenv("OPENSHIFT_DATA_DIR")+"/pics/" + fileName);
				System.out.print(saveFile.getAbsolutePath());
				if (!saveFile.getParentFile().exists()) {
					saveFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(pic, saveFile);
				fileNames += fileName + "|";
				i++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String allSize = "";
		String allColor = "";
		for (String size : goodsSize)
			allSize += size + "|";
		for (String color : goodsColor)
			allColor += color + "|";
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		goods.setEmployee(employee);
		goods.setGoodsSize(allSize.substring(0, allSize.length() - 1));
		goods.setGoodsColor(allColor.substring(0, allColor.length() - 1));
		goods.setGoodsRegtime(new Date());
		goods.setGoodsMark(0f);
		goods.setSoldAmount(0);
		goods.setGoodsPics(fileNames.substring(0, fileNames.length() - 1));

		CategoryService categoryService = new CategoryServiceImpl();
		Category ctg = new Category();
		ctg = categoryService.getEntity(goods.getCategory().getCtgNo());
		goods.setCategory(ctg);

		GoodsService goodsService = new GoodsServiceImpl();
		goodsService.addEntity(goods);

		ctg.setCtgAmount(ctg.getCtgAmount() + 1);
		categoryService.uptEntity(ctg);
		return getList();
	}

	public String delEntity() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		String status = "";
		if (goods.getGoodsStatus().equals("O"))
			status = "I";
		else
			status = "O";
		GoodsService goodsService = new GoodsServiceImpl();
		goods = goodsService.getEntity(goods.getGoodsNo());
		goods.setEmployee(employee);
		goods.setGoodsRegtime(new Date());
		goods.setGoodsStatus(status);
		goodsService.uptEntity(goods);
		return getList();
	}

	public String getEntity() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer goodNo = Integer.parseInt(request.getParameter("goods_no"));
		GoodsService goodsService = new GoodsServiceImpl();
		goods = goodsService.getEntity(goodNo);
		GoodsCommentService goodsCommentService = new GoodsCommentServiceImpl();
		GoodsComment goodsComment = new GoodsComment();
		goodsComment.setGoods(goods);
		if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
			page = new Page(Integer.parseInt(request.getParameter("pageNo")));
		else
			page = new Page();
		page = goodsCommentService.getList(page, goodsComment);
		return "detail";
	}

	public String getList() {
		this.listMethod();
		this.getPageGoods();
		return "list";
	}

	public String preUpt() {
		GoodsService goodsService = new GoodsServiceImpl();
		goods = goodsService.getEntity(goods.getGoodsNo());
		for (SizeEnum size : SizeEnum.values())
			goodsSize.add(size.toString());
		for (ColorEnum color : ColorEnum.values())
			goodsColor.add(color.toString());
		return "operate";
	}

	public String uptEntity() {
		GoodsService goodsService = new GoodsServiceImpl();
		Goods oldGoods = new Goods();
		oldGoods = goodsService.getEntity(goods.getGoodsNo());
		int i = 0;
		String fileNames = "";
		if (oldPics != null) {
			for (String oldPic : oldPics) {
				fileNames += oldPic + "|";
				i++;
			}
		}
		if (pics != null) {
			for (File pic : pics) {
				if (i == 3)
					break;
				try {
					String fileName = System.currentTimeMillis() + i + ".jpg";
					File saveFile = new File(System.getenv("OPENSHIFT_DATA_DIR")+"/pics/" + fileName);
					if (!saveFile.getParentFile().exists())
						saveFile.getParentFile().mkdirs();
					FileUtils.copyFile(pic, saveFile);
					fileNames += fileName + "|";
					i++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		String allSize = "";
		String allColor = "";
		for (String size : goodsSize)
			allSize += size + "|";
		for (String color : goodsColor)
			allColor += color + "|";
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		goods.setEmployee(employee);
		goods.setGoodsSize(allSize.substring(0, allSize.length() - 1));
		goods.setGoodsColor(allColor.substring(0, allColor.length() - 1));
		goods.setGoodsRegtime(new Date());
		goods.setGoodsMark(oldGoods.getGoodsMark());
		goods.setSoldAmount(oldGoods.getSoldAmount());
		goods.setGoodsPics(fileNames.substring(0, fileNames.length() - 1));
		CategoryService categoryService = new CategoryServiceImpl();
		Category ctg = new Category();
		ctg = categoryService.getEntity(goods.getCategory().getCtgNo());
		goods.setCategory(ctg);
		goodsService.uptEntity(goods);

		return getList();
	}

	public void listMethod() {
		CategoryService categoryService = new CategoryServiceImpl();
		Category pareCtg = new Category();
		pareCtg.setCtgNo("0");
		Category ctg = new Category();
		ctg.setCategory(pareCtg);
		firstCategories = categoryService.queryEntity(ctg);
	}

	public void getPageGoods() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
			page = new Page(Integer.parseInt(request.getParameter("pageNo")));
		else
			page = new Page();
		GoodsService goodsService = new GoodsServiceImpl();
		if (StringUtils.isNotEmpty(request.getParameter("act"))
				&& request.getParameter("act").equals("query")) {
			// System.out.println("======================");
			goodsHelper = new Goods();
			Category qryCtg = new Category();
			qryCtg.setCtgNo(request.getParameter("thirdCtg"));
			goodsHelper.setCategory(qryCtg);
		}
		try {
			if (goodsHelper != null && goodsHelper.getGoodsName() != null) {
				// System.out.println("-------------------:"+java.net.URLDecoder.decode(goodsHelper.getGoodsName(),
				// "UTF-8"));
				String[] names = java.net.URLDecoder.decode(
						goodsHelper.getGoodsName(), "UTF-8").split("\\,");
				goodsHelper.setGoodsName(names[0]);
				// System.out.println("-------------------:"+goodsHelper.getGoodsName());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if(request.getParameter("front")==null){ if(goodsHelper==null){
		 * goodsHelper=new Goods(); goodsHelper.setGoodsStatus("I"); } else {
		 * goodsHelper.setGoodsStatus("I"); }
		 * 
		 * }
		 */

		page = goodsService.getList(page, goodsHelper);
	}

}