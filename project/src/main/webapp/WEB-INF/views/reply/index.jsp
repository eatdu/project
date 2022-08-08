<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.*" %>
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
    <script>
    	function goWrite(){
			<c:if test="${empty loginInfo}">
				alert('로그인 후 사용해주세요.');
				location.href='/project/member/login.do'
			</c:if>
			<c:if test="${!empty loginInfo}">
				location.href='write.do'
			</c:if>
    	}
    </script>
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판${loginInfo.email }</h3>
    
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총게시물 : ${data.totalCount}</strong> | ${boardVO.page}/${data.totalPage} 페이지</span></p>
                        <caption>게시판 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>다운로드</th>
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
	                                <td>${data.totalCount - status.index - (boardVO.page - 1) * boardVO.pageRow}|${status.index}</td>
	                                <td class="txt_l" style="text-align: left;">
	                                	<c:forEach begin="1" end="${vo.nested}">&nbsp;&nbsp;&nbsp;</c:forEach>
	                                	<c:if test="${vo.nested > 0 }"><img src="/project/img/answer_icon.gif"></c:if>
		                                <a href="view.do?no=${vo.no}">${vo.title} [${vo.comment_count}]</a>
	                                    
	                                </td>
	                                <td>
	                                	${vo.viewcount}
	                                </td>
	                                <td class="writer">
	                                    ${vo.member_name}
	                                </td>
	                                <td class="date"><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></td>
	                                <td>
					                    <a href="/project/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}"
					                    target="_blank">[다운로드]</a>
					                </td>
	                            </tr>
							</c:forEach>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                    <c:if test="${!empty loginInfo}">
                        <a class="btn" href="javascript:goWrite();">글작성 </a>
                    </c:if>
                    <c:if test="${empty loginInfo}">
                        <a class="btn" href="javascript:goWrite();">글작성 </a>
                    </c:if>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        	<c:if test="${data.prev == true}">
                        		<div>${data.startPage }</div>
                        		<li><a href="index.do?page=${data.startPage-1}&stype=${param.stype}&sword=${param.sword}"><-</a>
                        	</c:if>
                        	<c:forEach var="num" begin="${data.startPage}" end="${data.endPage}">
	                            <li><a href='index.do?page=${num}&stype=${param.stype}&sword=${param.sword}' 
	                            <c:if test="${boardVO.page == num}"> class='current' </c:if>>${num}</a></li>
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