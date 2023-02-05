package com.demus.controller;

import com.demus.model.controller.ControllerResponse;
import com.demus.model.controller.CurrentlyPlayingControllerResponse;
import com.demus.model.service.CurrentlyPlayingServiceResponse;
import com.demus.model.service.ServiceResponse;
import com.demus.model.user.User;
import com.demus.service.AddToQueueService;
import com.demus.service.GetCurrentlyPlayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private GetCurrentlyPlayingService getCurrentlyPlayingService;
    @Autowired
    private AddToQueueService addToQueueService;

    @GetMapping("api/get/currentlyplaying")
    public CurrentlyPlayingControllerResponse getCurrentlyPlaying(@RequestAttribute("token") String token) {
        CurrentlyPlayingControllerResponse controllerResponse = new CurrentlyPlayingControllerResponse();
        try {
            CurrentlyPlayingServiceResponse serviceResponse = getCurrentlyPlayingService.getCurrentlyPlaying(token);
            if (serviceResponse.isSuccess())
                controllerResponse.setCurrentlyPlaying(serviceResponse.getCurrentlyPlaying());
            controllerResponse.buildResponse(serviceResponse);
        } catch (Exception ex) {
            controllerResponse.buildControllerError(ex);
        }
        return controllerResponse;
    }

    @GetMapping(value = "api/addtoqueue")
    public ControllerResponse addToQueue(@RequestAttribute("token") String token, String uri) {
        ControllerResponse controllerResponse = new ControllerResponse();
        try {
            ServiceResponse serviceResponse = addToQueueService.addToQueue(token, uri);
            controllerResponse.buildResponse(serviceResponse);
        } catch (Exception ex) {
            controllerResponse.buildControllerError(ex);
        }
        return controllerResponse;
    }



}
