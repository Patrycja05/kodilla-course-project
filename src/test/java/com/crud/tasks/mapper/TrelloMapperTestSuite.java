package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToList(){
        //Given
        TrelloListsDto testListsDto1 = new TrelloListsDto("1", "test1", true);
        TrelloListsDto testListsDto2 = new TrelloListsDto("2", "test2", false);
        List<TrelloListsDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(testListsDto1);
        trelloListsDto.add(testListsDto2);

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListsDto);

        //Then
        Assert.assertEquals("1", trelloList.get(0).getId());
        Assert.assertEquals("test2", trelloList.get(1).getName());
        Assert.assertEquals(false, trelloList.get(1).isClosed());
    }

    public List<TrelloListsDto> mapToListDto(final  List<TrelloList> trelloLists){
        return trelloLists.stream()
                .map(trelloList -> new TrelloListsDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    @Test
    public void testMapToListDto(){
        //Given
        TrelloList testList1 = new TrelloList("1", "test1", true);
        TrelloList testLists2 = new TrelloList("2", "test2", false);
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(testList1);
        trelloList.add(testLists2);

        //When
        List<TrelloListsDto> trelloListsDto = trelloMapper.mapToListDto(trelloList);

        //Then
        Assert.assertEquals("1", trelloListsDto.get(0).getId());
        Assert.assertEquals("test2", trelloListsDto.get(1).getName());
        Assert.assertEquals(false, trelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToBoards(){
        //Given
        TrelloListsDto testListsDto1 = new TrelloListsDto("1", "test1", true);
        TrelloListsDto testListsDto2 = new TrelloListsDto("2", "test2", false);
        List<TrelloListsDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(testListsDto1);
        trelloListsDto.add(testListsDto2);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "List1", trelloListsDto);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        TrelloBoard trelloBoard = trelloBoardList.get(0);
        TrelloList trelloList1 = trelloBoard.getLists().get(0);
        TrelloList trelloList2 = trelloBoard.getLists().get(1);

        //Then
        Assert.assertEquals("1", trelloBoard.getId());
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals("test1", trelloList1.getName());
        Assert.assertEquals(false, trelloList2.isClosed());
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        TrelloList testTrelloList1 = new TrelloList("1", "test1", true);
        TrelloList testTrelloList2 = new TrelloList("2", "test2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(testTrelloList1);
        trelloLists.add(testTrelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "List1", trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        TrelloBoardDto trelloBoardDto = trelloBoardDtoList.get(0);

        //Then
        Assert.assertEquals("1", trelloBoardDto.getId());
        Assert.assertEquals(1, trelloBoardDtoList.size());
        Assert.assertEquals("test1", trelloLists.get(0).getName());
        Assert.assertEquals(false, trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card1", "Test card", "1", "id:134");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("Card1", trelloCard.getName());
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("Card1", "Test card", "1", "id:134");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.maptoCardDto(trelloCard);

        //Then
        Assert.assertEquals("1", trelloCard.getPos());
    }
}
