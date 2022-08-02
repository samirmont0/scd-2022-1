package instances;

import org.postgresql.jdbc.PgResultSet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBInstance extends AbstractConnection {

    private static final String QUERRY_INSERT = "INSERT INTO :tabela (:colunas) VALUES (:dados_inseridos)";
    private static final String QUERRY_DELETE = "DELETE FROM :tabela WHERE :condicao";
    private static final String QUERRY_SELECT = "SELECT * FROM :tabela WHERE :condicao";
    private static final String QUERRY_UPDATE = "UPDATE :tabela SET :coluna = :valor WHERE :condicao";

    public DBInstance(LogInstance logInstance) {
        super(logInstance);
    }

    public boolean realizaInsert(Long idUsuario, String tabela, String colunas, String dados_inseridos) {
        String processedQuerry = QUERRY_INSERT
                .replaceAll(":tabela", tabela)
                .replaceAll(":colunas", colunas)
                .replaceAll(":dados_inseridos", dados_inseridos);
        boolean sucesso = false;
        synchronized (this) {
            try {
                sucesso = statement.execute(processedQuerry);
                if (sucesso) {
                    logInstance.registraAcao(idUsuario, tabela, "INSERT", dados_inseridos, null);
                }
            } catch (Exception ignored) {
            }

        }
        return sucesso;
    }

    public boolean realizaDelete(Long idUsuario, String tabela, String condicao) {
        String processedQuerryDelete = QUERRY_DELETE
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        boolean sucesso = false;
        synchronized (this) {
            try {
                ResultSet dados = statement.executeQuery(processedQuerrySelect);
                String dados_anteriores = convertResultSetString(dados);
                sucesso = (statement.executeUpdate(processedQuerryDelete) >= 1);
                if (sucesso) {
                    logInstance.registraAcao(idUsuario, tabela, "INSERT", null, dados_anteriores);
                }
            } catch (Exception ignored) {
            }

        }
        return sucesso;
    }

    public String realizaSelect(Long idUsuario, String tabela, String condicao) {
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        String dadosString = "";
        synchronized (this) {
            try {
                ResultSet dados = statement.executeQuery(processedQuerrySelect);
                dadosString = convertResultSetString(dados);
                if (!dadosString.equals("")) {
                    logInstance.registraAcao(idUsuario, tabela, "INSERT", null, dadosString);
                }
            } catch (Exception ignored) {
            }

        }
        return dadosString;
    }

    public boolean realizaUpdate(Long idUsuario, String tabela, String condicao, String coluna, String valor) {
        String processedQuerry = QUERRY_UPDATE
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao)
                .replaceAll(":coluna", coluna)
                .replaceAll(":valor", valor);
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao)
                .replaceAll("\\*", coluna);
        boolean sucesso = false;
        synchronized (this) {
            try {
                ResultSet dados = statement.executeQuery(processedQuerrySelect);
                String dados_anteriores = convertResultSetString(dados);
                sucesso = (statement.executeUpdate(processedQuerry) >= 1);
                if (sucesso) {
                    logInstance.registraAcao(idUsuario, tabela, "INSERT", coluna + "=" + valor, dados_anteriores);
                }
            } catch (Exception ignored) {
            }

        }
        return sucesso;
    }

    private String convertResultSetString(ResultSet dados) {
        try {
            ResultSetMetaData rsmd = dados.getMetaData();
            int numeroColunas = rsmd.getColumnCount();
            String resultadoString = "";
            while (dados.next()) {
                resultadoString = resultadoString + "[";
                for (int i = 1; i <= numeroColunas; i++) {
                    String coluna = rsmd.getColumnName(i);
                    String valor = dados.getString(coluna);
                    resultadoString = resultadoString + coluna + "=" + valor;
                }
                resultadoString = resultadoString + "]";
            }
            return resultadoString;
        } catch (Exception e) {
            return "";
        }
    }
}
