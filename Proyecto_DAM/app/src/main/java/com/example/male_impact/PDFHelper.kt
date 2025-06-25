package com.example.male_impact.util

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
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

            document.add(Paragraph("📋 Confirmación de Cita").setBold().setFontSize(20f))
            document.add(Paragraph("Nombre: $nombre $apellido"))
            document.add(Paragraph("Edad: $edad"))
            document.add(Paragraph("Correo: $correo"))
            document.add(Paragraph("Teléfono: $telefono"))
            document.add(Paragraph("Fecha de la cita: $fecha"))
            document.add(Paragraph("Barbería: $origen"))

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
