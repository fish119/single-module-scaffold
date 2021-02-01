package site.fish.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import site.fish.config.ExceptionMessage;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: [Spring Security 配置类]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:02
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    @Autowired
    private AccessDecisionManagerImpl accessDecisionManagerImpl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * antMatchers 在 AccessDecisionManagerImpl.decide()方法中动态配置
     **/
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //添加跨域filter
                .addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //401 authException 未认证异常处理
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                //403 access denied 访问无权限资源时的异常处理
                .accessDeniedHandler(accessDeniedHandler()).and()
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 关闭 CSRF
                .cors().and().csrf().disable()
                .formLogin().disable()
//                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                //permitAll() 无需认证的请求，如OPTIONS类请求和 /test 等，在accessDecisionManagerImpl.decide()方法中配置
                .accessDecisionManager(accessDecisionManagerImpl);
    }

    /**
     * 跨域配置
     *
     * @return CorsFilter
     */
    @Bean
    public Filter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * 410异常处理，处理匿名用户访问无权限资源时的异常
     *
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
            logger.error("401:" + authException.getMessage() + "|||Url=" + request.getRequestURI());
            String msg = ObjectUtils.isEmpty(authException.getLocalizedMessage()) ? ExceptionMessage.BAD_CREDENTIALS : authException.getLocalizedMessage();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Full authentication is required to access this resource".equals(authException.getLocalizedMessage()) ?
                            ExceptionMessage.BAD_CREDENTIALS : msg);
        };
    }

    /**
     * 403异常处理，访问无权限资源时的异常处理
     *
     * @return AccessDeniedHandler
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            logger.error("403:" + accessDeniedException.getLocalizedMessage() + "|||Url=" + request.getRequestURI());
            String msg = ObjectUtils.isEmpty(accessDeniedException.getLocalizedMessage()) ? ExceptionMessage.FORBIDDEN : accessDeniedException.getLocalizedMessage();
            response.sendError(HttpServletResponse.SC_FORBIDDEN, msg);
        };
    }

    /**
     * 注入验证所需AuthenticationManager，供Login等处使用
     *
     * @return AuthenticationManager
     * @throws Exception ex
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
