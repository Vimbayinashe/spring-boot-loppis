package se.iths.springloppis.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();    // support for older systems' encoder
        //  This above is better -> annotates encryption method (e.g. {brcypt}-hashed-password) & therefore supports
        //  many different types of encoders (e.g. sha256 )

//        return new BCryptPasswordEncoder();
    }
}
