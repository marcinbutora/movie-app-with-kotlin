package movie.service

import movie.category.Category
import movie.model.Movie
import movie.repository.MovieRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MovieServiceTest(
    @Autowired
    private var movieService: MovieService,
    @Autowired
    private var movieRepository: MovieRepository
) {
    @Test
    fun `should return correct number of movies in db`() {
        // given
        thereIsAMovie(1, "Titanic", 1997, Category.ROMANCE)
        thereIsAMovie(2, "Ace Ventura", 1995, Category.COMEDY)
        // when
        val allMovies = movieService.getAllMovies()
        val allMoviesCount = allMovies.size
        //then
        assertThat(allMoviesCount).isEqualTo(2)
    }

    @Test
    fun `should find movie by title`() {
        // given
        val movie1 = thereIsAMovie(1, "Titanic", 1997, Category.ROMANCE)
        // when
        val movieByTitle = movieService.getMovieByTitle("Titanic")
        //then
        assertThat(movieByTitle.get().title).isEqualTo(movie1.title)
    }

    @Test
    fun `should delete movie by given id`() {
        // given
        val movie1 = thereIsAMovie(1, "Titanic", 1997, Category.ROMANCE)
        // when
        movieService.deleteMovie(2)
        val movieByTitle = movieService.getMovieByTitle(movie1.title)
        // then
        assertThat(movieByTitle).isNotPresent
    }

    fun thereIsAMovie(id: Int, title: String, movieYear: Int, category: Category): Movie {
        return movieRepository.save(Movie(id, title, movieYear, category))
    }
}