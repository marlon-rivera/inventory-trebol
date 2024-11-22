package com.trebol.inventory.adapters.driven.mail;

import com.trebol.inventory.domain.model.Sale;
import com.trebol.inventory.domain.model.SaleDetail;
import com.trebol.inventory.domain.spi.IMailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.List;

@RequiredArgsConstructor
public class MailAdapter implements IMailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendInvoice(String to, List<SaleDetail> details, Sale sale) {
        StringBuilder html = new StringBuilder();
        html.append("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Factura Electrónica</title>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; margin: 0; padding: 0; }
                    .container { width: 100%; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f7f7f7; border: 1px solid #ddd; }
                    h2 { text-align: center; color: #1f5d11; }
                    .emitter-info, .customer-info, .total { margin-bottom: 20px; font-size: 0.9em; }
                    .items-table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
                    .items-table th, .items-table td { padding: 10px; border: 1px solid #ddd; text-align: left; }
                    .items-table th { background-color: #1f5d11; color: white; }
                    .text-right { text-align: right; }
                    .total-amount { font-size: 1.2em; font-weight: bold; }
                    .highlight { color: #1f5d11; }
                    .footer-info { font-size: 0.8em; color: #555; margin-top: 20px; text-align: center; }
                </style>
            </head>
            <body>
            <div class="container">
                <h2>Factura Electrónica</h2>
                <div class="emitter-info">
                    <p><strong>Comercio:</strong> Trebol PET.</p>
                    <p><strong>NIT:</strong> 123456789-0</p>
                    <p><strong>Resolución DIAN:</strong> 12345 de 2024-01-01</p>
                    <p><strong>Rango de facturación:</strong> 00001 al 99999</p>
                </div>
            """);
        html.append("""
                    <div class="customer-info">
                    <p><strong>Cliente:</strong> %s</p>
                    <p><strong>Fecha de Compra:</strong> %s</p>
                </div>\s
               \s""".formatted(sale.getClient().getName(), sale.getDate().toString()));

        html.append("""
                <table class="items-table">
                                    <thead>
                                        <tr>
                                            <th>Producto</th>
                                            <th>Cantidad</th>
                                            <th>Precio Unitario</th>
                                            <th>IVA</th>
                                            <th>Subtotal</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                """);

        for(SaleDetail detail: details) {
            html.append("""
                    <tr>
                        <td>%s</td>
                        <td>%s</td>
                        <td>$%.2f</td>
                        <td>$%.2f</td>
                        <td>$%.2f</td>
                    </tr>
                    """.formatted(detail.getProduct().getName(), detail.getQuantitySold(), detail.getUnitPrice(), detail.getIvaPrice(), detail.getSubtotal()));
        }

        html.append("""
                    </tbody>
                </table>
                <div class="total text-right">
                <p class="total-amount">Total IVA: <span class="highlight">$%.2f</span></p>
                <p class="total-amount">Total: <span class="highlight">$%.2f</span></p>
                    <p class="total-amount">Total Neto: <span class="highlight">$%.2f</span></p>
                    <p><strong>CUFE:</strong> ABCD1234567890</p>
                </div>
                <div class="footer-info">
                    <p>Factura electrónica generada según la Resolución 000042 de la DIAN.</p>
                    <p>Representación gráfica de la factura electrónica.</p>
                </div>
            </div>
            </body>
            </html>
            """.formatted(sale.getIva(), sale.getGrossPrice(), sale.getTotalPrice()));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Factura Electrónica de Trebol PET");
            mimeMessageHelper.setText(html.toString(), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
