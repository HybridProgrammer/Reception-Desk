<g:if test="${flash.authenticationFailure}">
	Login failed: ${message(code:"authentication.failure."+flash.authenticationFailure.result).encodeAsHTML()}
</g:if>
<auth:form authAction="login" success="[controller:'testing', action:'onlyLoggedInUsers']" error="[controller:'authentication', action:'index']">
    User: <g:textField name="login"/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit" value="Log in"/>
</auth:form>