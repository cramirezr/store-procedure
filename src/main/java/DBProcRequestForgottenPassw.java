import java.sql.*;

public class DBProcRequestForgottenPassw implements RequestorForgottenPassw {

    private final String dbUrl;

    public  DBProcRequestForgottenPassw(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public int requestForgottenPassword(int id) {
        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "dani2230")) {
            try (CallableStatement callableStatement = connection.prepareCall("{call forgot_passw_req(?)}")) {
                // set stored procedure input parameters
                callableStatement.setInt(1, id);

                callableStatement.execute();
                ResultSet resultSet = callableStatement.getResultSet();
                resultSet.next();
                int result = resultSet.getInt(1);
                System.out.println("DB PROC forgot_passw_req result " + result);
                return result;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
