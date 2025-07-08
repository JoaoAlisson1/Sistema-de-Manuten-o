<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Aeronaves</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .bg-gradient-custom {
            background: linear-gradient(135deg, #2c3e50, #0208b4);
            min-height: 100vh;
        }
    </style>
</head>
<body class="bg-gradient-custom text-white">

<div class="container py-5">
    <!-- Formulário -->
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow">
                <div class="card-header bg-dark text-white">
                    <h4 class="mb-0">
                        <c:choose>
                            <c:when test="${aeronave.id != null}">
                                Editar Aeronave
                            </c:when>
                            <c:otherwise>
                                Cadastro de Aeronaves
                            </c:otherwise>
                        </c:choose>
                    </h4>
                </div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/aeronaves" method="post" modelAttribute="aeronave">
                    <form:hidden path="id" />

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Modelo</label>
                                <form:input path="modelo" cssClass="form-control" placeholder="Modelo" required="required" />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Fabricante</label>
                                <form:input path="fabricante" cssClass="form-control" placeholder="Fabricante" required="required" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Ano de Fabricação</label>
                                <form:input path="anoFabricacao" type="number" cssClass="form-control" placeholder="Ano de Fabricação" required="required" />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Registro Nacional</label>
                                <form:input path="registroNacional" cssClass="form-control" placeholder="Registro Nacional" required="required" />
                            </div>
                        </div>

                        <div class="d-grid">
                            <input type="submit" class="btn btn-success"
                                   value="<c:choose><c:when test='${aeronave.id != null}'>Salvar Alterações</c:when><c:otherwise>Cadastrar</c:otherwise></c:choose>" />
                        </div>
                    </form:form>

                    <c:if test="${not empty msg}">
                        <div class="alert alert-info text-center mt-3">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Lista de Aeronaves -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="text-center mb-3">Lista de Aeronaves</h4>
            <div class="table-responsive">
                <table class="table table-striped table-bordered align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Modelo</th>
                        <th>Fabricante</th>
                        <th>Ano</th>
                        <th>Registro</th>
                        <th colspan="2" class="text-center">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="aeronave" items="${aeronaves}">
                        <tr>
                            <td>${aeronave.modelo}</td>
                            <td>${aeronave.fabricante}</td>
                            <td>${aeronave.anoFabricacao}</td>
                            <td>${aeronave.registroNacional}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/aeronaves/editar/${aeronave.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}aeronaves/excluir/${aeronave.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Tem certeza que deseja excluir esta aeronave?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="text-end mt-4">
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
