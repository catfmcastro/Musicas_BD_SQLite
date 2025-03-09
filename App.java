import java.sql.*;

public class App {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // todo mudar nome do bd
            connection = DriverManager.getConnection("jdbc:sqlite:teste.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(20);

            // ! apagar depois
            statement.executeUpdate("DROP TABLE IF EXISTS terminalroot");
            statement.executeUpdate("CREATE TABLE terminalroot (id INTEGER, name STRING)");
            statement.executeUpdate("INSERT INTO terminalroot VALUES(1, 'TESTE Marcos Oliveira')");
            statement.executeUpdate("INSERT INTO terminalroot VALUES(2, 'TESTE James Gosling')");
            ResultSet rs = statement.executeQuery("SELECT * FROM terminalroot");
            while (rs.next()) {
                // Ler os dados inseridos
                System.out.println("NOME DO CARA  : " + rs.getString("name"));
                System.out.println("IDENTIFICAÇÃO : " + rs.getInt("id"));
            }
            // ! apagar depois

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Falhou também para fechar o arquivo
                System.err.println(e.getMessage());
            }
        }

    }
}
