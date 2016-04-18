<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="resourcesUrl" value="${pageContext.request.contextPath}/resources" />
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet" media="screen">
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
					<h1>Add Computer</h1>
					<form id="formcreate" action="add" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									data-validation="length" data-validation-length="min3">
								<span class="help-block form-error"> ${error['name']} </span>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									name="introduced" placeholder="Introduced date"> <span
									class="help-block form-error">${error['introduced']}</span>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									data-validation="custom"
									data-validation-regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$"
									name="discontinued" placeholder="Discontinued date"> <span
									class="help-block form-error">${error['discontinued']}</span>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${map}" var="entry">
										<option value="${entry.key}">${entry.value}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="submit" type="submit" value="Add"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
					<script type="text/javascript" src="${resourcesUrl}/js/jquery.min.js"></script>
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