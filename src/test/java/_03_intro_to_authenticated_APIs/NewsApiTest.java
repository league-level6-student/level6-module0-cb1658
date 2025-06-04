package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;
    
    @Mock
    WebClient wcM;
    
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<ApiExampleWrapper> aewMonoMock;
    
    @Mock
    ApiExampleWrapper aew;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	newsApi = new NewsApi();
    	newsApi.setWebClient(wcM);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	
    	String topic = "Carrots";
    	
    	ApiExampleWrapper expectedAEW = new ApiExampleWrapper();
    	
    	Article article = new Article();
    	List<Article> expectedArticle = new ArrayList<>();
    	expectedArticle.add(article);
    	
    	when(wcM.get()).thenReturn(requestHeadersUriSpecMock);
    	when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
    	when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
    	when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(aewMonoMock);
    	
    	when(aewMonoMock.block()).thenReturn(expectedAEW);

        //when
        
        ApiExampleWrapper realVal = newsApi.getNewsStoryByTopic(topic);
        realVal.setArticles(expectedArticle);
        List<Article> realArticle = realVal.getArticles();

        //then
        
        verify(wcM, times(1)).get();
        assertEquals(expectedAEW, realVal);
        assertEquals(expectedArticle, realArticle);
    }

    @Test
    void itShouldFindStory(){
    	String topic = "Banana";
        String storyTitle = "Climate Crisis Threatens the Banana, the World's Most Popular Fruit";
        String articleLink = "https://news.slashdot.org/story/25/05/12/1535204/climate-crisis-threatens-the-banana-the-worlds-most-popular-fruit";

        Article result = new Article();
        result.setTitle(storyTitle);
        result.setUrl(articleLink);
        ApiExampleWrapper aew_ = new ApiExampleWrapper();
        ArrayList<Article> a = new ArrayList<>();
        a.add(result); aew_.setArticles(a);

        when(wcM.get()).thenReturn(requestHeadersUriSpecMock);
    	when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
    	when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
    	when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(aewMonoMock);
    	when(aewMonoMock.block()).thenReturn(aew_);

        String expectedArticle =
                storyTitle + " -\n"
                        + articleLink;
        //when
        String actualArticle = newsApi.findStory(topic);

        //then
        verify(wcM, times(1)).get();

    }


}