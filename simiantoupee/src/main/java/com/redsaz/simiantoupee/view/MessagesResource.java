/*
 * Copyright 2016 Redsaz <redsaz@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redsaz.simiantoupee.view;

import com.redsaz.simiantoupee.api.MessagesService;
import com.redsaz.simiantoupee.api.SimianToupeeMediaType;
import com.redsaz.simiantoupee.api.model.BasicMessage;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Provides endpoints for retrieving messages and info about messages.
 *
 * @author Redsaz <redsaz@gmail.com>
 */
@Path("/messages")
public class MessagesResource {

    private MessagesService messagesSrv;

    public MessagesResource() {
    }

    @Inject
    public MessagesResource(MessagesService messagesService) {
        messagesSrv = messagesService;
    }

    /**
     * Lists all of the messages.
     *
     * @return Messages, by URI and title.
     */
    @GET
    @Produces(SimianToupeeMediaType.BASIC_MESSAGES_V1_JSON)
    public Response listMessages() {
        return Response.ok(messagesSrv.getPreviewMessages()).build();
    }

    /**
     * Get the message contents.
     *
     * @param id The id of the message.
     * @param uriName The uri title of the message, not used in retrieving the
     * data but helpful for users which may read the message.
     * @return Message.
     */
    @GET
    @Produces({SimianToupeeMediaType.BASIC_MESSAGES_V1_JSON})
    @Path("{id}/{uriName}")
    public Response getMessage(@PathParam("id") String id, @PathParam("uriName") String uriName) {
        BasicMessage message = messagesSrv.getBasicMessage(id);
        if (message == null) {
            throw new NotFoundException("Could not find message id=" + id);
        }
        return Response.ok(message).build();
    }

    /**
     * Get the message contents.
     *
     * @param id The id of the message.
     * @return Message.
     */
    @GET
    @Produces({SimianToupeeMediaType.BASIC_MESSAGE_V1_JSON})
    @Path("{id}")
    public Response getMessageById(@PathParam("id") String id) {
        BasicMessage message = messagesSrv.getBasicMessage(id);
        if (message == null) {
            throw new NotFoundException("Could not find message id=" + id);
        }
        return Response.ok(message).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMessage(@PathParam("id") String id) {
        messagesSrv.deleteMessage(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
