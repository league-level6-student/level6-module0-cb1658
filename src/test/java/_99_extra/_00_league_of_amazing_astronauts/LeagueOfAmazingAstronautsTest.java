package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();
    
    @Mock
    Rocketship r;
    
    @Mock
    Astronaut a;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	underTest.setRocketship(r);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given

    	
    	
        //when
    	
    	underTest.prepareAstronaut(a);

        //then
    	
    	verify(a, times(1)).train();
    	verify(r, times(1)).loadOccupant(a);
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	
    	when(r.isLoaded()).thenReturn(true);

        //when
    	
    	underTest.launchRocket("Mars");

        //then
    	
    	verify(r, times(1)).setDestination("Mars", 68_000_000);
    	verify(r, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given

    	when(r.isLoaded()).thenReturn(true);
    	
        //when
    	
        //then
    	
    	Throwable t = assertThrows(IllegalArgumentException.class, () -> underTest.launchRocket("BobLand"));
    	assertEquals(t.getMessage(), "Destination is unavailable");
    	verify(r, never()).setDestination("BobLand", 68000000);
    	verify(r, never()).launch();
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	
    	when(r.isLoaded()).thenReturn(false);

        //when
        
    	//then
    	
    	Throwable t = assertThrows(IllegalStateException.class, () -> underTest.launchRocket("Mars"));
    	assertEquals(t.getMessage(), "Rocketship is not loaded");
    	verify(r, never()).setDestination("BobLand", 68000000);
    	verify(r, never()).launch();

    }
}