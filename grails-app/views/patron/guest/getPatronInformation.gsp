<g:form>
	<label>Name: </label><g:textField name="name" value="${myValue}" /></label>
	<label>Z Number: </label><g:textField name="zNumber" value="${myValue}" /></label>
	<g:select name="majorId"
          from="${major.toList()}"
          optionKey="id"
          optionValue="displayName"
          noSelection="['':'-Choose Your Major']"
           />
    <g:submitButton name="save" value="Save" />
    <g:submitButton name="cancel" value="Cancel" />
</g:form>

<g:link event="cancel">Cancel Link</g:link>