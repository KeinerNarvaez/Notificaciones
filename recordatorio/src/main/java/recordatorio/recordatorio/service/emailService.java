package recordatorio.recordatorio.service;

import java.time.LocalDateTime;

// Importación de las clases necesarias para enviar correos electrónicos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender; // Clase principal para el envío de correos electrónicos
import org.springframework.mail.javamail.MimeMessageHelper; // Clase para crear y manejar los mensajes de correo
import org.springframework.stereotype.Service; // Anotación para definir un servicio en Spring

// Importación de clases necesarias para el manejo de excepciones y el formato MIME de los correos
import jakarta.mail.MessagingException; // Excepción que se lanza cuando hay un error al enviar el correo
import jakarta.mail.internet.MimeMessage; // Clase que representa un mensaje de correo MIME


// Anotación que indica que esta clase es un servicio gestionado por el contenedor de Spring
@Service
public class emailService {

    // Inyección automática del repositorio de correo, para poder usar JavaMailSender
    @Autowired
    private JavaMailSender emailRepository;

    // Método básico para enviar un correo de prueba
public void basicEmail(String user, LocalDateTime date, String medicine, String email, String doses, int id) {
    try {
        String adressMail = email;
        String subject = "🔔  Recordatorio de Medicación";
        String dynamicUrl = "http://127.0.0.1:5500/notificaciones.html?id=" + id; // URL dinámica con el ID
        
        String bodyMail = "<!DOCTYPE html>" +
                "<html lang='es'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <style>" +
                "        body { margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #f5f3ff 0%, #ede9fe 100%); color: #1f1d2e; line-height: 1.6; }" +
                "        .email-wrapper { max-width: 650px; margin: 20px auto; background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 8px 32px rgba(124, 58, 237, 0.12); border: 1px solid rgba(124, 58, 237, 0.08); }" +
                "        .email-header { background: linear-gradient(135deg, #7c3aed 0%, #8b5cf6 50%, #a855f7 100%); color: white; padding: 40px 30px; text-align: center; position: relative; }" +
                "        .email-header::before { content: ''; position: absolute; top: 0; left: 0; right: 0; bottom: 0; background: url('data:image/svg+xml,<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 100 100\"><defs><pattern id=\"grain\" width=\"100\" height=\"100\" patternUnits=\"userSpaceOnUse\"><circle cx=\"25\" cy=\"25\" r=\"1\" fill=\"%23ffffff\" opacity=\"0.1\"/><circle cx=\"75\" cy=\"75\" r=\"1\" fill=\"%23ffffff\" opacity=\"0.08\"/><circle cx=\"50\" cy=\"10\" r=\"0.5\" fill=\"%23ffffff\" opacity=\"0.06\"/></pattern></defs><rect width=\"100\" height=\"100\" fill=\"url(%23grain)\"/></svg>'); }" +
                "        .email-header h1 { margin: 0; font-size: 26px; font-weight: 600; position: relative; z-index: 1; text-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
                "        .header-subtitle { font-size: 14px; opacity: 0.9; margin-top: 8px; position: relative; z-index: 1; }" +
                "        .email-body { padding: 40px 30px; }" +
                "        .email-body h2 { margin-top: 0; color: #6d28d9; font-size: 22px; font-weight: 600; margin-bottom: 20px; }" +
                "        .greeting-text { font-size: 16px; margin-bottom: 25px; color: #374151; }" +
                "        .info-card { background: linear-gradient(135deg, #faf7ff 0%, #f3f0ff 100%); padding: 25px; border-radius: 12px; margin: 25px 0; border-left: 4px solid #8b5cf6; box-shadow: 0 2px 8px rgba(124, 58, 237, 0.06); }" +
                "        .info-row { display: flex; align-items: center; margin: 12px 0; padding: 8px 0; border-bottom: 1px solid rgba(139, 92, 246, 0.1); }" +
                "        .info-row:last-child { border-bottom: none; margin-bottom: 0; }" +
                "        .info-icon { width: 24px; height: 24px; margin-right: 12px; background: #8b5cf6; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 12px; color: white; flex-shrink: 0; }" +
                "        .info-label { font-weight: 600; color: #6d28d9; min-width: 100px; margin-right: 15px; }" +
                "        .info-value { color: #374151; font-weight: 500; }" +
                "        .reminder-text { background: #eff6ff; padding: 20px; border-radius: 10px; margin: 25px 0; border-left: 4px solid #3b82f6; color: #1e40af; font-size: 15px; }" +
                // Estilos para el botón
                "        .button-container { text-align: center; margin: 30px 0; }" +
                "        .action-button { display: inline-block; background: linear-gradient(135deg, #7c3aed 0%, #8b5cf6 100%); color: white; text-decoration: none; padding: 15px 30px; border-radius: 8px; font-weight: 600; font-size: 16px; box-shadow: 0 4px 12px rgba(124, 58, 237, 0.3); transition: all 0.3s ease; border: none; cursor: pointer; }" +
                "        .action-button:hover { background: linear-gradient(135deg, #6d28d9 0%, #7c3aed 100%); transform: translateY(-2px); box-shadow: 0 6px 16px rgba(124, 58, 237, 0.4); }" +
                "        .action-button:active { transform: translateY(0); }" +
                "        .email-footer { text-align: center; font-size: 13px; color: #6b7280; padding: 30px 20px; background: linear-gradient(135deg, #f9fafb 0%, #f3f4f6 100%); border-top: 1px solid #e5e7eb; }" +
                "        .footer-note { margin-bottom: 10px; }" +
                "        .footer-signature { font-weight: 500; color: #8b5cf6; }" +
                "        @media only screen and (max-width: 650px) {" +
                "            .email-wrapper { margin: 10px; border-radius: 12px; }" +
                "            .email-body, .email-header { padding: 25px 20px; }" +
                "            .email-header h1 { font-size: 22px; }" +
                "            .info-card { padding: 20px 15px; }" +
                "            .info-row { flex-direction: column; align-items: flex-start; }" +
                "            .info-label { min-width: auto; margin-bottom: 5px; }" +
                "            .action-button { padding: 12px 24px; font-size: 14px; }" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='email-wrapper'>" +
                "        <div class='email-header'>" +
                "            <h1>Recordatorio de Medicación</h1>" +
                "            <div class='header-subtitle'>Sistema de Seguimiento Médico</div>" +
                "        </div>" +
                "        <div class='email-body'>" +
                "            <h2>Hola, " + user + ".</h2>" +
                "            <p class='greeting-text'>Te recordamos que es momento de tomar tu medicamento según la programación establecida.</p>" +
                "            " +
                "            <div class='info-card'>" +
                "                <div class='info-row'>" +
                "                    <div class='info-icon'>1</div>" +
                "                    <div class='info-label'>Fecha y Hora:</div>" +
                "                    <div class='info-value'>" + date.toString().replace("T", " a las ") + "</div>" +
                "                </div>" +
                "                <div class='info-row'>" +
                "                    <div class='info-icon'>2</div>" +
                "                    <div class='info-label'>Medicamento:</div>" +
                "                    <div class='info-value'>" + medicine + "</div>" +
                "                </div>" +
                "                <div class='info-row'>" +
                "                    <div class='info-icon'>3</div>" +
                "                    <div class='info-label'>Dosis:</div>" +
                "                    <div class='info-value'>" + doses + " gm</div>" +
                "                </div>" +
                "            </div>" +
                "            " +
                "            <div class='reminder-text'>" +
                "                <strong>Importante:</strong> Recuerda seguir estrictamente las indicaciones de tu profesional de salud. La adherencia al tratamiento es fundamental para tu bienestar." +
                "            </div>" +
                "            " +

                "            <div class='button-container'>" +
                "                <a href='" + dynamicUrl + "' class='action-button' target='_blank'>Configurar Recordatorio</a>" +
                "            </div>" +
                "        </div>" +   
                "        <div class='email-footer'>" +
                "            <div class='footer-note'>Este correo fue generado automáticamente por el sistema. No respondas a este mensaje.</div>" +
                "            <div class='footer-signature'>Sistema de Gestión Médica</div>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
        emailSender(adressMail, subject, bodyMail); // Aquí se envía el correo HTML
    } catch (Exception e) {
        e.printStackTrace(); // Muestra el error en consola para depuración
    }
}





