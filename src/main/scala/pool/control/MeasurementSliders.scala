package pool.control

import java.text.NumberFormat

import math.BigDecimal.double2bigDecimal

import scalafx.scene.control.{Label, Slider}
import scalafx.scene.layout.HBox
import scalafx.util.converter.FormatStringConverter

import pool.Measurement

/**
  * free chlorine (fc): 0 - 10, ok = 1 - 5, ideal = 3
  * combined chlorine (cc = tc - fc): 0 - 0.5, ok = 0.2, ideal = 0
  * total chlorine (tc = fc + cc): 0 - 10, ok = 1 - 5, ideal = 3
  * ph: 6.2 - 8.4, ok = 7.2 - 7.6, ideal = 7.4
  * calcium hardness (ch): 0 - 1000, ok = 250 - 500, ideal = 375
  * total alkalinity (ta): 0 - 240, ok = 80 - 120, ideal = 100
  * cyanuric acid (cya): 0 - 300, ok = 30 - 100, ideal = 50
  * total bromine (tb): 0 - 20, ok = 2 - 10, ideal = 5
  * temperature: 50 - 110
 */
object MeasurementSliders:
  def integerInstance = NumberFormat.getIntegerInstance
  def formatConverter(format: NumberFormat) = new FormatStringConverter[Number](format)

  def freeChlorineSlider(measurement: Measurement): HBox =
    val slider = new Slider {
      prefWidth = 600
      min = 0
      max = 10
      majorTickUnit = 1
      showTickLabels = true
      showTickMarks = true
      value = measurement.freeChlorine
    }
    val label = new Label {
      text = measurement.freeChlorine.toString
    }
    new HBox {
      spacing = 3; children = List(slider, label)
    }