package pl.jakub.walczak.driveme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.jakub.walczak.driveme.security.CustomUserDetailsService;
import pl.jakub.walczak.driveme.security.JwtAuthenticationEntryPoint;
import pl.jakub.walczak.driveme.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String ADMIN = "ADMIN";
        final String INSTRUCTOR = "INSTRUCTOR";
        final String STUDENT = "STUDENT";

        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/signin")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/user/me").hasAnyAuthority(ADMIN, INSTRUCTOR, STUDENT)
                .antMatchers(HttpMethod.GET, "/car").hasAnyAuthority(ADMIN, INSTRUCTOR, STUDENT)
                .antMatchers(HttpMethod.POST, "/car").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/car/brands").hasAnyAuthority(ADMIN, INSTRUCTOR, STUDENT)
                .antMatchers(HttpMethod.GET, "/city").hasAnyAuthority(ADMIN, INSTRUCTOR, STUDENT)
                .antMatchers(HttpMethod.POST, "/city").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/instructor").hasAnyAuthority(ADMIN, INSTRUCTOR, STUDENT)
                .antMatchers(HttpMethod.GET, "/course/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.GET, "/payments/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.GET, "/event/**").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.GET, "/driving/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.PUT, "/driving/rate").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.GET, "/driving/instructor").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.POST, "/reservation").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.POST, "/reservation/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.POST, "/reservation/instructor").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.POST, "/reservation/accept/**").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.GET, "/practical_exam/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.GET, "/practical_exam/instructor").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.PUT, "/practical_exam/rate").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.GET, "/student").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/student/activate/**").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/student").hasAuthority(STUDENT)
                .antMatchers(HttpMethod.PUT, "/instructor").hasAuthority(INSTRUCTOR)
                .antMatchers(HttpMethod.POST, "/auth/signup").hasAuthority(ADMIN)
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}
