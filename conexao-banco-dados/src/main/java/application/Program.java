package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from teste");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    private void inserirDados() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO test"
                    + "(nome, email, dataNasc)"
                    + "VALUES"
                    + "(?, ?, ?)",  // '?' lugar aonde vc coloca o valor depois
                    Statement.RETURN_GENERATED_KEYS);

            // trocando '?' pelos valores desejados
            st.setString(1, "Lucas"); // numero 1 significa a primeira '?'
            st.setString(2, "lucas@teste.com");
            st.setDate(3, new java.sql.Date(sdf.parse("25/03/2000").getTime()));

            int linhasAlteradas = st.executeUpdate();

            //System.out.println("Concluido com " + linhasAlteradas + "linhas alteradas");

            // pegar id do valor inserido
            if (linhasAlteradas > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("ID = " + id);
                }

            } else {
                System.out.println("Nenhuma linha alterada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    private void atualizandoDados() {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "UPDATE teste"
                    + "SET nome = ?"
                    + "WHERE id = ?");

            st.setString(1, "Lucas Teste");
            st.setInt(2, 3);

            int linhasAfetadas = st.executeUpdate();

            System.out.println("Concluido com " + linhasAfetadas + "linhas alteradas");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    private void deletarDados() {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM teste"
                        + "WHERE id = ?");

            st.setInt(1, 2);

            int linhasAfetadas = st.executeUpdate();

            System.out.println("Concluido com " + linhasAfetadas + "linhas alteradas");

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    private void transacoes() {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false); // nao confirma a operacao automaticamente

            st = conn.createStatement();

            int linhas1 = st.executeUpdate("UPDATE teste SET nome = 'Lucas' WHERE id = 1");

            int x = 1;
            if (x < 2) {
                throw new SQLException("Erro falso");
            }

            int linhas2 = st.executeUpdate("UPDATE teste SET nome = 'Zeca' WHERE id = 2");

            conn.commit(); // confirma a transacao

            System.out.println("Linhas " + linhas1 +", "+ linhas2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transacao nao concluida, causada por " + e.getMessage());
            } catch (SQLException er) {
                throw new DbException("Erro ao tentar voltar a transacao: " + er.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
