package recordatorio.recordatorio.config;
// Paquete donde se encuentra la clase

// Importación de clases necesarias para configurar las propiedades de correo
import java.util.Properties; // Clase para manejar propiedades de configuración

// Importación de clases de Spring necesarias para la configuración
import org.springframework.beans.factory.annotation.Value; // Para inyectar valores desde el archivo de configuración
import org.springframework.context.annotation.Bean; // Para declarar un bean de configuración
import org.springframework.context.annotation.Configuration; // Anotación que marca esta clase como una clase de configuración

// Importación de la implementación de JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl;

// Anotación que indica que esta clase es una clase de configuración de Spring
@Configuration
public class emailConfig {

    // Se inyectan los valores de configuración del archivo application.properties o application.yml
    @Value("${spring.mail.host}") // Dirección del servidor SMTP (p.ej., smtp.gmail.com)
    private String host;

    @Value("${spring.mail.port}") // Puerto del servidor SMTP (p.ej., 587 para TLS)
    private int port;

    @Value("${spring.mail.username}") // Nombre de usuario para la autenticación (correo electrónico)
    private String username;

    @Value("${spring.mail.password}") // Contraseña para la autenticación
    private String password;

    // Método que define un bean para el envío de correos electrónicos, configurando un JavaMailSenderImpl
    @Bean
    public JavaMailSenderImpl javaMailSender() {
        // Crear una instancia del JavaMailSenderImpl, que es la implementación de Spring para enviar correos
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        // Configurar el host y el puerto usando los valores inyectados
        mailSender.setHost(host);
        mailSender.setPort(port);
        
        // Configurar el nombre de usuario y la contraseña (autenticación SMTP)
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
        // Obtener las propiedades de la conexión SMTP
        Properties properties = mailSender.getJavaMailProperties();
        
        // Configurar propiedades adicionales necesarias para la conexión SMTP
        properties.put("mail.smtp.auth", "true"); // Activar autenticación SMTP
        properties.put("mail.smtp.starttls.enable", "true"); // Activar STARTTLS para la conexión segura
        
        // Devolver el bean de JavaMailSender configurado
        return mailSender;
    }

}
