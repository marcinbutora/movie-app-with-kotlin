package movie.model

import movie.category.Category
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Movie(@Id
                 @GeneratedValue(strategy = GenerationType.AUTO)
                 var id: Int,
                 var title: String,
                 var movieYear: Int,
                 var category: Category
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (title != other.title) return false
        if (movieYear != other.movieYear) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + movieYear
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String {
        return "Movie(id=$id, title='$title', movieYear=$movieYear, category=$category)"
    }


}
