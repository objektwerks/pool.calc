package pool.pane

import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}

import pool.Context

trait AddEditButtonBar(val context: Context):
  val addButton = new Button {
    graphic = context.addImage
    text = context.paneAdd
  }

  val editButton = new Button {
    graphic = context.editImage
    text = context.paneEdit
    disable = true
  }

  val addEditButtonBar = new HBox {
    spacing = 6
    children = List(addButton, editButton)
  }