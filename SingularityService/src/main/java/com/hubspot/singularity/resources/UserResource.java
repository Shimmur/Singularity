package com.hubspot.singularity.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.hubspot.singularity.SingularityUser;
import com.hubspot.singularity.SingularityUserSettings;
import com.hubspot.singularity.config.ApiPaths;
import com.hubspot.singularity.data.UserManager;

import io.dropwizard.auth.Auth;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Path(ApiPaths.USER_RESOURCE_PATH)
@Produces({ MediaType.APPLICATION_JSON })
@OpenAPIDefinition(
    info = @Info(title = "Retrieve or update settings for a particular user")
)
public class UserResource {
  private final UserManager userManager;

  @Inject
  public UserResource(UserManager userManager) {
    this.userManager = userManager;
  }

  @GET
  @Path("/settings")
  @Operation(summary = "Retrieve the settings for the current authenticated user")
  public SingularityUserSettings getUserSettings(@Auth SingularityUser user) {
    return userManager.getUserSettings(user.getId()).or(SingularityUserSettings.empty());
  }

  @POST
  @Path("/settings")
  @Operation(summary = "Update the settings for the current authenticated user")
  public void setUserSettings(
      @Auth SingularityUser user,
      @RequestBody(
          required = true,
          description = "The new settings to be saved for the currently authenticated user"
      ) SingularityUserSettings settings) {
    userManager.updateUserSettings(user.getId(), settings);
  }

  @POST
  @Consumes({ MediaType.APPLICATION_JSON })
  @Path("/settings/starred-requests")
  @Operation(summary = "Add starred requests for the current authenticated user")
  public void addStarredRequests(
      @Auth SingularityUser user,
      @RequestBody(required = true, description = "A SingularityUserSettings object containing the new starred requests for the currently authenticated user") SingularityUserSettings settings) {
    userManager.addStarredRequestIds(user.getId(), settings.getStarredRequestIds());
  }

  @DELETE
  @Consumes({ MediaType.APPLICATION_JSON })
  @Path("/settings/starred-requests")
  @Operation(summary = "Remove starred requests for the current authenticated user")
  public void deleteStarredRequests(
      @Auth SingularityUser user,
      @RequestBody(
          required = true,
          description = "A SingularityUserSettings object containing starred requests to remove for the currently authenticated user"
      ) SingularityUserSettings settings) {
    userManager.deleteStarredRequestIds(user.getId(), settings.getStarredRequestIds());
  }
}