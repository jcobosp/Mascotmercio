package es.upm.dit.isst.mascotmercioapifront.controller;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.mascotmercioapifront.model.Establecimiento;
import es.upm.dit.isst.mascotmercioapifront.model.Reservas;
import es.upm.dit.isst.mascotmercioapifront.model.Usuario;
import es.upm.dit.isst.mascotmercioapifront.model.Valoracion;

@Controller
@RequestMapping
public class MascotmercioController {
    private static final Logger log = Logger.getLogger(MascotmercioController.class.getName());

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public final String MASCOTMERCIOMANAGER_STRING;

    private RestTemplate restTemplate = new RestTemplate();

    public MascotmercioController(
            @Value("${mascotmerciomanager.server}") String MASCOTMERCIOMANAGER_STRING) {
        this.MASCOTMERCIOMANAGER_STRING = MASCOTMERCIOMANAGER_STRING;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/index_NotLogged";
    }

    // #region NotLogged
    @GetMapping("/acercaDe_NotLogged")
    public String acercaDe() {
        return "NotLogged/acercaDe_NotLogged";
    }

    @GetMapping("/establecimientos_NotLogged")
    public String establecimientos() {
        return "NotLogged/establecimientos_NotLogged";
    }

    @GetMapping("/index_NotLogged")
    public String index() {
        return "NotLogged/index_NotLogged";
    }

    @GetMapping("/iniciarSesion")
    public String inicioSesion() {
        return "NotLogged/iniciarSesion";
    }

