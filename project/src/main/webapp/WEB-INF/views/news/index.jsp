<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판목록</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
    
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총게시물 : ${data.totalCount}</strong> | ${newsVO.page}/${data.totalPage} 페이지</span></p>
                        <caption>게시판 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="200px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>작성자</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${empty data.list}">
	                        	<tr>
	                        		<td class="first" colspan="8">등록된 글이 없습니다.</td>
	                        	</tr>
	                        </c:if>
							<c:forEach var="vo" items="${data.list}" varStatus="status">
	                            <tr>
	                            						<!-- 총개수 - 인덱스-(현재페이지번호-1)*페이지당개수 -->
	                                <td>${data.totalCount - status.index - (newsVO.page - 1) * newsVO.pageRow}|${status.index}</td>
	                                <td class="txt_l">
	                                    <a href="view.do?no=${vo.no}">${vo.title}</a>
	                                </td>
	                                <td>
	                                	${vo.viewcount}
	                                </td>
	                                <td class="writer">
	                                    ${vo.content}
	                                </td>
	                                <td class="date"><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            </tr>
							</c:forEach>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                        <a class="btn" href="write.do">글작성 </a>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        	<c:if test="${data.prev == true}">
                        		<div>${data.startPage }</div>
                        		<li><a href="index.do?page=${data.startPage-1}&stype=${param.stype}&sword=${param.sword}"><-</a>
                        	</c:if>
                        	<c:forEach var="num" begin="${data.startPage}" end="${data.endPage}">
	                            <li><a href='index.do?page=${num}&stype=${param.stype}&sword=${param.sword}' 
	                            <c:if test="${newsVO.page == num}"> class='current' </c:if>>${num}</a></li>
	                        </c:forEach>
	                        <c:if test="${data.next == true}">
                        		<li><a href="index.do?page=${data.endPage+1}&stype=${param.stype}&sword=${param.sword}">-></a>
                        	</c:if>
                        </ul> 
                    </div>
                
                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
                                    <option value="all" <c:if test="${param.stype == 'all'}">selected</c:if>>전체</option>
                                    <option value="title" <c:if test="${param.stype == 'title'}">selected</c:if>>제목</option>
                                    <option value="content" <c:if test="${param.stype == 'content'}">selected</c:if>>내용</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="sword" value="${param.sword}" title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색">
                            </span>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>