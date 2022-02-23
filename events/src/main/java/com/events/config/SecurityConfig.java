package com.events.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.events.database.entity.User;
import com.events.security.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private AuthenticationSuccessHandlerImpl successHandler;
//
//	@Autowired
//	private AuthenticationFailureHandlerImpl failureHandler;
//
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
//	@Bean
//	public LoginUrlAuthenticationEntryPoint getAuthenticationEntryPoint() {
//		return new AuthenticationEntryPointImpl("/login/login");
//	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
//			.headers()
				// frame options implemented in a custom filter
//				.frameOptions().disable()
//				.and()
	        .authorizeRequests()
				// this line allows access to these URLs whithout the user logged in
				// they are considered public URLs
	        	.antMatchers("/pub/**", "/error/**", "/login/**","/search").permitAll()
				// these are URLs that the user must be authenticated for
				.antMatchers("/admin/**", "/user/**", "/category/**").authenticated()
	        	.and()
	        .formLogin()
	            // this is the URL for the login page - displays your JSP page for login
				// this needs to be implemented in a controller.
				.loginPage("/login/login")
				// this is the URL where the login page submits to be processed by spring security
				// this is implemented by spring security and does not need a controller
	            .loginProcessingUrl("/login/loginSecurityPost")
	            .successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// TODO Auto-generated method stub
						 // run custom logics upon successful login
						
//						Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//						String currentPrincipalName = authentication.getName();//get the email from the authentication
//
//	                    System.out.println("The user email is " + currentPrincipalName + " has logged in.");

	                     
	                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	                    String username = userDetails.getUsername();
	                     
//	                    System.out.println("The user email is " + username + " has logged in.");
	                     
	                   // User current_user = user_service.findByEmail(currentPrincipalName);
	                    response.sendRedirect("/user/profile");
					}
	            })
	            //.failureHandler(failureHandler)
	            .and()
	        .logout()
				// invalidating the session removes the JSESSION_ID cookie from the browser
				// and removes the user session from tomcat and from spring security
	            .invalidateHttpSession(true)

				// this is the URL that logs out a user.   So in your menu when you implement
				// this is implemented by spring security and does not need a controller
				.logoutUrl("/login/logout")

				// this is the URL the user will be redirected to after the have logged out
				.logoutSuccessUrl("/")
	            .and()
	        .rememberMe()
	        	// this configuration is for remember me and is not required for the class
				// but it would be nice if you implement it
				.key("SR_KEY_1")
	        	.tokenValiditySeconds(60 * 60 * 24 * 30)
	        	.rememberMeParameter("remember-me")
	        	.and()
			.exceptionHandling()
				//.authenticationEntryPoint(getAuthenticationEntryPoint())
				// this is the URL for the access denied page.
				.accessDeniedPage("/error/404");
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(getPasswordEncoder());
	    return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService);
	    auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean 
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
