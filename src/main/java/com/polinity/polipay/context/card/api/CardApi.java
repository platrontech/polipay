package com.polinity.polipay.context.card.api;

import com.polinity.polipay.commons.api.BaseController;
import com.polinity.polipay.commons.api.model.ApiResponse;
import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.card.CardCommandService;
import com.polinity.polipay.context.card.CardQueryService;
import com.polinity.polipay.context.card.api.model.CardTwin;
import com.polinity.polipay.context.card.api.model.SaveCardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardApi extends BaseController {

    private final CardQueryService cardQueryService;
    private final CardCommandService cardCommandService;

    @GetMapping("/user/{userId}")
    public ApiResponse<BaseListResponse<CardTwin>> login(@PathVariable("userId") String userId) {
        return ok(cardQueryService.retrieveCreditCards(userId));
    }

    @PostMapping("/user/{userId}")
    public ApiResponse<DoneResponse> addAdminUser(@PathVariable("userId") String userId,
                                                  @RequestBody @Valid SaveCardRequest saveCardRequest) {
        saveCardRequest.setUserId(userId);
        return ok(cardCommandService.saveCard(saveCardRequest));
    }

    @DeleteMapping("/{cardId}/user/{userId}/")
    public ApiResponse<DoneResponse> deleteCard(@PathVariable("userId") String userId,
                                                @PathVariable("cardId") String cardId) {
        return ok(cardCommandService.deleteCard(userId, cardId));
    }
}
