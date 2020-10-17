package com.crud.tasks.trello.domain;

import com.crud.tasks.domain.AttachmentsByType;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.Trello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadgesTestSuite {

    @Test
    public void getterTest(){
        //Given
        Trello trello1 = new Trello(1,1);
        Trello trello2 = new Trello(1,2);

        Badges badge1 = new Badges(721, new AttachmentsByType(trello1));
        Badges badge2 = new Badges(330, new AttachmentsByType(trello2));

        //When

        //Then
        assertEquals(721, badge1.getVotes());
        assertEquals(2, badge2.getAttachments().getTrello().getCard());
    }
}
