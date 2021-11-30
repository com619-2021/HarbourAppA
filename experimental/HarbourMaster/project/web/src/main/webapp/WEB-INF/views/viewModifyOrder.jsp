<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "selectedPage" value = "contact" scope="request"/>
<!-- start of contact.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->

<!--
private Long id;
private String uuid;                    
private String href;
private String name;
private OrderStatus status;
private ResourceAccess resourceAccess;                    
private Date orderDate;
private Date startDate;
private Date endDate;
private String description; 
private List<OrderHref> subOrders;
private PartyHref orderOwner;
private List<OrderChangeRequestHref> changeRequests;
private OrderHref parentOrder;
private List<ResourceHref> resourceOrService;
-->
<main role="main" class="container">

    <div class="row">

        <div class="col-xs-6">
            <H1>View and Modify Order</H1>
        </div>
        <div class="col-xs-6">

            <select class="form-control" name="changeRequestUUID" >
                <c:forEach var="changeRequest" items="${order.changeRequests}">
                    <option value="${changeRequest.uuid}">Date: ${changeRequest.requestDate} Name: ${changeRequest.name}  UUID: ${changeRequest.uuid}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">

        <!-- left hand side current order -->
        <div class="col-xs-6">
            <h3 class="sub-header">Current Order</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-md-1">Property</th>
                            <th class="col-md-2">Current Value</th>
                            <th class="col-md-3"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="col-md-1">order Id</td>
                            <td class="col-md-2"><input type="text" name="orderId" value="${order.id}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Order Uuid</td>
                            <td class="col-md-2"><input type="text" name="orderUuid" value="${order.uuid}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Order Href</td>
                            <td class="col-md-2"><input type="text" name="orderHref" value="${order.href}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Order Name</td>
                            <td class="col-md-2"><input type="text" name="orderName" value="${order.name}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Order Status</td>
                            <td class="col-md-2"><input type="text" name="orderStatus" value="${order.status}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Order Resource Access</td>
                            <td class="col-md-2"><input type="text" name="orderStatus" value="${order.resourceAccess}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Latest Update to Order</td>
                            <td class="col-md-2"><input type="text" name="orderDate" value="${order.orderDate}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">Start Date of Order</td>
                            <td class="col-md-2"><input type="text" name="orderDate" value="${order.startDate}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>
                        <tr>
                            <td class="col-md-1">End Date of Order</td>
                            <td class="col-md-2"><input type="text" name="orderDate" value="${order.endDate}" readonly /></td>
                            <td class="col-md-3"></td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-xs-6">

            <h3 class="sub-header">change request</h3>
            <form action="./viewModifyOrder" method="POST">
                <table class="table table-striped"  >
                    <thead>
                        <tr>
                            <th class="col-md-1">Property</th>
                            <th class="col-md-2">Change Request Value</th>
                            <th class="col-md-3"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="col-md-1">changeOrder Id</td>
                            <td class="col-md-2"><input type="text" id="changeOrderId" name="changeOrderId" value="${changeOrder.id}" <c:if test="${changeOrder.id == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderId')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">change Uuid</td>
                                <td class="col-md-2"><input type="text" id="changeOrderUuid"  name="changeOrderUuid" value="${changeOrder.uuid}" <c:if test="${changeOrder.uuid == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderUuid')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Order Href</td>
                                <td class="col-md-2"><input type="text" id="changeOrderHref" name="changeOrderHref" value="${changeOrder.href}" <c:if test="${changeOrder.href == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderHref')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Order Name</td>
                                <td class="col-md-2"><input type="text" id="changeOrderName" name="changeOrderName" value="${changeOrder.name}" <c:if test="${changeOrder.name == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderName')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Order Status</td>
                                <td class="col-md-2">
                                    <select class="form-control" id="changeOrderStatus" name="changeOrderStatus" <c:if test="${changeOrder.status == null}">disabled style="display:none"</c:if> >
                                    <c:forEach var="orderStatusValue" items="${orderStatusValues}">
                                        <option value="${orderStatusValue}" <c:if test="${changeOrder.status == orderStatusValue}">selected</c:if>  >${orderStatusValue}</option>
                                    </c:forEach>
                                </select>

                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderStatus')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Order Resource Access</td>
                                <td class="col-md-2">
                                    <select class="form-control" id="changeOrderResourceAccess" name="changeOrderResourceAccess" <c:if test="${changeOrder.resourceAccess == null}">disabled style="display:none"</c:if> >
                                    <c:forEach var="resourceAccess" items="${resourceAccessValues}">
                                        <option value="${resourceAccess}" <c:if test="${changeOrder.resourceAccess == resourceAccess}">selected</c:if>  >${resourceAccess}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderResourceAccess')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Latest Update to Order</td>
                                <td class="col-md-2"><input type="text" id="changeOrderDate" name="changeOrderDate" value="${changeOrder.orderDate}" <c:if test="${changeOrder.orderDate == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderDate')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">Start Date of Order</td>
                                <td class="col-md-2"><input type="text" id="changeOrderStartDate" name="changeOrderStartDate" value="${changeOrder.startDate}" <c:if test="${changeOrder.startDate == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderStartDate')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <td class="col-md-1">End Date of Order</td>
                                <td class="col-md-2"><input type="text" id="changeOrderEndDate"  name="changeOrderEndDate" value="${changeOrder.endDate}" <c:if test="${changeOrder.endDate == null}">disabled style="display:none"</c:if> /></td>
                            <td class="col-md-3"><button class="btn btn-sm" type="button" onclick="toggleVisabilityAndDisabled('changeOrderEndDate')" <c:if test="${! allowChangeButtons}">disabled style="display:none"</c:if>>change</button></td>
                            </tr>
                            <tr>
                                <!--change request details --> 
                            </tr>
                            <tr>
                                <!-- 
                                changeRequest
                                 private Order changeRequest;
            private Date requestDate;
            private Date approvedDate;
            private ChangeStatus status;
            private String changeReason;
            private Party changeRequestor;
            private String responseDescription;
            private Long id;
            private String uuid;
            private String href;
                                -->
                                <td class="col-md-1">Change Request Date</td>
                                <td class="col-md-2"></td>
                                <td class="col-md-3"></td>
                            </tr>
                        </tbody>
                    </table>
                <c:if test="${allowChangeButtons}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="action" value="submitChangeRequest">
                    <input type="hidden" name="action" value="${changeRequest.uuid}">
                    <button class="btn" type="submit" >Submit Change Request</button>
                </form> 
            </c:if>
            <c:if test="${! allowChangeButtons}">
                <form action="./viewModifyOrder" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="action" value="acceptChangeRequest">
                    <input type="hidden" name="action" value="${changeRequest.uuid}">
                    <button class="btn" type="submit" >Accept Change Request</button>
                </form>
            </c:if>

        </div>
    </div>
</main>
<script>
    function toggleVisabilityAndDisabled(elmt) {
        x = document.getElementById(elmt);
        if (x.getAttribute('disabled') === null) {
            x.disabled = true;
            x.style.display = 'none';
        } else {
            x.removeAttribute('disabled')
            x.style.display = 'block';
        }
    }
</script>

<jsp:include page="footer.jsp" />
