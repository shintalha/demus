package com.demus.controller;

import com.demus.model.controller.ControllerResponse;
import com.demus.model.controller.CurrentlyPlayingControllerResponse;
import com.demus.model.controller.GetPlaylistTracksControllerResponse;
import com.demus.model.controller.GetUsersPlaylistsControllerResponse;
import com.demus.model.service.CurrentlyPlayingServiceResponse;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.demus.model.service.ServiceResponse;
import com.demus.model.user.User;
import com.demus.service.AddToQueueService;
import com.demus.service.GetCurrentlyPlayingService;
import com.demus.service.GetPlaylistTracksService;
import com.demus.service.GetUsersPlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private GetCurrentlyPlayingService getCurrentlyPlayingService;
    @Autowired
    private AddToQueueService addToQueueService;
    @Autowired
    private GetUsersPlaylistsService getUsersPlaylistsService;

    @GetMapping("api/get/currentlyplaying")
    public CurrentlyPlayingControllerResponse getCurrentlyPlaying(@RequestAttribute("token") String token) {
        CurrentlyPlayingControllerResponse controllerResponse = new CurrentlyPlayingControllerResponse();
        try {
            CurrentlyPlayingServiceResponse serviceResponse = getCurrentlyPlayingService.getCurrentlyPlaying(token);
            if (serviceResponse.isSuccess())
                controllerResponse.setCurrentlyPlaying(serviceResponse.getCurrentlyPlaying());
            controllerResponse.constructResponse(serviceResponse);
        } catch (Exception ex) {
            controllerResponse.constructControllerError(ex);
        }
        return controllerResponse;
    }

    @GetMapping(value = "api/addtoqueue")
    public ControllerResponse addToQueue(@RequestAttribute("token") String token, @RequestParam("uri") String uri) {
        ControllerResponse controllerResponse = new ControllerResponse();
        try {
            ServiceResponse serviceResponse = addToQueueService.addToQueue(token, uri);
            controllerResponse.constructResponse(serviceResponse);
        } catch (Exception ex) {
            controllerResponse.constructControllerError(ex);
        }
        return controllerResponse;
    }

    @GetMapping(value = "api/get/usersplaylists")
    public GetUsersPlaylistsControllerResponse getUserPlaylists(@RequestAttribute("token") String token) {
        GetUsersPlaylistsControllerResponse controllerResponse = new GetUsersPlaylistsControllerResponse();
        try {
            GetUsersPlaylistsServiceResponse serviceResponse = getUsersPlaylistsService.getUsersPlaylists(token);
            if (serviceResponse.isSuccess())
                controllerResponse.setUsersPlaylists(serviceResponse.getUsersPlaylists());
            controllerResponse.constructResponse(serviceResponse);
        } catch (Exception ex) {
            controllerResponse.constructControllerError(ex);
        }
        return controllerResponse;
    }
}
