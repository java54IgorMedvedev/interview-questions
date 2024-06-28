package telran.interviews;

import java.util.*;

public class ConnectionPool {
    private final int size;
    private final LinkedHashMap<Long, Connection> pool;

    public ConnectionPool(int size) {
        this.size = size;
        this.pool = new LinkedHashMap<>(size, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, Connection> eldest) {
                return size() > ConnectionPool.this.size;
            }
        };
    }

    public Connection getConnection(Connection connection) {
        Connection result = pool.get(connection.id());
        if (result == null) {
            pool.put(connection.id(), connection);
            result = connection;
        }
        return result;
    }

    public boolean isInPool(long id) {
        return pool.containsKey(id);
    }
}
