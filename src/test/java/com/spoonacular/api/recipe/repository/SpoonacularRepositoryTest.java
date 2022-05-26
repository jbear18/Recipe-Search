package com.spoonacular.api.recipe.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

public class SpoonacularRepositoryTest {

    private SpoonacularRepository spoonacularRepository;

    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<SpoonacularResponse> LocResponseMonoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        spoonacularRepository = new SpoonacularRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnLocResponse() {
        //given
        String query = "Java";
        SpoonacularResponse spoonacularResponse = new SpoonacularResponse();
        Result result = new Result();
        result.setTitle("Java: A Drink, an Island, and a Programming Language");
        result.setAuthors(Collections.singletonList("AUTHOR"));
        result.setLink("LINK");
        List<Result> expectedResults = Collections.singletonList(result);
        locResponse.setResults(expectedResults);

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(LocResponse.class))
                .thenReturn(LocResponseMonoMock);
        when(LocResponseMonoMock.block())
                .thenReturn(locResponse);

        //when
        List<Result> actualLocResults = locRepository.getResults(query);

        //then
        assertEquals(expectedResults, actualLocResults);
    }

}
