package movie.service

import movie.model.Movie
import movie.repository.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.RuntimeException
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
        return movieRepository.findTopByTitle(title)
    }

    fun deleteMovie(id: Int) {
        val foundedMovie = findMovie(id)
        return movieRepository.delete(foundedMovie.get())
    }

    fun createMovie(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    private fun findMovie(id: Int): Optional<Movie> {
        val foundedMovie = movieRepository.findMovieById(id)
        if (foundedMovie.isEmpty) {
            throw RuntimeException("Movie not found")
        }
        return foundedMovie
    }
}
