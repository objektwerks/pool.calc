package pool

import com.github.plokhotnyuk.jsoniter_scala.core.*

import java.net.URI
import java.net.http.{HttpClient, HttpRequest}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.Executors

import Serializer.given

final class Fetcher(url: String):
  private val client = HttpClient
                         .newBuilder()
                         .executor( Executors.newVirtualThreadPerTaskExecutor() )
                         .build()

  def call(command: Command,
           handler: Event => Unit): Any =
    val commandJson = writeToString[Command](command)

    val request = HttpRequest
      .newBuilder
      .uri(URI(url))
      .timeout(Duration.of(10, SECONDS))
      .version(HttpClient.Version.HTTP_2)
      .headers("Content-Type", "application/json; charset=UTF-8", "Accept", "application/json")
      .POST( HttpRequest.BodyPublishers.ofString(commandJson) )
      .build
    
    val response = client.send( request, BodyHandlers.ofString )

    val event = readFromString[Event]( response.body )
    handler(event)