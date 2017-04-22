package com.hathome;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;

/**
 * Created by Acer on 18/4/2560.
 */
public class EmailService {

    public static ClientResponse SendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-e924951343a83eb3141db1fdd6b747a3"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox73ea640fcd4b4c57969625f839326d4d.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "HatHome <postmaster@sandbox73ea640fcd4b4c57969625f839326d4d.mailgun.org>");
        formData.add("to", "thara <lanliwenwen@gmail.com>)");
        formData.add("subject", "Hello thara");
        formData.add("text", "<h1>Congratulations thara, you just sent an email with Mailgun!  You are truly awesome!</h1>");
        formData.add("html", "<h1>Congratulations thara, you just sent an email with Mailgun!  You are truly awesome!</h1>");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }
}

