package pool

import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter

sealed trait Entity:
  val id: Int

final case class Pool(id: Int = 0, name: String, built: LocalDate, volume: Int = 10000) extends Entity

sealed trait Measurement extends Entity:
  val id: Int = 0
  val value: Double
  val dateMeasured: LocalDate = LocalDate.now
  val timeMeasured: LocalTime = LocalTime.now

  def newDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
  def newTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")

  given measurementOrdering: Ordering[Measurement] = Ordering.by(m => (m.dateMeasured.toEpochDay, m.timeMeasured.toSecondOfDay))

final case class FreeChlorine(value: Double) extends Measurement

final case class CombinedChlorine(value: Double) extends Measurement

final case class TotalChlorine(value: Double) extends Measurement

final case class pH(value: Double) extends Measurement

final case class CalciumHardness(value: Double) extends Measurement

final case class TotalAlkalinity(value: Double) extends Measurement

final case class CyanuricAcid(value: Double) extends Measurement

final case class TotalBromine(value: Double) extends Measurement

final case class Temperature(value: Double) extends Measurement