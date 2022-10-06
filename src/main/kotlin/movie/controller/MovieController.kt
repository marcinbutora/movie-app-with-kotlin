package movie.controller

import movie.model.Movie
import movie.service.MovieService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/movies")
class MovieController(private val movieService: MovieService) {
    @GetMapping
    fun getMovies(): Collection<Movie> = movieService.getAllMovies()

    @GetMapping("/{title}")
    fun getMovieByTitle(@PathVariable title: String): Optional<Movie> {
        return movieService.getMovieByTitle(title)
    }

    @PostMapping
    fun createMovie(@RequestBody movie: Movie): Movie {
        return movieService.createMovie(movie)
    }
}