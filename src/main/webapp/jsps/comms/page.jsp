<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/comms/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<nav style="text-align: right;margin:10px;">
  <ul class="pagination">
    <li id='li0'>
      <a aria-label="Previous">
        <span aria-hidden="true" onclick="switchPage(${page.pageNo-1});">&laquo;</span>
      </a>
    </li>
   <c:forEach begin="1" end="${page.totalPageNum}" varStatus="loop" >
     <li id="li${loop.count}"><a onclick="switchPage(${loop.count});">${loop.count}</a></li>
      </c:forEach>
    <li id='lii'>
      <a aria-label="Next">
        <span aria-hidden="true" onclick="switchPage(${page.pageNo+1},${page.totalPageNum});">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

   
</html>
