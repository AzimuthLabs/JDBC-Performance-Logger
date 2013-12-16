package ch.sla.jdbcperflogger.logger;

import java.io.IOException;

import javax.annotation.Nullable;

import org.junit.Assert;
import org.junit.Test;

public class PerfLoggerServerThreadTest {
    @Nullable
    private ClassLoader classLoaderInsideThread;

    @Test
    public void testCCL() throws InterruptedException, IOException {
        final ClassLoader oldCcl = currentCcl();
        try {
            final MyClassLoader myClassLoader = new MyClassLoader();
            Thread.currentThread().setContextClassLoader(myClassLoader);

            final PerfLoggerServerThread thread = PerfLoggerServerThread.spawn(0);
            Thread.sleep(1000);
            classLoaderInsideThread = thread.getContextClassLoader();
            thread.serverSocket.close();
            thread.done = true;
            thread.interrupt();
            thread.join();

            Assert.assertNotSame(myClassLoader, classLoaderInsideThread);
        } finally {
            Thread.currentThread().setContextClassLoader(oldCcl);
        }
    }

    private static ClassLoader currentCcl() {
        return Thread.currentThread().getContextClassLoader();
    }

}
