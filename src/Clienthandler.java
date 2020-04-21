/**
 * Listen for a connection on a specified port.  The constructor
 * does not perform any network operations; it just sets some
 * instance variables and starts the thread.  Note that the
 * thread will only listen for one connection, and then will
 * close its server socket.
 */
ConnectionHandler(int port) {  // For acting as the "server."
        state = ConnectionState.LISTENING;
        this.port = port;
        postMessage("\nLISTENING ON PORT " + port + "\n");
        try { setDaemon(true); }
        catch (Exception e) {}
        start();
        }

/**
 * Open a connection to a specified computer and port.  The constructor
 * does not perform any network operations; it just sets some
 * instance variables and starts the thread.
 */
        ConnectionHandler(String remoteHost, int port) {  // For acting as "client."
        state = ConnectionState.CONNECTING;
        this.remoteHost = remoteHost;
        this.port = port;
        postMessage("\nCONNECTING TO " + remoteHost + " ON PORT " + port + "\n");
        try { setDaemon(true); }
        catch (Exception e) {}
        start();
        }
