<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="resourcesUrl"
	value="${pageContext.request.contextPath}/resources" />
<!-- Bootstrap -->
<link href="${resourcesUrl}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="${resourcesUrl}/css/main.css" rel="stylesheet"
	media="screen">

<script src="${resourcesUrl}/js/jquery.min.js"></script>
<script src="${resourcesUrl}/js/jquery.validate.min.js"></script>
<script src="${resourcesUrl}/js/form.validator.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="computer/dashboard"> Application -
				Computer Database </a> <span style="float: right;"><a
				href="?lang=en"><img src="${resourcesUrl}/icone/en.png"></a> |
				<a href="?lang=fr"><img src="${resourcesUrl}/icone/fr.png"></a></span>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="login" />
					</h1>

					<fieldset>
						<div class="form-group">
							<form action="j_spring_security_check" method="post">
								<c:if test="${param.error != null}">
									<p>
										<spring:message code="login.error" />
									</p>
								</c:if>
								<c:if test="${logout != null}">
									<p>
										<spring:message code="logout" />
									</p>
								</c:if>

								<label for="username"><spring:message code="login.id" /></label>
								<input class="form-control" type="text" id="username"
									name="username" />
						</div>
						<div class="form-group">

							<label for="password"><spring:message
									code="login.password" /></label> <input class="form-control"
								type="password" id="password" name="password" /> <input
								type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" id="_csrf"/>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<button id="submit" type="submit" class="btn btn-primary">
							<spring:message code="login.button" />
						</button>
					</div>

					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>