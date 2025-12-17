package com.example.proveeduria_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    // Eliminamos el método específico 'sendOrderCreationEmail'
    // y lo reemplazamos por un método genérico para mayor reusabilidad.

    /**
     * Envía un correo electrónico simple (texto plano).
     * * @param toEmail Dirección de correo del destinatario.
     * @param subject Asunto del correo.
     * @param body Cuerpo (contenido) del correo.
     */
    public void sendGenericEmail(String toEmail, String subject, String body) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        // 1. Configurar destinatario, asunto y cuerpo (todos pasados por parámetro)
        message.setFrom("notificaciones@tu-app.com"); 
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        // 2. Enviar el correo
        try {
            mailSender.send(message);
            System.out.println("Correo enviado exitosamente a: " + toEmail + " (Asunto: " + subject + ")");
        } catch (Exception e) {
            // Manejo de errores de envío de correo
            System.err.println("❌ ERROR al enviar correo a " + toEmail + ". Causa: " + e.getMessage());
            // En un entorno de producción, aquí usarías un logger y quizás una cola de reintento.
        }
    }
}