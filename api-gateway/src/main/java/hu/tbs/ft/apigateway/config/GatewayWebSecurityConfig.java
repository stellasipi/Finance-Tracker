package hu.tbs.ft.apigateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class GatewayWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/oauth2/**", "/swagger**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/springfox-swagger-ui/**", "/v2/api-docs**", "/**/v2/api-docs", "/user/register").permitAll()
                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
//        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
