package org.cramirezr.procedures;

import java.sql.*;

public class DBProcRequestForgottenPassw implements RequestorForgottenPassw {

    private final String dbUrl;
    private final String user;
    private final String password;

    public  DBProcRequestForgottenPassw(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
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
