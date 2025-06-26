package com.example.male_impact.util

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment
import java.io.File

object PDFHelper {
    fun generarReportePDF(
        context: Context,
        nombre: String,
        apellido: String,
        edad: String,
        correo: String,
        telefono: String,
        fecha: String,
        origen: String
    ): File? {
        return try {
            val pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
            val file = File(pdfPath, "cita_${nombre.replace(" ", "_")}.pdf")

            val pdfWriter = PdfWriter(file)
            val pdfDocument = PdfDocument(pdfWriter)
            val document = Document(pdfDocument)

            // Título estilizado
            val titulo = Paragraph("📋 MALE IMPACT - CONFIRMACIÓN DE CITA")
                .setBold()
                .setFontSize(20f)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.BLUE)
            document.add(titulo)

            // Línea divisoria básica
            val linea = Paragraph("────────────────────────────────────")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12f)
                .setFontColor(ColorConstants.GRAY)
            document.add(linea)

            // Detalles de la cita
            val detalles = Paragraph("""
                ▪ Nombre: $nombre $apellido
                ▪ Edad: $edad años
                ▪ Correo: $correo
                ▪ Teléfono: $telefono
                ▪ Fecha: $fecha
                ▪ Servicio: $origen
            """.trimIndent())
                .setFontSize(12f)
                .setMarginTop(15f)
            document.add(detalles)

            // Pie de página
            val pie = Paragraph("Gracias por confiar en Male Impact.")
                .setItalic()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10f)
                .setMarginTop(25f)
            document.add(pie)

            document.close()
            Toast.makeText(context, "📄 PDF generado: ${file.name}", Toast.LENGTH_SHORT).show()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "❌ Error al generar PDF", Toast.LENGTH_SHORT).show()
            null
        }
    }
}
