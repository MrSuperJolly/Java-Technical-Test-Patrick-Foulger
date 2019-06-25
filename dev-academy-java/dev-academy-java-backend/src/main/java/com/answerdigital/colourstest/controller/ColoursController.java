package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getcolour")
    public ResponseEntity<Colour> getColour(long id){

        Optional<Colour> colourByID = coloursRepository.findById(id);

        if(!colourByID.isPresent()){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<Colour> (colourByID.get(), HttpStatus.OK);
        }

    }

    @PutMapping("/addcolour")
    public ResponseEntity<Colour> addColour(Colour colour){

        if(colour != null){
            
            coloursRepository.saveAndFlush(colour);
            return new ResponseEntity<Colour> (colour, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }


    }
  

}
