package com.adrhol.mafiaGame.presentation;

import com.adrhol.mafiaGame.presentation.model.Role;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class PresentationController {

    @GetMapping
    @CrossOrigin("http://localhost:4200/")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = new ArrayList<>();

        roles.add(new Role(1, "Detektyw", "Budzi się w nocy, sprawdza jednego gracza"));
        roles.add(new Role(2, "Mafia", "Bandyta, zabija mieszkańców"));
        roles.add(new Role(3, "Mieszkaniec", "Zazwyczaj ginie"));
        roles.add(new Role(4, "Boss Mafii", "Wskazany przez detektywa, nie ujawnia swojej tożsamości"));

        return ResponseEntity.ok(roles);
    }
}
