package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.dto.PersonUpdateDTO;
import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.model.Person;
import com.answerdigital.colourstest.repository.PeopleRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PeopleController.class)
@AutoConfigureMockMvc
public class PeopleControllerTest {

    @MockBean
    private PeopleRepository peopleRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllCallsRepository() throws Exception {
        // Given
        given(peopleRepository.findAll()).willReturn(getTestPeople());

        // When
        mvc.perform(get("/people").accept(APPLICATION_JSON));

        // Then
        verify(peopleRepository).findAll();
    }

    @Test
    public void testGetAllReturnsOk() throws Exception {
            // Given
        given(peopleRepository.findAll()).willReturn(Collections.emptyList());

        // When
        ResultActions result = mvc.perform(get("/people").accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetAllReturnsEmptyList() throws Exception {
        // Given
        given(peopleRepository.findAll()).willReturn(Collections.emptyList());

        // When
        ResultActions result = mvc.perform(get("/people").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllReturnsPeople() throws Exception {
        // Given
        given(peopleRepository.findAll()).willReturn(Arrays.asList(new Person[]{
            new Person(1001L, "Fred", "Wesley", true, true, Arrays.asList(new Colour[]{new Colour(2001L, "Red"), new Colour(2002L, "Green")})),
            new Person(1002L, "George", "Wesley", false, true, Arrays.asList(new Colour[]{new Colour(2003L, "Blue")})),
            new Person(1003L, "Neville", "Longbottom", false, false, Collections.emptyList())
        }));

        // When
        ResultActions result = mvc.perform(get("/people").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json("["
                + "{'id':1001, 'firstName':'Fred', 'lastName':'Wesley', 'authorised':true, 'enabled':true,"
                + " 'colours':[{'id':2001, 'name':'Red'},{'id':2002, 'name':'Green'}]},"
                + "{'id':1002, 'firstName':'George', 'lastName':'Wesley', 'authorised':false, 'enabled':true,"
                + " 'colours':[{'id':2003, 'name':'Blue'}]},"
                + "{'id':1003, 'firstName':'Neville', 'lastName':'Longbottom', 'authorised':false, 'enabled':false,"
                + " 'colours':[]}]"));
    }

    @Test
    public void testGetCallsRepository() throws Exception {
        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}))));

        // When
        mvc.perform(get("/people/1").accept(APPLICATION_JSON));

        // Then
        verify(peopleRepository).findById(eq(1L));
    }

    @Test
    public void testGetReturnsOk() throws Exception {
            // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}))));

        // When
        ResultActions result = mvc.perform(get("/people/1").accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetReturnsNotFound() throws Exception {
            // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.empty());

        // When
        ResultActions result = mvc.perform(get("/people/1").accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testGetReturnsPerson() throws Exception {
        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}))));

        // When
        ResultActions result = mvc.perform(get("/people/1").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json(
                "{'id':1, 'firstName':'Bo', 'lastName':'Bob', 'authorised':true, 'enabled':false, 'colours':[{'id':1, 'name':'Red'}]}"
            ));
    }

    @Test
    public void testUpdateCallsRepository() throws Exception {
        final Person originalPerson = new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}));
        final Person updatedPerson = new Person(1L, "Bo", "Bob", false, true, Arrays.asList(new Colour[]{new Colour(3L, "Blue")}));

        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(originalPerson));
        given(peopleRepository.save(any())).willReturn(updatedPerson);

        // When
        ResultActions result = mvc.perform(
            put("/people/1")
                .content(
                    "{\"authorised\":false, \"enabled\":true, \"colours\":[{\"id\":3,\"name\":\"Blue\"}]}")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(APPLICATION_JSON));

        // Then
        verify(peopleRepository).findById(eq(1L));
        verify(peopleRepository).save(eq(updatedPerson));
    }

    @Test
    public void testUpdateReturnsNotFound() throws Exception {
        final Person originalPerson = new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}));
        final Person updatedPerson = new Person(1L, "Bo", "Bob", false, true, Arrays.asList(new Colour[]{new Colour(3L, "Blue")}));

        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.empty());

        // When
        ResultActions result = mvc.perform(
            put("/people/1")
                .content(
                    "{\"authorised\":false, \"enabled\":true, \"colours\":[{\"id\":3,\"name\":\"Blue\"}]}")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateReturnsOk() throws Exception {
        final Person originalPerson = new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}));
        final Person updatedPerson = new Person(1L, "Bo", "Bob", false, true, Arrays.asList(new Colour[]{new Colour(3L, "Blue")}));

        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(originalPerson));
        given(peopleRepository.save(any())).willReturn(updatedPerson);

        // When
        ResultActions result = mvc.perform(
            put("/people/1")
                .content(
                    "{\"authorised\":false, \"enabled\":true, \"colours\":[{\"id\":3,\"name\":\"Blue\"}]}")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    public void testUpdateReturnsPerson() throws Exception {
        final Person originalPerson = new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")}));
        final Person updatedPerson = new Person(1L, "Bo", "Bob", false, true, Arrays.asList(new Colour[]{new Colour(3L, "Blue")}));

        // Given
        given(peopleRepository.findById(anyLong())).willReturn(Optional.of(originalPerson));
        given(peopleRepository.save(any())).willReturn(updatedPerson);

        // When
        ResultActions result = mvc.perform(
            put("/people/1")
                .content(
                    "{\"authorised\":false, \"enabled\":true, \"colours\":[{\"id\":3,\"name\":\"Blue\"}]}")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json(
                "{'id':1, 'firstName':'Bo', 'lastName':'Bob', 'authorised':false, 'enabled':true, 'colours':[{'id':3, 'name':'Blue'}]}"
            ));
    }

    private PersonUpdateDTO personUpdateDTO(Boolean authorised, Boolean enabled, Colour... colours) {
        PersonUpdateDTO p = new PersonUpdateDTO();
        p.setAuthorised(authorised);
        p.setEnabled(enabled);
        p.setColours(newArrayList(colours));
        return p;
    }

    private static List<Person> getTestPeople() {
        List<Person> people = Arrays.asList(new Person[]{
            new Person(1L, "Bo", "Bob", true, false, Arrays.asList(new Colour[]{new Colour(1L, "Red")})),
            new Person(2L, "Brian", "Allen", true, true, Arrays.asList(new Colour[]{new Colour(1L, "Red"), new Colour(2L, "Green"), new Colour(3L, "Blue")}))
        });
        return people;
    }
}
