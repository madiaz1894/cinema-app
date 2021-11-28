package com.marcos.projects

import org.springframework.http.MediaType


internal sealed class ExtensionMediaType(val extension: String, val mediaType: String) {
    class PNG : ExtensionMediaType(".png", MediaType.IMAGE_PNG_VALUE)
    class JPG : ExtensionMediaType(".jpg", MediaType.IMAGE_JPEG_VALUE)
    class JPEG : ExtensionMediaType(".jpeg", MediaType.IMAGE_JPEG_VALUE)
    class PDF : ExtensionMediaType(".pdf", MediaType.APPLICATION_PDF_VALUE)
    class XLSX : ExtensionMediaType(".xlsx", XLSX_MEDIA_TYPE)
    class CSV : ExtensionMediaType(".csv", CSV_MEDIA_TYPE)
    class ZIP : ExtensionMediaType(".zip", ZIP_MEDIA_TYPE)
    class Unknown : ExtensionMediaType("unknown", "unknown")

    companion object {

        const val CSV_MEDIA_TYPE = "text/csv"
        const val XLSX_MEDIA_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        const val ZIP_MEDIA_TYPE = "application/zip"

        fun fromExtension(extension: String): ExtensionMediaType {
            return when (extension) {
                ".jpeg" -> JPEG()
                ".pdf" -> PDF()
                ".jpg" -> JPG()
                ".png" -> PNG()
                ".xlsx" -> XLSX()
                ".csv" -> CSV()
                ".zip" -> ZIP()
                else -> Unknown()
            }
        }
    }
}