package com.dchoe.portal.controller;

import com.dchoe.portal.model.NodeNav;
import com.dchoe.portal.repositories.NodeNavRepository;
import com.dchoe.portal.repositories.PortalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nodenav")
@CrossOrigin("*")
public class NodeNavController
{
    @Autowired
    private NodeNavRepository nodeNavRepository;

    @GetMapping("/")
    public ResponseEntity<List<NodeNav>> getAllNode()
    {
        return new ResponseEntity<List<NodeNav>>(nodeNavRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNodeById(@PathVariable("id") String id)
    {
        return nodeNavRepository.findById(id)
                .map(node -> ResponseEntity.ok().body(node))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> addNode(@RequestBody NodeNav newNode)
    {
        nodeNavRepository.save(newNode);
        return ResponseEntity.ok().build();
//        return new ResponseEntity<String>("Added Response", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeNode(@PathVariable("id") String id)
    {
        return nodeNavRepository.findById(id)
                .map(x -> {
                    nodeNavRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
