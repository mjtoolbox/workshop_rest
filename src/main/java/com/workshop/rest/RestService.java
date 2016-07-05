package com.workshop.rest;

import com.google.gson.JsonObject;
import com.workshop.utility.Utility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/restservice")
@Api(value="/restservice", description = "Rest Service!")
public class RestService {

    @GET
    @Path("/{username}")
    @Produces("text/plain")
    @ApiOperation(value="Getting user name", notes="Simple hello with name")
    @ApiResponses(value={
            @ApiResponse(code=200, message= "OK"),
            @ApiResponse(code=500, message="Something wrong in the server")
    })
    public String getUsername(@PathParam("username") String aUserName) {
        return "Hello " + aUserName;
    }

    @GET
    @Path("/test")
    @Produces("application/json")
    @ApiOperation(value="Getting test message", notes="Print test message")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Response getTestMessage() {
        return Response.status(200).entity(Utility.loadProperties().getProperty("messageText")).build();
    }

    @GET
    @Path("/getjson")
    @Produces("application/json")
    @ApiOperation(value="Getting json string", notes="Outage related data")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Response getJson(){

        JsonObject json = Utility.convertFileToJSON("ORS.json");
        System.out.println("***********************************************************************");
        return Response.status(200).entity(json.toString()).build();

    }

    @GET
    @Path("/corstest")
    @Produces("application/json")
    @ApiOperation(value="Getting CORS test message", notes="Print test message")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Response getCORSTestMessage() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD") .header("Access-Control-Max-Age", "1209600")
                .entity("Header displays CORS related stuffs").build();
    }

}
