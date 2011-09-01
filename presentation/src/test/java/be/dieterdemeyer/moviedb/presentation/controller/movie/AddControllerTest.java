package be.dieterdemeyer.moviedb.presentation.controller.movie;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import be.dieterdemeyer.moviedb.application.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import be.dieterdemeyer.moviedb.test.TestCase;

public class AddControllerTest extends TestCase {

    private AddController addController;
    
    @Mock
    private MovieService movieServiceMock;
    
    @Before
    public void setUpSubject() {
        addController = new AddController(movieServiceMock);
    }
    
    @Test
    public void saveMoviesCallsService() {
        String result = addController.save();
        
        assertEquals("success", result);
        verify(movieServiceMock).add(addController.getMovie());
    }
    
}