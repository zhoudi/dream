<%@ page import="com.pkgplan.dream.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="pkglayout">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
        </sec:ifAllGranted>
		<div id="edit-profile" class="content scaffold-edit" role="main">
			<h2><g:message code="default.edit.label" args="[entityName]" /></h2>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${profileInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${profileInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${profileInstance?.id}" />
				<g:hiddenField name="version" value="${profileInstance?.version}" />
				<g:render template="form"/>

                <div class="pkgsubmit">
                    <table>
                        <tr>
                            <td class="right">
                                <g:actionSubmit action="cancel" class="buttonsub ui-corner-all" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}"/>
                            </td>
                            <td class="left">
                                <g:actionSubmit action="update" class="buttonsub ui-corner-all" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                            </td>
                        </tr>
                    </table>
                </div>

                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <fieldset class="buttons">
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </sec:ifAllGranted>
			</g:form>
		</div>
	</body>
</html>
