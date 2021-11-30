<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="org.solent.com504.project.model.user.dto.User"%>
<%@page import="org.solent.com504.project.model.user.dto.UserRoles"%>
<!-- start of viewModifyResource.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <H1>Resource Details</H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyResource" method="post">
            <div>
                <input type="hidden" name="abstractResourceUuid" value="${abstractResource.uuid}"/>
                <input type="hidden" name="action" value="deleteAbstractResource"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn" style="color:red;" type="submit" >Delete Resource</button>
            </div>
        </form>

        <form action="./viewModifyResource" method="post">
            <div>
                <input type="hidden" name="action" value="updateAbstractResource"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn" type="submit" >Update Resource </button>
            </div>
            <table class="table">
                <thead>
                </thead>

                <tbody>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="abstractResourceName" value="${abstractResource.name}" /></td>
                    </tr>
                    <tr>
                        <td>type</td>
                        <td><input type="text" name="abstractTypeName" value="${abstractResource.resourceTypeName}" readonly/></td>
                    </tr>
                    <tr>
                        <td>uuid</td>
                        <td><input type="text" name="abstractResourceUuid" value="${abstractResource.uuid}" size="50" readonly /></td>
                    </tr>
                    <tr>
                        <td>href</td>
                        <td><input type="text" name="abstractResourceHref" value="${abstractResource.href}" size="100" readonly /></td>
                    </tr>
                    <tr>
                        <td>control</td>
                        <td><input type="text" name="abstractResourceResourceController" value="${abstractResource.resourceController}" /></td>
                    </tr>
                    <tr>
                        <td>description</td>
                        <td><input type="text" name="abstractResourceDescription" value="${abstractResource.description}" size="100"/></td>
                    </tr>

                </tbody>
            </table>
        </form>

        <div>
            <p>Manage Characteristics </p>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Characteristic name</th>
                        <th scope="col">value</th>
                        <th scope="col">description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="characteristic" items="${abstractCharacteristics}">
                        <tr>
                    <form action="./viewModifyResource" method="POST">
                        <td><input type="text" name="characteristicName" value="${characteristic.name}" readonly /></td>
                        <td><input type="text" name="characteristicValue" value="${characteristic.value}" /></td>
                        <td><input type="text" name="characteristicDescription" value="${characteristic.description}" /></td>
                        <td>
                            <input type="hidden" name="action" value="updateCharacteristic"/>
                            <input type="hidden" name="abstractResourceUuid" value="${abstractResource.uuid}" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn" type="submit" >Update</button>
                        </td>
                    </form> 
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>


    </div>

</main>

<jsp:include page="footer.jsp" />
