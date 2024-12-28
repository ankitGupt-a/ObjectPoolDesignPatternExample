public class DatabaseConnection {
    private final String connectionName;

    public DatabaseConnection(final String connectionName) {
        this.connectionName = connectionName;
    }

    public void connect() {
        System.out.println("Connecting using connection: " + connectionName);
    }

    public void disConnect() {
        System.out.println("Disconnecting the connection: " + connectionName);
    }
}
