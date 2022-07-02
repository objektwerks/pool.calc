package pool.pane

import scala.concurrent.ExecutionContext.Implicits.global

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}

import pool.{Context, Pool, UnitOfMeasure}
import pool.dialog.PoolDialog

class PoolsPane(context: Context) extends VBox:
  spacing = 6
  padding = Insets(6)

  val model = context.model

  val tableView = new TableView[Pool]() {
    columns ++= List(
      new TableColumn[Pool, String] {
        text = context.headerName
        cellValueFactory = _.value.nameProperty
      },
      new TableColumn[Pool, Int] {
        text = context.headerVolume
        cellValueFactory = _.value.volumeProperty
      },
      new TableColumn[Pool, String] {
        text = context.headerUnit
        cellValueFactory = _.value.unitProperty
      }
    )
    items = model.observablePools
  }

  val addButton = new Button {
    graphic = context.addImage
    text = context.buttonAdd
    disable = false
    onAction = { _ => add() }
  }

  val editButton = new Button {
    graphic = context.editImage
    text = context.buttonEdit
    disable = true
    onAction = { _ => update() }
  }

  val addEditButtonBar = new HBox {
    spacing = 6
    children = List(addButton, editButton)
  }
  
  val tab = new Tab {
  	text = context.labelPools
  	closable = false
  	content = new VBox {
      spacing = 6
      padding = Insets(6)
      children = List(tableView, addEditButtonBar)
    }
  }

  val tabPane = new TabPane {
    tabs = List(tab)
  }

  children = List(tabPane)
  VBox.setVgrow(tableView, Priority.Always)
  VBox.setVgrow(tabPane, Priority.Always)

  tableView.onMouseClicked = { event =>
    if (event.getClickCount == 2 && tableView.selectionModel().getSelectedItem != null) update()
  }

  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single
  
  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedItem) =>
    // model.update executes a remove and add on items. the remove passes a null selectedItem!
    if selectedItem != null then
      model.selectedPoolId.value = selectedItem.id
      editButton.disable = false
  }

  def add(): Unit =
    PoolDialog(context, Pool()).showAndWait() match
      case Some(pool: Pool) =>
        model
          .add(pool)
          .map(pool => tableView.selectionModel().select(pool))
          .recover { case error: Throwable => model.onError(error, "Pool add failed.") }
      case _ => model.onError("Pool add failed.")

  def update(): Unit =
    val selectedIndex = tableView.selectionModel().getSelectedIndex
    val pool = tableView.selectionModel().getSelectedItem.pool
    PoolDialog(context, pool).showAndWait() match
      case Some(pool: Pool) =>
        model
          .update(selectedIndex, pool)
          .map(pool => tableView.selectionModel().select(selectedIndex))
          .recover { case error: Throwable => model.onError(error, "Pool update failed.") }
      case _ => model.onError("Pool update failed.")