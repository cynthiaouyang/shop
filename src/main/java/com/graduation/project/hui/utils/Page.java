package com.graduation.project.hui.utils;



import java.util.Collection;

/**
 * @author HUI
 *
 */
public class Page {
	
    private Integer pageNo; //当前页号
    private Integer pageSize; //每页记录数量
    private Boolean nextPage; //是否有下一页
    private Boolean prePage;  //是否有上一页
    private Long totalRecNum; //总共有几条记录
    private Integer totalPageNum;//总共有多少页
    private Collection pageContent;//本页记录集
    private Integer startIndex;//本页开始记录位置
    private Integer endIndex;
        
	public Page(int pageNo) {
		super();
        this.pageNo=pageNo;
        pageSize=15;
	}
	
	public Page() {
		this(1);
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Boolean getNextPage() {
		return pageNo<getTotalPageNum()?true:false;
	}

	public Boolean getPrePage() {
		return pageNo>1?true:false;
	}

	public Long getTotalRecNum() {
		return totalRecNum;
	}
	public void setTotalRecNum(Long totalRecNum) {
		this.totalRecNum = totalRecNum;
	}
	
	public Integer getTotalPageNum() {
		return totalRecNum%pageSize>0?(int)(totalRecNum/pageSize+1):(int)(totalRecNum/pageSize);
	}

	public Collection getPageContent() {
		return pageContent;
	}
	
	public void setPageContent(Collection pageContent) {
		this.pageContent = pageContent;
	}
    
	public int getStartIndex()
	{
		return pageSize*(pageNo-1)+1;  // size:10 pageno:3   21
	}
    
	public int getEndIndex()
	{
		if(this.pageContent==null)
		       return pageSize*pageNo;
		else if(this.pageContent.size()<pageSize)
			   return pageSize*(pageNo-1)+this.pageContent.size();
		     else
			   return pageSize*pageNo;
			 
	}
    
}
