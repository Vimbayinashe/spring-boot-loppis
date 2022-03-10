package se.iths.springloppis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity      // disable default security & enable personalised security below
public class SecurityConfig {
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // BeanConfig automatically replaces authenticationProvider() below (Martin)
    // automatically included in LoppisUserDetailsService (since it is marked by @Service)      (Martin)
//    private final LoppisUserDetailsService userDetailsService;
//
//    public SecurityConfig(LoppisUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {     //auto encrypt & decrypt passwords
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//    }

    // if we have only one userDetailServices we don't need to override this method (Pontus)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider());
//    }

    // Refactor bort "extends WebSecurityConfigurerAdapter"
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()   // temporarily disable csrf protection for demo purposes
                .httpBasic()        // type of login we want - HTTP Basic in this case (sent via HTTP Headers)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/users/signup").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()    // form login for Thymeleaf
                .loginPage("/login").permitAll()
                .and()
                .logout()   // one can create a logout.html template
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                // below only valid if not extending WebSecurityConfigurerAdapter
                .and()
                .build();


    }

}


/*
 * - antMatchers are a scripting language, likens regex
 * - can be used with wild cards (*) to set security rules for specific path patterns (e.g. "/subjects/**")
 */
