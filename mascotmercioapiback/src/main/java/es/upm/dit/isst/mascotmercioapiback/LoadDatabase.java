package es.upm.dit.isst.mascotmercioapiback;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.upm.dit.isst.mascotmercioapiback.model.Establecimiento;
import es.upm.dit.isst.mascotmercioapiback.model.Reservas;
import es.upm.dit.isst.mascotmercioapiback.model.Usuario;
import es.upm.dit.isst.mascotmercioapiback.model.Valoracion;
import es.upm.dit.isst.mascotmercioapiback.repository.EstablecimientoRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.UsuarioRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.ValoracionRepository;
import es.upm.dit.isst.mascotmercioapiback.repository.ReservasRepository;

@Configuration
class LoadDatabase {

        private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

        @Bean
        CommandLineRunner initDatabase(EstablecimientoRepository establecimientoRepository,
                        UsuarioRepository usuarioRepository,
                        ValoracionRepository valoracionRepository, ReservasRepository reservaRepository) {
                return args -> {

                        // #region Usuarios
                        byte[] fotoUsuario1 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Usuarios/johndoe.jpeg"));
                        byte[] fotoUsuario2 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Usuarios/igorkrum.jpeg"));
                        byte[] fotoUsuario3 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Usuarios/agustinaypepi.jpeg"));
                        byte[] fotoUsuario4 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Usuarios/aaruiz.jpeg"));
                        byte[] fotoUsuario5 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Usuarios/minervamc.jpeg"));

                        Usuario usuario1 = new Usuario("johndoe", "{bcrypt}$2a$04$UruM.AwNeg4EPyU/VYAMCug0A0Buqx.81ArRJqAAsz2a5x8s3rtLe", "John Doe",
                                        "johndoe@gmail.com",
                                        623486972, "Madrid", "ROLE_CLIENTE",
                                        "¡Hola! Soy John y tengo 13 años. Mi mejor amigo se llama Max, es un perro alucinante, me encanta pasar el rato con él. ¡Quiero explorar todos los rincones del mundo con él!",
                                        fotoUsuario1);
                        Usuario usuario2 = new Usuario("igorkrum", "{bcrypt}$2a$04$UruM.AwNeg4EPyU/VYAMCug0A0Buqx.81ArRJqAAsz2a5x8s3rtLe", "Igor Krum",
                                        "krumh@outlook.com",
                                        359818143, "Cadiz", "ROLE_CLIENTE",
                                        "Soy Igor. Llevo tres años en Cádiz, pero vengo de Bulgaria. Me sorprendió encontrar lugares en España donde puedo llevar a mi reptil. Voy a ir probándolos y espero que sean de mi agrado.",
                                        fotoUsuario2);
                        Usuario usuario3 = new Usuario("agustinaypepi", "{bcrypt}$2a$04$UruM.AwNeg4EPyU/VYAMCug0A0Buqx.81ArRJqAAsz2a5x8s3rtLe",
                                        "Agustina Dolores Prieto",
                                        "agusdolorespri@hotmail.com",
                                        656883291, "Badajoz", "ROLE_CLIENTE",
                                        "Me llamo Agustina Dolores Prieto, y mi fiel compañera es la dulce Pepi. Juntas exploramos cada rincón, buscando esos lugares acogedores donde podamos compartir momentos especiales. A mis 75 años, aún tengo energía para descubrir, siempre en busca de nuevas experiencias donde pasar el rato junto a mi querida Pepi.",
                                        fotoUsuario3);
                        Usuario usuario4 = new Usuario("aaruiz", "{bcrypt}$2a$04$UruM.AwNeg4EPyU/VYAMCug0A0Buqx.81ArRJqAAsz2a5x8s3rtLe", "Aaron Ruiz",
                                        "aaronruizz22@yahoo.com",
                                        642061833, "Sabadell", "ROLE_DUEÑO",
                                        "Soy Aaron, 25 años, y he montado mi propio local eco-friendly, pet friendly, vegano y gluten free que está arrasando. Además, tengo dos conejitos geniales con los que me flipa explorar nuevos spots alternativos para pasar el rato los tres.",
                                        fotoUsuario4);
                        Usuario usuario5 = new Usuario("minervamc", "{bcrypt}$2a$04$UruM.AwNeg4EPyU/VYAMCug0A0Buqx.81ArRJqAAsz2a5x8s3rtLe", "Joanne Rowling​",
                                        "mcgonagall@gmail.com",
                                        687311109, "San Sebastian", "ROLE_CLIENTE",
                                        "Me llamo Minerva, y desde hace un año comparto mi vida con una gatita adoptada súper juguetona y curiosa. Me encanta explorar nuevos lugares con ella, así que me emociona encontrar una plataforma donde pueda descubrir establecimientos donde pueda llevarla.",
                                        fotoUsuario5);

                        log.info("Preloading "
                                        + usuarioRepository.save(usuario1));
                        log.info("Preloading "
                                        + usuarioRepository.save(usuario2));
                        log.info("Preloading "
                                        + usuarioRepository.save(usuario3));
                        log.info("Preloading "
                                        + usuarioRepository.save(usuario4));
                        log.info("Preloading "
                                        + usuarioRepository.save(usuario5));
                        // #endregion

                        // #region Establecimientos
                        byte[] fotoEstablecimiento1 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Bonjour_Madame.jpg"));
                        byte[] fotoEstablecimiento2 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Cumple_GUAU.jpg"));
                        byte[] fotoEstablecimiento3 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/CALIFORNIA_PUG.jpg"));
                        byte[] fotoEstablecimiento4 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Study&Pet.jpg"));
                        byte[] fotoEstablecimiento5 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Peak_Hotel.jpg"));
                        byte[] fotoEstablecimiento6 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/New _Retro_Hotel.jpg"));
                        byte[] fotoEstablecimiento7 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/MovieDog.jpg"));
                        byte[] fotoEstablecimiento8 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Juice&Pet.jpg"));
                        byte[] fotoEstablecimiento9 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/Yoga_Puppy.jpg"));
                        byte[] fotoEstablecimiento10 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/CrossDog.jpg"));
                        byte[] fotoEstablecimiento11 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/Establecimientos/BurgPet.jpg"));

                        Establecimiento establecimiento1 = new Establecimiento("Bonjour Madame", fotoEstablecimiento1,
                                        "bonjourmadamecafe@hotmail.com", "España", 615466918,
                                        "Pça. de la Reina, 20, Ciutat Vella", "46003", "Valencia",
                                        "Valencia",
                                        "Ahora es posible llevar a tu mascota a una de las cafeterías con más encanto de Valencia. Disfrute de su ambiente parisino y bohemio junto a su perro o gato y sumérjase en el Paris más profundo con nuestra increíble bollería parisina.",
                                        Arrays.asList("Variedad de cafés de autor", "Brunch",
                                                        "Merienda", "Bollería francesa"),
                                        Arrays.asList("Perros", "Gatos", "Conejos", "Roedores"), null, 2);
                        Establecimiento establecimiento2 = new Establecimiento("Cumple GUAU", fotoEstablecimiento2,
                                        "cumpleguau@outlook.com", "España", 958636110,
                                        "C/ Sócrates, Ronda", "18002",
                                        "Granada", "Granada",
                                        "¿Quieres celebrar el cumpleaños de tu mascota? ¡Nosotros nos encargaremos de organizarlo en nuestro local! Tarta canina, globos, decoración, invitaciones , juguetes y sobretodo mucha diversión. Tu compañero disfrutará de su mejor cumpleaños de la mejor manera, además, ¡podrás invitar a todos sus amigos del parque!¿A qué esperas para reservar?",
                                        Arrays.asList("Alimentación para mascotas",
                                                        "Decoración personalizada del local",
                                                        "Invitaciones personalizadas",
                                                        "Espacio de recreación y ocio", "Animación"),
                                        Arrays.asList("Perros", "Gatos", "Conejos", "Roedores"),
                                        null, 4);
                        Establecimiento establecimiento3 = new Establecimiento("CALIFORNIA PUG", fotoEstablecimiento3,
                                        "californiapug@yahoo.com", "España", 627336820,
                                        "C/ Gran Vía 44", "28013", "Madrid", "Madrid",
                                        "Abre en Madrid la primera coctelería pet friendly. ¿Quieres que tu amigo de cuatro patas salga contigo a tomar unas copas? Ahora es posible en nuestra coctelería California Pug. Una terraza exclusiva en el centro de Madrid donde podrás disfrutar de unas increíbles vistas mientras te tomas uno de nuestros deliciosos cocteles junto a tu mascota. ¡Te esperamos!",
                                        Arrays.asList("Bebidas varias", "Cócteles", "Cócteles de autor",
                                                        "Tapas"),
                                        Arrays.asList("Perros", "Gatos", "Conejos"), null, 5);
                        Establecimiento establecimiento4 = new Establecimiento("Study & Pet", fotoEstablecimiento4,
                                        "study&pet@ayuntamientosalamanca.es", "España", 923441787,
                                        "Pl/ Trujillo", "37003",
                                        "Salamanca", "Salamanca",
                                        "Si acaba estudiando o leyendo en casa para por no dejar a su mascota sola... ¡Ahora está de suerte! Abrimos la primera biblioteca adaptada para su mascota, con una zona de juegos habilitada para ellos mientras usted se concentra. Podrá acceder a la sala en su rato de descanso y ver como su mascota disfruta con un cuidador.",
                                        Arrays.asList("Salas privadas", "Espacios de estudio",
                                                        "Espacios de ocio",
                                                        "Espacio de recreación para animales",
                                                        "Salas de exposiciones", "Salón de actos"),
                                        Arrays.asList("Perros", "Gatos", "Conejos", "Roedores", "Reptiles"),
                                        null, 3);
                        Establecimiento establecimiento5 = new Establecimiento("Peak Hotel", fotoEstablecimiento5,
                                        "peakhotel@peakhotel.es", "España", 981764833,
                                        "Av. de Pedro Barrié de la Maza, 29", "15004", "A Coruña",
                                        "A Coruña",
                                        "¿Quiere que su mascota le acompañe a sus viajes? Ahora es posible en nuestro hotel! Hemos adaptado las instalaciones para usted y su pequeño amigo canino o felino. Podrán disfrutar de una increíble estancia mientras disfrutan de un menú adaptado a su mascota o una cama a su medida. Encontrarán en todas las instalaciones ventajas exclusivas para que su animal de compañía se encuentre lo mas cómodo posible junto a usted. En Peak Hotel creemos en la importancia de que usted y su compañero puedan vivir una experiencia inolvidable fuera de casa.",
                                        Arrays.asList("Estancia pet friendly", "Media pensión",
                                                        "Pensión completa"),
                                        Arrays.asList("Perros", "Gatos", "Conejos"), null, 3);
                        Establecimiento establecimiento6 = new Establecimiento("New Retro Hotel", fotoEstablecimiento6,
                                        "newretrohotel@retrohotel.com", "España", 930796866,
                                        "Plaça Rosa Del Vents 1, Final, Pg. de Joan de Borbó", "08039",
                                        "Barcelona", "Barcelona",
                                        "Desayuno para dos con vistas al mar desde su habitación. Esta es la nueva incorporación que hemos realizado en nuestro Hotel pet friendly. Ahora, además de todas nuestras instalaciones y experiencias, podrá disfrutar junto a su mascota de un increíble desayuno ajustado a su compañero de cuatro patas, mientras disfrutan desde la cama del banquete y las espectaculares vistas.",
                                        Arrays.asList("Media pensión", "Pensión completa",
                                                        "Desayuno en la habitación",
                                                        "Instalaciones acomodadas para mascotas",
                                                        "Pet Menu"),
                                        Arrays.asList("Perros", "Gatos", "Conejos"), null, 1);
                        Establecimiento establecimiento7 = new Establecimiento("MovieDog", fotoEstablecimiento7,
                                        "moviedogcine@gmail.com", "España", 955456366,
                                        "Calle del Marqués de Paradas, 15", "41001",
                                        "Sevilla", "Sevilla",
                                        "¡El primer cine para perros está aquí! Porque sabemos que ellos también disfrutan de una buena película. Ahora podrán ver en la gran pantalla todos los estrenos donde los animales son los principales protagonistas. Con butacas adaptadas para ellos y snacks saludables disfrutarán de la mejor película.",
                                        Arrays.asList("Película de animación", "Menú cine", "Bebidas",
                                                        "Chuches caninas y felinas"),
                                        Arrays.asList("Perros", "Gatos"), null, 5);
                        Establecimiento establecimiento8 = new Establecimiento("Juice & Pet", fotoEstablecimiento8,
                                        "juice&pet@yahoo.com", "España", 683085851,
                                        "C/Astarloa Kalea, 1, Abando", "48001",
                                        "Bilbao", "Bizkaia",
                                        "Descubre nuestras larga carta de increíbles zumos y batidos de cientos de sabores y combinaciones diferentes. Somos un local Petfriendly y podrá venir acompañado de su mascota mientras ambos comparten un rato en compañía fuera del hogar.",
                                        Arrays.asList("Zumos", "Batidos", "Smoothies", "Frappuccinos",
                                                        "Cafés"),
                                        Arrays.asList("Perros", "Gatos", "Conejos", "Pájaros",
                                                        "Reptiles"),
                                        null, 3);
                        Establecimiento establecimiento9 = new Establecimiento("Yoga Puppy", fotoEstablecimiento9,
                                        "yogapuppy@hotmail.com", "España", 851446730,
                                        "Plaza Poeta Alfonso Canales, 4", "29001",
                                        "Málaga", "Málaga",
                                        "Vive esta experiencia única de poder venir junto a su mascota a nuestras clases de Yoga. Con ejercicios adaptados para que puedan disfrutar ambos de la meditación, los estiramientos y conectar más con su amigo.",
                                        Arrays.asList("Yoga", "Pilates", "Meditación",
                                                        "Clases de grupos reducidos"),
                                        Arrays.asList("Perros"), null, 4);
                        Establecimiento establecimiento10 = new Establecimiento("CrossDog", fotoEstablecimiento10,
                                        "crossdog@outlook.com", "España", 669015808,
                                        "", "28000",
                                        "", "",
                                        "Ahora es posible hacer ejercicio mientras juega con su mascota desde el hogar. Somos una empresa de entrenamiento y clases personalizadas online. Ahora no tendrá excusa de sacar tiempo para hacer deporte mientras juega con su amiguito canino.",
                                        Arrays.asList("Ejerecicios personalizados",
                                                        "Rutina adaptada junto a su mascota"),
                                        Arrays.asList("Perros", "Gatos", "Conejos"), null, 4);
                        Establecimiento establecimiento11 = new Establecimiento("BurgPet", fotoEstablecimiento11,
                                        "burgpet@hotmail.com", "España", 966112312,
                                        "C/ Jade, 2, La Zenia", "03189",
                                        "Alicante", "Alicante",
                                        "Creamos la primera hamburguesa adaptada para su animalito. Con esta incorporación a nuestra carta, queremos incentivar que venga acompañado de su compañero mientras ambos disfrutan de una de nuestras sabrosas e increíbles hamburguesas echas en nuestras cocinas, con productos ecológicos y de la mejor calidad. ¡Os esperamos!",
                                        Arrays.asList("Las mejores hamburguesas",
                                                        "Carta variada de hamburguesas y otras especialidades"),
                                        Arrays.asList("Perros", "Gatos", "Reptiles"), null, 4);

                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento1));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento2));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento3));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento4));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento5));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento6));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento7));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento8));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento9));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento10));
                        log.info("Preloading "
                                        + establecimientoRepository.save(establecimiento11));
                        // #endregion

                        // #region Valoraciones
                        Valoracion valoracion1 = new Valoracion(5.0, "Excelente servicio y ambiente acogedor.",
                                        "Este local ofrece una experiencia excepcional. El personal es muy atento y amable, y el ambiente es cálido y acogedor. Sin duda, volveré a visitarlo.",
                                        "johndoe", Long.valueOf(1));
                        Valoracion valoracion2 = new Valoracion(2.0, "Personal atento y precios razonables.",
                                        "El personal fue extremadamente atento y servicial durante toda mi visita. Además, los precios son muy razonables para la calidad de la comida y el servicio. Definitivamente, lo recomendaría a amigos y familiares.",
                                        "higorkrum", Long.valueOf(2));
                        Valoracion valoracion3 = new Valoracion(3.5,
                                        "Decepcionante experiencia, servicio lento y comida mediocre.",
                                        "Lamentablemente, mi visita a este local no estuvo a la altura de mis expectativas.",
                                        "agustinaypepi", Long.valueOf(3));
                        Valoracion valoracion4 = new Valoracion(3.5, "Ambiente relajante y música encantadora.",
                                        "Me encantó la atmósfera tranquila y relajante de este lugar. La música de fondo era perfecta, no demasiado alta, lo que permitió una conversación agradable. Sin duda, un sitio ideal para relajarse después de un largo día.",
                                        "agustinaypepi", Long.valueOf(4));
                        Valoracion valoracion5 = new Valoracion(4.0,
                                        "Comimos genial!",
                                        "Este local ofrece una experiencia verdaderamente única. Las vistas desde el lugar son impresionantes, especialmente al atardecer. La comida y el servicio estuvieron a la altura de las expectativas. ¡No puedo esperar para volver!",
                                        "minervamc", Long.valueOf(5));
                        Valoracion valoracion6 = new Valoracion(3.5,
                                        "Salí decepcionado",
                                        "Cuando leí que aceptaban reptiles, me animé a probarlo porque no muchos establecimientos aprueban tales animales. Pero mi decepción llegó cuando me dijeron que tenía que mantener a mi reptil todo el rato en su jaula, una decepción absoluta ya que para eso lo hubiera dejado en casa. No volveremos.",
                                        "igorkrum ", Long.valueOf(1));
                        Valoracion valoracion7 = new Valoracion(4.0,
                                        "¡Espectacular!",
                                        "Queridos lectores,\n" + //
                                                        "Permítanme compartir mi experiencia en este encantador establecimiento. La atención que recibí fue simplemente espectacular, no podría haber sido mejor. Desde el momento en que entré, fui recibida con una calidez y amabilidad que realmente me conmovieron.\n"
                                                        + //
                                                        "Charlie, el joven que me atendió, fue un verdadero encanto en todo momento. Estuvo atento a todas mis necesidades y consultas, y siempre con una sonrisa en el rostro. ¡Realmente hizo que mi visita fuera una delicia!\n"
                                                        + //
                                                        "Mi querida Pepi y yo disfrutamos de cada momento en este lugar. La atmósfera acogedora y el servicio impecable realmente hicieron que nuestra experiencia fuera inolvidable. Sin duda alguna, volveremos en el futuro.\n"
                                                        + //
                                                        "¡Recomiendo encarecidamente este establecimiento a todos los que busquen una experiencia excepcional y llena de calidez!\n"
                                                        + //
                                                        "Con aprecio,\n" + //
                                                        "Agustina Dolores Prieto",
                                        "agustinaypepi", Long.valueOf(1));
                        Valoracion valoracion8 = new Valoracion(4.0,
                                        "Agradable y alternativo",
                                        "¡Este lugar estuvo bastante decente! Me animé a ir después de ver que era pet-friendly y ofrecía opciones veganas saludables, lo cual es justo mi rollo. Mis dos conejitos estaban tan a gusto, correteando por ahí, que me sentí como un padre orgulloso, ¿sabes?\n"
                                                        + //
                                                        "El ambiente del lugar era relajado y tenía esas buenas vibras que me encantan. El personal fue súper amable y atento, lo cual siempre suma puntos.\n"
                                                        + //
                                                        "La única pega fue que me decepcionó un poco que no tuvieran leche de cabra. Pero aparte de eso, todo estuvo genial. Definitivamente volveremos.",
                                        "aaruiz", Long.valueOf(1));
                        Valoracion valoracion9 = new Valoracion(3.0,
                                        "Bastante antiguo",
                                        "La verdad es que este lugar no me terminó de convencer del todo. Los muebles lucían bastante deteriorados y anticuados. Además, había un montón de decoraciones frágiles por todas partes, me ponía nerviosa pensar que mi gatita podría tirar algo y luego tendría que pagar por ello.\n"
                                                        + //
                                                        "En cuanto a la atención, pues sin más. No estuvieron muy atentos, pero al menos fueron correctos.\n"
                                                        + //
                                                        "En fin, no creo que vuelva por aquí. Prefiero lugares con un ambiente más acogedor y con muebles un poco más cuidados.",
                                        "minervamc", Long.valueOf(1));
                        Valoracion valoracion10 = new Valoracion(4.5,
                                        "Geniaal!!",
                                        "¡Este lugar fue genial para Max y para mí! El ambiente nos encantó, y la comida estaba riquísima. Max se lo comió todo, ¡literalmente todo! Definitivamente vamos a volver pronto.",
                                        "jacinto33", Long.valueOf(1));

                        log.info("Preloading " + valoracionRepository.save(valoracion1));
                        log.info("Preloading " + valoracionRepository.save(valoracion2));
                        log.info("Preloading " + valoracionRepository.save(valoracion3));
                        log.info("Preloading " + valoracionRepository.save(valoracion4));
                        log.info("Preloading " + valoracionRepository.save(valoracion5));
                        log.info("Preloading " + valoracionRepository.save(valoracion6));
                        log.info("Preloading " + valoracionRepository.save(valoracion7));
                        log.info("Preloading " + valoracionRepository.save(valoracion8));
                        log.info("Preloading " + valoracionRepository.save(valoracion9));
                        log.info("Preloading " + valoracionRepository.save(valoracion10));
                        // #endregion

                        // #region Reservas
                        Reservas reserva1 = new Reservas("2025-05-25", "18:00", "John Doe", 1234567890, 4, "Perro",
                                        "Necesito una mesa cerca de la ventana", "johndoe", 1L);
                        log.info("Preloading " + reservaRepository.save(reserva1));
                        // #endregion

                };
        }
}