<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Mecânicos</title>
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
                            <c:when test="${mecanico.id != null}">
                                Editar Mecânico
                            </c:when>
                            <c:otherwise>
                                Cadastro de Mecânicos
                            </c:otherwise>
                        </c:choose>
                    </h4>
                </div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/mecanicos" method="post" modelAttribute="mecanico">
                        <form:hidden path="id" />

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Nome</label>
                                <form:input path="nome" cssClass="form-control" placeholder="Nome" required="required"/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Registro ANAC</label>
                                <form:input path="registroAnac" cssClass="form-control" placeholder="Registro ANAC" required="required"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Especialidade</label>
                                <form:input path="especialidade" cssClass="form-control" placeholder="Especialidade" required="required"/>
                            </div>
                            <div class="col-md-6 mb-3 d-flex align-items-center">
                                <div class="form-check mt-4">
                                    <form:checkbox path="ativo" cssClass="form-check-input" id="ativo"/>
                                    <label class="form-check-label" for="ativo">Ativo</label>
                                </div>
                            </div>
                        </div>

                        <div class="d-grid">
                            <c:choose>
                                <c:when test="${mecanico.id != null}">
                                    <input type="submit" class="btn btn-success" value="Salvar Alterações" />
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="btn btn-success" value="Cadastrar" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form:form>

                    <c:if test="${not empty msg}">
                        <div class="alert alert-info text-center mt-3">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Lista de Mecânicos -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="text-center mb-3">Lista de Mecânicos</h4>
            <div class="table-responsive">
                <table class="table table-striped table-bordered align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Registro ANAC</th>
                        <th>Especialidade</th>
                        <th>Ativo</th>
                        <th colspan="2" class="text-center">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="mecanico" items="${mecanicos}">
                        <tr>
                            <td>${mecanico.nome}</td>
                            <td>${mecanico.registroAnac}</td>
                            <td>${mecanico.especialidade}</td>
                            <td><c:choose><c:when test="${mecanico.ativo}">Sim</c:when><c:otherwise>Não</c:otherwise></c:choose></td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/mecanicos/editar/${mecanico.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/mecanicos/excluir/${mecanico.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Tem certeza que deseja excluir este mecânico?')">Excluir</a>
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
