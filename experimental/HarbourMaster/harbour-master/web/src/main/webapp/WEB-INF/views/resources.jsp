<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- start of resource.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <div>
        <h1>Manage Resources</h1>
        <p>showing ${abstractResourceListSize} resources: </p>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">name</th>
                    <th scope="col">resourceTypeName</th>
                    <th scope="col">resourceController</th>
                    <th scope="col">uuid</th>
                    <th scope="col">href</th>
                    <th scope="col">resourceOwner</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="abstractResource" items="${abstractResourceList}">
                    <tr>
                        <td>${abstractResource.id}</td>
                        <td>${abstractResource.name}</td>
                        <td>${abstractResource.resourceTypeName}</td>
                        <td>${abstractResource.resourceController}</td>
                        <td>${abstractResource.uuid}</td>
                        <td>${abstractResource.href}</td>
                        <td>todo </td>
                        <td>
                            <form action="./viewModifyResource" method="GET">
                                <input type="hidden" name="abstractResourceUuid" value="${abstractResource.uuid}">
                                <button class="btn" type="submit" >Modify Resource</button>
                            </form> 
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <form action="./viewModifyResource" method="POST">
            <p>Select Owning Party</p>
            <select class="form-control" name="ownerPartyUUID" >
                <c:forEach var="party" items="${partyList}">
                    <option value="${party.uuid}">${party.firstName}  ${party.uuid}</option>
                </c:forEach>
            </select>
            <p>Select Catalogue Template </p>
            <select class="form-control" name="catalogUUID" >
                <c:if test="${empty catalogList}">
                    <option value="notDefined">You need to define a catalogue entry first</option>
                </c:if>

                <c:forEach var="catalog" items="${catalogList}">
                    <option value="${catalog.uuid}">TYPE: ${catalog.resourceTypeName} UUID: ${catalog.uuid}</option>
                </c:forEach>

            </select>
            <!-- creates a new resource -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="action" value="createAbstractResource">

            <button class="btn" type="submit" <c:if test="${empty catalogList}">disabled </c:if> >Add Resource</button>
            </form> 
        </div>
    </main>

<jsp:include page="footer.jsp" />
