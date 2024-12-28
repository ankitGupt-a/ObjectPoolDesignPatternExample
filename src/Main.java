public class Main {
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {

            DatabaseConnection connection1 = connectionPool.getConnection();
            connection1.connect();
            DatabaseConnection connection2 = connectionPool.getConnection();
            connection2.connect();
            DatabaseConnection connection3 = connectionPool.getConnection();
            connection3.connect();

            DatabaseConnection connection4 = connectionPool.getConnection();

            connection1.disConnect();
            connectionPool.releaseConnection(connection1);

            DatabaseConnection connection5 = connectionPool.getConnection();
            connection5.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}