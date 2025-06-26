package com.example.male_impact.util

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import java.io.File

object PDFHelper {
    fun generarReportePDF(
        context: Context,
        nombre: String,
        apellido: String,
        edad: String,
        correo: String,
        telefono: String,
        motivo: String,
        origen: String): File? {
        return try {
            val pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
            val file = File(pdfPath, "cita_${nombre.replace(" ", "_")}.pdf")
            val pdfWriter = PdfWriter(file)
            val pdfDocument = PdfDocument(pdfWriter)
            val document = Document(pdfDocument)
            val azulFuerte = DeviceRgb(21, 32, 43)
            val azulResaltado = DeviceRgb(33, 150, 243)
            val fondoCaja = DeviceRgb(250, 250, 250)
            val header = Paragraph("💈 MALE IMPACT")
                .setFontSize(26f)
                .setFontColor(ColorConstants.WHITE)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(azulFuerte)
                .setPaddingTop(20f)
                .setPaddingBottom(8f)
            document.add(header)
            val subHeader = Paragraph("CONFIRMACIÓN DE CITA")
                .setFontSize(14f)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(azulFuerte)
                .setPaddingBottom(20f)
            document.add(subHeader)
            val caja = Table(UnitValue.createPercentArray(floatArrayOf(35f, 65f)))
                .useAllAvailableWidth()
                .setMarginTop(25f)
                .setBackgroundColor(fondoCaja)
                .setBorder(SolidBorder(azulResaltado, 1f))
            caja.addCell(celdaEtiqueta("👤 Nombre completo")).addCell(celdaDato("$nombre $apellido"))
            caja.addCell(celdaEtiqueta("🎂 Edad")).addCell(celdaDato("$edad años"))
            caja.addCell(celdaEtiqueta("📧 Correo")).addCell(celdaDato(correo))
            caja.addCell(celdaEtiqueta("📱 Teléfono")).addCell(celdaDato(telefono))
            caja.addCell(celdaEtiqueta("📝 Motivo")).addCell(celdaDato(motivo))
            caja.addCell(celdaEtiqueta("🏢 Servicio")).addCell(celdaDato(origen))
            document.add(caja)
            document.add(
                Paragraph("─".repeat(40))
                    .setFontColor(ColorConstants.LIGHT_GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20f))
            val pie = Paragraph("Gracias por elegirnos.\n¡Te esperamos!")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(11f)
                .setItalic()
                .setMarginTop(20f)
            document.add(pie)
            document.close()
            Toast.makeText(context, "📄 PDF generado: ${file.name}", Toast.LENGTH_SHORT).show()
            file } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "❌ Error al generar PDF", Toast.LENGTH_SHORT).show()
            null } }
    private fun celdaEtiqueta(text: String): Cell {
        return Cell()
            .add(Paragraph(text).setBold())
            .setBackgroundColor(DeviceRgb(230, 230, 230))
            .setFontSize(12f)
            .setPadding(8f)
            .setBorderBottom(SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f))
    }
    private fun celdaDato(text: String): Cell {
        return Cell()
            .add(Paragraph(text))
            .setFontSize(12f)
            .setPadding(8f)
            .setBorderBottom(SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f))
    }
}
