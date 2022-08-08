<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.*" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!DOCTYPE html>
<p><span><strong>${comment.totalCount}</strong>  |  ${commentVO.page}/${comment.totalPage}</span></p>
                    <table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <tbody>
							<c:if test="${empty comment.list }">
	                            <tr>
	                                <td class="first" colspan="8">등록된 댓글이 없습니다.</td>
	                            </tr>
                            </c:if>
							<c:if test="${!empty comment.list }">
                            <c:forEach var="list" items="${comment.list }">      
	                            <tr>
	                                <td>${list.no }</td>
	                                <td class="txt_l">
	                                    ${list.content}<c:if test="${loginInfo.no == list.member_no}"><a href="javascript:commentDel(${list.no});">[삭제]</a></c:if>
	                                </td>
	                                <td class="writer">
	                                	${list.member_name}
	                                </td>
	                                <td class="date"><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>	
	                            </tr>
                            </c:forEach>
	                      	</c:if>
                        </tbody>
                    </table>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        	<c:if test="${comment.prev == true}">
                        		<li><a href="javascript:getComment(${comment.startPage -1})"><-</a>
                        	</c:if>
                        	<c:forEach var="num" begin="${comment.startPage}" end="${comment.endPage}">
	                            <li><a href='javascript:getComment(${num})' 
	                            <c:if test="${commentVO.page == num}"> class='current' </c:if>>${num}</a></li>
	                        </c:forEach>
	                        <c:if test="${comment.next == true}">
                        		<li><a href="javascript:getComment(${comment.endPage + 1})">-></a>
                        	</c:if>
                        </ul> 
                    </div>