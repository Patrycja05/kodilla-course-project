package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {
    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() /*throws Exception*/{
        /*Method method = TrelloClient.class.getDeclaredMethod("getTrelloBoards");
        method.setAccessible(true);
        method.invoke(trelloClient);*/

        /*List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards().stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla") && trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .collect(Collectors.toList());

        trelloBoards.forEach(trelloBoardDto -> { System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

        });*/
        return trelloService.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloService.createdTrelloCard((trelloCardDto));
    }
}
