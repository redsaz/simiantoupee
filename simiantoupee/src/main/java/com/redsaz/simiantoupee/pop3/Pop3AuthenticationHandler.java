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
package com.redsaz.simiantoupee.pop3;

import com.redsaz.simiantoupee.api.MessagesService;
import org.apache.james.protocols.pop3.POP3Session;
import org.apache.james.protocols.pop3.core.AbstractPassCmdHandler;
import org.apache.james.protocols.pop3.mailbox.Mailbox;

/**
 *
 * @author Redsaz <redsaz@gmail.com>
 */
public class Pop3AuthenticationHandler extends AbstractPassCmdHandler {

    private final MessagesService msgSrv;

    public Pop3AuthenticationHandler(MessagesService messagesService) {
        msgSrv = messagesService;
    }

    @Override
    protected Mailbox auth(POP3Session session, String username, String password) throws Exception {
        Pop3PersistingMessageMailbox jm = new Pop3PersistingMessageMailbox(msgSrv);
        return jm;
    }
}
