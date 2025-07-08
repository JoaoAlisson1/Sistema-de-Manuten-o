<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Ordens de Serviço</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<style>
    .bg-gradient-custom {
        background: linear-gradient(135deg, #2c3e50, #0208b4);
        min-height: 100vh;
    }
</style>
<body class="bg-gradient-custom text-white">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow">
                <div class="card-header bg-dark text-white">
                    <h3 class="mb-0">
                        ${ordem.id > 0 ? 'Editar Ordem de Serviço' : 'Cadastro de Ordem de Serviço'}
                    </h3>
                </div>
                <div class="card-body">
                    <form action="/servicos" method="post">
                        <input type="hidden" name="id" value="${ordem.id}" />

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Descrição do Serviço</label>
                                <input type="text" name="descricaoServico" class="form-control"
                                       value="${ordem.descricaoServico}" required />
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Tipo de Manutenção</label>
                                <select name="tipoManutencao" class="form-select" required>
                                    <option value="">Selecione</option>
                                    <option value="PREVENTIVA" ${ordem.tipoManutencao == 'PREVENTIVA' ? 'selected' : ''}>Preventiva</option>
                                    <option value="CORRETIVA" ${ordem.tipoManutencao == 'CORRETIVA' ? 'selected' : ''}>Corretiva</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Data de Solicitação</label>
                                <input type="date" name="dataSolicitacao" class="form-control"
                                       value="${ordem.dataSolicitacao}" required />
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Data de Conclusão</label>
                                <input type="date" name="dataConclusao" class="form-control"
                                       value="${ordem.dataConclusao != null ? ordem.dataConclusao : ''}" />
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Status</label>
                                <select name="status" class="form-select" required>
                                    <option value="">Selecione</option>
                                    <option value="PENDENTE" ${ordem.status == 'PENDENTE' ? 'selected' : ''}>Pendente</option>
                                    <option value="EM_EXECUCAO" ${ordem.status == 'EM_EXECUCAO' ? 'selected' : ''}>Em execução</option>
                                    <option value="CONCLUIDO" ${ordem.status == 'CONCLUIDO' ? 'selected' : ''}>Concluída</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Aeronave</label>
                                <select name="aeronave.id" class="form-select" required>
                                    <option value="">Selecione uma aeronave</option>
                                    <c:forEach var="aeronave" items="${aeronaves}">
                                        <option value="${aeronave.id}"
                                                <c:if test="${ordem.aeronave != null && ordem.aeronave.id == aeronave.id}">selected</c:if>>
                                                ${aeronave.modelo} - ${aeronave.registroNacional}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Mecânico</label>
                            <select name="mecanico.id" class="form-select" required>
                                <option value="">Selecione um mecânico</option>
                                <c:forEach var="mecanico" items="${mecanicos}">
                                    <c:if test="${mecanico.ativo}">
                                        <option value="${mecanico.id}"
                                                <c:if test="${ordem.mecanico != null && ordem.mecanico.id == mecanico.id}">selected</c:if>>
                                                ${mecanico.nome} - ${mecanico.registroAnac}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">
                                ${ordem.id > 0 ? 'Salvar Alterações' : 'Cadastrar'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${not empty msg}">
                <div class="alert alert-info mt-3">${msg}</div>
            </c:if>
        </div>
    </div>

    <!-- Lista de ordens -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="mb-3 text-white">Lista de Ordens de Serviço</h4>
            <div class="table-responsive">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Descrição</th>
                        <th>Tipo</th>
                        <th>Solicitação</th>
                        <th>Conclusão</th>
                        <th>Status</th>
                        <th>Aeronave</th>
                        <th>Mecânico</th>
                        <th colspan="2" class="text-center">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ordem" items="${ordens}">
                        <tr>
                            <td>${ordem.descricaoServico}</td>
                            <td>${ordem.tipoManutencao}</td>
                            <td>${ordem.dataSolicitacao}</td>
                            <td><c:out value="${ordem.dataConclusao != null ? ordem.dataConclusao : '-'}" /></td>
                            <td>${ordem.status}</td>
                            <td>${ordem.aeronave.modelo} - ${ordem.aeronave.registroNacional}</td>
                            <td>${ordem.mecanico.nome} - ${ordem.mecanico.registroAnac}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/servicos/editar/${ordem.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/servicos/excluir/${ordem.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Deseja realmente excluir?');">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="text-end mt-3">
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
