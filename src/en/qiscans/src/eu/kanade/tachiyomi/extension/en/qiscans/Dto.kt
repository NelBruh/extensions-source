package eu.kanade.tachiyomi.extension.en.qiscans

import eu.kanade.tachiyomi.source.model.SManga
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchResponse(
    val data: List<Manga>,
    val totalItems: Int,
    val totalPages: Int,

    @SerialName("current") val currentPage: Int,
    @SerialName("next") val nextPage: Int,
)

@Serializable
class Manga(
    val slug: String,
    val title: String,
    val cover: String,
    val status: String,
    val type: String,
) {
    fun toSManga() = SManga.create().apply {
        url = slug
        title = this@Manga.title
        thumbnail_url = cover
        status = when (this@Manga.status) {
            "ONGOING", "MASS_RELEASED" -> SManga.ONGOING
            "COMPLETED" -> SManga.ONGOING
            else -> SManga.UNKNOWN
        }
    }
}
