import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
    private static final Queue<DatabaseConnection> freeConnections = new LinkedList<>();
    private static final HashSet<DatabaseConnection> connectionInUse = new HashSet<>();
    private static final ConnectionPool connectionPool = new ConnectionPool();
    private static final int MAX_POOL_SIZE = 3;

    public ConnectionPool() {
        for (int i=0; i<MAX_POOL_SIZE; i++) {
            freeConnections.add(new DatabaseConnection("connection-" + (i+1)));
        }
    }

    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    public synchronized DatabaseConnection getConnection() {
        if (freeConnections.isEmpty()) {
            System.out.println("No free connection available");
            return null;
        }

        connectionInUse.add(freeConnections.peek());
        return freeConnections.poll();
    }

    public synchronized void releaseConnection(final DatabaseConnection databaseConnection) {
        if (!connectionInUse.contains(databaseConnection)) {
            System.out.println("Connection is not in use, already release");
            return;
        }

        connectionInUse.remove(databaseConnection);
        if (freeConnections.size() < MAX_POOL_SIZE) {
            freeConnections.add(databaseConnection);
            System.out.println("Releasing connection: " + databaseConnection);
        } else {
            throw new RuntimeException("Connection pool is full, cannot add release connection");
        }
    }

}
