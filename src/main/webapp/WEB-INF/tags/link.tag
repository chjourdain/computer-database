<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pager" required="true"
	type="com.excilys.formation.computerdatabase.model.Pager"%>

<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ attribute name="name" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ attribute name="nbByPage" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="action" required="false" type="java.lang.String"%>
<%@ attribute name="sort" required="false" type="java.lang.String"%>

<c:if test="${empty page}">
	<c:set var="page" value="${pager.currentPage }" />
</c:if>
<c:if test="${empty nbByPage}">
	<c:set var="nbByPage" value="${pager.nbByPage}" />
</c:if>
<c:if test="${empty search}">
	<c:set var="search" value="${pager.search}" />
</c:if>
<c:if test="${empty sort}">
	<c:set var="sort" value="${pager.sort}" />
</c:if>

<c:choose>
	<c:when test="${action == 'action'}">
	<c:out value="action='${type}?Page=${page}&Nb=${nbByPage}&search=${search}&order=${sort}'" escapeXml="false"/>
	</c:when>
	<c:when test="${action == 'href'}">href="${type}?Page=${page}&Nb=${nbByPage}&search=${search}&order=${sort}"</c:when>
	<c:otherwise>
	<a href="${type}?Page=${page}&Nb=${nbByPage}&search=${search}&order=${sort}">${name}</a>
	</c:otherwise>
</c:choose>

