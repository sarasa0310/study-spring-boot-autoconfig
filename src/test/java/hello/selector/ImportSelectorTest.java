package hello.selector;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

public class ImportSelectorTest {

    @Test
    void staticConfig() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean helloBean = context.getBean(HelloBean.class);
        assertThat(helloBean).isNotNull();
    }

    @Test
    void dynamicConfig() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean helloBean = context.getBean(HelloBean.class);
        assertThat(helloBean).isNotNull();
    }

    @Configuration
    @Import(HelloConfig.class)
    static class StaticConfig {
    }

    @Configuration
    @Import(HelloImportSelector.class)
    static class SelectorConfig {
    }

}