    @GetMapping("/mapa_NotLogged")
    public String mapa(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                    MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                    Establecimiento.class);
            Establecimiento establecimiento = response.getBody();
            model.addAttribute("establecimiento", establecimiento);
        } else {
            Establecimiento establecimiento = new Establecimiento();
            model.addAttribute("establecimiento", establecimiento);
        }

        String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
        List<Establecimiento> lista = Arrays.asList(restTemplate
                .getForEntity(url, Establecimiento[].class).getBody());
        model.addAttribute("establecimientos", lista);
        return "NotLogged/mapa_NotLogged";
    }

    @GetMapping("/registrarse")
    public String registro(ModelMap mp) {
        mp.put("usuario", new Usuario());
        return "NotLogged/registrarse";
    }

    @PostMapping("/registrarse")
    public String crearUsuario(@RequestParam("imagenPerfil") MultipartFile imagenPerfil,
            @RequestParam("user_type") String userType,
            @ModelAttribute("usuario") Usuario usu,
            BindingResult bindingResult, ModelMap mp) {
        if (bindingResult.hasErrors()) {
            return "/layouts/pantallasDeError/errorRegistroUsuario.html";
        } else {
            try {
                usu.setFoto(imagenPerfil.getBytes());
                if (userType.equals("cliente")) {
                    usu.setRol("ROLE_CLIENTE");
                } else if (userType.equals("dueño_local")) {
                    usu.setRol("ROLE_DUEÑO");
                } else {
                    throw new IllegalArgumentException("Invalid user type");
                }
                usu.setContraseña("{bcrypt}" + encoder.encode(usu.getContraseña()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/usuarios", usu, Usuario.class);
            mp.addAttribute("usuario", usu);
            return "redirect:/loginRedirect";
        }
    }

    @GetMapping("/succesfulLogin")
    public String succesfulLogin() {
        return "NotLogged/succesfulLogin";
    }

    @GetMapping("/loginRedirect")
    public String redirectByRole(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"))) {
                return "redirect:/index_Cliente";
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DUEÑO"))) {
                return "redirect:/index_Dueno";
            } else {
                return "redirect:/errorInicioSesion";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/todos")
    public String todos() {
        return "todos.html";
    }
    // #endregion

    // #region Dueño
    @GetMapping("/acercaDe_Dueno")
    public String acercaDeDueno() {
        return "Dueno/acercaDe_Dueno";
    }

    @GetMapping("/editarPerfil_Dueno")
    public String cargarDatosEditarPerfilDueno(Principal principal,
            Model model) {
        String username = principal.getName();
        ResponseEntity<Usuario> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username,
                Usuario.class);
        Usuario usuario = response.getBody();
        String imagenBase64 = Base64.getEncoder().encodeToString(usuario.getFoto());
        model.addAttribute("usuario", usuario);
        model.addAttribute("imagenBase64", imagenBase64);
        return "Dueno/editarPerfil_Dueno";
    }

    @PostMapping("/editarPerfil_Dueno")
    public String editarUsuarioDueno(@RequestParam("imagenPerfil") MultipartFile imagenPerfil, Usuario usuario,
            BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "pantallasDeError/errorRegistroUsuario.html";
        } else {
            try {
                Usuario newUsuario = new Usuario();
                newUsuario.setNombreUsuario(principal.getName());
                newUsuario.setContraseña(usuario.getContraseña());
                newUsuario.setNombreCompleto(usuario.getNombreCompleto());
                newUsuario.setEmail(usuario.getEmail());
                newUsuario.setTelefono(usuario.getTelefono());
                newUsuario.setCiudad(usuario.getCiudad());
                newUsuario.setRol("ROLE_" + usuario.getRol());
                newUsuario.setDescripcion(usuario.getDescripcion());
                if (!imagenPerfil.isEmpty()) {
                    newUsuario.setFoto(imagenPerfil.getBytes());
                } else {
                    newUsuario.setFoto(usuario.getFoto());
                }
                System.out.println("PATCH request received for /usuarios/" + newUsuario.getNombreCompleto());
                restTemplate.put(MASCOTMERCIOMANAGER_STRING + "/usuarios/" + principal.getName(),
                        newUsuario, Usuario.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("usuario", usuario);
            if (usuario.getRol().equals("CLIENTE")) {
                return "redirect:/logout";
            } else {
                return "redirect:/perfil_Dueno";
            }
        }
    }

    @GetMapping("/establecimientos_Dueno")
    public String establecimientosDueno(
            @RequestParam(name = "codigoPostal", required = false) String codigoPostal, Model model, ModelMap mp) {
        String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
        if (codigoPostal != null && !codigoPostal.isEmpty()) {
            url += "?codigoPostal=" + codigoPostal;
        }
        List<Establecimiento> establecimientos = Arrays.asList(restTemplate
                .getForEntity(url, Establecimiento[].class).getBody());
        mp.put("establecimientos", establecimientos);
        List<String> imagenes = new ArrayList<>();
        Map<Establecimiento, String> establecimientosConImagenes = new LinkedHashMap<>();
        for (Establecimiento establecimiento : establecimientos) {
            imagenes.add(Base64.getEncoder().encodeToString(establecimiento.getFoto()));
        }
        for (int i = 0; i < establecimientos.size(); i++) {
            Establecimiento establecimiento = establecimientos.get(i);
            String imagen = imagenes.get(i);
            establecimientosConImagenes.put(establecimiento, imagen);
        }
        mp.put("establecimientosConImagenes", establecimientosConImagenes);
        return "Dueno/establecimientos_Dueno";
    }

    @GetMapping("/index_Dueno")
    public String indexDueno() {
        return "Dueno/index_Dueno";
    }

    @GetMapping("/mapa_Dueno")
    public String mapaDueno(@RequestParam(required = false) Long id, Model model, ModelMap mp) {
        if (id != null) {
            ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                    MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                    Establecimiento.class);
            Establecimiento establecimiento = response.getBody();
            model.addAttribute("establecimiento", establecimiento);
        } else {
            Establecimiento establecimiento = new Establecimiento();
            model.addAttribute("establecimiento", establecimiento);
        }

        String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
        List<Establecimiento> lista = Arrays.asList(restTemplate
                .getForEntity(url, Establecimiento[].class).getBody());
        model.addAttribute("establecimientos", lista);
        mp.put("reserva", new Reservas());

        return "Dueno/mapa_Dueno";
    }

    @GetMapping("/perfil_Dueno")
    public String perfilDueno(Principal principal, Model model) {
        String username = principal.getName();
        ResponseEntity<Usuario> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username,
                Usuario.class);
        Usuario usuario = response.getBody();
        if (usuario.getFoto() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(usuario.getFoto()); // Encode if not null
            model.addAttribute("imagenBase64", imagenBase64);
        } else {
            model.addAttribute("imagenBase64", null); // Pass null to indicate no image
        }
        model.addAttribute("usuario", usuario);
        List<Establecimiento> lista = Arrays.asList(restTemplate
                .getForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos/usuario/" + username,
                        Establecimiento[].class)
                .getBody());
        model.addAttribute("establecimientos", lista);
        return "Dueno/perfil_Dueno";
    }

    @GetMapping("/registrarEstablecimiento_Dueno")
    public String registrarEstablecimientoDueno(ModelMap mp) {
        mp.put("establecimiento", new Establecimiento());
        return "Dueno/registrarEstablecimiento_Dueno";
    }
    // #endregion

    // #region Cliente
    @GetMapping("/acercaDe_Cliente")
    public String acercaDeCliente() {
        return "Cliente/acercaDe_Cliente";
    }

    @GetMapping("/editarPerfil_Cliente")
    public String cargarDatosEditarPerfil(Principal principal, Model model) {
        String username = principal.getName();
        ResponseEntity<Usuario> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username,
                Usuario.class);
        Usuario usuario = response.getBody();
        String imagenBase64 = Base64.getEncoder().encodeToString(usuario.getFoto());
        model.addAttribute("usuario", usuario);
        model.addAttribute("imagenBase64", imagenBase64);
        return "Cliente/editarPerfil_cliente";
    }

    @PostMapping("/editarPerfil_Cliente")
    public String editarUsuario(@RequestParam("imagenPerfil") MultipartFile imagenPerfil, Usuario usuario,
            BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "pantallasDeError/errorRegistroUsuario";
        } else {
            try {
                Usuario newUsuario = new Usuario();
                newUsuario.setNombreUsuario(principal.getName());
                newUsuario.setContraseña(usuario.getContraseña());
                newUsuario.setNombreCompleto(usuario.getNombreCompleto());
                newUsuario.setEmail(usuario.getEmail());
                newUsuario.setTelefono(usuario.getTelefono());
                newUsuario.setCiudad(usuario.getCiudad());
                newUsuario.setRol("ROLE_" + usuario.getRol());
                newUsuario.setDescripcion(usuario.getDescripcion());
                if (!imagenPerfil.isEmpty()) {
                    newUsuario.setFoto(imagenPerfil.getBytes());
                } else {
                    newUsuario.setFoto(usuario.getFoto());
                }
                System.out.println("PATCH request received for /usuarios/" + newUsuario.getNombreCompleto());
                restTemplate.put(MASCOTMERCIOMANAGER_STRING + "/usuarios/" + principal.getName(),
                        newUsuario, Usuario.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("usuario", usuario);
            if (usuario.getRol().equals("DUEÑO")) {
                return "redirect:/logout";
            } else {
                return "redirect:/perfil_Cliente";
            }
        }
    }

    @PostMapping("/eliminarPerfil_Cliente")
    public String eliminarUsuario(Model model, Principal principal) {
        String username = principal.getName();
        String url = MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username;
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Usuario.class);
        // Establecimiento esta = response.getBody();
        // model.addAttribute("establecimiento", esta);
        return "redirect:/logout";
    }

    @PostMapping("/eliminarPerfil_Dueno")
    public String eliminarUsuarioDueno(Model model, Principal principal) {
        String username = principal.getName();
        String url = MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username;
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Usuario.class);
        // Establecimiento esta = response.getBody();
        // model.addAttribute("establecimiento", esta);
        return "redirect:/logout";
    }

    @GetMapping("/establecimientos_Cliente")
    public String establecimientosCliente(
            @RequestParam(name = "codigoPostal", required = false) String codigoPostal, Model model, ModelMap mp) {
        String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
        if (codigoPostal != null && !codigoPostal.isEmpty()) {
            url += "?codigoPostal=" + codigoPostal;
        }
        List<Establecimiento> establecimientos = Arrays.asList(restTemplate
                .getForEntity(url, Establecimiento[].class).getBody());
        mp.put("establecimientos", establecimientos);
        List<String> imagenes = new ArrayList<>();
        Map<Establecimiento, String> establecimientosConImagenes = new LinkedHashMap<>();
        for (Establecimiento establecimiento : establecimientos) {
            imagenes.add(Base64.getEncoder().encodeToString(establecimiento.getFoto()));
        }
        for (int i = 0; i < establecimientos.size(); i++) {
            Establecimiento establecimiento = establecimientos.get(i);
            String imagen = imagenes.get(i);
            establecimientosConImagenes.put(establecimiento, imagen);
        }
        mp.put("establecimientosConImagenes", establecimientosConImagenes);
        return "Cliente/establecimientos_Cliente";
    }

    @GetMapping("/index_Cliente")
    public String indexCliente() {
        return "Cliente/index_Cliente";
    }

    @GetMapping("/mapa_Cliente")
    public String mapaCliente(@RequestParam(required = false) Long id, Model model, ModelMap mp) {
        if (id != null) {
            ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                    MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                    Establecimiento.class);
            Establecimiento establecimiento = response.getBody();
            model.addAttribute("establecimiento", establecimiento);
        } else {
            Establecimiento establecimiento = new Establecimiento();
            model.addAttribute("establecimiento", establecimiento);
        }

        String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
        List<Establecimiento> lista = Arrays.asList(restTemplate
                .getForEntity(url, Establecimiento[].class).getBody());
        model.addAttribute("establecimientos", lista);
        mp.put("reserva", new Reservas());
        return "Cliente/mapa_Cliente";
    }

    @GetMapping("/perfil_Cliente")
    public String perfilCliente(Principal principal, Model model) {
        String username = principal.getName();
        ResponseEntity<Usuario> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username,
                Usuario.class);
        Usuario usuario = response.getBody();
        if (usuario.getFoto() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(usuario.getFoto()); // Encode if not null
            model.addAttribute("imagenBase64", imagenBase64);
        } else {
            model.addAttribute("imagenBase64", null); // Pass null to indicate no image
        }
        model.addAttribute("usuario", usuario);
        return "Cliente/perfil_Cliente";
    }
    // #endregion

    // #region Pantallas de error
    @GetMapping("/errorCrearEstablecimiento")
    public String errorCrearEstablecimiento() {
        return "layouts/pantallasDeError/errorCrearEstablecimiento";
    }

    @GetMapping("/errorInicioSesion")
    public String errorInicioSesion() {
        return "layouts/pantallasDeError/errorInicioSesion";
    }

    @GetMapping("/errorRegistroUsuario")
    public String errorRegistroUsuario() {
        return "layouts/pantallasDeError/errorRegistroUsuario";
    }
    // #endregion

    // #region Métodos que funcionan

    @GetMapping("/establecimientosMapas") // Assuming you're using Spring MVC
    public ResponseEntity<List<Establecimiento>> getEstablecimientos(@RequestParam(required = false) Long id) {
        if (id != null) {
            ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                    MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                    Establecimiento.class);
            Establecimiento establecimiento = response.getBody();
            return ResponseEntity.ok(Arrays.asList(establecimiento));
        } else {
            String url = MASCOTMERCIOMANAGER_STRING + "/establecimientos";
            List<Establecimiento> establecimientos = Arrays.asList(restTemplate
                    .getForEntity(url, Establecimiento[].class).getBody());
            return ResponseEntity.ok(establecimientos);
        }
    }

    // Registrar un usuario

    // @PostMapping("/establecimientos") // en proceso
    // public String crearEstablecimiento(@RequestParam("imagenEstablecimiento")
    // MultipartFile imagenEstablecimiento,
    // @ModelAttribute("establecimiento") Establecimiento es,
    // BindingResult bindingResult, ModelMap mp) {
    // if (bindingResult.hasErrors()) {
    // return "pantallasDeError/errorCrearEstablecimiento.html";
    // } else {
    // try {

    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // String nombreUsuario;
    // if (authentication != null && authentication.getPrincipal() instanceof
    // UserDetails) {
    // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    // nombreUsuario = userDetails.getUsername();
    // } else {
    // // Manejar el caso en que no se pueda obtener el nombre de usuario
    // return "pantallasDeError/errorCrearEstablecimiento.html";
    // }

    // // Obtener el usuario correspondiente al nombre de usuario

    // String url = MASCOTMERCIOMANAGER_STRING + "/usuarios/" + nombreUsuario;
    // ResponseEntity<Usuario> response = restTemplate.getForEntity(url,
    // Usuario.class);
    // Usuario propietario = response.getBody();

    // if (propietario == null) {
    // // Manejar el caso en que no se pueda encontrar el usuario
    // return "pantallasDeError/errorCrearEstablecimiento.html";
    // }

    // // Asignar el usuario autenticado como propietario del establecimiento
    // log.info("EEEEEEEEEEEEEEE" + propietario.getNombreUsuario());

    // es.setUsuario(propietario);
    // es.setFoto(imagenEstablecimiento.getBytes());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos",
    // es, Establecimiento.class);
    // mp.addAttribute("establecimiento", es);
    // return "Dueno/index_Dueno";
    // }
    // }
    // Login de un usuario
    // @PostMapping("/login")
    // public String login(Model model, @RequestParam("nombreUsuario") String
    // username,
    // @RequestParam("contraseña") String password) {
    // log.info("Login: " + username + ", " + password);
    // try {
    // log.info("Fetching user: " + username);
    // ResponseEntity<Usuario> auth = restTemplate.getForEntity(
    // MASCOTMERCIOMANAGER_STRING + "/usuarios/" + username,
    // Usuario.class);
    // log.info("aaaaauth: " + auth);
    // log.info("aaaaauth.getBody: " + auth.getBody());
    // log.info("aaaaauth.getBody.getContraseña: " +
    // auth.getBody().getContraseña());
    // log.info("aaaaauth.getBody.getContraseña.equals: " +
    // auth.getBody().getContraseña());
    // log.info("aaaaauth.getBody.getContraseña.equals: " +
    // auth.getBody().getContraseña().equals(password));
    // if (auth != null && auth.getBody() != null && auth.getBody().getContraseña()
    // != null
    // && auth.getBody().getContraseña().equals(password)) {
    // Cookie cookie = new Cookie("nombreUsuario", username);
    // cookie.setMaxAge(3600 * 24 * 7); // 7 days
    // model.addAttribute("usuario", auth.getBody()); // Add the authenticated user
    // to the model
    // log.info("MOOOOOOOOOOOOOOOOOOOODELL" + model.toString());
    // log.info("User authenticated: " + auth.getBody());
    // return "Cliente/index_Cliente";
    // } else {
    // return "/layouts/pantallasDeError/errorInicioSesion";
    // }
    // } catch (RestClientException e) {
    // log.severe("Error during login: " + e.getMessage());
    // return "/layouts/pantallasDeError/errorInicioSesion";
    // }
    // }

    // @PostMapping("/logoutUser")
    // public String logoutUser(HttpServletResponse response) {
    // // Eliminar la cookie
    // Cookie cookie = new Cookie("nombreUsuario", null);
    // cookie.setMaxAge(0);
    // response.addCookie(cookie);
    // // Redirigir a la página web
    // return "redirect:/index_NotLogged";
    // }

    // #endregion

    // #region Métodos que arreglar
    @PostMapping("/establecimientos") // en proceso
    public String crearEstablecimiento(@RequestParam("imagenEstablecimiento") MultipartFile imagenEstablecimiento,
            @ModelAttribute("establecimiento") Establecimiento es, BindingResult bindingResult, ModelMap mp,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "pantallasDeError/errorCrearEstablecimiento.html";
        } else {
            try {
                // ResponseEntity<Usuario> response = restTemplate.getForEntity(
                // MASCOTMERCIOMANAGER_STRING + "/usuarios/" + principal.getName(),
                // Usuario.class);
                // Usuario usuario = response.getBody();
                // System.out.println("ESTE ES EL USUARIO: " + usuario);
                es.setFoto(imagenEstablecimiento.getBytes());
                es.setUsuario(principal.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ESTE ES EL ESTABLECIMIENTO: " + es);
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos", es, Establecimiento.class);
            mp.addAttribute("establecimiento", es);
            return "redirect:/index_Dueno";
        }
    }
    // #endregion

    @PostMapping("/reservas_Clientes")
    public String crearReservasClientes(@ModelAttribute("reserva") Reservas newReserva,
            BindingResult bindingResult, ModelMap mp, Principal principal, @RequestParam("id") String id,
            @RequestParam(required = false) String establecimientoId) {
        if (bindingResult.hasErrors()) {
            List<String> errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            String mensajesError = String.join(", ", errores);
            log.info("eeeeeeeeeee" + mensajesError);
            return "Cliente/index_cliente.html";
        } else {
            System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");
            System.out.println("ESTA ES LA RESERVA: *************** " + establecimientoId);

            if (establecimientoId != null) {
                Reservas reservasPorId = restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas/" + establecimientoId,
                                Reservas.class)
                        .getBody();
                System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");

                List<Reservas> reservas = Arrays.asList(restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas",
                                Reservas[].class)
                        .getBody());
                if (reservasPorId != null) {
                    System.out.println("ESTA ES LA RESERVA: ***************   NO ESTA EMPTY EL ID");

                    newReserva.setId(reservas.get(reservas.size() - 1).getId() + 1);
                }

                Long idLong = Long.parseLong(establecimientoId);
                newReserva.setIdEstablecimiento(idLong);
            } else {

                Reservas reservasPorId = restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas/" + id,
                                Reservas.class)
                        .getBody();
                System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");

                List<Reservas> reservas = Arrays.asList(restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas",
                                Reservas[].class)
                        .getBody());
                if (reservasPorId != null) {
                    System.out.println("ESTA ES LA RESERVA: ***************   NO ESTA EMPTY EL ID");

                    newReserva.setId(reservas.get(reservas.size() - 1).getId() + 1);
                }

                Long idLong = Long.parseLong(id);
                newReserva.setIdEstablecimiento(idLong);

            }

            newReserva.setNombreUsuario(principal.getName());
            System.out.println("ESTA ES LA RESERVA: ***************" + newReserva);
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas", newReserva, Reservas.class);
            mp.addAttribute("reserva", newReserva);
            return "Cliente/index_cliente.html";
        }
    }

    @PostMapping("/reservas_Duenos")
    public String crearReservasDuenos(@ModelAttribute("reserva") Reservas newReserva,
            BindingResult bindingResult, ModelMap mp, Principal principal, @RequestParam("id") String id,
            @RequestParam(required = false) String establecimientoId) {
        if (bindingResult.hasErrors()) {
            List<String> errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            String mensajesError = String.join(", ", errores);
            log.info("eeeeeeeeeee" + mensajesError);
            return "Dueno/index_Dueno.html";
        } else {
            System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");
            System.out.println("ESTA ES LA RESERVA: *************** " + establecimientoId);

            if (establecimientoId != null) {
                Reservas reservasPorId = restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas/" + establecimientoId,
                                Reservas.class)
                        .getBody();
                System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");

                List<Reservas> reservas = Arrays.asList(restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas",
                                Reservas[].class)
                        .getBody());
                if (reservasPorId != null) {
                    System.out.println("ESTA ES LA RESERVA: ***************   NO ESTA EMPTY EL ID");

                    newReserva.setId(reservas.get(reservas.size() - 1).getId() + 1);
                }

                Long idLong = Long.parseLong(establecimientoId);
                newReserva.setIdEstablecimiento(idLong);
            } else {

                Reservas reservasPorId = restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas/" + id,
                                Reservas.class)
                        .getBody();
                System.out.println("ESTA ES LA RESERVA: *************** Hasta aqui va bien");

                List<Reservas> reservas = Arrays.asList(restTemplate
                        .getForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas",
                                Reservas[].class)
                        .getBody());
                if (reservasPorId != null) {
                    System.out.println("ESTA ES LA RESERVA: ***************   NO ESTA EMPTY EL ID");

                    newReserva.setId(reservas.get(reservas.size() - 1).getId() + 1);
                }

                Long idLong = Long.parseLong(id);
                newReserva.setIdEstablecimiento(idLong);

            }

            newReserva.setNombreUsuario(principal.getName());
            System.out.println("ESTA ES LA RESERVA: ***************" + newReserva);
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/reservas", newReserva, Reservas.class);
            mp.addAttribute("reserva", newReserva);
            return "Dueno/index_Dueno.html";
        }
    }

    @GetMapping("/detallesEstablecimiento_Cliente")
    public String detallesEstablecimientoCliente(@RequestParam("id") Long id, Model model, ModelMap mp) {
        ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                Establecimiento.class);
        Establecimiento establecimiento = response.getBody();
        if (establecimiento.getFoto() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(establecimiento.getFoto()); // Encode if not null
            model.addAttribute("imagenBase64", imagenBase64);
        } else {
            model.addAttribute("imagenBase64", null); // Pass null to indicate no image

        }
        model.addAttribute("establecimiento", establecimiento);

        // Call valoracionesEstablecimiento method and add its result to the model
        String idString = String.valueOf(id);
        valoracionesEstablecimiento_Cliente(idString, model);
        reservasEstablecimiento_Cliente(idString, model);

        // Call valorarEstablecimiento method and add its result to the model
        valorarEstablecimiento_Cliente(idString, model);
        mp.put("reserva", new Reservas());

        List<Valoracion> valoraciones = Arrays.asList(restTemplate
                .getForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/valoraciones",
                        Valoracion[].class)
                .getBody());

        double sumEstrella = 0.0;

        for (Valoracion valoracion : valoraciones) {
            sumEstrella += valoracion.getPuntuacion(); // Assuming you have getEstrella()
        }

        double averageEstrella = valoraciones.isEmpty() ? 0.0 : sumEstrella / valoraciones.size();
        averageEstrella = Math.round(averageEstrella * 10.0) / 10.0;

        Establecimiento newEstablecimiento = new Establecimiento();
        newEstablecimiento.setId(id);
        newEstablecimiento.setEstrellas(averageEstrella);
        newEstablecimiento.setNombre(establecimiento.getNombre());
        newEstablecimiento.setFoto(establecimiento.getFoto());
        newEstablecimiento.setEmail(establecimiento.getEmail());
        newEstablecimiento.setPais(establecimiento.getPais());
        newEstablecimiento.setTelefono(establecimiento.getTelefono());
        newEstablecimiento.setDireccion(establecimiento.getDireccion());
        newEstablecimiento.setCodigoPostal(establecimiento.getCodigoPostal());
        newEstablecimiento.setCiudad(establecimiento.getCiudad());
        newEstablecimiento.setProvincia(establecimiento.getProvincia());
        newEstablecimiento.setFoto(establecimiento.getFoto());
        newEstablecimiento.setDescripcion(establecimiento.getDescripcion());
        newEstablecimiento.setServiciosOfrecidos(establecimiento.getServiciosOfrecidos());
        newEstablecimiento.setMascotasAceptadas(establecimiento.getMascotasAceptadas());
        newEstablecimiento.setUsuario(establecimiento.getUsuario());

        restTemplate.put(MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id, newEstablecimiento,
                Establecimiento.class);

        return "Cliente/detallesEstablecimiento_Cliente";
    }

    @GetMapping("/reservasEstablecimiento_Cliente")
    public void reservasEstablecimiento_Cliente(@RequestParam("id") String id, Model model) {
        ResponseEntity<List<Reservas>> response = restTemplate.exchange(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/reservas",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reservas>>() {
                });
        List<Reservas> reservas = response.getBody();
        model.addAttribute("reservas", reservas);
    }

    @GetMapping("/valoracionesEstablecimiento_Cliente")
    public void valoracionesEstablecimiento_Cliente(@RequestParam("id") String id, Model model) {
        ResponseEntity<List<Valoracion>> response = restTemplate.exchange(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/valoraciones",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Valoracion>>() {
                });
        List<Valoracion> valoraciones = response.getBody();
        model.addAttribute("valoraciones", valoraciones);
    }

    @GetMapping("/valorarEstablecimiento_Cliente")
    public void valorarEstablecimiento_Cliente(@RequestParam("id") String id, Model model) {
        model.addAttribute("valoracion", new Valoracion());
        model.addAttribute("id", id);
    }

    // Falta añadir propiedades al formulario del html
    @PostMapping("/valorarEstablecimiento_Cliente")
    public String crearValoracion_Cliente(@RequestParam("id") String id, @ModelAttribute("valoracion") Valoracion val,
            BindingResult bindingResult,
            ModelMap mp, Principal principal) {
        if (principal == null) {
            return "redirect:/iniciarSesion"; // Redirect to login page if user is not authenticated
        }
        if (bindingResult.hasErrors()) {
            return "pantallasDeError/errorRegistroUsuario";
        } else {
            Long idLong = Long.parseLong(id); // Convert String to Long
            val.setEstablecimiento(idLong); // Pass Long instead of String
            val.setUsuario(principal.getName()); // Set the usuario to the currently logged in user
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos/{id}/valoraciones", val,
                    Valoracion.class, id);
            mp.addAttribute("valoracion", val);
            mp.addAttribute("id", id);
            return "redirect:/detallesEstablecimiento_Cliente?id=" + val.getEstablecimiento();
        }
    }

    @GetMapping("/detallesEstablecimiento_Dueno")
    public String detallesEstablecimientoDueno(@RequestParam("id") Long id, Model model, ModelMap mp) {
        ResponseEntity<Establecimiento> response = restTemplate.getForEntity(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id,
                Establecimiento.class);
        Establecimiento establecimiento = response.getBody();
        if (establecimiento.getFoto() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(establecimiento.getFoto()); // Encode if not null
            model.addAttribute("imagenBase64", imagenBase64);
        } else {
            model.addAttribute("imagenBase64", null); // Pass null to indicate no image

        }
        model.addAttribute("establecimiento", establecimiento);

        // Call valoracionesEstablecimiento method and add its result to the model
        String idString = String.valueOf(id);
        valoracionesEstablecimiento_Dueno(idString, model);
        reservasEstablecimiento_Dueno(idString, model);

        // Call valorarEstablecimiento method and add its result to the model
        valorarEstablecimiento_Dueno(idString, model);
        mp.put("reserva", new Reservas());

        List<Valoracion> valoraciones = Arrays.asList(restTemplate
                .getForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/valoraciones",
                        Valoracion[].class)
                .getBody());

        double sumEstrella = 0.0;

        for (Valoracion valoracion : valoraciones) {
            sumEstrella += valoracion.getPuntuacion(); // Assuming you have getEstrella()
        }

        double averageEstrella = valoraciones.isEmpty() ? 0.0 : sumEstrella / valoraciones.size();
        averageEstrella = Math.round(averageEstrella * 10.0) / 10.0;

        Establecimiento newEstablecimiento = new Establecimiento();
        newEstablecimiento.setId(id);
        newEstablecimiento.setEstrellas(averageEstrella);
        newEstablecimiento.setNombre(establecimiento.getNombre());
        newEstablecimiento.setFoto(establecimiento.getFoto());
        newEstablecimiento.setEmail(establecimiento.getEmail());
        newEstablecimiento.setPais(establecimiento.getPais());
        newEstablecimiento.setTelefono(establecimiento.getTelefono());
        newEstablecimiento.setDireccion(establecimiento.getDireccion());
        newEstablecimiento.setCodigoPostal(establecimiento.getCodigoPostal());
        newEstablecimiento.setCiudad(establecimiento.getCiudad());
        newEstablecimiento.setProvincia(establecimiento.getProvincia());
        newEstablecimiento.setFoto(establecimiento.getFoto());
        newEstablecimiento.setDescripcion(establecimiento.getDescripcion());
        newEstablecimiento.setServiciosOfrecidos(establecimiento.getServiciosOfrecidos());
        newEstablecimiento.setMascotasAceptadas(establecimiento.getMascotasAceptadas());
        newEstablecimiento.setUsuario(establecimiento.getUsuario());

        restTemplate.put(MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id, newEstablecimiento,
                Establecimiento.class);

        return "Dueno/detallesEstablecimiento_Dueno";
    }

    @GetMapping("/reservasEstablecimiento_Dueno")
    public void reservasEstablecimiento_Dueno(@RequestParam("id") String id, Model model) {
        ResponseEntity<List<Reservas>> response = restTemplate.exchange(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/reservas",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reservas>>() {
                });
        List<Reservas> reservas = response.getBody();
        model.addAttribute("reservas", reservas);
    }

    @GetMapping("/valoracionesEstablecimiento_Dueno")
    public void valoracionesEstablecimiento_Dueno(@RequestParam("id") String id, Model model) {
        ResponseEntity<List<Valoracion>> response = restTemplate.exchange(
                MASCOTMERCIOMANAGER_STRING + "/establecimientos/" + id + "/valoraciones",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Valoracion>>() {
                });
        List<Valoracion> valoraciones = response.getBody();
        model.addAttribute("valoraciones", valoraciones);
    }

    @GetMapping("/valorarEstablecimiento_Dueno")
    public void valorarEstablecimiento_Dueno(@RequestParam("id") String id, Model model) {
        model.addAttribute("valoracion", new Valoracion());
        model.addAttribute("id", id);
    }

    @PostMapping("/valorarEstablecimiento_Dueno")
    public String crearValoracion_Dueno(@RequestParam("id") String id, @ModelAttribute("valoracion") Valoracion val,
            BindingResult bindingResult,
            ModelMap mp, Principal principal) {
        if (principal == null) {
            return "redirect:/iniciarSesion"; // Redirect to login page if user is not authenticated
        }
        if (bindingResult.hasErrors()) {
            return "pantallasDeError/errorRegistroUsuario";
        } else {
            Long idLong = Long.parseLong(id); // Convert String to Long
            val.setEstablecimiento(idLong); // Pass Long instead of String
            val.setUsuario(principal.getName()); // Set the usuario to the currently logged in user
            restTemplate.postForEntity(MASCOTMERCIOMANAGER_STRING + "/establecimientos/{id}/valoraciones", val,
                    Valoracion.class, id);
            mp.addAttribute("valoracion", val);
            mp.addAttribute("id", id);
            return "redirect:/detallesEstablecimiento_Dueno?id=" + val.getEstablecimiento();
        }
    }

}
