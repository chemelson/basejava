<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
        <title>Резюме ${resume.fullName}</title>
    </head>
    <body>
        <jsp:include page="fragments/header.jsp"/>
        <section>
            <h1>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h1>
            <p>
                <c:forEach var="contactEntry" items="${resume.contacts}">
                    <jsp:useBean id="contactEntry"
                                 type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                    <%= contactEntry.getKey().toHtml(contactEntry.getValue()) %><br/>
                </c:forEach>
            </p>
            <p>
                <c:forEach var="sectionEntry" items="${resume.sections}">
                    <jsp:useBean id="sectionEntry"
                                 type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType,
                                  ru.javawebinar.basejava.model.AbstractSection>"/>
                    <c:set var="type" value="${sectionEntry.key}"/>
                    <c:set var="section" value="${sectionEntry.value}"/>
                    <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
                    <p>
                        ${type.title}:
                            <c:choose>
                                <c:when test="${type=='OBJECTIVE'}">
                                    <%= ((TextSection) section).getContent() %>
                                </c:when>
                                <c:when test="${type=='PERSONAL'}">
                                    <%= ((TextSection) section).getContent() %>
                                </c:when>
                                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                                    <ul>
                                        <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                            <li>${item}</li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                                    <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>">
                                        <c:choose>
                                            <c:when test="${empty organization.homePage.url}">
                                                <h3>${organization.homePage.name}</h3>
                                            </c:when>
                                            <c:otherwise>
                                                <h3><a href="${organization.homePage.url}">${organization.homePage.name}</a></h3>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="position" items="${organization.positions}">
                                            <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                                                <%=HtmlUtil.formatDates(position)%>
                                        <b>${position.title}</b><br>${position.description}
                                        </c:forEach>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                    </p>
                </c:forEach>
            </p>
            <br/>
            <button onclick="window.history.back()">ОК</button>
        </section>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>