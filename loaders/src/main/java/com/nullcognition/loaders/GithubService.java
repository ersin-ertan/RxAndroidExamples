package com.nullcognition.loaders;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mms on 12/7/16.
 */
public class GithubService {

  public static class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
      this.login = login;
      this.contributions = contributions;
    }
  }

  public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors") Observable<List<Contributor>> contributors(
        @Path("owner") String owner, @Path("repo") String repo);
  }
}