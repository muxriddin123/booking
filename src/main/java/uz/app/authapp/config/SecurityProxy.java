package uz.app.authapp.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityProxy extends AbstractSecurityWebApplicationInitializer {

    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }

}
