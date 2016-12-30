<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer>
    <hr />
    <p class="pull-right">${config_system_copyright}</p>
</footer>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<!--[if lte IE 9]>
<script type="text/javascript" src="/static/js/html5shiv.min.js"></script>
<script type="text/javascript" src="/static/js/respond.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.placeholder.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
$('input[type="text"]').placeholder();
})
</script>
<![endif]-->
<c:if test="${script!=null}">
<script type="text/javascript">
    <c:out value="${script}" escapeXml="false" />
</script>
</c:if>