package es.upm.dit.isst.mascotmercioapifront.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.mascotmercioapifront.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Protección contra ataques CSRF (debería estar habilitada para aplicaciones
                // web, y se podría deshabilitar para apps)
                .csrf(csrf -> {
                    csrf.disable();
                })
                // Bloquea CORS por defecto, pero se puede deshabilitar. Se puede fijar a nivel
                // global/controller/handler si esperamos que nuestro cliente cargue datos de
                // distintos sitios)
                // .cors(cors -> {cors.disable();})
                // Fijamos las autorizaciones a cada ruta de mi sistema (de lo más general a lo
                // más específico)
                .authorizeHttpRequests(auth -> {
                    // Permiso para entrar en la consola H2

                    auth.requestMatchers("/indexLogged").authenticated();
                    auth.requestMatchers("/todos/**").authenticated();
                    auth.requestMatchers("/Clientes/**").hasRole("CLIENTE");
                    auth.requestMatchers("/index_Cliente", "/acercaDe_Cliente", "/detallesEstablecimiento_Cliente",
                            "/editarPerfil_Cliente", "/mapa_Cliente", "/perfil_Cliente").hasRole("CLIENTE");
                    auth.requestMatchers("/DUENO/**").hasRole("DUEÑO");
                    auth.requestMatchers("/acercaDe_Dueno", "/detallesEstablecimiento_Dueno", "/editarPerfil_Dueno",
                            "/establecimientos_Dueno", "/index_Dueno", "/mapa_Dueno", "/perfil_Dueno",
                            "/registrarEstablecimiento_Dueno").hasRole("DUEÑO");
                    auth.requestMatchers("/loginRedirect").authenticated();
                    auth.requestMatchers("/h2-console/**").permitAll();
                    auth.requestMatchers("/", "/iniciarsesion", "/index",
                            "/establecimientos", "/mapa", "/registrarse", "/logged")
                            .permitAll();
                    auth.requestMatchers("/userLogin").permitAll();
                    auth.requestMatchers("/succesfulLogin").permitAll();
                    auth.requestMatchers("/iniciarSesion").permitAll();
                    auth.requestMatchers("/assets/**").permitAll();
                    auth.anyRequest().permitAll();

                })
                // Indicamos que usamos el login por defecto (/login /logout)
                .formLogin(form -> form
                        .loginPage("/iniciarSesion")
                        .loginProcessingUrl("/userLogin")
                        .defaultSuccessUrl("/succesfulLogin"))
                .logout(logout -> logout.logoutSuccessUrl("/index_NotLogged"))
                .userDetailsService(customUserDetailsService());
        ;// Podemos indicar si usamos formulario de login propio
         // .formLogin(form -> form.loginPage("/login").permitAll());
        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
        // http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsService() {

            @Autowired
            private RestTemplate restTemplate;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                ResponseEntity<Usuario> response = restTemplate
                        .getForEntity("http://localhost:8080/myApi/usuarios/" + username, Usuario.class);

                if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                    throw new UsernameNotFoundException("User not found");
                }

                Usuario user = response.getBody();
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRol()));

                return new User(user.getNombreUsuario(), user.getContraseña(), authorities);
            }
        };

        // @Bean
        // public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        // String usersByUsernameQuery = "select nombre_usuario, contraseña, true from
        // usuario where nombre_usuario = ?";
        // String authsByUserQuery = "select nombre_usuario, rol from usuario where
        // nombre_usuario = ?";
        // JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        // users.setUsersByUsernameQuery(usersByUsernameQuery);
        // users.setAuthoritiesByUsernameQuery(authsByUserQuery);
        // return users;
        // }
    }
}
