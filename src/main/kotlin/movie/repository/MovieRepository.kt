package movie.repository

import movie.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByTitle(title: String): Optional<Movie>
}