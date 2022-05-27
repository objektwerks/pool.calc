package pool

import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter

sealed trait Entity:
  val id: Int = 0
  val date: LocalDate = LocalDate.now
  val time: LocalTime = LocalTime.now

  def newDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

  def newTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")

final case class Pool(volume: Int = 0) extends Entity