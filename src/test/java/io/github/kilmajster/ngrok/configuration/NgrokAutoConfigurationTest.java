package io.github.kilmajster.ngrok.configuration;

import io.github.kilmajster.ngrok.control.NgrokApiClient;
import io.github.kilmajster.ngrok.control.NgrokDownloader;
import io.github.kilmajster.ngrok.control.NgrokRunner;
import io.github.kilmajster.ngrok.control.NgrokSystemCommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = NgrokAutoConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NgrokAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(NgrokAutoConfiguration.class));

    @Test
    public void ngrokAsyncConfigurationShouldBeNotVisibleWhenPropertyIsDisabled() {
        this.contextRunner.withPropertyValues("ngrok.enabled=false");

        this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(NgrokAsyncConfiguration.class));
    }

    @Test
    public void ngrokRunnerShouldBeNotVisibleWhenPropertyIsDisabled() {
        this.contextRunner.withPropertyValues("ngrok.enabled=false");

        this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(NgrokRunner.class));
    }

    @Test
    public void ngrokDownloaderShouldBeNotVisibleWhenPropertyIsDisabled() {
        this.contextRunner.withPropertyValues("ngrok.enabled=false");

        this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(NgrokDownloader.class));
    }

    @Test
    public void ngrokApiClientShouldBeNotVisibleWhenPropertyIsDisabled() {
        this.contextRunner.withPropertyValues("ngrok.enabled=false");

        this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(NgrokApiClient.class));
    }

    @Test
    public void systemCommandExecutorShouldBeNotVisibleWhenPropertyIsDisabled() {
        this.contextRunner.withPropertyValues("ngrok.enabled=false");

        this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(NgrokSystemCommandExecutor.class));
    }
}