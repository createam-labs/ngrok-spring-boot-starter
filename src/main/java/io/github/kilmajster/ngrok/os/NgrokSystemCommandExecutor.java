package io.github.kilmajster.ngrok.os;

import io.github.kilmajster.ngrok.NgrokComponent;
import io.github.kilmajster.ngrok.exception.NgrokCommandExecuteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@NgrokComponent("ngrokCommandExecutor")
public class NgrokSystemCommandExecutor implements SystemCommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(NgrokSystemCommandExecutor.class);

    private final long waitForStartupMillis;

    public NgrokSystemCommandExecutor(@Value("${ngrok.waitForStartup.millis:3000}") long waitForStartupMillis) {
        this.waitForStartupMillis = waitForStartupMillis;
    }

    @Override
    public void execute(final String command) {
        try {
            Runtime.getRuntime().exec(command);
            Thread.sleep(waitForStartupMillis);
        } catch (IOException | InterruptedException e) {
            log.error("Failed to run: " + command, e);

            throw new NgrokCommandExecuteException("Error while executing: " + command, e);
        }
    }
}