<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/"%>

<%@ attribute name="pager" required="true"
	type="org.springframework.data.domain.PageImpl"%>
<c:set var="iteration" value="5" />
<c:if test="${ pager.numberOfElements - pager.number < 2}">
	<c:set var="iteration"
		value="${ pager.numberOfElements - pager.number + 3}" />
</c:if>
<c:if test="${ pager.numberOfElements < 5 }">
	<c:set var="iteration" value="${pager.numberOfElements}" />
</c:if>
<c:choose>
	<c:when test="${pager.number < 3 }">
		<c:forEach var="i" begin="1" end="${iteration}" step="1">
			<li><l:link pager="${pager}" type="dashboard" page="${i}" name="${i}"/></li>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="i" begin="${pager.number -2 }"
			end="${iteration+ pager.number - 3}" step="1">
			<li><l:link pager="${pager}" type="dashboard" page="${i}" name="${i}" /> </li>
		</c:forEach>
	</c:otherwise>
</c:choose>