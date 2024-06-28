package telran.interviews;

import java.util.*;

public class ConnectionPool {
    private final int size;
    private final LinkedHashMap<Long, Connection> pool;

    public ConnectionPool(int size) {
        this.size = size;
        pool = new LinkedHashMap<Long, Connection>(size, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, Connection> eldest) {
                return size() > ConnectionPool.this.size;
            }
        };
    }

    public Connection getConnection(Connection connection) {
        pool.computeIfAbsent(connection.id(), id -> connection);
        if (pool.size() > size) {
            Long eldestKey = pool.keySet().iterator().next();
            pool.remove(eldestKey);
        }
        return connection;
    }

    public boolean isInPool(long id) {
        return pool.containsKey(id);
    }
}
