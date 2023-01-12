package pool.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.VBox

import pool.{Context}
import pool.Client
import pool.chart.ChemicalsChart

final class ChemicalsChartDialog(context: Context) extends Dialog[Unit]:
  initOwner(Client.stage)
  title = context.windowTitle
  headerText = context.chartChemicals
  
  dialogPane().buttonTypes = List(ButtonType.Close)
  dialogPane().content = new VBox:
    spacing = 6
    children = List( ChemicalsChart(context) )