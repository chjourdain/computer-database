<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<c:set var="resourcesUrl"
	value="${pageContext.request.contextPath}/resources" />
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<h4 align="center" style="color:${service.color};">
			<c:out value="${result}" />
		</h4>
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1><spring:message code="computer.edit"/></h1>

					<springForm:form action="edit" modelAttribute="computerDTO"
						method="POST">
						<springForm:input type="hidden" path="id" name="id"
							value="${computer.id}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name"/></label>
								<springForm:input path="name" name="computerName" type="text"
									class="form-control" id="computerName" data-validation="length"
									data-validation-length="min3" value="${computer.name}" />
								<springForm:errors path="name" cssClass="help-block form-error" />
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="computer.introduced"/></label>
								<springForm:input type="date" class="form-control"
									path="introduced" id="introduced" name="introduced"
									data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									value="${computer.introduced}" />
								<springForm:errors path="introduced"
									cssClass="help-block form-error" />
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="computer.add"/></label>
								<springForm:input path="discontinued" type="date"
									class="form-control" id="discontinued" name="discontinued"
									data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									value="${computer.discontinued }" />
								<span class="help-block form-error">${error['discontinued']}</span>
								<springForm:errors path="discontinued"
									cssClass="help-block form-error" />
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="computer.company"/></label>
								<springForm:select path="companyId" class="form-control"
									id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${map}" var="entry">
										<option value="${entry.key}">
											<f:selected stringTest="${entry.value}"
												stringRef="${computer.companyName}" /> ${entry.value}
										</option>
									</c:forEach>
								</springForm:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="button.edit"/>" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default"><spring:message code="button.cancel"/></a>
						</div>
					</springForm:form>
					<script type="text/javascript"
						src="${resourcesUrl}/js/jquery.min.js"></script>
					<script type="text/javascript"
						src="${resourcesUrl}/js/jquery-form-validator.js"></script>
					<script>
						$.validate();
					</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>