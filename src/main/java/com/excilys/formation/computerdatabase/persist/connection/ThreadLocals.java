package com.excilys.formation.computerdatabase.persist.connection;

import java.sql.Connection;

public class ThreadLocals {
    
    public static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();
}
