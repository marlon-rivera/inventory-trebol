package com.trebol.inventory.adapters.driven.pdf;

import com.trebol.inventory.domain.model.*;
import com.trebol.inventory.domain.spi.IPdfPort;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

public class PdfGeneratorAdapter implements IPdfPort {
    @Override
    public Invoice generateInvoicePdf(Sale sale, List<SaleDetail> details) {
        String pdfPath = "factura_" + sale.getId() + ".pdf";
        StringBuilder html = new StringBuilder();
        html.append("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8"> </meta>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"> </meta>
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
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return new Invoice(pdfPath, byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateReportCurrentInventory(List<Product> products) {
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                    <title>Reporte de Inventario Actual</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f7fc;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            width: 80%;
                            max-width: 1200px;
                            margin: 30px auto;
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }
                        h1 {
                            text-align: center;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border: 1px solid #ddd;
                        }
                        th {
                            background-color: #4CAF50;
                            color: white;
                            font-size: 1.1em;
                        }
                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }
                        tr:hover {
                            background-color: #ddd;
                        }
                        .text-right {
                            text-align: right;
                        }
                        .highlight {
                            color: #4CAF50;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Inventario Actual por Producto</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Cantidad Disponible</th>
                                    <th>Precio Unitario</th>
                                    <th>Fecha de Vencimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        for (Product product : products) {
            List<Batch> batches = product.getBatches();
            for (Batch batch : batches) {
                html.append(
                        """
                                <tr>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td>$%.2f</td>
                                    <td>%s</td>
                                </tr>
                                """.formatted(product.getName() + " - " + product.getBrand().getName(), batch.getQuantityAvalaible(), batch.getUnitPrice(), batch.getExpirationDate() == null ? "N/A" : batch.getExpirationDate())
                );
            }
        }
        html.append("""
                </tbody>
                        </table>
                    </div>
                </body>
                </html>
                """);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateReportBestSellingProduct(Product product, ProductSalesInfo bestSaleInfo) {
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                    <title>Producto mas vendido</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f7fc;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            width: 80%;
                            max-width: 1200px;
                            margin: 30px auto;
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }
                        h1 {
                            text-align: center;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border: 1px solid #ddd;
                        }
                        th {
                            background-color: #4CAF50;
                            color: white;
                            font-size: 1.1em;
                        }
                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }
                        tr:hover {
                            background-color: #ddd;
                        }
                        .text-right {
                            text-align: right;
                        }
                        .highlight {
                            color: #4CAF50;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Producto mas vendido</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Cantidad Vendida</th>
                                    <th>Total vendido</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        html.append(
                """
                        <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>$%.2f</td>
                        </tr>
                        """.formatted(product.getName() + " - " + product.getBrand().getName(), bestSaleInfo.getQuantitySold(), bestSaleInfo.getTotalSales())
        );
        html.append("""
                </tbody>
                        </table>
                    </div>
                </body>
                </html>
                """);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateReportSales(int quantitySales, BigDecimal totalSales, BigDecimal totalGross, BigDecimal totalIva) {
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                    <title>Reporte de ventas</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f7fc;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            width: 80%;
                            max-width: 1200px;
                            margin: 30px auto;
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }
                        h1 {
                            text-align: center;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border: 1px solid #ddd;
                        }
                        th {
                            background-color: #4CAF50;
                            color: white;
                            font-size: 1.1em;
                        }
                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }
                        tr:hover {
                            background-color: #ddd;
                        }
                        .text-right {
                            text-align: right;
                        }
                        .highlight {
                            color: #4CAF50;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Reporte de ventas</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>Total de ventas</th>
                                    <th>Total BRUTO </th>
                                    <th>Total IVA</th>
                                    <th>Total NETO vendido</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        html.append(
                """
                        <tr>
                            <td>%s</td>
                            <td>$%.2f</td>
                            <td>$%.2f</td>
                            <td>$%.2f</td>
                        </tr>
                        """.formatted(quantitySales, totalGross, totalIva, totalSales)
        );
        html.append("""
                </tbody>
                        </table>
                    </div>
                </body>
                </html>
                """);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateReportPurchases(int quantityPurchases, BigDecimal totalPurchases) {
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                    <title>Reporte de ventas</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f7fc;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            width: 80%;
                            max-width: 1200px;
                            margin: 30px auto;
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }
                        h1 {
                            text-align: center;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border: 1px solid #ddd;
                        }
                        th {
                            background-color: #4CAF50;
                            color: white;
                            font-size: 1.1em;
                        }
                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }
                        tr:hover {
                            background-color: #ddd;
                        }
                        .text-right {
                            text-align: right;
                        }
                        .highlight {
                            color: #4CAF50;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Reporte de ventas</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>Total de ventas</th>
                                    <th>Total comprado</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        html.append(
                """
                        <tr>
                            <td>%s</td>
                            <td>$%.2f</td>
                        </tr>
                        """.formatted(quantityPurchases, totalPurchases)
        );
        html.append("""
                </tbody>
                        </table>
                    </div>
                </body>
                </html>
                """);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generatePdfBacthes(List<Batch> batches) {
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8"></meta>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                    <title>Tickets</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f7fc;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            width: 80%;
                            max-width: 1200px;
                            margin: 30px auto;
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }
                        h1 {
                            text-align: center;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border: 1px solid #ddd;
                        }
                        th {
                            background-color: #4CAF50;
                            color: white;
                            font-size: 1.1em;
                        }
                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }
                        tr:hover {
                            background-color: #ddd;
                        }
                        .text-right {
                            text-align: right;
                        }
                        .highlight {
                            color: #4CAF50;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Identifiación Productos</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Fecha de vencimiento</th>
                                    <th>Lote</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        for(Batch batch: batches) {
            html.append("""
                    <tr>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                    </tr>
                    """.formatted(batch.getProduct().getName(), batch.getExpirationDate(), batch.getId()));
        }
        html.append("""
                </tbody>
                        </table>
                    </div>
                </body>
                </html>
                """);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
