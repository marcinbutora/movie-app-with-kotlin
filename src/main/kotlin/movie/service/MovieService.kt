package movie.service

import movie.model.Movie
import movie.repository.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(private val movieRepository: MovieRepository) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getAllMovies(): Collection<Movie> {
        logger.info("Getting list of all movies")
        val moviesInDatabase = movieRepository.findAll().size
        logger.info(String.format("Movies in database: %d", moviesInDatabase))
        return movieRepository.findAll()
    }

    fun getMovieByTitle(title: String): Optional<Movie> {
        return movieRepository.findByTitle(title)
    }

    fun createMovie(movie: Movie): Movie {
        return movieRepository.save(movie)
    }
}