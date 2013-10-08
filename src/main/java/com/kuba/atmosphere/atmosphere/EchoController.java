package com.kuba.atmosphere.atmosphere;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("echo")
@PreAuthorize("isAuthenticated()")
public class EchoController {

    @RequestMapping()
    public void echo(){}


    @RequestMapping(value = "message", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public EchoMessage register(AtmosphereResource atmosphereResource) {
        System.out.println("Registering");

        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup("/echo",true);
        broadcaster.addAtmosphereResource(atmosphereResource);

        atmosphereResource.suspend();

        return new EchoMessage("Hello from "+ SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
