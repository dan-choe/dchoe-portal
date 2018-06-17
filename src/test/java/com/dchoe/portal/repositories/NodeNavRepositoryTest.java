package com.dchoe.portal.repositories;

import static org.junit.Assert.*;

import com.dchoe.portal.model.NodeNav;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@ActiveProfiles("Test NodeNav")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataMongo
public class NodeNavRepositoryTest {

    @Autowired
    private NodeNavRepository nodeNavRepository;

    @Before
    public void setup() throws Exception
    {
        NodeNav node1 = new NodeNav("menu1", 1, false, null);
        NodeNav node2 = new NodeNav("menu2", 1, false, null);
        assertNull(node1.getId());
        assertNull(node2.getId());

        nodeNavRepository.save(node1);
        nodeNavRepository.save(node2);
        assertNotNull(node1.getId());
        assertNotNull(node2.getId());
    }

    @Test
    public void whenFindNodeById()
    {
        NodeNav node1 = this.nodeNavRepository.findByName("menu1");
        assertNotNull(node1);
        assertEquals(false, node1.isHasChild());

        for(NodeNav node : nodeNavRepository.findAll())
        {
            System.out.println(node);
        }
    }

    @Test
    public void checkAll()
    {
        int count = 0;
        for(NodeNav node : nodeNavRepository.findAll())
        {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void checkChildNodes()
    {
        NodeNav parentNode = nodeNavRepository.findByName("menu2");
        NodeNav subNode1 = new NodeNav("menu2-1", 2, false, null);

        if(parentNode != null)
        {
            List<NodeNav> children = parentNode.getChildren();
            List<NodeNav> nodeNavList;

            if(children == null) {
                assertEquals(false, parentNode.isHasChild());

                nodeNavList = new ArrayList<>();
                parentNode.setChildren(nodeNavList);
                parentNode.setHasChild(true);
                nodeNavList.add(nodeNavRepository.save(subNode1));
                nodeNavRepository.save(parentNode);
            }
            else{
                assertEquals(true, parentNode.isHasChild());

                nodeNavList = parentNode.getChildren();
                nodeNavList.add(nodeNavRepository.save(subNode1));
                nodeNavRepository.save(parentNode);
            }
        }
    }

    @After
    public void cleanTest() throws Exception
    {
        nodeNavRepository.deleteAll();
    }
}