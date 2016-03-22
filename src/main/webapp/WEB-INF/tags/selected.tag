<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="stringRef" required="true" type="java.lang.String"%>
<%@ attribute name="stringTest" required="true" type="java.lang.String"%>

<c:if test="${stringRef == stringTest}">
	<c:out value="selected">
	</c:out>
</c:if>