    public void AdvancedMail(String adressMail) {
        try {
            // Dirección de correo, asunto y cuerpo del mensaje de prueba
            //String adressMail = "angelfaridr1@gmail.com";
            //String subject = "Prueba de envio de correo";
            //String bodyMail = "Este es un mensaje de prueba";
            
            // Llamada al método emailSender para enviar el correo
            String subject = "Prueba de envio de correo";
            String bodyMail = "<!DOCTYPE html>\r\n" + //
                                "<html lang=\"es\">\r\n" + //
                                "<head>\r\n" + //
                                "    <meta charset=\"UTF-8\">\r\n" + //
                                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                                "    <title>Correo HTML de Prueba</title>\r\n" + //
                                "    <style>\r\n" + //
                                "        body {\r\n" + //
                                "            font-family: Arial, sans-serif;\r\n" + //
                                "            background-color: #f4f4f4;\r\n" + //
                                "            color: #333;\r\n" + //
                                "            margin: 0;\r\n" + //
                                "            padding: 0;\r\n" + //
                                "        }\r\n" + //
                                "        .email-container {\r\n" + //
                                "            width: 100%;\r\n" + //
                                "            padding: 20px;\r\n" + //
                                "            background-color: #ffffff;\r\n" + //
                                "        }\r\n" + //
                                "        .email-header {\r\n" + //
                                "            background-color: #007BFF;\r\n" + //
                                "            color: #ffffff;\r\n" + //
                                "            padding: 10px;\r\n" + //
                                "            text-align: center;\r\n" + //
                                "        }\r\n" + //
                                "        .email-content {\r\n" + //
                                "            padding: 20px;\r\n" + //
                                "        }\r\n" + //
                                "        .email-footer {\r\n" + //
                                "            text-align: center;\r\n" + //
                                "            padding: 10px;\r\n" + //
                                "            background-color: #f4f4f4;\r\n" + //
                                "        }\r\n" + //
                                "        a {\r\n" + //
                                "            color: #007BFF;\r\n" + //
                                "            text-decoration: none;\r\n" + //
                                "        }\r\n" + //
                                "    </style>\r\n" + //
                                "</head>\r\n" + //
                                "<body>\r\n" + //
                                "    <div class=\"email-container\">\r\n" + //
                                "        <div class=\"email-header\">\r\n" + //
                                "            <h1>¡Hola!</h1>\r\n" + //
                                "        </div>\r\n" + //
                                "        <div class=\"email-content\">\r\n" + //
                                "            <p>Este es un correo de prueba enviado desde nuestra aplicación. Estamos felices de que formes parte de nuestra comunidad.</p>\r\n" + //
                                "            <p>Para más detalles, por favor visita nuestro <a href=\"https://www.ejemplo.com\">sitio web</a>.</p>\r\n" + //
                                "            <p>Si tienes alguna pregunta, no dudes en <a href=\"mailto:soporte@ejemplo.com\">contactarnos</a>.</p>\r\n" + //
                                "        </div>\r\n" + //
                                "        <div class=\"email-footer\">\r\n" + //
                                "            <p>&copy; 2025 Nombre de la Empresa | Todos los derechos reservados.</p>\r\n" + //
                                "        </div>\r\n" + //
                                "    </div>\r\n" + //
                                "</body>\r\n" + //
                                "</html>\r\n" + //
                                "";
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            // Si ocurre un error en el método basicEmail, no hace nada con la excepción
        }
    }
    public void newAccountEmail(String adressMail) {
        try {
            String subject = "Activación de tu cuenta";
            String bodyMail = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>Activación de Cuenta</title>
                </head>
                <body style="font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; color: #333;">
                  <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); padding: 30px;">
                    <h2 style="color: #2c3e50;">¡Bienvenido a EventosOnline!</h2>
                    <p>Hola <strong>Keiner</strong>, gracias por registrarte.</p>
                    <p>Para activar tu cuenta, simplemente haz clic en el siguiente botón:</p>
                    <p style="text-align: center;">
                      <a href="https://www.ejemplo.com/activar?token=xyz123" style="background-color: #3498db; color: white; padding: 10px 20px; border-radius: 5px; text-decoration: none;">Activar Cuenta</a>
                    </p>
                    <p>Si no solicitaste esta activación, puedes ignorar este mensaje.</p>
                    <p style="margin-top: 40px; font-size: 12px; color: #aaa;">EventosOnline © 2025</p>
                  </div>
                </body>
                </html>
                """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            // Manejo de excepción
        }
    }
    public void passwordResetEmail(String adressMail) {
        try {
            String subject = "Restablecimiento de Contraseña";
            String bodyMail = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>Restablecer Contraseña</title>
                </head>
                <body style="font-family: sans-serif; background-color: #f4f6f7; padding: 20px;">
                  <div style="max-width: 600px; margin: auto; background-color: white; border-radius: 10px; padding: 30px; box-shadow: 0 0 10px rgba(0,0,0,0.05);">
                    <h2 style="color: #e74c3c;">¿Olvidaste tu contraseña?</h2>
                    <p>Hola, recibimos una solicitud para restablecer tu contraseña.</p>
                    <p style="text-align: center;">
                      <a href="https://www.ejemplo.com/restablecer?token=xyz123" style="background-color: #e74c3c; color: white; padding: 10px 25px; text-decoration: none; border-radius: 4px;">Restablecer ahora</a>
                    </p>
                    <p>Si no fuiste tú, simplemente ignora este mensaje.</p>
                    <p style="margin-top: 30px; font-size: 12px; color: #888;">Este enlace expirará en 24 horas.</p>
                  </div>
                </body>
                </html>
                """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            // Manejo de excepción
        }
    }
    public void passwordChangeEmail(String adressMail) {
        try {
            String subject = "Confirmación de Cambio de Contraseña";
            String bodyMail = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Cambio de Contraseña</title>
    </head>
    <body style="font-family: 'Segoe UI', sans-serif; background-color: #f2f2f2; margin: 0; padding: 0;">
      <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); overflow: hidden;">
        <div style="background-color: #4CAF50; color: white; padding: 20px 30px;">
          <h2 style="margin: 0;">¡Cambio de Contraseña Exitoso!</h2>
        </div>
        <div style="padding: 30px;">
          <p>Hola <strong>Keiner</strong>,</p>
          <p>Queremos informarte que tu contraseña se ha actualizado correctamente el <strong>25 de abril de 2025</strong>.</p>
          <p>Si no realizaste este cambio, te recomendamos ponerte en contacto con nuestro equipo de soporte inmediatamente.</p>
          <p>También puedes cambiar tu contraseña de nuevo si lo deseas:</p>
          <p style="text-align: center; margin: 30px 0;">
            <a href="https://www.ejemplo.com/cambiar-contraseña" style="background-color: #4CAF50; color: white; text-decoration: none; padding: 12px 25px; border-radius: 5px;">Cambiar Contraseña</a>
          </p>
          <p>Gracias por confiar en <strong>EventosOnline</strong>.</p>
          <p style="margin-top: 40px;">Atentamente,<br><em>El equipo de EventosOnline</em></p>
        </div>
        <div style="background-color: #f0f0f0; padding: 15px; text-align: center; font-size: 12px; color: #777;">
          Este correo fue generado automáticamente. Por favor, no respondas a este mensaje.<br>
          © 2025 EventosOnline. Todos los derechos reservados.
        </div>
      </div>
    </body>
    </html>
    """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
    public void verificationCodeEmail(String adressMail) {
        try {
            String subject = "Código de Verificación";
            String bodyMail = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Código de Verificación</title>
    </head>
    <body style="font-family: 'Segoe UI', sans-serif; background-color: #f9f9f9; padding: 0; margin: 0;">
      <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 3px 10px rgba(0,0,0,0.1); overflow: hidden;">
        <div style="background-color: #3f51b5; color: white; padding: 20px 30px;">
          <h2 style="margin: 0;">Tu Código de Verificación</h2>
        </div>
        <div style="padding: 30px; text-align: center;">
          <p>Gracias por tu solicitud. Usa el siguiente código para continuar:</p>
          <div style="font-size: 36px; font-weight: bold; color: #3f51b5; margin: 20px 0;">123456</div>
          <p>Ingresa este código en la aplicación para completar tu acción.</p>
        </div>
        <div style="background-color: #f0f0f0; padding: 15px; text-align: center; font-size: 12px; color: #666;">
          Si no solicitaste este código, puedes ignorar este mensaje.
        </div>
      </div>
    </body>
    </html>
    """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
    public void lowStockNotification(String adressMail) {
        try {
            String subject = "¡Atención! Producto con stock bajo";
            String bodyMail = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Stock Bajo</title>
    </head>
    <body style="font-family: 'Segoe UI', sans-serif; background-color: #fff8f0; margin: 0; padding: 0;">
      <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">
        <div style="background-color: #ff9800; color: white; padding: 20px 30px;">
          <h2 style="margin: 0;">¡Stock Bajo Detectado!</h2>
        </div>
        <div style="padding: 30px;">
          <p>Hola,</p>
          <p>El producto <strong>Nombre del Producto</strong> está a punto de agotarse.</p>
          <p>No dejes pasar la oportunidad de adquirirlo antes de que se agote.</p>
          <p style="text-align: center; margin-top: 30px;">
            <a href="https://www.ejemplo.com/producto" style="background-color: #ff9800; color: white; text-decoration: none; padding: 12px 20px; border-radius: 5px;">Comprar Ahora</a>
          </p>
        </div>
        <div style="background-color: #f6f6f6; padding: 15px; text-align: center; font-size: 12px; color: #888;">
          Recibes esta notificación porque activaste alertas de stock.
        </div>
      </div>
    </body>
    </html>
    """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
    public void abandonedCartEmail(String adressMail) {
        try {
            String subject = "¡No olvides completar tu compra!";
            String bodyMail = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Carrito Abandonado</title>
    </head>
    <body style="font-family: 'Segoe UI', sans-serif; background-color: #f2f2f2; margin: 0; padding: 0;">
      <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); overflow: hidden;">
        <div style="background-color: #2196F3; color: white; padding: 20px 30px;">
          <h2 style="margin: 0;">¡Tu carrito te está esperando!</h2>
        </div>
        <div style="padding: 30px;">
          <p>Hola, notamos que dejaste algunos artículos en tu carrito de compras.</p>
          <p>No los pierdas. Haz clic abajo para volver a tu carrito y completar tu compra:</p>
          <p style="text-align: center; margin: 30px 0;">
            <a href="https://www.ejemplo.com/mi-carrito" style="background-color: #2196F3; color: white; text-decoration: none; padding: 12px 25px; border-radius: 5px;">Volver al Carrito</a>
          </p>
          <p>¡Aún estás a tiempo de obtener tus productos favoritos!</p>
        </div>
        <div style="background-color: #eeeeee; padding: 15px; text-align: center; font-size: 12px; color: #777;">
          Si ya realizaste la compra, por favor ignora este mensaje.
        </div>
      </div>
    </body>
    </html>
    """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    public void purchaseNotification(String adressMail) {
        try {
            String subject = "Confirmación de Compra - Gracias por tu pedido";
            String bodyMail = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Confirmación de Compra</title>
    </head>
    <body style="font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0;">
      <div style="max-width: 700px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
        <h2 style="color: #4CAF50;">¡Gracias por tu compra, Juan!</h2>
        <p>Hemos recibido tu pedido con éxito. A continuación se muestra un resumen de tu compra:</p>
    
        <table style="width: 100%; border-collapse: collapse; margin-top: 20px;">
          <thead style="background-color: #eeeeee;">
            <tr>
              <th style="padding: 12px; border: 1px solid #ccc; text-align: left;">Producto</th>
              <th style="padding: 12px; border: 1px solid #ccc; text-align: center;">Cantidad</th>
              <th style="padding: 12px; border: 1px solid #ccc; text-align: right;">Precio</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style="padding: 10px 15px; border: 1px solid #ccc;">Camiseta Negra</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: center;">2</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: right;">$19.99</td>
            </tr>
            <tr>
              <td style="padding: 10px 15px; border: 1px solid #ccc;">Jeans Azul</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: center;">1</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: right;">$39.99</td>
            </tr>
            <tr>
              <td style="padding: 10px 15px; border: 1px solid #ccc;">Zapatos Deportivos</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: center;">1</td>
              <td style="padding: 10px 15px; border: 1px solid #ccc; text-align: right;">$59.99</td>
            </tr>
            <tr>
              <td colspan="2" style="padding: 12px; text-align: right; font-weight: bold; border: 1px solid #ccc;">Total:</td>
              <td style="padding: 12px; text-align: right; font-weight: bold; border: 1px solid #ccc;">$119.97</td>
            </tr>
          </tbody>
        </table>
    
        <p style="margin-top: 30px;">Pronto recibirás una notificación cuando tu pedido sea enviado.</p>
        <p>Gracias por confiar en nuestra tienda.</p>
        <p style="color: #888888; font-size: 13px;">Este correo es automático. No respondas a este mensaje.</p>
      </div>
    </body>
    </html>
    """;
            emailSender(adressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
        
                    

    // Método para enviar un correo electrónico con la dirección, asunto y cuerpo especificados
    public boolean emailSender(String adressMail, String subject, String bodyMail) throws MessagingException {
        try {
            // Crear un mensaje MIME
            MimeMessage message = emailRepository.createMimeMessage();
            // Crear un helper para construir el mensaje
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            // Establecer los valores del correo
            helper.setTo(adressMail);  // Establecer la dirección del destinatario
            helper.setSubject(subject); // Establecer el asunto del correo
            helper.setText(bodyMail,true);   // Establecer el contenido del mensaje
            
            // Enviar el correo electrónico
            emailRepository.send(message);
            return true; // Si todo va bien, se devuelve true
        } catch (MessagingException e) {
            // Si ocurre una excepción durante el envío, se imprime el mensaje de error
            System.out.println(e.getMessage());
        }
        return false; // Si hubo un error, se devuelve false
    }
}



